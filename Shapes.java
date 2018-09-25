import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import static javafx.scene.paint.Color.GREEN;

public class Shapes extends Application {

    public static void main(String[] arg) {
        launch(arg);
    }

    @Override
    public void start(Stage primaryStage) {

        //PICTURES??
        Image TigerS = new Image("TigerS.jpg");
        Image WhiteS = new Image("WhiteS.jpg");


        Circle circle1 = new Circle(500,500,20);
        circle1.setStroke(GREEN);
        circle1.setFill(new ImagePattern(TigerS));

        Circle circle2 = new Circle(100,100,20);
        circle2.setStroke(GREEN);
        circle2.setFill(new ImagePattern(WhiteS));

        Pane layout = new Pane();
        layout.getChildren().addAll(circle1);

        Scene scene1 = new Scene(layout,1000,1000);
        primaryStage.setScene(scene1);


        EventHandler<KeyEvent> eventHander = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
                    case UP:
                        circle1.setCenterY(circle1.getCenterY() - 10);
                        System.out.println("UP Pressed");
                        break;
                    case DOWN:
                        circle1.setCenterY(circle1.getCenterY() + 10);
                        System.out.println("DOWN Pressed");
                        break;
                    case RIGHT:
                        circle1.setCenterX(circle1.getCenterX() + 10);
                        System.out.println("RIGHT Pressed");
                        break;
                    case LEFT:
                        circle1.setCenterX(circle1.getCenterX() - 10);
                        System.out.println("LEFT Pressed");
                        break;
                }
            }
        };


        scene1.addEventFilter(KeyEvent.KEY_PRESSED, eventHander);
        primaryStage.show();

    }

}
