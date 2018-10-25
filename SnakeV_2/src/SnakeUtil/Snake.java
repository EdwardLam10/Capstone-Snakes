package SnakeUtil;

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
    public Snake(String HeadPic) {

        //Creating Head Cirlce to be added to Vector
        Rectangle headRectangle = new Rectangle(45,45);
        headRectangle.setStroke(BLACK);
        Image headPic = new Image(HeadPic);
        headRectangle.setFill(new ImagePattern(headPic));

        //Adding Head Rectangle to Vector
        SnakeVec.add(headRectangle);
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
                //tmp keeping old head location
                tmpX = SnakeVec.firstElement().getX(); //LINE NOT NEEDED ONCE NET CODE IS IMPLEMENTED
                tmpY = SnakeVec.firstElement().getY(); //LINE NOT NEEDED ONCE NET CODE IS IMPLEMENTED

                //Changing the location of the head.
                SnakeVec.firstElement().setY(SnakeVec.firstElement().getY() - 45); //LINE NOT NEEDED ONCE NET CODE IS IMPLEMENTED

                /*
                 Net code can be implemented here to change the location of the head based on the direction.
                 */

                //updating rest of the snake to follow the head
                update(tmpX, tmpY, grow);
                System.out.println("UP");
                break;
            case "DOWN":
                tmpX = SnakeVec.firstElement().getX(); //LINE NOT NEEDED ONCE NET CODE IS IMPLEMENTED
                tmpY = SnakeVec.firstElement().getY(); //LINE NOT NEEDED ONCE NET CODE IS IMPLEMENTED

                SnakeVec.firstElement().setY(SnakeVec.firstElement().getY() + 45); //LINE NOT NEEDED ONCE NET CODE IS IMPLEMENTED

                /*
                 Net code can be implemented here to change the location of the head based on the direction.
                 */

                update(tmpX, tmpY, grow);
                System.out.println("DOWN");
                break;
            case "RIGHT":
                tmpX = SnakeVec.firstElement().getX(); //LINE NOT NEEDED ONCE NET CODE IS IMPLEMENTED
                tmpY = SnakeVec.firstElement().getY(); //LINE NOT NEEDED ONCE NET CODE IS IMPLEMENTED

                SnakeVec.firstElement().setX(SnakeVec.firstElement().getX() + 45); //LINE NOT NEEDED ONCE NET CODE IS IMPLEMENTED

                /*
                 Net code can be implemented here to change the location of the head based on the direction.
                 */

                update(tmpX, tmpY, grow);
                System.out.println("RIGHT");
                break;
            case "LEFT":
                tmpX = SnakeVec.firstElement().getX(); //LINE NOT NEEDED ONCE NET CODE IS IMPLEMENTED
                tmpY = SnakeVec.firstElement().getY(); //LINE NOT NEEDED ONCE NET CODE IS IMPLEMENTED

                SnakeVec.firstElement().setX(SnakeVec.firstElement().getX() - 45); //LINE NOT NEEDED ONCE NET CODE IS IMPLEMENTED

                /*
                 Net code can be implemented here to change the location of the head based on the direction.
                 */

                update(tmpX, tmpY, grow);
                System.out.println("LEFT");
                break;
        }
    }

    //Updates the rest of the snake to follow the head, also contains grow to tell whether or not the snake will grow
    //on this move,
    //Bool true = does grow
    //Bool false = does not grow
    //X/Y is the old location of the head
    private void update(double X, double Y, boolean grow) {
        //REALLY CONFUSING????? TOOK LIKE A WHOLE DAYS WTF
        double prevX = X;
        double prevY = Y;
        double tmpX = X;
        double tmpY = Y;
        for (int i = 1; i < SnakeVec.size(); i++) {
            prevX = SnakeVec.elementAt(i).getX();
            prevY = SnakeVec.elementAt(i).getY();
            SnakeVec.elementAt(i).setX(tmpX);
            SnakeVec.elementAt(i).setY(tmpY);
            tmpX = prevX;
            tmpY = prevY;
        }
        if(grow) {
            Rectangle BodRect = new Rectangle(45,45);
            BodRect.setStroke(snakeOutline);
            BodRect.setFill(snakeFill);
            BodRect.setX(tmpX);
            BodRect.setY(tmpY);
            SnakeVec.addElement(BodRect);
        }
    }

    //Restart Snake, removes all nodes in the snake except for head node.
    //Sets the head back to X and Y.
    public void restartSnake(double X, double Y) {
        while(SnakeVec.size() > 1) {
            SnakeVec.removeElementAt(SnakeVec.size()-1);
        }
        SnakeVec.firstElement().setX(X);
        SnakeVec.firstElement().setY(Y);
        CurrentDir = "UP";
    }

    //Collision Detection
    public Boolean collisionDetection(Vector<Rectangle> otherSnake) {
        for(int i = 0; i < otherSnake.size(); i++) {
            if(SnakeVec.firstElement().intersects(otherSnake.elementAt(i).getBoundsInLocal())){
                return true;
            }
        }
        return false;
    }

    //Regular sets and gets as needed
    public void setCurrentDir(String tmp) { CurrentDir = tmp; }
    public void setHeadLoc(double X, double Y) {
        SnakeVec.firstElement().setX(X);
        SnakeVec.firstElement().setY(Y);
    }
    public Vector<Rectangle> getSnake() { return SnakeVec; }

    public Paint getSnakeFill() {
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

    //Vector of Rectangles to represent the snake itself
    private Vector<Rectangle> SnakeVec = new Vector();
    private String CurrentDir = "UP";
    private Paint snakeOutline = BLACK;
    private Paint snakeFill = RED;

}
