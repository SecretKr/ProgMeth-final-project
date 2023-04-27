package item;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Item extends ImageView{
	private float posX;
	private float posY;
	private ImageView image;
	private int width;
	private int height;
	
	
	public Item(Image image2,float posX, float posY) {
		super(image2);
		this.setPosX(posX);
		this.setPosY(posY);
		super.relocate(posX, posY);
	}
	
	public abstract void useItem();
	
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
