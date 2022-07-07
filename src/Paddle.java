import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//Raketlerin özellikleri ve hareketleri

public class Paddle extends Rectangle{
	//Rectangle sýnýfýný kullanýlabilir hale getirdik.
	
	int id; //Player1 ve Player2 'yi birbrinden ayýran id olacak.
	
	int yVelocity; // Y ekseninde raketlerin hýzlarý.
	int speed = 6;

	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int _id) {
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		this.id = _id;

	}

	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(-speed);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(speed);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(-speed);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(speed);
				move();
			}
			break;
		}

	}

	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_W) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_S) {
				setYDirection(0);
				move();
			}
			break;
		case 2:
			if(e.getKeyCode()==KeyEvent.VK_UP) {
				setYDirection(0);
				move();
			}
			if(e.getKeyCode()==KeyEvent.VK_DOWN) {
				setYDirection(0);
				move();
			}
			break;
		}


	}
	public void setYDirection (int yDirection) {
		//Raketler sadece y ekseninde hareket eder
		yVelocity = yDirection;
		
	}
	public void move () {
		y = y+ yVelocity;
		
	}
	public void draw (Graphics g) {
		//id'lere göre renkleri ayarladýk.
		if(id==1) {
			g.setColor(Color.blue);
		}
		else {
			g.setColor(Color.red);
		}
		g.fillRect(x, y, width, height);//Renkleri þekillerin içine dolduran komut.
	}
	public void paint (Graphics g) {
		
	}
	
}
