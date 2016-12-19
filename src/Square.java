import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

/*
 * Square.java
 * -----------
 * A square in the maze grid
 * 
 * Based on code by Dr. Jason Fritts
 * (http://cs.slu.edu/~fritts/csci2300/assignments/CheckersGUI_F16/GameSquare.java)
 */
public class Square extends JComponent {
	private final double DIST_FROM_EDGE = 0.2;
	private final int LINE_WIDTH = 0;
	private final Color DARK_BROWN = new Color(153, 76, 0);
	private final int BORDER_WIDTH = 1;

	private int x;
	private int y;
	private int top;
	private int bottom;
	private int left;
	private int right;
	private Position pos = null;
	private Color squareColor;
	private Color pathColor = null;
	private Player player = null;

	public Square(int row, int col, Color squareColor) {
		super();

		pos = new Position(row, col);
		this.squareColor = squareColor;
		top = bottom = left = right = 0;
	}

	public Square(Position pos, Color squareColor) {
		this(pos.r, pos.c, squareColor);
	}

	public Position getPosition() {
		return pos;
	}

	public void setPlayer(Player p) {
		player = p;
	}

	public void setColor(Color color) {
		squareColor = color;
	}

	public Color getColor() {
		return squareColor;
	}

	public void setPath(Color color) {
		pathColor = color;
	}

	public void addBorder(String location) {
		switch (location) {
		case "top":
			top += BORDER_WIDTH;
			break;
		case "bottom":
			bottom += BORDER_WIDTH;
			break;
		case "right":
			right += BORDER_WIDTH;
			break;
		case "left":
			left += BORDER_WIDTH;
			break;
		}

		setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, DARK_BROWN));
	}

	public boolean hasTop() {
		if (top > 0)
			return true;
		else
			return false;
	}

	public boolean hasBottom() {
		if (bottom > 0)
			return true;
		else
			return false;
	}

	public boolean hasRight() {
		if (right > 0)
			return true;
		else
			return false;
	}

	public boolean hasLeft() {
		if (left > 0)
			return true;
		else
			return false;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponents(g);

		Graphics2D g2 = (Graphics2D) g;

		int height = getHeight();
		int width = getWidth();

		Rectangle2D.Double bkgnd = new Rectangle2D.Double(x, y, width, height);
		g2.setColor(squareColor);
		g2.fill(bkgnd);

		if (player != null)
			player.drawPlayer(g2, x, y, width, height);

		if (pathColor != null) {
			Ellipse2D.Double path = new Ellipse2D.Double(x + width * DIST_FROM_EDGE + LINE_WIDTH / 2,
					y + height * DIST_FROM_EDGE + LINE_WIDTH / 2, width * (1 - 2 * DIST_FROM_EDGE) - LINE_WIDTH,
					height * (1 - 2 * DIST_FROM_EDGE) - LINE_WIDTH);
			g2.setColor(pathColor);
			g2.fill(path);
		}
	}
}
