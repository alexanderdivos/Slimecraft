package me.mrCookieSlime.Slimecraft.Utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import me.mrCookieSlime.Slimecraft.frame;
import me.mrCookieSlime.Slimecraft.Minecraft.ChangeLog;
import me.mrCookieSlime.Slimecraft.Options.Options;
import me.mrCookieSlime.Slimecraft.Options.VersionManager;

public class Window {
	
	public static void openChangelog() throws IOException {
		ChangeLog.main(null);
		frame.main.setVisible(false);
	}
	
	public static void openOptions() throws IOException {
		Options.main(null);
		frame.main.setVisible(false);
	}
	
	public static void openTwitter() throws IOException, URISyntaxException {
		
		System.out.println("Opening Window \"Twitter\"");
		
		Desktop.getDesktop().browse(new URI("http://www.twitter.com/mrCookieSlime"));
	}
	public static void openSteam() throws IOException, URISyntaxException {
		
		System.out.println("Opening Window \"Steam\"");
		
		Desktop.getDesktop().browse(new URI("http://steamcommunity.com/id/moewe1/"));
	}
	public static void openYouTube() throws IOException, URISyntaxException {
		
		System.out.println("Opening Window \"YoutTube\"");
		
		Desktop.getDesktop().browse(new URI("http://www.youtube.com/mrCookieSlimeYT"));
	}
	
	public static void openVersionManager() throws IOException {
		VersionManager.main(null);
		frame.main.setVisible(false);
	}

}
