package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import graphics.Camera;

public abstract class Entity {
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected double velocityX;
	protected double velocityY;
	private boolean alive;
	
	public Entity(double x, double y, int width, int height) {
	    this.x = x;
	    this.y = y;
	    this.width = width;
	    this.height = height;
	    this.velocityX = 0;
	    this.velocityY = 0;
	    this.alive = true;
	}
	
	public abstract void update(double deltaTime);
	public abstract void render(Graphics2D g, Camera camera);
	
	public boolean intersects(Entity other) {
	    return getBounds().intersects(other.getBounds());
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	}
	
	public void kill() {
		alive = false;
	}

	public double getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}

	public double getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isAlive() {
		return alive;
	}
	
	
}
