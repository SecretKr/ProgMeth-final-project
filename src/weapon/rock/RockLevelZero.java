package weapon.rock;

import config.Config;
import javafx.scene.image.Image;

public class RockLevelZero extends BaseRock{

	public RockLevelZero(float posX, float posY) {
		super(new Image("assets/weapon/rock.png", 10, 10, false, false), posX, posY, 0);
	}
	
}
