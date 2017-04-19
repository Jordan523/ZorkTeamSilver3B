package Entities;

import java.util.Random;
import java.util.Scanner;

import Dungeon.Dungeon;
import Dungeon.Room;
import Game.GameState;

public class Goblin extends Enemy{

	private Random rand = new Random();
	public Goblin(int health, String name, Room currentRoom) {
		super(health, name, currentRoom);
		
		this.critical = rand.nextInt(30) + 10;
		
	}

	public Goblin(Scanner s, String name, Dungeon d){
		super(s, d);
		this.name = name;
		
		
		
		
	}
	@Override
	public void attack(Player player) {
		if(player.isUnarmed()){
			System.out.println(player.getName() + ", you are not holding a weapon!");
			return;
		}
		int hitChance = rand.nextInt(100) + 1;
		
		if(canSeenPlayer){
			if(hitChance > 79){
			
				GameState.instance().getPlayer().enemyAttack(this);
			
			}else{
				System.out.println(this.name + " has spotted you!");
			}
		
		}
		

		
		
		
	}

}
