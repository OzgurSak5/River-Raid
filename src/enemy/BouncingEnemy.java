package enemy;

import java.awt.Color;

public abstract class BouncingEnemy extends Enemy{
	protected final double leftBound;
	protected final double rightBound;

	public BouncingEnemy(double x, double y, int width, int height, Color color, int points,double leftBound,double rightBound, double speed) {
		super(x, y, width, height, color, points);
		this.leftBound = leftBound;
		this.rightBound = rightBound;
		this.velocityX = speed;
		
	}

	@Override
	public void update(double deltaTime) {
		x += velocityX * deltaTime;
        
        if (x + width > rightBound) {
            x = rightBound - width;
            velocityX = -velocityX;
        }
        
        if (x < leftBound) {
            x = leftBound;
            velocityX = -velocityX;
        }
	}

}
