package game;

import config.Config;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EndGame extends VBox {
	public EndGame(int xp, int wave) {
		this.setPrefSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		Text gameOverText = new Text("GAME OVER");
		gameOverText.setFont(new Font(64));
		gameOverText.setFill(Color.RED);
		
		Text text = new Text("You have reached Wave " + Integer.toString(wave) + " with " + Integer.toString(xp) + " XP.");
		text.setFont(new Font(16));
		
		Button exitBt = new Button("Exit");
		exitBt.setPrefSize(100, 40);
		exitBt.setFont(new Font(16));
		exitBt.setOnAction(e -> {
			Platform.exit();
	    });

		this.getChildren().add(gameOverText);
		this.getChildren().add(text);
		this.getChildren().add(exitBt);
	}
}
