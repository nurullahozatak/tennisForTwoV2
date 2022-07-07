import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ball extends Rectangle{
	//Rectangle sýnýfýný kullanýlabilir hale getirdik.
	
	Random random;
	
	double xVelocity; // x ekseninde topun hýzý.
	
	double yVelocity; // x ekseninde topun hýzý.
	double speed = 3;

	
	
	Ball(int x, int y, int width, int height) {
		super(x,y,width,height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0) {
			randomXDirection--;
		}
		setXDirection(randomXDirection*speed);
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0) {
			randomYDirection--;
		}
		setYDirection(randomYDirection*speed);

	}

	public void setXDirection(double randomXDirection) {
		// Topun x ekseninde hareketi
		xVelocity = randomXDirection;

	}

	public void setYDirection(double randomYDirection) {
		// Topun y ekseninde hareketi
		yVelocity = randomYDirection;

	}

	public void move() {
		x+= xVelocity;
		y+= yVelocity;

	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x,y,width,height);

	}

	public void paint(Graphics g) {

	}

}
