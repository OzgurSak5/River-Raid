package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import game.GameConstants;
import game.GameContext;

public class Hud {
	public void render(Graphics2D g, GameContext context) {
		g.setColor(Color.WHITE);
	    g.setFont(new Font("Arial", Font.BOLD, 16));
	    g.drawString("SCORE: " + context.getScore(), 10, 30);
	    
	    g.drawString("LIVES: " + context.getLives(), 10, 52);
	    
	    g.drawString("FUEL", 10, 78);
	    double fuelRatio = context.getFuel() / GameConstants.FUEL_MAX;
	    int barX = 60, barY = 66, barWidth = 150, barHeight = 14;
	    int filledWidth = (int) (barWidth * fuelRatio);
	    
	    g.setColor(Color.DARK_GRAY);
	    g.fillRect(barX, barY, barWidth, barHeight);
	    if (fuelRatio > 0.3) {
	        g.setColor(Color.GREEN);
	    } else {
	        g.setColor(Color.RED);
	    }
	    g.fillRect(barX, barY, filledWidth, barHeight);
	    g.fillRect(barX, barY, filledWidth, barHeight);
        
	}
}
