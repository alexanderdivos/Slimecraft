package me.mrCookieSlime.Slimecraft.Utils;

public class OS {
	
	public static boolean isWindows() {
		if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
			return true;
		}
		else {
			return false;
		}
	}

}
