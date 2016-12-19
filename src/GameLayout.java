import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * GameLayout.java
 * ---------------
 * Arranges GUI for the game
 */
public class GameLayout extends JFrame {
	private final int DELAY = 30;	// delay (ms) for solver animation
	
	private Maze scene;
	private JDialog won;

	private String level; // difficulty level
	private int w = 100; // width and height for buttons
	private int h = 50;

	public GameLayout() {
		super();
		level = "Casual";
		init();
	}

	// initialize components
	private void init() {
		setResizable(false);
		setSize(850, 700);
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		addMaze();
		addPlay();
		addDifficulty();
		addSolve();
		addQuit();
		addW();
		addS();
		addA();
		addD();
		addTutorial();
		addInfo();

		won = new JDialog(); // for winning pop-up
	}

	public void reset() {
		getContentPane().removeAll();
		dispose();
		init();
		revalidate();
		repaint();
	}

	private void checkWin() {
		if (scene.getPlayer().hasWon()) {
			String wonMessage = "<html>YOU WON!!!<br>"
					+ "Click New Game if you want to play again or Quit Game to exit</html>";
			JOptionPane optionPane = new JOptionPane(wonMessage, JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.DEFAULT_OPTION, null, new Object[] {}, null);

			won.setTitle("Congratulations!");
			won.setModal(false);
			won.setContentPane(optionPane);
			won.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			won.pack();
			won.setLocationRelativeTo(null);
			won.setVisible(true);
		}
	}

	private void addMaze() { // add a Maze
		scene = new Maze(level);
		scene.setSize(640, 670);
		scene.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				scene.movePlayer(key);
				checkWin();
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		scene.setFocusable(true);
		add(scene, BorderLayout.CENTER);
	}

	private void addPlay() { // add New Game button
		JButton play = new JButton("New Game");
		play.setSize(w, h);
		play.setLocation(640, 10);
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				won.setVisible(false);
				won.dispose();
				reset();
			}
		});
		play.setFocusable(false);
		add(play);
	}

	private void addDifficulty() { // add Difficulty menu and label
		String[] difficulties = { "Rookie", "Casual", "Pro" };
		JComboBox<String> difficulty = new JComboBox<String>(difficulties);
		difficulty.setSize(w, h);
		difficulty.setLocation(740, 22);
		difficulty.setSelectedIndex(1);
		difficulty.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				level = (String) cb.getSelectedItem();
			}
		});
		add(difficulty);

		// add label
		JLabel diffLabel = new JLabel("Difficulty:");
		diffLabel.setSize(w, h);
		diffLabel.setLocation(745, -5);
		add(diffLabel);
	}

	private void addSolve() { // add Give Up button
		JButton solve = new JButton("Give Up");
		solve.setSize(w, h);
		solve.setLocation(640, 70);

		solve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Solver(DELAY).execute(); // solve the maze slowly
			}
		});
		solve.setFocusable(false);
		add(solve);
	}

	private void addQuit() { // add Quit Game button
		JButton exit = new JButton("Quit Game");
		exit.setSize(w, h);
		exit.setLocation(740, 70);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				won.setVisible(false);
				won.dispose();
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
		add(exit);
	}

	private void addW() { // add W button
		JButton W = new JButton("W");
		W.setSize(w / 2, h);
		W.setFocusable(false);
		W.setLocation(710, 170);
		W.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scene.movePlayer(KeyEvent.VK_W);
			}
		});
		W.setFocusable(false);
		add(W);
	}

	private void addS() {
		JButton S = new JButton("S");
		S.setSize(w / 2, h);
		S.setLocation(710, 220);
		S.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scene.movePlayer(KeyEvent.VK_S);
				checkWin();
			}
		});
		S.setFocusable(false);
		add(S);
	}

	private void addA() {
		JButton A = new JButton("A");
		A.setSize(w / 2, h);
		A.setLocation(660, 220);
		A.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scene.movePlayer(KeyEvent.VK_A);
				checkWin();
			}
		});
		A.setFocusable(false);
		add(A);
	}

	private void addD() {
		JButton D = new JButton("D");
		D.setSize(w / 2, h);
		D.setLocation(760, 220);
		D.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				scene.movePlayer(KeyEvent.VK_D);
				checkWin();
			}
		});
		D.setFocusable(false);
		add(D);
	}

	private void addTutorial() {
		String howToText = "<html><B>How to play:</B><br>" + "Click or press W to move up<br>"
				+ "Click or press S to move down<br>" + "Click or press A to move left<br>"
				+ "Click or press D to move right<br>" + "Reach the red square to win!</html>";
		JLabel howTo = new JLabel(howToText);
		howTo.setSize(w * 2, h * 4);
		howTo.setLocation(645, 270);
		add(howTo);
	}

	private void addInfo() {
		String infoText = "<html><font size=\"3\">––––––––––––––––––––––––––––––––<br>"
				+ "Developed by <I>Ahmad Rajeh</I> in fulfillment of"
				+ " the requirements for the final project in CS2300.<br>"
				+ "Professor: <I>Jason Fritts</I></font><html>";
		JLabel info = new JLabel(infoText);
		info.setSize(w * 2, h * 2);
		info.setLocation(645, 572);
		add(info);

	}

	// SwingWorker class used by addSolve() for showing the solution
	// progressively
	private class Solver extends SwingWorker<Integer, Integer> {
		int delay;

		Solver(int delay) {
			this.delay = delay;
		}

		protected Integer doInBackground() throws Exception {
			scene.solve(delay);
			return 1;
		}
	}

}
