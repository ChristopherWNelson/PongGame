
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PelletAI {
	int x;
	int y;
	int width = 10;
	int height = 10;
	int speed = 10;
	Rectangle boundingBox;

	boolean shot = false; // if going
	int maxShots = 6;
	int shots;
	boolean shooting = false;

	public PelletAI() {
		boundingBox = new Rectangle(x, y, width, height); // hit box
		boundingBox.setBounds(x, y, width, height);
	}

	public void pelletCollide(Game game) {
		if (boundingBox.intersects(game.ball.boundingBox)) { // bounce if hit
			game.ball.vx = -game.ball.speed;
			shot = false;
		} 
		else if (x + width <= 0) {
			shot = false;
		}
	}

	public void update(Game game) {
		boundingBox.setBounds(x, y, width, height);
		if (shooting) {
			if (shot && shots > 0) {
				x -= speed;
				pelletCollide(game);
			}
		}
	}

	public void render(Graphics g) {
		if (shooting) {
			if (shot && shots > 0) {
				g.setColor(Color.WHITE);
				g.fillRect(x, y, width, height);
			}
		}
	}
}
