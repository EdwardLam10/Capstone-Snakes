
package snake;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
        
        Label top = new Label("\n Welcome to Snake Online \n");
        Label left = new Label("\n Player 1 \n");
        Label right = new Label("\n Player 2 \n");
        Label bottom = new Label("\n Waiting for second player to enter \n" + "\n Waiting for both players to click ready \n" + "\n Once both players have clicked ready \n" + "please click start \n");
        Label center = new Label("\n Waiting for Player to to enter \n");
        
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
        
        VBox lvbox = new VBox();
        lvbox.getChildren().add(left);
        lvbox.setAlignment(Pos.CENTER);
        
        VBox rvbox = new VBox();
        rvbox.getChildren().add(right);
        rvbox.setAlignment(Pos.CENTER);

        VBox cvbox = new VBox();
        cvbox.getChildren().addAll(top, center, bottom, start);
        cvbox.setAlignment(Pos.CENTER);
        
        //Setting the top, bottom, center, right and left nodes to the pane 
        bPane.setLeft(lvbox); 
        bPane.setRight(rvbox); 
        bPane.setCenter(cvbox);
        
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
        
    }
}
