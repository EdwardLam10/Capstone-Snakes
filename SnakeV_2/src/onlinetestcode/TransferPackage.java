package onlinetestcode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.awt.Point;
public class TransferPackage implements Serializable {
        private Point P1nextpos;

	private Point P2nextpos;

	private Point Applepos;

	private boolean aNewApple;

	private boolean gameEnd;

	private boolean Thewinner; //true = player 1 wins, false = player 2 wins. by default is false but should only be checked if game over is true



	public TransferPackage(int p1x, int p1y, int p2x, int p2y){

	P1nextpos = new Point(p1x,p1y);

	P2nextpos = new Point(p2x,p2y);

	aNewApple = false;

	Applepos = null;

	gameEnd = false;

	Thewinner = false;

	}



	public TransferPackage(int p1x, int p1y, int p2x, int p2y, int Ax, int Ay, boolean newapple, boolean gameover, boolean winner){

	P1nextpos = new Point(p1x,p1y);

	P2nextpos = new Point(p2x,p2y);

	aNewApple = newapple;

	Applepos = new Point(Ax,Ay);

	gameEnd = false;

	Thewinner = false;

	}



	public TransferPackage( boolean winner){

	P1nextpos = null;

	P2nextpos = null;

	Applepos = null;

	aNewApple = false;

	gameEnd = true;

	Thewinner = winner;

	}

	

	public int getP1X(){

		return (int)P1nextpos.getX();

	}

	public int getP1Y(){

		return (int)P1nextpos.getY();

	}

	public int getP2X(){

		return (int)P2nextpos.getX();

	}

	public int getP2Y(){

		return (int)P2nextpos.getY();

	}

	public boolean isGameOver(){

		return gameEnd;

	}

	public boolean whoWon(){	// only use if isGameOver returns true

		return Thewinner;

	}

	public boolean isNewApple(){

		return aNewApple;

	}

	public int getAppleX(){

		return (int)Applepos.getX();

	}

	public int getAppleY(){

		return (int)Applepos.getY();

	}
}
