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
        this.room = gs.getDungeon().getRoom(rName);
        this.key = key;
    }
    
    @Override
    public void execute() {
        if (room != null && room.isAdjacentTo(gs.getPlayer().getAdventurersCurrentRoom())) {
            room.setBlockage(false);
            
            if (key != null) {
                DisappearEvent d = new DisappearEvent(key);
                
                d.execute();
            }
        }
    }
    
    @Override
    public boolean hasCalledTimer() { return false; }
    @Override
    public void setHasCalledTimer(boolean x) {}
    @Override
    public String getType() { return null; }
}
