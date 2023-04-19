package game;

import java.util.ArrayList;

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
