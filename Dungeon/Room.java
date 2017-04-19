package Dungeon;
import Items.*;
import Items.Weapon.NoWeaponException;
import Game.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 
 * @author Jordan, Nathan, Matthew, Trevor
 */
public class Room {
    class NoRoomException extends Exception {}

    static String CONTENTS_STARTER = "Contents: ";

    private String title;
    private String desc;
    private boolean beenHere;
    private ArrayList<Item> contents;
    private ArrayList<Exit> exits;
    private ArrayList<Exit> blockedExits;
    private Hashtable<String,Weapon> weapons;

    Room(String title) {
        init();
        this.title = title;
    }

    Room(Scanner s, Dungeon d) throws NoRoomException,
        Dungeon.IllegalDungeonFormatException {

        this(s, d, true);
    }

    /**
     * Constructor of type Room
     * @param s
     * @param d
     * @param initState
     * @throws Room.NoRoomException
     * @throws Dungeon.IllegalDungeonFormatException 
     */
    Room(Scanner s, Dungeon d, boolean initState) throws NoRoomException,
        Dungeon.IllegalDungeonFormatException {

        init();
        title = s.nextLine();
        desc = "";
        if (title.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoRoomException();
        }
        
        String lineOfDesc = s.nextLine();
        while (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM) &&
               !lineOfDesc.equals(Dungeon.TOP_LEVEL_DELIM)) {

            if (lineOfDesc.startsWith(CONTENTS_STARTER)) {
                String itemsList = lineOfDesc.substring(CONTENTS_STARTER.length());
                String[] itemNames = itemsList.split(",");
                for (String itemName : itemNames) {
                    try {
                        if (initState) {
                            add(d.getItem(itemName));
                        }
                    } catch (Item.NoItemException e) {
                        throw new Dungeon.IllegalDungeonFormatException(
                            "No such item '" + itemName + "'");
                    }
                }
            } else {
                desc += lineOfDesc + "\n";
            }
            lineOfDesc = s.nextLine();
        }

        // throw away delimiter
        if (!lineOfDesc.equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new Dungeon.IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after room.");
        }
    }

    // Common object initialization tasks.
    private void init() {
        contents = new ArrayList<Item>();
        exits = new ArrayList<Exit>();
        blockedExits = new ArrayList<Exit>();
        weapons = new Hashtable<String, Weapon>();
        beenHere = false;
    }

    public String getTitle() { return title; }

    void setDesc(String desc) { this.desc = desc; }

    /*
     * Store the current (changeable) state of this room to the writer
     * passed.
     */
    void storeState(PrintWriter w) throws IOException {
        w.println(title + ":");
        w.println("beenHere=" + beenHere);
        if (contents.size() > 0) {
            w.print(CONTENTS_STARTER);
            for (int i=0; i<contents.size()-1; i++) {
                w.print(contents.get(i).getPrimaryName() + ",");
            }
            w.println(contents.get(contents.size()-1).getPrimaryName());
        }
        w.println(Dungeon.SECOND_LEVEL_DELIM);
    }

    void restoreState(Scanner s, Dungeon d) throws 
        GameState.IllegalSaveFormatException {

        String line = s.nextLine();
        if (!line.startsWith("beenHere")) {
            throw new GameState.IllegalSaveFormatException("No beenHere.");
        }
        beenHere = Boolean.valueOf(line.substring(line.indexOf("=")+1));

        line = s.nextLine();
        if (line.startsWith(CONTENTS_STARTER)) {
            String itemsList = line.substring(CONTENTS_STARTER.length());
            String[] itemNames = itemsList.split(",");
            for (String itemName : itemNames) {
                try {
                    add(d.getItem(itemName));
                } catch (Item.NoItemException e) {
                    throw new GameState.IllegalSaveFormatException(
                        "No such item '" + itemName + "'");
                }
            }
            s.nextLine();  // Consume "---".
        }
    }

    public String describe() {
        String description;
        if (beenHere) {
            description = title;
        } else {
            description = title + "\n" + desc;
        }
        
        
        try{
        	Set<String> keys = weapons.keySet();
        	for(String x : keys){
        		description += "\n" + weapons.get(x).getDesc() + "\n";
        	}
        }catch(Exception e){ System.out.println("Got here");}
        
        
        for (Item item : contents) {
            description += "\nThere is " + Dungeon.aOrAn(item.getPrimaryName())
                    + item.getPrimaryName() + " here.";
        }
        if (contents.size() > 0) { description += "\n"; }
        if (!beenHere) {
            for (Exit exit : exits) {
                description += "\n" + exit.describe();
            }
        }
        
        
        
        beenHere = true;
        return description;
    }
    
    public String fullDescribe(){
        String description = title + "\n" + desc;
        
        for (Item item : contents) {
            description += "\nThere is " + Dungeon.aOrAn(item.getPrimaryName())
                    + item.getPrimaryName() + " here.";
        }
        if (contents.size() > 0) { description += "\n"; }
        
        for (Exit exit : exits) {
                description += "\n" + exit.describe();
            
        }
        
        try{
        	Set<String> keys = weapons.keySet();
        	for(String x : keys){
        		weapons.get(x).getDesc();
        	}
        }catch(Exception e){}
        
        
        beenHere = true;
        return description;
    }
    
    public Room leaveBy(String dir) {
        for (Exit exit : exits) {
            if (exit.getDir().equals(dir)) {
                return exit.getDest();
            }
        }
        return null;
    }

    void addExit(Exit exit) {
        exits.add(exit);
    }
    /**
     * Removes an Exit from the Room's blocked ArrayList and adds it to exits.
     * @param exit 
     */
    void unblockExit(Exit exit) {
        if (this.blockedExits.contains(exit)) {
            this.blockedExits.remove(exit);
            this.exits.add(exit);
        }
    }
    /**
     * Adds a blocked exit to the room, removing it from exits ArrayList if needed
     * @param exit 
     */
    void blockExit(Exit exit) {
        if (this.exits.contains(exit)) {
            this.exits.remove(exit);
        }
        this.blockedExits.add(exit);
    }

    public void add(Item item) {
        contents.add(item);
    }

    public void remove(Item item) {
        contents.remove(item);
    }

    public Item getItemNamed(String name) throws Item.NoItemException {
        for (Item item : contents) {
            if (item.toString().equalsIgnoreCase(name)) {
                return item;
            }
        }
        throw new Item.NoItemException();
    }
    /**
     * this method will add a container object to the room
     * @param container
     */
    public void addWeapon(Weapon weapon)
    {
        weapons.put(weapon.getName(), weapon);
    }
    
    public Weapon getWeaponNamed(String name) throws NoWeaponException{
    	if(this.weapons.get(name) != null)	
    		return this.weapons.get(name);
    	else
    		return null;
    }
    public ArrayList<Item> getContents() {
        return contents;
    }
}
