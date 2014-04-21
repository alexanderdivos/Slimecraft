package me.mrCookieSlime.Slimecraft.Dialogs;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import me.mrCookieSlime.Slimecraft.Minecraft.Minecraft;
import me.mrCookieSlime.Slimecraft.Utils.SoundHandler;

public class Notifications {
	
	public static JFrame current;
	
	public static void send(String message) throws IOException {
		String header = "Slimecraft Notifications";
		final JFrame frame = new JFrame();
		frame.setSize(300,125);
		frame.setUndecorated(true);
		frame.setLayout(new GridBagLayout());
		Minecraft.setupIcon(frame);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.BOTH;
		JLabel headingLabel = new JLabel(header);
		ImageIcon headingIcon = new ImageIcon(ImageIO.read(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\icon.png")));
		headingLabel.setIcon(headingIcon);
		headingLabel.setOpaque(false);
		frame.add(headingLabel, constraints);
		constraints.gridx++;
		constraints.weightx = 0f;
		constraints.weighty = 0f;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTH;
		JButton closeButton = new JButton(new AbstractAction("x") {
			private static final long serialVersionUID = 1L;

			@Override
	        public void actionPerformed(final ActionEvent e) {
	               frame.dispose();
	        }
		});
		closeButton.setMargin(new Insets(1, 4, 1, 4));
		closeButton.setFocusable(false);
		frame.add(closeButton, constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.BOTH;
		JLabel messageLabel = new JLabel("<HtMl>"+message);
		frame.add(messageLabel, constraints);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen
		Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());// height of the task bar
		frame.setLocation(scrSize.width - frame.getWidth(), scrSize.height - toolHeight.bottom - frame.getHeight());
		frame.setAlwaysOnTop(true);
		
		current = frame;
		
		new Thread(){
		      public void run() {
		           try {
		        	   Thread.sleep(5000); // time after which pop up will be disappeared.
		               frame.dispose();
		               current = null;
		           } catch (InterruptedException e) {
		           }
		      };
		}.start();
		
		SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\Notification.wav");
		
	}

}
