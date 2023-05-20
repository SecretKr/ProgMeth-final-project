package weapon.rock;

import game.Entity;
import javafx.scene.image.Image;
import weapon.BaseWeapon;

public class BaseRock extends BaseWeapon{
	
	private int speed;
	private boolean status; // true = there is currently enemy the rock is targeting
	private Entity currentEntity;
	
	public BaseRock(Image image2,float posX, float posY, int level) {
		super(image2, posX, posY);
		//setPosX((Config.SCREEN_WIDTH-30)/2);
		//setPosY((Config.SCREEN_HEIGHT-30)/2);
		this.setStatus(false);
		this.setCurrentEntity(null);
		this.setLevel(level);
		
	}

	@Override
	public void changePosition(Entity entity) {
		speed = this.getSpeed();
		float dx = entity.getPosX() - this.getPosX();
		float dy = entity.getPosY() - this.getPosY();
		float sum = Math.abs(dx) + Math.abs(dy);
		if(Math.abs(dx) > 25 || Math.abs(dy) > 25 ) {
			float newX = this.getPosX() + speed*(dx/(sum));
			float newY = this.getPosY() + speed*(dy/(sum));
			this.setPosX(newX);
			this.setPosY(newY);
			this.relocate(newX,newY);
			
		}
		
	}
	
	public String toString() {
		return "Rock lv." + this.getLevel();
	}
	
	public void changePositionTo(float posX, float posY) {
		this.relocate(posX, posY);
		this.setPosX(posX);
		this.setPosY(posY);
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Entity getCurrentEntity() {
		return currentEntity;
	}

	public void setCurrentEntity(Entity entity) {
		this.currentEntity = entity;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
