import SnakeUtil.Scenes;
import SnakeUtil.Snake;
import javafx.application.Application;
import javafx.stage.Stage;

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


        primaryStage.setTitle("Main Menu");
        mainScenes.set2MM(primaryStage);
        primaryStage.show();

    }

}
