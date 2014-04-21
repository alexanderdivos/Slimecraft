package me.mrCookieSlime.Slimecraft.UpdateChecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Updates {
	
	private static int build = 51;
	public static String version = "    Version Beta 1.5";
	
	private static File versioncheckfile = new File(System.getenv("APPDATA") + "\\.Slimecraft\\version.txt");
	public static boolean isAvailable;
	
	public static void checkFor() throws IOException {
		
		URL versioncheck = new URL("https://dl.dropboxusercontent.com/s/ct37o944ua4npa5/version.txt?token_hash=AAHMMDZyLXBDkEH9eS7VkxS4q8a8s8v0LwF1AT2vmixAOQ&dl=1");
	    ReadableByteChannel versionrbc = Channels.newChannel(versioncheck.openStream());
		FileOutputStream versionfos = new FileOutputStream(System.getenv("APPDATA") + "\\.Slimecraft\\version.txt");
		versionfos.getChannel().transferFrom(versionrbc, 0, Long.MAX_VALUE);
		versionfos.close();
		
		String vs;
		
		BufferedReader br = new BufferedReader(new FileReader(versioncheckfile));
		 try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append('\n');
		            line = br.readLine();
		        }
		        vs = sb.toString();
		    } finally {
		        br.close();
		    }
		 String[] updater = vs.split(":");
		 Integer ver = Integer.parseInt(updater[1]);
		 if (ver > build) {
			 isAvailable = true;
		 }
	}

}
