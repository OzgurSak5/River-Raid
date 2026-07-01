package map;

import java.util.Random;

import game.GameConstants;

public class RiverGenerator {
	private int currentLeft;
	private int currentRight;
	private final Random random;
	
	public RiverGenerator() {
	    this.random = new Random();
	    
	    int riverWidth = GameConstants.RIVER_START_WIDTH;
	    this.currentLeft = (GameConstants.WIDTH - riverWidth) / 2;
	    this.currentRight = currentLeft + riverWidth;
	}
	
	public RiverSegment generateNext(double worldY) {
        int leftDrift  = random.nextInt(2 * GameConstants.RIVER_DRIFT + 1) - GameConstants.RIVER_DRIFT;
		int rightDrift = random.nextInt(2 * GameConstants.RIVER_DRIFT + 1) - GameConstants.RIVER_DRIFT;

		currentLeft  += leftDrift;
		currentRight += rightDrift;
        
		if (currentLeft < GameConstants.BANK_MARGIN) {
		    currentLeft = GameConstants.BANK_MARGIN;
		}
		
		if (currentRight > GameConstants.WIDTH - GameConstants.BANK_MARGIN) {
	        currentRight = GameConstants.WIDTH - GameConstants.BANK_MARGIN;
	    }
	    
	    if (currentRight - currentLeft < GameConstants.RIVER_MIN_WIDTH) {
	        currentRight = currentLeft + GameConstants.RIVER_MIN_WIDTH;
	    }
	    
	    if (currentRight - currentLeft > GameConstants.RIVER_MAX_WIDTH) {
	        currentRight = currentLeft + GameConstants.RIVER_MAX_WIDTH;
	    }
	    
	    return new RiverSegment(worldY, currentLeft, currentRight);
    }
}
