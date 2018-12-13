package online_snake;

import java.awt.Point;

public class Board {
	// class members
	private BoardObject[][] _board;
	private Snake _player1, _player2; 
	
	// default constructor 
	public Board(int rows, int cols, Point[] snake1, Point[] snake2) {
		_board = new BoardObject[rows][cols];
		
//		for (int i = 0; i < cols; i++) {
//			_board[0][i] = BoardObject.BLOCK;
//			_board[rows-1][i]=BoardObject.BLOCK;
//		}
//		for (int i = 0; i < rows; i++) {
//			_board[i][0] = BoardObject.BLOCK;
//			_board[i][cols-1] = BoardObject.BLOCK;
//		}
		_player1 = new Snake(snake1);
		_player2 = new Snake(snake2);
	}
	
	// constructor that receives a matrix of boardObjects
	public Board(BoardObject[][] board, Point[] snake1, Point[] snake2) {
		_board = board;
		_player1 = new Snake(snake1);
		_player2 = new Snake(snake2);
	}
	
	/**
	 * places a BoardObject on a point in the board
	 * @param p
	 * @param b_o
	 */
	public void put(Point p, BoardObject b_o) {
		// p.x is the row and p.y is the column
		this._board[p.x][p.y]= b_o;
	}
	
	/**
	 * 
	 * @param p
	 * @return the boardObject corresponding to point p
	 */
	public BoardObject get(Point p) {
		// p.x is the row and p.y is the column
		if (_player1.isSnake(p)) {
			return BoardObject.SNAKE;
		}
		if (_player2.isSnake(p)) {
			return BoardObject.SNAKE;
		}
		System.out.println(p.x  + " " + p.y);
		return this._board[p.x][p.y];
	}
	
	public void delete(Point p) {
		_board[p.x][p.y] = null;
	}
	/**
	 * 
	 * @param player
	 * @return Snake corresponding to a player
	 */
	public Snake getSnake(Player player) {
		if (player == Player.PLAYER1) {
			return this._player1;
		} else if (player == Player.PLAYER2) {
			return this._player2;
		}
		return null;
	}

    @Override 
	public String toString() {
		String str = "";
		for (int i = 0; i < _board.length; i++) {
			for (int j = 0; j < _board.length; j++) {
				if(_board[i][j] != null) {
					System.out.println("(" + i + ", " + j + ") : " + _board[i][j]);				
				}
			}
		}		
		return str;
	}
	
}
	
