package map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import entity.Entity;
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
	
	public boolean collidesWithBank(Entity e) {
		double left = e.getX();
		double right = e.getX() + e.getWidth();
		double top = e.getY();
		double bottom = e.getY() + e.getHeight();
		
		for (RiverSegment segment : segments) {
			double segmentTop = segment.getWorldY();
			double segmentBottom = segmentTop + GameConstants.SEGMENT_HEIGHT;
			
			boolean verticalOverlap = segmentTop < bottom && segmentBottom > top;
			
			if(!verticalOverlap) {
				continue;
			}
			
			if(left < segment.getLeftBank() || right > segment.getRightBank()) {
				return true;
			}
		}
		
		return false;
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
