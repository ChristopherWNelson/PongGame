import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class InputHandler implements MouseMotionListener, MouseListener,
		KeyListener {

	boolean sound = false;
	int preX = 0;
	int preY = 0;

	int mouseX, mouseY;

	public int yVel() {
		return mouseY - preY;
	}

	public int xVel() {
		return mouseX - preX;
	}

	public InputHandler(Game game) {
		game.addKeyListener(this); // listens for key and mouse
		game.addMouseMotionListener(this);
		game.addMouseListener(this);
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode(); // which key is pressed

		if (keyCode == KeyEvent.VK_SPACE) { // press space and will make a
			if (Game.pellet.shots == 0) {
				Game.pellet.shooting = false;
				Game.pellet.shot = false;
			}
			if (Game.pellet.shooting) {
				Game.pellet.x = Game.player.x + 5;
				Game.pellet.y = Game.player.y + Game.player.height / 2;
				Game.pellet.shots--;
				Game.pellet.shot = true;
				if (Game.sound) {
					try {
						AudioInputStream audio = AudioSystem
								.getAudioInputStream(new File(
										"music/Skorpion-Kibblesbob-1109158827.wav"));
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
		}

		if (keyCode == KeyEvent.VK_CONTROL) { // press space and will make a
			if (Game.pelletAI.shooting) {
				Game.pelletAI.x = Game.ai.x + 5;
				Game.pelletAI.y = Game.ai.y + Game.ai.height / 2;
				Game.pelletAI.shots--;
				Game.pelletAI.shot = true;
			}
		}
		if (keyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		if (keyCode == KeyEvent.VK_P) {
			if (Game.pause == true) {
				Game.pause = false;
			} else if (Game.pause == false) {
				Game.pause = true;
			}
		}
		if (keyCode == KeyEvent.VK_SHIFT) {
			Game.ball.caught = false;
			Game.ball.aiCaught = false;
			Game.ball.playCaught = false;
		}
		if (keyCode == KeyEvent.VK_UP) {
			Game.ai.goingUp = true;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			Game.ai.goingDown = true;
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			Game.ai.goingLeft = true;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			Game.ai.goingRight = true;
		}
		if (keyCode == KeyEvent.VK_W) {
			Game.player.goingUp = true;
		}
		if (keyCode == KeyEvent.VK_S) {
			Game.player.goingDown = true;
		}
		if (keyCode == KeyEvent.VK_A) {
			Game.player.goingLeft = true;
		}
		if (keyCode == KeyEvent.VK_D) {
			Game.player.goingRight = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_UP) {
			Game.ai.goingUp = false;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			Game.ai.goingDown = false;
		}
		if (keyCode == KeyEvent.VK_LEFT) {
			Game.ai.goingLeft = false;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			Game.ai.goingRight = false;
		}
		if (keyCode == KeyEvent.VK_W) {
			Game.player.goingUp = false;
		}
		if (keyCode == KeyEvent.VK_S) {
			Game.player.goingDown = false;
		}
		if (keyCode == KeyEvent.VK_A) {
			Game.player.goingLeft = false;
		}
		if (keyCode == KeyEvent.VK_D) {
			Game.player.goingRight = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void mouseMoved(MouseEvent e) { // algorithm for tracking mouse very
											// weird
		if (!Game.player.keys) {
			if (!Game.ai.isTwoPlayer) {
				preY = mouseY;
				mouseY = e.getY();
				if (mouseY + Game.player.height / 2 > Game.HEIGHT + 10) { // up
																			// down
					mouseY = Game.HEIGHT + 10 - Game.player.height / 2;
				}
				if (mouseY < Game.player.height / 2) {
					mouseY = Game.player.height / 2;
				}

				Game.player.y = mouseY - Game.player.height / 2;

				preX = mouseX;
				mouseX = e.getX();
				if (mouseX + Game.player.width / 2 > Game.WIDTH / 4 + 2) { // left
					// right
					mouseX = Game.WIDTH / 4 + 2 - Game.player.width / 2;
				}
				if (mouseX < Game.player.width / 2) {
					mouseX = Game.player.width / 2;
				}

				Game.player.x = mouseX - Game.player.width / 2;
			} 
			else {
				int mouseY = e.getY();
				int mouseX = e.getX();

				right(mouseX);
				up(mouseY);

				if (Game.player.x > preX - 20 && Game.player.x < preX + 20) {
					Game.player.goingRight = false;
					Game.player.goingLeft = false;
				}
				if (Game.player.y > preY - Game.player.height&& Game.player.y < preY) {
					Game.player.goingDown = false;
					Game.player.goingUp = false;
				}
			}
		}
	}

	public void right(int mouseX) {
		if (Game.player.x > mouseX - 20 && Game.player.x < mouseX + 20) {
			Game.player.goingRight = false;
			Game.player.goingLeft = false;
		} else if (Game.player.x > mouseX) {
			Game.player.goingLeft = true;
			Game.player.goingRight = false;
		} else if (Game.player.x < mouseX) {
			Game.player.goingRight = true;
			Game.player.goingLeft = false;
		}
		preX = mouseX;
	}

	public void up(int mouseY) {
		if (Game.player.y > mouseY - Game.player.height
				&& Game.player.y < mouseY) {
			Game.player.goingDown = false;
			Game.player.goingUp = false;
		} else if (Game.player.y > mouseY) {
			Game.player.goingUp = true;
			Game.player.goingDown = false;
		} else if (Game.player.y < mouseY) {
			Game.player.goingDown = true;
			Game.player.goingUp = false;
		}
		preY = mouseY;
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		Game.ball.caught = false;
		Game.ball.playCaught = false;

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
