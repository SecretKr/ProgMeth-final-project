package game;

import java.util.ArrayList;

import config.Config;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	private static ArrayList<Enemy> enemies;
	private static Pane pane;
	private static Player player;
	
	@Override
	public void start(Stage primaryStage) {
		//Group root = new Group();
		pane = new Pane();
		enemies = new ArrayList<Enemy>();
		player = new Player();
		pane.getChildren().add(player);
		
		addEnemy();
		
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
	        	KeyCode kc = event.getCode();
	        	float movementX = player.getMovementX();
	        	float movementY = player.getMovementY();
	        	
	        	if(kc == KeyCode.A) {
	        		movementX = -1;
	        		player.setDirection("left");
	        	}
	        	if(kc == KeyCode.D) {
	        		movementX = 1;
	        		player.setDirection("right");
	        	}
	        	if(kc == KeyCode.W) {
	        		movementY = -1;
	        		player.setDirection("up");
	        	}
	        	if(kc == KeyCode.S) {
	        		movementY = 1;
	        		player.setDirection("down");
	        	}
	        	
	        	if(movementX != 0 && movementY != 0) {
	        		movementX *= 0.707f;
	        		movementY *= 0.707f;
	        	}
	        	
	        	player.setMovement(movementX, movementY);
	        }
	    });
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent event) {
	        	KeyCode kc = event.getCode();
	        	float movementX = player.getMovementX();
	        	float movementY = player.getMovementY();
	        	
	        	if(kc == KeyCode.A) movementX = Math.max(0f, player.getMovementX());
	        	if(kc == KeyCode.D) movementX = Math.min(0f, player.getMovementX());
	        	if(kc == KeyCode.W) movementY = Math.max(0f, player.getMovementY());
	        	if(kc == KeyCode.S) movementY = Math.min(0f, player.getMovementY());
	        	
	        	if(movementX == 0 && movementY != 0) {
	        		if(movementY == 0.707f) movementY = 1;
	        		if(movementY == -0.707f) movementY = -1;
	        	}
	        	if(movementY == 0 && movementX != 0) {
	        		if(movementX == 0.707f) movementX = 1;
	        		if(movementX == -0.707f) movementX = -1;
	        	}
	        	player.setMovement(movementX, movementY);
	        }
	    });
	}

	public static void addEnemy(){
		System.out.println("add enemy");
		enemies.add(new Enemy());
		System.out.println("added");
		pane.getChildren().add(enemies.get(enemies.size()-1));
	}
	
	public static ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	
	public static Player getPlayer() {
		return player;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
