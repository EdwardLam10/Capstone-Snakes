import SnakeUtil.Scenes;
import SnakeUtil.Snake;
import javafx.application.Application;
import javafx.stage.Stage;
import onlinetestcode.TransferPackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.TimerTask;

public class main extends Application {
    public static void mainFunction(String[] arg) { launch(arg); }
    Socket transferSocket = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    TransferPackage transferObj = null;
    @Override
    public void start(Stage primaryStage) throws IOException {
        String headPic = "Resources/SnakeHead.jpg";

        Snake yourSnake = new Snake("Resources/Josh.jpg");
        Snake theirSnake = new Snake("Resources/Ruby.jpg");
        Scenes mainScenes = new Scenes(primaryStage, yourSnake, theirSnake);


        primaryStage.setOnCloseRequest(event -> {
            primaryStage.close();
        });

        try {

            transferSocket = new Socket(/*SERVER_IP_ADDRESS*/, 10007);

            out = new ObjectOutputStream(transferSocket.getOutputStream());
            in = new ObjectInputStream(transferSocket.getInputStream());

        } catch (UnknownHostException e) {
            System.err.println("?");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection");
            System.exit(1);
        }


        try {
            transferObj = (TransferPackage)in.readObject();
        }
        catch (Exception ex)
        {
            System.out.println (ex.getMessage());
        }
        if(transferObj!=null){
            System.out.println("Got from Server");
        }
        else{
            System.err.println("Object not received");
            System.exit(1);
        }


        TimerTask loop = new TimerTask() {
            @Override
            public void run() {



                int X = transferObj.getP1X();
                int Y = transferObj.getP1Y();
                int newX = X;
                int newY = Y;

                String direction;
                boolean grows = transferObj.isNewApple();
                boolean end = transferObj.isGameOver();
                direction = yourSnake.getSnakeDirection(); //<-returns direction of the snake

                try {
                    out.writeObject(direction);// sends snake direction to server
                } catch (IOException e) {
                    e.printStackTrace();
                }

                yourSnake.setHeadLoc(X, Y); //<- sets head location to X and Y *X and Y are doubles*
                yourSnake.serverMove(newX, newY, grows); //<- moves the snake head to newX and newY, grows if true
                  if (grows) {
                      mainScenes.getGameLayout().getChildren().addAll(yourSnake.getSnake().lastElement()); //<-adds new element in snake to board.
                  }
                mainScenes.setPoint(X, Y); //<- sets the location of the point to X and Y *X and Y are doubles*
                if(end){ //if server sends the end signal, checks for winner and ends game
                    String winner = "";
                    if(transferObj.whoWon()==true){
                        winner = "player 1";
                    }
                    else{
                        winner = "player 2";
                    }
                    mainScenes.endGame(winner, primaryStage); //<-ends game with string winner's name, primaryStage is just primaryStage
                }
            }
        };

        out.close();
        in.close();
        transferSocket.close();

        primaryStage.setTitle("Main Menu");
        mainScenes.set2MM(primaryStage);
        primaryStage.show();


    }

}
