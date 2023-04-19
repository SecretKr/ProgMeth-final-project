/*public class Player extends ImageView {
	private boolean movingDown;
	private boolean movingLeft;
	private boolean movingRight;
	private int playerNum=0;
	private int playerCounter=0;
	private String direction;
	
	public Player() {
		super(new Image("/Assets/mario.png", 50, 50, false, false));
public class Player extends ImageView {
	
	public void updatePos() {
		super.relocate(posX, posY);
		playerCounter+=1;
		if(playerCounter>10) {
			this.changePlayerNum();
			playerCounter=0;
		}
	}
	
	public void draw() {
		
		if(direction == "up") {
			if(playerNum==0) {
				image = up1;
			}
			else if(playerNum==1) {
				image = up2;
			}
		}
		else if(direction == "down") {
			if(playerNum==0) {
				image = down1;
			}
			else if(playerNum==1) {
				image = down2;
			}
		}
		else if(direction == "left") {
			if(playerNum==0) {
				image = left1;
			}
			else if(playerNum==1) {
				image = left2;
			}
		}
		else if(direction == "right") {
			if(playerNum==0) {
				image = right1;
			}
			else if(playerNum==1) {
				image = right2;
			}
		}
	}

	public int getPosX() {
@ -45,6 +89,10 @@ public class Player extends ImageView {
		else { if(posY >= Config.SCREEN_HEIGHT-Config.PLAYER_HEIGHT) this.posY = Config.SCREEN_HEIGHT-Config.PLAYER_HEIGHT;
		else this.posY = posY;}
	}
	
	public int changePlayerNum() {
		return (this.playerNum+1)%2;
	}

	public boolean isMovingUp() {
		return movingUp;
@ -78,5 +126,20 @@ public class Player extends ImageView {
		this.movingRight = movingRight;
	}
	
	public int getPlayerNum() {
		return playerNum;
	}
	
	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}
	
}*/