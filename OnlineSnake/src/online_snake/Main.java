package online_snake;

import java.awt.Point;
import java.util.Random;
import java.io.*;
import java.net.*;
import onlinetestcode.TransferPackage;

public class Main {

	public static Point generateRandomPoint() {
		Point p = new Point();
		Random randomNum = new Random(); 
		p.setLocation(randomNum.nextInt(29)+1, randomNum.nextInt(19)+1);
		return p;
	}
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		Board b = new Board(31, 21, 
				new Point[] {new Point(19,1)},
				new Point[] {new Point(1,19)});
		Game game = new Game(b);
		Point applePoint = new Point(15,10);
		game.put(applePoint, BoardObject.APPLE);
		//Serversocket
		ServerSocket serverSocket = null;
		Socket player1soc = null;
		Socket player2soc = null;
		try { 
	         serverSocket = new ServerSocket(10007); 
	        } 
	    catch (IOException e) 
	        { 
	         System.err.println("Error on port: 10007."); 
	         System.exit(1); 
	        }
		
		//wait for socket 1
		try { 
	         System.out.println ("Waiting for Player 1");
	         player1soc = serverSocket.accept(); 
	        } 
	    catch (IOException e) 
	        { 
	         System.err.println("Accept 1 failed."); 
	         System.exit(1); 
	        }
		
		//wait for socket 2
		try { 
	         System.out.println ("Waiting for Player 2");
	         player2soc = serverSocket.accept(); 
	        } 
	    catch (IOException e) 
	        { 
	         System.err.println("Accept 2 failed."); 
	         System.exit(1); 
	        }
		//initialize i/o streams for sockets
		   ObjectOutputStream p1out = new ObjectOutputStream(
                   player1soc.getOutputStream());
		   ObjectInputStream p1in = new ObjectInputStream( 
                   player1soc.getInputStream());
		   ObjectOutputStream p2out = new ObjectOutputStream(
                   player2soc.getOutputStream());
		   ObjectInputStream p2in = new ObjectInputStream( 
                   player2soc.getInputStream());
		//initialize package for data transfer to client
		   TransferPackage p1Package = null;
		   TransferPackage p2Package = null;
		
		//initialize 2 input variables
		String strDir1 = "";
		String strDir2 = "";
		Direction direction1 = null;
		Direction direction2 = null; 
		Player player1 = Player.enumFromValue(1);
		Player player2 = Player.enumFromValue(2);
		Player winner = null;

		while(winner == null) {				
			boolean hasEaten = false;
			
			strDir1 = (String)p1in.readObject();
			direction1 = Direction.enumFromValue(strDir1);
			// for player 1
			if (game.isLegalMove(player1, direction1)) {
				
				if (game.isEatMove(player1, direction1)) {
					hasEaten = true;
					game.eat(player1, direction1);
					winner = game.getWinner();
					if (winner == null) { // continue the game
						game.delete(applePoint);
						applePoint.setLocation(generateRandomPoint());
						game.put(applePoint, BoardObject.APPLE);
					}
				} else {
					game.move(player1, direction1);
				}
			} else {
				winner = Player.getRival(player1);
			}
			// hasEaten variable knows if eat occurs
			Point playerHead1 = game.getPlayerSnake(player1).getHead();
			// socket returns playerHead and hasEaten to GUI
			
			
			// socket receives input

				strDir2 = (String)p2in.readObject();
			direction2 = Direction.enumFromValue(strDir2);
			
			
			
			//for player 2
			if (game.isLegalMove(player2, direction2)) {
				
					if (game.isEatMove(player2, direction2)) {
						hasEaten = true;
						game.eat(player2, direction2);
						winner = game.getWinner();
						if (winner == null) { // continue the game
								game.delete(applePoint);
								applePoint.setLocation(generateRandomPoint());
								game.put(applePoint, BoardObject.APPLE);
						} else { // max points reached
							// maybe print something to board
							}
						} else {
							game.move(player2, direction2);
						}
					} else {
						winner = Player.getRival(player2);
					}
					// hasEaten variable knows if eat occurs
					Point playerHead2 = game.getPlayerSnake(player2).getHead();
					// socket returns playerHead and hasEaten to GUI
					if(winner!=null){
						System.out.println("");
						if(winner == player1) {
							p1Package = new TransferPackage(true);
							p2Package = new TransferPackage(false);
						}
						else {
							p1Package = new TransferPackage(false);
							p2Package = new TransferPackage(true);
						}
					}
					else {
						p1Package = new TransferPackage((int)playerHead1.getX(),(int)playerHead1.getY(),(int)playerHead2.getX(),(int)playerHead2.getY());
						p2Package = new TransferPackage((int)playerHead2.getX(),(int)playerHead2.getY(),(int)playerHead1.getX(),(int) playerHead1.getY());
					}
					
					p1out.writeObject(p1Package);
					p2out.writeObject(p2Package);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}

		System.out.println("The winner is: " + winner.name());
	}

}
