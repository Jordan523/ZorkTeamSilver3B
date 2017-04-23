package Items;
import Dungeon.*;
import Game.*;
import Events.*;
import java.awt.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class Item {

    public static class NoItemException extends Exception {}

    private String primaryName;
    private int weight;
    private Hashtable<String,String> messages = new Hashtable<>();
    private Hashtable<Events, String> events = new Hashtable<>();
   

    public Item(Scanner s) throws NoItemException,
        Dungeon.IllegalDungeonFormatException {

        messages = new Hashtable<String,String>();
        

        // Read item name.
        primaryName = s.nextLine();
        if (primaryName.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoItemException();
        }
        
        String next = s.nextLine();
        
        if(next.contains("weapon")){
            String[] nameAndStats = next.split(":");
            String[] stats = nameAndStats[1].split(",");
            
            this.events.put(new AttackEvent(Integer.parseInt(stats[0]), Integer.parseInt(stats[1])), "attack");
            
            next = s.nextLine();
        }

        // Read item weight.
        weight = Integer.valueOf(next);

        // Read and parse verbs lines, as long as there are more.
        String verbLine = s.nextLine();
        while (!verbLine.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            if (verbLine.equals(Dungeon.TOP_LEVEL_DELIM)) {
                throw new Dungeon.IllegalDungeonFormatException("No '" +
                    Dungeon.SECOND_LEVEL_DELIM + "' after item.");
            }
            
            String[] verbParts = verbLine.split(":");
            String verb;
            
            //if the verb has any events attached to it read them, else just get the verb and message
            if(verbParts[0].contains("[")){
                // Get only the stuff between the brackets, and split it by comma
                String[] firstSplit = verbParts[0].substring(verbParts[0].indexOf("[")+1,verbParts[0].indexOf("]")).split(",");
                ArrayList<Events> forVerb = new ArrayList<Events>();
                verb = verbParts[0].substring(0, verbParts[0].indexOf("["));
                
                for (String evLine : firstSplit) {
                    String evName = "";
                    String evParam = "";
                    
                    if (evLine.contains("(")) {
                        evName = evLine.substring(0, evLine.indexOf("("));
                        evParam = evLine.substring(evLine.indexOf("(")+1, evLine.indexOf(")"));
                    } else {
                        evName = evLine;
                    }
                    if (evName.equalsIgnoreCase("DeadTimer"))
                        this.events.put(new Die(Integer.parseInt(evParam), this), verb);
                    else if (evName.equalsIgnoreCase("Die"))
                        this.events.put(new Die(), verb);
                    else if (evName.equalsIgnoreCase("Disappear"))
                        this.events.put(new DisappearEvent(this), verb);
                    else if(evName.equalsIgnoreCase("Heal"))
                        this.events.put(new HealEvent(Integer.valueOf(evParam)), verb);
                    else if(evName.equalsIgnoreCase("Illuminate"))
                        this.events.put(new IlluminateEvent(), verb);
                    else if (evName.equalsIgnoreCase("Score"))
                        this.events.put(new AddScore(Integer.parseInt(evParam)), verb);
                    else if (evName.equalsIgnoreCase("Transform"))
                        this.events.put(new TransformEvent(evParam, this), verb);
                    else if (evName.equalsIgnoreCase("Unlock"))
                        this.events.put(new Unlock(evParam, this), verb);
                    else if (evName.equalsIgnoreCase("Wound"))
                        this.events.put(new Wound(Integer.parseInt(evParam)), verb);
                    else if(evName.equalsIgnoreCase("Win"))
                        this.events.put(new Win(), verb);
                }
            } else {
                verb = verbParts[0];
            }
            
            messages.put(verb, verbParts[1]);
            verbLine = s.nextLine();
        }
    }
    
    /**
     * 
     * @param item item to create
     * This is used for creating items in rooms that were not initialized in the dungeon file. However, this still can only use items that are 
     * created in the dungeon file.
     */
    public Item(Item item){
       
        this.events = item.itemEvents();
        this.messages = item.messages;
        this.primaryName = item.getPrimaryName();
        this.weight = item.getWeight();
        
    }
    
    /**
     * \
     * @param name item name
     * @return
     * If this item goes by the given name then return true
     */
    public boolean goesBy(String name) {
        // could have other aliases
        return this.primaryName.toLowerCase().contains(name.toLowerCase());
    }

    /**
     * 
     * @return
     * return item name
     */
    public String getPrimaryName() { return primaryName; }
    
    /**
     * 
     * @param verb verb for message
     * @return
     * Return the message for the corresponding verb
     */
    public String getMessageForVerb(String verb) {
        return messages.get(verb);
    }

    /**
     * Returns primary name
     */
    public String toString() {
        return primaryName;
    }
    
    /**
     * 
     * @return
     * Returns items weight. This is used for calculating the carrying weight for the player's inventory.
     */
    public int getWeight(){
        return this.weight;
    }
    
    /**
     * 
     * @return
     * Returns the Hashtable of all the given items events.
     */
    public Hashtable<Events, String> itemEvents(){
        return this.events;
    }
    
    /**
     * 
     * @return
     * Returns the damage of the items attack event. If no attack event exists then it will return 0.
     */
    public int getDamage(){
        Set<Events> keys = this.events.keySet();
        AttackEvent temp;
        try{
	        for(Events e : keys){
	            if(e.getType().equalsIgnoreCase("AttackEvent")){
	                temp = (AttackEvent)e;
	                return temp.getDamage();
	            }
	        }
	        }catch(Exception e){
	        	return 0;
	      }
        return 0;
    }
    
    /**
     * 
     * @return
     * Will return max damage of the attack event or 0 if no attack event exists.
     */
    public int getMaxDamage(){
        
        Set<Events> keys = this.events.keySet();
        AttackEvent temp;
        for(Events e : keys){
            if(e.getType().equalsIgnoreCase("AttackEvent")){
                temp = (AttackEvent)e;
                return temp.getMaxDamage();
            }
        }
        return 0;
    }
    
    /**
     * 
     * @return
     * Will return minimum damage of attack event or 0 if none exists.
     */
    public int getMinDamage(){
        
        Set<Events> keys = this.events.keySet();
        AttackEvent temp;
        for(Events e : keys){
            if(e.getType().equalsIgnoreCase("AttackEvent")){
                temp = (AttackEvent)e;
                return temp.getMinDamage();
            }
        }
        return 0;
        
    }
    
    /**
     * 
     * @return
     * Return the chance of a successful hit for this item.
     */
    public int getHitChance(){
        return 90;
    }
}
