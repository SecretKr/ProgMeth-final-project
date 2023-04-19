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
