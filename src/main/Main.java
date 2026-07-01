package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import game.Game;
public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> launch());
	}

	private static void launch() {
		JFrame frame = new JFrame("River Raid");
		Game game = new Game();

		frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.requestFocusInWindow();
        game.start();
	}
}
