public class OneBoat extends Ship {

	OneBoat(int x, int y) {
		cells = new Cell[ONE_BOAT_SIZE];
		cells[0] = new Cell(x, y, this);
	}
}