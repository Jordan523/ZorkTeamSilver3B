package Events;
import Dungeon.*;
import Game.GameState;

/** 
 * 
 * @author Nathan 
 */ 
public class Unlock implements Events {
    private Room origin;
    
    public Unlock(Room room) {
        this.origin = room;
    }
    
    @Override
    public void execute() {
        GameState gs = GameState.instance();
        
        origin.setBlockage(false);
    }
 
    @Override
    public boolean hasCalledTimer() { return false; }
    @Override
    public void setHasCalledTimer(boolean x) {}
    @Override
    public String getType() {
        return null; 
    }
}
