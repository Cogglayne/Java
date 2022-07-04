import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;


public class PongCanvas extends JPanel {

	private int ballX;
	private int ballY;
	private int ballSize = 25;
	private int ballSpeed = 50;
	private int delta = 5;
	private int xDir = +1;
	private int yDir = +1;

	private int leftY = 100;
	private int rightY = 100;
	private boolean leftUp;
	private boolean leftDown;
	private boolean rightUp;
	private boolean rightDown;
	private int paddleHeight = 100;

	private Pong gameInterface;

	public PongCanvas(Pong gameInterface) {

		this.gameInterface= gameInterface;

		setBackground(Color.black);
		setDoubleBuffered(true);

		setFocusable(true);

		new Timer(ballSpeed, e -> {
				ballX += xDir * delta;
				ballY += yDir * delta;

				// check for point
				// if the ball goes off the left side and the paddle isn't there, point for 
				// the rightside player 
				if (ballX <= 0 && !(ballY >= leftY && ballY <= leftY + paddleHeight)) {
					gameInterface.rightPlayerScored();
				}

				if (ballX + ballSize >= getSize().getWidth() && !(ballY >= rightY && ballY <= rightY + paddleHeight)) {
					gameInterface.leftPlayerScored();
				}

				if (ballX <= 0 || ballX+ballSize >= getSize().getWidth()) {
					xDir = -xDir;
				}

				if (ballY <= 0 || ballY+ballSize >= getSize().getHeight()) {
					yDir = -yDir;
				}

				if (leftUp && leftY > 0) {
					leftY -= delta;
				}

				if (leftDown && leftY+paddleHeight < getSize().getHeight()) {
					leftY += delta;
				}

				if (rightUp && rightY > 0) {
					rightY -= delta;
				}

				if (rightDown && rightY+paddleHeight < getSize().getHeight()) {
					rightY += delta;
				}

				repaint();
		}).start();

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_UP) {
					rightUp = true;
				}

				if (key == KeyEvent.VK_DOWN) {
					rightDown = true;
				}

				if (key == KeyEvent.VK_W) {
					leftUp = true;
				}

				if (key == KeyEvent.VK_S) {
					leftDown = true;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_UP) {
					rightUp = false;
				}

				if (key == KeyEvent.VK_DOWN) {
					rightDown = false;
				}

				if (key == KeyEvent.VK_W) {
					leftUp = false;
				}

				if (key == KeyEvent.VK_S) {
					leftDown = false;
				}
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillOval(ballX, ballY, ballSize, ballSize);
		g.fillRect(0, leftY, 10, paddleHeight);
		g.fillRect((int) getSize().getWidth()-10, rightY, 10, paddleHeight);
	}
}
