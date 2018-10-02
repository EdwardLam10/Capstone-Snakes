import javafx.scene.paint.ImagePattern;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.*;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BLUE;

public class Snake {
    Snake(String HeadPic) {

        //Creating Head Cirlce to be added to Vector
        Rectangle headRectangle = new Rectangle(30,30,45,45);
        headRectangle.setStroke(BLACK);
        Image headPic = new Image(HeadPic);
        headRectangle.setFill(new ImagePattern(headPic));

        //Adding Head Rectangle to Vector
        SnakeVec.add(headRectangle);
    }

    void grow() {
        //Creating a new portion to add.
        Rectangle bodCir = new Rectangle(45,45);
        bodCir.setFill(BLUE);
        bodCir.setStroke(BLACK);
        //Setting X/Y of new body part
        bodCir.setX(SnakeVec.lastElement().getX());
        bodCir.setY(SnakeVec.lastElement().getY() - 30);

        //Adding to SnakeVec
        SnakeVec.addElement(bodCir);
        System.out.println("Here");
    }

    void move(String Direction) {
        double tmpX;
        double tmpY;

        switch(Direction) {
            case "UP":
                //tmp keeping old head location
                tmpX = SnakeVec.firstElement().getX();
                tmpY = SnakeVec.firstElement().getY();

                //Changing the location of the head.
                //Set head Y to the Y of the current Y+10
                SnakeVec.firstElement().setY(SnakeVec.firstElement().getY() - 45);
                lastDir = "UP";

                //updating rest of the snake to follow the head
                update(tmpX, tmpY);
                break;
            case "DOWN":
                tmpX = SnakeVec.firstElement().getX();
                tmpY = SnakeVec.firstElement().getY();

                SnakeVec.firstElement().setY(SnakeVec.firstElement().getY() + 45);
                lastDir = "DOWN";
                update(tmpX, tmpY);
                break;
            case "RIGHT":
                tmpX = SnakeVec.firstElement().getX();
                tmpY = SnakeVec.firstElement().getY();

                SnakeVec.firstElement().setX(SnakeVec.firstElement().getX() + 45);
                lastDir = "RIGHT";
                update(tmpX, tmpY);
                break;
            case "LEFT":
                tmpX = SnakeVec.firstElement().getX();
                tmpY = SnakeVec.firstElement().getY();

                SnakeVec.firstElement().setX(SnakeVec.firstElement().getX() - 45);
                lastDir = "LEFT";
                update(tmpX, tmpY);
                break;
        }
    }

    private void update(double X, double Y) {
        //REALLY CONFUSING????? TOOK LIKE A WHOLE DAYS WTF
        double prevX = X;
        double prevY = Y;
        double tmpX = X;
        double tmpY = Y;
        for(int i = 1; i < SnakeVec.size(); i++) {
            prevX = SnakeVec.elementAt(i).getX();
            prevY = SnakeVec.elementAt(i).getY();
            SnakeVec.elementAt(i).setX(tmpX);
            SnakeVec.elementAt(i).setY(tmpY);
            tmpX = prevX;
            tmpY = prevY;
        }
    }


    Vector<Rectangle> SnakeVec = new Vector();
    String lastDir;
}
