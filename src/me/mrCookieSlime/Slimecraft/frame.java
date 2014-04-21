package me.mrCookieSlime.Slimecraft;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import me.mrCookieSlime.Slimecraft.Dialogs.Notifications;
import me.mrCookieSlime.Slimecraft.Dialogs.Warning;
import me.mrCookieSlime.Slimecraft.Downloader.DownloadDialog;
import me.mrCookieSlime.Slimecraft.Downloader.FileManager;
import me.mrCookieSlime.Slimecraft.Messages.ButtonText;
import me.mrCookieSlime.Slimecraft.Messages.Error;
import me.mrCookieSlime.Slimecraft.Messages.Tooltip;
import me.mrCookieSlime.Slimecraft.Minecraft.Minecraft;
import me.mrCookieSlime.Slimecraft.Minecraft.Versions;
import me.mrCookieSlime.Slimecraft.Setup.Configurations;
import me.mrCookieSlime.Slimecraft.Setup.Folders;
import me.mrCookieSlime.Slimecraft.Setup.Localization;
import me.mrCookieSlime.Slimecraft.UpdateChecker.Updates;
import me.mrCookieSlime.Slimecraft.Utils.Console;
import me.mrCookieSlime.Slimecraft.Utils.OS;
import me.mrCookieSlime.Slimecraft.Utils.ServerStatus;
import me.mrCookieSlime.Slimecraft.Utils.SoundHandler;
import me.mrCookieSlime.Slimecraft.Utils.SystemInformations;
import me.mrCookieSlime.Slimecraft.Utils.Window;

@SuppressWarnings("serial")
public class frame extends JFrame implements ActionListener, ItemListener {
	
	private JButton options;
	private JButton exit;
	public JButton login;
	private JButton versionmanager;
	private JButton changelog;
	
	private JButton twitter;
	private JButton steam;
	private JButton youtube;
	
	public static frame main;
	
	public static Choice user;
	
	private static JTextField userName;
	private static JPasswordField passWord;
	
	private static JTextField userName_desc;
	private static JTextField passWord_desc;
	
	public static Choice MCversion;
	
	public static Properties properties = new Properties();
	public static File props = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Slimecraft.properties");
	
	public static JTextField vs;
	
	static Point mouseDownCompCoords;
	
	public static void main(String[] args) throws IOException{
		
		Localization.setup();
		
		if (OS.isWindows()) {
			Folders.create();
			FileManager.registerDownloads();
			
			if (FileManager.getFiles().size() > 0) {
				DownloadDialog.downloadList(FileManager.getFiles(), FileManager.getURLs());
			}
			
			SystemInformations.collect();
			Configurations.setDefaults();
			Updates.checkFor();
			
			new Background();
			
			final JFrame frame = new frame("Slimecraft");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setState(JFrame.SOMEBITS);
			frame.setSize(800, 600);
			frame.setAlwaysOnTop(false);
			frame.setLocation(400, 200);
			frame.setIconImage(ImageIO.read(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\icon.png")));
			frame.setResizable(false);
			
			frame.setUndecorated(true);
			frame.addMouseListener(new MouseListener(){
	            public void mouseReleased(MouseEvent e) {
	                mouseDownCompCoords = null;
	            }
	            public void mousePressed(MouseEvent e) {
	                mouseDownCompCoords = e.getPoint();
	            }
	            public void mouseExited(MouseEvent e) {
	            }
	            public void mouseEntered(MouseEvent e) {
	            }
	            public void mouseClicked(MouseEvent e) {
	            }
	        });

	        frame.addMouseMotionListener(new MouseMotionListener(){
	            public void mouseMoved(MouseEvent e) {
	            }

	            public void mouseDragged(MouseEvent e) {
	                Point currCoords = e.getLocationOnScreen();
	                try {
	                	 frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
	                }
	                catch(NullPointerException x){
	                	
	                }
	            }
	        });
	        
	        main = (frame) frame;
			
			
			if (Updates.isAvailable) {
				Warning.send(Error.UPDATE);
				System.out.println("Update Available");
				System.out.println("Slimecraft.exe");
				URL website = new URL("https://dl.dropboxusercontent.com/s/cheze9njbsyuva4/Slimecraft.exe?token_hash=AAHI2UP8Kz-Xz_AnHPl2pp3ukeTW7lwhHuVXKOgRANapPg&dl=1");
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream("Slimecraft.exe");
			    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			    
			    fos.close();
			    
			    Desktop.getDesktop().open(new File("Slimecraft.exe"));
				
				System.exit(0);
			}
			else {
				
				Minecraft.init(main);
				Minecraft.setupSkin(main);
				
				ServerStatus.get(main);
				
				File[] userlist = Minecraft.users.listFiles();
				
				if (userlist.length != 0) {
					
					user = new Choice();
					user.setBounds(20, 170, 128, 20);
					
					 for (int i = 0; i < userlist.length; i++) {
			        	 String raw = userlist[i].getName();
			        	 String replaced = raw.replace(".uid", "");
			        	 user.addItem(replaced);
			        }
			        
					 user.addItemListener(new frame(""));
			        
					user.select(properties.getProperty("last-user"));
			        
					main.add(user);
				}
				
				MCversion = new Choice();
				  MCversion.addItemListener(new frame(""));
				  MCversion.setBounds(20, 200, 128, 20);
				  MCversion.addItem("Latest Release");
				  MCversion.addItem("Latest Snapshot");
				  for (int i = 0; i < Versions.getInstalledVersions().size(); i++) {
					    MCversion.addItem(Versions.getInstalledVersions().get(i));
					  }
				   String current = properties.getProperty("mc-version");
				   MCversion.select(current);
				   main.add(MCversion);
				
				  main.add(Background.current);
				
				  SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\start.wav");
				  main.setVisible(true);
				
				try {
					if (properties.getProperty("show-console").equalsIgnoreCase("true")) {
						Console.main(null);
					}
				}
				catch(Exception x) {
				}
				
				System.out.println("Starting Slimecraft" + Updates.version);
				System.out.println("Language: " + System.getProperties().getProperty("user.language"));
				System.out.println("Operating System:");
				System.out.println(System.getProperty("os.name") + " v" + System.getProperty("os.version"));
				System.out.println("with arch: " + System.getProperty("os.arch"));
				
				System.out.println("Loaded Properties");
				
				System.out.println("Opening Window \"Slimecraft\"");
				
				System.out.println("Currently using Minecraft version: " + properties.getProperty("mc-version"));
				
				if (ServerStatus.ping("http://s3.amazonaws.com/Minecraft.Download/versions/versions.json", 5000)) {
					System.out.println("Reached Minecraft File Server");
					Versions.getLatestVersions();
				}
				else {
					System.out.println("Could not reach Minecraft File Server");
				}
				
				System.out.println("Latest Minecraft Version: " + Versions.getLatestVersion());
				System.out.println("Latest Minecraft Snapshot: " + Versions.getLatestSnapshot());
				
				if (Versions.isUpdateAvailable()) {
					Notifications.send(ButtonText.UPDATE_OUT + Versions.getLatestVersion());
					System.out.println("A new Minecraft Update is available for Download: " + Versions.getLatestVersion());
				}
				else {
					System.out.println("Minecraft is up to date!");
				}
				if (Versions.isSnapshotAvailable()) {
					if (!Versions.isSnapshotRelease()) {
						Notifications.send(ButtonText.SNAPSHOT_OUT + Versions.getLatestSnapshot());
						System.out.println("A new Snapshot is available for Download: " + Versions.getLatestSnapshot());
					}
				}
			}
		}
		else {
			Warning.send(Error.WRONG_OS);
			new Thread(){
			      public void run() {
			           try {
			        	   Thread.sleep(10000);
			               System.exit(0);
			           } catch (InterruptedException e) {
			           }
			      };
			}.start();
		}
	}
	
	public frame(String title) {
		super(title);
			
			versionmanager = new JButton(ButtonText.VERSION_MANAGER);
			versionmanager.setBounds(620, 90, 160, 30);
			versionmanager.setCursor(new Cursor(12));
			versionmanager.addActionListener(this);
			add(versionmanager);
			
			changelog = new JButton(ButtonText.CHANGELOG);
			changelog.setBounds(620, 50, 160, 30);
			changelog.setCursor(new Cursor(12));
			changelog.addActionListener(this);
			add(changelog);
			
			exit = new JButton();
			exit.setIcon(new ImageIcon(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\exit.png"));
			exit.setBounds(730, 12, 50, 20);
			exit.setCursor(new Cursor(12));
			exit.addActionListener(this);
			add(exit);
			
			options = new JButton();
			options.setIcon(new ImageIcon(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\config.png"));
			options.setBounds(675, 12, 50, 20);
			options.addActionListener(this);
			options.setCursor(new Cursor(12));
			add(options);
			
			twitter = new JButton();
			twitter.setIcon(new ImageIcon(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\twitter.png"));
			twitter.setBounds(20, 520, 32, 32);
			twitter.setCursor(new Cursor(12));
			twitter.addActionListener(this);
			twitter.setToolTipText(Tooltip.TWITTER);
			add(twitter);
			
			steam = new JButton();
			steam.setIcon(new ImageIcon(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\steam.png"));
			steam.setBounds(68, 520, 32, 32);
			steam.setCursor(new Cursor(12));
			steam.addActionListener(this);
			steam.setToolTipText(Tooltip.STEAM);
			add(steam);
			
			youtube = new JButton();
			youtube.setIcon(new ImageIcon(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\youtube.png"));
			youtube.setBounds(118, 520, 32, 32);
			youtube.setCursor(new Cursor(12));
			youtube.addActionListener(this);
			youtube.setToolTipText(Tooltip.YOUTUBE);
			add(youtube);
			
			login = new JButton(ButtonText.LOGIN);
			login.setBounds(620, 510, 160, 70);
			login.setCursor(new Cursor(12));
			login.addActionListener(this);
			add(login);
			
			vs = new JTextField(20);
			vs.setBounds(20, 560, 128, 20);
			vs.setEditable(false);
			vs.setText(Updates.version);
			add(vs);
			
			userName = new JTextField(20);
			userName.setBounds(320, 515, 160, 22);
			add(userName);
			
			passWord = new JPasswordField(20);
			passWord.setBounds(320, 553, 160, 22);
			add(passWord);
			
			userName_desc = new JTextField(20);
			userName_desc.setBounds(245, 515, 68, 22);
			userName_desc.setEditable(false);
			userName_desc.setText(" Username");
			userName_desc.setForeground(Color.darkGray);
			add(userName_desc);
			
			passWord_desc = new JTextField(20);
			passWord_desc.setBounds(245, 553, 68, 22);
			passWord_desc.setEditable(false);
			passWord_desc.setText(" Password");
			passWord_desc.setForeground(Color.darkGray);
			add(passWord_desc);
			
			userName.setText(properties.getProperty("last-username"));
			passWord.setText(properties.getProperty("last-password"));
	}
	

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exit) {
			System.out.println("Terminated");
			
			System.exit(0);
		}
		else if (e.getSource() == options) {
			
			SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			
			try {
				Window.openOptions();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == login)  {
			
			SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			
			if (properties.getProperty("always-latest").equalsIgnoreCase("true")) {
				if (Versions.isUpdateAvailable()) {
					Versions.installVersion(Versions.getLatestVersion());
				}
				else {
					Versions.setVersion(Versions.getLatestVersion());
				}
			}
			else {
				
			}
			
			if (properties.getProperty("snapshot").equalsIgnoreCase("true")) {
				if (Versions.isSnapshotAvailable()) {
					Versions.installVersion(Versions.getLatestSnapshot());
				}
				else {
					Versions.setVersion(Versions.getLatestSnapshot());
				}
			}
			
			try {
				Minecraft.lauchGame(userName.getText(), passWord.getText());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == twitter) {
			
			SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			
			try {
				Window.openTwitter();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == steam) {
			
			SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			
			try {
				Window.openSteam();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == youtube) {
			
			SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			
			try {
				Window.openYouTube();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == versionmanager) {
			
			SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			
			try {
				Window.openVersionManager();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == changelog) {
			
			SoundHandler.playSound(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds\\click.wav");
			
			try {
				Window.openChangelog();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e){
		if (e.getSource() == user) {
			try {
				String current = user.getSelectedItem();
				
				File userfile = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Users\\" + current + ".UID");
				
				BufferedInputStream uid = new BufferedInputStream(new FileInputStream(userfile));
				Properties data = new Properties();
				data.load(uid);
				uid.close();
					
				String password = data.getProperty("password");
				String username = data.getProperty("username");
				String name = data.getProperty("name");
					
				Minecraft.saveUser(username, password, name);
				
				System.out.println("Selected User: " + current);
				
				main.remove(Background.current);
				main.remove(Minecraft.panel);
				
				main.repaint();
				main.invalidate();
				main.validate();
				
				Minecraft.setupSkin(main);
				main.add(Background.current);
				
				main.repaint();
				main.invalidate();
				main.validate();
				
				userName.setText(username);
				passWord.setText(password);
				
				userName.updateUI();
				passWord.updateUI();
				
				System.out.println("TextField \"Username\" has been set to: " + username);
				System.out.println("TextField \"Password\" has been set to: " + password);
			}
			catch(IOException x) {
			}
		}
		else if (e.getSource() == MCversion) {
			String version = MCversion.getSelectedItem();
			if (version.equalsIgnoreCase("Latest Release")) {
				Versions.setAlwaysLatest("true");
				Versions.setAlwaysSnapshot("false");
				System.out.println("Now using latest Minecraft \"Release\"");
			}
			else if (version.equalsIgnoreCase("Latest Snapshot")) {
				Versions.setAlwaysSnapshot("true");
				Versions.setAlwaysLatest("false");
				System.out.println("Now using latest Minecraft \"Snapshot\"");
			}
			else {
				Versions.setVersion(version);
				Versions.setAlwaysSnapshot("false");
				Versions.setAlwaysLatest("false");
			}
		}
			
	}
}