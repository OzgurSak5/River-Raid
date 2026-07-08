package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import enemy.Enemy;
import enemy.Helicopter;
import entity.Bullet;
import entity.FuelDepot;
import entity.Player;
import game.GameConstants;
import game.GameContext;
import graphics.Camera;
import input.KeyInput;
import map.RiverSegment;
import map.World;

public class PlayState extends State{
	private final Player player;
	private final Camera camera;
	private final List<Bullet> bullets = new ArrayList<>();
	private final World world = new World();
	private final List<Enemy> enemies = new ArrayList<>();
	private double nextEnemySpawnY;
	private final GameContext context = new GameContext();
	private final List<FuelDepot> fuelDepots = new ArrayList<>();
	private double nextDepotSpawnY;

	public PlayState(StateManager manager) {
		super(manager);
		double startX = (GameConstants.WIDTH - GameConstants.PLAYER_WIDTH) / 2.0;
		double startY = 600;
		this.player = new Player(startX,startY);
		this.camera = new Camera(player,GameConstants.SCROLL_SPEED);
		this.nextEnemySpawnY = 0;
		this.nextDepotSpawnY = -200;
	}
	
	private void handlePlayerDeath() {
		context.loseLife();
		
		if(context.isGameOver()) {
			manager.setState(new GameOverState(manager));
			return;
		}
		
		double respawnX = (GameConstants.WIDTH - GameConstants.PLAYER_WIDTH) / 2.0;
		player.respawn(respawnX, player.getY());
		double safeDistance = 150;
		enemies.removeIf(e -> Math.abs(e.getY() - player.getY()) < safeDistance);
		context.addFuel(GameConstants.RESPAWN_FUEL);
	}
	
	private void spawnFuelDepot(double worldY) {
	    RiverSegment segment = world.getSegmentAt(worldY);
	    
	    if (segment == null) {
	        return;
	    }
	    
	    int leftBank = segment.getLeftBank();
	    int rightBank = segment.getRightBank();
	    double centerX = (leftBank + rightBank) / 2.0 - GameConstants.FUEL_DEPOT_WIDTH / 2.0;
	    fuelDepots.add(new FuelDepot(centerX, worldY));
	}
	
	private void spawnEnemy(double worldY) {
	    RiverSegment segment = world.getSegmentAt(worldY);
	    
	    if (segment == null) {
	        return;
	    }
	    
	    int leftBank = segment.getLeftBank();
	    int rightBank = segment.getRightBank();
	    double centerX = (leftBank + rightBank) / 2.0 - GameConstants.ENEMY_WIDTH / 2.0;
	    Helicopter heli = new Helicopter(centerX, worldY, leftBank, rightBank);
	    enemies.add(heli);
	}

	@Override
	public void update(KeyInput input) {
		if(input.isDown(KeyEvent.VK_ESCAPE)) {
			manager.setState(new MenuState(manager));
			return;
		}
		
		player.handleInput(input);
		double deltaTime = 1.0 / GameConstants.FPS;
		player.update(deltaTime);
		camera.update(deltaTime);
		context.consumeFuel(GameConstants.FUEL_CONSUMPTION * deltaTime);

		if (context.isOutOfFuel()) {
		    handlePlayerDeath();
		    return;
		}
		world.update(camera);
		
		if(world.collidesWithBank(player)) {
			handlePlayerDeath();
			return;
		}
		
		while (nextEnemySpawnY > camera.getY() - GameConstants.HEIGHT) {
		    spawnEnemy(nextEnemySpawnY);
		    nextEnemySpawnY -= GameConstants.ENEMY_SPAWN_GAP;
		}
		
		for (Enemy e : enemies) {
		    e.update(deltaTime);
		}
		
		while(nextDepotSpawnY > camera.getY() - GameConstants.HEIGHT) {
			spawnFuelDepot(nextDepotSpawnY);
			nextDepotSpawnY -= GameConstants.FUEL_DEPOT_SPAWN_GAP;
		}
		
		for(FuelDepot depot : fuelDepots) {
			if(player.intersects(depot)) {
				context.addFuel(GameConstants.FUEL_REFILL_RATE * deltaTime);
			}
		}
		
		
		
		if (input.isDown(KeyEvent.VK_SPACE) && player.canShoot()) {
	        bullets.add(player.shoot());
	    }
		
		for (Bullet b : bullets) {
	        b.update(deltaTime);
	    }
		
		for (Bullet b : bullets) {
		    for (Enemy e : enemies) {
		        if (b.isAlive() && e.isAlive() && b.intersects(e)) {
		            b.kill();
		            e.kill();
		            context.addScore(e.getPoints());
		        }
		    }
		}
		
		for (Enemy e : enemies) {
		    if (e.isAlive() && player.intersects(e) && !player.isInvincible()) {
		        handlePlayerDeath();
		        return;
		    }
		}
		
		bullets.removeIf(b -> {
			if(!b.isAlive()) {
				return true;
			}
	        int screenY = camera.worldToScreenY(b.getY());
	        return screenY < -50 || screenY > GameConstants.HEIGHT + 50;
	    });
		
		enemies.removeIf(e -> !e.isAlive());
		enemies.removeIf(e -> camera.worldToScreenY(e.getY()) > GameConstants.HEIGHT + 50);
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(GameConstants.COLOR_WATER);
		g.fillRect(0, 0, GameConstants.WIDTH, GameConstants.HEIGHT);
		world.render(g, camera);
		
		for (Bullet b : bullets) {
	        b.render(g, camera);
	    }
		
		for (Enemy e : enemies) {
		    e.render(g, camera);
		}
		
		for (FuelDepot depot : fuelDepots) {
		    depot.render(g, camera);
		}
		
        player.render(g, camera);
        //TEST İÇİN HUD
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("SCORE: " + context.getScore(), 10, 40);
        g.drawString("FUEL: " + (int) context.getFuel(), 10, 65);
        g.drawString("LIVES: " + context.getLives(), 10, 90);
	}
	
}
