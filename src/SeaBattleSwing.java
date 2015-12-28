import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class SeaBattleSwing extends JFrame {

	private SwingField playerField;
	private SwingField computerField;

	public SeaBattleSwing() {
		setTitle("Sea Battle");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(SwingField.WIDTH * 2 + 100, SwingField.HEIGHT + 100);

		playerField = new SwingField(true, this);
		computerField = new SwingField(false, this);

		add(playerField);
		add(computerField);

		playerField.setLocation(20, 20);
		computerField.setLocation(SwingField.WIDTH + 50, 20);

		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	public SwingField getPlayerField() {
		return playerField;
	}

	public SwingField getComputerField() {
		return computerField;
	}

	public void addMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu menuFile = new JMenu("File");

		JMenuItem eMenuFileItemExit = new JMenuItem("Exit");
		eMenuFileItemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		menuFile.add(eMenuFileItemExit);
		menu.add(menuFile);
		setJMenuBar(menu);
	}

	public void drawGameField(Field playerFieldMap, Field computerFieldMap) {
		playerField.setField(playerFieldMap);
		computerField.setField(computerFieldMap);
		playerField.printField();
	}

}