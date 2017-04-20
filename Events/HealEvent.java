/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Entities.Player;
import Game.GameState;

/**
 *
 * @author jordan.smith
 */
public class HealEvent implements Events{

    private int healAmount;
    private Player player = GameState.instance().getPlayer();
    
    public HealEvent(int healAmount){
        this.healAmount = healAmount;
    }
    
    @Override
    public void execute() {
        
        player.addToHealth(healAmount);
        
    }

    @Override
    public String getType() {
        return "HealEvent";
    }
    
    
    
    
    
    
    public boolean hasCalledTimer() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHasCalledTimer(boolean x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
