
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GameOver {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOver window = new GameOver();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameOver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panelPlayer1 = new JPanel();
		frame.getContentPane().add(panelPlayer1, "name_8208434133366");
		panelPlayer1.setBackground(realPong.panelBackground);
		panelPlayer1.setLayout(null);
		panelPlayer1.setVisible(false);
		if (Game.player1wins == true)
			panelPlayer1.setVisible(true);
		
		JPanel panelPlayer2 = new JPanel();
		frame.getContentPane().add(panelPlayer2, "name_8210826612597");
		panelPlayer2.setBackground(realPong.panelBackground);
		panelPlayer2.setLayout(null);
		panelPlayer2.setVisible(false);
		if (Game.player2wins == true)
			panelPlayer2.setVisible(true);
		
		/* 
		 * Player 1 Win Screen
		 */
		
		JLabel lblPlayer1Wins = new JLabel(realPong.Player1Name + " Wins!");
		lblPlayer1Wins.setForeground(realPong.labelText);
		lblPlayer1Wins.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer1Wins.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPlayer1Wins.setBounds(10, 129, 474, 33);
		panelPlayer1.add(lblPlayer1Wins);
		
		
		JButton btnP1MainMenu = new JButton("Main Menu");
		btnP1MainMenu.setBackground(realPong.buttonBackground);
		btnP1MainMenu.setForeground(realPong.buttonText);
		btnP1MainMenu.setBorder(realPong.buttonBorder);
		btnP1MainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				realPong.frame.setVisible(true);
			}
		});
		btnP1MainMenu.setBounds(167, 173, 161, 23);
		panelPlayer1.add(btnP1MainMenu);
		
		JButton btnP1QuitGame = new JButton("Quit Game");
		btnP1QuitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnP1QuitGame.setBackground(realPong.buttonBackground);
		btnP1QuitGame.setForeground(realPong.buttonText);
		btnP1QuitGame.setBorder(realPong.buttonBorder);
		btnP1QuitGame.setBounds(167, 202, 161, 23);
		panelPlayer1.add(btnP1QuitGame);
		
		/*
		 * Player 2 Win Screen
		 */
		
		JLabel lblPlayer2Wins = new JLabel(realPong.Player2Name + " Wins!");
		lblPlayer2Wins.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer2Wins.setForeground(realPong.labelText);
		lblPlayer2Wins.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPlayer2Wins.setBounds(10, 129, 474, 33);
		panelPlayer2.add(lblPlayer2Wins);
		
		JButton btnP2MainMenu = new JButton("Main Menu");
		btnP2MainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				realPong.frame.setVisible(true);
			}
		});
		btnP2MainMenu.setBackground(realPong.buttonBackground);
		btnP2MainMenu.setForeground(realPong.buttonText);
		btnP2MainMenu.setBorder(realPong.buttonBorder);
		btnP2MainMenu.setBounds(167, 173, 161, 23);
		panelPlayer2.add(btnP2MainMenu);
		
		JButton btnP2QuitGame = new JButton("Quit Game");
		btnP2QuitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnP2QuitGame.setBackground(realPong.buttonBackground);
		btnP2QuitGame.setForeground(realPong.buttonText);
		btnP2QuitGame.setBorder(realPong.buttonBorder);
		btnP2QuitGame.setBounds(167, 202, 161, 23);
		panelPlayer2.add(btnP2QuitGame);
		
		
	}
}
