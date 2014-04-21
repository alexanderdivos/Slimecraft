package me.mrCookieSlime.Slimecraft.Minecraft;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

import me.mrCookieSlime.Slimecraft.frame;
import me.mrCookieSlime.Slimecraft.Dialogs.Warning;
import me.mrCookieSlime.Slimecraft.Downloader.DownloadDialog;
import me.mrCookieSlime.Slimecraft.Messages.Error;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Versions {
	
	public static File jars = new File(System.getenv("APPDATA") + "\\.minecraft\\versions");
	
	public static String getSelectedVersion() {
		return frame.properties.getProperty("mc-version");
	}
	
	public static void setVersion(String version) {
		try {
			frame.properties.setProperty("mc-version", version);
		      frame.properties.setProperty("jar-path", System.getenv("APPDATA") + "\\.minecraft\\versions\\" + version + "\\" + version + ".jar");

		      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(System.getenv("APPDATA") + "\\.Slimecraft\\Slimecraft.properties"));
		      frame.properties.store(out, "");
		      out.close();
		      
		      System.out.println("Now using Minecraft Version  " + version);
		}
		catch(IOException x) {
		}
	}
	
	public static void deleteVersion(String version) {
		File input = new File(System.getenv("APPDATA") + "\\.minecraft\\" + version);
		for (int i = 0; i < input.listFiles().length; i++) {
			input.listFiles()[i].delete();
		}
		
		input.delete();
		
		frame.MCversion.remove(version);
	}
	
	public static void setAlwaysSnapshot(String value) {
		try {
			frame.properties.setProperty("snapshot", value);

		      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(System.getenv("APPDATA") + "\\.Slimecraft\\Slimecraft.properties"));
		      frame.properties.store(out, "");
		      out.close();
		}
		catch(IOException x) {
		}
	}
	
	public static void setAlwaysLatest(String value) {
		try {
			frame.properties.setProperty("always-latest", value);

		      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(System.getenv("APPDATA") + "\\.Slimecraft\\Slimecraft.properties"));
		      frame.properties.store(out, "");
		      out.close();
		}
		catch(IOException x) {
		}
	}
	
	public static void installVersion(final String version) {
		System.out.println("Installing Version " + version + " ...");
		  
		  new Thread(){
		      public void run() {
		           try {
		        	   Thread.sleep(500);
		        	   File folder = new File(System.getenv("APPDATA") + "\\.minecraft\\versions\\" + version);

		 		      folder.mkdir();
		 		      
		 		      List<String> files = new ArrayList<String>();
		 		      List<String> urls = new ArrayList<String>();
		 		     
		 		      files.add(System.getenv("APPDATA") + "\\.minecraft\\versions\\" + version + "\\" + version + ".jar");
		 		      files.add(System.getenv("APPDATA") + "\\.minecraft\\versions\\" + version + "\\" + version + ".json");
		 		      urls.add("http://s3.amazonaws.com/Minecraft.Download/versions/" + version + "/" + version + ".jar");
		 		      urls.add("http://s3.amazonaws.com/Minecraft.Download/versions/" + version + "/" + version + ".json");
		 		     
		 		     DownloadDialog.downloadList(files, urls);

		 		      System.out.println("Successfully installed Version " + version);
		 		      
		 		      String done = Error.INSTALLED;
		 		      done = done.replace("%version%", version);
		 		      
		 		      Warning.send(done);

		 		      setVersion(version);
		 		      
		 		      frame.MCversion.addItem(version);
		 		      frame.MCversion.select(version);
		           } catch (InterruptedException e) {
		           }
		      };
		}.start();
	}
	
	public static boolean isSnapshotAvailable() {
		return !getInstalledVersions().contains(getLatestSnapshot());
	}
	
	public static boolean isUpdateAvailable() {
		return !getInstalledVersions().contains(getLatestVersion());
	}
	
	public static boolean isSnapshotRelease() {
		return getLatestSnapshot().equalsIgnoreCase(getLatestVersion());
	}
	
	public static void getLatestVersions() {
		System.out.println("Receiving latest Minecraft version list...");
		
		try {
			URL website = new URL("https://s3.amazonaws.com/Minecraft.Download/versions/versions.json");
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(System.getenv("APPDATA") + "\\.Slimecraft\\versions.json");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			System.out.println("Received latest Minecraft version list");
		} catch (IOException e) {
			System.out.println("Could not get the latest Version list, ignoring it");
		}
	}
	
	public static String getLatestVersion()  {
		
		String v = "";
		
		try {
		
			BufferedReader reader = new BufferedReader(new FileReader(new File(System.getenv("APPDATA") + "\\.Slimecraft\\versions.json")));
			
			String full = "";
			
			String line;
		    while ((line = reader.readLine()) != null) {
		        full = full + line;
		    }
		    
		    reader.close();
		    
		    JsonElement element = new JsonParser().parse(full);
		    JsonObject object = element.getAsJsonObject();
		    object = (JsonObject) object.get("latest");
		    v = object.get("release").toString().replace("\"", "");
			
		} catch (IOException e) {
		}
		
		return v;
	}
	
	public static String getLatestSnapshot()  {
		
		String v = "";
		
		try {
		
			BufferedReader reader = new BufferedReader(new FileReader(new File(System.getenv("APPDATA") + "\\.Slimecraft\\versions.json")));
			
			String full = "";
			
			String line;
		    while ((line = reader.readLine()) != null) {
		        full = full + line;
		    }
		    
		    reader.close();
		    
		    JsonElement element = new JsonParser().parse(full);
		    JsonObject object = element.getAsJsonObject();
		    object = (JsonObject) object.get("latest");
		    v = object.get("snapshot").toString().replace("\"", "");
			
		} catch (IOException e) {
		}
		
		return v;
	}
	
	public static List<String> getVersions() {
		List<String> versions = new ArrayList<String>();
		
		try {
		
			BufferedReader reader = new BufferedReader(new FileReader(new File(System.getenv("APPDATA") + "\\.Slimecraft\\versions.json")));
			
			String full = "";
			
			String line;
		    while ((line = reader.readLine()) != null) {
		        full = full + line;
		    }
		    
		    reader.close();
		    
		    JsonElement element = new JsonParser().parse(full);
		    JsonObject object = element.getAsJsonObject();
		    JsonArray array = object.getAsJsonArray("versions");
		    
		    for (int i = 0; i < array.size(); i++) {
		    	object = array.get(i).getAsJsonObject();
		    	String current = object.get("id").toString().replace("\"", "");
		    	versions.add(current);
		    }
			
		} catch (IOException e) {
		}
		
		return versions;
	}
	
	public static List<String> getMissingVersions() {
List<String> versions = new ArrayList<String>();
		
		try {
		
			BufferedReader reader = new BufferedReader(new FileReader(new File(System.getenv("APPDATA") + "\\.Slimecraft\\versions.json")));
			
			String full = "";
			
			String line;
		    while ((line = reader.readLine()) != null) {
		        full = full + line;
		    }
		    
		    reader.close();
		    
		    JsonElement element = new JsonParser().parse(full);
		    JsonObject object = element.getAsJsonObject();
		    JsonArray array = object.getAsJsonArray("versions");
		    
		    for (int i = 0; i < array.size(); i++) {
		    	object = array.get(i).getAsJsonObject();
		    	String current = object.get("id").toString().replace("\"", "");
		    	if (!new File(System.getenv("APPDATA") + "\\.minecraft\\versions\\" + current + "\\" + current + ".jar").exists() || !new File(System.getenv("APPDATA") + "\\.minecraft\\versions\\" + current + "\\" + current + ".json").exists()) {
		    		versions.add(current);
		    	}
		    }
			
		} catch (IOException e) {
		}
		
		return versions;
	}
	
	public static List<String> getInstalledVersions() {
		List<String> versions = new ArrayList<String>();
		
		 for (int i = 0; i < jars.listFiles().length; i++) {
			 if (new File(jars.listFiles()[i].getPath() + "\\" + jars.listFiles()[i].getName() + ".jar").exists() && new File(jars.listFiles()[i].getPath() + "\\" + jars.listFiles()[i].getName() + ".json").exists()) {
				 versions.add(jars.listFiles()[i].getName());
			 }
		  }
		
		return versions;
	}
	
	public static void changeID(String version, String id) {
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
		    
		    object.addProperty("id", id);
		    
		    ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(System.getenv("APPDATA") + "\\.minecraft\\versions\\" + version + "\\" + version + ".json"));
                outputStream.writeObject(object);
                outputStream.flush();
                    outputStream.close();
			
		} catch (IOException e) {
		}
	}

}
