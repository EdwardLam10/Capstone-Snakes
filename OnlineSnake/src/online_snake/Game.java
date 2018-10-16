package online_snake;

import java.awt.Point;
import java.util.ArrayList;

public class Game {
	// class members
	private Board _gameBoard;
	private static int player1Score = 0;
	private static int player2Score = 0;
	/**
	 * 
	 * @param p
	 * @param d
	 * @return true if moving a player was successful
	 */
	public boolean move(Player p, Direction d) {
		return false;
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
	 */
	public void delete(Point p) {
		
	}
	/**
	 * 
	 * @param p
	 * @param d
	 * @return true if eating is successful
	 */
	public boolean eat(Player p, Direction d) {
		return false;
	}
	/**
	 * 
	 * @return the Player who wins the game
	 */
	public Player getWinner() {
		return null;
	}
	/**
	 * 
	 * @param p
	 * @return a list of possible legal moves for a player
	 */
	private ArrayList<Direction> possibleLegalMoves(Player p) {
		return null;
	}
	/**
	 * 
	 * @param p
	 * @param d
	 * @return true if a player can move to a Direction d
	 */
	private boolean isLegalMove(Player p, Direction d) {
		return false;
	}
	/**
	 * 
	 * @param p
	 * @return a list of possible eat moves for a player
	 */
	private ArrayList<Direction> possibleEatMoves(Player p){
		return null;
	}
	/**
	 * 
	 * @param p
	 * @param d
	 * @return true if the direction results in eating
	 */
	private boolean isEatMove(Player p, Direction d) {
		return false;
	}
}
