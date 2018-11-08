package online_snake;

import java.awt.Point;
import java.util.ArrayList;

public enum Direction {
	UP("UP"),
	DOWN("DOWN"),
	LEFT("LEFT"),
	RIGHT("RIGHT");

	private String _str;

	private Direction(String str) {
		this._str = str;
	}

	/**
	 * 
	 * @param Point of source
	 * @return Point of destination according to DIRECTION
	 */
	public Point getDestination(Point source) {
		// current Direction is "this"
		Point dest = new Point(source);
		if (this == UP) {
			dest.x = dest.x - 1;
		} else if(this == DOWN) {
			dest.x = dest.x + 1;
		} else if (this== LEFT) {
			dest.y = dest.y - 1;
		} else if ( this== RIGHT) {
			dest.y = dest.y + 1;
		}
		return dest;
	}

	public static ArrayList<Direction> getDirections() {
		ArrayList<Direction> arr = new ArrayList<>();
		arr.add(UP);
		arr.add(DOWN);
		arr.add(LEFT);
		arr.add(RIGHT);
		return arr;
	} 

	public static Direction enumFromValue(String value) {
		if (value.equals("UP")) {
			return UP;
		} else if(value.equals("DOWN")) {
			return DOWN;
		} else if (value.equals("LEFT")) {
			return LEFT;
		} else if (value.equals("RIGHT")) {
			return RIGHT;
		}
		return null;
	}
}
