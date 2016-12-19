import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/*
 * Player.java
 * -----------
 * Defines a player in the maze
 *
 * Based on code by Dr. Jason Fritts
 * (http://cs.slu.edu/~fritts/csci2300/assignments/CheckersGUI_F16/Player.java)
 */
public class Player {
	private final double DIST_FROM_EDGE = 0.15;
	private final int LINE_WIDTH = 0;
	private final Color LIGHT_RED = new Color(255, 85, 85);

	private boolean won = false;
	private Position pos = null;

	public Player(int row, int col) {
		pos = new Position(row, col);
	}

	public void setPosition(Position pos) {
		pos = new Position(pos);
	}

	public Position getPosition() {
		return pos;
	}

	public boolean hasWon() {
		return won;
	}

	public void moveRight(Squares squares) {
		int dr = pos.r + 1;
		int dc = pos.c;
		Position dp = new Position(dr, dc);
		if (!squares.getSquare(pos.r, pos.c).hasRight()) {
			move(dp, squares);
		}
	}

	public void moveLeft(Squares squares) {
		int dr = pos.r - 1;
		int dc = pos.c;
		Position dp = new Position(dr, dc);
		if (!squares.getSquare(pos.r, pos.c).hasLeft()) {
			move(dp, squares);
		}
	}

	public void moveUp(Squares squares) {
		int dr = pos.r;
		int dc = pos.c + 1;
		Position dp = new Position(dr, dc);
		if (!squares.getSquare(pos.r, pos.c).hasTop()) {
			move(dp, squares);
		}
	}

	public void moveDown(Squares squares) {
		int dr = pos.r;
		int dc = pos.c - 1;
		Position dp = new Position(dr, dc);
		if (!squares.getSquare(pos.r, pos.c).hasBottom()) {
			move(dp, squares);
		}
	}

	private void move(Position newPos, Squares squares) {
		Position dp = newPos.offset(pos);
		int dr = dp.r;
		int dc = dp.c;
		squares.getSquare(pos.r, pos.c).setPlayer(null);
		squares.getSquare(pos.r + dr, pos.c + dc).setPlayer(this);
		pos = new Position(newPos);

		if (squares.getSquare(pos.r, pos.c).getColor().equals(LIGHT_RED)) { // won!
			won = true;
			squares.getSquare(pos.r, pos.c).setPath(null);
		}
	}

	public void drawPlayer(Graphics2D g2, int x, int y, int width, int height) {
		Ellipse2D.Double circle = new Ellipse2D.Double(x + width * DIST_FROM_EDGE + LINE_WIDTH / 2,
				y + height * DIST_FROM_EDGE + LINE_WIDTH / 2, width * (1 - 2 * DIST_FROM_EDGE) - LINE_WIDTH,
				height * (1 - 2 * DIST_FROM_EDGE) - LINE_WIDTH);

		g2.setColor(new Color(0, 128, 255));
		g2.fill(circle);

	}
}
