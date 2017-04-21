package Commands;
import Game.*;
import Dungeon.Room;
 

class MovementCommand extends Command {

    private String dir;
                       

    MovementCommand(String dir) {
        this.dir = dir;
    }

    public String execute() {
        Room currentRoom = GameState.instance().getPlayer().getAdventurersCurrentRoom();
        Room nextRoom = currentRoom.leaveBy(dir);
        
        if (nextRoom != null) {  // could try/catch here.
            GameState.instance().getPlayer().setAdventurersCurrentRoom(nextRoom);
            return "\n" + nextRoom.describe() + "\n";
        } else {
            return "You can't go " + dir + ".\n";
        }
    }
}
