package main;

import java.awt.Font;

/**
 * This class holds static program parameters and handles setting
 * the game parameters based on command line input, or using default values.
 * 
 * @author Gage Davidson
 */
public class Parameters {
	
	// ADD PARTY MODE WOOOO
	
	public static final int GRID_SIZE = 20;
	public static final int SNAKE_GROWTH_FACTOR = 5;
	public static final int SEGMENT_DRAW_SIZE = 30;
	public static final int DISPLAY_SIZE = GRID_SIZE * SEGMENT_DRAW_SIZE;
	public static final String DISPLAY_TITLE = "Snake";
	public static final Font GAME_FONT = new Font("Consolas", Font.BOLD, 60);
	
	/**
	 * Program parameters which aren't changed.
	 */
	private static int difficulty, displayFPS;
	
	private static final int DEFAULT_DIFFICULTY = 3; // 1|6fps 2|8fps 3|10fps 4|12fps 5|14fps
	
	/**
	 * Cannot be instantiated.
	 */
	private Parameters() {
	}
	
	public static int getDisplayFPS() { return displayFPS; }
	public static int getDifficulty() { return difficulty; }
	
	/**
	 * Initializes all the program parameters
	 * @param args command line arguments
	 */
	static void setParameters(String[] args) {
		if (args.length > 0 && args[0].equals("-help"))
			printHelp();
		
		setDifficultyAndFPS(args);
	}
	
	/**
	 * Prints usage parameters and terminates.
	 */
	private static void printHelp() {
		System.out.println("Optionally use the following flags:");
		System.out.println("-d <difficulty>		(an integer 1-5 inclusive)");
		System.out.println("Example usage: java -jar Snake.jar -d 3");
		
		System.exit(0);
	}
	
	/**
	 * Searches command line arguments for "-d" flag. Then sets the
	 * difficulty and display FPS.
	 * @param args command line arguments
	 */
	private static void setDifficultyAndFPS(String[] args) {
		for (int i = 0; i < args.length; ++i) {
			if (args[i].equals("-d")) {
				try {
					difficulty = Integer.parseInt(args[i + 1]);
				} catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
					invalidUsage();
				}
				
				break;
			}
		}
		
		if (difficulty == 0)
			difficulty = DEFAULT_DIFFICULTY;
		else if (difficulty < 1 || difficulty > 5)
			invalidUsage();
		
		displayFPS = getDisplayFPS(difficulty);
	}
	
	/**
	 * @param difficulty difficulty selected
	 * @return display FPS based on difficulty
	 */
	private static int getDisplayFPS(int difficulty) {
		switch (difficulty) {
		case 1: return 6;
		case 2: return 8;
		case 3: return 10;
		case 4: return 12;
		case 5: return 14;
		}
		
		return 0;
	}
	
	/**
	 * Informs the user the usage was invalid and exits the program.
	 */
	private static void invalidUsage() {
		System.out.println("Invalid usage. Use -help for help.");
		System.exit(-1);
	}
}
