package Events;
import Game.GameState;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matt
 */
public class teleport {
    private int ran;
    private String[] room;
    /**
     * This will return where the user has been teleported to
     * @return 
     */ 
    public String execute(){
        Random rand = new Random();
        room = GameState.instance().getDungeon().getHash();
        ran = rand.nextInt(room.length);
        GameState.instance().setAdventurersCurrentRoom(GameState.instance().getDungeon().getRoom(room[ran]));
        return ("You've been teleported to " +GameState.instance().getAdventurersCurrentRoom().describe());
    }
    
}
