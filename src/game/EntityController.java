package game;

public class EntityController {
	private static int enemyAmount = 0;
	private static int enemyAmountMax = 5;
	private static int enemyKilled = 0;
	private static int wave = 1;
	
	
	public static boolean isSpawnable() {
		if(EntityController.getEnemyAmount() < EntityController.getEnemyAmountMax()) {
			return true;
		}
		return false;
	}
	
	public static void updateWave() {
		EntityController.increaseWave();
		EntityController.setEnemyAmount(0);
		EntityController.setEnemyKilled(0);
		int enemyMax = 10 * EntityController.getWave();
		EntityController.setEnemyAmountMax(enemyMax);
		
	}
	
	
	public static int getEnemyAmount() {
		return enemyAmount;
	}
	public static void setEnemyAmount(int enemyAmount) {
		EntityController.enemyAmount = enemyAmount;
	}
	public static void increaseEnemyAmount() {
		EntityController.enemyAmount = EntityController.getEnemyAmount() + 1;
	}
	
	public static int getEnemyAmountMax() {
		return enemyAmountMax;
	}
	public static void setEnemyAmountMax(int enemyAmountMax) {
		EntityController.enemyAmountMax = enemyAmountMax;
	}
	public static int getEnemyKilled() {
		return enemyKilled;
	}
	public static void setEnemyKilled(int enemyKilled) {
		EntityController.enemyKilled = enemyKilled;
	}
	
	public static void increaseEnemyKilled() {
		EntityController.enemyKilled = EntityController.getEnemyKilled() + 1;
	}

	public static int getWave() {
		return wave;
	}

	public static void setWave(int wave) {
		EntityController.wave = wave;
	}
	public static void increaseWave() {
		EntityController.wave = EntityController.getWave() + 1;
	}
	
	
	
	
	
	
}
