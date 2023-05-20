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
	
	public static int randomWeapon(Player player){
		ArrayList<BaseWeapon> playerWeapons = player.getWeapons();
		int ran = 0 + (int)(Math.random() * ((playerWeapons.size() - 0)));
		// 0 = Rock
		// 1 = Homing
		
		for(int i=0; i< playerWeapons.size(); i++) {
			
			if(ran == 0) {
				for(int j = 0; j < playerWeapons.size(); j++) {
					BaseWeapon weapon = playerWeapons.get(j);
					if(weapon instanceof BaseRock) {
						if(weapon.getLevel()!=3) return j;
					}
					
				}
			}
			if(ran == 1) {
				for(int j = 0; j < playerWeapons.size(); j++) {
					BaseWeapon weapon = playerWeapons.get(j);
					if(weapon instanceof BaseHoming) {
						if(weapon.getLevel()!=3) return j;
					}
				}
			}
			ran = (ran+1) % 2;
		}
		return -1;
		
	}
	
}
