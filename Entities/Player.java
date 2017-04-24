package Entities;

import Dungeon.Room;
import java.util.ArrayList;
import java.util.Random;

import Game.GameState;
import Items.Item;
import Items.Item.NoItemException;
import Timers.DayTimer;

/**
 * 
 * @author Jordan.Smith
 * This class is used to create the player.
 */
public class Player extends Entity{

	
	Random rand = new Random();
	private Item wielding = null;
	private ArrayList<Item> inventory;
        private static int adventurerScore = 0;
        private Room adventurersCurrentRoom;
        private final int inventoryLimit = 100;
        private int carryingWeight = 0;
        private DayTimer time = new DayTimer(14, 0);
	
	/**
	 * 
	 * @param health health of player
	 * @param name name of player
	 * Constructor to create the player object.
	 */
	public Player(int health, String name) {
		super(health, name);
		this.init();
	}
	
	/**
	 * Initializes the player
	 */
	public void init(){
		inventory = new ArrayList<Item>();
		time.start();
	}
	
	/**
	 * 
	 * @param e Enemy to attack
	 * @throws NoItemException
	 * Method to attack the given enemy.
	 */
	public void attack(Enemy e) throws NoItemException{
		
		
		
		int hitChance = wielding.getDamage();
		
		if(rand.nextInt(100) + 1 < wielding.getHitChance()){
			
			switch(rand.nextInt(3) + 1){
			
			case 1:
				System.out.println("You attack the " + e.getName() + " with a direct hit!");
                e.resetTimer();
				e.reduceHealth(wielding.getMaxDamage());
				break;
			case 2:
				System.out.println(e.getName() + " was grazed by your attack.");
				e.resetTimer();
                e.reduceHealth(wielding.getMinDamage());
				break;
			case 3:
				System.out.println("A direct hit! The " + e.getName() + " was hit off blance!");
                e.resetTimer();
				e.reduceHealth(wielding.getDamage());
				break;
			}
		}
                else{

                       System.out.println("You missed your attack! The " + e.getName() + " countered you!");
                       e.attack(this);
                       e.resetTimer();
                }
		
		
	}
	
	/**
	 * 
	 * @param e Attacking enemy
	 * This method is used by dungeon enemies to attack the player.
	 */
	public void enemyAttack(Enemy e){
		
		int messageNum = rand.nextInt(2) + 1;
		int damageDone;
                
		
		switch(messageNum){
		
		case 1:
			damageDone = e.getDamage();
			System.out.println("A " + e.getName() + " attacked you!");
			this.reduceHealth(damageDone);
			break;
		case 2:
			damageDone = e.getDamage();
			System.out.println(e.getName() + " damaged you with it's " + e.getWeapon().getPrimaryName() +"!");
			this.reduceHealth(damageDone);
			break;
		}
	}
	
	/**
	 * 
	 * @return
	 * Returns the player's health.
	 */
	public int getHealth(){
		return this.health;
	}
	
	/**
	 * 
	 * @param health What to set player's health to.
	 * This method sets the player's health to the given value.
	 */
	public void setHealth(int health){
		this.health = health;
	}
	
	/**
	 * 
	 * @param healthToReduce amount of health to subtract from player.
	 * This method subtracts the given value from the player's health.
	 */
	public void reduceHealth(int healthToReduce){
		
		int messageToDisplay = rand.nextInt(3) + 1;
		
		switch(messageToDisplay){
		case 1:
			System.out.println(this.name + ", you have lost " + healthToReduce + " health!");
			break;
		case 2:
			System.out.println(this.name + ", you have taken " + healthToReduce + " damage!");
			break;
		case 3:
			System.out.println("Your health has been damaged by " + healthToReduce + "!");
			break;
		
		}
		
		this.health -= healthToReduce;
	}
	
	/**
	 * 
	 * @param healthToAdd Value to add to health
	 * This method is used to add the given value to the player's health.
	 */
	public void addToHealth(int healthToAdd){
		Random rand = new Random();
		
		int messageToDisplay = rand.nextInt(3);
		
		switch(messageToDisplay){
		case 1:
			System.out.println(this.name + ", you have gained " + healthToAdd + " health!");
			System.out.print(">");
			break;
		case 2:
			System.out.println(this.name + ", your health has increased by " + healthToAdd + "!");
			System.out.print(">");
			break;
		case 3:
			System.out.println("Your health was increased by " + healthToAdd + "!");
			System.out.print(">");
			break;
		
		}
		
		
		this.health += healthToAdd;
                
                if(this.health > 150)
                    this.health = 150;
	}
	
	/**
	 * 
	 * @return
	 * Returns true if the player is not holding a item, and false if the player is empty handed.
	 */
	public boolean isUnarmed(){
		if(wielding != null)
			return false;
		else
			return true;
		
	}
	
	/**
	 * 
	 * @param item Item to equip
	 * This method equips the given item.
	 */
	public void equip(Item item){
		this.wielding = item;
	}
	
	/**
	 * Returns the player's name.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Checks players stats and updates necessary values.
	 */
	public void update(){
		
		if(this.health <= 0){
			System.out.println("You have died!");
			System.exit(0);
		}
	}
	
	/**
	 * 
	 * @param item Item to add to inventory
	 * @throws TooHeavyException
	 * Adds the given item to the players inventory.
	 */
	public void addToInventory(Item item) throws TooHeavyException {
            if(item.getWeight() + carryingWeight < inventoryLimit){
                inventory.add(item);
                this.carryingWeight += item.getWeight();
            }
            else 
                throw new TooHeavyException();
    }

	/**
	 * 
	 * @param item Item to remove
	 * Removes the given item from the players inventory.
	 */
    public void removeFromInventory(Item item) {
        inventory.remove(item);
         this.carryingWeight -= item.getWeight();
    }
    
    /**
     * 
     * @return
     * Returns the players inventory.
     */
    public ArrayList<Item> getInventory(){
    	return this.inventory;
    }
    
    /**
     * 
     * @return
     * Returns the item that the player is currently wielding.
     */
    public Item getWielding(){
        return this.wielding;
    }
    
    /**
     * 
     * @param x Value to set the score too.
     * This method sets the players score to the given value.
     */
    public void setScore(int x){
    	this.adventurerScore = x;
    }
    
    /**
     * 
     * @return
     * Returns the players score.
     */
    public int getScore(){
    	return this.adventurerScore;
    }
    
    /**
     * 
     * @param s Value to add to score
     * This method adds the given value to the score.
     */
    public void addScore(int s){
    	this.adventurerScore += s;
    }
    
    /**
     * 
     * @param sValue to subtract from score
     * Subtracts the given value from the score.
     */
    public void reduceScore(int s){
    	this.adventurerScore -= s;
    }
    
    /**
     * 
     * @return
     * Returns the player's current room.
     */
     public Room getAdventurersCurrentRoom() {
        return adventurersCurrentRoom;
    }

    /**
     * 
     * @param room Set the player's room to the given room
     * This methods sets the player's room to the given room.
     */
    public void setAdventurersCurrentRoom(Room room) {
        adventurersCurrentRoom = room;
    }

    /**
     * 
     * @return
     * Returns the name of all the items currently in the inventory.
     */
    public ArrayList<String> getInventoryNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (Item item : inventory) {
            names.add(item.getPrimaryName());
        }
        return names;
    }
    
    /**
     * 
     * @param name Name of item
     * @return
     * @throws Item.NoItemException
     * Returns the item from the player's inventory with the given name.
     */
    public Item getItemFromInventoryNamed(String name) throws Item.NoItemException {

        for (Item item : inventory) {
            if (item.toString().equalsIgnoreCase(name)) {
                return item;
            }
        }
        throw new Item.NoItemException();
    }
    
    public void setTime(int min, int sec){
    	this.time.setTime(min, sec);
    }
    
    public String getTime(){
    	return this.time.getTime();
    }
    /**
     * 
     * @author Jordan.smith
     * Used to check if the player has too many items in their inventory
     */
    public class TooHeavyException extends Exception{}
    
}
