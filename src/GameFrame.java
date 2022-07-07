import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	
	GamePanel gamePanel = new GamePanel();

	GameFrame() {
		this.add(gamePanel); //GamePanel GameFrame i�ine eklendi.
		this.setTitle("Tennis For Two V2.0");
		this.setAutoRequestFocus(false); //GameFrame yeniden boyutalnd�r�lamaz.
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack(); //Bu Pencerenin, alt bile�enlerinin tercih edilen boyutlar�na ve yerle�imlerine uyacak �ekilde boyutland�r�lmas�n� sa�lar.
		this.setVisible(true);

	}

}
