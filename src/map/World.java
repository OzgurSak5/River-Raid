package map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import game.GameConstants;
import graphics.Camera;

public class World {
	private final List<RiverSegment> segments;
	private final RiverGenerator generator;
	private double topY;
	
	public World() {
		this.segments = new ArrayList<>();
		this.generator = new RiverGenerator();
		this.topY = GameConstants.HEIGHT;
		
		while(topY > -GameConstants.HEIGHT) {
			segments.add(generator.generateNext(topY));
			topY -= GameConstants.SEGMENT_HEIGHT;
		}
	}
	
	public void update(Camera camera) {
		while(topY > camera.getY() - GameConstants.HEIGHT) {
			segments.add(generator.generateNext(topY));
			topY -= GameConstants.SEGMENT_HEIGHT;
		}
		segments.removeIf(seg -> seg.getWorldY() > camera.getY() + 2 * GameConstants.HEIGHT);
	}
	
	public void render(Graphics2D g,Camera camera) {
		g.setColor(GameConstants.COLOR_LAND);
		
		for(RiverSegment segment : segments) {
			int screenY = camera.worldToScreenY(segment.getWorldY());
			
			g.fillRect(0, screenY, segment.getLeftBank(), GameConstants.SEGMENT_HEIGHT);
			int rightWidth = GameConstants.WIDTH - segment.getRightBank();
			g.fillRect(segment.getRightBank(), screenY, rightWidth, GameConstants.SEGMENT_HEIGHT);
		}
	}
	
	public List<RiverSegment> getSegments() {
        return segments;
    }
}
