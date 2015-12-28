public class Ship {
	public static final int ONE_BOAT_SIZE = 1;
	public static final int TWO_BOAT_SIZE = 2;
	public static final int THREE_BOAT_SIZE = 3;
	public static final int FOUR_BOAT_SIZE = 4;
	public static boolean isLife = true;

	protected Cell[] cells;

	public boolean deadShip() {
		for (Cell cell : cells) {
			if (!cell.isFired()) {
				return false;
			}
		}

		return true;
	}

}
