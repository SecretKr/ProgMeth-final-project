package weapon.rock;

import javafx.scene.image.Image;

public class RockLevelOne extends BaseRock{

	public RockLevelOne(float posX, float posY) {
		super(new Image("/Assets/alien.png", 10, 10, false, false), posX, posY, 1);
		setDamage(10);
		setSpeed(10);
		setWidth(10);
		setHeight(10);
	}
	
}
