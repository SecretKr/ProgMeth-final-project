package weapon;

import game.Entity;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class BaseWeapon extends ImageView {
	private String name;
	private int damage;
	private int level;
	private float posX;
	private float posY;
	private int width;
	private int height;	
	
	public BaseWeapon(Image image2, float posX, float posY) {
		super(image2);
		this.setPosX(posX);
		this.setPosY(posY);
		super.relocate(posX, posY);
	}
	
	public float getLeftX() {
		return (this.getPosX()-(this.getWidth()/2));
	}
	public float getTopY() {
		return (this.getPosY()-(this.getHeight()/2));
	}
	public float getRightX() {
		return (this.getPosX()+(this.getWidth()/2));
	}
	public float getBottomY() {
		return (this.getPosY()+(this.getHeight()/2));
	}
	
	public abstract void changePosition(Entity entity);
	
	public boolean isCollideEntity(Entity entity){
		float wLeftX = this.getLeftX();
		float eLeftX = entity.getLeftX();
		float wRightX = this.getRightX();
		float eRightX = entity.getRightX();
		float wTopY = this.getTopY();
		float eTopY = entity.getTopY();
		float wBottomY = this.getBottomY();
		float eBottomY = entity.getBottomY();
		
		if((wLeftX <= eRightX && wLeftX >= eLeftX) && (wTopY >= eTopY && wTopY <= eBottomY) // check if top left corner of weapon in enemy area
				|| (eLeftX <= wRightX && eLeftX >= wLeftX) && (eTopY >= wTopY && eTopY <= wBottomY)) { //check if top left corner of enemy in weapon area
			return true;
		}
		
		if((wRightX <= eRightX && wRightX >= eLeftX) && (wTopY >= eTopY && wTopY <= eBottomY) // check if top right corner of weapon in enemy area
				|| (eRightX <= wRightX && eRightX >= wLeftX) && (eTopY >= wTopY && eTopY <= wBottomY)) { //check if top right corner of enemy in weapon area
			return true;
		}
		
		if((wLeftX <= eRightX && wLeftX >= eLeftX) && (wBottomY >= eTopY && wBottomY <= eBottomY) // check if bottom left corner of weapon in enemy area
				|| (eLeftX <= wRightX && eLeftX >= wLeftX) && (eBottomY >= wTopY && eBottomY <= wBottomY)) { //check if bottom left corner of enemy in weapon area
			return true;
		}
		
		if((wRightX <= eRightX && wRightX >= eLeftX) && (wBottomY >= eTopY && wBottomY <= eBottomY) // check if bottom right corner of weapon in enemy area
				|| (eRightX <= wRightX && eRightX >= wLeftX) && (eBottomY >= wTopY && eBottomY <= wBottomY)) { //check if bottom right corner of enemy in weapon area
			return true;
		}
		
		return false;
	}
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
