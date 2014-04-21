package me.mrCookieSlime.Slimecraft.Downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Downloader {
	
	public static void download(String source, String destination) throws IOException {
        System.out.println("Started Download of " + destination);
        System.out.println("from " + source);
        
        URL website = new URL(source);
	    ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream(destination);
	    fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
	    fos.close();
	    
	    System.out.println("Finished Downloading of " + destination);
	}

}
