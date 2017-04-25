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
           System.out.println("You give the coffee mug to the professor. His eye's brighten and a bright light shone upon him from"
           		+ "\nan unkown source. \"Thank you " + GameState.instance().getPlayer().getName() + ", you have saved my life and the "
           		+ "small kingdome of Trinkle.\nI shall give everyone A's for the semester and no finals!\"\nIt is said that the professor's"
           		+ " heart grew three sizes that day.\nWell done adventurer and thanks for playing!");
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
