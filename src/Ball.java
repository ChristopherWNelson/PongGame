import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Ball {
	int x, y;
	int size = 16;
	int speedStart = 3; // start speed
	int speed = speedStart; // stats
	double vx, vy;
	int powerSpeed = 5; // fast speed
	int powerSpeed2 = 2; // slow speed
	double spin =  0.0;		// spin of ball
	
	boolean caught = false;
	boolean playCaught = false;
	boolean aiCaught = false;
	boolean fake;
	boolean displaying = true;
	boolean right;
	boolean up;

	Rectangle boundingBox;
	
	public void bonk(){
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
					"music/team_fortress_2_quotes_-Bonk_-.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();
		}
		catch (UnsupportedAudioFileException uae) {
			System.out.println(uae);
		} catch (IOException ioe) {
			System.out.println(ioe);
		} catch (LineUnavailableException lua) {
			System.out.println(lua);
		} 
	}

	public void Thud(){
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
					"music/boing-raw-aif-7967.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();
		}
		catch (UnsupportedAudioFileException uae) {
			System.out.println(uae);
		} catch (IOException ioe) {
			System.out.println(ioe);
		} catch (LineUnavailableException lua) {
			System.out.println(lua);
		} 
	}
	public void win(){
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
					"music/90754_WINNER.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();
		}
		catch (UnsupportedAudioFileException uae) {
			System.out.println(uae);
		} catch (IOException ioe) {
			System.out.println(ioe);
		} catch (LineUnavailableException lua) {
			System.out.println(lua);
		} 
	}
	
	public Ball(int x, int y, boolean fake, boolean right, boolean up) {
		this.x = x;
		this.y = y;
		this.fake = fake;
		this.right = right;
		if (right) {
			vx = speed;
		} else {
			vx = -speed;
		}
		if (up) {
			vy = speed;
		} else {
			vy = -speed;
		}
		boundingBox = new Rectangle(x, y, size, size); // hit box
		boundingBox.setBounds(x, y, size, size);
	}
	
	public void update(Game game) {
		if (displaying) {
			boundingBox.setBounds(x, y, size, size);
				
			if (!caught) {
				if (fake) {
					if (x <= 0) {
						game.p2Score++; // if touch left end p2 score
						displaying = false;
						win();
					} else if (x + size >= game.getWidth()) {
						game.p1Score++; // touch right side p1 socre
						displaying = false;
						if(game.sound)
							win();
					} else if (x + size <= game.WIDTH / 30
							&& game.player.shield) { // shield
						displaying = false;
						game.player.shield = false;
						if(game.sound)
							bonk();
					} else if (x + size >= 96 * game.getWidth() / 100
							&& game.ai.shield) { // shield
						displaying = false;
						game.ai.shield = false;
						if(game.sound)
							bonk();
					}
				} else {
					if (x <= 0) {
						game.p2Score++; // if touch left end p2 score
						// vx = speed;
						caught = true;
						playCaught = true;
					} else if (x + size >= game.getWidth()) {
						game.p1Score++; // touch right side p1 socre
						caught = true;
						aiCaught = true;
						if(game.sound)
							win();
					} else if (x + size <= game.WIDTH / 30
							&& game.player.shield) { // shield
						vx = speed;
						game.player.shield = false;
						if(game.sound)
							win();
					} else if (x + size >= 96 * game.getWidth() / 100
							&& game.ai.shield) { // shield
						vx = -speed;
						game.ai.shield = false;
						if(game.sound)
							win();						
					}
				}
				if (y <= 0) { // hit top
					spin = 0.0;
					vy = -vy;
					y = size/2;
					if(game.sound)
						Thud();
				} else if (y + size >= game.getHeight()) { // hit bottom
					spin = 0.0;
					vy = -vy;
					if(game.sound)
						Thud();
				}
				x += vx;				// update ball x location
				if( spin > 0){			// update ball y location w/ spin
					vy = vy+spin;
					y -= vy;
				}else if(spin < 0){
					vy = vy-spin;
					y += vy;
				}else{
					y += vy;
				}
				paddleCollide(game);
			} else if(caught) {
				if (playCaught) {
					x = game.player.x+13;
					y = game.player.y + game.player.width;
					vx = speed;
					vy = speed;
				} else{
					x = game.ai.x-19;
					y = game.ai.y + game.ai.width;
					vx = -speed;
					vy = speed;
					if (!game.ai.isTwoPlayer){
						caught = false;
						aiCaught = false;
					}
				}
			}
		}
	}

	private void paddleCollide(Game game) { 
		if (displaying) {
			if (boundingBox.intersects(game.player.boundingBox)) { // bounce if
					vx = speed + game.player.xVel();// hit// player
					spin = (game.player.yVel()/100f);					
//				System.out.println("xVel"+ game.player.xVel());
//				System.out.println("yVel"+ game.player.yVel());
//				System.out.println("spin is "+ spin);
				if (game.sound)
					bonk();	
			} else if (boundingBox.intersects(game.ai.boundingBox)) { // bounce
				vx = -speed - game.ai.xVel();// hit ai
				spin = (game.ai.yVel()/100f);
				if (game.sound)
					bonk();	
			}
		}
	}

	public void render(Graphics g) {
		if (displaying) {
			if (!fake) {
				g.setColor(Color.YELLOW); // waht to draw
				g.fillOval(x, y + Game.player.width, size, size);
//				System.out.println(x);
				//System.out.println(y);
			} else {
				g.setColor(Color.DARK_GRAY); // waht to draw
				g.fillOval(x, y + Game.player.width, size, size);

			}
		}
	}
}
