package online_snake;

import java.awt.Point;

public enum Direction {
	UP,
	DOWN,
	LEFT,
	RIGHT;
	
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
}