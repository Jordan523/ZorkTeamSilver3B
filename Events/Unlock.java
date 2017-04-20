package Events;
import Dungeon.*;
import Items.*;
import Game.GameState;

/**
 *
 * @author Nathan
 */
public class Unlock implements Events {
    private Room origin;
    private Exit exit;
    
    public Unlock(Room room, Exit exit) {
        this.origin = room;
        this.exit = exit;
    }
    
    @Override
    public void execute() {
        GameState gs = GameState.instance();
        
        origin.unblockExit(exit);
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
