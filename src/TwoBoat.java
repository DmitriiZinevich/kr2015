public class TwoBoat extends Ship {

	TwoBoat(int x1, int y1, int x2, int y2) {
		cells = new Cell[TWO_BOAT_SIZE];
		cells[0] = new Cell(x1, y1, this);
		cells[1] = new Cell(x2, y2, this);
	}
}