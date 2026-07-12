package map;

import java.util.Random;

import game.GameConstants;

public class RiverGenerator {
	private int currentLeft;
	private int currentRight;
	private final Random random;
	private int targetWidth;
	private int segmentsUntilNewTarget;
	private double centerX;
	
	public RiverGenerator() {
	    this.random = new Random();
	    
	    int riverWidth = GameConstants.RIVER_START_WIDTH;
	    this.currentLeft = (GameConstants.WIDTH - riverWidth) / 2;
	    this.currentRight = currentLeft + riverWidth;
	    this.centerX = GameConstants.WIDTH / 2.0;
	    this.targetWidth = GameConstants.RIVER_START_WIDTH;
	    this.segmentsUntilNewTarget = 50;
	}
	
	public RiverSegment generateNext(double worldY) {
		segmentsUntilNewTarget--;
		
		if(segmentsUntilNewTarget <= 0) {
			targetWidth = GameConstants.RIVER_MIN_WIDTH 
					+ random.nextInt(GameConstants.RIVER_MAX_WIDTH - GameConstants.RIVER_MIN_WIDTH +1);
			
			segmentsUntilNewTarget = GameConstants.RIVER_PHASE_MIN 
					+ random.nextInt(GameConstants.RIVER_PHASE_MAX - GameConstants.RIVER_PHASE_MIN + 1);
		}
		
		int currentWidth = currentRight - currentLeft;
		
        if(currentWidth < targetWidth) {
        	currentWidth += GameConstants.RIVER_WIDTH_STEP;
        	
        	if(currentWidth > targetWidth) {
        		currentWidth = targetWidth;
        	}
        }
        else if(currentWidth > targetWidth) {
        	currentWidth -= GameConstants.RIVER_WIDTH_STEP;
        	
        	if(currentWidth < targetWidth) {
        		currentWidth = targetWidth;
        	}
        }
        
        centerX += random.nextInt(3) -1;
        
        currentLeft = (int) (centerX - currentWidth / 2.0);
        currentRight = currentLeft + currentWidth;
        
        if (currentLeft < GameConstants.BANK_MARGIN) {
            currentLeft = GameConstants.BANK_MARGIN;
            currentRight = currentLeft + currentWidth;
            centerX = currentLeft + currentWidth / 2.0;
        }
        if (currentRight > GameConstants.WIDTH - GameConstants.BANK_MARGIN) {
            currentRight = GameConstants.WIDTH - GameConstants.BANK_MARGIN;
            currentLeft = currentRight - currentWidth;
            centerX = currentLeft + currentWidth / 2.0;
        }
        
        return new RiverSegment(worldY, currentLeft, currentRight);
    }
}
