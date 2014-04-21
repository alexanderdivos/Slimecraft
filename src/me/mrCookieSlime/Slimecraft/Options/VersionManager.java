package me.mrCookieSlime.Slimecraft.Options;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import me.mrCookieSlime.Slimecraft.Background;
import me.mrCookieSlime.Slimecraft.frame;
import me.mrCookieSlime.Slimecraft.Dialogs.Notifications;
import me.mrCookieSlime.Slimecraft.Dialogs.Warning;
import me.mrCookieSlime.Slimecraft.Filters.ModFilter;
import me.mrCookieSlime.Slimecraft.Messages.ButtonText;
import me.mrCookieSlime.Slimecraft.Messages.Error;
import me.mrCookieSlime.Slimecraft.Minecraft.Minecraft;
import me.mrCookieSlime.Slimecraft.Minecraft.Versions;
import me.mrCookieSlime.Slimecraft.Utils.SoundHandler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@SuppressWarnings("serial")
public class VersionManager extends JFrame implements ActionListener {
	
	static Point mouseDownCompCoords;
	
	public static String not_installed;
	public static String install_name;

	public static JTextField uninstalled_desc;
	public static Choice uninstalled;

	public static JButton install;
	
	public static JTextField mods_desc;
	public static Choice installed_mods;
	public static Choice disabled_mods;
	public static JButton mods_enable;
	public static JButton mods_add;
	public static JButton mods_disable;
	
	public static Choice modpacks;
	public static JButton modpack_new;
	public static JButton modpack_use;
	public static JButton modpack_delete;
	public static JTextField modpack_name;
	
	public static VersionManager main;
	
	public static File modlist = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Mods");
	public static File modlist_installed = new File(System.getenv("APPDATA") + "\\.minecraft\\mods");
	public static File modpacklist = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Modpacks");
	
	private JButton exit;
	
	public static void main(String[] args) throws IOException {
		final JFrame frame = new VersionManager("Version Manager");
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

		  main = (VersionManager)frame;

		  System.out.println("Opening Window \"Version Manager\"");
	}
	
	public VersionManager(String title) throws IOException {
		uninstalled_desc = new JTextField(20);
		  uninstalled_desc.setBounds(50, 20, 260, 22);
		  uninstalled_desc.setEditable(false);
		  uninstalled_desc.setBackground(Color.LIGHT_GRAY);
		  uninstalled_desc.setText(not_installed);
		  add(uninstalled_desc);

		  uninstalled = new Choice();
		  uninstalled.setBounds(50, 40, 260, 22);
		  for (int i = 0; i < Versions.getMissingVersions().size(); i++) {
		    uninstalled.addItem(Versions.getMissingVersions().get(i));
		  }
		  add(uninstalled);
		  
		  install = new JButton();
		  install.setBounds(150, 70, 60, 22);
		  install.setIcon(new ImageIcon(ImageIO.read(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\add.png"))));
		  install.addActionListener(this);
		  install.setCursor(new Cursor(12));
		  add(install);
		  
		  mods_desc = new JTextField(20);
		  mods_desc.setBounds(50, 110, 260, 22);
		  mods_desc.setEditable(false);
		  mods_desc.setBackground(Color.LIGHT_GRAY);
		  mods_desc.setText(ButtonText.MOD_REMOVE_DESCRIPTION);
		  add(mods_desc);
		  
		  installed_mods = new Choice();
		  installed_mods.setBounds(50, 130, 260, 22);
		  for (int i = 0; i < modlist_installed.listFiles().length; i++) {
			  if (modlist_installed.listFiles()[i].getName().contains(".jar") || modlist_installed.listFiles()[i].getName().contains(".zip")) {
				  installed_mods.addItem(modlist_installed.listFiles()[i].getName().replace(".zip", "").replace(".jar", ""));
			  }
		  }
		  add(installed_mods);
		  
		  mods_desc = new JTextField(20);
		  mods_desc.setBounds(50, 190, 260, 22);
		  mods_desc.setEditable(false);
		  mods_desc.setBackground(Color.LIGHT_GRAY);
		  mods_desc.setText(ButtonText.MOD_ADD_DESCRIPTION);
		  add(mods_desc);
		  
		  disabled_mods = new Choice();
		  disabled_mods.setBounds(50, 210, 260, 22);
		  for (int i = 0; i < modlist.listFiles().length; i++) {
			  if (modlist.listFiles()[i].getName().contains(".jar") || modlist.listFiles()[i].getName().contains(".zip")) {
				  disabled_mods.addItem(modlist.listFiles()[i].getName().replace(".zip", "").replace(".jar", ""));
			  }
		  }
		  add(disabled_mods);
		  
		  mods_add = new JButton();
		  mods_add.setBounds(70, 160, 60, 22);
		  mods_add.setIcon(new ImageIcon(ImageIO.read(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\add.png"))));
		  mods_add.addActionListener(this);
		  mods_add.setCursor(new Cursor(12));
		  add(mods_add);
		  
		  mods_enable = new JButton();
		  mods_enable.setBounds(150, 160, 60, 22);
		  mods_enable.setIcon(new ImageIcon(ImageIO.read(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\enable.png"))));
		  mods_enable.addActionListener(this);
		  mods_enable.setCursor(new Cursor(12));
		  add(mods_enable);
		  
		  mods_disable = new JButton();
		  mods_disable.setBounds(230, 160, 60, 22);
		  mods_disable.setIcon(new ImageIcon(ImageIO.read(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\disable.png"))));
		  mods_disable.addActionListener(this);
		  mods_disable.setCursor(new Cursor(12));
		  add(mods_disable);
		  
		  exit = new JButton();
		  exit.setIcon(new ImageIcon(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\back.png"));
		  exit.setBounds(730, 12, 50, 20);
		  exit.setCursor(new Cursor(12));
		  exit.addActionListener(this);
		  add(exit);
		  
//		  mods_desc = new JTextField(20);
//		  mods_desc.setBounds(50, 240, 260, 22);
//		  mods_desc.setEditable(false);
//		  mods_desc.setBackground(Color.LIGHT_GRAY);
//		  mods_desc.setText(ButtonText.MODPACK_DESCRIPTION);
//		  add(mods_desc);
//		  
//		  modpacks = new Choice();
//		  modpacks.setBounds(50, 260, 260, 22);
//		  for (int i = 0; i < modpacklist.listFiles().length; i++) {
//			  if (modpacklist.listFiles()[i].getName().contains(".json")) {
//				  modpacks.addItem(modpacklist.listFiles()[i].getName().replace(".json", ""));
//			  }
//		  }
//		  add(modpacks);
//		  
//		  modpack_new = new JButton();
//		  modpack_new.setBounds(70, 290, 60, 22);
//		  modpack_new.setIcon(new ImageIcon(ImageIO.read(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\add.png"))));
//		  modpack_new.addActionListener(this);
//		  modpack_new.setCursor(new Cursor(12));
//		  add(modpack_new);
//		  
//		  modpack_delete = new JButton();
//		  modpack_delete.setBounds(150, 290, 60, 22);
//		  modpack_delete.setIcon(new ImageIcon(ImageIO.read(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\delete.png"))));
//		  modpack_delete.addActionListener(this);
//		  modpack_delete.setCursor(new Cursor(12));
//		  add(modpack_delete);
//		  
//		  modpack_use = new JButton();
//		  modpack_use.setBounds(230, 290, 60, 22);
//		  modpack_use.setIcon(new ImageIcon(ImageIO.read(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\enable.png"))));
//		  modpack_use.addActionListener(this);
//		  modpack_use.setCursor(new Cursor(12));
//		  add(modpack_use);
//		  
//		  modpack_name = new JTextField(50);
//		  modpack_name.setBounds(50, 320, 260, 22);
//		  modpack_name.setText(ButtonText.MODPACK_NAME);
//		  add(modpack_name);
		  
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == install) {
		    SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");

		    Versions.installVersion(uninstalled.getSelectedItem());
		    main.setVisible(false);
		    frame.main.setLocation(main.getLocation());
		    frame.main.setVisible(true);
		    frame.main.add(Background.current);
		  }
		else if (e.getSource() == exit) {
		    SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");

		    main.setVisible(false);
		    frame.main.setLocation(main.getLocation());
		    frame.main.setVisible(true);
		    frame.main.add(Background.current);
		  }
		else if (e.getSource() == mods_add) {
		    SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
		    
		    JFileChooser chooser = null;
		    LookAndFeel previousLF = UIManager.getLookAndFeel();
		    try {
		      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		      chooser = new JFileChooser();
		      chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
		      chooser.setFileFilter(new ModFilter());
		      chooser.showOpenDialog(null);
		      UIManager.setLookAndFeel(previousLF);
		    } catch (Exception localException1) {
		    }
		    try {
		      File jar = chooser.getSelectedFile();
		      if (jar.getName().endsWith(".zip") || jar.getName().endsWith(".jar"));
		      disabled_mods.addItem(jar.getName().replace(".jar", "").replace(".zip", ""));
		      jar.renameTo(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Mods\\" + jar.getName()));
		    }
		    catch (NullPointerException localNullPointerException) {
		    }
		  }
		else if (e.getSource() == mods_enable) {
			SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			try {
				String current = disabled_mods.getSelectedItem();
				if (!current.equalsIgnoreCase("") && current != null) {
					File file = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Mods\\" + current + ".jar");
					if (file.exists()) {
						file.renameTo(new File(System.getenv("APPDATA") + "\\.minecraft\\mods\\" + current + ".jar"));
						System.out.println("File moved successfully to " + System.getenv("APPDATA") + "\\.minecraft\\mods\\" + current + ".jar");
					}
					else {
						File file2 = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Mods\\" + current + ".zip"); 
						if (file2.exists()) {
							file2.renameTo(new File(System.getenv("APPDATA") + "\\.minecraft\\mods\\" + current + ".zip"));
							System.out.println("File moved successfully to " + System.getenv("APPDATA") + "\\.minecraft\\mods\\" + current + ".zip");
						}
					}
					installed_mods.addItem(current);
					disabled_mods.remove(current);
				}
			}
			catch(Exception x) {
				System.out.println(x.getMessage());
			}
		}
		else if (e.getSource() == mods_disable) {
			SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			try {
				String current = installed_mods.getSelectedItem();
				if (!current.equalsIgnoreCase("") && current != null) {
					File file = new File(System.getenv("APPDATA") + "\\.minecraft\\mods\\" + current + ".jar");
					if (file.exists()) {
						file.renameTo(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Mods\\" + current + ".jar"));
						System.out.println("File moved successfully to " + System.getenv("APPDATA") + "\\.Slimecraft\\Mods\\" + current + ".jar");
					}
					else {
						File file2 = new File(System.getenv("APPDATA") + "\\.minecraft\\mods\\" + current + ".zip"); 
						if (file2.exists()) {
							file2.renameTo(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Mods\\" + current + ".zip"));
							System.out.println("File moved successfully to " + System.getenv("APPDATA") + "\\.Slimecraft\\Mods\\" + current + ".zip");
						}
					}
					disabled_mods.addItem(current);
					installed_mods.remove(current);
				}
			}
			catch(Exception x) {
				System.out.println(x.getMessage());
			}
		}
		else if (e.getSource() == modpack_new) {
			SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			
			String name = modpack_name.getText();
			
			if (!name.equalsIgnoreCase(ButtonText.MODPACK_NAME)) {
				
				JSONObject object = new JSONObject();
				JSONArray array = new JSONArray();
				
				for (int i = 0; i < modlist_installed.listFiles().length; i++) {
					  if (modlist_installed.listFiles()[i].getName().contains(".jar") || modlist_installed.listFiles()[i].getName().contains(".zip")) {
						  array.add(modlist_installed.listFiles()[i].getName());
					  }
				  }
				
				object.put("Mods", array);
				
				System.out.println("Created JSONArray:");
				System.out.println(object.toString());
				
				try {
					FileWriter file = new FileWriter(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Modpacks\\" + name + ".json"));
					file.write(object.toJSONString());
					file.flush();
					file.close();
			 
				} catch (IOException x) {
				}
				
				modpacks.addItem(name);
				modpacks.select(name);
				
				try {
					Notifications.send(Error.MODPACK_CREATED);
				} catch (IOException e1) {
				}
				
			}
			else {
				Warning.send(Error.MODPACK_FAILED);
			}
		}
		else if (e.getSource() == modpack_use) {
			SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			
			String name = modpacks.getSelectedItem();
			
			File json = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Modpacks\\" + name + ".json");
			
			String full = "";
			
		    try {
		    	BufferedReader reader = new BufferedReader(new FileReader(json));
				
			    String line;
		    	
				while ((line = reader.readLine()) != null) {
				  full = full + line;
				}
				reader.close();
			} catch (IOException e1) {
			}
			
			JsonObject object = new JsonParser().parse(full).getAsJsonObject();
			JsonArray array = object.get("Mods").getAsJsonArray();
			
			for (int i = 0; i < installed_mods.getItemCount(); i++) {
				installed_mods.remove(i);
			}
			
			for (int i = 0; i < modlist_installed.listFiles().length; i++) {
				modlist_installed.listFiles()[i].renameTo(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Mods\\" + modlist_installed.listFiles()[i].getName()));
			}
			
			for (int i = 0; i < array.size(); i++) {
				String current = array.get(i).getAsString();
				File file = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Mods\\" + current);
				if (file.exists()) {
					file.renameTo(new File(System.getenv("APPDATA") + "\\.minecraft\\mods\\" + current));
				}
				installed_mods.addItem(current.replace(".zip", "").replace(".jar", ""));
			}
			
			System.out.println("Read JsonArray:");
			System.out.println(array.toString());
			
			try {
				Notifications.send(Error.MODPACK_LOADED);
			} catch (IOException e1) {
			}
		}
		else if (e.getSource() == modpack_delete) {
			SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			
			String name = modpacks.getSelectedItem();
			
			modpacks.remove(name);
			new File(System.getenv("APPDATA") + "\\.Slimecraft\\Modpacks\\" + name + ".json").delete();
			
			Warning.send(Error.MODPACK_DELETED);
		}
	}

}
