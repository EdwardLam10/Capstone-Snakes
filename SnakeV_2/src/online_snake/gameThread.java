package online_snake;

import javafx.application.Platform;
import onlinetestcode.TransferPackage;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class gameThread implements Runnable{
    public gameThread(Socket p1, Socket p2) {
        player1socket = p1;
        player2socket = p2;
    }

    public void run() {
        //Try Catch to create Object input and output streams
        try {
            makeInputOutput(player1socket, player2socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

        makeGameVariables();

        try {
            gameLoop();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Point generateRandomPoint() {
        Point p = new Point();
        Random randomNum = new Random();
        p.setLocation(randomNum.nextInt(29) + 1, randomNum.nextInt(19) + 1);
        return p;
    }
    private void makeInputOutput(Socket player1socket, Socket player2socket) throws IOException{
        p1out = new ObjectOutputStream(player1socket.getOutputStream());
        p1in = new ObjectInputStream(player1socket.getInputStream());
        p2out = new ObjectOutputStream(player2socket.getOutputStream());
        p2in = new ObjectInputStream(player2socket.getInputStream());
    }
    private void makeGameVariables() {
        b = new Board(100, 100,
                new Point[] {new Point(0,0)},
                new Point[] {new Point(0,12)});
        game = new Game(b);
        applePoint = generateRandomPoint();
        game.put(applePoint, BoardObject.APPLE);
        gameSpeed = 170;
        strDir1 = "";
        strDir2 = "";
        direction1 = null;
        direction2 = null;
        player1 = Player.enumFromValue(1);
        player2 = Player.enumFromValue(2);
        winner = null;
    }
    private void gameLoop() throws IOException, ClassNotFoundException {
        while(winner == null) {
            boolean hasEaten1 = false;

            strDir1 = (String)p1in.readObject();
            direction1 = Direction.enumFromValue(strDir1);
            // for player 1
            if (game.isLegalMove(player1, direction1)) {

                if (game.isEatMove(player1, direction1)) {
                    gameSpeed -= 5;
                    hasEaten1 = true;
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
                System.out.println("Game " + Thread.currentThread().getId() + " has ended");
                winner = Player.getRival(player1);
            }
            // hasEaten variable knows if eat occurs
            Point playerHead1 = game.getPlayerSnake(player1).getHead();
            // socket returns playerHead and hasEaten to GUI


            // socket receives input

            strDir2 = (String)p2in.readObject();
            direction2 = Direction.enumFromValue(strDir2);
            boolean hasEaten2 = false;


            //for player 2
            if (game.isLegalMove(player2, direction2)) {

                if (game.isEatMove(player2, direction2)) {
                    gameSpeed -= 5;
                    hasEaten2 = true;
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
                System.out.println("Game " + Thread.currentThread().getId() + " has ended");
                winner = Player.getRival(player2);
            }
            // hasEaten variable knows if eat occurs
            Point playerHead2 = game.getPlayerSnake(player2).getHead();
            // socket returns playerHead and hasEaten to GUI
            if(winner!=null){
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
                p1Package = new TransferPackage((int)playerHead1.getX(),(int)playerHead1.getY(),hasEaten1,(int)playerHead2.getX(),(int)playerHead2.getY(),hasEaten2, (int)applePoint.getX(), (int) applePoint.getY());
                p2Package = new TransferPackage((int)playerHead2.getX(),(int)playerHead2.getY(),hasEaten2,(int)playerHead1.getX(),(int) playerHead1.getY(),hasEaten1, (int)applePoint.getX(), (int)applePoint.getY());
            }

            p1out.writeObject(p1Package);
            p2out.writeObject(p2Package);
            p1out.reset();
            p2out.reset();
            try {
                Thread.sleep(gameSpeed);
            } catch (InterruptedException e) {
            }
        }
    }

    //Game Engine Variables
    private Board b;
    private Game game;
    private Point applePoint;
    private int gameSpeed;
    private Player player1;
    private Player player2;
    private Player winner;
    private String strDir1;
    private String strDir2;
    private Direction direction1;
    private Direction direction2;

    //Networking Variables
    private Socket player1socket;
    private Socket player2socket;

    private ObjectOutputStream p1out;
    private ObjectInputStream p1in;
    private ObjectOutputStream p2out;
    private ObjectInputStream p2in;

    private TransferPackage p1Package;
    private TransferPackage p2Package;
}
