
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.CardLayout;
import java.io.*;
import java.util.*;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JTextField;

public class realPong {

	static JFrame frame;
	private JPanel panelMainMenu;
	private JPanel panelStartGame;
	private JPanel panelOptions;
	private JPanel panelPlayerAccounts;
	private JPanel panelHelp;
	
	static Border buttonBorder;
	
	static Color borderColor;
	static Color buttonText;
	static Color buttonBackground;
	static Color labelText;
	static Color panelBackground;
	private int themeindex;
	private int scoreindex;
	private String currentTheme;
	
	private Formatter gamePreferences;
	private Formatter playerAccounts;
	private Scanner scanner;
	
	DefaultListModel model = new DefaultListModel();
	
	static String Player1Name = "Player 1";
	static String Player2Name = "Player 2";
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					realPong window = new realPong();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public realPong() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setResizable(false);
		
		findFile();
		readFile();	

		findPlayerFile();
		readPlayerFile();
		
		buttonBorder = new LineBorder(borderColor,1);
		
		/**
		 * Panels Codes
		 */
		
		final JPanel panelMainMenu = new JPanel();
		panelMainMenu.setBackground(panelBackground);
		frame.getContentPane().add(panelMainMenu, "name_4861446421878");
		panelMainMenu.setLayout(null);
		panelMainMenu.setVisible(true);
		
		final JPanel panelStartGame = new JPanel();
		panelStartGame.setBackground(panelBackground);
		frame.getContentPane().add(panelStartGame, "name_8218228970703");
		panelStartGame.setLayout(null);
		panelStartGame.setVisible(false);
		
		final JPanel panelOptions = new JPanel();
		panelOptions.setBackground(panelBackground);
		frame.getContentPane().add(panelOptions, "name_9690173283315");
		panelOptions.setLayout(null);
		panelOptions.setVisible(false);
		
		final JPanel panelPlayerAccounts = new JPanel();
		panelPlayerAccounts.setBackground(panelBackground);
		frame.getContentPane().add(panelPlayerAccounts, "name_9744614853926");
		panelPlayerAccounts.setLayout(null);
		panelPlayerAccounts.setVisible(false);
		
		final JPanel panelHelp = new JPanel();
		panelHelp.setBackground(panelBackground);
		frame.getContentPane().add(panelHelp, "name_9761999327997");
		panelHelp.setLayout(null);
		panelHelp.setVisible(false);
		
		/**
		 * Main Menu contents
		 */
		
		JLabel lblRealPong = new JLabel("Real Pong");
		lblRealPong.setForeground(labelText);
		lblRealPong.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRealPong.setHorizontalAlignment(SwingConstants.CENTER);
		lblRealPong.setBounds(10, 11, 474, 50);
		panelMainMenu.add(lblRealPong);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setBackground(buttonBackground);
		btnStartGame.setForeground(buttonText);
		btnStartGame.setBorder(buttonBorder);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelStartGame.setVisible(true);
				panelMainMenu.setVisible(false);
			}
		});
		btnStartGame.setBounds(167, 72, 161, 23);
		panelMainMenu.add(btnStartGame);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBackground(buttonBackground);
		btnOptions.setForeground(buttonText);
		btnOptions.setBorder(buttonBorder);
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelOptions.setVisible(true);
				panelMainMenu.setVisible(false);
			}
		});
		btnOptions.setBounds(167, 106, 161, 23);
		panelMainMenu.add(btnOptions);
		
		JButton btnPlayerAccounts = new JButton("Player Accounts");
		btnPlayerAccounts.setBackground(buttonBackground);
		btnPlayerAccounts.setForeground(buttonText);
		btnPlayerAccounts.setBorder(buttonBorder);
		btnPlayerAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPlayerAccounts.setVisible(true);
				panelMainMenu.setVisible(false);
			}
		});
		btnPlayerAccounts.setBounds(167, 140, 161, 23);
		panelMainMenu.add(btnPlayerAccounts);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setBackground(buttonBackground);
		btnHelp.setForeground(buttonText);
		btnHelp.setBorder(buttonBorder);
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelHelp.setVisible(true);
				panelMainMenu.setVisible(false);
			}
		});
		btnHelp.setBounds(167, 174, 161, 23);
		panelMainMenu.add(btnHelp);
		
		JButton btnQuit = new JButton("Quit Game");
		btnQuit.setBackground(buttonBackground);
		btnQuit.setForeground(buttonText);
		btnQuit.setBorder(buttonBorder);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(167, 208, 161, 23);
		panelMainMenu.add(btnQuit);
		
		/**
		 * Start Game contents
		 */
		
		JLabel lblStartGame = new JLabel("Start Game");
		lblStartGame.setForeground(labelText);
		lblStartGame.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartGame.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStartGame.setBounds(10, 11, 474, 50);
		panelStartGame.add(lblStartGame);
		
		JButton btnSinglePlayer = new JButton("Single Player");
		btnSinglePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { // initialize Pong game
				panelStartGame.setVisible(false);
				panelMainMenu.setVisible(true);
				frame.setVisible(false);
				Game game = new Game();
				game.ai.isTwoPlayer = false;
				game.start();
			}
		});
		btnSinglePlayer.setBackground(buttonBackground);
		btnSinglePlayer.setForeground(buttonText);
		btnSinglePlayer.setBorder(buttonBorder);
		btnSinglePlayer.setBounds(166, 157, 161, 23);
		panelStartGame.add(btnSinglePlayer);
		
		JButton btnTwoPlayers = new JButton("Two Players");
		btnTwoPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelStartGame.setVisible(false);
				panelMainMenu.setVisible(true);
				frame.setVisible(false);
				Game game = new Game();
				game.ai.isTwoPlayer = true;
				game.start();
			}
		});
		btnTwoPlayers.setBackground(buttonBackground);
		btnTwoPlayers.setForeground(buttonText);
		btnTwoPlayers.setBorder(buttonBorder);
		btnTwoPlayers.setBounds(166, 191, 161, 23);
		panelStartGame.add(btnTwoPlayers);
		
		JButton btnSGBack = new JButton("Main Menu");
		btnSGBack.setBackground(buttonBackground);
		btnSGBack.setForeground(buttonText);
		btnSGBack.setBorder(buttonBorder);
		btnSGBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelStartGame.setVisible(false);
				panelMainMenu.setVisible(true);
			}
		});
		btnSGBack.setBounds(166, 338, 161, 23);
		panelStartGame.add(btnSGBack);
		
		/**
		 * Options contents
		 */
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setForeground(labelText);
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptions.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblOptions.setBounds(10, 11, 474, 50);
		panelOptions.add(lblOptions);	

		JLabel lblTheme = new JLabel("Theme");
		lblTheme.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTheme.setHorizontalAlignment(SwingConstants.CENTER);
		lblTheme.setForeground(labelText);
		lblTheme.setBounds(20, 55, 124, 17);
		panelOptions.add(lblTheme);

		JLabel lblPowerUps = new JLabel("Power Ups");
		lblPowerUps.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPowerUps.setHorizontalAlignment(SwingConstants.CENTER);
		lblPowerUps.setForeground(labelText);
		lblPowerUps.setBounds(26, 122, 124, 17);
		panelOptions.add(lblPowerUps);
		
		JLabel lblMusic = new JLabel("Sound");
		lblMusic.setHorizontalAlignment(SwingConstants.CENTER);
		lblMusic.setForeground(labelText);
		lblMusic.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMusic.setBounds(176, 202, 124, 17);
		panelOptions.add(lblMusic);
		
		JLabel lblMouse = new JLabel("Mouse Control (in progess)");
		lblMouse.setHorizontalAlignment(SwingConstants.CENTER);
		lblMouse.setForeground(labelText);
		lblMouse.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMouse.setBounds(85, 252, 315, 17);
		panelOptions.add(lblMouse);
		
		JLabel lblMaxScore = new JLabel("Max Score");
		lblMaxScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaxScore.setForeground(labelText);
		lblMaxScore.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMaxScore.setBounds(30, 86, 124, 17);
		panelOptions.add(lblMaxScore);
		
		String[] themeStrings = { "Default", "Classic", "MERICA'" }; // Various themes for game
		final JComboBox comboBoxTheme = new JComboBox(themeStrings);
		comboBoxTheme.setSelectedIndex(themeindex);
		comboBoxTheme.setBounds(168, 55, 161, 20);
		panelOptions.add(comboBoxTheme);
		
		String[] scoreStrings = { "10", "15", "20", "25" };
		final JComboBox comboBoxScore = new JComboBox(scoreStrings);
		comboBoxScore.setSelectedIndex(scoreindex);
		comboBoxScore.setBounds(168, 86, 161, 20);
		panelOptions.add(comboBoxScore);
		
		JButton btnOBack = new JButton("Main Menu");
		btnOBack.setBackground(buttonBackground);
		btnOBack.setForeground(buttonText);
		btnOBack.setBorder(buttonBorder);
		btnOBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelOptions.setVisible(false);
				panelMainMenu.setVisible(true);
			}
		});
		btnOBack.setBounds(20, 338, 161, 23);
		panelOptions.add(btnOBack);
		
		final JRadioButton rdbtnPlayerSpeed = new JRadioButton("Increase/Decrease Player Speed");
		rdbtnPlayerSpeed.setForeground(buttonText);
		rdbtnPlayerSpeed.setBackground(buttonBackground);
		rdbtnPlayerSpeed.setBounds(137, 121, 223, 23);
		panelOptions.add(rdbtnPlayerSpeed);
		if (Game.playerSpeed == true)
			rdbtnPlayerSpeed.setSelected(true);
		if (Game.playerSpeed == false)
			rdbtnPlayerSpeed.setSelected(false);
		
		final JRadioButton rdbtnBallSpeed = new JRadioButton("Increase/Decrease Ball Speed");
		rdbtnBallSpeed.setForeground(buttonText);
		rdbtnBallSpeed.setBackground(buttonBackground);
		rdbtnBallSpeed.setBounds(137, 146, 223, 23);
		panelOptions.add(rdbtnBallSpeed);
		if (Game.ballSpeed == true)
			rdbtnBallSpeed.setSelected(true);
		if (Game.ballSpeed == false)
			rdbtnBallSpeed.setSelected(false);
		
		final JRadioButton rdbtnPaddleLength = new JRadioButton("Increase/Decrease Paddle Length");
		rdbtnPaddleLength.setForeground(buttonText);
		rdbtnPaddleLength.setBackground(buttonBackground);
		rdbtnPaddleLength.setBounds(137, 172, 223, 23);
		panelOptions.add(rdbtnPaddleLength);
		if (Game.playerLength == true)
			rdbtnPaddleLength.setSelected(true);
		if (Game.playerLength == false)
			rdbtnPaddleLength.setSelected(false);
		
		final JRadioButton rdbtnNet = new JRadioButton("Protective Net");
		rdbtnNet.setForeground(buttonText);
		rdbtnNet.setBackground(buttonBackground);
		rdbtnNet.setBounds(362, 121, 132, 23);
		panelOptions.add(rdbtnNet);
		if (Game.net == true)
			rdbtnNet.setSelected(true);
		if (Game.net == false)
			rdbtnNet.setSelected(false);
		
		final JRadioButton rdbtnGun = new JRadioButton("Pellet Gun");
		rdbtnGun.setForeground(buttonText);
		rdbtnGun.setBackground(buttonBackground);
		rdbtnGun.setBounds(362, 146, 128, 23);
		panelOptions.add(rdbtnGun);
		if (Game.gun == true)
			rdbtnGun.setSelected(true);
		if (Game.gun == false)
			rdbtnGun.setSelected(false);
		
		final JRadioButton rdbtnMultiBall = new JRadioButton("Multi Ball");
		rdbtnMultiBall.setForeground(buttonText);
		rdbtnMultiBall.setBackground(buttonBackground);
		rdbtnMultiBall.setBounds(362, 172, 128, 23);
		panelOptions.add(rdbtnMultiBall);
		if (Game.Multi == true)
			rdbtnMultiBall.setSelected(true);
		if (Game.Multi == false)
			rdbtnMultiBall.setSelected(false);
		
		ButtonGroup musicGroup = new ButtonGroup();
		
		final JRadioButton rdbtnMusicOn = new JRadioButton("On");
		rdbtnMusicOn.setForeground(buttonText);
		rdbtnMusicOn.setBackground(buttonBackground);
		rdbtnMusicOn.setBounds(186, 226, 51, 23);
		panelOptions.add(rdbtnMusicOn);
		musicGroup.add(rdbtnMusicOn);
		if (Game.sound == true)
			rdbtnMusicOn.setSelected(true);
		
		final JRadioButton rdbtnMusicOff = new JRadioButton("Off");
		rdbtnMusicOff.setForeground(buttonText);
		rdbtnMusicOff.setBackground(buttonBackground);
		rdbtnMusicOff.setBounds(249, 226, 51, 23);
		panelOptions.add(rdbtnMusicOff);
		musicGroup.add(rdbtnMusicOff);
		if (Game.sound == false)
			rdbtnMusicOff.setSelected(true);
		
		ButtonGroup mouseGroup = new ButtonGroup();
		
		final JRadioButton rdbtnMouseOn = new JRadioButton("On");
		rdbtnMouseOn.setForeground(buttonText);
		rdbtnMouseOn.setBackground(buttonBackground);
		rdbtnMouseOn.setBounds(186, 276, 51, 23);
		panelOptions.add(rdbtnMouseOn);
		mouseGroup.add(rdbtnMouseOn);
		if (PlayerPaddle.keys == false)
			rdbtnMouseOn.setSelected(true);
		
		final JRadioButton rdbtnMouseOff = new JRadioButton("Off");
		rdbtnMouseOff.setForeground(buttonText);
		rdbtnMouseOff.setBackground(buttonBackground);
		rdbtnMouseOff.setBounds(249, 276, 51, 23);
		panelOptions.add(rdbtnMouseOff);
		mouseGroup.add(rdbtnMouseOff);
		if (PlayerPaddle.keys == true)
			rdbtnMouseOff.setSelected(true);
		
		JButton btnApplyChanges = new JButton("Apply Changes");
		btnApplyChanges.setBackground(buttonBackground);
		btnApplyChanges.setForeground(buttonText);
		btnApplyChanges.setBorder(buttonBorder);
		btnApplyChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Please restart the game for some changes to take effect.");
				currentTheme = comboBoxTheme.getSelectedItem().toString(); // gets currently selected theme.
				if (currentTheme == "Default"){
					borderColor = Color.BLACK;
					buttonText = Color.BLACK;
					buttonBackground = Color.LIGHT_GRAY;
					labelText = Color.BLACK;
					panelBackground = Color.LIGHT_GRAY;
					themeindex=0;
				}
				if (currentTheme == "Classic"){
					borderColor = Color.YELLOW;
					buttonText = Color.YELLOW;
					buttonBackground = Color.BLACK;
					labelText = Color.YELLOW;
					panelBackground = Color.BLACK;
					themeindex=1;
				}
				if (currentTheme == "MERICA'"){
					borderColor = Color.RED;
					buttonText = Color.BLUE;
					buttonBackground = Color.WHITE;
					labelText = Color.BLUE;
					panelBackground = Color.WHITE;
					themeindex=2;
				}
				if (comboBoxScore.getSelectedIndex() == 0){
					Game.maxScore = 10;
					scoreindex=0;
				}
				if (comboBoxScore.getSelectedIndex() == 1){
					Game.maxScore = 15;
					scoreindex=1;
				}
				if (comboBoxScore.getSelectedIndex() == 2){
					Game.maxScore = 20;
					scoreindex=2;
				}
				if (comboBoxScore.getSelectedIndex() == 3){
					Game.maxScore = 25;
					scoreindex=3;
				}
				
				if (rdbtnPlayerSpeed.isSelected())
					Game.playerSpeed = true;
				if (!rdbtnPlayerSpeed.isSelected())
					Game.playerSpeed = false;
				
				if (rdbtnBallSpeed.isSelected())
					Game.ballSpeed = true;
				if (!rdbtnBallSpeed.isSelected())
					Game.ballSpeed = false;
				
				if (rdbtnPaddleLength.isSelected())
					Game.playerLength = true;
				if (!rdbtnPaddleLength.isSelected())
					Game.playerLength = false;
				
				if (rdbtnNet.isSelected())
					Game.net = true;
				if (!rdbtnNet.isSelected())
					Game.net = false;
				
				if (rdbtnGun.isSelected())
					Game.gun = true;
				if (!rdbtnGun.isSelected())
					Game.gun = false;
				
				if (rdbtnMultiBall.isSelected())
					Game.Multi = true;
				if (!rdbtnMultiBall.isSelected())
					Game.Multi = false;
				
				if (rdbtnMusicOn.isSelected())
					Game.sound = true;
				if (rdbtnMusicOff.isSelected())
					Game.sound = false;
				
				if(rdbtnMouseOn.isSelected())
					PlayerPaddle.keys = false;
				if(rdbtnMouseOff.isSelected())
					PlayerPaddle.keys = true;
				
				openFile();
				changePreferences();
				closeFile();
			}
		});
		btnApplyChanges.setBounds(323, 338, 161, 23);
		panelOptions.add(btnApplyChanges);
		
		/**
		 * Player Accounts contents
		 */
		
		JLabel lblPlayerAccounts = new JLabel("Player Accounts");
		lblPlayerAccounts.setForeground(labelText);
		lblPlayerAccounts.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerAccounts.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPlayerAccounts.setBounds(10, 11, 474, 50);
		panelPlayerAccounts.add(lblPlayerAccounts);
		
		final JList listPlayerNames = new JList(model);
		listPlayerNames.setBounds(39, 72, 178, 243);
		panelPlayerAccounts.add(listPlayerNames);
		
		JButton btnSelectPlayer1 = new JButton("Select Player 1");
		btnSelectPlayer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s1 = (String) listPlayerNames.getSelectedValue();
				if (s1 == null)
					JOptionPane.showMessageDialog(null, "Please select a player.");
				else{
					if (s1 == Player2Name)
						JOptionPane.showMessageDialog(null, "Please select different accounts for each player.");
					else{
						Player1Name = s1;
						String display = "Player 1 is " + Player1Name;
						JOptionPane.showMessageDialog(null, display);
					}
				}
			}
		});
		btnSelectPlayer1.setBackground(buttonBackground);
		btnSelectPlayer1.setForeground(buttonText);
		btnSelectPlayer1.setBorder(buttonBorder);
		btnSelectPlayer1.setBounds(278, 72, 161, 23);
		panelPlayerAccounts.add(btnSelectPlayer1);
		
		JButton btnSelectPlayer2 = new JButton("Select Player 2");
		btnSelectPlayer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s2 = (String) listPlayerNames.getSelectedValue();
				if (s2 == null)
					JOptionPane.showMessageDialog(null, "Please select a player.");
				else{
					if (s2 == Player1Name)
						JOptionPane.showMessageDialog(null, "Please select different accounts for each player.");
					else{
						Player2Name = s2;
						String display = "Player 2 is " + Player2Name;
						JOptionPane.showMessageDialog(null, display);
					}
				}
			}
		});
		btnSelectPlayer2.setBackground(buttonBackground);
		btnSelectPlayer2.setForeground(buttonText);
		btnSelectPlayer2.setBorder(buttonBorder);
		btnSelectPlayer2.setBounds(278, 106, 161, 23);
		panelPlayerAccounts.add(btnSelectPlayer2);
		
		JButton btnDeletePlayer = new JButton("Delete Player");
		btnDeletePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String delete = (String) listPlayerNames.getSelectedValue();
				if (delete == null)
					JOptionPane.showMessageDialog(null, "Please select a player.");
				else{
					int index = listPlayerNames.getSelectedIndex();
					model.remove(index);
				}
			}
		});
		btnDeletePlayer.setBackground(buttonBackground);
		btnDeletePlayer.setForeground(buttonText);
		btnDeletePlayer.setBorder(buttonBorder);
		btnDeletePlayer.setBounds(278, 292, 161, 23);
		panelPlayerAccounts.add(btnDeletePlayer);
		
		textField = new JTextField();
		textField.setBounds(278, 178, 161, 20);
		panelPlayerAccounts.add(textField);
		textField.setColumns(10);
		
		JButton btnCreatePlayer = new JButton("Create Player");
		btnCreatePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newPlayer = textField.getText();
				model.addElement(newPlayer);
			}
		});
		btnCreatePlayer.setBackground(buttonBackground);
		btnCreatePlayer.setForeground(buttonText);
		btnCreatePlayer.setBorder(buttonBorder);
		btnCreatePlayer.setBounds(278, 209, 161, 23);
		panelPlayerAccounts.add(btnCreatePlayer);
		
		JButton btnPABack = new JButton("Main Menu");
		btnPABack.setBackground(buttonBackground);
		btnPABack.setForeground(buttonText);
		btnPABack.setBorder(buttonBorder);
		btnPABack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPlayerFile();
				editPlayerFile(listPlayerNames);
				closePlayerFile();
				panelPlayerAccounts.setVisible(false);
				panelMainMenu.setVisible(true);
			}
		});
		btnPABack.setBounds(161, 338, 161, 23);
		panelPlayerAccounts.add(btnPABack);
		
		/**
		 * Help contents
		 */
		
		JLabel lblHelp = new JLabel("Help");
		lblHelp.setForeground(labelText);
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHelp.setBounds(10, 11, 474, 50);
		panelHelp.add(lblHelp);
		
		JButton btnHBack = new JButton("Main Menu");
		btnHBack.setBackground(buttonBackground);
		btnHBack.setForeground(buttonText);
		btnHBack.setBorder(buttonBorder);
		btnHBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelHelp.setVisible(false);
				panelMainMenu.setVisible(true);
			}
		});
		btnHBack.setBounds(166, 338, 161, 23);
		panelHelp.add(btnHBack);
		
		JLabel lblControls = new JLabel("<html>ESC to quit"
				+ "<br/> P to Pause"
				+ "<br/>"
				+ "<br/> Player 1:"
				+ "<br/> W,A,S,D to move"
				+ "<br/> Left Shift to Launch Ball"
				+ "<br/> Space to fire pellet"
				+ "<br/>"
				+ "<br/> Player 2:"
				+ "<br/> Arrow keys to move"
				+ "<br/> Right Shift to Launch Ball"
				+ "<br/> Right Ctrl to to fire pellet<html>");
		lblControls.setHorizontalAlignment(SwingConstants.CENTER);
		lblControls.setForeground(labelText);
		lblControls.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblControls.setBounds(10, 55, 474, 272);
		panelHelp.add(lblControls);
	}
	
	
	/** 
	 * Text File Management 
	 */
	
	public void openFile(){ // open game preferences text file
		try{
			gamePreferences = new Formatter ("gamepreferences.txt");
		}
		catch (Exception e){
			System.out.println("An error occured, this shouldn't happen");
		}
	}
	
	public void changePreferences(){ // save preferences to text file
		gamePreferences.format("BorderColor=%s\n", Integer.toString(borderColor.getRGB()));
		gamePreferences.format("ButtonText=%s\n", Integer.toString(buttonText.getRGB()));
		gamePreferences.format("ButtonBackground=%s\n", Integer.toString(buttonBackground.getRGB()));
		gamePreferences.format("LabelText=%s\n", Integer.toString(labelText.getRGB()));
		gamePreferences.format("PanelBackground=%s\n", Integer.toString(panelBackground.getRGB()));
		gamePreferences.format("themeindex=%s\n", Integer.toString(themeindex));
		gamePreferences.format("scoreindex=%s\n", Integer.toString(scoreindex));
		gamePreferences.format("maxScore=%s\n", Integer.toString(Game.maxScore));
		gamePreferences.format("playerSpeed=%s\n", String.valueOf(Game.playerSpeed));
		gamePreferences.format("ballSpeed=%s\n", String.valueOf(Game.ballSpeed));
		gamePreferences.format("playerLength=%s\n", String.valueOf(Game.playerLength));
		gamePreferences.format("net=%s\n", String.valueOf(Game.net));
		gamePreferences.format("gun=%s\n", String.valueOf(Game.gun));
		gamePreferences.format("Multi=%s\n", String.valueOf(Game.Multi));
		gamePreferences.format("sound=%s\n", String.valueOf(Game.sound));
		gamePreferences.format("keys=%s\n", String.valueOf(PlayerPaddle.keys));
	}
	
	public void findFile() throws IOException{ // find game preferences text file
		try{
			scanner = new Scanner(new File("gamepreferences.txt"));
		}
		catch (Exception e){ // create a default text file
			File fout = new File("gamepreferences.txt");
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			bw.write("BorderColor=-16777216\n");
			bw.write("ButtonText=-16777216\n");
			bw.write("ButtonBackground=-4144960\n");
			bw.write("LabelText=-16777216\n");
			bw.write("PanelBackground=-4144960\n");
			bw.write("themeindex=0\n");
			bw.write("scoreindex=0\n");
			bw.write("maxScore=10\n");
			bw.write("playerSpeed=true\n");
			bw.write("ballSpeed=true\n");
			bw.write("playerLength=true\n");
			bw.write("net=true\n");
			bw.write("gun=true\n");
			bw.write("Multi=true\n");
			bw.write("sound=true\n");
			bw.write("keys=true\n");
			
			bw.close();
			scanner = new Scanner(new File("gamepreferences.txt"));
		}
	}
	
	public void readFile(){ // read game preferences text file
		while(scanner.hasNext()){
			borderColor = new Color (Integer.parseInt(last(scanner.next().split("="))));
			buttonText = new Color (Integer.parseInt(last(scanner.next().split("="))));
			buttonBackground = new Color (Integer.parseInt(last(scanner.next().split("="))));
			labelText = new Color (Integer.parseInt(last(scanner.next().split("="))));
			panelBackground = new Color (Integer.parseInt(last(scanner.next().split("="))));
			themeindex = Integer.parseInt(last(scanner.next().split("=")));
			scoreindex = Integer.parseInt(last(scanner.next().split("=")));
			Game.maxScore = Integer.parseInt(last(scanner.next().split("=")));
			Game.playerSpeed = Boolean.valueOf(last(scanner.next().split("=")));
			Game.ballSpeed = Boolean.valueOf(last(scanner.next().split("=")));
			Game.playerLength = Boolean.valueOf(last(scanner.next().split("=")));
			Game.net = Boolean.valueOf(last(scanner.next().split("=")));
			Game.gun = Boolean.valueOf(last(scanner.next().split("=")));
			Game.Multi = Boolean.valueOf(last(scanner.next().split("=")));
			Game.sound = Boolean.valueOf(last(scanner.next().split("=")));
			PlayerPaddle.keys = Boolean.valueOf(last(scanner.next().split("=")));
			
		}
	}
	
	public void closeFile(){ // close file
		gamePreferences.close();
	}	
	
	public static <T> T last(T[] array){ // helper function for readFile()
		return array[array.length -1];
	}
	
	/** 
	 * Player File Management 
	 */
	
	public void openPlayerFile(){ // open player text file
		try{
			playerAccounts = new Formatter ("playeraccounts.txt");
		}
		catch (Exception e){
			System.out.println("An error occured, this shouldn't happen");
		}
	}
	
	public void editPlayerFile(JList list){
		try {
			FileWriter filer = new FileWriter("playeraccounts.txt");
			PrintWriter out = new PrintWriter(filer);
			for (int i = 0; i < list.getModel().getSize(); i++){
				Object item = list.getModel().getElementAt(i);
				out.println(item);
			}
			out.close();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void findPlayerFile() throws IOException{ // find player text file
		try{
			scanner = new Scanner(new File("playeraccounts.txt"));
		}
		catch (Exception e){ // create a default text file
			File foutP = new File("playeraccounts.txt");
			FileOutputStream fosP = new FileOutputStream(foutP);
			BufferedWriter bwP = new BufferedWriter(new OutputStreamWriter(fosP));
			bwP.close();
			scanner = new Scanner(new File("playeraccounts.txt"));
		}
	}
	
	public void readPlayerFile(){
		while(scanner.hasNext())
			model.addElement(scanner.next());
	}
	
	public void closePlayerFile(){
		playerAccounts.close();
	}
}
