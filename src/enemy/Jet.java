package enemy;

import game.GameConstants;

public class Jet extends Enemy {

    public Jet(double x, double y) {
        super(x, y, GameConstants.JET_WIDTH, GameConstants.JET_HEIGHT,GameConstants.COLOR_JET, GameConstants.JET_POINTS);
        this.velocityY = GameConstants.JET_SPEED;
    }

    @Override
    public void update(double deltaTime) {
        y += velocityY * deltaTime;
    }
}