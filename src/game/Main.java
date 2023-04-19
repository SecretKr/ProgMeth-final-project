package game;

import config.Config;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		//Group root = new Group();
		Pane pane = new Pane();
		
		final Player player = new Player();
		pane.getChildren().add(player);
		
		Scene scene = new Scene(pane, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		movePlayer(scene, player);
		
		GameCoreWrapper gameCoreWrapper = new GameCoreWrapper(player);
		gameCoreWrapper.start();
		
		primaryStage.setTitle("Game");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	private static void movePlayer(Scene scene, Player player) {
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            switch (event.getCode()) {
	            case W:
	                player.setMovingUp(true);
	                break;
	            case D:
	            	player.setMovingRight(true);
	                break;
	            case S:
	            	player.setMovingDown(true);
	                break;
	            case A:
	            	player.setMovingLeft(true);
	                break;
	            }
	        }
	    });
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	            switch (event.getCode()) {
	            case W:
	                player.setMovingUp(false);
	                break;
	            case D:
	            	player.setMovingRight(false);
	                break;
	            case S:
	            	player.setMovingDown(false);
	                break;
	            case A:
	            	player.setMovingLeft(false);
	                break;
	            }
	        }
	    });
	}

	public static void main(String[] args) {
		launch(args);
	}

}
