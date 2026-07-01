package graphics;

import entity.Player;

public class Camera {
	private double y;
	private double scrollSpeed;
	private final Player target;
	
	public Camera(Player target, double scrollSpeed) {
		this.target = target;
		this.scrollSpeed = scrollSpeed;
		this.y = 0;
	}	
	
	public void update(double deltaTime) {
		y = target.getY() - 600;
	}
	
	public int worldToScreenX(double worldX) {
		return (int) worldX;
	}
	
	public int worldToScreenY(double worldY) {
		return (int) (worldY - y);
	}
	
	public double getY() {
		return y;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getScrollSpeed() {
        return scrollSpeed;
    }
}
