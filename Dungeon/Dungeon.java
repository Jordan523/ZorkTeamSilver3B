package Dungeon;
import Game.GameState;
import Items.*;
import Items.Item.NoItemException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import Entities.Enemy;
import Entities.Enemy.NoEnemyException;
import Entities.NPC;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map;

public class Dungeon {

    public static class IllegalDungeonFormatException extends Exception {
        public IllegalDungeonFormatException(String e) {
            super(e);
        }
    }
    
    /**
     * Checks the word given to see whether "a" or "an" is appropriate.
     * @param word
     * @return a or an
     */
    public static String aOrAn(String word) {
        return "AEIOUaeiou".contains(word.substring(0, 1)) ? "an " : "a ";
    }

    /**
     * Checks the word given to see whether "a" or "an" is appropriate.
     * @param word
     * @return a or an
     */
    public static String aOrAnCap(String word) {
        return "AEIOUaeiou".contains(word.substring(0, 1)) ? "An " : "A ";
    }
    
    // Variables relating to both dungeon file and game state storage.
    public static String TOP_LEVEL_DELIM = "===";
    public static String SECOND_LEVEL_DELIM = "---";

    // Variables relating to dungeon file (.bork) storage.
    public static String ROOMS_MARKER = "Rooms:";
    public static String EXITS_MARKER = "Exits:";
    public static String ITEMS_MARKER = "Items:";
    public static String ENEMIES_MARKER = "Enemies:";
    public static String NPCS_MARKER = "NPCS:";
    
    // Variables relating to game state (.sav) storage.
    public static String FILENAME_LEADER = "Dungeon file: ";
    public static String ROOM_STATES_MARKER = "Room states:";
    public static List<String> ENEMY_TYPES = Arrays.asList("Goblin", "Troll");

    private String name;
    private Room entry;
    private Hashtable<String,Room> rooms;
    private Hashtable<String,Item> items;
    private ArrayList<Enemy> enemies;
    private ArrayList<NPC> npcs;
    private String filename;

    Dungeon(String name, Room entry) {
        init();
        this.filename = null;    // null indicates not hydrated from file.
        this.name = name;
        this.entry = entry;
        rooms = new Hashtable<String,Room>();
    }

    /**
     * Read from the .bork filename passed, and instantiate a Dungeon object
     * based on it.
     */
    public Dungeon(String filename) throws FileNotFoundException, 
        IllegalDungeonFormatException {

        this(filename, true);
    }

    /**
     * Read from the .bork filename passed, and instantiate a Dungeon object
     * based on it, including (possibly) the items in their original locations.
     */
    public Dungeon(String filename, boolean initState) 
        throws FileNotFoundException, IllegalDungeonFormatException {

        init();
        this.filename = filename;

        Scanner s = new Scanner(new FileReader(filename));
        name = s.nextLine();

        s.nextLine();   // Throw away version indicator.

        // Throw away delimiter.
        if (!s.nextLine().equals(TOP_LEVEL_DELIM)) {
            throw new IllegalDungeonFormatException("No '" +
                TOP_LEVEL_DELIM + "' after version indicator.");
        }

        // Throw away Items starter.
        if (!s.nextLine().equals(ITEMS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                ITEMS_MARKER + "' line where expected.");
        }

        
        
        try {
            // Instantiate items.
            while (true) {
                add(new Item(s));
            }
        } catch (Item.NoItemException e) {  /* end of items */ }

        
        
        // Throw away Rooms starter.
        if (!s.nextLine().equals(ROOMS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                ROOMS_MARKER + "' line where expected.");
           
        }

        try {
            // Instantiate and add first room (the entry).
            entry = new Room(s, this, initState);
            add(entry);
            GameState.instance().getPlayer().setAdventurersCurrentRoom(entry);

            // Instantiate and add other rooms.
            while (true) {
                add(new Room(s, this, initState));
            }
        } catch (Room.NoRoomException e) {  /* end of rooms */ }

        /**
        // Instantiate Weapons
        
        if(!s.nextLine().equalsIgnoreCase(WEAPONS_MARKER)){
        	System.out.println("No Weapons found");
        }
        else{
        	try{
        		while(true){
        			new Item(s);
        		}
        	}
        	catch(NoItemException e){
        		
        	}
        }
        */
        
        
        
        
        // Throw away Exits starter.
        if (!s.nextLine().equals(EXITS_MARKER)) {
            throw new IllegalDungeonFormatException("No '" +
                EXITS_MARKER + "' line where expected.");
        }

        try {
            // Instantiate exits.
            while (true) {
                Exit exit = new Exit(s, this);
            }
        } catch (Exit.NoExitException e) {  /* end of exits */ }
        
        if(s.hasNextLine()){
	        if(!s.nextLine().equals(ENEMIES_MARKER)){
	        	throw new IllegalDungeonFormatException("No enemies found.");
	        }
	        
	        
	        try{
	        	
	        	while(true){
	        		String next = s.nextLine();
	        		if(next.equals("---")){
	        			next = s.nextLine();
	                                if(next.equals("==="))
	                                    break;
	                        }
	        		else if(next.equals("==="))
	        			break;
	        		new Enemy(s, this, next);	
	  
	        		}
	        		
	        	
	        }catch(NoEnemyException e){
	        	
	        }
        }
        
        	
        	if(!s.nextLine().equalsIgnoreCase(NPCS_MARKER))
        		throw new IllegalDungeonFormatException("NPCS not found");
        	
        	String next = s.nextLine();
        	try{
        	while(!next.equalsIgnoreCase("===")){
        		
        		this.npcs.add(new NPC(next, s, this));
        	}
        }catch(NoEnemyException e){
        	
        }
        s.close();
    }
    
    // Common object initialization tasks, regardless of which constructor
    // is used.
    private void init() {
        rooms = new Hashtable<String,Room>();
        items = new Hashtable<String,Item>();
        enemies = new ArrayList<Enemy>();
        npcs = new ArrayList<NPC>();
    }

    
    public void updateDungeon(){
    	
    	GameState.instance().getPlayer().update();
    	
    	for(Enemy e : enemies){
                
                if(e.getHealth() <= 0)
                    try{
                    e.getCurrentRoom().removeEnemy(e.getName());
                    }catch(Exception x){}
                else
                    e.update();
    	}
    	for(NPC x : npcs)
    		x.update();
    	
    	
    	
    }
    
    
    /*
     * Store the current (changeable) state of this dungeon to the writer
     * passed.
     */
    public void storeState(PrintWriter w) throws IOException {
        w.println(FILENAME_LEADER + getFilename());
        w.println(ROOM_STATES_MARKER);
        for (Room room : rooms.values()) {
            room.storeState(w);
        }
        w.println(TOP_LEVEL_DELIM);
    }

    /*
     * Restore the (changeable) state of this dungeon to that reflected in the
     * reader passed.
     */
    public void restoreState(Scanner s) throws GameState.IllegalSaveFormatException {

        // Note: the filename has already been read at this point.
        
        if (!s.nextLine().equals(ROOM_STATES_MARKER)) {
            throw new GameState.IllegalSaveFormatException("No '" +
                ROOM_STATES_MARKER + "' after dungeon filename in save file.");
        }

        String roomName = s.nextLine();
        while (!roomName.equals(TOP_LEVEL_DELIM)) {
            getRoom(roomName.substring(0,roomName.length()-1)).
                restoreState(s, this);
            roomName = s.nextLine();
        }
    }

    public Room getEntry() { return entry; }
    public String getName() { return name; }
    public String getFilename() { return filename; }
    public void add(Room room) { rooms.put(room.getTitle(),room); }
    public void add(Item item) { items.put(item.getPrimaryName(),item); }

    public Room getRoom(String roomTitle) {
        return rooms.get(roomTitle);
    }

    /**
     * Get the Item object whose primary name is passed. This has nothing to
     * do with where the Adventurer might be, or what's in his/her inventory,
     * etc.
     */
    public Item getItem(String primaryItemName) throws Item.NoItemException {
        
        for(String x : items.keySet()){
        	if(items.get(x).getPrimaryName().equalsIgnoreCase(primaryItemName))
        		return items.get(x);
        }
        throw new NoItemException();
    }
    
    /**
     * This allows for the rooms to be accessed in teleport
     * @return 
     */
     public String[] getHash(){
        String[] keys = new String[rooms.size()];
     int index = 0;
     for (Map.Entry<String, Room> mapEntry : rooms.entrySet()) {
         keys[index] = mapEntry.getKey();
    
         index++;
        }
        return  keys;
    }
    
    public void addEnemy(Enemy e){
    	this.enemies.add(e);
    }
    
    public ArrayList<Room> getRooms(){
    	
    	ArrayList<Room> Rooms = new ArrayList<>();
    	
    	for(String x : this.rooms.keySet())
    		Rooms.add(rooms.get(x));
    	
    	return Rooms;
    	
    }
    
    public ArrayList<NPC> getNPCs(){
    	return this.npcs;
    }
    
    public NPC getNPC(String name){
    	
    	for(NPC x : npcs){
    		if(x.getName().equalsIgnoreCase(name))
    			return x;
    	}
    	
    	return null;
    }
    
}
