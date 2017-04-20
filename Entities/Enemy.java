package Entities;
import Items.*;
import Items.Item.NoItemException;
import Timers.AttackTimer;

import java.util.*;

import Dungeon.Dungeon;
import Dungeon.Room;
import Events.*;
import Game.GameState;

public class Enemy extends Entity{
	
	private boolean canSeePlayer = false;
	private boolean hasAlreadyAttackedPlayer = false;
        private boolean alreadyAttacking = false;
	private Room currentRoom;
	private Item weapon;
	private int critical;
	private ArrayList<Item> inventory = new ArrayList<>();
	private Random rand = new Random();
	private AttackTimer timer = new AttackTimer(this, 20);

	
	public Enemy(int health, String name, Room currentRoom) {
		super(health, name);
		this.currentRoom = currentRoom;
		
	}
	
	public Enemy(Scanner s, Dungeon d, String nh) throws NoEnemyException{
		super(s, d);
                
                if(nh.equals("==="))
			throw new NoEnemyException();
                else if(!s.hasNextLine())
                        throw new NoEnemyException();
                
		String[] nameAndHealth = nh.split(":");
                this.name = nameAndHealth[0];
                System.out.println(nameAndHealth[1]);
                this.health = Integer.valueOf(nameAndHealth[1]);
		if(!s.hasNextLine())
			throw new NoEnemyException();
		String next = s.nextLine();
		
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
                d.getRoom(currentRoom.getTitle()).addEnemy(this);
		
	}

        
        public void notice(Player player){
		
            if(!canSeePlayer){
                System.out.println("A " + this.name + " has noticed you and is approaching!");
                canSeePlayer = true;
            }
            if(!alreadyAttacking)
                this.timer.start();
		
		
	}
        
        

    public void update(){
    	

        
        
    	if(currentRoom == GameState.instance().getPlayer().getAdventurersCurrentRoom()){
    		if(!canSeePlayer){
    			this.notice(GameState.instance().getPlayer());
                        return;
                }
                
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
        
        public Room getCurrentRoom(){
            return this.currentRoom;
            
        }
        
        public void setCurrentRoom(Room room){
            this.currentRoom = room;
        }
	 

	
	public void attack(Player player){
		
		int waitChance = rand.nextInt(100) + 1;
		this.alreadyAttacking = true;
		
		if(canSeePlayer && !hasAlreadyAttackedPlayer){
				
				System.out.println("\nThe " + this.name + " approached you!");
				this.hasAlreadyAttackedPlayer = true;
		
		}
		
		
		
		int messageNum = rand.nextInt(2) + 1;
		int damageDone;
		
		switch(messageNum){
		
		case 1:
			damageDone = this.getDamage();
			System.out.println("\nThe " + this.name + " attacked you!");
			player.reduceHealth(damageDone);
                        this.timer.reset();
			break;
		case 2:
			damageDone = this.getDamage();
			System.out.println("\nThe " + this.name + " attacked you with it's " + this.weapon.getPrimaryName() +"!");
			player.reduceHealth(damageDone);
                        this.timer.reset();
			break;
		}
		
		if(player.getHealth() < 0){
			player.setHealth(0);
		}
		
	}
	
        public boolean canSeePlayer(){
            return this.canSeePlayer;
        }
        
        public void setCanSeePlayer(boolean x){
            this.canSeePlayer = x;
        }
        
	public int getHealth(){
		return this.health;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public void reduceHealth(int healthToReduce) throws NoItemException{
		
		System.out.println(this.name + " took " + healthToReduce + " damage!");
		
		
		this.health -= healthToReduce;
                
               if(this.health <= 0){
                    int potionChance = rand.nextInt(100) + 1;
                    System.out.println("The " + this.name + " was defeated! You have gained 20 points!");
                    GameState.instance().getPlayer().addScore(20);
                    this.timer.stop();
                    this.currentRoom.addDefeatedEnemy(this);
                    this.currentRoom.removeEnemy(this.name);
                    if(potionChance > 0){
                        System.out.println("The " + this.name + " dropped a potion!");
                        GameState.instance().getPlayer().getAdventurersCurrentRoom().add(new Item(GameState.instance().getDungeon().getItem("Potion")));
                    }
                    
                }
		
		
		
	}
        
        public void resetTimer(){
            this.timer.reset();
        }
        
        
	
	public static class NoEnemyException extends Exception{}
}
