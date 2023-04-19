package game;

public class GameCoreWrapper extends Thread {
	private GameCore gameCore;

	public GameCoreWrapper(Player player) {
		gameCore = new GameCore(player);
	}
	
	@Override
	public void run() {
		gameCore.executeGameCore();
	}
}
