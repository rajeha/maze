/*
 * Position.java
 * -------------
 * Defines position in a grid
 * 
 * Based on code by Dr. Jason Fritts
 * (http://cs.slu.edu/~fritts/csci2300/assignments/CheckersGUI_F16/Position.java)
 */
public class Position {
	protected int r;
	protected int c;

	public Position (Position p){
		this (p.r, p.c);
	}

	public Position(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	public Position offset(Position pos) {
		return new Position (r - pos.r, c - pos.c);
	}
}
