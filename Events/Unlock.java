package Events;
import Dungeon.*;
import Game.GameState;
import Items.*;

/** 
 * 
 * @author Nathan 
 */ 
public class Unlock implements Events {
    private GameState gs = GameState.instance();
    private String type;
    private Room room;
    private Item key;
    
    /**
     * Constructor for Unlock. Requires a room name parameter, but key is
     * an optional parameter to make the item disappear after using.
     * @param rName
     * @param key 
     */
    public Unlock(String rName, Item key) {
        this.type = rName;
        this.key = key;
    }
    
    @Override
    public void execute() {
        this.room = gs.getDungeon().getRoom(type);
        
        System.out.println(gs.getPlayer().getAdventurersCurrentRoom().getTitle());
        if (room != null && gs.getPlayer().getAdventurersCurrentRoom().isAdjacentTo(room)) {
            room.setBlockage(false);
            System.out.println("CLICK! -- the door to "
                    +room.getTitle()+" is now open, but the key is now stuck.");
            
            if (key != null) {
                DisappearEvent d = new DisappearEvent(key);
                
                d.execute();
            }
        } else if (room != null) {
            System.out.println("The key doesn't appear to fit any doors here.");
        } else {
            System.out.println("You aren't quite sure what to use it on.");
        }
    }
    
    @Override
    public boolean hasCalledTimer() { return false; }
    @Override
    public void setHasCalledTimer(boolean x) {}
    @Override
    public String getType() { return null; }
}
