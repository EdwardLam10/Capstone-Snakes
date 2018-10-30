package online_snake;
import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	private ArrayList<Point> _snakeCoord;

	// default constructor
	public Snake() {
		_snakeCoord = new ArrayList<Point>();
	}

	// constructor
	public Snake(Point[] points) {
		_snakeCoord = new ArrayList<Point>();
		for (int i = 0; i < points.length; i++) {
			_snakeCoord.add(points[i]);
		}
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
	 * @param to
	 */
	public void eat(Direction to) {
		Point dest = to.getDestination(_snakeCoord.get(0));
		_snakeCoord.add(0, dest); // Add dest to head of the snake 		
	}
	
	public Point getHead() {
		return _snakeCoord.get(0);
	}

	/**
	 * 
	 * @param point
	 * @return true if this point is a snake
	 */
	public boolean isSnake(Point point) {
		for(int i = 0; i < _snakeCoord.size(); i++) {
			if(_snakeCoord.get(i).x == point.x && _snakeCoord.get(i).y == point.y)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String str ="";
		for (int i = 0; i < _snakeCoord.size(); i++) {
			Point p = _snakeCoord.get(i);
			str+="(" + p.x + ", " + p.y+") ";
		}
		return str;
	}
}
