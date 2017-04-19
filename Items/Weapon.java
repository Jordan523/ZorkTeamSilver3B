package Items;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Dungeon.Dungeon;
import Dungeon.Dungeon.IllegalDungeonFormatException;
import Game.GameState;
/**
 * A weapon will be an item that can be used in order to fight or potentially do other things with
 * 
 * @author Team Silver
 * @version 1
 */
public class Weapon
{
	private Random rand = new Random();
    private String name;
    private int weight;
    private int minDmg;
    private int maxDmg;
    private int critDmg;
    private int hitChance = 80;
    private String desc;
    private List<String> vowels = Arrays.asList("a","e","i","o","u","A","E","I","O","U");
    /**
     * The weapon constructor will be passed in it's fields and the critDmg will be maxDmg * 2.
     */
    
    public Weapon(Scanner s, Dungeon d) throws NoWeaponException{
    	String next = s.nextLine();
    	if(next.equalsIgnoreCase("==="))
    		throw new NoWeaponException();
    	else if(next.equalsIgnoreCase("---"))
    		next = s.nextLine();
    		if(next.equals("==="))
    			throw new NoWeaponException();
    	
    	String[] nameAndStats = next.split(":");
    	String[] stats = nameAndStats[1].split(",");
    	System.out.println(stats[2]);
    	
    	this.name = nameAndStats[0];
    	System.out.println(this.name);
    	this.weight = Integer.parseInt(stats[0]);
    	this.minDmg = Integer.parseInt(stats[1]);
    	this.maxDmg = Integer.parseInt(stats[2]);
    	
    	next = s.nextLine();
    	if(!next.equalsIgnoreCase("No_Room")){
	    	String[] roomAndDescription = next.split(":");
	    	System.out.println(roomAndDescription[0] + " " + roomAndDescription[1]);
	    	this.desc = roomAndDescription[1];
	    	
	    	
    		d.getRoom(roomAndDescription[0]).addWeapon(this);
    		
    	}
    	d.add(this);
    	
    }

    /**
     * This method will allow the system to determine how much damage the user will do
     * and if the hit is successful
     * 
     * 
     * @param  y   none
     * @return     damage dealt 
     */
    public int getDamage()
    {
        int damageChance = rand.nextInt(2) + 1;
        int critChance = rand.nextInt(100) + 1;
        
        
      
        
        switch(damageChance){
        
        case 1:
        	if(critChance < 6)
        		return critDmg;
        	else
        		return rand.nextInt(maxDmg) + minDmg;
        case 2:
        	if(critChance < 6)
        		return critDmg;
        	else
        		return minDmg;
        }
        
        return minDmg;
        
    }
    
    public int getMinDamage(){
    	return this.minDmg;
    }
    
    public int getMaxDamage(){
    	return this.maxDmg;
    }
    
    public String getName(){
    	return this.name;
    }
    
    public int getHitChance(){
    	return this.hitChance;
    }
    
    public String getDesc(){
    	char[] vowelCheck = this.name.toCharArray();
    	
    	if(vowels.contains(Character.toString(vowelCheck[0])))
    		return "an " + this.name + " is " + this.desc;
    	else
    		return "a " + this.name + " is " + this.desc;
    }
    
    public class NoWeaponException extends Exception{}
}
