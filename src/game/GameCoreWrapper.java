package game;

import java.util.ArrayList;

public class GameCoreWrapper extends Thread {
	private GameCore gameCore;

	public GameCoreWrapper(Player player, StatusBar statusBar) {
		gameCore = new GameCore(player,statusBar);
	}
	
	@Override
	public void run() {
		gameCore.executeGameCore();
	}
}
