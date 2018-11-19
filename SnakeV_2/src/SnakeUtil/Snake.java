package SnakeUtil;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.geometry.Bounds;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Vector;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;

public class Snake {
    //Class constructor
    //Creates the first node of the snake with the picture passed through the address HeadPic
    public Snake(String HeadPic, String tmpName) {

        //Creating Head Cirlce to be added to Vector
        Rectangle headRectangle = new Rectangle(35,35);
        headRectangle.setStroke(BLACK);
        Image headPic = new Image(HeadPic);
        headRectangle.setFill(new ImagePattern(headPic));

        //Adding Head Rectangle to Vector
        SnakeVec.add(headRectangle);

        Name = tmpName;
    }

    //Moves the head of the snake with a Boolean to tell whether or not the snake grows on this move,
    //Bool true = does grow
    //Bool false = does not grow
    void move(boolean grow) {
        double tmpX;
        double tmpY;

        //Switch statement moves the head of the snake, and calls update function which moves teh rest of
        //the snakes based on location of the head
        switch(CurrentDir) {
            case "UP":
                update(SnakeVec.firstElement().getX(), SnakeVec.firstElement().getY() - 35, grow);
//                System.out.println("UP");

                break;
            case "DOWN":
                update(SnakeVec.firstElement().getX(), SnakeVec.firstElement().getY() + 35, grow);
//                System.out.println("DOWN");

                break;
            case "RIGHT":
                update(SnakeVec.firstElement().getX() + 35, SnakeVec.firstElement().getY(), grow);
//                System.out.println("RIGHT");

                break;
            case "LEFT":
                update(SnakeVec.firstElement().getX() - 35, SnakeVec.firstElement().getY(), grow);
//                System.out.println("LEFT");

                break;
        }
    }

    //Updates the rest of the snake to follow the head, also contains grow to tell whether or not the snake will grow
    //on this move,
    //Bool true = does grow
    //Bool false = does not grow
    //X/Y is the old location of the head
    public void update(double X, double Y, boolean grow) {
        double nextX = X;
        double nextY = Y;
        double tmpX = X;
        double tmpY = Y;
        for(int i = 0; i < SnakeVec.size(); i++) {
            tmpX = SnakeVec.elementAt(i).getX();
            tmpY = SnakeVec.elementAt(i).getY();

            SnakeVec.elementAt(i).setX(nextX);
            SnakeVec.elementAt(i).setY(nextY);

            nextX = tmpX;
            nextY = tmpY;
        }

        if(grow) {
            Rectangle newBod = new Rectangle(35, 35);
            newBod.setX(nextX);
            newBod.setY(nextY);
            newBod.setFill(snakeFill);
            newBod.setStroke(snakeOutline);
            SnakeVec.addElement(newBod);
        }
    }

    //Restart Snake, removes all nodes in the snake except for head node.
    //Sets the head back to X and Y.
    public void restartSnake(double X, double Y, String direction) {
        while(SnakeVec.size() > 1) {
            SnakeVec.removeElementAt(SnakeVec.size()-1);
        }
        SnakeVec.firstElement().setX(X);
        SnakeVec.firstElement().setY(Y);
        CurrentDir = direction;
    }

    //Collision Detection
    public Boolean collisionDetection(Vector<Rectangle> otherSnake) {
        for(int i = 0; i < otherSnake.size(); i++) {
            if(collisioncheck(otherSnake.elementAt(i))) {
                return true;
            }
        }
        return false;
    }

    public Boolean selfCollision() {
        for(int i = 1; i < SnakeVec.size(); i++) {
            if(collisioncheck(SnakeVec.elementAt(i))) {
                return true;
            }
        }
        return false;
    }

    public Boolean borderCollision(double X, double Y) {
        if(SnakeVec.firstElement().getX() < 0 || SnakeVec.firstElement().getX() >= X) {
//            System.out.println(SnakeVec.firstElement().getX());
//            System.out.println(X);
//            System.out.println(1);
            System.out.println(SnakeVec.firstElement().getX());
            System.out.println("X collision");
            return true;
        } else if(SnakeVec.firstElement().getY() < 0 || SnakeVec.firstElement().getY() >= Y) {
//            System.out.println(2);
            System.out.println("Y collision");
            return true;
        } else {
//            System.out.println(3);
//            System.out.println(SnakeVec.firstElement().getX());
            return false;
        }
    }

    public Boolean collisioncheck(Rectangle other) {
        if((SnakeVec.firstElement().getX() == other.getX()) && (SnakeVec.firstElement().getY() == other.getY())) {
            return true;
        } else {
            return false;
        }
    }


    //Regular sets and gets as needed
    public void setCurrentDir(String tmp) { CurrentDir = tmp; }
    public void setHeadLoc(double X, double Y) {
        SnakeVec.firstElement().setX(X);
        SnakeVec.firstElement().setY(Y);
    }
    public Vector<Rectangle> getSnake() { return SnakeVec; }

    public String getSnakeDirection() {
        return CurrentDir;
    }

    public Paint getSnakeFill(java.awt.Color yellow) {
        return snakeFill;
    }
    public void setSnakeFill(Paint snakeFill) {
        this.snakeFill = snakeFill;
    }

    public Paint getSnakeoutline() {
        return snakeOutline;
    }
    public void setSnakeoutline(Paint snakeoutline) {
        this.snakeOutline = snakeoutline;
    }

    public String getName() { return Name; }

    //Vector of Rectangles to represent the snake itself
    private Vector<Rectangle> SnakeVec = new Vector();
    private String CurrentDir = "UP";
    private Paint snakeOutline = BLACK;
    private Paint snakeFill = RED;
    private String Name;
}
