package me.mrCookieSlime.Slimecraft.Setup;

import java.io.File;

public class Folders {
	
	private static File slimecraft = new File(System.getenv("APPDATA") + "\\.Slimecraft");
	private static File Images = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Images");
	private static File Sounds = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Sounds");
	private static File Natives = new File(System.getenv("APPDATA") + "\\.Slimecraft\\natives");
	private static File Skins = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Skins");
	private static File mods = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Mods");
	private static File mods_installed = new File(System.getenv("APPDATA") + "\\.minecraft\\mods");
	private static File Heads = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Heads");
	private static File Modpacks = new File(System.getenv("APPDATA") + "\\.Slimecraft\\Modpacks");
	
	private static File[] folders = {slimecraft, Images, Sounds, Natives, Skins, mods, mods_installed, Heads, Modpacks};
	
	public static void create() {
		for (int i = 0; i <folders.length; i++) {
			if (!folders[i].exists()) {
				folders[i].mkdir();
			}
		}
	}
	
	
}
