package game;

import java.util.ArrayList;

import config.Config;
import item.Bomb;
import item.Item;
import javafx.application.Platform;
import weapon.BaseWeapon;
import weapon.homing.BaseHoming;
import weapon.rock.BaseRock;
import weapon.rock.RockLevelOne;

public class GameCore {
	private Player player;
	private StatusBar statusBar;
	
	public GameCore(Player player, StatusBar statusBar) {
		this.player = player;
		this.statusBar = statusBar;
	}

	public void gameLoop() {
		float speed = Config.DEFAULT_PLAYER_SPEED;
		player.setPosX(player.getPosX() + speed*player.getMovementX());
		player.setPosY(player.getPosY() + speed*player.getMovementY());
		player.updatePos();
		player.draw();
		player.setEntityCounter(player.getEntityCounter()+1);
		
		if(player.getEntityCounter() >= 20) {
			player.changeEntityAnimation();
			player.setEntityCounter(0);
		}
		if(player.getHP() == 0) {
			//restart
			//player.addWeapon(new RockLevelOne());
			//upgradeRock((BaseRock) player.getWeapons().get(player.getRockIndex()), player.getPosX(),player.getPosY());
			//addHoming(2, player.getPosX(),player.getPosY());
			//player.setHP(101);
			//addRock(1,20,20);
			//player.setHP(150);
		}
		
		if(player.getXP() >= player.getLevel() * player.getLevel() * 10) { // level up
			System.out.println("lv up");
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
				useItemLater(item);
				removeItemLater(item);
			}
		}
		
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
				updateStatusBar();
			}
			
			if(enemy.getHP() <= 0) {
				player.setXP(player.getXP()+1);
				EntityController.increaseEnemyKilled();
				removeEnemyLater(enemy);
				System.out.println(Integer.toString(EntityController.getEnemyKilled()) + "/" + Integer.toString(EntityController.getEnemyAmountMax()));
			}
			
		
			
		}
		//break;
		//updateAllPos();
		
		if(nearestEnemy != null) {
			
			for(BaseWeapon weapon:player.getWeapons()) {
				if(weapon.getLevel() == 0) continue;
				if(weapon instanceof BaseRock) {
					attackRock((BaseRock) weapon, nearestEnemy);
					
				}
				
				else if(weapon instanceof BaseHoming) {		
					Entity nearestNextEnemy = ((BaseHoming)weapon).getNearestNextEnemy();
					if(((BaseHoming)weapon).getCurrentEntity() == null) {
						((BaseHoming)weapon).setCurrentEntity(farthestEnemy);
						((BaseHoming)weapon).setStatus(true);
					}
					attackHoming((BaseHoming) weapon, nearestNextEnemy);
				}
				
			}
			/*
			if(nearestEnemy.getHP() <= 0) {
				player.setXP(player.getXP()+1);
				EntityController.increaseEnemyKilled();
				System.out.println(Integer.toString(EntityController.getEnemyKilled()) + "/" + Integer.toString(EntityController.getEnemyAmountMax()));
				removeEnemyLater(nearestEnemy);
			}*/
		}
		
		if(EntityController.getEnemyAmountMax() == EntityController.getEnemyKilled()) {
			EntityController.updateWave();
			Asset.nextWave.play();
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
				if(counter%40 == 0 && EntityController.isSpawnable() ) {
					float randomPosX = (float) Math.floor(Math.random() * (Config.SCREEN_WIDTH - 0 + 1) + 0);
					float randomPosY = (float) Math.floor(Math.random() * (Config.SCREEN_HEIGHT - 0 + 1) + 0);
					EntityController.increaseEnemyAmount();
					addEnemy(randomPosX, randomPosY, 1);
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
	
	private void initializeWeapon() {
		addRock(0, player.getPosX(), player.getPosY());
		addHoming(0,  player.getPosX(), player.getPosY());
	}
	
	private static void addBomb(float posX, float posY) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.addBomb(posX, posY);
			}
		});
	}
	
	public void updateStatusBar() {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				statusBar.setHp(player.getHP());
				statusBar.setXp(player.getXP());
				statusBar.setWave(EntityController.getWave());
				statusBar.update();
			}
		});
	}
	
	public void removeItemLater(Item b) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Main.removeItem(b);
			}
		});
	}
	
	public void removeEnemyLater(Enemy a) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Main.removeEnemy(a);
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

	private static void addEnemy(float posX, float posY,int hP) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.addEnemy(posX, posY, hP);
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
	
	private void attackRock(BaseRock rock, Entity nearestEnemy) {
		
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				if(rock.getLevel() == 0) return;
				if(rock.getStatus() && Main.getEnemies().contains(rock.getCurrentEntity())) { // has target
					rock.changePosition(rock.getCurrentEntity());// rock is going to the enemy
					if(rock.isCollideEntity(rock.getCurrentEntity())) { // rock hit the target
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
	
	/*
	private static void resetHomingLater(BaseHoming homing, float posX, float posY) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Main.resetHoming(homing ,posX, posY);
			}
		});
	}*/
	

}