package online_snake;

import java.awt.Point;

public class Tests {

	public static void main(String[] args) {
		
		// array of points for Snake
		Point[] points = new Point[1];
		for (int i = 0; i < points.length; i++) {
			
			points[i]= new Point(5,3);
		}
		// testing class Snake
		Point p = new Point(5,3);
		Snake s1 = new Snake(points);
		System.out.println(s1.toString());
		System.out.println(p);
		// testing method IsSnake
		System.out.println(s1.isSnake((p)));
		// testing method Eat
		s1.eat(Direction.UP);
		System.out.println(s1.toString());
		// testing method moveToDirection
		s1.moveToDirection(Direction.LEFT);
		System.out.println(s1.toString());
		System.out.println();
		
		// Testing Board class
		BoardObject[][] board = new BoardObject[10][10];
		Point[] points1 = new Point[1];
		for (int i = 0; i < points1.length; i++) {
			
			points1[i]= new Point(3, 7);
		}
		// Snake s2 = new Snake(points1);
		Board b = new Board(board, points, points1);
		// testing method getSnake
		Snake s_test = b.getSnake(Player.PLAYER1); 
		Snake s_test2 = b.getSnake(Player.PLAYER2); 
		System.out.println(s_test.toString());
		System.out.println(s_test2.toString());		
		// testing method put
		Point p1 = new Point(2,4);
		b.put(p1, BoardObject.APPLE);
		System.out.println(b.toString());	
		// testing method get
		System.out.println(b.get(p1));
		
	}
		
	
}
