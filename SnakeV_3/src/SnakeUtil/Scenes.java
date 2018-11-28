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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.UnknownHostException;
import onlinetestcode.TransferPackage;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static javafx.scene.paint.Color.*;

public class Scenes {

    public Scenes() {
        makeMainMenu();
        makeGameOnline();
        makeGame();
        makeSettings();
        Image snake = new Image(getClass().getResourceAsStream("/SnakeUtil/Resources/SnakeIcon.png"));
        primaryStage.setTitle("Python: Snake Online");
        primaryStage.getIcons().add(snake);
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }


    private void makeMainMenu() {
        mainMenuLayout = new BorderPane();
        mainMenu = new Scene(mainMenuLayout, 1085, 735);
        mainMenu.setUserAgentStylesheet("/SnakeUtil/Resources/Background.css");
        mainMenuLayout.setId("Blimp");

        //START(ONLINE) BUTTON
        Button startONLINEButton = new Button("Start(Online)");
        //Having the start button send you to game Scene.
        startONLINEButton.setOnAction(e -> {
            primaryStage.setScene(gameONLINE);
        });

        //START BUTTON
        Button startButton = new Button("Start");
        //Having the start button send you to game Scene.
        startButton.setOnAction(e -> {
            primaryStage.setScene(game);
            try {
                startGame();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        //SETTINGS BUTTON
        Button settingsButton = new Button("Settings");
        //Having the settings button send you to settings
        settingsButton.setOnAction(e -> {
            primaryStage.setScene(settings);
        });

        Button exit = new Button("Exit");
        exit.setOnAction(e -> {
            primaryStage.close();
        });

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

        cvbox.getChildren().addAll(top, center, /*bottom,*/ startButton, startONLINEButton, settingsButton, exit);
        cvbox.setAlignment(Pos.CENTER);

        //Setting the top, bottom, center, right and left nodes to the pane
        mainMenuLayout.setLeft(lvbox);
        mainMenuLayout.setRight(rvbox);
        mainMenuLayout.setCenter(cvbox);
    }
    private void makeSettings() {

        BorderPane setting = new BorderPane();

        settings = new Scene(setting, 1085, 735);
        settings.setUserAgentStylesheet("SnakeUtil/Resources/Background.css");

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

        back_btn.setOnAction(e -> {
            primaryStage.setScene(mainMenu);
        });

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


    }
    private void makeGameOnline() {
        ONLINELayout = new Pane();
        gameONLINE = new Scene(ONLINELayout, layoutX, layoutY);
        gameONLINE.setUserAgentStylesheet("SnakeUtil/Resources/Background.css");
        ONLINELayout.setId("Blimp");

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
                }
            }
        };

        //Adding the EventHandler to the game Scene.
        //Note: KeyEvent has other variables so you can change when the eventHandler executes,
        //      For example you can have it execute when the key is released instead of pressed.
        gameONLINE.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);
    }
    private void makeGame() {
        gameLayout = new Pane();
        game = new Scene(gameLayout,layoutX,layoutY);
        game.setUserAgentStylesheet("SnakeUtil/Resources/Background.css");

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
                }
            }
        };

        //Adding the EventHandler to the game Scene.
        //Note: KeyEvent has other variables so you can change when the eventHandler executes,
        //      For example you can have it execute when the key is released instead of pressed.
        game.addEventFilter(KeyEvent.KEY_PRESSED, eventHandler);
    }


    private void startGame() throws IOException, UnknownHostException{
        Online online = new Online("ip");
        transferObj = online.getTP();
        if(transferObj.isGameOver() == true) {
            endGame("error");
        }


        makePoint();

        yourSnake.setBorderX(layoutX);
        yourSnake.setBorderY(layoutY);
        theirSnake.setBorderX(layoutX);
        theirSnake.setBorderY(layoutY);

        gameLayout.getChildren().removeAll(yourSnake.getSnake());
        gameLayout.getChildren().removeAll(theirSnake.getSnake());
        gameLayout.getChildren().removeAll(point);

        //sets/resets each snake to only the head node and location
        yourSnake.restartSnake(transferObj.getP1X(),transferObj.getP1Y(), "LEFT");
        theirSnake.restartSnake(transferObj.getP2X(),transferObj.getP2Y(), "RIGHT");

        gameLayout.getChildren().addAll(yourSnake.getSnake());
        gameLayout.getChildren().addAll(theirSnake.getSnake());
        gameLayout.getChildren().addAll(point);

        game.setRoot(gameLayout);

        speed = 200;

        makeTimerTask();

        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(task, 1500,  speed);
    }
    private void endGame(String winPlayer) {
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

    private void makeTimerTask() {
        //Creating the task to pass through the Timer to be executed every X ms
        //task to be executed every X ms is yourSnake.move(false);
        task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //COLLISION WITH OTHER SNAKE
                        if(yourSnake.collisionDetection(theirSnake.getSnake())) {
                            endGame(theirSnake.getName());
                            System.out.println("collision end");
                            //COLLISION WITH OTHER SNAKE
                        } else if(theirSnake.collisionDetection(yourSnake.getSnake())) {
                            endGame(yourSnake.getName());
                            System.out.println("collision end");
                            //COLLISION WITH YOURSELF
                        } else if(yourSnake.selfCollision()) {
                            endGame(theirSnake.getName());
                            System.out.println("self-collision end");
                            //COLLISION WITH YOURSELF
                        } else if(theirSnake.selfCollision()) {
                            endGame(yourSnake.getName());
                            System.out.println("self-collision end");
                            //COLLISION WITH A POINT
                        } else if (yourSnake.collisioncheck(point)) {
                            yourSnake.move(true);
                            gameLayout.getChildren().add(yourSnake.getSnake().lastElement());
                            theirSnake.move(false);
                            setSpeed();
                            setPoint();
                            //COLLISION WITH A POINT
                        } else if (theirSnake.collisioncheck(point)) {
                            theirSnake.move(true);
                            gameLayout.getChildren().add(theirSnake.getSnake().lastElement());
                            yourSnake.move(false);
                            setSpeed();
                            setPoint();
                        } else {
                            yourSnake.move(false);
                            theirSnake.move(false);
                        }

                    }
                });
            }
        };
    }
    private void makeONLINETimerTask() {
        task = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if(transferObj.isGameOver()) {
                            endGame("you");
                        } else {
                            yourSnake.update(transferObj.getP1X(), transferObj.getP1Y(),transferObj.does1Grow());
                            theirSnake.update(transferObj.getP2X(), transferObj.getP2Y(),transferObj.does2Grow());
                        }

                    }
                });
            };
        };
    }
    private void makePoint() {
        point = new Rectangle(35,35);
        Image apple = new Image("SnakeUtil/Resources/apple.jpg");
        point.setFill(new ImagePattern(apple));
        setPoint();
    }
    private void setPoint() {
        Random rand = new Random();
        point.setX(rand.nextInt(31) * 35);
        point.setY(rand.nextInt(21) * 35);
    }
    private void setSpeed() {
        speed -= 10;
        cancelTimer();
        gameTimer = new Timer();
        makeTimerTask();
        gameTimer.scheduleAtFixedRate(task, speed, speed);
    }
    private void cancelTimer() {
        gameTimer.cancel();
        gameTimer.purge();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private TransferPackage transferObj;
    private int speed;
    private Stage primaryStage = new Stage();
    private Stage gameOverWindow;
    private Scene mainMenu;
    private BorderPane mainMenuLayout;
    private Scene settings;
    private Scene gameONLINE;
    private Pane ONLINELayout;
    private Scene game;
    private Pane gameLayout;
    private Timer gameTimer;
    private TimerTask task;
    //Width in pixels of game
    private double layoutX = 1085;
    //Height in pixels of the game
    private double layoutY = 735;

    private Rectangle point;
    private Snake yourSnake = new Snake("SnakeUtil/Resources/Josh.jpg", "Josh");
    private Snake theirSnake = new Snake("SnakeUtil/Resources/Ruby.jpg", "Ruby");
}