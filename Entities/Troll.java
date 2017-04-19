package Entities;

import java.util.Scanner;

import Dungeon.Dungeon;
import Dungeon.Room;

public class Troll extends Enemy{

	
	public Troll(int health, String name, Room currentRoom){
		super(health, name, currentRoom);
	}
	
	public Troll(Scanner s, String name, Dungeon d){
		super(s, d);
		this.name = name;
		
	}
	
	@Override
	public void attack(Player players) {
		// TODO Auto-generated method stub
		
		
	}

}
