package state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.GameConstants;
import input.KeyInput;

public class GameOverState extends State{


	public GameOverState(StateManager manager) {
		super(manager);
	}

	@Override
	public void update(KeyInput input) {
		if(input.isDown(KeyEvent.VK_ENTER)) {
			manager.setState(new PlayState(manager));
			return;
		}
		
		if(input.isDown(KeyEvent.VK_ESCAPE)) {
			manager.setState(new MenuState(manager));
		}
		
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GameConstants.WIDTH, GameConstants.HEIGHT);
		String gameOver = "GAME OVER";
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 48));
		
		int overWidth = g.getFontMetrics().stringWidth(gameOver);
		int overX = (GameConstants.WIDTH - overWidth) / 2;
		g.drawString(gameOver, overX, 100);
			
		String retry = "PRESS ENTER TO RETRY";
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Arial", Font.PLAIN, 20));
		
		int retryWidth = g.getFontMetrics().stringWidth(retry);
		int retryX = (GameConstants.WIDTH - retryWidth) / 2;
		g.drawString(retry, retryX, 300);
		
		String menu = "ESC FOR MENU";
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Arial", Font.PLAIN, 15));
		
		int menuWidth = g.getFontMetrics().stringWidth(menu);
		int menuX = (GameConstants.WIDTH - menuWidth) / 2;
		g.drawString(menu, menuX, 350);
	}

}
