
import java.awt.*;

public class PlayerPaddle {
	int x, prevX;
	int y, prevY;
	int width = 10;
	int heightStart = 60; // start height
	int height = heightStart;
	int powerHeight = 120; // long height
	int powerHeight2 = 30; // short height
	int speedStart = 4; // stats
	int speed = speedStart;
	int powerSpeed = 6; // fast speed
	int powerSpeed2 = 3; // slow speed

	boolean shield = false;
	static boolean keys;

	Rectangle boundingBox;

	boolean goingUp = false;
	boolean goingDown = false;
	boolean goingLeft = false;
	boolean goingRight = false;

	public PlayerPaddle(int x, int y) {
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
		boundingBox.setBounds(x, y, width, height); // hit box
		prevY = y;
		prevX = x;
		if(keys) { // will take info from keys
			if (goingUp && y >= 0) { // top
				prevY = y;
				y -= speed;
			} else if (goingDown && y <= game.getHeight() - height) { // bottom
				prevY = y;
				y += speed;
			}
			if (goingLeft && x >= width / 2) {
				prevX = x;
				x -= speed;
			} else if (goingRight && x <= game.getWidth() / 4 - width - 3) {
				prevX = x;
				x += speed;
			}
		}
		else {
			if (x > game.InpHand.preX - 20 && x < game.InpHand.preX + 20) {
				goingRight = false;
				goingLeft = false;
			}
			if (y > game.InpHand.preY - height && y < game.InpHand.preY) {
				goingDown = false;
				goingUp = false;
			}
			
			if (goingUp && y >= 0) { // top
				prevY = y;
				y -= speed;
				//goingUp = false;
			} else if (goingDown && y <= game.getHeight() - height) { // bottom
				prevY = y;
				y += speed;
			//	goingDown = false;
			}
			if (goingLeft && x >= width / 2) {
				prevX = x;
				x -= speed;
				//goingLeft = false;
			} else if (goingRight && x <= game.getWidth() / 4 - width - 3) {
				prevX = x;
				x += speed;
				//goingRight = false;
			}
			

			
		}
		paddleTouch(game);
	}

//	public void update(Game game) {
//		boundingBox.setBounds(x, y, width, height); // hit box
//		if (keys) { // will take info from keys
//			if (goingUp && y >= 0) { // top
//				prevY = y;
//				y -= speed;
//			} else if (goingDown && y <= game.getHeight() - height) { // bottom
//				prevY = y;
//				y += speed;
//			}
//			if (goingLeft && x >= width / 2) {
//				prevX = x;
//				x -= speed;
//			} else if (goingRight && x <= game.getWidth() / 4 - width - 3) {
//				prevX = x;
//				x += speed;
//			}
//		}
//		if(!keys){
//			if (x > game.InpHand.preX - 20 && x < game.InpHand.preX + 20) {
//				goingRight = false;
//				goingLeft = false;
//			}
//			if (y > game.InpHand.preY - height && y < game.InpHand.preY) {
//				goingDown = false;
//				goingUp = false;
//			}
//			
//			if (goingUp && y >= 0) { // top
//				prevY = y;
//				y -= speed;
//				//goingUp = false;
//			} else if (goingDown && y <= game.getHeight() - height) { // bottom
//				prevY = y;
//				y += speed;
//			//	goingDown = false;
//			}
//			if (goingLeft && x >= width / 2) {
//				prevX = x;
//				x -= speed;
//				//goingLeft = false;
//			} else if (goingRight && x <= game.getWidth() / 4 - width - 3) {
//				prevX = x;
//				x += speed;
//				//goingRight = false;
//			}
//			
//
//		}
//		paddleTouch(game);
//	}
	
	
	
	private void paddleTouch(Game game) {
		if (boundingBox.intersects(game.power.boundingBox)) {
			game.power.displaying = false;
			game.power.does(game, game.power.pick);
			game.power.pick = 0;
			game.power.clear = true;
		}
	}

	public void render(Graphics g) { // what to draw
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}

}
