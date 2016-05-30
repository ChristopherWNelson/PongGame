
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static long serialVersionUID = 1L;
	
	static boolean playerSpeed;
	static boolean ballSpeed;
	static boolean playerLength;
	static boolean net;
	static boolean gun;
	static boolean Multi;
	
	static boolean sound;
	static boolean player1wins;
	static boolean player2wins;
	
	public boolean balls = false;
	public boolean powerUpTimeFast = false;
	public boolean poweuUpTimeSlow = false;
	public boolean powerUpTimeNormal = true;
	
	int poweruUpTime = 300;
	
	public static PlayerPaddle player; // player class
	public static AIPaddle ai; // ai/second player class
	public static Ball ball; // ball class
	public static Ball ball1; // ball class
	public static Ball ball2; // ball class
	public static PowerUps power; // powerup class
	public static PowerUpsAI powerAI; // powerup class
	public static Pellet pellet; // pellet class
	public static PelletAI pelletAI; // pellet class
	
	InputHandler InpHand; // input handler class
	JFrame frame; // frame
	JPanel mainPanel; // panel
	public static int maxScore;
	public final static int WIDTH = 1080; // width of screen
	public final static int HEIGHT = WIDTH * 9 / 16; // height
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT); // game

	int WIDTH1 = getSize().width;
	int HEIGHT1 = getSize().height;

	BufferedImage image = new BufferedImage(WIDTH, HEIGHT, // image that will be
															// drawn
			BufferedImage.TYPE_INT_RGB);

	static boolean pause = false;
	static boolean gameRunning = false;// is game running
	public final String TITILE = "REAL Pong"; // name on application
	int p1Score, p2Score; // scores
	
	private Clip clip;

	public void run() { // main loop
		while (gameRunning) {// game going
			if (p1Score == maxScore || p2Score == maxScore) { // someone wins
				if(p1Score == maxScore){
					player1wins = true;
					player2wins = false;
				}
				if(p2Score == maxScore){
					player1wins = false;
					player2wins = true;
				}
				clip.stop();
				GameOver.main(null);
				frame.dispose();
				break;
			} else { // else update and draw it
				if (!pause) {
					update();
					render();
					try { // wait seven seconds to update
						Thread.sleep(7);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}

	public synchronized void start() { // these are to make sure that the thread
										// starts and stops
		gameRunning = true;
		new Thread(this).start();
		
		if(sound){
			try {
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
						"music/Tetris.wav"));
				clip = AudioSystem.getClip();
				clip.open(audio);
				clip.loop(clip.LOOP_CONTINUOUSLY);
			}

			catch (UnsupportedAudioFileException uae) {
				System.out.println(uae);
			} catch (IOException ioe) {
				System.out.println(ioe);
			} catch (LineUnavailableException lua) {
				System.out.println(lua);
			} }
	}

	public static synchronized void stop() {
		gameRunning = false;
		System.exit(0);
	}

	public Game() { // game init kind of
		
		if(powerUpTimeFast){
			poweruUpTime = 100;
		}
		if(poweuUpTimeSlow){
			poweruUpTime = 300;
		}
		if(powerUpTimeNormal){
			poweruUpTime = 200;
		}
				
		frame = new JFrame();
		
		
		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);

		frame.add(this, BorderLayout.CENTER);

		frame.pack();	
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITILE);
		frame.setLocationRelativeTo(null); // when screen shows up will not put
											// in realtion to anywhere on screen

		mainPanel = new JPanel();
		InpHand = new InputHandler(this);
		gameRunning = true;

		player = new PlayerPaddle(10, 60); // new player class
		ai = new AIPaddle(getWidth() - 15, 60); //
		ball = new Ball(getWidth() / 2, getHeight() / 2, false,false, true);
		power = new PowerUps(poweruUpTime); // power up with 100 counts before spawing a// new power up
		powerAI = new PowerUpsAI(poweruUpTime); // power up with 100 counts before spawing a// new power up
		pellet = new Pellet();
		pelletAI = new PelletAI();

	}

	public void update() {
		player.update(this); // updates everything
		ai.update(this);
		ball.update(this);
		if(balls){
			ball1.update(this);
			ball2.update(this);
		}
		power.update(this);
		powerAI.update(this);
		pellet.update(this);
		pelletAI.update(this);
	}

	public void render() {
		BufferStrategy buffstrat = getBufferStrategy(); //
		if (buffstrat == null) {
			createBufferStrategy(3); // just a thing to make it draw
			return;
		}

		Graphics g = buffstrat.getDrawGraphics(); //

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // screen

		if (Game.player.shield) {
			g.setColor(Color.RED);
			g.fillRect(getSize().width / 40, 0, 1, getSize().height);
			g.fillRect(getSize().width / 30, 0, 1, getSize().height);

		}
		if (Game.ai.shield) {
			g.setColor(Color.BLUE);
			g.fillRect(97*getSize().width / 100 , 0, 1, getSize().height);
			g.fillRect(96*getSize().width / 100 , 0, 1, getSize().height);

		}
		g.setColor(Color.WHITE);
		g.drawString(realPong.Player1Name + " :" + p1Score, 40, 20);
		g.drawString(realPong.Player2Name + " :" + p2Score, getWidth() - 100, 20); // scores
		g.setColor(Color.CYAN);
		g.fillRect(getSize().width / 4, 0, 1, getSize().height);
		g.fillRect(3 * getSize().width / 4, 0, 1, getSize().height); // lines of
																		// boundrys

		player.render(g);
		ai.render(g);
		ball.render(g); // draw everything
		if(balls){
			ball1.render(g); // draw everything
			ball2.render(g); // draw everything
		}
		power.render(g);
		powerAI.render(g);
		pellet.render(g);
		pelletAI.render(g);
		g.dispose(); // t
		buffstrat.show();

	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
		
	}

}
