package game;

import config.Config;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends ImageView {
	private int posX;
	private int posY;
	private boolean alive;
	private ImageView image;
	private boolean movingUp;
	private boolean movingDown;
	private boolean movingLeft;
	private boolean movingRight;
	
	public Player() {
		super(new Image("/Assets/mario.png", 50, 50, false, false));
		posX = 400;
		posY = 400;
		super.relocate(posX, posY);
		alive = true;
	}
	
	public void updatePos() {
		super.relocate(posX, posY);
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		if(posX < 0) this.posX = 0;
		else { if(posX >= Config.SCREEN_WIDTH-Config.PLAYER_WIDTH) this.posX = Config.SCREEN_WIDTH-Config.PLAYER_WIDTH;
		else this.posX = posX;}
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		if(posY < 0) this.posY = 0;
		else { if(posY >= Config.SCREEN_HEIGHT-Config.PLAYER_HEIGHT) this.posY = Config.SCREEN_HEIGHT-Config.PLAYER_HEIGHT;
		else this.posY = posY;}
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}
	
	
}
