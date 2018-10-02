import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import javax.print.DocFlavor;


public class test extends Application {
    public static void main(String[] arg) {
        launch(arg);
    }

    @Override
    public void start(Stage primaryStage) {

        VBox intro = new VBox(40);


        Snake snake1 = new Snake("Ruby.jpg");

        Pane layout = new Pane();
        layout.getChildren().addAll(snake1.SnakeVec);
        Scene scene1 = new Scene(layout,1080,720);

        Image bg = new Image("Background.jpg");
        scene1.setFill(new ImagePattern(bg));

        EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        snake1.move("UP");
                        break;
                    case DOWN:
                        snake1.move("DOWN");
                        break;
                    case RIGHT:
                        snake1.move("RIGHT");
                        break;
                    case LEFT:
                        snake1.move("LEFT");
                        break;
                    case P:
                        snake1.grow();
                        layout.getChildren().add(snake1.SnakeVec.lastElement());
                        break;
                }
            }
        };


        scene1.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);

        primaryStage.setScene(scene1);
        primaryStage.show();

        while(true) {

        }
    }

}