package Commands;
import Entities.Enemy.NoEnemyException;
import Entities.Player;
import Game.GameState;
import Items.*;
import Items.Item.NoItemException;

/*
* I added this
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Jordan.smith
 * This class is used to attack enemies in the dungeon.
 */
public class AttackCommand extends Command{
	
	private Player player = GameState.instance().getPlayer();
	private Item weapon = this.player.getWielding();
        private String enemy;
	
    /**
     * 
     * @param enemy The enemy to attack
     * This creates the AttackCommand object and stores the name of the enemy to attack.
     */
    public AttackCommand(String enemy){
        this.enemy = enemy;
    }
    /**
     * 
     * @return 
     * This will see if the players currently held item can even damage an enemy. If it can then it will search
     * for the enemy with the given name in the players current room. When the enemy is found this method will initiate the 
     * players attack.
     */
    public String execute(){
    	
        if(player.getWielding() == null)
            return "You are not holding a weapon!";
        else if(player.getWielding().getDamage() == 0)
            return "You try to attack with the " + player.getWielding().getPrimaryName() + " but it has no effect!\n";

        
        
        try{
            
            player.attack(player.getAdventurersCurrentRoom().getEnemy(enemy));
        
        }catch(NoEnemyException | NoItemException e){
            
            return "There is no " + enemy + " to attack.";
            
        }
    	
        return "";
    }
}
