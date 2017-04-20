package Events;

import Game.GameState;
import Commands.ItemSpecificCommand;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author trevor
 */
public class Win implements Events {
    
    private ItemSpecificCommand command;
    
    public Win(ItemSpecificCommand command){
        this.command = command;
    }
    
    public Win(){}
    
    @Override
    public boolean hasCalledTimer() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void setHasCalledTimer(boolean x) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public void execute()
    {
        if(!GameState.instance().getPlayer().getAdventurersCurrentRoom().hasEnemies()){
           System.out.println("You win!");
           System.exit(0);
        }
        else{
            System.out.println("There are enemies around you!");
            //this.command.setEventMessagePrinted(true);
        }
    }

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "WinEvent";
	};
    
}
