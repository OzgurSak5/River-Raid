package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import input.KeyInput;
import state.MenuState;
import state.StateManager;

public class Game extends JPanel{
	private final GameLoop loop;
	private final KeyInput input;
	private final StateManager stateManager;
	

	public Game() {
		setPreferredSize(new Dimension(GameConstants.WIDTH,GameConstants.HEIGHT));
		setBackground(GameConstants.COLOR_BACKGROUND);
		setFocusable(true);
		
		this.input = new KeyInput();
		addKeyListener(input);
		this.loop = new GameLoop(this);
		this.stateManager = new StateManager();
		this.stateManager.setState(new MenuState(stateManager));
	}
	
	public void start() {
		loop.start();
	}
	
	public void stop() {
		loop.stop();
	}

	public void update() {
		stateManager.update(input);
	}

	public void render() {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		stateManager.render(g2);
		g2.setColor(GameConstants.COLOR_HUD_TEXT);
		g2.drawString("FPS: " + loop.getCurrentFps(), 10, 20);
	}
	
	

}
