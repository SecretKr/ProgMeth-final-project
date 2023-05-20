package game;

import java.util.ArrayList;
import weapon.BaseWeapon;
import weapon.homing.BaseHoming;
import weapon.rock.BaseRock;

public class Util {
	
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
