package game;

public class GameLoop implements Runnable{
	private final Game game;
	private Thread thread;
	private volatile boolean running;
	private int currentFPS;
	
	public GameLoop(Game game) {
		this.game = game;
		running = false;
		currentFPS = 0;
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0;
		int frames = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / (double) GameConstants.FRAME_TIME_NS;
			lastTime = now;
			boolean shouldRender = false;
			
			
			while(delta >= 1) {
				game.update();
				delta--;
				shouldRender = true;
			}
			
			if (shouldRender) {
		        game.render();
		        frames++;
		    }
			
			if(System.currentTimeMillis() - timer >= 1000) {
				currentFPS = frames;
				frames = 0;
				timer += 1000;
			}
			
			try {
				Thread.sleep(1);
			}
			catch(InterruptedException e) {
				Thread.currentThread().interrupt();
				running = false;
			}
		}
	}
	
	public synchronized void start() {
		if(running == true) {
			return;
		}
		
		running = true;
		thread = new Thread(this,"GameLoop");
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		
		try {
			if(thread != null) {
				thread.join();
			}
		}
		catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public int getCurrentFps() {
		return currentFPS;
	}
	
	
}
