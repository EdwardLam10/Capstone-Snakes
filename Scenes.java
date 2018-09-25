import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Scenes extends Application{

    Stage window;
    Scene scene1, scene2;

    public static void main(String[] arg) {
        launch(arg);
    }

    @Override
    public void start(Stage primaryStage){
        window = primaryStage;

        //Initializing things for Scene 1
        Label label1 = new Label("Scene 1");
        Button button1 = new Button("Goto Scene 2");
        button1.setOnAction(e -> {
            window.setScene(scene2);
            System.out.println("Scene set to Scene 2");
        });

        //Initializing things for Scene 2
        Label label2 = new Label("Scene 2");
        Button button2 = new Button("Goto Scene 1");
        button2.setOnAction(e -> {
            window.setScene(scene1);
            System.out.println("Scene set to Scene 1");
        });

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200,200);

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(label2, button2);
        scene2 = new Scene(layout2, 200,200);



        //Showing the whole thing
        window.setScene(scene1);
        window.show();
    }
}
