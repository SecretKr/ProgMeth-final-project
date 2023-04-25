package game;

import java.util.ArrayList;

import config.Config;
import items.Bomb;
import items.Item;
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
	private static ArrayList<Item> items;
	private static Pane pane;
	private static Player player;
	
	@Override
	public void start(Stage primaryStage) {
		//Group root = new Group();
		pane = new Pane();
		enemies = new ArrayList<Enemy>();
		items = new ArrayList<Item>();
		player = new Player();
		pane.getChildren().add(player);
		
		//addEnemy();
		
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
	        	
	        	if(kc == KeyCode.A && movementX >= 0) {
	        		movementX = -1;
	        		player.setDirection("left");
	        	}
	        	if(kc == KeyCode.D && movementX <= 0) {
	        		movementX = 1;
	        		player.setDirection("right");
	        	}
	        	if(kc == KeyCode.W && movementY >= 0) {
	        		movementY = -1;
	        		player.setDirection("up");
	        	}
	        	if(kc == KeyCode.S && movementY <= 0) {
	        		movementY = 1;
	        		player.setDirection("down");
	        	}
	        	if(movementX != 0 && movementY != 0) {
	        		if(Math.abs(movementX) == 1) movementX *= 0.707f;
	        		if(Math.abs(movementY) == 1) movementY *= 0.707f;
	        	}
//
//	    		System.out.print(movementX);
//	    		System.out.print(" ");
//	    		System.out.println(movementY);
	        	
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
		enemies.add(new Enemy());
		pane.getChildren().add(enemies.get(enemies.size()-1));
	}
	
	public static void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
		pane.getChildren().remove(enemy);
	}
	
	public static void clearEnemy() {
		enemies.clear();
		//pane.getChildren().clear();
		
	}
	
	public static void addBomb(float posX, float posY) {
		items.add(new Bomb(posX, posY));
		pane.getChildren().add(items.get(items.size()-1));
	}
	
	public static void removeItem(Item item) {
		items.remove(item);
		pane.getChildren().remove(item);
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

	public static ArrayList<Item> getItems() {
		return items;
	}

}
