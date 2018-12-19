import online_snake.*;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;

import onlinetestcode.TransferPackage;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = null;
        Socket player1soc = null;
        Socket player2soc = null;

        //CREATING SERVER SOCKET WITH PORT: 10007
        try{
            serverSocket = new ServerSocket(10007);
        } catch(IOException e) {
            System.err.println("Error on port: 10007.");
            System.exit(1);
        }

        //Main Loop for creating games
        while(true) {
            //wait for socket 1
            try {
                System.out.println ("Waiting for Player 1");
                player1soc = serverSocket.accept();
            } catch(IOException e) {
                System.err.println("Accept 1 failed.");
                System.exit(1);
            }

            //wait for socket 2
            try {
                System.out.println ("Waiting for Player 2");
                player2soc = serverSocket.accept();
            } catch(IOException e) {
                System.err.println("Accept 2 failed.");
                System.exit(1);
            }

            //Once Both Sockets have been established
            Thread gameThread = new Thread(new gameThread(player1soc, player2soc));
            gameThread.start();
            System.out.println("Game " + gameThread.getId() + " is now running.");
        }
    }
}


