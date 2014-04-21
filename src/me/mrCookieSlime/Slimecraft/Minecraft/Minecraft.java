package me.mrCookieSlime.Slimecraft.Minecraft;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import me.mrCookieSlime.Slimecraft.Background;
import me.mrCookieSlime.Slimecraft.frame;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Minecraft {
	
	public static BufferedImage image; 
	
	public static JPanel panel;

	public static JLabel label;

	public static ImageIcon icon;
	
	public static File users = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Users");
	
	public static void init(frame frame) {
        if (!users.exists()) {
        	users.mkdir();
        }
	}
	
	public static void lauchGame(String username, String password) throws IOException {
		String version = Versions.getSelectedVersion();
		String fullscreen = frame.properties.getProperty("fullscreen");
		
		String XMS = frame.properties.getProperty("XMS") + "m";
		String XMX = frame.properties.getProperty("XMX") + "m";
		
		
		String response = MinecraftAuthentication.send(username, password);
		
		if (!response.equalsIgnoreCase("error")) {
			String[] authentication = getAuthenticationAsArray(username, password, response);
			List<File> LibraryFiles = getLibraries(version);
			String jar = "\"" + frame.properties.getProperty("jar-path") + "\"";
			
			String user = authentication[2];
			
			String natives = "\"" + System.getenv("APPDATA") + "\\.Slimecraft\\natives\"";
			
			String arguments = getArguments(version, authentication, Boolean.parseBoolean(fullscreen));
			String libraries = getLibrariesAsString(LibraryFiles);
			
			saveUser(username, password, user);
			
			String runtime =
					"javaw -Xms" + XMS + " -Xmx" + XMX + " -Djava.library.path=" + natives + " -cp " + libraries + jar + " " + arguments;
			
			System.out.println(runtime);
			
			frame.main.setVisible(false);
			
			Process p = MinecraftLauncher.start(runtime);
			
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			  while ((line = input.readLine()) != null) {
			    System.out.println(line);
			  }
			input.close();
			
			frame.main.setVisible(true);
			frame.main.add(Background.current);
		}
	}
	
	public static BufferedImage createResizedCopy(Image originalImage, 
    		int scaledWidth, int scaledHeight, 
    		boolean preserveAlpha)
    {
    	int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
    	BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
    	Graphics2D g = scaledBI.createGraphics();
    	if (preserveAlpha) {
    		g.setComposite(AlphaComposite.Src);
    	}
    	g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
    	g.dispose();
    	return scaledBI;
    }
	
	public static void setupSkin(JFrame frame) {
		if (users.listFiles().length != 0) {
				
				System.out.println("Displaying " + me.mrCookieSlime.Slimecraft.frame.properties.getProperty("last-user") + "'s Head");
				
				File skin = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Skins\\" + me.mrCookieSlime.Slimecraft.frame.properties.getProperty("last-user") + ".png");

				try {
					image = ImageIO.read(skin);
					Graphics g = image.getGraphics();
			        g.setColor(Color.BLACK);
			        g.fillRect(0, 190, 450, 50);
			        g.setFont(new Font("Sans", Font.PLAIN, 40));
			        g.setColor(Color.WHITE);
			        
			       image = image.getSubimage(8, 8, 8, 8);
			        
			        BufferedImage bimg = createResizedCopy(image, 128, 128, true);
			        
			        icon = new ImageIcon(bimg);
			        label = new JLabel();
			        label.setBorder(new LineBorder(Color.WHITE));
			        label.setIcon(icon);
			        label.revalidate();
			        label.repaint();
			        
			        panel = new JPanel();
			        panel.setLayout(new BorderLayout());
			        panel.setBackground(Color.DARK_GRAY);
			        panel.add(label);
			        panel.setBounds(20, 20, 128, 128);
			        
			        frame.setIconImage(bimg);
			        
			        frame.add(panel);
			        
			        System.out.println("Skin found, using it's head");
			        
				} catch (IOException e) {
				}
		}
	}
	
	public static void setupIcon(JFrame frame) {
		try {
			if (users.listFiles().length != 0) {
				
				File skin = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Skins\\" + me.mrCookieSlime.Slimecraft.frame.properties.getProperty("last-user") + ".png");

				try {
					image = ImageIO.read(skin);
					Graphics g = image.getGraphics();
			        g.setColor(Color.BLACK);
			        g.fillRect(0, 190, 450, 50);
			        g.setFont(new Font("Sans", Font.PLAIN, 40));
			        g.setColor(Color.WHITE);
			        
			       image = image.getSubimage(8, 8, 8, 8);
			        
			        BufferedImage bimg = createResizedCopy(image, 128, 128, true);
			        
			        frame.setIconImage(bimg);
			        
				} catch (IOException e) {
				}
		}
			else {
				
				try {
					frame.setIconImage(ImageIO.read(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\icon.png")));
				} catch (IOException e) {
				}
				
			}
		}
		catch(NullPointerException y) {
			try {
				frame.setIconImage(ImageIO.read(new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images\\icon.png")));
			} catch (IOException e) {
			}
		}
	}
	
	public static String getArguments(String version, String[] credentials, boolean fullscreen) {
		
		String args = "";
		
		String User = credentials[2];
		String accessToken = credentials[3];
		String UUID = credentials[5];
		String UserProperties = credentials[6];
		
		try {
		
			BufferedReader reader = new BufferedReader(new FileReader(new File(System.getenv("APPDATA") + "\\.minecraft\\versions\\" + version + "\\" + version + ".json")));
			
			String full = "";
			
			String line;
		    while ((line = reader.readLine()) != null) {
		        full = full + line;
		    }
		    
		    reader.close();
		    
		    JsonElement element = new JsonParser().parse(full);
		    JsonObject object = element.getAsJsonObject();
		    
		    args = object.get("minecraftArguments").getAsString();
			
		} catch (IOException e) {
		}
		
		if (args.contains("${auth_player_name}")) {
			args = args.replace("${auth_player_name}",  User);
		}
		if (args.contains("${auth_session}")) {
			args = args.replace("${auth_session}", accessToken);
		}
		if (args.contains("${version_name}")) {
			args = args.replace("${version_name}", me.mrCookieSlime.Slimecraft.frame.properties.getProperty("mc-version"));
		}
		if (args.contains("${game_directory}")) {
			args = args.replace("${game_directory}", "\"" + me.mrCookieSlime.Slimecraft.frame.properties.getProperty("minecraft-path") + "\"");
		}
		if (args.contains("${game_assets}")) {
			args = args.replace("${game_assets}", "\"" + System.getenv("APPDATA") + "\\.minecraft\\assets\\virtual\\legacy\"");
		}
		if (args.contains("${auth_uuid}")) {
			args = args.replace("${auth_uuid}", UUID);
		}
		if (args.contains("${auth_access_token}")) {
			args = args.replace("${auth_access_token}", accessToken);
		}
		if (args.contains("${user_properties}")) {
			args = args.replace("${user_properties}", UserProperties);
		}
		if (args.contains("${assets_root}")) {
			args = args.replace("${assets_root}", "\"" + System.getenv("APPDATA") + "\\.minecraft\\assets\"");
		}
		if (args.contains("${assets_index_name}")) {
			if (new File(System.getenv("APPDATA") + "\\.minecraft\\assets\\indexes\\" + version + ".json").exists()) {
				args = args.replace("${assets_index_name}", "\"" + version + "\"");
			}
			else {
				args = args.replace("${assets_index_name}", "\"" + "legacy" + "\"");
			}
		}
		
		String fs = "";
		if (fullscreen) {
			fs = " --fullscreen";
		}
		
		args = " " + getMain(version) + " " + args + "" + fs + " --width " + frame.properties.getProperty("width")+ " --height " + frame.properties.getProperty("height");
		
		
		return args;
	}
	
	public static String getMain(String version) {
		String main = "";
		
		try {
		
			BufferedReader reader = new BufferedReader(new FileReader(new File(System.getenv("APPDATA") + "\\.minecraft\\versions\\" + version + "\\" + version + ".json")));
			
			String full = "";
			
			String line;
		    while ((line = reader.readLine()) != null) {
		        full = full + line;
		    }
		    
		    reader.close();
		    
		    JsonElement element = new JsonParser().parse(full);
		    JsonObject object = element.getAsJsonObject();
		    
		    main = object.get("mainClass").getAsString();
			
		} catch (IOException e) {
		}
		
		return main;
	}
	
	public static String getLibrariesAsString(List<File> libs) {
		String lib = "";
		
		for (int i = 0; i < libs.size(); i++) {
			lib = lib + "\"" + libs.get(i).getPath() + "\";";
		}
		
		System.out.println("Found Libraries:");
		System.out.println(lib);
		
		return lib;
	}
	
	
	public static List<File> getLibraries(String version) {
		List<String> lib = new ArrayList<String>();
	    try
	    {
	      BufferedReader reader = new BufferedReader(new FileReader(new File(System.getenv("APPDATA") + "\\.minecraft\\versions\\" + version + "\\" + version + ".json")));

	      String full = "";
	      String line;
	      while ((line = reader.readLine()) != null) {
	        full = full + line;
	      }

	      reader.close();

	      JsonElement element = new JsonParser().parse(full);
	      JsonObject object = element.getAsJsonObject();
	      JsonArray array = object.getAsJsonArray("libraries");

	      for (int i = 0; i < array.size(); i++) {
	        object = array.get(i).getAsJsonObject();
	        lib.add(object.get("name").toString());
	      }
	    }
	    catch (IOException localIOException)
	    {
	    }

	    String libPrefix = System.getenv("APPDATA") + "\\.minecraft\\libraries\\";

	    List<File> libs = new ArrayList<File>();

	    for (int i = 0; i < lib.size(); i++) {
	      String raw = (String)lib.get(i);
	      String[] packed = raw.split(":");
	      try {
	        String v = packed[2];
	        if (v.contains("-")) {
	          v = v.split("-")[0];
	        }
	        String replaced = null;

	        replaced = packed[0].replace(".", "\\") + "\\" + packed[1] + "\\" + v;
	        String path = libPrefix + replaced;
	        path = path.replace("\"", "");
	        path = new File(path).listFiles()[0].getPath();
	        libs.add(new File(path));
	      }
	      catch (NullPointerException x) {
	        try {
	          String v = packed[2];
	          if (v.contains("-")) {
	            v = v.split("-")[0];
	          }
	          String replaced = null;

	          replaced = packed[0].replace(".", "\\") + "\\" + packed[1];
	          String path = libPrefix + replaced;
	          path = path.replace("\"", "");
	          path = new File(path).listFiles()[0].listFiles()[0].getPath();
	          libs.add(new File(path));
	        }
	        catch (NullPointerException n) {
	          System.out.println("There was an Error with the Library " + raw);
	        }
	      }
	    }

	    return libs;
	}
	
	public static String[] getAuthenticationAsArray(String username, String password, String authenticationResponse) {
		
		String User = "";
		String accessToken = "";
		String clientToken = "";
		String UUID = "";
		String UserProperties = "{}";
		
		try {
			
			BufferedReader reader = new BufferedReader(new StringReader(authenticationResponse));
			
			String full = "";
			
			String line;
		    while ((line = reader.readLine()) != null) {
		        full = full + line;
		    }
		    
		    reader.close();
		    
		    JsonElement element = new JsonParser().parse(full);
		    JsonObject object = element.getAsJsonObject();
		    User = object.get("selectedProfile").getAsJsonObject().get("name").getAsString();
		    accessToken = object.get("accessToken").getAsString();
		    clientToken = object.get("clientToken").getAsString();
		    UUID =  object.get("selectedProfile").getAsJsonObject().get("id").getAsString();
		    
		    try {
		    	JsonArray array = object.get("user").getAsJsonObject().get("properties").getAsJsonArray();
		    	String properties = "";
		    	for (int i = 0; i < array.size(); i++) {
		    		if (i != (array.size() - 1)) {
		    			properties = properties + array.get(i).getAsJsonObject().get("name").getAsString() + ":[" + array.get(i).getAsJsonObject().get("value").getAsString() + "],";
		    		}
		    		else {
		    			properties = properties + array.get(i).getAsJsonObject().get("name").getAsString() + ":[" + array.get(i).getAsJsonObject().get("value").getAsString() + "]";
		    		}
		    	}
		    	
		    	UserProperties = "{" + properties + "}";
		    }
		    catch(NullPointerException x) {
		    }
		    
		    String replaced = "";
		    for (int i = 0; i < password.length(); i++) {
		    	replaced = replaced + "*";
		    }
		    
		    System.out.println("Username = " + username);
		    System.out.println("Password = " + replaced);
		    System.out.println("User = " + User);
		    System.out.println("AccessToken = " + accessToken);
		    System.out.println("ClientToken = " + clientToken);
		    System.out.println("UUID = " + UUID);
		    System.out.println("User Properties = " + UserProperties);
			
		} catch (IOException e) {
		}
		
		String[] array = {username,password,User,accessToken,clientToken,UUID,UserProperties};
		return array;
	}
	
	public static void saveUser(String username, String password, String user) throws FileNotFoundException, IOException {
		
		if (frame.properties.getProperty("save_data").equalsIgnoreCase("true")) {
			Properties properties = frame.properties;
			properties.setProperty("last-username", username);
			properties.setProperty("last-password", password);
			properties.setProperty("last-user", user);
			properties.store(new FileOutputStream(frame.props), "");
		}
		
	}
}
