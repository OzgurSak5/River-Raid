package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
	private static final int KEY_COUNT = 256;
	private final boolean[] keys = new boolean[KEY_COUNT];
	
	public boolean isDown(int keyCode) {
		return keyCode >= 0 && keyCode < KEY_COUNT && keys[keyCode];
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode >=0 && keyCode < KEY_COUNT) {
			keys[keyCode] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(keyCode >=0 && keyCode < KEY_COUNT) {
			keys[keyCode] = false;
		}
	}

}
