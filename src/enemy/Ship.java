package enemy;

import game.GameConstants;

public class Ship extends BouncingEnemy{
	public Ship(double x, double y, double leftBound, double rightBound) {
        super(x, y, GameConstants.SHIP_WIDTH, GameConstants.SHIP_HEIGHT,
              GameConstants.COLOR_SHIP, GameConstants.SHIP_POINTS,
              leftBound, rightBound, GameConstants.SHIP_SPEED);
    }
}
