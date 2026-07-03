package enemy;

import java.awt.Color;
import java.awt.Graphics2D;

import entity.Entity;
import graphics.Camera;

public abstract class Enemy extends Entity{
	protected final Color color;

	public Enemy(double x, double y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
	}



	@Override
	public void render(Graphics2D g, Camera camera) {
		int screenX = camera.worldToScreenX(x);
		int screenY = camera.worldToScreenY(y);
		
		g.setColor(color);
		g.fillRect(screenX, screenY, width, height);
	}

}
