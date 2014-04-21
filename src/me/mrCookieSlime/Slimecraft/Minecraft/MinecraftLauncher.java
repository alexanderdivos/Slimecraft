package me.mrCookieSlime.Slimecraft.Minecraft;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MinecraftLauncher {
	
	public static Process start(String command) throws IOException {
		List<String> full = new ArrayList<String>();
		String[] split = command.split(" ");
		
		for (int i = 0; i < split.length; i++) {
			full.add(split[i]);
		}
		
		ProcessBuilder builder = new ProcessBuilder(full);
		builder.redirectErrorStream(true);
		builder.directory(new File(System.getenv("APPDATA") + "\\.minecraft"));
		
		return builder.start();
	}

}
