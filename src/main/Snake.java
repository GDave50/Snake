package main;

import java.awt.Graphics;

import display.Display;
import game.Game;

/**
 * Snake is a game in which the player controls the travel
 * direction of the head of a snake while the body follows it.
 * The player must eat food to make the snake grow. If the snake
 * runs into itself or goes out of bounds, the game is over.
 * 
 * @author Gage Davidson
 */
class Snake {
	
	/**
	 * @param args command-line arguments (see Parameters.java)
	 */
	public static void main(String[] args) {
		Parameters.setParameters(args);
		
		Game game = new Game();
		
		Display display = new Display(Parameters.DISPLAY_SIZE, Parameters.DISPLAY_SIZE,
				Parameters.DISPLAY_TITLE, false, Parameters.getDisplayFPS()) {
			
			private final Graphics g = super.getDrawGraphics();
			
			@Override
			public void draw() {
				game.draw(g);
			}
			
			@Override
			public void tick() {
				game.tick();
			}
		};
		
		display.addKeyListener(game);
		
		display.run();
	}
}
