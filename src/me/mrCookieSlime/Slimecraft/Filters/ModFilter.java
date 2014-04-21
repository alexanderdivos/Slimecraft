package me.mrCookieSlime.Slimecraft.Filters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ModFilter extends FileFilter {

	public boolean accept(File f) {
		
		if (f.isDirectory()) {
			return true;
		}
		else {
			String name = f.getName().toLowerCase();
			
			if (name.endsWith(".jar")) {
				return true;
			}
			else if (name.endsWith(".zip")) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public String getDescription() {
		return("*.jar / *.zip");
	}
	
}
