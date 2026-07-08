package entity;

import java.awt.Graphics2D;

import game.GameConstants;
import graphics.Camera;

public class FuelDepot extends Entity{

	public FuelDepot(double x, double y) {
		super(x, y, GameConstants.FUEL_DEPOT_WIDTH, GameConstants.FUEL_DEPOT_HEIGHT);
	}

	@Override
	public void update(double deltaTime) {}

	@Override
	public void render(Graphics2D g, Camera camera) {
		int screenX = camera.worldToScreenX(x);
		int screenY = camera.worldToScreenY(y);
		
		g.setColor(GameConstants.COLOR_FUEL_DEPOT);
		g.fillRect(screenX, screenY, width, height);
		
	}

}
