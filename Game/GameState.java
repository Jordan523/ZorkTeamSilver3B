package Game;
import Dungeon.*;
import Entities.Player;
import Items.*;
import Events.*;
import Timers.*;
import Commands.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GameState {

	
	
    public static class IllegalSaveFormatException extends Exception {
    	
        public IllegalSaveFormatException(String e) {
            super(e);
        }
    }

    static String DEFAULT_SAVE_FILE = "zork_save";
    public static String SAVE_FILE_EXTENSION = ".sav";
    static String SAVE_FILE_VERSION = "Zork v2.0 save data";

    static String ADVENTURER_MARKER = "Adventurer:";
    static String CURRENT_ROOM_LEADER = "Current room: ";
    static String INVENTORY_LEADER = "Inventory: ";
    static String HEALTH_LEADER = "Health: ";
    static String SCORE_LEADER = "Score: ";
    static String TIME_LEADER = "Time:";

    private static GameState theInstance;
    private Dungeon dungeon;
    private static boolean isLightOut = true;
    
    private static Player player;
    

    public static synchronized GameState instance() {
        if (theInstance == null) {
            theInstance = new GameState();
          
        }
        return theInstance;
    }

    private GameState() {
        
    }

    void restore(String filename) throws FileNotFoundException,
        IllegalSaveFormatException, Dungeon.IllegalDungeonFormatException {

        Scanner s = new Scanner(new FileReader(filename));

        if (!s.nextLine().equals(SAVE_FILE_VERSION)) {
            throw new IllegalSaveFormatException("Save file not compatible.");
        }

        String dungeonFileLine = s.nextLine();

        if (!dungeonFileLine.startsWith(Dungeon.FILENAME_LEADER)) {
            throw new IllegalSaveFormatException("No '" +
                Dungeon.FILENAME_LEADER + 
                "' after version indicator.");
        }

        dungeon = new Dungeon(dungeonFileLine.substring(
            Dungeon.FILENAME_LEADER.length()), false);
        dungeon.restoreState(s);

        s.nextLine();  // Throw away "Adventurer:".
        String currentRoomLine = s.nextLine();
        this.player.setAdventurersCurrentRoom(dungeon.getRoom(
            currentRoomLine.substring(CURRENT_ROOM_LEADER.length())));
        while (s.hasNext()) {
            String next = s.nextLine();
            if(next.contains(INVENTORY_LEADER)){
                String inventoryList = next.substring(
                    INVENTORY_LEADER.length());
                String[] inventoryItems = inventoryList.split(",");
                for (String itemName : inventoryItems) {
                    try {
                        player.addToInventory(dungeon.getItem(itemName));
                    } catch (Item.NoItemException e) {
                        throw new IllegalSaveFormatException("No such item '" +
                            itemName + "'");
                    } catch(Player.TooHeavyException e){
                        System.out.println("Restored too much weight");
                    }
                }
            }
            else if(next.contains(HEALTH_LEADER)){
                    String[] sep = next.split(":");
                    this.player.setHealth((Integer.parseInt(sep[1].replace(" ", ""))));

            }
            else if(next.contains(SCORE_LEADER)){
                    String[] sep = next.split(":");
                    this.player.setScore(Integer.parseInt(sep[1].replace(" ", "")));
            }
            else if(next.contains(TIME_LEADER)){
                	String[] messageAndTime = next.split(":");
                	int min = Integer.valueOf(messageAndTime[1]);
                	int sec = Integer.valueOf(messageAndTime[2]);
                	GameState.instance().getPlayer().setTime(min, sec);
            
            }
        }
    }
    

    void store() throws IOException {
        store(DEFAULT_SAVE_FILE);
    }

    public void store(String saveName) throws IOException {
        String filename = saveName + SAVE_FILE_EXTENSION;
        PrintWriter w = new PrintWriter(new FileWriter(filename));
        w.println(SAVE_FILE_VERSION);
        dungeon.storeState(w);
        w.println(ADVENTURER_MARKER);
        w.println(CURRENT_ROOM_LEADER + this.player.getAdventurersCurrentRoom().getTitle());
        if (player.getInventory().size() > 0) {
            w.print(INVENTORY_LEADER);
            for (int i=0; i<player.getInventory().size()-1; i++) {
                w.print(player.getInventory().get(i).getPrimaryName() + ",");
            }
            w.println(player.getInventory().get(player.getInventory().size()-1).getPrimaryName());
        }
        
        w.print(HEALTH_LEADER);
        w.print(GameState.instance().player.getHealth());
        w.println();
        w.print(SCORE_LEADER);
        w.print(GameState.instance().player.getScore());
        w.println();
        w.print(TIME_LEADER);
        w.print(player.getTime());
        w.close();
    }

    void initialize(Dungeon dungeon) {
        this.dungeon = dungeon;
        this.player.setAdventurersCurrentRoom(dungeon.getEntry());
        System.out.println(this.player.getAdventurersCurrentRoom().getTitle());
    }


    
    public Item getItemInVicinityNamed(String name) throws Item.NoItemException {

        // First, check inventory.
        for (Item item : player.getInventory()) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        // Next, check room contents.
        for (Item item : this.player.getAdventurersCurrentRoom().getContents()) {
            if (item.goesBy(name)) {
                return item;
            }
        }

        throw new Item.NoItemException();
    }


   

    public Dungeon getDungeon() {
        return dungeon;
    }
     
    
    public void setLight(boolean x){
    	this.isLightOut = x;
    }
    
    public Player getPlayer(){
    	return this.player;
    }
    
    public void initPlayer(String playerName){
    	this.player = new Player(150, playerName);
    }
    private boolean earthquake = false;
    public void setEarthquake(boolean b){
        this.earthquake = b;
    }
    public boolean getEarthquake(){
        return this.earthquake;
    }

}
