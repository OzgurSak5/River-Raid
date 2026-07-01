package map;

public class RiverSegment {
	private final double worldY;
	private final int leftBank;
	private final int rightBank;
	
	public RiverSegment(double worldY, int leftBank, int rightBank) {
		this.worldY = worldY;
		this.leftBank = leftBank;
		this.rightBank = rightBank;
	}

	public double getWorldY() {
		return worldY;
	}

	public int getLeftBank() {
		return leftBank;
	}

	public int getRightBank() {
		return rightBank;
	}
	
	
}
