package me.mrCookieSlime.Slimecraft.Utils;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundHandler {
	
	public static synchronized void playSound(final String path) {
		  new Thread(new Runnable() {
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
		        clip.open(inputStream);
		        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		        gainControl.setValue(-12.0f);
		        clip.start(); 
		      } catch (Exception e) {
		      }
		    }
		  }).start();
		}

}
