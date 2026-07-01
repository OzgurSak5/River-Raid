package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.GameConstants;
import input.KeyInput;

public class MenuState extends State{

	public MenuState(StateManager manager) {
		super(manager);
	}

	@Override
	public void update(KeyInput input) {
		if(input.isDown(KeyEvent.VK_ENTER)) {
			manager.setState(new PlayState(manager));
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GameConstants.WIDTH, GameConstants.HEIGHT);
		String title = "RIVER RAID";
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 48));
		
		int titleWidth = g.getFontMetrics().stringWidth(title);
		int titleX = (GameConstants.WIDTH - titleWidth) / 2;
		
		g.drawString(title, titleX, 100);
		String subtitle  = "PRESS ENTER TO START";
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Arial", Font.PLAIN, 24));
		
		int subtitleWidth = g.getFontMetrics().stringWidth(subtitle);
		int subtitleX = (GameConstants.WIDTH - subtitleWidth) / 2;
		g.drawString(subtitle, subtitleX, 300);
	}

}
