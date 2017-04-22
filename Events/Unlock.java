package Events;
import Dungeon.*;
import Game.GameState;

/** 
 * 
 * @author Nathan 
 */ 
public class Unlock implements Events {
    private Room origin;
    
    public Unlock(String rName) {
        this.origin = GameState.instance().getDungeon().getRoom(rName);
    }
    
    @Override
    public void execute() {
        if (origin != null) {
            origin.setBlockage(false);
        }
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
