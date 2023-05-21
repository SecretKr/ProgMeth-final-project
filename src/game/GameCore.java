package game;

import config.Config;
import item.Bomb;
import item.Item;
import item.Potion;
import javafx.application.Platform;
import weapon.BaseWeapon;
import weapon.homing.BaseHoming;
import weapon.rock.BaseRock;

public class GameCore {
	private Player player;
	private StatusBar statusBar;
	private boolean exit = false;
	
	public GameCore(Player player, StatusBar statusBar) {
		this.player = player;
		this.statusBar = statusBar;
	}

	public void gameLoop() {
		float speed = Config.DEFAULT_PLAYER_SPEED;
		Enemy tempEnemy = null;
		Item tempItem = null;
		BaseRock tempRock = null;
		BaseHoming tempHoming = null;
		
		player.setPosX(player.getPosX() + speed*player.getMovementX());
		player.setPosY(player.getPosY() + speed*player.getMovementY());
		player.updatePos();
		player.draw();
		player.setEntityCounter(player.getEntityCounter()+1);
		updateStatusBar();
		
		if(player.getEntityCounter() >= 20) {
			player.changeEntityAnimation();
			player.setEntityCounter(0);
		}
		if(player.getHP() == 0) {
			exit = true;
		}
		
		if(player.getXP() >= player.getLevel() * player.getLevel() * 10) { // level up
			player.setLevel(player.getLevel() + 1);
			int IndexWeaponToUpgrade = Util.randomWeapon(player);
			if(IndexWeaponToUpgrade != -1) {
				BaseWeapon weaponToUpgrade = player.getWeapons().get(IndexWeaponToUpgrade);
				if(weaponToUpgrade instanceof BaseRock) {
					upgradeRock((BaseRock) player.getWeapons().get(player.getRockIndex()), player.getPosX(),player.getPosY());
				}
				else if(weaponToUpgrade instanceof BaseHoming) {
					upgradeHoming((BaseHoming) player.getWeapons().get(player.getHomingIndex()), player.getPosX(),player.getPosY());
				}
			}
		}
		
		
		
		for(Item item: Main.getItems()) {
			if(player.isCollideItem(item)) {
				if(item instanceof Bomb) {
					int enemyAmount = Main.getEnemies().size();
					EntityController.setEnemyKilled(EntityController.getEnemyKilled() + enemyAmount);
					player.setXP(player.getXP() + enemyAmount);
					Asset.bomb.play();
				}
				
				if(item instanceof Potion) {
					player.setHP(Config.PLAYER_HP);
					Asset.heal.play();
				}
				tempItem = item;
				useItemLater(tempItem);
			}
		}
		
		if(tempItem!=null) removeItemLater(tempItem);
		
		speed = Config.DEFAULT_ENEMY_SPEED;
		Enemy nearestEnemy = null;
		double nearestDistance = 1e9;
		Enemy farthestEnemy = null;//farthest
		double farthestDistance = -1;
		for(Enemy enemy: Main.getEnemies()) {
			float dx = player.getPosX() - enemy.getPosX();
			float dy = player.getPosY() - enemy.getPosY();
			double distance = Math.sqrt((dx*dx)+(dy*dy));
			if(distance < nearestDistance) {
				nearestDistance = distance;
				nearestEnemy = enemy;
			}
			if(distance > farthestDistance) {
				farthestDistance = distance;
				farthestEnemy = enemy;
			}
			float sum = Math.abs(dx) + Math.abs(dy);
			if(Math.abs(dx) > 25 || Math.abs(dy) > 25 ) {
				enemy.setPosX(enemy.getPosX() + speed*(dx/(sum)));
				enemy.setPosY(enemy.getPosY() + speed*(dy/(sum)));
				enemy.updatePos();
				enemy.draw();
				enemy.setEntityCounter(enemy.getEntityCounter()+1);
			}
			
			if(player.isCollideEntity(enemy)) {
				enemy.setHP(enemy.getHP()-1);
				player.setHP(player.getHP()-enemy.getHitDamage()); // both enemy and player take damage
				statusBar.setHp(player.getHP());
				Asset.enemyHit.play();
			}
			
			if(enemy.getHP() <= 0) {
				Asset.hit.play();
				tempEnemy = enemy;
			}
		}
		
		if(tempEnemy!=null) {
			player.setXP(player.getXP()+1);
			EntityController.increaseEnemyKilled();
			removeEnemyLater(tempEnemy);
			System.out.println(Integer.toString(EntityController.getEnemyKilled()) + "/" + Integer.toString(EntityController.getEnemyAmountMax()));
		}
			
		if(nearestEnemy != null) {	
			for(BaseWeapon weapon:player.getWeapons()) {
				if(weapon.getLevel() == 0) continue;
				if(weapon instanceof BaseRock) {
					tempRock = (BaseRock) weapon;
				}
				else if(weapon instanceof BaseHoming) {		
					if(((BaseHoming)weapon).getCurrentEntity() == null) {
						((BaseHoming)weapon).setCurrentEntity(farthestEnemy);
						((BaseHoming)weapon).setStatus(true);
					}
					tempHoming = (BaseHoming) weapon;
					
				}	
			}
			if(tempRock != null) {
				attackRock((BaseRock) tempRock, nearestEnemy);
					if(tempRock.getDurability() == 0) {
						downgradeRock((BaseRock) tempRock, player.getPosX(), player.getPosY());
					}
			}
			if(tempHoming != null) {
				attackHoming((BaseHoming) tempHoming, tempHoming.getNearestNextEnemy());
					if(tempHoming.getDurability() == 0) {
						downgradeHoming((BaseHoming) tempHoming, player.getPosX(), player.getPosY());
					}
			}
		}

		if(EntityController.getEnemyAmountMax() <= EntityController.getEnemyKilled()) {
			EntityController.updateWave();
			Asset.nextWave.play();
			//bomb
			float randomPosX = (float) Math.floor(Math.random() * (Config.SCREEN_WIDTH - 0 + 1) + 0);
			float randomPosY = (float) Math.floor(Math.random() * (Config.SCREEN_HEIGHT - 0 + 1) + 0);
			int randint = 0 + (int)(Math.random() * (100 - 0));
			addBomb(randomPosX, randomPosY);
			//potion
			randomPosX = (float) Math.floor(Math.random() * (Config.SCREEN_WIDTH - 0 + 1) + 0);
			randomPosY = (float) Math.floor(Math.random() * (Config.SCREEN_HEIGHT - 0 + 1) + 0);
			if(randint % 10 == 1) { // 10% chance of potion spawning
				addPotion(randomPosX, randomPosY);
			}
			
		}
		
	}

	public void executeGameCore() {
		try {
			int counter = 0;
			addBomb(500,500);
			initializeWeapon();
			while (true) {
				Thread.sleep(Config.DELAY_BETWEEN_FRAME);
				this.gameLoop();
				if(exit) break;
				if(counter%40 == 0 && EntityController.isSpawnable() ) {
					float randomPosX = (float) Math.floor(Math.random() * (Config.SCREEN_WIDTH - 0 + 1) + 0);
					float randomPosY = (float) Math.floor(Math.random() * (Config.SCREEN_HEIGHT - 0 + 1) + 0);
					EntityController.increaseEnemyAmount();
					addEnemy(randomPosX, randomPosY, 1);
				}
				counter += 1;
			}
			exitPageLater();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void initializeWeapon() {
	
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				addRock(0, player.getPosX(), player.getPosY());
				addHoming(0,  player.getPosX(), player.getPosY());
			}
		});
	}
	
	private static void addBomb(float posX, float posY) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.addBomb(posX, posY);
			}
		});
	}
	
	private static void addPotion(float posX, float posY) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.addPotion(posX, posY);
			}
		});
	}
	
	public void exitPageLater() {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.endGame(player.getXP(), EntityController.getWave());
				Asset.gameOver.play();
			}
		});
	}
	
	public void updateStatusBar() {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				statusBar.setHp(player.getHP());
				statusBar.setLevel(player.getLevel());
				statusBar.setXp(player.getXP());
				statusBar.setWave(EntityController.getWave());
				statusBar.update();
			}
		});
	}
	
	public void removeItemLater(Item item) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Main.removeItem(item);
			}
		});
	}
	
	public void removeEnemyLater(Enemy enemy) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Main.removeEnemy(enemy);
			}
		});
	}
	
	public void useItemLater(Item item) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				item.useItem();
			}
		});
	}

	private static void addEnemy(float posX, float posY,int hp) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.addEnemy(posX, posY, hp);
			}
		});
	}
	
	private static void addRock(int level, float posX, float posY) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.addRock(level, posX, posY);
			}
		});
	}
	
	private static void upgradeRock(BaseRock rock, float posX, float posY) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.removeRock(rock);
				Main.addRock(rock.getLevel() + 1, posX, posY);
			}
		});
	}
	
	private static void downgradeRock(BaseRock rock, float posX, float posY) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.removeRock(rock);
				Main.addRock(rock.getLevel() - 1, posX, posY);
			}
		});
	}
	
	private static void resetRockLater(BaseRock rock, float posX, float posY) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.resetRock(rock ,posX, posY);
			}
		});
	}
	
	private static void addHoming(int level, float posX, float posY) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.addHoming(level, posX, posY);
			}
		});
	}

	
	private static void upgradeHoming(BaseHoming homing, float posX, float posY) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.removeHoming(homing);
				Main.addHoming(homing.getLevel() + 1, posX, posY);
			}
		});
	}
	
	private static void downgradeHoming(BaseHoming homing, float posX, float posY) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.removeHoming(homing);
				Main.addHoming(homing.getLevel() - 1, posX, posY);
			}
		});
	}
	
	private void attackRock(BaseRock rock, Entity nearestEnemy) {
		
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				if(rock.getLevel() == 0) return;
				if(rock.getStatus() && Main.getEnemies().contains(rock.getCurrentEntity())) { // has target
					rock.changePosition(rock.getCurrentEntity());// rock is going to the enemy
					if(rock.isCollideEntity(rock.getCurrentEntity())) { // rock hit the target
						rock.decreaseDurability();
						rock.getCurrentEntity().setHP(rock.getCurrentEntity().getHP()-rock.getDamage()); 
						rock.setStatus(false); // set status to no currently target
						resetRockLater(rock, player.getPosX(), player.getPosY());  // reset the rock position after hit
					}
				}
				
				else { // no currently target
					resetRockLater(rock, player.getPosX(), player.getPosY());
					rock.setCurrentEntity(nearestEnemy);
					rock.setStatus(true);
				}
			}
		});
	}
	
	private void attackHoming(BaseHoming homing, Entity nearestNextEnemy) { // just like the rock but there is no reset, so it will move continuously
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				if(homing.getLevel() == 0) return;
				if(homing.getStatus() && Main.getEnemies().contains(homing.getCurrentEntity())) {
					homing.changePosition(homing.getCurrentEntity());
					if(homing.isCollideEntity(homing.getCurrentEntity())) {
						homing.decreaseDurability();
						homing.getCurrentEntity().setHP(homing.getCurrentEntity().getHP()-homing.getDamage());
						homing.setStatus(false);
					}	
				}
				
				else {
					homing.setCurrentEntity(nearestNextEnemy);
					homing.setStatus(true);
				}
			}
		});
	}
}