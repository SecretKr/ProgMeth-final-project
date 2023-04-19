package game;

import config.Config;

public class GameCore {
	private Player player;

	public GameCore(Player player) {
		this.player = player;
	}

	public void gameLoop() {
		if(player.isMovingUp()) player.setPosY(player.getPosY() - Config.DEFAULT_SPEED);
		if(player.isMovingRight()) player.setPosX(player.getPosX() + Config.DEFAULT_SPEED);
		if(player.isMovingDown()) player.setPosY(player.getPosY() + Config.DEFAULT_SPEED);
		if(player.isMovingLeft()) player.setPosX(player.getPosX() - Config.DEFAULT_SPEED);
		player.updatePos();
	}

	public void executeGameCore() {
		try {
			while (true) {
				Thread.sleep(Config.DELAY_BETWEEN_FRAME);
				this.gameLoop();
			}
		} catch (Exception e) {
		}
	}
}