package me.mrCookieSlime.Slimecraft;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Background extends JPanel {
	
	ImageIcon bg;
	int posX;
	int posY;
	
	public static JPanel current;
	
	public static File folder = new File(System.getenv("APPDATA") + "\\.minecraft\\screenshots");
	
	public Background() throws IOException {
		
			bg = new ImageIcon(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\Background.png");
			
			posX = 0;
			posY = 0;
			
			current = this;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D d = (Graphics2D) g;
		d.drawImage(bg.getImage(), posX, posY, null);
	}

}
