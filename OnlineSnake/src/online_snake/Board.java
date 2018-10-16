package online_snake;

import java.awt.Point;

public class Board {
	private BoardObject[][] board;
	private Snake player1, player2; 
	
	// default constructor 
	public Board() {
		
	}
	// constructor that receives a matrix of boardObjects
	public Board(BoardObject[][] board) {
		
	}
	/**
	 * places a BoardObject on a point in the board
	 * @param p
	 * @param board
	 */
	public void put(Point p, BoardObject board) {
		
	}
	/**
	 * 
	 * @param p
	 * @return the boardObject corresponding to point p
	 */
	public BoardObject get(Point p) {
		return null;
	}
	/**
	 * 
	 * @param player
	 * @return Snake corresponding to a player
	 */
	public Snake getSnake(Player player) {
		return null;
	}
}
