package SnakeUtil;

import onlinetestcode.TransferPackage;

import java.io.IOException;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Online {
    public Online(String tmpIP) throws IOException, UnknownHostException {
        try {
            transferSocket = new Socket( tmpIP, 10007); //connecting to server
            out = new ObjectOutputStream(transferSocket.getOutputStream()); //set up output stream from socket
            in = new ObjectInputStream(transferSocket.getInputStream()); //set up input stream from socket
        } catch (UnknownHostException e) {
            System.err.println("?");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection");
            System.exit(1);
        }
    }

    public TransferPackage getTP() {
        updateTP();
        return transferObj;
    }

    private boolean updateTP() {
        try {
            //This should be inside the loop
            //Recieveing package
            transferObj = (TransferPackage)in.readObject();
        }
        catch (Exception ex)
        {
            System.out.println (ex.getMessage());
        }
        if(transferObj!=null){
            System.out.println("Got from Server");
            System.out.println( transferObj.isGameOver());
            return true;
        }
        else{
            System.err.println("Object not received");
            System.exit(1);
            return false;
        }
    }

    private TransferPackage transferObj;
    private Socket transferSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
}
