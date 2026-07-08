package enemy;

import java.awt.Color;

import game.GameConstants;

public class Helicopter extends BouncingEnemy{
	public Helicopter(double x, double y, double leftBound, double rightBound) {
        super(x, y, GameConstants.ENEMY_WIDTH, GameConstants.ENEMY_HEIGHT,
              GameConstants.COLOR_ENEMY, GameConstants.HELICOPTER_POINTS,
              leftBound, rightBound, GameConstants.HELICOPTER_SPEED);
    }
	
}
