package online_snake;

import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	private ArrayList<Point> _snakeCoord;

	// constructor
	public Snake() {
		_snakeCoord = new ArrayList<Point>();
	}

	/** 
	 * Moves snake to a direction by updating it's coordinates
	 * @param Direction 
	 */
	public void moveToDirection(Direction d) {
		Point dest = d.getDestination(_snakeCoord.get(0));
		_snakeCoord.add(0, dest); // Add dest to head of the snake 
		_snakeCoord.remove(_snakeCoord.size()-1); // Remove the tail of the snake
	}

	/**
	 * the Snake eats a point
	 * @param point
	 */
	public void eat(Direction to) {
		Point dest = to.getDestination(_snakeCoord.get(0));
		_snakeCoord.add(0, dest); // Add dest to head of the snake 		
	}

	/**
	 * 
	 * @param point
	 * @return true if this point is a snake
	 */
	public boolean isSnake(Point point) {
		for(int i = 0; i < _snakeCoord.size(); i++) {
			if(_snakeCoord.get(i) == point)
				return true;
		}
		return false;
	}
}
