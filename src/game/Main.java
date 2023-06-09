package game;

import java.util.ArrayList;

import config.Config;
import item.Bomb;
import item.Item;
import item.Potion;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import weapon.homing.BaseHoming;
import weapon.homing.HomingLevelOne;
import weapon.homing.HomingLevelThree;
import weapon.homing.HomingLevelTwo;
import weapon.homing.HomingLevelZero;
import weapon.rock.BaseRock;
import weapon.rock.RockLevelOne;
import weapon.rock.RockLevelThree;
import weapon.rock.RockLevelTwo;
import weapon.rock.RockLevelZero;

public class Main extends Application {
	private static ArrayList<Enemy> enemies;
	private static ArrayList<Item> items;
	private static Pane pane;
	private static Player player;
	private static Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		Asset.getAssets();
		pane = new Pane();
		VBox menu = new VBox();
		
		pane.setBackground(new Background(new BackgroundImage(new Image("/assets/scene/grass2.png", 320, 320, false, false), null, null, null, null)));
		
		ImageView title = new ImageView(new Image("/assets/scene/WongSurvivor.png", 350, 50, false, false));
		menu.getChildren().add(title);
		Button playBt = new Button("Play");
		playBt.setPrefSize(100, 40);
		playBt.setFont(new Font(16));
		playBt.setOnAction(e -> {
        	rule();
	    });
		menu.getChildren().add(playBt);
		Button exitBt = new Button("Quit game");
		exitBt.setPrefSize(100, 40);
		exitBt.setFont(new Font(16));
		exitBt.setOnAction(e -> {
			Platform.exit();
	    });
		menu.setPrefSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		menu.getChildren().add(exitBt);
		menu.setAlignment(Pos.CENTER);
		menu.setSpacing(30);
		
		Asset.music.setCycleCount(AudioClip.INDEFINITE);
		Asset.music.setVolume(0.4);
		Asset.music.play();
		
		pane.getChildren().add(menu);
		scene = new Scene(pane, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		
		primaryStage.setTitle("Wong Survivor");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public void rule() {
		pane.getChildren().clear();
		VBox rule = new VBox();
		Text ruleTitle = new Text("Rules");
		ruleTitle.setFont(new Font(40));
		
		Text ruleText = new Text("- Use WASD to move around\n- Hitting the slime will reduce 10 HP\n- Weapons will be selected randomly when leveling up\n- Items will randomly drop each wave\n- Bomb item -> kill all enemys\n- Potion item -> restore full HP");
		ruleText.setFont(new Font(20));
		
		Button playBt = new Button("Continue");
		playBt.setPrefSize(100, 40);
		playBt.setFont(new Font(16));
		playBt.setOnAction(e -> {
        	startGame();
	    });
		rule.setPrefSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
		rule.getChildren().add(ruleTitle);
		rule.getChildren().add(ruleText);
		rule.getChildren().add(playBt);
		rule.setAlignment(Pos.CENTER);
		rule.setSpacing(30);
		
		pane.getChildren().add(rule);
	}
	
	public void startGame() {
		pane.getChildren().clear();
		StatusBar statusBar = new StatusBar(Config.PLAYER_HP, 1, 0, EntityController.getWave());
		statusBar.relocate(0, 0);
		pane.getChildren().add(statusBar);
		
		enemies = new ArrayList<Enemy>();
		items = new ArrayList<Item>();
		pane.setBackground(new Background(new BackgroundImage(new Image("/assets/scene/grass.png", 320, 320, false, false), null, null, null, null)));
		player = new Player((Config.SCREEN_WIDTH-Config.PLAYER_WIDTH)/2, (Config.SCREEN_HEIGHT-Config.PLAYER_HEIGHT)/2, Config.PLAYER_HP);
		pane.getChildren().add(player);
		
		movePlayer(scene, player);
		GameCoreWrapper gameCoreWrapper = new GameCoreWrapper(player, statusBar);
		gameCoreWrapper.start();
	}
	
	public static void endGame(int xp, int wave) {
		pane.getChildren().clear();
		EndGame endGame = new EndGame(xp, wave);
		endGame.relocate(0, 0);
		
		pane.setBackground(new Background(new BackgroundImage(new Image("/assets/scene/grass.png", 320, 320, false, false), null, null, null, null)));
		pane.getChildren().add(endGame);
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
	        	if(movementY == 0 && movementX == 0) player.setDirection("stop");
	        	player.setMovement(movementX, movementY);
	        }
	    });
	}

	public static void addEnemy(float posX, float posY, int hp){
		enemies.add(new Enemy(posX, posY, hp));
		pane.getChildren().add(enemies.get(enemies.size()-1));
	}
	
	public static void removeEnemy(Enemy enemy) {
		enemies.remove(enemy);
		pane.getChildren().remove(enemy);
	}
	
	public static void clearEnemy() {
		enemies.clear();
		ArrayList<Object> entityList = new ArrayList<>(pane.getChildren());
		for(Object i: entityList) {
			if(i.getClass().equals(Enemy.class)) {
				pane.getChildren().remove(i);
			}
		}
	}
	
	public static void addBomb(float posX, float posY) {
		items.add(new Bomb(posX, posY));
		pane.getChildren().add(items.get(items.size()-1));
	}
	
	public static void addPotion(float posX, float posY) {
		items.add(new Potion(posX, posY));
		pane.getChildren().add(items.get(items.size()-1));
	}
	
	public static void addRock(int level, float posX, float posY) {
		if(level == 0) {
			player.addWeapon(new RockLevelZero(posX, posY));
		}
		if(level == 1) {
			player.addWeapon(new RockLevelOne(posX, posY));
		}
		if(level == 2) {
			player.addWeapon(new RockLevelTwo(posX, posY));
		}
		if(level == 3) {
			player.addWeapon(new RockLevelThree(posX, posY));
		}
		pane.getChildren().add(player.getWeapons().get(player.getWeapons().size()-1));
	}
	
	public static void removeRock(BaseRock rock) {
		player.removeWeapon(rock);
		pane.getChildren().remove(rock);
	}
	
	public static void resetRock(BaseRock rock, float posX, float posY) {
		player.removeWeapon(rock);
		pane.getChildren().remove(rock);
		rock.changePositionTo(posX, posY);
		player.addWeapon(rock);
		pane.getChildren().add(rock);
	}
	
	public static void addHoming(int level, float posX, float posY) {
		if(level == 0) {
			player.addWeapon(new HomingLevelZero(posX, posY));
		}
		if(level == 1) {
			player.addWeapon(new HomingLevelOne(posX, posY));
		}
		if(level == 2) {
			player.addWeapon(new HomingLevelTwo(posX, posY));
		}
		if(level == 3) {
			player.addWeapon(new HomingLevelThree(posX, posY));
		}
		pane.getChildren().add(player.getWeapons().get(player.getWeapons().size()-1));
	}
	
	public static void removeHoming(BaseHoming homing) {
		player.removeWeapon(homing);
		pane.getChildren().remove(homing);
	}
	
	public static void resetHoming(BaseHoming homing, float posX, float posY) {
		player.removeWeapon(homing);
		pane.getChildren().remove(homing);
		homing.changePositionTo(posX, posY);
		player.addWeapon(homing);
		pane.getChildren().add(homing);
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
