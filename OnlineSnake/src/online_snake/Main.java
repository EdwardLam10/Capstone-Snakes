package online_snake;

import java.awt.Point;
import java.util.Random;

public class Main {

	public static Point generateRandomPoint() {
		Point p = new Point();
		Random randomNum = new Random(); 
		p.setLocation(randomNum.nextInt(29)+1, randomNum.nextInt(19)+1);
		return p;
	}
	public static void main(String[] args) {
		Board b = new Board(31, 21, 
				new Point[] {new Point(29,1)},
				new Point[] {new Point(1,19)});
		Game game = new Game(b);
		Point applePoint = new Point(15,10);
		game.put(applePoint, BoardObject.APPLE);
		//Serversocket
		//wait for socket 1
		//wait for socket 2
		//initialize 2 input variables
		String strDir = "";
		Direction direction = Direction.enumFromValue(strDir);
		int intPlayer = -1;
		Player player = Player.enumFromValue(intPlayer);
		Player winner = null;

		while(winner == null) {
			boolean hasEaten = false;
			
			// get player and move of player
			if (game.isLegalMove(player, direction)) {
				
				if (game.isEatMove(player, direction)) {
					hasEaten = true;
					game.eat(player, direction);
					winner = game.getWinner();
					if (winner == null) { // continue the game
						game.delete(applePoint);
						applePoint.setLocation(generateRandomPoint());
						game.put(applePoint, BoardObject.APPLE);
					} else { // max points reached
						// maybe print something to board
					}
				} else {
					game.move(player, direction);					
				}
			} else {
				winner = Player.getRival(player);
			}
			// hasEaten variable knows if eat occurs
			Point playerHead = game.getPlayerSnake(player).getHead();
			// socket returns playerHead and hasEaten to GUI
			// socket receives input
			strDir = "";
			direction = Direction.enumFromValue(strDir);
			intPlayer = -1;
			player = Player.enumFromValue(intPlayer);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}

		System.out.println("The winner is: " + winner.name());
	}

}
