package game;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import game.snake.Snake;
import main.Parameters;

/**
 * Handles game logic.
 * 
 * @author Gage Davidson
 */
public class Game implements KeyListener {
	
	private final Snake snake;
	private final Food food;
	private final LinkedList<Direction> movementQueue;
	
	public Game() {
		snake = new Snake();
		
		food = new Food();
		food.placeRandom(snake);
		
		movementQueue = new LinkedList<>();
	}
	
	/**
	 * Updates the game (happens each display frame).
	 */
	public void tick() {
		synchronized (movementQueue) {
			if (! movementQueue.isEmpty())
				snake.setDirection(movementQueue.remove());
		}
		
		snake.move();
		
		if (snake.isOutOfBounds() || snake.isColliding()) {
			Sound.DIE.play();
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException ex) {
			}
			
			System.exit(0);
		}
		
		if (snake.tryEat(food)) {
			Sound.EAT.play();
			food.placeRandom(snake);
		}
	}
	
	/**
	 * Handles game input.
	 * 
	 *  ** This method is called asynchronous from tick() and draw(). **
	 * 
	 * @param dir Direction pressed
	 */
	private void input(Direction dir) {
		synchronized (movementQueue) {
			if (movementQueue.isEmpty()) {
				if (compatibleDirections(dir, snake.direction()))
					movementQueue.add(dir);
			} else if (compatibleDirections(dir, movementQueue.getLast())) {
				movementQueue.add(dir);
			}
		}
	}
	
	/**
	 * Draws the game.
	 * @param g Graphics to use
	 */
	public synchronized void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Parameters.DISPLAY_SIZE, Parameters.DISPLAY_SIZE);
		
		g.setColor(Color.BLACK);
		g.setFont(Parameters.GAME_FONT);
		FontMetrics fm = g.getFontMetrics();
		
		String scoreString = snake.size() + "";
		g.drawString(scoreString,
				Parameters.DISPLAY_SIZE / 2 - fm.stringWidth(scoreString) / 2,
				Parameters.DISPLAY_SIZE / 2 - fm.getAscent() / 2);
		
		snake.draw(g);
		food.draw(g);
	}
	
	/**
	 * Determines if two directions are "compatible". Two directions are
	 * compatible if they are not equal or opposite.
	 * @param dir1 direction 1
	 * @param dir2 direction 2
	 * @return true if the two directions are compatible
	 */
	private static boolean compatibleDirections(Direction dir1, Direction dir2) {
		if (dir1 == Direction.UP || dir1 == Direction.DOWN)
			return dir2 == Direction.LEFT || dir2 == Direction.RIGHT;
		
		// else dir1 == Direction.LEFT || dir1 == Direction.RIGHT
		return dir2 == Direction.UP || dir2 == Direction.DOWN;
	}
	
	@Override
	public void keyPressed(KeyEvent evt) {
		switch (evt.getKeyCode()) {
		case KeyEvent.VK_UP: input(Direction.UP); break;
		case KeyEvent.VK_DOWN: input(Direction.DOWN); break;
		case KeyEvent.VK_LEFT: input(Direction.LEFT); break;
		case KeyEvent.VK_RIGHT: input(Direction.RIGHT); break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent evt) {
	}
	
	@Override
	public void keyTyped(KeyEvent evt) {
	}
}
