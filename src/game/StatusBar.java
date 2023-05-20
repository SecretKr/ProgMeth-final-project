package game;

import config.Config;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StatusBar extends HBox {
	private int hp = 100;
	private int xp = 0;
	private int level = 1;
	private int wave = 1;
	
	public StatusBar(int hp, int level, int xp, int wave) {
		this.setPrefSize(Config.SCREEN_WIDTH, 20);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(50);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		Text levelText = new Text("Level: " + Integer.toString(xp));
		levelText.setFont(new Font(16));
		
		Text xpText = new Text("XP: " + Integer.toString(xp) + "/" + Integer.toString(level*level*10));
		xpText.setFont(new Font(16));
		
		Text hpText = new Text("HP: " + Integer.toString(hp));
		hpText.setFont(new Font(16));
		
		Text waveText = new Text("Wave " + Integer.toString(wave));
		waveText.setFont(new Font(16));

		this.getChildren().add(hpText);
		this.getChildren().add(levelText);
		this.getChildren().add(xpText);
		this.getChildren().add(waveText);
	}
	
	public void update() {
		this.getChildren().clear();
		Text levelText = new Text("Level: " + Integer.toString(level));
		levelText.setFont(new Font(16));
		Text xpText = new Text("XP: " + Integer.toString(xp) + "/" + Integer.toString(level*level*10));
		xpText.setFont(new Font(16));
		Text hpText = new Text("HP: " + Integer.toString(hp));
		hpText.setFont(new Font(16));
		Text waveText = new Text("Wave " + Integer.toString(wave));
		waveText.setFont(new Font(16));
		this.getChildren().add(hpText);
		this.getChildren().add(levelText);
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
