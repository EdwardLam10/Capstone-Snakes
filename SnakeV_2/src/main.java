import SnakeUtil.Scenes;
import SnakeUtil.Snake;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.TimerTask;

public class main extends Application {
    public static void mainFunction(String[] arg) { launch(arg); }

    @Override
    public void start(Stage primaryStage) {
        String headPic = "Resources/SnakeHead.jpg";

        Snake yourSnake = new Snake("Resources/Josh.jpg");
        Snake theirSnake = new Snake("Resources/Ruby.jpg");
        Scenes mainScenes = new Scenes(primaryStage, yourSnake, theirSnake);

        primaryStage.setOnCloseRequest(event -> {
            primaryStage.close();
        });

//        TimerTask loop = new TimerTask() {
//            @Override
//            public void run() {
//                yourSnake.getSnakeDirection(); //<-returns direction of the snake
//                yourSnake.setHeadLoc(X, Y); //<- sets head location to X and Y *X and Y are doubles*
//                yourSnake.serverMove(newX, newY, grows?); //<- moves the snake head to newX and newY, grows if true
//                  if (snake does grow) {
//                      mainScenes.getGameLayout.getChildren.addAll(yourSnake.getlastelement(); //<-adds new element in snake to board.
//                  }
//                mainScenes.setPoint(X, Y); //<- sets the location of the point to X and Y *X and Y are doubles*
//                mainScenes.endGame(winner, primaryStage); //<-ends game with string winner's name, primaryStage is just primaryStage
//            }
//        };



        primaryStage.setTitle("Main Menu");
        mainScenes.set2MM(primaryStage);
        primaryStage.show();

    }

}
