import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/*
 * MazeGrid.java
 * -------------
 * Constructs a grid of Square objects that form a maze
 * 
 * solve() is based on code in Algorithms, 4th edition
 * (http://algs4.cs.princeton.edu/41graph/Maze.java)
 */
public class Maze extends JPanel {
	protected static int N; // needs to be accessed by Squares

	private final Color LIGHT_GREEN = new Color(53, 243, 172);
	private final Color LIGHT_GRAY = new Color(220, 220, 220);
	private final Color LIGHT_BROWN = new Color(255, 249, 240);
	private final Color LIGHT_RED = new Color(255, 85, 85);
	private final Color VLIGHT_RED = new Color(255, 215, 215);

	private Squares squares;
	private Player p;
	private RandomMaze m;
	private boolean done;

	public Maze(String level) {
		super();

		switch (level) {
		case "Rookie":
			N = 10;
			break;
		case "Casual":
			N = 25;
			break;
		case "Pro":
			N = 50;
		}

		init();
	}

	public Player getPlayer() {
		return p;
	}

	// finds a path to target starting at player's position and
	// outputs the solution progressively (using delay milliseconds)
	public void solve(int delay) {
		for (int x = 1; x <= N; x++)
			for (int y = 1; y <= N; y++)
				m.setVisited(x, y, false);

		done = false;
		solve(p.getPosition().r, p.getPosition().c, squares, delay);
	}

	// for the GameLayout action listener
	public void movePlayer(int key) {
		if (key == KeyEvent.VK_W) {
			p.moveUp(squares);
		}
		if (key == KeyEvent.VK_A) {
			p.moveLeft(squares);
		}
		if (key == KeyEvent.VK_S) {
			p.moveDown(squares);
		}
		if (key == KeyEvent.VK_D) {
			p.moveRight(squares);
		}

		repaint();
	}

	private void init() { // initialize the MazeGrid
		setLayout(new GridLayout(N, N, 0, 0));

		done = false;
		squares = new Squares();
		m = new RandomMaze(N);
		
		// draw the maze based off of a random maze m
		for (int x = 1; x <= N; x++)
			for (int y = 1; y <= N; y++) {
				squares.setSquare(new Square(x, y, LIGHT_BROWN));

				if (m.Top(x, y))
					squares.getSquare(x, y).addBorder("top");
				if (m.Bottom(x, y))
					squares.getSquare(x, y).addBorder("bottom");
				if (m.Right(x, y))
					squares.getSquare(x, y).addBorder("right");
				if (m.Left(x, y))
					squares.getSquare(x, y).addBorder("left");

				if (x == 1 && y == 1) { // put player in lower left square
					p = new Player(x, y);
					squares.getSquare(x, y).setPlayer(p);
				}
			}

		// put target in the middle
		squares.getSquare(N / 2, N / 2).setColor(LIGHT_RED);
		squares.getSquare(N / 2, N / 2).setPath(VLIGHT_RED);

		// reverse loops because GridLayout adds top left to bottom right
		for (int c = N; c > 0; c--)
			for (int r = 1; r <= N; r++)
				add(squares.getSquare(r, c));
	}

	// depth-first search method to solve maze
	private void solve(int x, int y, Squares squares, int delay) {
		if (x == 0 || y == 0 || x == N + 1 || y == N + 1 || done || m.isVisited(x, y))
			return;

		m.setVisited(x, y, true);

		if (!(x == p.getPosition().r & y == p.getPosition().c)) { // don't draw above player
			squares.getSquare(x, y).setPath(LIGHT_GREEN);

			try { // delay
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				System.err.println("Error while sleeping!");
				Thread.currentThread().interrupt();
			}

			repaint();
		}

		if (x == N / 2 && y == N / 2) // reached target
			done = true;

		if (!m.Top(x, y))
			solve(x, y + 1, squares, delay);
		if (!m.Right(x, y))
			solve(x + 1, y, squares, delay);
		if (!m.Bottom(x, y))
			solve(x, y - 1, squares, delay);
		if (!m.Left(x, y))
			solve(x - 1, y, squares, delay);

		if (done)
			return;

		if (!(x == p.getPosition().r & y == p.getPosition().c)) {
			squares.getSquare(x, y).setPath(LIGHT_GRAY);

			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				System.err.println("Error while sleeping!");
				Thread.currentThread().interrupt();
			}
			repaint();
		}
	}
}
