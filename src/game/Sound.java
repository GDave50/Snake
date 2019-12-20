package game;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Enumerates possible game sounds.
 * 
 * @author Gage Davidson
 */
public enum Sound {
	
	EAT ("eat.wav"),
	DIE ("die.wav");
	
	private static final String PATH =
			"C:\\Users\\Gage\\Documents\\Programming\\Eclipse Workspace\\Snake\\res\\";
	
	private final sound.Sound sound;
	
	/**
	 * @param file filename for the sound
	 */
	private Sound(String file) {
		sound.Sound sound = null;
		
		try {
			sound = new sound.Sound(PATH + file);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			System.out.println("FAILED TO LOAD SOUND: " + ex.getMessage());
		}
		
		this.sound = sound;
	}
	
	/**
	 * Plays the sound.
	 */
	public void play() {
		sound.play();
	}
}
