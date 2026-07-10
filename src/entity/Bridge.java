package entity;

import java.awt.Graphics2D;

import game.GameConstants;
import graphics.Camera;

public class Bridge extends Entity{

	public Bridge(double x, double y, int width) {
		super(x, y, width, GameConstants.BRIDGE_HEIGHT);

	}

	@Override
	public void update(double deltaTime) {}

	@Override
	public void render(Graphics2D g, Camera camera) {
		int screenX = camera.worldToScreenX(x);
        int screenY = camera.worldToScreenY(y);
        g.setColor(GameConstants.COLOR_BRIDGE);
        g.fillRect(screenX, screenY, width, height);
	}

}
