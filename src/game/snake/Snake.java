package game.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import game.Direction;
import game.Food;
import main.Parameters;

/**
 * The snake which is controlled by the player.
 * 
 * @author Gage Davidson
 */
public class Snake {
	
	private ArrayList<Segment> segs;
	// holds number of segments the snake is queued to grow for
	private int growthQueue;
	
	public Snake() {
		segs = new ArrayList<>();
		
		for (int i = 0; i < Parameters.SNAKE_GROWTH_FACTOR; ++i)
			segs.add(new Segment(-i, 0, Direction.RIGHT));
	}
	
	/**
	 * Moves all the segments of the snake based on their direction.
	 */
	public void move() {
		Segment tail = null;
		if (growthQueue > 0)
			tail = segs.get(segs.size() - 1).copy();
		
		for (Segment seg : segs)
			seg.move();
		
		for (int i = segs.size() - 1; i > 0; --i)
			segs.get(i).setDir( segs.get(i - 1).getDir() );
		
		if (tail != null) {
			segs.add(tail);
			--growthQueue;
		}
	}
	
	/**
	 * @return true if the snake is out of bounds
	 */
	public boolean isOutOfBounds() {
		Segment head = segs.get(0);
		
		return head.getX() < 0 || head.getX() >= Parameters.GRID_SIZE ||
				head.getY() < 0 || head.getY() >= Parameters.GRID_SIZE;
	}
	
	/**
	 * Determines if the snake is colliding with itself. That is,
	 * if the head has hit any other part of the snake.
	 * @return true if the snake is colliding with itself
	 */
	public boolean isColliding() {
		Segment head = segs.get(0);
		for (int i = 1; i < segs.size(); ++i) {
			Segment seg = segs.get(i);
			
			if (seg.getX() == head.getX() && seg.getY() == head.getY())
				return true;
		}
		
		return false;
	}
	
	/**
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return true if the snake occupies the given grid space
	 */
	public boolean occupiesSpace(int x, int y) {
		for (Segment seg : segs)
			if (seg.getX() == x && seg.getY() == y)
				return true;
		
		return false;
	}
	
	/**
	 * Attempts to eat the food, growing if it does.
	 * @param food game food
	 * @return true if the snake ate the food
	 */
	public boolean tryEat(Food food) {
		if (canEat(food)) {
			growthQueue += Parameters.SNAKE_GROWTH_FACTOR;
			return true;
		}
		
		return false;
	}
	
	/**
	 * @param food game food
	 * @return true if the snake can eat the food, which is when
	 * the head occupies the same space as the food
	 */
	private boolean canEat(Food food) {
		Segment head = segs.get(0);
		
		return head.getX() == food.getX() && head.getY() == food.getY();
	}
	
	/**
	 * Draws the snake.
	 * @param g Graphics to use
	 */
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		segs.get(0).draw(g);
		
		g.setColor(Color.GREEN);
		for (int i = 1; i < segs.size(); ++i)
			segs.get(i).draw(g);
	}
	
	/**
	 * @return length of the snake
	 */
	public int size() {
		return segs.size();
	}
	
	/**
	 * Sets the direction of the snake, which is the direction of the head.
	 * @param dir new direction
	 */
	public void setDirection(Direction dir) {
		segs.get(0).setDir(dir);
	}
	
	/**
	 * @return direction of the snake, which is the direction of the head
	 */
	public Direction direction() {
		return segs.get(0).getDir();
	}
}
