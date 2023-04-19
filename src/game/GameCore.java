package game;

import config.Config;

public class GameCore {
	private Player player;

	public GameCore(Player player) {
		this.player = player;
	}

	public void gameLoop() {
		int moveDirections = 0;
		double speed = Config.DEFAULT_SPEED;
		if(player.isMovingUp()) moveDirections += 1;
		if(player.isMovingRight()) moveDirections += 1;
		if(player.isMovingDown()) moveDirections += 1;
		if(player.isMovingLeft()) moveDirections += 1;
		if(moveDirections >= 2) speed = Config.DEFAULT_SPEED * 0.707f;
		if(player.isMovingUp()) player.setPosY(player.getPosY() - speed);
		if(player.isMovingRight()) player.setPosX(player.getPosX() + speed);
		if(player.isMovingDown()) player.setPosY(player.getPosY() + speed);
		if(player.isMovingLeft()) player.setPosX(player.getPosX() - speed);
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