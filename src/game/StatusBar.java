package game;

import config.Config;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StatusBar extends HBox {
	private int hp = 100;
	private int xp = 0;
	private int wave = 1;
	
	public StatusBar(int hp, int xp, int wave) {
		this.setPrefSize(Config.SCREEN_WIDTH, 20);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(50);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		Text xpText = new Text("XP: " + Integer.toString(xp));
		xpText.setFont(new Font(16));
		
		Text hpText = new Text("HP: " + Integer.toString(hp));
		hpText.setFont(new Font(16));
		
		Text waveText = new Text("Wave " + Integer.toString(wave));
		waveText.setFont(new Font(16));

		this.getChildren().add(hpText);
		this.getChildren().add(xpText);
		this.getChildren().add(waveText);
	}
	
	public void update() {
		this.getChildren().clear();
		Text xpText = new Text("XP: " + Integer.toString(xp));
		xpText.setFont(new Font(16));
		Text hpText = new Text("HP: " + Integer.toString(hp));
		hpText.setFont(new Font(16));
		Text waveText = new Text("Wave " + Integer.toString(wave));
		waveText.setFont(new Font(16));
		this.getChildren().add(hpText);
		this.getChildren().add(xpText);
		this.getChildren().add(waveText);
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getWave() {
		return wave;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}
}
