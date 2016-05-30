
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

public class PowerUpsAI {
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

	boolean displayShort = false;

	boolean displayShoot = false;
	boolean displayMulti = false;

	boolean displayBallSpeedUp = false;
	boolean displayBallSpeedDown = false;

	boolean displayAiLong = false;

	boolean displayAiSpeedUp = false;

	boolean safetyNet = false;

	boolean displaySpeedDown = false;

	Rectangle boundingBox;

	public PowerUpsAI(int maxTime) {
		this.maxTime = maxTime;
		boundingBox = new Rectangle(x, y, height/2, height/2); // hit box
		boundingBox.setBounds(x, y, height/2, height/2);

	}

	public void does(Game game, int find) {
		if (find == 1) { // short
			if (game.player.height == game.player.heightStart) {
				game.player.height = game.player.powerHeight2;
			}
			if (game.player.height == game.player.powerHeight) {
				game.player.height = game.player.heightStart;
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
		if (find == 2) { // shoot
			game.pelletAI.shooting = true;
			game.pelletAI.shots = game.pellet.maxShots;
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
		if (find == 3) { // mulit //not implemented yet
			game.balls =true;
			game.ball1 = new Ball(game.ai.x, game.ai.y, true,false,true);
			game.ball2 = new Ball(game.ai.x, game.ai.y, true,false,false);
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
		if (find == 6) { // ai long
			if (game.ai.height == game.ai.heightStart) {
				game.ai.height = game.ai.powerHeight;
			}
			if (game.ai.height == game.ai.powerHeight2) {
				game.ai.height = game.ai.heightStart;
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
		if (find == 7) { // ai fast
			if (game.ai.speed == game.ai.speedStart) {
				game.ai.speed = game.ai.powerSpeed;
			}
			if (game.ai.speed == game.ai.powerSpeed2) {
				game.ai.speed = game.ai.speedStart;
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
		if (find == 8) { // shield
			game.ai.shield = true;
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
		if (find == 9) {// player slow
			if (game.player.speed == game.player.speedStart) {
				game.player.speed = game.player.powerSpeed2;
			}
			if (game.player.speed == game.player.powerSpeed) {
				game.player.speed = game.player.speedStart;
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
		} else {

		}
	}

	public void update(Game game) {
		boundingBox.setBounds(x, y, height/2, height/2);
		if (clear) { // put all display's to false
			displayShort = false;
			displayShoot = false;
			displayMulti = false;
			displayBallSpeedUp = false;
			displayBallSpeedDown = false;
			displayAiLong = false;
			displayAiSpeedUp = false;
			safetyNet = false;
			displaySpeedDown = false;

			clear = false;
		}
		if (!displaying) { // if not displaying count then pick a random
							// upgrade
			// and spot to put it
			if (timer == maxTime) {
				while (!picked) {
					if (Game.player.keys) {
						pick = rand.nextInt(9) + 1;
						 //pick = 3;
					} else {
						pick = rand.nextInt(8) + 1;
						 //pick = 3;
					}
					if (pick == 1&& game.playerLength) {
						displayShort = true;
						picked = true;
					}
					if (pick == 2&& game.gun) {
						displayShoot = true;
						picked = true;
					}
					if (pick == 3&& game.Multi) {
						displayMulti = true;
						picked = true;
					}
					if (pick == 4&& game.ballSpeed) {
						displayBallSpeedUp = true;
						picked = true;
					}
					if (pick == 5&& game.ballSpeed) {
						displayBallSpeedDown = true;
						picked = true;
					}
					if (pick == 6&& game.playerLength) {
						displayAiLong = true;
						picked = true;
					}
					if (pick == 7 && game.playerSpeed) {
						displayAiSpeedUp = true;
						picked = true;
					}
					if (pick == 8&& game.net) {
						safetyNet = true;
						picked = true;
					}
					if (pick == 9&& game.playerSpeed) {
						displaySpeedDown = true;
						picked = true;
					}
				}
				x = rand.nextInt(game.getWidth() / 4) + 3 * game.getWidth() / 4;
				y = rand.nextInt(game.getHeight());
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
			if (displayShort) { // red minus
				g.setColor(Color.RED);
				g.fillRect(x, y, height / 2, 3);
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
			if (displayAiLong) { // blue plus
				g.setColor(Color.BLUE);
				g.fillRect(x, y, 3, height / 2 + 2);
				g.fillRect(x - height / 4, y + height / 4, height / 2 + 2, 3);
			}
			if (displayAiSpeedUp) { // cyan plus
				g.setColor(Color.CYAN);
				g.fillRect(x, y, 3, height / 2 + 2);
				g.fillRect(x - height / 4, y + height / 4, height / 2 + 2, 3);
			}
			if (safetyNet) {
				g.setColor(Color.BLUE);
				g.fillRect(x, y, 2, height / 2);
				g.fillRect(x + 7, y, 2, height / 2);
			}
			if (displaySpeedDown) { // cyan plus
				g.setColor(Color.MAGENTA);
				g.fillRect(x, y, height / 2, 3);
			}
		}
	}
}
