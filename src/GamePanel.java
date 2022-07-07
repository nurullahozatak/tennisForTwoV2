import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
	// Runnable interface olarak atand�
	// De�i�kenler

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int) (GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread thread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;

	GamePanel() {
		newPaddles();
		newBall();
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true); // Tu�a bast���m�zda okuyabilmesi i�in.
		this.addKeyListener(new ActionListener());
		this.setPreferredSize(SCREEN_SIZE);

		Thread gameThread = new Thread(this);
		gameThread.start();

	}

	public void newBall() {
		random = new Random();
		ball = new Ball((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), (GAME_HEIGHT / 2) - (BALL_DIAMETER / 2), BALL_DIAMETER,BALL_DIAMETER);  
                                                             //random.nextInt(GAME_HEIGHT-BALL_DIAMETER) eklersek top random bir y ekseninde ba�lar
	}

	public void newPaddles() {
		// Raketler
		paddle1 = new Paddle(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH, PADDLE_HEIGHT, 1);// Gerekli
																											// de�i�kenleri
																											// atad�k.
		paddle2 = new Paddle(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2), PADDLE_WIDTH,
				PADDLE_HEIGHT, 2);
	}

	public void paint(Graphics g) {
		// Panel tasar�m�
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);

	}

	public void draw(Graphics g) {
		// Panel �ekli ve �izimi
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);

	}

	public void move() {
		// Hareketler
		paddle1.move();
		paddle2.move();
		ball.move();
		/*
		 * Bunlar� burda tan�mlad���m�zda hem kontroller birbirinden ba��ms�z oluyor hem
		 * de raket ve top hareketleri daha yuma�ak ve h�zl� oluyor
		 */
	}

	public void checkCollisions() {
		// Topu pencere s�n�r� i�erisinde tutacak kod.
		if (ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if (ball.y >= (GAME_HEIGHT - BALL_DIAMETER)) {
			ball.setYDirection(-ball.yVelocity);
		}
		
		// Topu raketlere �arpt�racak kod.
		if(ball.intersects(paddle1)){
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity += 0.6;
			if(ball.yVelocity>0) {
				ball.yVelocity += 0.6;
			}
			else {
				ball.yVelocity -=0.6;
			}
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		if(ball.intersects(paddle2)){
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity += 0.6;
			if(ball.yVelocity>0) {
				ball.yVelocity += 0.6;
			}
			else {
				ball.yVelocity -=0.6;
			}
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		
		// Raketlerin pencere s�n�r� i�erisinde tutacak kod.
		if (paddle1.y <= 0) {
			paddle1.y = 0;
		}
		if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}
		if (paddle2.y <= 0) {
			paddle2.y = 0;
		}
		if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT)) {
			paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;
		}
		
		// player1 i�in skor say�p raketleri ve topu yeniden ba�altan kod.
		if(ball.x <=0) {
			score.player2++;
			//newPaddles();
			newBall();
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			score.player1++;
			//newPaddles();
			newBall();
		}
		

	}

	public void run() {
		// Game loop
		long lastTime = System.nanoTime();
		/*
		 * Java�da, ge�en zaman�n hesaplanmas� i�in iki temel metot
		 * System.currentTimeMillis() ve System.nanoTime()�d�r.
		 */
		double amountOfTicks = 60.0;
		double nanosecond = 1000000000 / amountOfTicks;
		/*
		 * Ge�en s�reyi milisaniye cinsinden saniye cinsine�eviremk i�in milyarla
		 * b�l�yoruz.
		 */
		double delta = 0;
		while (true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nanosecond;
			lastTime = now;
			if (delta >= 1) {
				move();
				checkCollisions();
				repaint();
				delta--;

			}
		}
	}

	public class ActionListener extends KeyAdapter {
		// ActionListener(AL) ActionEvent'i alg�lar ve gere�ini yapacak metodu
		// �al��t�r�r

		public void keyPressed(KeyEvent e) {
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);

		}

		public void keyReleased(KeyEvent e) {
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);

		}
	}

}