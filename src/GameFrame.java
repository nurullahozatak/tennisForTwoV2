import java.awt.*;
import java.awt.Color.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameFrame extends JFrame {
	
	GamePanel gamePanel = new GamePanel();

	GameFrame() {
		this.add(gamePanel); //GamePanel GameFrame içine eklendi.
		this.setTitle("Tennis For Two V2.0");
		this.setAutoRequestFocus(false); //GameFrame yeniden boyutalndýrýlamaz.
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack(); //Bu Pencerenin, alt bileþenlerinin tercih edilen boyutlarýna ve yerleþimlerine uyacak þekilde boyutlandýrýlmasýný saðlar.
		this.setVisible(true);

	}

}
