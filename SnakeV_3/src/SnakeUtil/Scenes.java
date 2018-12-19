package SnakeUtil;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import onlinetestcode.TransferPackage;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Paths;
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
        makeCredits();
        Image snake = new Image(getClass().getResourceAsStream("/SnakeUtil/Resources/SnakeIcon.png"));
        primaryStage.setTitle("Viper: Snake Online");
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
        Button startONLINEButton = new Button("Start (Online)");
        //Having the start button send you to game Scene.
        startONLINEButton.setOnAction(e -> {
            primaryStage.setScene(gameONLINE);
            try {
                startONLINEGame();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        //START BUTTON
        Button startButton = new Button("Start");
        //Having the start button send you to game Scene.
        startButton.setOnAction(e -> {
            primaryStage.setScene(game);
            startGame();
        });

        //SETTINGS BUTTON
        Button settingsButton = new Button("Settings");
        //Having the settings button send you to settings
        settingsButton.setOnAction(e -> {
            primaryStage.setScene(settings);
        });

        //CREDITS BUTTON
        Button creditsButton = new Button("Credits");
        creditsButton.setOnAction(e-> primaryStage.setScene(credits));


        //EXIT BUTTON
        Button exit = new Button("Exit");
        exit.setOnAction(e -> {
            primaryStage.close();
        });

        //Christmas Button
        Button christmas = new Button("Christmas");
        christmas.setOnAction(e-> {
            gameLayout.setId("ChristmasTree");
            settingsLayout.setId("ChristmasTree");
            mainMenuLayout.setId("ChristmasTree");
            creditsLayout.setId("ChristmasTree");
            yourSnake.setSnakeHead("SnakeUtil/Resources/Santa.jpg");
            yourSnake.setSnakeBody("SnakeUtil/Resources/MilkCookies.jpg");
            theirSnake.setSnakeHead("SnakeUtil/Resources/grinch.jpg");
            theirSnake.setSnakeBody("SnakeUtil/Resources/present1.jpg");
            pointIMG = new Image("SnakeUtil/Resources/House.jpg");
            Media media = new Media(Paths.get("Jingle-Bells.mp3").toUri().toString());
            MediaPlayer player = new MediaPlayer(media);
            player.play();
        });

        Label top = new Label("Welcome to Viper");
        //Label bottom = new Label("Waiting for second player to enter \n" + "Waiting for both players to click ready \n" + "Once both players have clicked ready \n" + "please click start \n");
        Label center = new Label("Waiting for Players to to enter");

        //changing the font and size of the text
        top.setFont(Font.font(30));
        //bottom.setFont(Font.font("arial", FontWeight.BOLD,30));
        center.setFont(Font.font(30));


        VBox cvbox = new VBox(10);

        cvbox.getChildren().addAll(top, /*bottom,*/ startButton, startONLINEButton, settingsButton, creditsButton, exit);
        cvbox.setAlignment(Pos.CENTER);



        //Setting the top, bottom, center, right and left nodes to the pane
        mainMenuLayout.setCenter(cvbox);
        mainMenuLayout.setTop(christmas);

    }
    private void makeSettings() {
        settingsLayout = new BorderPane();
        settings = new Scene(settingsLayout, 1085, 735);
        settings.setUserAgentStylesheet("SnakeUtil/Resources/Background.css");

        settingsLayout.setId("colorful");
        settingsLayout.setId("Blimp");


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

        Label name_label = new Label("Name");
        name_label.setFont(Font.font(30));
        TextField name1 = new TextField();
        name1.setPromptText("Enter name");
        name1.setFont(Font.font(25));
        name1.setAlignment(Pos.CENTER);
        EventHandler<ActionEvent> event_name1 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                name_label.setText(name1.getText());
                yourSnake.setName(name1.getText());
            }
        };
        name1.setOnAction(event_name1);

        VBox back = new VBox();
        Button back_btn = new Button("Back");
        back.getChildren().add(back_btn);

        VBox title = new VBox();
        title.getChildren().add(setting_title);
        title.setAlignment(Pos.CENTER);

        HBox name = new HBox(10);
        name.getChildren().addAll(name_label, name1);
        name.setAlignment(Pos.CENTER);

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
        all.getChildren().addAll(back, title, name, skin_title, background_title);

        cur_color_skin.setStyle("-fx-background-color: Red; -fx-font-size: 2em; ");
        yourSnake.setSnakeFill(RED);

        cur_color_bg.setId("Blimp");
        gameLayout.setId("Blimp");
        settingsLayout.setId("Blimp");
        mainMenuLayout.setId("Blimp");

        settingsLayout.setCenter(all);

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
                settingsLayout.setId("Blimp");
                mainMenuLayout.setId("Blimp");
            }
        });
        bg_color2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setId("Sky");
                gameLayout.setId("Sky");
                settingsLayout.setId("Sky");
                mainMenuLayout.setId("Sky");
            }
        });
        bg_color3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setId("SkyIsland");
                gameLayout.setId("SkyIsland");
                settingsLayout.setId("SkyIsland");
                mainMenuLayout.setId("SkyIsland");
            }
        });
        bg_color4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setId("Train");
                gameLayout.setId("Train");
                settingsLayout.setId("Train");
                mainMenuLayout.setId("Train");
            }
        });
        bg_color5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setId("Cloud");
                gameLayout.setId("Cloud");
                settingsLayout.setId("Cloud");
                mainMenuLayout.setId("Cloud");
            }
        });
        bg_color6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cur_color_bg.setId("NightSky");
                gameLayout.setId("NightSky");
                settingsLayout.setId("NightSky");
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
    private void makeCredits() {
        creditsLayout = new BorderPane();

        credits = new Scene(creditsLayout, 1085, 735);
        credits.setUserAgentStylesheet("SnakeUtil/Resources/Background.css");
        creditsLayout.setId("colorful");
        creditsLayout.setId("Blimp");

        Button back = new Button("Back");
        back.setOnAction(event -> primaryStage.setScene(mainMenu));
        creditsLayout.setTop(back);

        VBox center = new VBox(20);
        center.setAlignment(Pos.CENTER);

        Label credit_title = new Label("Credits page");
        credit_title.setFont(Font.font(50));


        HBox images_credits = new HBox();
        images_credits.setAlignment(Pos.CENTER);
        Label images_title = new Label("Image credits:");
        images_title.setFont(Font.font(35));
        images_credits.getChildren().add(images_title);

        HBox line1 = new HBox(10);
        HBox line2 = new HBox(10);
        HBox line3 = new HBox(10);
        HBox line4 = new HBox(10);
        HBox line5 = new HBox(10);
        HBox line6 = new HBox(10);

        line1.setAlignment(Pos.CENTER);
        line2.setAlignment(Pos.CENTER);
        line3.setAlignment(Pos.CENTER);
        line4.setAlignment(Pos.CENTER);
        line5.setAlignment(Pos.CENTER);
        line6.setAlignment(Pos.CENTER);

        Button line1_btn = new Button("               \n\n");
        Button line2_btn = new Button("               \n\n");
        Button line3_btn = new Button("               \n\n");
        Button line4_btn = new Button("               \n\n");
        Button line5_btn = new Button("               \n\n");
        Button line6_btn = new Button("               \n\n");

        line1_btn.setId("Blimp");
        line2_btn.setId("Sky");
        line3_btn.setId("SkyIsland");
        line4_btn.setId("Train");
        line5_btn.setId("Cloud");
        line6_btn.setId("NightSky");

        Label line1_link = new Label("https://anime-pictures.net/pictures/view_post/430983?lang=en");
        Label line2_link = new Label("https://wallpapercave.com/w/wp2394212");
        Label line3_link = new Label("https://www.artstation.com/artwork/La9XR");
        Label line4_link = new Label("https://www.wallpaperup.com/877343/caring201_clouds_original_scenic_tagme_train.html");
        Label line5_link = new Label("http://www.allfinweb.com/single/23256048-anime-wallpaper-feathers.html");
        Label line6_link = new Label("https://images5.alphacoders.com/495/495521.jpg");

        line1_link.setFont(Font.font(25));
        line2_link.setFont(Font.font(25));
        line3_link.setFont(Font.font(25));
        line4_link.setFont(Font.font(25));
        line5_link.setFont(Font.font(25));
        line6_link.setFont(Font.font(25));

        line1.getChildren().addAll(line1_btn, line1_link);
        line2.getChildren().addAll(line2_btn, line2_link);
        line3.getChildren().addAll(line3_btn, line3_link);
        line4.getChildren().addAll(line4_btn, line4_link);
        line5.getChildren().addAll(line5_btn, line5_link);
        line6.getChildren().addAll(line6_btn, line6_link);


        center.getChildren().addAll(credit_title, images_credits, line1, line2, line3, line4, line5, line6);
        creditsLayout.setCenter(center);
    };

    private void startONLINEGame() throws IOException, UnknownHostException{

        online = new Online("146.95.21.219");
        online.sendDirection("UP");

        transferObj = online.getTP();


        if(transferObj.isGameOver() == true) {
            endGame("error");
        }

        ONLINELayout.getChildren().removeAll(yourSnake.getSnake());
        ONLINELayout.getChildren().removeAll(theirSnake.getSnake());
        ONLINELayout.getChildren().removeAll(point);

        makePoint();
        setPoint((double) transferObj.getAppleX()*35, (double)transferObj.getAppleY()*35);

        //sets/resets each snake to only the head node and location
        yourSnake.restartSnake(transferObj.getP1X() * 35,transferObj.getP1Y() * 35, "RIGHT");
        theirSnake.restartSnake(transferObj.getP2X() * 35,transferObj.getP2Y() * 35, "LEFT");

        ONLINELayout.getChildren().addAll(yourSnake.getSnake());
        ONLINELayout.getChildren().addAll(theirSnake.getSnake());
        ONLINELayout.getChildren().addAll(point);

        gameONLINE.setRoot(ONLINELayout);

        makeONLINETimerTask();

        speed = 170;

        ONLINETimer = new Timer();
        ONLINETimer.scheduleAtFixedRate(ONLINEtask, 1500, speed);

    }
    private void startGame() {

        yourSnake.setBorderX(layoutX);
        yourSnake.setBorderY(layoutY);
        theirSnake.setBorderX(layoutX);
        theirSnake.setBorderY(layoutY);

        gameLayout.getChildren().removeAll(yourSnake.getSnake());
        gameLayout.getChildren().removeAll(theirSnake.getSnake());
        gameLayout.getChildren().removeAll(point);

        makePoint();
        setRandPoint();

        //sets/resets each snake to only the head node and location
        yourSnake.restartSnake(1050,700, "LEFT");
        theirSnake.restartSnake(0,0, "RIGHT");

        gameLayout.getChildren().addAll(yourSnake.getSnake());
        gameLayout.getChildren().addAll(theirSnake.getSnake());
        gameLayout.getChildren().addAll(point);

        game.setRoot(gameLayout);

        speed = 170;

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
    private void endONLINEGame(String winPlayer) {
        //Ends and stops snakes from moving.
        cancelONLINETimer();

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
                            setRandPoint();
                            //COLLISION WITH A POINT
                        } else if (theirSnake.collisioncheck(point)) {
                            theirSnake.move(true);
                            gameLayout.getChildren().add(theirSnake.getSnake().lastElement());
                            yourSnake.move(false);
                            setSpeed();
                            setRandPoint();
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
        //Creating the task to pass through the Timer to be executed every X ms
        //task to be executed every X ms is yourSnake.move(false);
        ONLINEtask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            online.sendDirection(yourSnake.getSnakeDirection());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        transferObj = online.getTP();
                        if(transferObj.isGameOver()) {
                            try {
                                online.closeOnline();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if(transferObj.whoWon()) {
                                endONLINEGame(yourSnake.getName());
                            } else {
                                endONLINEGame("!You");
                            }
                        } else {
                            yourSnake.update(transferObj.getP1X() * 35, transferObj.getP1Y() * 35,transferObj.does1Grow());
                            theirSnake.update(transferObj.getP2X() * 35, transferObj.getP2Y() * 35,transferObj.does2Grow());
                            if(transferObj.does1Grow() || transferObj.does2Grow()) {
                                setPoint(transferObj.getAppleX()*35, transferObj.getAppleY()*35);
                                System.out.println("applex : " + transferObj.getAppleX());
                                System.out.println("appley : " + transferObj.getAppleY());
                                if(transferObj.does1Grow()) {
                                    ONLINELayout.getChildren().addAll(yourSnake.getSnake().lastElement());
                                }
                                if(transferObj.does2Grow()) {
                                    ONLINELayout.getChildren().addAll(theirSnake.getSnake().lastElement());
                                }
                            }
                        }
                    }
                });
            }
        };
    }

    private void makePoint() {
        point = new Rectangle(35,35);
        point.setFill(new ImagePattern(pointIMG));
        point.setStroke(RED);
    }
    private void setPoint(double x, double y) {
       point.setX(x);
       point.setY(y);
    }
    private void setRandPoint() {
        Random rand = new Random();
        point.setX(rand.nextInt(31) * 35);
        point.setY(rand.nextInt(21) * 35);
    }

    private void setSpeed() {
        speed -= 5;
        cancelTimer();
        gameTimer = new Timer();
        makeTimerTask();
        gameTimer.scheduleAtFixedRate(task, speed, speed);
    }
    private  void setONLINESpeed() {
        speed -= 5;
        cancelONLINETimer();
        gameTimer = new Timer();
        makeTimerTask();
        gameTimer.scheduleAtFixedRate(ONLINEtask, speed, speed);
    }
    private void cancelTimer() {
        gameTimer.cancel();
        gameTimer.purge();
    }
    private void cancelONLINETimer() {
        ONLINETimer.purge();
        ONLINETimer.cancel();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private Scene credits;
    private BorderPane creditsLayout;
    private Online online;
    private TransferPackage transferObj;
    private int speed;
    private Stage primaryStage = new Stage();
    private Stage gameOverWindow;
    private Scene mainMenu;
    private BorderPane mainMenuLayout;
    private Scene settings;
    private BorderPane settingsLayout;
    private Scene gameONLINE;
    private Pane ONLINELayout;
    private Scene game;
    private Pane gameLayout;
    private Timer ONLINETimer;
    private Timer gameTimer;
    private TimerTask task;
    private TimerTask ONLINEtask;
    private Image pointIMG = new Image("SnakeUtil/Resources/apple.jpg");
    //Width in pixels of game
    private double layoutX = 1085;
    //Height in pixels of the game
    private double layoutY = 735;
    private Rectangle point;
    private Snake yourSnake = new Snake("SnakeUtil/Resources/cuteface.jpg", "Player 1");
    private Snake theirSnake = new Snake("SnakeUtil/Resources/angry.jpg", "Player 2");
}