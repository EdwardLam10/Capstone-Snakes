package SnakeUtil;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class Scenes {

    //Class Constructor
    public Scenes(Stage PrimaryStage, Snake yourSnake, Snake enemySnake) {
        makeMainMenu(PrimaryStage, yourSnake, enemySnake);
        makeGame(yourSnake, enemySnake, PrimaryStage);
        makeSettings(PrimaryStage, yourSnake);
    }

    //Cancels the timer and removes any functions still queued on the gameTimer
    public void endGame() {
        gameTimer.cancel();
        gameTimer.purge();
    }


     //Private Function to set all the settings for the main menu
    private void makeMainMenu(Stage primaryStage, Snake yourSnake, Snake enemySnake) {
        //Instantiating the BorderPane class *layout* for mainMenu Scene
        BorderPane mainMenuLayout = new BorderPane();

        //Setting
        mainMenu = new Scene(mainMenuLayout, 720, 720);

<<<<<<< HEAD
        //CHANGE
        mainMenuLayout.setId("colorful");

        Button startButton = new Button("Start");
        //Having the start button send you to game Scene.
        startButton.setOnAction(e -> {
            primaryStage.setScene(game);
            startGame(yourSnake,enemySnake,primaryStage);
        });

        Button settingsButton = new Button("Settings");
        //Having the settings button send you to settings
        settingsButton.setOnAction(e -> { primaryStage.setScene(settings); });

        Button endButton = new Button("End");
        endButton.setOnAction(e -> {
            primaryStage.close();
=======
        /* startButton = new Button("Start");
        startButton.setStyle("-fx-font: 24 arial;");
        //Having the start button send you to game Scene.
        startButton.setOnAction(e -> {
            primaryStage.setScene(game);
            startGame(yourSnake,enemySnake);
        });*/

        Label start = new Label("\n Start: \n");
        start.setStyle("-fx-font: 24 arial;");
        start.setTextAlignment(TextAlignment.CENTER);

        Button settingsButton = new Button("Settings");
        settingsButton.setStyle("-fx-font: 24 arial;");
        //Having the settings button send you to settings
        settingsButton.setOnAction(e -> { primaryStage.setScene(settings); });

        Button localGame = new Button("Local Game");
        localGame.setStyle("-fx-font: 24 arial;");
        localGame.setOnAction(e -> {
            primaryStage.setScene(game);
            startGame(yourSnake,enemySnake);
        });

        Button playWithRandom = new Button("Play with Random Player");
        playWithRandom.setStyle("-fx-font: 24 arial;");
        playWithRandom.setOnAction(e -> {
            primaryStage.setScene(game);
            startGame(yourSnake,enemySnake);
>>>>>>> efb37241706b35aa9ed6121590b898459f056c75
        });

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
<<<<<<< HEAD
        cvbox.getChildren().addAll(top, center, bottom, startButton, settingsButton, endButton);
=======
        cvbox.getChildren().addAll(top, center, bottom, start,localGame, playWithRandom, settingsButton);
>>>>>>> efb37241706b35aa9ed6121590b898459f056c75
        cvbox.setAlignment(Pos.CENTER);

        //Setting the top, bottom, center, right and left nodes to the pane
        mainMenuLayout.setLeft(lvbox);
        mainMenuLayout.setRight(rvbox);
        mainMenuLayout.setCenter(cvbox);
    }
    private void makeGame(Snake yourSnake, Snake theirSnake, Stage primaryStage) {
        //Creating gameLayout
        gameLayout = new Pane();
        //Adding the initial Head of the snake (or whatever is in the SnakeVector atm)
        //to the layout
        gameLayout.getChildren().addAll(yourSnake.getSnake());
        gameLayout.getChildren().addAll(theirSnake.getSnake());

        String colorfulcss = "Resources/test.css";

        //Setting the Scene with the gameLayout that now contains a snake/snakes
        game = new Scene(gameLayout,1080,720);
        game.setUserAgentStylesheet(colorfulcss);

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
                    /*
                        THIS CASE IS
                        FOR TESTING ONLY
                     */
                    case P:
                        yourSnake.move(true);
                        gameLayout.getChildren().add(yourSnake.getSnake().lastElement());
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
                    /*
                        THIS CASE IS
                        FOR TESTING ONLY
                     */
                    case O:
                        theirSnake.move(true);
                        gameLayout.getChildren().add(theirSnake.getSnake().lastElement());
                        break;
                    /*
                        THIS CASE IS
                        FOR TESTING ONLY
                     */
                    case I:
                        makeAlertStage("Edward", primaryStage);
                }
            }
        };

        //Adding the EventHandler to the game Scene.
        //Note: KeyEvent has other variables so you can change when the eventHandler executes,
        //      For example you can have it execute when the key is released instead of pressed.
        game.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);
    }
    public void makeSettings(Stage primaryStage, Snake yourSnake) {

        BorderPane setting = new BorderPane();

        settings = new Scene(setting, 720, 720);
        settings.setUserAgentStylesheet("Resources/settings.css");

        Label setting_title = new Label("Setting \n   ");
        Label set_skin = new Label("   \n Set color of \n skin \n   ");
        Label current_skin = new Label("   \n Current color of \n skin \n   ");
        Label set_background = new Label("   \n Set color of \n background \n   ");
        Label current_background = new Label("   \n Current color of\n  background \n   ");
        Button cur_color_skin = new Button("Current \n Skin");
        Button cur_color_bg = new Button("Current \n Background");

        setting_title.setStyle("-fx-font: 50 arial;");
        set_skin.setStyle("-fx-font: 24 arial;");
        current_skin.setStyle("-fx-font: 24 arial;");
        set_background.setStyle("-fx-font: 24 arial;");
        current_background.setStyle("-fx-font: 24 arial;");
        cur_color_skin.setStyle("-fx-font-size: 2em; ");
        cur_color_bg.setStyle("-fx-font-size: 2em; ");

        setting_title.setTextAlignment(TextAlignment.CENTER);
        set_skin.setTextAlignment(TextAlignment.CENTER);
        current_skin.setTextAlignment(TextAlignment.CENTER);
        set_background.setTextAlignment(TextAlignment.CENTER);
        current_background.setTextAlignment(TextAlignment.CENTER);
        cur_color_skin.setTextAlignment(TextAlignment.CENTER);
        cur_color_bg.setTextAlignment(TextAlignment.CENTER);

        VBox back = new VBox();
        Button back_btn = new Button("Back");
        back_btn.setStyle("-fx-font-size: 2em; ");
        back.getChildren().add(back_btn);

        VBox title = new VBox();
        title.getChildren().add(setting_title);
        title.setAlignment(Pos.CENTER);

        HBox skin_title = new HBox();
        VBox skin_set = new VBox();
        VBox skin_cur = new VBox();
        HBox color_skin = new HBox(10);

        Button skin_color1 = new Button("Red");
        skin_color1.setStyle("-fx-background-color: Red; -fx-font-size: 2em; ");
        Button skin_color2 = new Button("Green");
        skin_color2.setStyle("-fx-background-color: Green; -fx-font-size: 2em; ");
        Button skin_color3 = new Button("White");
        skin_color3.setStyle("-fx-background-color: White; -fx-font-size: 2em; ");
        Button skin_color4 = new Button("Yellow");
        skin_color4.setStyle("-fx-background-color: Yellow; -fx-font-size: 2em; ");
        Button skin_color5 = new Button("Pink");
        skin_color5.setStyle("-fx-background-color: Pink; -fx-font-size: 2em; ");
        Button skin_color6 = new Button("Blue");
        skin_color6.setStyle("-fx-background-color: Blue; -fx-font-size: 2em; ");
        color_skin.getChildren().addAll(skin_color1, skin_color2, skin_color3, skin_color4, skin_color5, skin_color6);

        skin_cur.getChildren().addAll(current_skin, cur_color_skin);
        skin_set.getChildren().addAll(set_skin, color_skin);
        skin_title.getChildren().addAll(skin_set, skin_cur);
        skin_title.setAlignment(Pos.CENTER);
        skin_cur.setAlignment(Pos.CENTER);
        skin_set.setAlignment(Pos.CENTER);

        HBox background_title = new HBox();
        VBox background_set = new VBox();
        VBox background_cur = new VBox();
        HBox color_background = new HBox(10);

        Button bg_color1 = new Button("Black");
        bg_color1.setStyle("-fx-background-color: Black; -fx-font-size: 2em; ");
        Button bg_color2 = new Button("White");
        bg_color2.setStyle("-fx-background-color: White; -fx-font-size: 2em; ");
        Button bg_color3 = new Button("Red");
        bg_color3.setStyle("-fx-background-color: Red; -fx-font-size: 2em; ");
        Button bg_color4 = new Button("Purple");
        bg_color4.setStyle("-fx-background-color: Purple; -fx-font-size: 2em; ");
        Button bg_color5 = new Button("Yellow");
        bg_color5.setStyle("-fx-background-color: Yellow; -fx-font-size: 2em; ");
        Button bg_color6 = new Button("Gray");
        bg_color6.setStyle("-fx-background-color: Gray; -fx-font-size: 2em; ");
        color_background.getChildren().addAll(bg_color1, bg_color2, bg_color3, bg_color4, bg_color5, bg_color6);

        background_cur.getChildren().addAll(current_background, cur_color_bg);
        background_set.getChildren().addAll(set_background, color_background);
        background_title.getChildren().addAll(background_set, background_cur);
        background_cur.setAlignment(Pos.CENTER);
        background_set.setAlignment(Pos.CENTER);
        background_title.setAlignment(Pos.CENTER);

        VBox all = new VBox();
        all.getChildren().addAll(back, title, skin_title, background_title);

        setting.setCenter(all);

        back_btn.setOnAction(e -> { primaryStage.setScene(mainMenu); });

        //START WRITING CONDITIONS TO CHANGE THE DIFFERENT COLORS TO CURRENT COLOR
        //FOR THE SKIN
        skin_color1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_skin.setStyle("-fx-background-color: Red; -fx-font-size: 2em; ");
                yourSnake.getSnakeFill(RED);
            }
        });
        skin_color2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_skin.setStyle("-fx-background-color: Green; -fx-font-size: 2em; ");
                yourSnake.getSnakeFill(GREEN);
            }
        });
        skin_color3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_skin.setStyle("-fx-background-color: White; -fx-font-size: 2em; ");
                yourSnake.getSnakeFill(WHITE);
            }
        });
        skin_color4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_skin.setStyle("-fx-background-color: Yellow; -fx-font-size: 2em; ");
                yourSnake.getSnakeFill(YELLOW);
            }
        });
        skin_color5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_skin.setStyle("-fx-background-color: Pink; -fx-font-size: 2em; ");
            }
        });
        skin_color6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_skin.setStyle("-fx-background-color: Blue; -fx-font-size: 2em; ");
            }
        });

        //FOR THE BACKGROUND
        bg_color1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setStyle("-fx-background-color: Black; -fx-font-size: 2em; ");
                gameLayout.setId("BlackBG");
            }
        });
        bg_color2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setStyle("-fx-background-color: White; -fx-font-size: 2em; ");
            }
        });
        bg_color3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setStyle("-fx-background-color: Red; -fx-font-size: 2em; ");
                gameLayout.setId("RedBG");
            }
        });
        bg_color4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setStyle("-fx-background-color: Purple; -fx-font-size: 2em; ");
            }
        });
        bg_color5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setStyle("-fx-background-color: Yellow; -fx-font-size: 2em; ");
            }
        });
        bg_color6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setStyle("-fx-background-color: Gray; -fx-font-size: 2em; ");
            }
        });


    };
    private void makeAlertStage(String winPlayer, Stage primaryStage) {
        //Ends and stops snakes from moving.
        endGame();
        gameOverWindow = new Stage();
        gameOverWindow.setTitle("Game Over!");

        //This keeps the user from clicking on anything other than the alert box.
        gameOverWindow.initModality(Modality.APPLICATION_MODAL);
        VBox gameOverLayout = new VBox();

        //Text to show who has won.
        Label gameOverLabel = new Label(winPlayer+" has won!");
        gameOverLabel.setStyle("-fx-font: 24 arial;");
        gameOverLabel.setTextAlignment(TextAlignment.CENTER);


        //Option 1 @ Game Over: Sends player back to main menu to play again.
        Button gameOverButton = new Button("Main Menu");
        gameOverButton.setStyle("-fx-font: 24 arial;");
        gameOverButton.setOnAction(e -> {
            primaryStage.setScene(mainMenu);
            gameOverWindow.close();
        });


        //Option 2 @ Game Over: Rage Quit and exit program.
        Button gameOverButton2 = new Button("Exit");
        gameOverButton2.setStyle("-fx-font: 24 arial;");
        gameOverButton2.setOnAction(e -> {
            primaryStage.close();
            gameOverWindow.close();
        });

        gameOverLayout.getChildren().addAll(gameOverLabel, gameOverButton, gameOverButton2);
        gameOverLayout.setAlignment(Pos.CENTER);

        Scene gameOverScene = new Scene(gameOverLayout,200,150);
        gameOverWindow.setScene(gameOverScene);
        //Setting the Opacity of the alert window
        gameOverWindow.setOpacity(0.95);
        gameOverWindow.show();
        //If you X out of the Game Over alert, you will be sent back to the main menu.
        gameOverWindow.setOnCloseRequest(e -> {
            primaryStage.setScene(mainMenu);
            gameOverWindow.close();
        });
    }

    private void startGame(Snake yourSnake, Snake theirSnake, Stage primaryStage) {
        //sets/resets each snake to only the head node and location
        yourSnake.restartSnake(980,360);
        theirSnake.restartSnake(100,360);

        gameLayout = new Pane();
        gameLayout.getChildren().addAll(yourSnake.getSnake());
        gameLayout.getChildren().addAll(theirSnake.getSnake());

        game.setRoot(gameLayout);

        gameTimer = new Timer();
        //Creating the task to pass through the Timer to be executed every X ms
        //task to be executed every X ms is yourSnake.move(false);
        TimerTask task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        yourSnake.move(false);
                        theirSnake.move(false);
                        if(yourSnake.collisionDetection(theirSnake.getSnake())) {
                            endGame();
                            makeAlertStage("Josh", primaryStage);
                        } else if(theirSnake.collisionDetection(yourSnake.getSnake())) {
                            endGame();
                            makeAlertStage("Ruby", primaryStage);
                        }

                    }
                });
            }
        };
        gameTimer.scheduleAtFixedRate(task, 1,  750);

    }

    //SETS AND GETS

    public void set2MM(Stage primaryStage) {
        primaryStage.setScene(mainMenu);
    }

    //Creating the Main Menu Scene
    private Scene mainMenu;
    //Creating Game Scene
    private Scene game;
    //Game layout
    private Pane gameLayout;
    //Creating Settings Scene
    private Scene settings;
    //Game Over Alert
    private Stage gameOverWindow;
    //Timer for the game (helps dictate how fast the snake moves)
    private Timer gameTimer;
}
