package state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import entity.Bullet;
import entity.Player;
import game.GameConstants;
import graphics.Camera;
import input.KeyInput;
import map.World;

public class PlayState extends State{
	private final Player player;
	private final Camera camera;
	private final List<Bullet> bullets = new ArrayList<>();
	private final World world = new World();

	public PlayState(StateManager manager) {
		super(manager);
		
		double startX = (GameConstants.WIDTH - GameConstants.PLAYER_WIDTH) / 2.0;
		double startY = 600;
		this.player = new Player(startX,startY);
		this.camera = new Camera(player,GameConstants.SCROLL_SPEED);
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
		
		if (input.isDown(KeyEvent.VK_SPACE) && player.canShoot()) {
	        bullets.add(player.shoot());
	    }
		
		for (Bullet b : bullets) {
	        b.update(deltaTime);
	    }
		
		bullets.removeIf(b -> {
	        int screenY = camera.worldToScreenY(b.getY());
	        return screenY < -50 || screenY > GameConstants.HEIGHT + 50;
	    });
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(GameConstants.COLOR_WATER);
		g.fillRect(0, 0, GameConstants.WIDTH, GameConstants.HEIGHT);
		world.render(g, camera);
		
		for (Bullet b : bullets) {
	        b.render(g, camera);
	    }
		
        player.render(g, camera);
	}
	
}
