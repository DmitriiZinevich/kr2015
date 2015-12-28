import javax.swing.*;
import java.awt.*;

public class SwingField extends JComponent {
	public static int SECTOR_COUNT = 10;
	public static int HEAD = 20;
	public static int HEIGHT = 350;
	public static int WIDTH = 350;

	private Sector[][] sectors;
	private Sector selectedSector;
	private Field field;
	private SeaBattleSwing game;

	private boolean gameEnd = false;
	private boolean isOpen;

	public SwingField(boolean isOpen, SeaBattleSwing game) {
		this.isOpen = isOpen;
		this.game = game;

		
		sectors = new Sector[SECTOR_COUNT][SECTOR_COUNT];
		for (int i = 0; i < SECTOR_COUNT; i++)
			for (int j = 0; j < SECTOR_COUNT; j++)
				sectors[i][j] = Sector.sectorCreate(this, i, j);

		selectedSector = null;

		setSize(WIDTH, HEIGHT);
		printLetNum();
	}

	public SeaBattleSwing getGame() {
		return this.game;
	}

	public Sector[][] getSectors() {
		return this.sectors;
	}

	public Field getField() {
		return this.field;
	}

	public void setField(Field field) {
		this.field = field;
	}


	public void setSelected(Sector sector) {
		selectedSector = sector;
		repaint();
	}

	public boolean isOpen() {
		return isOpen;
	}

	private void printLetNum() {
		char[] letters = { 'À', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J' };
		for (int i = 0; i < SECTOR_COUNT; i++) {
			JLabel horizontal = new JLabel(String.valueOf(i + 1));
			if (i < letters.length)
				horizontal.setText(String.valueOf(letters[i]));
			JLabel vertical = new JLabel(String.valueOf(i + 1));
			horizontal.setHorizontalAlignment(JLabel.CENTER);
			horizontal.setVerticalAlignment(JLabel.CENTER);
			vertical.setHorizontalAlignment(JLabel.CENTER);
			vertical.setVerticalAlignment(JLabel.CENTER);
			horizontal.setSize(Sector.WIDTH, HEAD);
			vertical.setSize(HEAD, Sector.HEIGHT);
			horizontal.setLocation(HEAD + (Sector.WIDTH) * i, 0);
			vertical.setLocation(0, HEAD + (Sector.HEIGHT) * i);
			add(horizontal);
			add(vertical);
		}
	}

	public void setGameEnd() {
		this.gameEnd = true;
	}

	public void printField() {
		Cell[][] cells = this.field.getFieldMap();
		for (int i = 0; i < SECTOR_COUNT; i++) {
			for (int j = 0; j < SECTOR_COUNT; j++) {
				if (cells[i][j].isShip()) {
					sectors[i][j].setShip();
					sectors[i][j].repaint();
				} else if (cells[i][j].isFired()) {
					sectors[i][j].setAttacked();
				}
			}
		}
	}

}