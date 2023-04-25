package game;

import config.Config;
import javafx.scene.image.Image;

public class Player extends Entity {
	public Player() {
		super(new Image("/Assets/mario.png", 50, 50, false, false));
		setDirection("down");
		setPosX((Config.SCREEN_WIDTH-Config.PLAYER_WIDTH)/2);
		setPosY((Config.SCREEN_HEIGHT-Config.PLAYER_HEIGHT)/2);
		setAlive(true);
		setHP(100);
		setWidth(Config.PLAYER_WIDTH);
		setHeight(Config.PLAYER_HEIGHT);
		setHitDamage(10);
	}
	
	public boolean isCollide(Entity enemy) {

		float pLeftX = this.getLeftX();
		float eLeftX = enemy.getLeftX();
		float pRightX = this.getRightX();
		float eRightX = enemy.getRightX();
		float pTopY = this.getTopY();
		float eTopY = enemy.getTopY();
		float pBottomY = this.getBottomY();
		float eBottomY = enemy.getBottomY();
		
		if((pLeftX <= eRightX && pLeftX >= eLeftX) && (pTopY >= eTopY && pTopY <= eBottomY) // check if top left corner of player in enemy area
				|| (eLeftX <= pRightX && eLeftX >= pLeftX) && (eTopY >= pTopY && eTopY <= pBottomY)) { //check if top left corner of enemy in player area
			return true;
		}
		
		if((pRightX <= eRightX && pRightX >= eLeftX) && (pTopY >= eTopY && pTopY <= eBottomY) // check if top right corner of player in enemy area
				|| (eRightX <= pRightX && eRightX >= pLeftX) && (eTopY >= pTopY && eTopY <= pBottomY)) { //check if top right corner of enemy in player area
			return true;
		}
		
		if((pLeftX <= eRightX && pLeftX >= eLeftX) && (pBottomY >= eTopY && pBottomY <= eBottomY) // check if bottom left corner of player in enemy area
				|| (eLeftX <= pRightX && eLeftX >= pLeftX) && (eBottomY >= pTopY && eBottomY <= pBottomY)) { //check if bottom left corner of enemy in player area
			return true;
		}
		
		if((pRightX <= eRightX && pRightX >= eLeftX) && (pBottomY >= eTopY && pBottomY <= eBottomY) // check if bottom right corner of player in enemy area
				|| (eRightX <= pRightX && eRightX >= pLeftX) && (eBottomY >= pTopY && eBottomY <= pBottomY)) { //check if bottom right corner of enemy in player area
			return true;
		}
		
		return false;
		
	}
	
	public void draw() {
		//System.out.println("count:"+this.getEntityCounter());
		Image image = null;
		
		if(getDirection() == "up") {
			if(getEntityNum()==0) {
				image = new Image("Assets/up1.png", 50, 50, false, false);
			}
			else if(getEntityNum()==1) {
				image = new Image("Assets/up2.png", 50, 50, false, false);
			}
		}
		else if(getDirection() == "down") {
			if(getEntityNum()==0) {
				image = new Image("Assets/down1.png", 50, 50, false, false);
			}
			else if(getEntityNum()==1) {
				image = new Image("Assets/down2.png", 50, 50, false, false);
			}
		}
		else if(getDirection() == "left") {
			if(getEntityNum()==0) {
				image = new Image("Assets/left1.png", 50, 50, false, false);
			}
			else if(getEntityNum()==1) {
				image = new Image("Assets/left2.png", 50, 50, false, false);
			}
		}
		else if(getDirection() == "right") {
			if(getEntityNum()==0) {
				image = new Image("Assets/right1.png", 50, 50, false, false);
			}
			else if(getEntityNum()==1) {
				image = new Image("Assets/right2.png", 50, 50, false, false);
			}
		}
		this.setImage(image);
	}
	
	
	/*
	 * player.setEntityCounter(player.getEntityCounter()+1);
		if(player.getEntityCounter() >= 10) {
			player.setEntityNum(player.getEntityNum()+1);
			player.setEntityCounter(0);
		}
	 * 
	 */
}
