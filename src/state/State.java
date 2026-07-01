package state;

import java.awt.Graphics2D;

import input.KeyInput;

public abstract class State {
	protected final StateManager manager;

	public State(StateManager manager) {
		this.manager = manager;
	}
	
	public abstract void update(KeyInput input);
	public abstract void render(Graphics2D g);
	
	
	public void enter() {}
	
	public void exit() {}
}
