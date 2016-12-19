/*
 * Squares.java
 * ------------
 * A matrix of Square instances
 * 
 * Author: Dr. Jason Fritts
 * 
 * Based on code by Dr. Jason Fritts
 * (http://cs.slu.edu/~fritts/csci2300/assignments/CheckersGUI_F16/GameSquares.java)
 */
public class Squares {
	private Square[][] squares;

	public Squares() {
		squares = new Square[Maze.N + 2][Maze.N + 2];
	}

	public Square getSquare(int row, int col) {
		if (validPosition(row, col))
			return squares[row][col];
		else
			return null;
	}

	public void setSquare(Square sq) {
		Position pos = sq.getPosition();

		if (validPosition(pos))
			squares[pos.r][pos.c] = sq;
	}

	private boolean validPosition(int row, int col) {
		if (row < 0)
			return false;
		else if (row >= Maze.N + 2)
			return false;

		if (col < 0)
			return false;
		else if (col >= Maze.N + 2)
			return false;

		return true;
	}

	private boolean validPosition(Position pos) {
		return validPosition(pos.r, pos.c);
	}
}
