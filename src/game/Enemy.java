package game;

import config.Config;

public class Enemy extends Entity {
	public Enemy(float posX, float posY, int hP) {
		super(Asset.enemy.get(0), posX, posY, hP);
		setAlive(true);
		setHP(hP);
		setWidth(Config.ENEMY_WIDTH);
		setHeight(Config.ENEMY_HEIGHT);
		setHitDamage(10);
	}

	public void draw() {
		this.setImage(Asset.enemy.get(getEntityNum()));
	}

	public int getEntityNumMax() {
		return Asset.enemyFrame;
	}
}
