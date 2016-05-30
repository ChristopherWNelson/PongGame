
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PowerUps {
	int x;
	int y;
	int size = 10;
	int width = 10;
	int height = 40;
	int timer = 0;
	int maxTime;
	int pick = 0;

	boolean picked = false;

	Random rand = new Random();

	boolean clear = false;
	boolean displaying = false;

	boolean displayLong = false; // power ups/degrades

	boolean displayShoot = false;
	boolean displayMulti = false;

	boolean displayBallSpeedUp = false;
	boolean displayBallSpeedDown = false;

	boolean displayAiShort = false;

	boolean displayAiSpeedDown = false;

	boolean safetyNet = false;

	boolean displaySpeedUp = false;

	// acess

	Rectangle boundingBox;

	public PowerUps(int maxTime) {
		this.maxTime = maxTime;
		boundingBox = new Rectangle(x, y, height/2, height/2); // hit box
		boundingBox.setBounds(x, y, height/2, height/2);

	}

	public void does(Game game, int find) {
		if (find == 1) { // long
			if (game.player.height == game.player.heightStart) {
				game.player.height = game.player.powerHeight;
			}
			if (game.player.height == game.player.powerHeight2) {
				game.player.height = game.player.heightStart;
			}
			if(game.sound){
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
						"music/412363_SOUNDDOGS_ru.wav"));
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
			} }
		}
		if (find == 2) { // shoot
			game.pellet.shooting = true;
			game.pellet.shots = game.pellet.maxShots;
			if(game.sound){
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
						"music/Cocking Gun-SoundBible.com-327068561.wav"));
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
			} }
		}
		if (find == 3) { // multi
			game.balls =true;
			game.ball1 = new Ball(game.player.x, game.player.y, true,true,true);
			game.ball2 = new Ball(game.player.x, game.player.y, true,true,false);
			if(game.sound){
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
						"music/two-low-hits.wav"));
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
			} }
		}
		if (find == 4) { // ball speed up
			if (game.ball.speed == game.ball.speedStart) {
				game.ball.speed = game.ball.powerSpeed;
			}
			if (game.ball.speed == game.ball.powerSpeed2) {
				game.ball.speed = game.ball.speedStart;
			}
			if(game.sound){
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
						"music/powerup.wav"));
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
			} }
		}
		if (find == 5) { // Ball speed down
			if (game.ball.speed == game.ball.speedStart) {
				game.ball.speed = game.ball.powerSpeed2;
			}
			if (game.ball.speed == game.ball.powerSpeed) {
				game.ball.speed = game.ball.speedStart;
			}
			if(game.sound){
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
						"music/power-down.wav"));
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
			} }
			
		}

		if (find == 6) { // ai short
			if (game.ai.height == game.ai.heightStart) {
				game.ai.height = game.ai.powerHeight2;
			}
			if (game.ai.height == game.ai.powerHeight) {
				game.ai.height = game.ai.heightStart;
			}
			if(game.sound){
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
						"music/Cartoon_Shrink_Sound_Effect.wav"));
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
		}

		if (find == 7) { // ai slow
			if (game.ai.speed == game.ai.speedStart) {
				game.ai.speed = game.ai.powerSpeed2;
			}
			if (game.ai.speed == game.ai.powerSpeed) {
				game.ai.speed = game.ai.speedStart;
			}
			if(game.sound){
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
						"music/Slow_Motion_Warp-CouchMango-1259869864.wav"));
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
		}
		if (find == 8) { // shield
			game.player.shield = true;
			if(game.sound){
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
						"music/wub-wub-wub.wav"));
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
		}
		if (find == 9) {// player fast
			if (game.player.speed == game.player.speedStart) {
				game.player.speed = game.player.powerSpeed;
			}
			if (game.player.speed == game.player.powerSpeed2) {
				game.player.speed = game.player.speedStart;
			}
			if(game.sound){
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
						"music/potion-drink-regen.wav"));
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
			} }
		}

		else {

		}
	}
	
	

	public void update(Game game) {
		boundingBox.setBounds(x, y, height/2, height/2);
		if (clear) { // put all display's to false
			displayLong = false;
			displayShoot = false;
			displayMulti = false;
			displayBallSpeedUp = false;
			displayBallSpeedDown = false;
			displayAiShort = false;
			displayAiSpeedDown = false;
			safetyNet = false;
			displaySpeedUp = false;

			clear = false;
		}

		if (!displaying) { // if not displaying count then pick a random upgrade
							// and spot to put it
			if (timer == maxTime) {
				while (!picked) {
					if (Game.player.keys) {
						pick = rand.nextInt(9) + 1;
						// pick =2;			//tests
					} else {
						pick = rand.nextInt(8) + 1;
						// pick = 2;		//tests
					}
					if (pick == 1 && game.playerLength) {
						displayLong = true;
						picked = true;
					}
					if (pick == 2 && game.gun) {
						displayShoot = true;
						picked = true;

					}
					if (pick == 3 && game.Multi) {
						displayMulti = true;
						picked = true;

					}
					if (pick == 4 && game.ballSpeed) {
						displayBallSpeedUp = true;
						picked = true;

					}
					if (pick == 5 && game.ballSpeed) {
						displayBallSpeedDown = true;
						picked = true;

					}
					if (pick == 6 && game.playerLength) {
						displayAiShort = true;
						picked = true;

					}
					if (pick == 7 && game.playerSpeed) {
						displayAiSpeedDown = true;
						picked = true;

					}
					if (pick == 8 && game.net) {
						safetyNet = true;
						picked = true;

					}
					if (pick == 9 && game.playerSpeed) {
						displaySpeedUp = true;
						picked = true;

					}
				}
				x = rand.nextInt(Game.WIDTH / 4 - 30) + 10;
				y = rand.nextInt(Game.HEIGHT - height) + 10;
				timer = 0;
				displaying = true;
				picked = false;
			}
			timer++;
		}
	}

	public void render(Graphics g) { // what each upgarde will look like on the
										// board
		if (displaying) {
			if (displayLong) {
				g.setColor(Color.RED);
				g.fillRect(x, y, 3, height / 2 + 2);
				g.fillRect(x - height / 4, y + height / 4, height / 2 + 2, 3); // red
																				// plus
			}
			if (displayMulti) { // yellow ball
				g.setColor(Color.DARK_GRAY);
				g.fillOval(x, y, size, size);
			}
			if (displayShoot) { // white ball
				g.setColor(Color.WHITE);
				g.fillOval(x, y, size, size);
			}
			if (displayBallSpeedUp) { // green ball
				g.setColor(Color.GREEN);
				g.fillOval(x, y, size, size);
			}
			if (displayBallSpeedDown) { // orange ball
				g.setColor(Color.ORANGE);
				g.fillOval(x, y, size, size);
			}
			if (displayAiShort) { // blue minus
				g.setColor(Color.BLUE);
				g.fillRect(x, y, height / 2, 3);
			}
			if (displayAiSpeedDown) { // cyan minus
				g.setColor(Color.CYAN);
				g.fillRect(x, y, height / 2, 3);
			}
			if (safetyNet) {
				g.setColor(Color.RED);
				g.fillRect(x, y, 2, height / 2);
				g.fillRect(x + 7, y, 2, height / 2);
			}
			if (displaySpeedUp) {
				g.setColor(Color.MAGENTA);
				g.fillRect(x, y, 3, height / 2 + 2);
				g.fillRect(x - height / 4, y + height / 4, height / 2 + 2, 3);
			}
		}
	}
}
