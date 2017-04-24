/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Dungeon.Room;
import Game.GameState;
import Items.Item;

/**
 *
 * @author Matt
 */
public class DigCommand extends Command {
    private GameState gs = GameState.instance();
    private Room room;
    public DigCommand(String rName) {
        this.room = gs.getDungeon().getRoom(rName);
    }

     public String execute() {
        if (room != null && room.isAdjacentTo(gs.getPlayer().getAdventurersCurrentRoom())
                && room.getCovered()) {
            room.setCoverage(false);
            return "Debris is cleared from " + room;
        }
        else if(room == null){
            return "You didn't enter a room to clear";
        }
        else{
            return "There is no debris in the way of " + room;
        }
     }
}
