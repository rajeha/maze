import javax.swing.SwingUtilities;

/*
 * Game.java
 * ---------
 * Driver class
 */
public class Game {
	public static void main(String[] args) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				new GameLayout();
			}
		};

		SwingUtilities.invokeLater(r);
	}
}
