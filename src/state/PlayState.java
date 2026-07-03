package state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import enemy.Enemy;
import enemy.Helicopter;
import entity.Bullet;
import entity.Player;
import game.GameConstants;
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

	public PlayState(StateManager manager) {
		super(manager);
		
		double startX = (GameConstants.WIDTH - GameConstants.PLAYER_WIDTH) / 2.0;
		double startY = 600;
		this.player = new Player(startX,startY);
		this.camera = new Camera(player,GameConstants.SCROLL_SPEED);
		this.nextEnemySpawnY = 0;
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
		world.update(camera);
		
		if(world.collidesWithBank(player)) {
			player.kill();
			manager.setState(new GameOverState(manager));
			return;
		}
		
		while (nextEnemySpawnY > camera.getY() - GameConstants.HEIGHT) {
		    spawnEnemy(nextEnemySpawnY);
		    nextEnemySpawnY -= GameConstants.ENEMY_SPAWN_GAP;
		}
		
		for (Enemy e : enemies) {
		    e.update(deltaTime);
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
		        }
		    }
		}
		
		for (Enemy e : enemies) {
		    if (e.isAlive() && player.intersects(e)) {
		        player.kill();
		        manager.setState(new GameOverState(manager));
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
		
        player.render(g, camera);
	}
	
}
