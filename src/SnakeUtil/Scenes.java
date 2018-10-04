package SnakeUtil;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Scenes {
    //Creating the Main Menu Scene
    private Scene mainMenu;
    //Creating Game Scene
    private Scene game;

    //Class Constructor
    public Scenes(Stage PrimaryStage, Snake tmpSnake) {
        System.out.println("Inside Scenes Constructor");
        makeMainMenu(PrimaryStage);
        makeGame(tmpSnake);
    }

    //Private Function to set all the settings for the main menu
    private void makeMainMenu(Stage primaryStage) {
        //Instantiating the BorderPane class *layout* for mainMenu Scene
        BorderPane mainMenuLayout = new BorderPane();

        //Setting
        mainMenu = new Scene(mainMenuLayout, 720, 720);


        Button start = new Button("Start");
        start.setStyle("-fx-font: 24 arial;");
        //Having the start button send you to game Scene.
        start.setOnAction(e -> { primaryStage.setScene(game); });

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
        mainMenuLayout.setLeft(lvbox);
        mainMenuLayout.setRight(rvbox);
        mainMenuLayout.setCenter(cvbox);
    }
    private void makeGame(Snake tmpSnake) {
        //Creating gameLayout
        Pane gameLayout = new Pane();
        //Adding the initial Head of the snake (or whatever is in the SnakeVector atm)
        //to the layout
        gameLayout.getChildren().addAll(tmpSnake.SnakeVec);
        //Setting the Scene with the gameLayout that now contains a snake/snakes
        game = new Scene(gameLayout,1080,720);

        //Creating background image from "Background.jpg" and adding it to the game Scene
        Image bg = new Image("Background.jpg");
        game.setFill(new ImagePattern(bg));


        //The Event Handler for the Game Scene.
        //Takes in a Keystroke and will handle which key is pressed using a switch statement
        //Note: KeyEvent can be changed for any other event if you want to handle other events such as Mouse clicks and such
        EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        tmpSnake.setCurrentDir("UP");
                        break;
                    case DOWN:
                        tmpSnake.setCurrentDir("DOWN");
                        break;
                    case RIGHT:
                        tmpSnake.setCurrentDir("RIGHT");
                        break;
                    case LEFT:
                        tmpSnake.setCurrentDir("LEFT");
                        break;
                    case P:
                        tmpSnake.grow();
                        gameLayout.getChildren().add(tmpSnake.SnakeVec.lastElement());
                        break;
                }
            }
        };

        //Adding the EventHandler to the game Scene.
        //Note: KeyEvent has other variables so you can change when the eventHandler executes,
        //      For example you can have it execute when the key is released instead of pressed.
        game.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);

        //Timer and TimerTask to make the snake/snakes move together and at a same rate
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                tmpSnake.move();
            }
        };
        timer.scheduleAtFixedRate(task, 1, 250);
    }



    //SETS AND GETS
    public Scene getgame() { return game; };
    public Scene getMainMenu() { return mainMenu; };

    public void set2MM(Stage primaryStage) {
        primaryStage.setScene(mainMenu);
    }

    public void set2Game(Stage primaryStage) {
        primaryStage.setScene(game);
    }
}
