package game;

import java.util.ArrayList;

import config.Config;
import item.Item;
import javafx.scene.image.Image;
import weapon.BaseWeapon;
import weapon.homing.BaseHoming;
import weapon.rock.BaseRock;

public class Player extends Entity {
	
	private ArrayList<BaseWeapon> weapons;
	private int xP;
	private int level;
	
	public Player(float posX, float posY, int hP) {
		super(Asset.player.get(0), posX, posY, hP);
		setDirection("down");
		//setPosX((Config.SCREEN_WIDTH-Config.PLAYER_WIDTH)/2);
		//setPosY((Config.SCREEN_HEIGHT-Config.PLAYER_HEIGHT)/2);
		setLevel(0);
		setAlive(true);
		setHP(100);
		setWidth(Config.PLAYER_WIDTH);
		setHeight(Config.PLAYER_HEIGHT);
		setHitDamage(10);
		setWeapons(new ArrayList<BaseWeapon>());
	}
	
	public int getEntityNumMax() {
		return Asset.playerFrame;
	}
	
	public int getRockIndex() {
		int n=-1;
		for(int i = 0; i < this.getWeapons().size(); i++) {
			if(this.getWeapons().get(i) instanceof BaseRock) {
				n=i;
				return i;
			}
		}
		return n;
	}
	
	public int getHomingIndex() {
		int n=-1;
		for(int i = 0; i < this.getWeapons().size(); i++) {
			if(this.getWeapons().get(i) instanceof BaseHoming) {
				n=i;
				return i;
			}
		}
		return n;
	}
	
	public boolean isCollideEntity(Entity enemy) {

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
	
	public boolean isCollideItem(Item item) {

		float pLeftX = this.getLeftX();
		float iLeftX = item.getLeftX();
		float pRightX = this.getRightX();
		float iRightX = item.getRightX();
		float pTopY = this.getTopY();
		float iTopY = item.getTopY();
		float pBottomY = this.getBottomY();
		float iBottomY = item.getBottomY();
		
		if((pLeftX <= iRightX && pLeftX >= iLeftX) && (pTopY >= iTopY && pTopY <= iBottomY) // check if top left corner of player in enemy area
				|| (iLeftX <= pRightX && iLeftX >= pLeftX) && (iTopY >= pTopY && iTopY <= pBottomY)) { //check if top left corner of enemy in player area
			return true;
		}
		
		if((pRightX <= iRightX && pRightX >= iLeftX) && (pTopY >= iTopY && pTopY <= iBottomY) // check if top right corner of player in enemy area
				|| (iRightX <= pRightX && iRightX >= pLeftX) && (iTopY >= pTopY && iTopY <= pBottomY)) { //check if top right corner of enemy in player area
			return true;
		}
		
		if((pLeftX <= iRightX && pLeftX >= iLeftX) && (pBottomY >= iTopY && pBottomY <= iBottomY) // check if bottom left corner of player in enemy area
				|| (iLeftX <= pRightX && iLeftX >= pLeftX) && (iBottomY >= pTopY && iBottomY <= pBottomY)) { //check if bottom left corner of enemy in player area
			return true;
		}
		
		if((pRightX <= iRightX && pRightX >= iLeftX) && (pBottomY >= iTopY && pBottomY <= iBottomY) // check if bottom right corner of player in enemy area
				|| (iRightX <= pRightX && iRightX >= pLeftX) && (iBottomY >= pTopY && iBottomY <= pBottomY)) { //check if bottom right corner of enemy in player area
			return true;
		}
		
		return false;
		
	}
	
	public void draw() {
		Image image = null;
		
		if(getDirection() == "up") {
			image = Asset.playerUp.get(getEntityNum());
		}
		else if(getDirection() == "down") {
			image = Asset.playerDown.get(getEntityNum());
		}
		else if(getDirection() == "left") {
			image = Asset.playerLeft.get(getEntityNum());
		}
		else if(getDirection() == "right") {
			image = Asset.playerRight.get(getEntityNum());
		}
		else if(getDirection() == "stop") {
			image = Asset.player.get(getEntityNum());
		}
		this.setImage(image);
	}
	
	public void addWeapon(BaseWeapon weapon) {
		ArrayList<BaseWeapon> tempWeapons = this.getWeapons();
		tempWeapons.add(weapon);
		this.setWeapons(tempWeapons);
	}
	
	public void removeWeapon(BaseWeapon weapon) {
		ArrayList<BaseWeapon> tempWeapons = this.getWeapons();
		tempWeapons.remove(weapon);
		this.setWeapons(tempWeapons);
	}
	
	public ArrayList<BaseWeapon> getWeapons(){
		return weapons;
	}
	
	public void setWeapons(ArrayList<BaseWeapon> weapons) {
		this.weapons = weapons;
	}

	public int getXP() {
		return xP;
	}

	public void setXP(int xP) {
		this.xP = xP;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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
