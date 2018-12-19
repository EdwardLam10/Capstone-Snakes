package SnakeUtil;

import onlinetestcode.TransferPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            System.out.println("before in.read");
            transferObj = (TransferPackage)in.readObject();
            System.out.println("after in.read");
        }
        catch (Exception ex)
        {
            System.out.println (ex.getMessage());
        }
        if(transferObj!=null){
            System.out.println("Got from Server");
            System.out.println( "game over?" + transferObj.isGameOver());
            return true;
        }
        else{
            System.err.println("Object not received");
            System.exit(1);
            return false;
        }
    }

    public void sendDirection(String dir) throws IOException {
        out.writeObject(dir);
        out.reset();
    }

    public void closeOnline() throws IOException{
        in.close();
        out.close();
        transferSocket.close();
        System.out.println("everything is closed");
    }

    private TransferPackage transferObj;
    private Socket transferSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
}
