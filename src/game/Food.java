package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import game.snake.Snake;
import main.Parameters;

/**
 * A piece of food.
 * 
 * @author Gage Davidson
 */
public class Food {
	
	private int x, y;
	private final Random random;
	
	Food() {
		random = new Random();
	}
	
	/**
	 * Randomly places the food in a grid space that is not occupied
	 * by the snake.
	 * @param snake game snake
	 */
	void placeRandom(Snake snake) {
		for (;;) {
			int xRand = random.nextInt(Parameters.GRID_SIZE);
			int yRand = random.nextInt(Parameters.GRID_SIZE);
			
			if (! snake.occupiesSpace(xRand, yRand)) {
				x = xRand;
				y = yRand;
				break;
			}
		}
	}
	
	/**
	 * Draws the segment. Assumes Graphics color has already been set.
	 * @param g Graphics to use
	 */
	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x * Parameters.SEGMENT_DRAW_SIZE, y * Parameters.SEGMENT_DRAW_SIZE,
				Parameters.SEGMENT_DRAW_SIZE, Parameters.SEGMENT_DRAW_SIZE);
		
		// draw black outline
		g.setColor(Color.BLACK);
		g.drawOval(x * Parameters.SEGMENT_DRAW_SIZE, y * Parameters.SEGMENT_DRAW_SIZE,
				Parameters.SEGMENT_DRAW_SIZE, Parameters.SEGMENT_DRAW_SIZE);
		g.drawOval(x * Parameters.SEGMENT_DRAW_SIZE + 1, y * Parameters.SEGMENT_DRAW_SIZE + 1,
				Parameters.SEGMENT_DRAW_SIZE - 2, Parameters.SEGMENT_DRAW_SIZE - 2);
	}
	
	public int getX() { return x; }
	public int getY() { return y; }
}
