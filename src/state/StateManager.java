package state;

import java.awt.Graphics2D;

import input.KeyInput;

public class StateManager {
	private State currentState;

	public void render(Graphics2D g) {
		if(currentState != null) {
			currentState.render(g);
		}
	}
	
	public void update(KeyInput input) {
		if(currentState != null) {
			currentState.update(input);
		}
	}
	
	public State getCurrentState() {
		return currentState;
	}

	public void setState(State newState) {
		if(currentState != null) {
			currentState.exit();
		}
		currentState = newState;
		
		if(currentState != null) {
			currentState.enter();
		}
	}
	
}
