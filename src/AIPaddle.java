
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class AIPaddle {
	int x, prevX;
	int y, prevY;
	int width = 10;
	int heightStart = 60; // start height
	int height = heightStart;
	int powerHeight = 120; // long height
	int powerHeight2 = 30; // short height
	int speedStart = 4	; // stats
	int speed = speedStart;
	int powerSpeed = 6; // fast speed
	int powerSpeed2 = 3; // slow speed

	boolean isTwoPlayer = false; // if two player
	boolean shield;

	Rectangle boundingBox;

	boolean goingUp = false;
	boolean goingDown = false;
	boolean goingLeft = false;
	boolean goingRight = false;
	
	public AIPaddle(int x, int y) {
		this.x = x;
		this.y = y;
		boundingBox = new Rectangle(x, y, width, height); // hit box
		boundingBox.setBounds(x, y, width, height);

	}
	
	public int yVel(){
		return y - prevY;
	}

	public int xVel(){
		return x - prevX;
	}

	public void update(Game game) {
		boundingBox.setBounds(x, y, width, height);
		prevY = y;
		prevX = x;
		if (!isTwoPlayer) { // will track ball
			aiGame(game);
		} else { // will take info from keys
			if (goingUp && y >= 0) { // top
				y -= speed;
			} else if (goingDown && y <= game.getHeight() - height) { // bottom
				y += speed;
			}
			if (goingLeft && x >= 3 * game.getWidth() / 4 + 3) {
				x -= speed;
			} else if (goingRight && x <= game.getWidth() - width - 5) {
				x += speed;
			}
		}
		paddleTouch(game);
	}

	private void aiGame(Game game) {
		if (game.ball.vx > 0) {
			if (game.ball.y < y + height && y >= 0) {
				prevY = y;
				y -= speed;
			} else if (game.ball.y > y && y + height <= game.getHeight()) {
				prevY = y;
				y += speed;
			}
			if (game.ball.x < x && x >= 3 * game.getWidth() / 4 + 3) {
				prevX = x;
				x -= speed;
			} else if (game.ball.x > x && x <= game.getWidth() + width) {
				prevX = x;
				x += speed;
			}
		} else if (game.balls && game.ball1.vx > 0 && game.ball1.displaying) {
			if (game.ball1.y < y + height && y >= 0) {
				prevY = y;
				y -= speed;
			} else if (game.ball1.y > y && y + height <= game.getHeight()) {
				prevY = y;
				y += speed;
			}
			if (game.ball1.x < x && x >= 3 * game.getWidth() / 4 + 3) {
				prevX = x;
				x -= speed;
			} else if (game.ball1.x > x && x <= game.getWidth() + width) {
				prevX = x;
				x += speed;
			}
		} else if (game.balls && game.ball2.vx > 0 && game.ball2.displaying) {
			if (game.ball2.y < y + height && y >= 0) {
				prevY = y;
				y -= speed;
			} else if (game.ball2.y > y && y + height <= game.getHeight()) {
				prevY = y;
				y += speed;
			}
			if (game.ball2.x < x && x >= 3 * game.getWidth() / 4 + 3) {
				prevX = x;
				x -= speed;
			} else if (game.ball2.x > x && x <= game.getWidth() + width) {
				prevX = x;
				x += speed;
			}
		} else if (game.ball.vx < 0) {
			if (game.powerAI.displaying) {
				if (game.powerAI.y < y + height && y >= 0) {
					y -= speed;
				} else if (game.powerAI.y > y && y + height <= game.getHeight()) {
					y += speed;
				}
				if (game.powerAI.x < x && x >= 0) {
					x -= speed;
				} else if (game.powerAI.x > x && x <= game.getWidth() + width) {
					x += speed;
				}
			}
		}
	}

	private void paddleTouch(Game game) {
		if (boundingBox.intersects(game.powerAI.boundingBox)) { 
			game.powerAI.displaying = false;
			game.powerAI.does(game, game.powerAI.pick);
			game.powerAI.pick = 0;
			game.powerAI.clear = true;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}
}
