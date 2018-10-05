import SnakeUtil.Scenes;
import SnakeUtil.Snake;
import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {
    public static void mainFunction(String[] arg) { launch(arg); }

    @Override
    public void start(Stage primaryStage) {
        String headPic = "SnakeHead.jpg";

        Snake yourSnake = new Snake("Josh.jpg");
        Snake theirSnake = new Snake("Ruby.jpg");
        Scenes mainScenes = new Scenes(primaryStage, yourSnake, theirSnake);

        primaryStage.setTitle("Main Menu");
        mainScenes.set2MM(primaryStage);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            primaryStage.close();
            mainScenes.endGame();
        });

    }

}
