package enemy;

import game.GameConstants;

public class Helicopter extends Enemy{
	private final double leftBound;
	private final double rightBound;

	public Helicopter(double x, double y, double leftBound, double rightBound) {
		super(x, y, GameConstants.ENEMY_WIDTH, GameConstants.ENEMY_HEIGHT, GameConstants.COLOR_ENEMY,GameConstants.HELICOPTER_POINTS);
		this.leftBound = leftBound;
	    this.rightBound = rightBound;
	    this.velocityX = GameConstants.HELICOPTER_SPEED;
	}

	@Override
	public void update(double deltaTime) {
		x += velocityX * deltaTime;
		
		if(x + width > rightBound) {
			x = rightBound - width;
			velocityX = -velocityX;
		}
		
		if(x < leftBound) {
			x = leftBound;
	        velocityX = -velocityX; 
		}
	}

}
