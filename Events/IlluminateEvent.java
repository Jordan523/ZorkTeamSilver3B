package Events;

import Entities.Player;
import Game.GameState;
import Dungeon.Room;

/**
 * Write a description of class IlluminateEvent here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IlluminateEvent implements Events
{
    // instance variables - replace the example below with your own
    private boolean light = true;
    private Player player = GameState.instance().getPlayer();
    private Room room;
    /**
     * Constructor for objects of class IlluminateEvent
     */
    public IlluminateEvent()
    {
        
    }
    @Override
    public void execute()
    {
        room = GameState.instance().getPlayer().getAdventurersCurrentRoom();
        room.setLight(light);
        System.out.println(room.fullDescribe());
    }
    public String getType()
    {
        return "IlluminateEvent";
    }
    public boolean hasCalledTimer() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHasCalledTimer(boolean x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
