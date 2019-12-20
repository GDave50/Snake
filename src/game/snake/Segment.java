package game.snake;

import java.awt.Color;
import java.awt.Graphics;

import game.Direction;
import main.Parameters;

/**
 * A single segment of the snake.
 * 
 * @author Gage Davidson
 */
class Segment {
	
	private int x, y;
	private Direction dir;
	
	/**
	 * @param x starting x-coordinate
	 * @param y starting y-coordinate
	 * @param dir starting direction
	 */
	Segment(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	/**
	 * Moves this segment based on the direction it is facing.
	 */
	void move() {
		switch (dir) {
		case UP:     y--;  break;
		case DOWN:   y++;  break;
		case LEFT:   x--;  break;
		case RIGHT:  x++;  break;
		}
	}
	
	/**
	 * Draws the segment. Assumes Graphics color has already been set.
	 * @param g Graphics to use
	 */
	void draw(Graphics g) {
		g.fillOval(x * Parameters.SEGMENT_DRAW_SIZE, y * Parameters.SEGMENT_DRAW_SIZE,
				Parameters.SEGMENT_DRAW_SIZE, Parameters.SEGMENT_DRAW_SIZE);
		
		Color colorSave = g.getColor();
		
		// draw black outline
		g.setColor(Color.BLACK);
		g.drawOval(x * Parameters.SEGMENT_DRAW_SIZE, y * Parameters.SEGMENT_DRAW_SIZE,
				Parameters.SEGMENT_DRAW_SIZE, Parameters.SEGMENT_DRAW_SIZE);
		g.drawOval(x * Parameters.SEGMENT_DRAW_SIZE + 1, y * Parameters.SEGMENT_DRAW_SIZE + 1,
				Parameters.SEGMENT_DRAW_SIZE - 2, Parameters.SEGMENT_DRAW_SIZE - 2);
		
		g.setColor(colorSave);
	}
	
	/**
	 * @return copy of this segment
	 */
	Segment copy() {
		return new Segment(x, y, dir);
	}
	
	/**
	 * Sets the direction of this segment.
	 * @param dir new direction
	 */
	void setDir(Direction dir) {
		this.dir = dir;
	}
	
	int getX() { return x; }
	int getY() { return y; }
	Direction getDir() { return dir; }
}
