package me.mrCookieSlime.Slimecraft.Filters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class FolderFilter extends FileFilter {
	
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		else {
		    return false;
		}
	}
	
	public String getDescription() {
		return("All folders");
	}

}
