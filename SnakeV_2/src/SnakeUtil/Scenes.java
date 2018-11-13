package SnakeUtil;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static javafx.scene.paint.Color.*;

/*
ADD A HOW TO PLAY SCENE THAT CAN BE ACCESSED THROUGH MAIN MENU!!
 */


public class Scenes {

    //Class Constructor
    public Scenes(Stage PrimaryStage, Snake yourSnake, Snake enemySnake) {
        makeMainMenu(PrimaryStage, yourSnake, enemySnake);
        makePoint();
        makeGame(yourSnake, enemySnake, PrimaryStage);
        makeSettings(PrimaryStage, yourSnake);
    }

    //Cancels the timer and removes any functions still queued on the gameTimer
    private void cancelTimer() {
        gameTimer.cancel();
        gameTimer.purge();
    }


    //Private Function to set all the settings for the main menu
    private void makeMainMenu(Stage primaryStage, Snake yourSnake, Snake enemySnake) {
        //Instantiating the BorderPane class *layout* for mainMenu Scene
        mainMenuLayout = new BorderPane();

        //Setting
        mainMenu = new Scene(mainMenuLayout, 1085, 735);
        mainMenu.setUserAgentStylesheet("Resources/Background.css");


        mainMenuLayout.setId("Blimp");


        Button startButton = new Button("Start");
        //Having the start button send you to game Scene.
        startButton.setOnAction(e -> {
            primaryStage.setScene(game);
            startGame(yourSnake, enemySnake, primaryStage);
        });

        /*Label start = new Label("\n Start: \n");
        start.setTextAlignment(TextAlignment.CENTER);*/

        Button settingsButton = new Button("Settings");
        //Having the settings button send you to settings
        settingsButton.setOnAction(e -> {
            primaryStage.setScene(settings);
        });

       /* Button localGame = new Button("Local Game");
        localGame.setOnAction(e -> {
            primaryStage.setScene(game);
            startGame(yourSnake,enemySnake, primaryStage);
        });

        Button playWithRandom = new Button("Play with Random Player");
        playWithRandom.setOnAction(e -> {
            primaryStage.setScene(game);
            startGame(yourSnake,enemySnake, primaryStage);
        });*/

        Button exit = new Button("Exit");
        exit.setOnAction(e -> primaryStage.close());

        Label top = new Label("Welcome to Snake Online");
        Label left = new Label("Player 1");
        Label right = new Label("Player 2");
        //Label bottom = new Label("Waiting for second player to enter \n" + "Waiting for both players to click ready \n" + "Once both players have clicked ready \n" + "please click start \n");
        Label center = new Label("Waiting for Players to to enter");

        //changing the font and size of the text
        top.setFont(Font.font(30));
        left.setFont(Font.font(30));
        right.setFont(Font.font(30));
        //bottom.setFont(Font.font("arial", FontWeight.BOLD,30));
        center.setFont(Font.font(30));

        VBox lvbox = new VBox();
        lvbox.getChildren().add(left);
        lvbox.setAlignment(Pos.CENTER);

        VBox rvbox = new VBox();
        rvbox.getChildren().add(right);
        rvbox.setAlignment(Pos.CENTER);

        VBox cvbox = new VBox(10);

        cvbox.getChildren().addAll(top, center, /*bottom,*/ startButton, settingsButton, exit);
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
//        gameLayout.getChildren().addAll(yourSnake.getSnake());
//        gameLayout.getChildren().addAll(theirSnake.getSnake());
//        gameLayout.getChildren().addAll(point);

        gameLayout.setId("colorful");

        //Setting the Scene with the gameLayout that now contains a snake/snakes
        game = new Scene(gameLayout,layoutX,layoutY);
        game.setUserAgentStylesheet("Resources/Background.css");

        gameLayout.setId("Blimp");

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
                        endGame("Edward", primaryStage);
                }
            }
        };

        //Adding the EventHandler to the game Scene.
        //Note: KeyEvent has other variables so you can change when the eventHandler executes,
        //      For example you can have it execute when the key is released instead of pressed.
        game.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);
    }
    private void makeSettings(Stage primaryStage, Snake yourSnake) {

        BorderPane setting = new BorderPane();

        settings = new Scene(setting, 1085, 735);
        settings.setUserAgentStylesheet("Resources/Background.css");

        setting.setId("colorful");
        setting.setId("Blimp");


        Label setting_title = new Label("Setting");
        Label set_skin = new Label("Set color of\nskin");
        Label current_skin = new Label("Current color of\nskin");
        Label set_background = new Label("Set color of\nbackground");
        Label current_background = new Label("Current color of\nbackground");
        Button cur_color_skin = new Button("                               \n\n\n");
        Button cur_color_bg = new Button("                               \n\n\n");

        setting_title.setFont(Font.font(50));
        set_skin.setFont(Font.font(30));
        current_skin.setFont(Font.font(30));
        set_background.setFont(Font.font(30));
        current_background.setFont(Font.font(30));

        VBox back = new VBox();
        Button back_btn = new Button("Back");
        back.getChildren().add(back_btn);

        VBox title = new VBox();
        title.getChildren().add(setting_title);
        title.setAlignment(Pos.CENTER);

        HBox skin_title = new HBox(10);
        VBox skin_set = new VBox(10);
        VBox skin_cur = new VBox(10);
        HBox color_skin = new HBox(10);

        Button skin_color1 = new Button("               \n\n");
        skin_color1.setStyle("-fx-background-color: Red; -fx-font-size: 2em; ");
        Button skin_color2 = new Button("               \n\n");
        skin_color2.setStyle("-fx-background-color: Green; -fx-font-size: 2em; ");
        Button skin_color3 = new Button("               \n\n");
        skin_color3.setStyle("-fx-background-color: White; -fx-font-size: 2em; ");
        Button skin_color4 = new Button("               \n\n");
        skin_color4.setStyle("-fx-background-color: Yellow; -fx-font-size: 2em; ");
        Button skin_color5 = new Button("               \n\n");
        skin_color5.setStyle("-fx-background-color: Pink; -fx-font-size: 2em; ");
        Button skin_color6 = new Button("               \n\n");
        skin_color6.setStyle("-fx-background-color: Blue; -fx-font-size: 2em; ");
        color_skin.getChildren().addAll(skin_color1, skin_color2, skin_color3, skin_color4, skin_color5, skin_color6);

        skin_cur.getChildren().addAll(current_skin, cur_color_skin);
        skin_set.getChildren().addAll(set_skin, color_skin);
        skin_title.getChildren().addAll(skin_set, skin_cur);
        skin_title.setAlignment(Pos.CENTER);
        skin_cur.setAlignment(Pos.CENTER);
        skin_set.setAlignment(Pos.CENTER);

        HBox background_title = new HBox(10);
        VBox background_set = new VBox(10);
        VBox background_cur = new VBox(10);

        HBox color_background1 = new HBox(10);
        //HBox color_background2 = new HBox(10);

        Button bg_color1 = new Button("               \n\n");
        bg_color1.setId("Blimp");
        Button bg_color2 = new Button("               \n\n");
        bg_color2.setId("Sky");
        Button bg_color3 = new Button("               \n\n");
        bg_color3.setId("SkyIsland");
        Button bg_color4 = new Button("               \n\n");
        bg_color4.setId("Train");
        Button bg_color5 = new Button("               \n\n");
        bg_color5.setId("Cloud");
        Button bg_color6 = new Button("               \n\n");
        bg_color6.setId("NightSky");
        color_background1.getChildren().addAll(bg_color1, bg_color2, bg_color3, bg_color4, bg_color5, bg_color6);

        background_cur.getChildren().addAll(current_background, cur_color_bg);
        background_set.getChildren().addAll(set_background, color_background1/*, color_background2*/);
        background_title.getChildren().addAll(background_set, background_cur);
        background_cur.setAlignment(Pos.CENTER);
        background_set.setAlignment(Pos.CENTER);
        background_title.setAlignment(Pos.CENTER);

        VBox all = new VBox(50);
        all.getChildren().addAll(back, title, skin_title, background_title);

        cur_color_skin.setStyle("-fx-background-color: Red; -fx-font-size: 2em; ");
        yourSnake.setSnakeFill(RED);

        cur_color_bg.setId("Blimp");
        gameLayout.setId("Blimp");
        setting.setId("Blimp");
        mainMenuLayout.setId("Blimp");

        setting.setCenter(all);

        back_btn.setOnAction(e -> { primaryStage.setScene(mainMenu); });

        //START WRITING CONDITIONS TO CHANGE THE DIFFERENT COLORS TO CURRENT COLOR
        //FOR THE SKIN
        skin_color1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_skin.setStyle("-fx-background-color: Red; -fx-font-size: 2em; ");
                yourSnake.setSnakeFill(RED);
            }
        });
        skin_color2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_skin.setStyle("-fx-background-color: Green; -fx-font-size: 2em; ");
                yourSnake.setSnakeFill(GREEN);
            }
        });
        skin_color3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_skin.setStyle("-fx-background-color: White; -fx-font-size: 2em; ");
                yourSnake.setSnakeFill(WHITE);
            }
        });
        skin_color4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_skin.setStyle("-fx-background-color: Yellow; -fx-font-size: 2em; ");
                yourSnake.setSnakeFill(YELLOW);
            }
        });
        skin_color5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_skin.setStyle("-fx-background-color: Pink; -fx-font-size: 2em; ");
                yourSnake.setSnakeFill(PINK);
            }
        });
        skin_color6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_skin.setStyle("-fx-background-color: Blue; -fx-font-size: 2em; ");
                yourSnake.setSnakeFill(BLUE);
            }
        });

        //FOR THE BACKGROUND
        bg_color1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setId("Blimp");
                gameLayout.setId("Blimp");
                setting.setId("Blimp");
                mainMenuLayout.setId("Blimp");
            }
        });
        bg_color2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setId("Sky");
                gameLayout.setId("Sky");
                setting.setId("Sky");
                mainMenuLayout.setId("Sky");
            }
        });
        bg_color3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setId("SkyIsland");
                gameLayout.setId("SkyIsland");
                setting.setId("SkyIsland");
                mainMenuLayout.setId("SkyIsland");
            }
        });
        bg_color4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setId("Train");
                gameLayout.setId("Train");
                setting.setId("Train");
                mainMenuLayout.setId("Train");
            }
        });
        bg_color5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setId("Cloud");
                gameLayout.setId("Cloud");
                setting.setId("Cloud");
                mainMenuLayout.setId("Cloud");
            }
        });
        bg_color6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setId("NightSky");
                gameLayout.setId("NightSky");
                setting.setId("NightSky");
                mainMenuLayout.setId("NightSky");
            }
        });


    };
    private void makePoint() {
        point = new Rectangle(35,35);
        Image apple = new Image("Resources/apple.jpg");
        point.setFill(new ImagePattern(apple));
        resetPoint();
    }
    private void makeTimerTask(Snake yourSnake, Snake theirSnake, Stage primaryStage) {
        //Creating the task to pass through the Timer to be executed every X ms
        //task to be executed every X ms is yourSnake.move(false);
        task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        yourSnake.move(false);
                        theirSnake.move(false);
                        if(yourSnake.collisionDetection(theirSnake.getSnake())) {
                            endGame("Ruby", primaryStage);
                            System.out.println("collision end");
                        } else if(theirSnake.collisionDetection(yourSnake.getSnake())) {
                            endGame("Josh", primaryStage);
                            System.out.println("collision end");
                        } else if(yourSnake.selfCollision()) {
                            endGame("Ruby", primaryStage);
                            System.out.println("self-collision end");
                        } else if(theirSnake.selfCollision()) {
                            endGame("Josh", primaryStage);
                            System.out.println("self-collision end");
                        } else if (yourSnake.collisioncheck(point)) {
                            yourSnake.move(true);
                            gameLayout.getChildren().add(yourSnake.getSnake().lastElement());
//                            gameLayout.getChildren().add(yourSnake.getSnake().elementAt(1));

                            theirSnake.move(false);

                            setSpeed(yourSnake, theirSnake, primaryStage);

                            resetPoint();
                        } else if (theirSnake.collisioncheck(point)) {
                            theirSnake.move(true);
                            gameLayout.getChildren().add(theirSnake.getSnake().lastElement());
//                            gameLayout.getChildren().add(theirSnake.getSnake().elementAt(1));

                            yourSnake.move(false);

                            setSpeed(yourSnake, theirSnake, primaryStage);

                            resetPoint();
                        } else if(yourSnake.borderCollision(layoutX, layoutY)) {
                            endGame("Ruby", primaryStage);
                        } else if(theirSnake.borderCollision(layoutX, layoutY)) {
                            endGame("Josh", primaryStage);
                        }

                    }
                });
            }
        };
    }

    private void setSpeed(Snake yourSnake, Snake theirSnake, Stage primaryStage) {
        speed -= 20;
        cancelTimer();
        gameTimer = new Timer();
        makeTimerTask(yourSnake, theirSnake, primaryStage);
        gameTimer.scheduleAtFixedRate(task, 0, speed);
    }

    private void startGame(Snake yourSnake, Snake theirSnake, Stage primaryStage) {
        gameLayout.getChildren().removeAll(yourSnake.getSnake());
        gameLayout.getChildren().removeAll(theirSnake.getSnake());
        gameLayout.getChildren().removeAll(point);

        //sets/resets each snake to only the head node and location
        yourSnake.restartSnake(1050,700, "LEFT");
        theirSnake.restartSnake(0,0, "RIGHT");

        gameLayout.getChildren().addAll(yourSnake.getSnake());
        gameLayout.getChildren().addAll(theirSnake.getSnake());
        gameLayout.getChildren().addAll(point);

        game.setRoot(gameLayout);

        speed = 200;

        makeTimerTask(yourSnake, theirSnake, primaryStage);

        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(task, 1500,  speed);

    }
    //Stops the game and creates the alert screen that shows who won.
    public void endGame(String winPlayer, Stage primaryStage) {
        //Ends and stops snakes from moving.
        cancelTimer();
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

    public void moveSnake(Snake snake1, Boolean grows) {
        snake1.move(grows);
    }

    private void resetPoint() {
        Random rand = new Random();
        point.setX(rand.nextInt(31) * 35);
        point.setY(rand.nextInt(21) * 35);
    }

    public void setPoint(int X, int Y) {
        point.setX(X);
        point.setY(Y);
    }

    //Sets Scene of the primaryStage to main menu Scene
    public void set2MM(Stage primaryStage) {
        primaryStage.setScene(mainMenu);
    }

    //Speed at which snake moves, the lower the faster
    public int speed;
    //Width in pixels of game
    private double layoutX = 1085;
    //Height in pixels of the game
    private double layoutY = 735;
    //Creating the Main Menu Scene
    private Scene mainMenu;
    //Creating Game Scene
    private Scene game;
    //Game layout
    private Pane gameLayout;
    //MainMenu Layout
    private BorderPane mainMenuLayout;
    //Creating Settings Scene
    private Scene settings;
    //Game Over Alert
    private Stage gameOverWindow;
    //Timer for the game (helps dictate how fast the snake moves)
    private Timer gameTimer;
    //TimerTask the functions that are called periodically in Timer;
    private TimerTask task;
    //Point
    private Rectangle point;
}
