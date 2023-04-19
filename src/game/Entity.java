package game;

import config.Config;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Entity extends ImageView {
	private float posX;
	private float posY;
	private ImageView image;
	private float movementX;
	private float movementY;
	private boolean alive;
	private int entityNum=0;
	private int entityCounter=0;
	private String direction;
	
	
	
	public Entity(Image image2) {
		super(image2);
	}
	
	public void updatePos() {
		super.relocate((int)posX, (int)posY);
	}
	
	public boolean getAlive() {
		return alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		if(posX < 0) this.posX = 0;
		else { if(posX >= Config.SCREEN_WIDTH-Config.PLAYER_WIDTH) this.posX = Config.SCREEN_WIDTH-Config.PLAYER_WIDTH;
		else this.posX = posX;}
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		if(posY < 0) this.posY = 0;
		else { if(posY >= Config.SCREEN_HEIGHT-Config.PLAYER_HEIGHT) this.posY = Config.SCREEN_HEIGHT-Config.PLAYER_HEIGHT;
		else this.posY = posY;}
	}

	public float getMovementX() {
		return movementX;
	}

	public void setMovementX(float movementX) {
		this.movementX = movementX;
	}

	public float getMovementY() {
		return movementY;
	}

	public void setMovementY(float movementY) {
		this.movementY = movementY;
	}
	
	public void setMovement(float x, float y) {
		this.movementX = x;
		this.movementY = y;
	}
	
	public void changeEntityAnimation() {
		this.setEntityNum((entityNum+1)%2);
	}
	
	public int getEntityNum() {
		return entityNum;
	}
	
	public void setEntityNum(int entityNum) {
		this.entityNum = entityNum;
	}

	public int getEntityCounter() {
		return entityCounter;
	}

	public void setEntityCounter(int entityCount) {
		this.entityCounter = entityCount;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
