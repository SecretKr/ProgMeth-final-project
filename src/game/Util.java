package game;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.image.Image;
import weapon.BaseWeapon;
import weapon.homing.BaseHoming;
import weapon.rock.BaseRock;

public class Util {
		
	public static ArrayList<BaseWeapon> randomWeapons(Player player){
		ArrayList<BaseWeapon> res = new ArrayList<BaseWeapon>();
		int first = 0 + (int)(Math.random() * ((player.getWeapons().size() - 0)));
		int second = 0 + (int)(Math.random() * ((player.getWeapons().size() - 0)));
		if(second==first) {
			second = (first+1)%player.getWeapons().size();
		}
		res.add(player.getWeapons().get(first));
		res.add(player.getWeapons().get(second));
		return res;
	}
	
	public static BaseWeapon randomWeapon(Player player){
		int first = 0 + (int)(Math.random() * ((2 - 0)));
		int n = first;
		while( player.getWeapons().get(n).getLevel() == 3) {
			n = n + 1;
			if(n == first) {
				break;
			}
		}
		
		if(n==first) {
			return null;
		}
		return player.getWeapons().get(n);
	}
	
}
