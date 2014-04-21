package me.mrCookieSlime.Slimecraft.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SystemInformations {
	
	public static void collect() throws IOException {
		Properties Sys = new Properties();
		File SysProps = new File(System.getenv("APPDATA") + "\\.Slimecraft\\System.properties");
		
		if (!SysProps.exists()) {
			SysProps.createNewFile();
		}
		
			BufferedInputStream Instream = new BufferedInputStream(new FileInputStream(SysProps));
			Sys.load(Instream);
			BufferedOutputStream Outstream = new BufferedOutputStream(new FileOutputStream(SysProps));
			
			Sys.setProperty("os.name", System.getProperty("os.name"));
			Sys.setProperty("os.version", System.getProperty("os.version"));
			Sys.setProperty("os.arch", System.getProperty("os.arch"));
			Sys.setProperty("java.version", System.getProperty("java.version"));
			Sys.setProperty("java.vendor", System.getProperty("java.vendor"));
			Sys.setProperty("sun.arch.data.model", System.getProperty("sun.arch.data.model"));
			
			Instream.close();
			Sys.store(Outstream, "Send this File to \"slimefuncontact@gmail.com\" if Slimecraft does not work");
			Outstream.close();
	}

}
