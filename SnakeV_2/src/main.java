import SnakeUtil.Scenes;
import SnakeUtil.Snake;
import javafx.application.Application;
import javafx.scene.image.Image;
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

        Image snake = new Image(getClass().getResourceAsStream("Resources/SnakeIcon.png"));

        primaryStage.setTitle("Python: Snake Online");
        primaryStage.getIcons().add(snake);
        mainScenes.set2MM(primaryStage);
        primaryStage.show();

    }

}
