package online_snake;

import java.awt.Point;

public class Main {

	public static void main(String[] args) {
		Board b = new Board(10, 10, 
				new Point[] {new Point(5,2)},
				new Point[] {new Point(9,8)});
		Game game = new Game(b);
		
		Player winner = null;
		while(winner == null) {
			// get player and move of player
			if (game.isLegalMove(Player.PLAYER1, Direction.UP)) {
				
				if (game.isEatMove(Player.PLAYER1, Direction.UP)) {
					game.eat(Player.PLAYER1, Direction.UP);
				} else {
					game.move(Player.PLAYER1, Direction.UP);					
				}
			}
			
			winner = game.getWinner();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println("The winner is: " + winner.name());
	}

}
