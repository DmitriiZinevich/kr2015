import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		final SeaBattle game = new SeaBattle();

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				SeaBattleSwing sB = new SeaBattleSwing();
				sB.addMenu();
				sB.drawGameField(game.getPlayerField(), game.getComputerField());
				game.setSeaBattleSwing(sB);

			}
		});

		game.play();

	}

}