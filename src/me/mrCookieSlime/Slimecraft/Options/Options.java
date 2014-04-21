package me.mrCookieSlime.Slimecraft.Options;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import me.mrCookieSlime.Slimecraft.Background;
import me.mrCookieSlime.Slimecraft.frame;
import me.mrCookieSlime.Slimecraft.Filters.FolderFilter;
import me.mrCookieSlime.Slimecraft.Minecraft.Minecraft;
import me.mrCookieSlime.Slimecraft.Utils.SoundHandler;

@SuppressWarnings("serial")
public class Options extends JFrame

implements ActionListener {
	
static Point mouseDownCompCoords;

public static String OPTIONS;
public static String minecraftpath;
public static String width1;
public static String height1;
public static String jarpath;

public static JTextField minecraft_path_desc;
public static JTextField minecraft_path;
public static JButton minecraft_path_files;

public static JTextField XMS_desc;
public static JTextField XMS;

public static JTextField XMX_desc;
public static JTextField XMX;

public static JTextField width_desc;
public static JTextField width;
public static JTextField height_desc;
public static JTextField height;

public static JTextField console_desc;
public static JCheckBox console;
public static String console_name;

public static JTextField fullscreen_desc;
public static JCheckBox fullscreen;
public static String fullscreen_name;

public static JTextField save_data_desc;
public static JCheckBox save_data;
public static String save_data_name;

public static Options main;

private JButton exit;

public static void main(String[] args) throws IOException {
  final JFrame frame = new Options(OPTIONS);
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

  main = (Options)frame;

  System.out.println("Opening Window \"" + OPTIONS + "\"");
}

public Options(String title) throws MalformedURLException, IOException {
  super(title);
  
  exit = new JButton();
	exit.setIcon(new ImageIcon(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\back.png"));
	exit.setBounds(730, 12, 50, 20);
	exit.setCursor(new Cursor(12));
	exit.addActionListener(this);
	add(exit);

  width_desc = new JTextField(20);
  width_desc.setBounds(50, 120, 120, 22);
  width_desc.setEditable(false);
  width_desc.setBackground(Color.LIGHT_GRAY);
  width_desc.setText(width1);
  add(width_desc);

  width = new JTextField(20);
  width.setBounds(50, 140, 120, 22);
  width.setEditable(true);
  add(width);

  height_desc = new JTextField(20);
  height_desc.setBounds(190, 120, 120, 22);
  height_desc.setEditable(false);
  height_desc.setBackground(Color.LIGHT_GRAY);
  height_desc.setText(height1);
  add(height_desc);

  height = new JTextField(20);
  height.setBounds(190, 140, 120, 22);
  height.setEditable(true);
  add(height);

  XMS_desc = new JTextField(20);
  XMS_desc.setBounds(50, 70, 120, 22);
  XMS_desc.setEditable(false);
  XMS_desc.setBackground(Color.LIGHT_GRAY);
  XMS_desc.setText("   Minimum RAM");
  add(XMS_desc);

  XMS = new JTextField(20);
  XMS.setBounds(50, 90, 120, 22);
  XMS.setEditable(true);
  add(XMS);

  XMX_desc = new JTextField(20);
  XMX_desc.setBounds(190, 70, 120, 22);
  XMX_desc.setEditable(false);
  XMX_desc.setBackground(Color.LIGHT_GRAY);
  XMX_desc.setText("  Maximal RAM");
  add(XMX_desc);

  XMX = new JTextField(20);
  XMX.setBounds(190, 90, 120, 22);
  XMX.setEditable(true);
  add(XMX);
  
  minecraft_path_desc = new JTextField(20);
  minecraft_path_desc.setBounds(50, 20, 260, 22);
  minecraft_path_desc.setEditable(false);
  minecraft_path_desc.setBackground(Color.LIGHT_GRAY);
  minecraft_path_desc.setText(minecraftpath);
  add(minecraft_path_desc);

  minecraft_path_files = new JButton("...");
  minecraft_path_files.setBounds(320, 40, 40, 22);
  minecraft_path_files.setCursor(new Cursor(12));
  minecraft_path_files.addActionListener(this);
  add(minecraft_path_files);

  minecraft_path = new JTextField(20);
  minecraft_path.setBounds(50, 40, 260, 22);
  minecraft_path.setEditable(false);
  add(minecraft_path);

  console = new JCheckBox();
  console.setBounds(50, 170, 21, 21);
  add(console);

  console_desc = new JTextField(20);
  console_desc.setText(console_name);
  console_desc.setEditable(false);
  console_desc.setBounds(75, 170, 235, 21);
  add(console_desc);

  fullscreen = new JCheckBox();
  fullscreen.setBounds(50, 200, 21, 21);
  add(fullscreen);

  fullscreen_desc = new JTextField(20);
  fullscreen_desc.setText(fullscreen_name);
  fullscreen_desc.setEditable(false);
  fullscreen_desc.setBounds(75, 200, 235, 21);
  add(fullscreen_desc);

  save_data = new JCheckBox();
  save_data.setBounds(50, 230, 21, 21);
  add(save_data);

  save_data_desc = new JTextField(20);
  save_data_desc.setText(save_data_name);
  save_data_desc.setEditable(false);
  save_data_desc.setBounds(75, 230, 235, 21);
  add(save_data_desc);

  minecraft_path.setText(frame.properties.getProperty("minecraft-path"));
  XMS.setText(frame.properties.getProperty("XMS"));
  XMX.setText(frame.properties.getProperty("XMX"));
  width.setText(frame.properties.getProperty("width"));
  height.setText(frame.properties.getProperty("height"));
  try
  {
    if (frame.properties.getProperty("show-console").equalsIgnoreCase("true")) {
      console.setSelected(true);
    }
    if (frame.properties.getProperty("fullscreen").equalsIgnoreCase("true")) {
      fullscreen.setSelected(true);
    }
    if (frame.properties.getProperty("save_data").equalsIgnoreCase("true")) {
      save_data.setSelected(true);
    }
  }
  catch (Exception localException1)
  {
  }
}

public void actionPerformed(ActionEvent e)
{
  if (e.getSource() == exit)
  {
    SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
    try
    {
      Properties props = frame.properties;

      props.setProperty("minecraft-path", minecraft_path.getText());
      props.setProperty("XMS", XMS.getText());
      props.setProperty("XMX", XMX.getText());
      props.setProperty("width", width.getText());
      props.setProperty("height", height.getText());

      if (console.isSelected()) {
        props.setProperty("show-console", "true");
      }
      else {
        props.setProperty("show-console", "false");
      }

      if (fullscreen.isSelected()) {
        props.setProperty("fullscreen", "true");
      }
      else {
        props.setProperty("fullscreen", "false");
      }

      if (save_data.isSelected()) {
        props.setProperty("save_data", "true");
      }
      else {
        props.setProperty("save_data", "false");
      }

      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(System.getenv("APPDATA") + "\\.Slimecraft\\Slimecraft.properties"));
      frame.properties.store(out, "");
      out.close();

      main.setVisible(false);
      frame.main.setLocation(main.getLocation());
      frame.main.setVisible(true);
      frame.main.add(Background.current);
    }
    catch (Exception localException)
    {
    }
  }
  else if (e.getSource() == minecraft_path_files)
  {
    SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");

    JFileChooser chooser = null;
    LookAndFeel previousLF = UIManager.getLookAndFeel();
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File(System.getenv("APPDATA")));
      chooser.setFileSelectionMode(1);
      chooser.setFileFilter(new FolderFilter());
      chooser.showOpenDialog(null);
      UIManager.setLookAndFeel(previousLF);
    } catch (Exception localException1) {
    }
    try {
      File jar = chooser.getSelectedFile();
      minecraft_path.setText(jar.getPath());
    }
    catch (NullPointerException localNullPointerException) {
    }
  }
}
}