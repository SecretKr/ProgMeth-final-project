package game;

import java.util.ArrayList;

import config.Config;
import items.Item;
import javafx.application.Platform;

public class GameCore {
	private Player player;

	public GameCore(Player player) {
		this.player = player;
	}

	public void gameLoop() {
		float speed = Config.DEFAULT_PLAYER_SPEED;
		Enemy a = null;
		Item b = null;
		player.setPosX(player.getPosX() + speed*player.getMovementX());
		player.setPosY(player.getPosY() + speed*player.getMovementY());
		player.updatePos();
		player.draw();
		
		player.setEntityCounter(player.getEntityCounter()+1);
		if(player.getEntityCounter() >= 20) {
			player.changeEntityAnimation();
			player.setEntityCounter(0);
		}
		if(player.getHP() <= 0) {
			//restart
		}
		
		//if(player.isCollideItem(null))
		
		for(Item item: Main.getItems()) {
			if(player.isCollideItem(item)) {
				item.useItem();
				b = item;
			}
		}
		if(Main.getItems().contains(b)) Main.removeItem(b);
		
		speed = Config.DEFAULT_ENEMY_SPEED;
		for(Enemy enemy: Main.getEnemies()) {
			float dx = player.getPosX() - enemy.getPosX();
			float dy = player.getPosY() - enemy.getPosY();
			float sum = Math.abs(dx) + Math.abs(dy);
			if(Math.abs(dx) > 25 || Math.abs(dy) > 25 ) {
				enemy.setPosX(enemy.getPosX() + speed*(dx/(sum)));
				enemy.setPosY(enemy.getPosY() + speed*(dy/(sum)));
				enemy.updatePos();
				
			}
			
			if(player.isCollideEntity(enemy)) {
				System.out.println("HIT!!");
				enemy.setHP(enemy.getHP()-1); 
				player.setHP(player.getHP()-enemy.getHitDamage()); // both enemy and player take damage
			}
			
			if(enemy.getHP() <= 0) {
				//System.out.println("HIT!!");
				//Main.getEnemies().remove(enemy);
				//Main.getEnemies().clear();
				a = enemy; // store which enemy has to be remove to remove after list iteration
			}
			
		
			
		}
		if(Main.getEnemies().contains(a)) Main.removeEnemy(a); // remove
		//break;
		//updateAllPos();
	}

	public void executeGameCore() {
		try {
			int counter = 0;
			Main.addBomb(500,500);
			while (true) {
				Thread.sleep(Config.DELAY_BETWEEN_FRAME);
				this.gameLoop();
				if(counter%200 == 0) {
					addEnemy();
				}
				/*if(counter%500 == 0) { // add bomb randomly
					//float randomPosX = (float) (Math.random() * (Config.SCREEN_WIDTH + 1));
					//float randomPosY = (float) (Math.random() * (Config.SCREEN_HEIGHT + 1));
					float randomPosX = (float) Math.floor(Math.random() * (Config.SCREEN_WIDTH - 0 + 1) + 0);
					float randomPosY = (float) Math.floor(Math.random() * (Config.SCREEN_HEIGHT - 0 + 1) + 0);
					Main.addBomb(randomPosX, randomPosY);
				}*/
				counter += 1;
			}
		} catch (Exception e) {
		}
	}

	private static void addEnemy() {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.addEnemy();
			}
		});
	}
	

}