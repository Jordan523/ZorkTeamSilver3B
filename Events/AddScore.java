package Events;
import Game.*;

/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jordan.smith
 */
public class AddScore implements Events{

    private int value;
    
    public AddScore(int value){
        
        this.value = value;
    }
    public void execute() {
        
        if(value > 1){
            System.out.println("You gained " + value + " points!");
            GameState.instance().addScore(value);
        }
        else if(value < 0){
            System.out.println("You lost " + value + " points!");
        }
        else if(value == 1){
            System.out.println("You earned a point for your score!");
            GameState.instance().addScore(value);
        }
    }

    @Override
    public boolean hasCalledTimer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setHasCalledTimer(boolean x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "AddScoreEvent";
	}
    
    
    
}
