package me.mrCookieSlime.Slimecraft.Utils;

import java.awt.BorderLayout;
import java.io.PrintStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Console {
	
	public static JFrame window;
	
	public static void main( String [] args ) throws InterruptedException  {
        JFrame frame = new JFrame("Console");
        frame.add( new JLabel(" Output" ), BorderLayout.NORTH );

        JTextArea ta = new JTextArea();
        ta.setEditable(false);
        TextAreaOutputStream taos = new TextAreaOutputStream( ta );
        PrintStream ps = new PrintStream( taos );
        System.setOut( ps );
        System.setErr( ps );


        frame.add( new JScrollPane( ta )  );

        frame.pack();
        frame.setSize(800, 600);
        frame.setLocation(50, 50);
        frame.setIconImage(new ImageIcon(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\icon.png").getImage());
        frame.setVisible( true );
        window = frame;
    }

}
