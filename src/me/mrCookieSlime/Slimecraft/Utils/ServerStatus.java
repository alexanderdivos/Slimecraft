package me.mrCookieSlime.Slimecraft.Utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import me.mrCookieSlime.Slimecraft.frame;
import me.mrCookieSlime.Slimecraft.Messages.Tooltip;

public class ServerStatus {
	
	public static BufferedImage image;
	
	public static JPanel panel;

	public static JLabel label;

	public static ImageIcon icon;
	
	public static File status;
	
	public static String info;
	
	public static boolean ping(String url, int timeout)
	  {
	    url = url.replaceFirst("https", "http");
	    try
	    {
	      HttpURLConnection connection = (HttpURLConnection)new URL(url).openConnection();
	      connection.setConnectTimeout(timeout);
	      connection.setReadTimeout(timeout);
	      connection.setRequestMethod("HEAD");
	      int responseCode = connection.getResponseCode();
	      return (200 <= responseCode) && (responseCode <= 399); } catch (IOException exception) {
	    }
	    return false;
	  }
	
	public static void get(frame frame) throws IOException {
		
		if (ping("https://login.minecraft.net/", 10000)) {
			status = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\server_on.png");
			System.out.println("Pinged Minecraft Server, it's on");
			info = Tooltip.SERVER_STATUS_ON;
		}
		else {
			status = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\server_off.png");
			System.out.println("Could not reach Minecraft Server, it may be down");
			info = Tooltip.SERVER_STATUS_OFF;
		}
		
		try {
			image = ImageIO.read(status);
			Graphics g = image.getGraphics();
	        g.setColor(Color.BLACK);
	        g.fillRect(0, 190, 450, 50);
	        g.setFont(new Font("Sans", Font.PLAIN, 40));
	        g.setColor(Color.WHITE);
	        
	        icon = new ImageIcon(image);
	        label = new JLabel();
	        label.setBorder(new LineBorder(Color.WHITE));
	        label.setIcon(icon);
	        label.revalidate();
	        label.repaint();
	        
	        panel = new JPanel();
	        panel.setLayout(new BorderLayout());
	        panel.setBackground(Color.DARK_GRAY);
	        panel.add(label);
	        panel.setBounds(620, 12, 50, 20);
	        
	        panel.setToolTipText(info);
	        
	        frame.add(panel);
	        
		} catch (IOException e) {
		}
	}
}
