package entity;

import java.awt.Graphics2D;

import game.GameConstants;
import graphics.Camera;

public class Bullet extends Entity{

	public Bullet(double x, double y) {
		super(x, y, GameConstants.BULLET_WIDTH, GameConstants.BULLET_HEIGHT);
        this.velocityY = -GameConstants.BULLET_SPEED;
	}

	@Override
	public void update(double deltaTime) {
		y += velocityY * deltaTime;
	}

	@Override
	public void render(Graphics2D g, Camera camera) {
		int screenX = camera.worldToScreenX(x);
	    int screenY = camera.worldToScreenY(y);
	    g.setColor(GameConstants.COLOR_BULLET);
	    g.fillRect(screenX, screenY, width, height);
	}
	
}
