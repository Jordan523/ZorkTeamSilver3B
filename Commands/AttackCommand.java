package Commands;
import Entities.Player;
import Game.GameState;
import Items.*;

/*
* I added this
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *This allows user to fight Enemies
 * @author Matt
 */
public class AttackCommand {
	
	private Player player = GameState.instance().getPlayer();
	private Weapon weapon;
	
    /**
     * This is where the user selects an item from inventory to use as a weapon
     * @param weapon 
     */
    public AttackCommand(Weapon weapon){
        this.weapon = weapon;
    }
    /**
     * This will return what happens when user attacks an enemy
     * @return 
     */
    public String execute(){
    	
    	
    	
    	
        return null;
    }
}
