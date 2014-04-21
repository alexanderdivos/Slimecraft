package me.mrCookieSlime.Slimecraft.Minecraft;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import me.mrCookieSlime.Slimecraft.Background;
import me.mrCookieSlime.Slimecraft.frame;
import me.mrCookieSlime.Slimecraft.Utils.SoundHandler;

@SuppressWarnings("serial")
public class ChangeLog extends JFrame implements ActionListener {
	
	static Point mouseDownCompCoords;
	
	public static ChangeLog main;
	
	private JEditorPane news;
	private JScrollPane scroll;
	
	private JButton exit;
	
	public static void main(String[] args) throws MalformedURLException, IOException {
		final JFrame frame = new ChangeLog("Changelog");
		  frame.setDefaultCloseOperation(2);
		  frame.setSize(800, 600);
		  frame.setLocation(me.mrCookieSlime.Slimecraft.frame.main.getLocation());
		  frame.setResizable(false);
		  frame.add(Background.current);
		  Minecraft.setupIcon(frame);

		  frame.setUndecorated(true);
		  frame.addMouseListener(new MouseListener() {
		    public void mouseReleased(MouseEvent e) {
		      mouseDownCompCoords = null;
		    }
		    public void mousePressed(MouseEvent e) {
		      mouseDownCompCoords = e.getPoint();
		    }

		    public void mouseExited(MouseEvent e)
		    {
		    }

		    public void mouseEntered(MouseEvent e)
		    {
		    }

		    public void mouseClicked(MouseEvent e)
		    {
		    }
		  });
		  frame.addMouseMotionListener(new MouseMotionListener() {
		    public void mouseMoved(MouseEvent e) {
		    }

		    public void mouseDragged(MouseEvent e) {
		      Point currCoords = e.getLocationOnScreen();
		      try {
		        frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
		      }
		      catch (NullPointerException localNullPointerException)
		      {
		      }
		    }
		  });
		  
		  frame.setVisible(true);

		  main = (ChangeLog) frame;

		  System.out.println("Opening Window \"Changelog\"");
	}
	
	public ChangeLog(String title) throws MalformedURLException, IOException {
		
		  	exit = new JButton();
			exit.setIcon(new ImageIcon(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\back.png"));
			exit.setBounds(730, 12, 50, 20);
			exit.setCursor(new Cursor(12));
			exit.addActionListener(this);
			add(exit);
			
			news = new JEditorPane(new URL("http://mcupdate.tumblr.com/"));
			  news.setEditable(false);
			  scroll = new JScrollPane(news, 20, 30);
			  scroll.setBounds(40, 40, 720, 520);
			  add(scroll);
		  
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
		    SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");

		    main.setVisible(false);
		    frame.main.setLocation(main.getLocation());
		    frame.main.setVisible(true);
		    frame.main.add(Background.current);
		  }
	}

}
