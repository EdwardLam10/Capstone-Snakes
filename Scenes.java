
package snake;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Scenes extends Application{

    Stage window;
    Scene scene1, scene2;

    public static void main(String[] arg) {
        launch(arg);
    }

    @Override
    public void start(Stage primaryStage){
        
        
        
        //Instantiating the BorderPane class  
        BorderPane bPane = new BorderPane();  
        
        //Creating a scene object 
        //to make it a fixed size add hte size behind bPane
        Scene scene = new Scene(bPane, 720, 720);  
        
        bPane.getMaxWidth();
        
        Button start = new Button("Start");        
        start.setStyle("-fx-font: 24 arial;");
        bPane.setPrefSize(500,400);
        
        Label top = new Label("Welcome to Snake Online");
        Label left = new Label("\n Player 1 \n");
        Label right = new Label("\n Player 2 \n");
        Label bottom = new Label("Waiting for second player to enter \n or \n Waiting for player to click start");
        Label center = new Label("Center \n" + "Waiting for Player to to enter");
        
        //changing the font and size of the text
        top.setStyle("-fx-font: 24 arial;");
        left.setStyle("-fx-font: 24 arial;");
        right.setStyle("-fx-font: 24 arial;");
        bottom.setStyle("-fx-font: 24 arial;");
        center.setStyle("-fx-font: 24 arial;");
        
        //cnetering the text within each box
        top.setTextAlignment(TextAlignment.CENTER);
        left.setTextAlignment(TextAlignment.CENTER);
        right.setTextAlignment(TextAlignment.CENTER);
        bottom.setTextAlignment(TextAlignment.CENTER);
        center.setTextAlignment(TextAlignment.CENTER);
        
        

        VBox vbox = new VBox();
        vbox.getChildren().addAll(center, start);
        
        //Setting the top, bottom, center, right and left nodes to the pane 
        bPane.setTop(top); 
        bPane.setBottom(bottom); 
        bPane.setLeft(left); 
        bPane.setRight(right); 
        bPane.setCenter(vbox);
        
        start.setOnAction(e -> {
            //alert when the start is clicked
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Start button");
            String s ="the start button has been clicked";
            alert.setContentText(s);
            alert.show();
            window.setScene(scene2);
            System.out.println("Start Button Clicked");
        });

        //Setting title to the Stage
        primaryStage.setTitle("Main Menu"); 

        //Adding scene to the stage 
        primaryStage.setScene(scene);          

        //Displaying the contents of the stage 
        primaryStage.show();
        
        /*window = primaryStage;

        //Initializing things for Scene 1
        Label label1 = new Label("Main Menu");
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

        VBox layout1 = new VBox(0);
        layout1.getChildren().addAll(label1, button1);
        scene1 = new Scene(layout1, 200,200);

        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(label2, button2);
        scene2 = new Scene(layout2, 200,200);



        //Showing the whole thing
        window.setScene(scene1);
        window.show();*/
    }
}

