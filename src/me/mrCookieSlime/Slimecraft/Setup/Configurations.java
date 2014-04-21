package me.mrCookieSlime.Slimecraft.Setup;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import me.mrCookieSlime.Slimecraft.frame;

public class Configurations {
	
	public static void setDefaults() throws IOException {
		if (!frame.props.exists()) {
		    
			frame.props.createNewFile();
			
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(frame.props));
			frame.properties.load(stream);
			
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(frame.props));
			
			
			if (!frame.properties.contains("jar-path")) {
				frame.properties.setProperty("jar-path", System.getenv("APPDATA") + "\\.minecraft\\versions\\" + "1.7.2" + ".jar");
			}
			if (!frame.properties.contains("minecraft-path")) {
				frame.properties.setProperty("minecraft-path", System.getenv("APPDATA") + "\\.minecraft");
			}
			if (!frame.properties.contains("XMS")) {
				frame.properties.setProperty("XMS", "512");
			}
			if (!frame.properties.contains("XMX")) {
				frame.properties.setProperty("XMX", "1024");
			}
			if (!frame.properties.contains("width")) {
				frame.properties.setProperty("width", "854");
			}
			if (!frame.properties.contains("height")) {
				frame.properties.setProperty("height", "480");
			}
			if (!frame.properties.contains("save_data")) {
				frame.properties.setProperty("save_data", "true");
			}
			if (!frame.properties.contains("fullscreen")) {
				frame.properties.setProperty("fullscreen", "false");
			}
			if (!frame.properties.contains("mc-version")) {
				frame.properties.setProperty("mc-version", "1.7.2");
			}
			if (!frame.properties.contains("always-latest")) {
				frame.properties.setProperty("always-latest", "false");
			}
			if (!frame.properties.contains("snapshot")) {
				frame.properties.setProperty("snapshot", "false");
			}
			
			stream.close();
			frame.properties.store(out, "");
			out.close();
			
		}
		else {
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream(frame.props));
			frame.properties.load(stream);
			
			stream.close();
		}
	}

}
