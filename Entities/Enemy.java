package Entities;
import Items.*;
import Items.Item.NoItemException;
import Timers.AttackTimer;

import java.util.*;

import Dungeon.Dungeon;
import Dungeon.Room;
import Events.Die;
import Game.GameState;

public class Enemy extends Entity{
	
	private boolean canSeePlayer = false;
	private boolean hasAlreadyAttackedPlayer = false;
	private Room currentRoom;
	private Item weapon;
	private int critical;
	private ArrayList<Item> inventory = new ArrayList<>();
	private Random rand = new Random();
	private AttackTimer timer = new AttackTimer(this, 5);

	
	public Enemy(int health, String name, Room currentRoom) {
		super(health, name);
		this.currentRoom = currentRoom;
		
	}
	
	public Enemy(Scanner s, Dungeon d, String name) throws NoEnemyException{
		super(s, d);
		this.name = name;
		if(!s.hasNextLine())
			throw new NoEnemyException();
		String next = s.nextLine();
		if(next.equals("==="))
			throw new NoEnemyException();
		
		String[] sep = next.split(":");
		try {
			this.weapon = d.getItem(sep[1]);
		} catch (NoItemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		next = s.nextLine();
		String[] roomSplit = next.split(":");
		this.currentRoom = d.getRoom(roomSplit[1]);
		d.addEnemy(this);
		
	}


    public void update(){
    	
    	if(currentRoom == GameState.instance().getAdventurersCurrentRoom()){
    		if(!canSeePlayer)
    			this.notice(GameState.instance().getPlayer());
    		else
    			this.attack(GameState.instance().getPlayer());
    	}
    	else
    		canSeePlayer = false;
    }

	public int getDamage(){
		return this.weapon.getDamage();
	}
	
	public Item getWeapon(){
		return this.weapon;
	}
	
	public void notice(Player player){
		
		System.out.println("A " + this.name + " has noticed you and is approaching!");
		canSeePlayer = true;
		this.timer.start();
		
		
	}
    

	
	public void attack(Player player){
		
		int waitChance = rand.nextInt(100) + 1;
		boolean firstBlood = false;
		
		if(canSeePlayer && !hasAlreadyAttackedPlayer){
				
				System.out.println("The " + this.name + " approached you!");
				this.hasAlreadyAttackedPlayer = true;
		
		}
		
		
		
		int messageNum = rand.nextInt(2) + 1;
		int damageDone;
		
		switch(messageNum){
		
		case 1:
			damageDone = this.getDamage();
			System.out.println("The " + this.getName() + " attacked you!");
			player.reduceHealth(damageDone);
			break;
		case 2:
			damageDone = this.getDamage();
			System.out.println("The " + this.getName() + " attacked you with it's " + this.weapon.getPrimaryName() +"!");
			player.reduceHealth(damageDone);
			break;
		}
		
		if(player.getHealth() < 0){
			player.setHealth(0);
		}
		
		this.timer.start();
	}
	
	public int getHealth(){
		return this.health;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public void reduceHealth(int healthToReduce){
		
		System.out.println(this.name + " took " + healthToReduce + " damage!");
		
		
		this.health -= healthToReduce;
		
		
		
	}
	
	public static class NoEnemyException extends Exception{}
}
