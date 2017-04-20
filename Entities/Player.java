package Entities;

import java.util.ArrayList;
import java.util.Random;

import Game.GameState;
import Items.Item;
import Items.Weapon;

public class Player extends Entity{

	
	Random rand = new Random();
	private Item weilding;
	private Item holding;
	private ArrayList<Item> inventory;
    private ArrayList<Weapon> weapons;
	
	
	public Player(int health, String name) {
		super(health, name);
		this.init();
	}
	
	public void init(){
		inventory = new ArrayList<Item>();
        weapons = new ArrayList<Weapon>();
	}
	
	public void attack(Enemy e){
		
		if(weilding == null){
			System.out.println(this.name + ", you are not weilding a weapon!");
			return;
		}
		
		int hitChance = weilding.getDamage();
		
		if(rand.nextInt(100) + 1 < 80){
			
			switch(rand.nextInt(3) + 1){
			
			case 1:
				System.out.println("You attack the " + e.getName() + " with a direct hit!");
				e.reduceHealth(weilding.getMaxDamage());
				break;
			case 2:
				System.out.println(e.getName() + " was grazed by your attack.");
				e.reduceHealth(weilding.getMinDamage());
				break;
			case 3:
				System.out.println("A direct hit! The " + e.getName() + " was hit off blance!");
				e.reduceHealth(weilding.getDamage());
				break;
				
			}
		}
		
		
	}
	
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
	
	public int getHealth(){
		return this.health;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public void reduceHealth(int healthToReduce){
		
		int messageToDisplay = rand.nextInt(3) + 1;
		
		switch(messageToDisplay){
		case 1:
			System.out.println(this.name + ", you have lost " + healthToReduce + " health!\n>");
			break;
		case 2:
			System.out.println(this.name + ", you have taken " + healthToReduce + " damage!\n>");
			break;
		case 3:
			System.out.println("Your health has been damaged by " + healthToReduce + "!\n>");
			break;
		
		}
		
		this.health -= healthToReduce;
	}
	
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
	}
	
	public boolean isUnarmed(){
		if(weilding != null)
			return false;
		else
			return true;
		
	}
	
	public void equip(Item item){
		this.weilding = item;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void update(){
		
		if(this.health <= 0){
			System.out.println("You have died!");
			System.exit(0);
		}
	}
	
	
	public void addToInventory(Item item) /* throws TooHeavyException */ {
        inventory.add(item);
    }

    public void removeFromInventory(Item item) {
        inventory.remove(item);
    }
    
    public ArrayList<Item> getInventory(){
    	return this.inventory;
    }
	

}
