package entity;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.GameConstants;
import graphics.Camera;
import input.KeyInput;

public class Player extends Entity{
	private double shootCooldown;
	private double invincibleTimer;

	public Player(double x, double y) {
		super(x, y, GameConstants.PLAYER_WIDTH, GameConstants.PLAYER_HEIGHT);
		this.shootCooldown = 0;
		this.invincibleTimer = 0;
	}
	
	public boolean isInvincible() {
		return invincibleTimer > 0;
	}
	
	public void makeInvincible(double duration) {
		invincibleTimer = duration;
	}
	
	public void respawn(double x, double y) {
		this.x = x;
		this.y = y;
		this.velocityX = 0;
		makeInvincible(GameConstants.INVINCIBLE_TIME);
	}
	
	public Bullet shoot() {
		shootCooldown = GameConstants.SHOOT_COOLDOWN;

		double bulletX = x + (width - GameConstants.BULLET_WIDTH) / 2.0;
		double bulletY = y;
		return new Bullet(bulletX, bulletY);
	}
	
	public boolean canShoot() {
		return shootCooldown <= 0;
	}
	
	public void handleInput(KeyInput input) {
	    velocityX = 0;
	    
	    if (input.isDown(KeyEvent.VK_LEFT)) {
	        velocityX = -GameConstants.PLAYER_SPEED;
	    }
	    if (input.isDown(KeyEvent.VK_RIGHT)) {
	        velocityX = GameConstants.PLAYER_SPEED;
	    }
	}

	@Override
	public void update(double deltaTime) {
		x += velocityX * deltaTime;
		y -= GameConstants.SCROLL_SPEED * deltaTime;
		
		if(x + width > GameConstants.WIDTH) {
			x = GameConstants.WIDTH - width;
		}
		
		if(x < 0) {
			x = 0;
		}
		
		if(shootCooldown > 0) {
			shootCooldown -= deltaTime;
		}
		
		if (invincibleTimer > 0) {
		    invincibleTimer -= deltaTime;
		}
	}

	@Override
	public void render(Graphics2D g,Camera camera) {
		if (isInvincible()) {
	        int blinkPhase = (int) (invincibleTimer * 6);
	        if (blinkPhase % 2 == 0) {
	            return;
	        }
	    }
		
		int screenX = camera.worldToScreenX(x);
	    int screenY = camera.worldToScreenY(y);
	    
		g.setColor(GameConstants.COLOR_PLAYER);
		g.fillRect(screenX,screenY, width, height);
	}


}
