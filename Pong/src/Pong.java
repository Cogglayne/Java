
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class Pong extends JFrame {

	private int leftScore = 0;
	private int rightScore = 0;
	private JLabel leftLabel;
	private JLabel rightLabel;


	public Pong() {

		super("Pong");
	
		this.setLayout(new BorderLayout());

		PongCanvas canvas = new PongCanvas(this);
		this.add(canvas, BorderLayout.CENTER);

		JPanel scorePanel = new JPanel();
		scorePanel.setLayout(new GridLayout(1, 2));

		leftLabel = new JLabel("Player 1: " + leftScore);
		rightLabel = new JLabel("Player 2: " + rightScore);
		Font f = new Font("Arial", Font.BOLD, 16);
		leftLabel.setFont(f);
		rightLabel.setFont(f);
		scorePanel.add(leftLabel);
		scorePanel.add(rightLabel);

		this.add(scorePanel, BorderLayout.SOUTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500, 300);
	}


//	public void playSound() {
//		try {
//			String soundName = "bells.wav";    
//			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName));
//			Clip clip = AudioSystem.getClip();
//			clip.open(audioInputStream);
//			clip.start();
//		} catch (Exception e) {
//			System.out.println("Error playing sound: " + e);
//		}
//	}


	public void leftPlayerScored() {
		leftScore++;
		leftLabel.setText("Player 1: " + leftScore);
		repaint();

//		playSound();

		if (leftScore == 3) {
			JOptionPane.showMessageDialog(this, "Player 1 wins!");

			// Once left score reaches 3 (winning amount) scores are reset utilizing method call.
			// See below for method
			leftReset();
			rightReset();
		}
	}

	
	public void rightPlayerScored() {
		rightScore++;
		rightLabel.setText("Player 2: " + rightScore);
		repaint();

//		playSound();

		if (rightScore == 3) {
			JOptionPane.showMessageDialog(this, "Player 2 wins!");

			/*
			* Once right score reaches 3 (winning amount) scores are reset utilizing method call.
			* See below for method
			* These methods are called after the winning message is displayed so players can
			* continue to see final score until new game is started
			 */
			leftReset();
			rightReset();
		}
	}


	/*
	* Since both rightPlayerScored and leftPlayerScored reach winning numbers separately
	* methods were created for resetting each side in order to minimize the copying of code.
	* Each method initializes the score to 0 and resets the label with newly initialized
	* score.
	 */
	public void leftReset() {
		leftScore = 0;
		leftLabel.setText("Player 1: " + leftScore);
	}

	public void rightReset() {
		rightScore = 0;
		rightLabel.setText("Player 1: " + rightScore);
	}


	public static void main(String[] args) {
		Pong pong = new Pong();
		pong.setVisible(true);
	}

}