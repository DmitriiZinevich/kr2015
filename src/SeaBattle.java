import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class SeaBattle {

	public static final int PAUSE = 10;

	private Field playerField;
	private Field computerField;

	private SeaBattleSwing seaBattleSwing;

	public static boolean userShoot;
	public static boolean userKill;

	public SeaBattle() {
		playerField = new Field();
		computerField = new Field();
	}

	public void setSeaBattleSwing(SeaBattleSwing seaBattleSwing) {
		this.seaBattleSwing = seaBattleSwing;
	}

	public SeaBattleSwing getSeaBattleSwing() {
		return seaBattleSwing;
	}

	public Field getPlayerField() {
		return this.playerField;
	}

	public Field getComputerField() {
		return this.computerField;
	}

	public void play() {
		boolean userWin = false;
		boolean computerWin = false;

		do {
			userShoot = false;
			waitUserAttack();

			userWin = checkWin(computerField.getFieldMap());
			if (userWin) {
				continue;
			}

			if (userKill) {
				userKill = false;
				continue;
			}

			while (computerAttack()) {
				try {
					TimeUnit.MILLISECONDS.sleep(PAUSE);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			computerWin = checkWin(playerField.getFieldMap());

		} while (!userWin && !computerWin);

		if (computerWin) {
			showMessage("You lose", false);
		} else {
			showMessage("You win", true);
		}

	}

	private boolean computerAttack() {
		int x = Field.getRandomCoordinate();
		int y = Field.getRandomCoordinate();

		SwingField pField = seaBattleSwing.getPlayerField();
		Sector sector = pField.getSectors()[x][y];

		Cell[][] cells = playerField.getFieldMap();
		Cell cell = cells[x][y];

		while (cell.isFired()) {
			x = Field.getRandomCoordinate();
			y = Field.getRandomCoordinate();
			cell = cells[x][y];
		}

		cell.setWasFired();

		sector.setAttacked();
		if (cell.isShip()) {
			sector.setShip();
		}
		sector.repaint();
		return cell.isShip();
	}

	private void showMessage(String message, boolean userWin) {
		if (userWin) {
			seaBattleSwing.getComputerField().setGameEnd();
			seaBattleSwing.getComputerField().repaint();
		} else {
			SwingField playerField = seaBattleSwing.getPlayerField();
			playerField.setGameEnd();
			playerField.repaint();
		}

		Object[] options = { "Yes", "No" };
		int result = JOptionPane.showOptionDialog(seaBattleSwing
				.getComputerField().getParent(), "Enjoy game?", message,
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[1]);

		if (result == 0) {
			System.out.println("Reloading");
		}

	}

	public static void waitUserAttack() {
		do {
			try {
				TimeUnit.MILLISECONDS.sleep(PAUSE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!userShoot);
	}

	public boolean checkWin(Cell[][] map) {
		int firedShips = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Cell cell = map[i][j];
				if (cell.isShip() && cell.isFired()) {
					firedShips++;
					if (firedShips == Field.ALL_SHIPS_CELLS_COUNT) {
						return true;
					}
				}
			}
		}

		return false;
	}

}