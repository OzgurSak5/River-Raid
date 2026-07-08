package game;

public class GameContext {
	private int score;
	private double fuel;
	private int lives;
	
	public GameContext() {
		this.score = 0;
		this.fuel = GameConstants.FUEL_MAX;
		this.lives = GameConstants.STARTING_LIVES;
	}
	
	public void addScore(int points) {
		score += points;
	}

	public int getScore() {
		return score;
	}

	public void addFuel(double amount) {
	    fuel += amount;
	    if (fuel > GameConstants.FUEL_MAX) {
	        fuel = GameConstants.FUEL_MAX;
	    }
	}
	
	public void consumeFuel(double amount) {
	    fuel -= amount;
	    if (fuel < 0) {
	        fuel = 0;
	    }
	}
	
	public boolean isOutOfFuel() {
		return fuel <= 0;
	}
	
	public double getFuel() {
		return fuel;
	}
	
	public int getLives() {
		return lives; 
	}
	
	public void loseLife() {
		lives--; 
	}
	
	public boolean isGameOver() { 
		return lives <= 0;
	}
	
}
