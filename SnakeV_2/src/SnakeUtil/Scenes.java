package SnakeUtil;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Scenes {

    //Class Constructor
    public Scenes(Stage PrimaryStage, Snake yourSnake, Snake enemySnake) {
        makeMainMenu(PrimaryStage);
        makeGame(yourSnake, enemySnake);
        makeSettings();
    }

    public void endGame() {
        gameTimer.cancel();
        gameTimer.purge();
    }


    //Private Function to set all the settings for the main menu
    private void makeMainMenu(Stage primaryStage) {
        //Instantiating the BorderPane class *layout* for mainMenu Scene
        BorderPane mainMenuLayout = new BorderPane();

        //Setting
        mainMenu = new Scene(mainMenuLayout, 720, 720);

        Button startButton = new Button("Start");
        startButton.setStyle("-fx-font: 24 arial;");
        //Having the start button send you to game Scene.
        startButton.setOnAction(e -> { primaryStage.setScene(game); });

        Button settingsButton = new Button("Settings");
        settingsButton.setStyle("-fx-font: 24 arial;");
        //Having the settings button send you to settings
        settingsButton.setOnAction(e -> { primaryStage.setScene(settings); });

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
        cvbox.getChildren().addAll(top, center, bottom, startButton, settingsButton);
        cvbox.setAlignment(Pos.CENTER);

        //Setting the top, bottom, center, right and left nodes to the pane
        mainMenuLayout.setLeft(lvbox);
        mainMenuLayout.setRight(rvbox);
        mainMenuLayout.setCenter(cvbox);
    }
    private void makeGame(Snake yourSnake, Snake theirSnake) {
        //Creating gameLayout
        Pane gameLayout = new Pane();
        //Adding the initial Head of the snake (or whatever is in the SnakeVector atm)
        //to the layout
        gameLayout.getChildren().addAll(yourSnake.SnakeVec);
        gameLayout.getChildren().addAll(theirSnake.SnakeVec);

        //Setting the Scene with the gameLayout that now contains a snake/snakes
        game = new Scene(gameLayout,1080,720);
        game.setUserAgentStylesheet("test.css");

        //The Event Handler for the Game Scene.
        //Takes in a Keystroke and will handle which key is pressed using a switch statement
        //Note: KeyEvent can be changed for any other event if you want to handle other events such as Mouse clicks and such
        EventHandler<KeyEvent> eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        yourSnake.setCurrentDir("UP");
                        break;
                    case DOWN:
                        yourSnake.setCurrentDir("DOWN");
                        break;
                    case RIGHT:
                        yourSnake.setCurrentDir("RIGHT");
                        break;
                    case LEFT:
                        yourSnake.setCurrentDir("LEFT");
                        break;
                    case P:
                        yourSnake.move(true);
                        gameLayout.getChildren().add(yourSnake.SnakeVec.lastElement());
                        break;

                    //MOVING 2ND SNAKE
                    case W:
                        theirSnake.setCurrentDir("UP");
                        break;
                    case S:
                        theirSnake.setCurrentDir("DOWN");
                        break;
                    case D:
                        theirSnake.setCurrentDir("RIGHT");
                        break;
                    case A:
                        theirSnake.setCurrentDir("LEFT");
                        break;
                    case O:
                        theirSnake.move(true);
                        gameLayout.getChildren().add(theirSnake.SnakeVec.lastElement());
                        break;
                }
            }
        };

        //Adding the EventHandler to the game Scene.
        //Note: KeyEvent has other variables so you can change when the eventHandler executes,
        //      For example you can have it execute when the key is released instead of pressed.
        game.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);

        //Creating the task to pass through the Timer to be executed every X ms
        //task to be executed every X ms is yourSnake.move(false);
        TimerTask task = new TimerTask() {
            public void run() {
                yourSnake.move(false);
                theirSnake.move(false);
            }
        };
        gameTimer.scheduleAtFixedRate(task, 1,  750);
    }
    private void makeSettings() {

    };


    //SETS AND GETS

    public void set2MM(Stage primaryStage) {
        primaryStage.setScene(mainMenu);
    }

    //Creating the Main Menu Scene
    private Scene mainMenu;
    //Creating Game Scene
    private Scene game;
    //Creating Settings Scene
    private Scene settings;
    //Timer for the game (helps dictate how fast the snake moves)
    private Timer gameTimer = new Timer();
}
