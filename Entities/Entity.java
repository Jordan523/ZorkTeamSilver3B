package Entities;

import java.util.Scanner;

import Dungeon.Dungeon;

public abstract class Entity {
	
	protected int health;
	protected String name;
	
	
	public Entity(int health, String name) {
		
		this.health = health;
		this.name = name;
	}
	
	public Entity(Scanner s, Dungeon d){
		
	}
	
	public String getName(){
		return name;
	}

}
