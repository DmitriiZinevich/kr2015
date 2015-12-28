import java.util.Random;

public class Field {
	public static final int FIELD_ROW_SIZE = 10;
	public static final int FIELD_COL_SIZE = 10;

	private Cell[][] field = new Cell[FIELD_ROW_SIZE][FIELD_COL_SIZE];
	public static final int FOUR_BOAT_SHIPS_COUNT = 1;
	public static final int THREE_BOAT_SHIPS_COUNT = 2;
	public static final int TWO_BOAT_SHIPS_COUNT = 3;
	public static final int ONE_BOAT_SHIPS_COUNT = 4;
	public static final int ALL_SHIPS_CELLS_COUNT = ONE_BOAT_SHIPS_COUNT
			* Ship.ONE_BOAT_SIZE + TWO_BOAT_SHIPS_COUNT * Ship.TWO_BOAT_SIZE
			+ THREE_BOAT_SHIPS_COUNT * Ship.THREE_BOAT_SIZE
			+ FOUR_BOAT_SHIPS_COUNT * Ship.FOUR_BOAT_SIZE;

	public Field() {
		regenerateForGame();
	}

	private void regenerateForGame() {

		for (int i = 0; i < FIELD_COL_SIZE; i++) {
			for (int j = 0; j < FIELD_ROW_SIZE; j++) {
				Cell cell = new Cell(i, j, null);
				field[i][j] = cell;
			}
		}

		addFourBoat();
		addThreeBoats();
		addTwoBoats();
		addOneBoats();
	}

	public void addOneBoats() {
		int i = 0;
		while (i < ONE_BOAT_SHIPS_COUNT) {
			int x = getRandomCoordinate();
			int y = getRandomCoordinate();
			if (!shipInPlace(x, y, false, Ship.ONE_BOAT_SIZE)) {
				addOneBoat(x, y);
				i++;
			}
		}
	}

	public void addTwoBoats() {
		int i = 0;
		while (i < TWO_BOAT_SHIPS_COUNT) {
			int x = getRandomCoordinate();
			int y = getRandomCoordinate();
			boolean rotation = rotationShipGenerate();
			int x1 = calculateXCoordinate(x, rotation);
			int y1 = calculateYCoordinate(y, rotation);
			if ((x1 != 10) && (y1 != 10))
				if (!shipInPlace(x, y, rotationShipGenerate(),
						Ship.TWO_BOAT_SIZE)) {
					addTwoBoat(x, y, x1, y1);
					i++;
				}
		}
	}

	public void addThreeBoats() {
		int i = 0;
		while (i < THREE_BOAT_SHIPS_COUNT) {
			int x = getRandomCoordinate();
			int y = getRandomCoordinate();
			boolean rotation = rotationShipGenerate();
			int x1 = calculateXCoordinate(x, rotation);
			int y1 = calculateYCoordinate(y, rotation);
			int x2 = calculateXCoordinate(x1, rotation);
			int y2 = calculateYCoordinate(y1, rotation);
			if ((x1 != 10) && (y1 != 10) & (x2 != 10) && (y2 != 10))
				if (!shipInPlace(x, y, rotation, Ship.THREE_BOAT_SIZE)) {
					addThreeBoat(x, y, x1, y1, x2, y2);
					i++;
				}
		}
	}

	public void addFourBoat() {
		int i = 0;
		while (i < FOUR_BOAT_SHIPS_COUNT) {
			int x = getRandomCoordinate();
			int y = getRandomCoordinate();
			boolean rotation = rotationShipGenerate();
			int x1 = calculateXCoordinate(x, rotation);
			int y1 = calculateYCoordinate(y, rotation);
			int x2 = calculateXCoordinate(x1, rotation);
			int y2 = calculateYCoordinate(y1, rotation);
			int x3 = calculateXCoordinate(x2, rotation);
			int y3 = calculateYCoordinate(y2, rotation);
			if ((x1 != 10) && (y1 != 10) && (x2 != 10) && (y2 != 10)
					&& (x3 != 10) && (y3 != 10))
				if (!shipInPlace(x, y, rotation, Ship.FOUR_BOAT_SIZE)) {
					addFourBoat(x, y, x1, y1, x2, y2, x3, y3);
					i++;
				}
		}
	}

	private boolean shipInPlace(int x, int y, boolean rotation, int boatSize) {
		int x1, y1;
		int i, j;
		if (rotation) {
			i = boatSize;
			j = 3;
		} else {
			i = 3;
			j = boatSize;
		}

		for (x1 = -1; x1 <= i; x1++)
			for (y1 = -1; y1 <= j; y1++) {
				if ((x + x1) >= 0 && (x + x1) <= 9 && (y + y1) >= 0
						&& (y + y1) <= 9) {
					if (field[x + x1][y + y1].isShip()) {
						return true;
					}
				}
			}

		return false;
	}

	public static int getRandomCoordinate() {
		Random random = new Random();
		return random.nextInt(10);
	}

	private static boolean rotationShipGenerate() {
		return new Random().nextBoolean();
	}

	public void addOneBoat(int x, int y) {
		OneBoat b1 = new OneBoat(x, y);
		field[x][y] = b1.cells[0];
	}

	public void addTwoBoat(int x1, int y1, int x2, int y2) {
		TwoBoat b2 = new TwoBoat(x1, y1, x2, y2);
		field[x1][y1] = b2.cells[0];
		field[x2][y2] = b2.cells[1];
	}

	public void addThreeBoat(int x1, int y1, int x2, int y2, int x3, int y3) {
		ThreeBoat b3 = new ThreeBoat(x1, y1, x2, y2, x3, y3);
		field[x1][y1] = b3.cells[0];
		field[x2][y2] = b3.cells[1];
		field[x3][y3] = b3.cells[2];
	}

	public void addFourBoat(int x1, int y1, int x2, int y2, int x3, int y3,
			int x4, int y4) {
		FourBoat b4 = new FourBoat(x1, y1, x2, y2, x3, y3, x4, y4);
		field[x1][y1] = b4.cells[0];
		field[x2][y2] = b4.cells[1];
		field[x3][y3] = b4.cells[2];
		field[x4][y4] = b4.cells[3];
	}

	public int calculateXCoordinate(int x1, boolean rotation) {
		int x2;

		if (!rotation) {
			x2 = x1;
		} else {
			x2 = x1 + 1;
		}

		if ((x2 < 0) || (x2 > 9)) {
			return 10;
		} else {
			return x2;
		}
	}

	public int calculateYCoordinate(int y1, boolean rotation) {
		int y2;

		if (!rotation) {
			y2 = y1 + 1;
		} else {
			y2 = y1;
		}

		if ((y2 < 0) || (y2 > 9)) {

			return 10;
		} else {
			return y2;
		}
	}

	public Cell[][] getFieldMap() {
		return field;
	}

}
