package online_snake;

import java.awt.Point;
import java.util.ArrayList;

public class Game {
	// class members
	private Board _gameBoard;
	private static int player1Score = 0;
	private static int player2Score = 0;
	
	public Game(Board b) {
		_gameBoard = b;
	}
	
	/**
	 * assuming the move is legal
	 * @param p
	 * @param d
	 * @return true if moving a player was successful
	 */
	public void move(Player p, Direction d) {
		// assuming the move is legal
		Snake player = _gameBoard.getSnake(p);
		player.moveToDirection(d);
	}

	/**
	 * places a BoardObject on a point in the board
	 * @param p
	 * @param board
	 */
	public void put(Point p, BoardObject board) {
		_gameBoard.put(p, board);
	}
	/**
	 * 
	 * @param p
	 */
	public void delete(Point p) {
		_gameBoard.delete(p);
	}
	/**
	 * 
	 * @param p
	 * @param d
	 * @return true if eating is successful
	 */
	public void eat(Player p, Direction d) {
		// assuming the move is legal
		Snake player = _gameBoard.getSnake(p);
		player.eat(d);
	}
	/**
	 * once 25 points are reached by a player the game ends, 
	 * and that player wins he game
	 * @return the Player who wins the game
	 */
	public Player getWinner() {
		if(player1Score == 25) {
			return Player.PLAYER1;
		}
		else if (player2Score == 25) {
			return Player.PLAYER2;
		}
		return null;
	}
	/**
	 * 
	 * @param p
	 * @return a list of possible legal moves for a player
	 */
	private ArrayList<Direction> possibleLegalMoves(Player p) {
		ArrayList<Direction> arr = new ArrayList<>();
		for (Direction dir : Direction.getDirections()) {
			if (isLegalMove(p, dir)) {
				arr.add(dir);
			}
		}
		return arr;
	}
	/**
	 * 
	 * @param p
	 * @param d
	 * @return true if a player can move to a Direction d
	 */
	public boolean isLegalMove(Player p, Direction d) {
		Snake s = _gameBoard.getSnake(p);
		Point move = d.getDestination(s.getHead());
		BoardObject bo = _gameBoard.get(move);
		if(bo == null || bo == BoardObject.APPLE) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param p
	 * @return a list of possible eat moves for a player
	 */
	private ArrayList<Direction> possibleEatMoves(Player p) {
		ArrayList<Direction> arr = new ArrayList<>();
		for (Direction dir : Direction.getDirections()) {
			if (isEatMove(p, dir)) {
				arr.add(dir);
			}
		}
		return arr;
	}
	/**
	 * 
	 * @param p
	 * @param d
	 * @return true if the direction results in eating
	 */
	public boolean isEatMove(Player p, Direction d) {
		Snake s = _gameBoard.getSnake(p);
		Point move = d.getDestination(s.getHead());
		if(_gameBoard.get(move) == BoardObject.APPLE) {
			return true;
		}
		return false;
	}
	
	public Snake getPlayerSnake(Player p) {
		return this._gameBoard.getSnake(p);
	}
}
