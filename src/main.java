import SnakeUtil.Scenes;
import SnakeUtil.Snake;
import javafx.application.Application;
import javafx.stage.Stage;

public class main extends Application {
    public static void mainFunction(String[] arg) { launch(arg); }

    @Override
    public void start(Stage primaryStage) {
        String headPic = "Josh.jpg";

        Snake snake1 = new Snake(headPic);
        Scenes mainScenes = new Scenes(primaryStage, snake1);

        primaryStage.setTitle("Main Menu");
        mainScenes.set2MM(primaryStage);
        primaryStage.show();
    }
}
