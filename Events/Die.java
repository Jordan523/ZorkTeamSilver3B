package Events;
import Timers.*;
import Items.*;
import Game.*;

/**
 *
 * @author jordan.smith
 * This class will cause the player to die.
 */
public class Die implements Events{
    
    private int timeTillDeath = -23;
    private GameTimer time = null;
    private boolean hasCalledTimer = false;
    private Item item;
    private String message;
    
    /**
     * 
     * @param timeTillDeath time till the player possibly dies
     * Constructor with the time till the player possible dies. This depends if 
     * the object that executes this is within the vicinity of the player.
     */
    public Die(int timeTillDeath, Item item){
        time = new GameTimer(this, timeTillDeath);
        this.item = item;
        
    }
    
    public Die(GameTimer time){
        this.time = time;
    }
  
    /**
     * This will instantly kill the player if executed.
     */
    public Die(){ 
    	
    }
    
    
    public void execute(){
            if(time != null && hasCalledTimer == false){
                startTimer();
                return;
            }
            else if(hasCalledTimer == true){
                //Finds the message for the event and prints it out.
                System.out.println("\nYou Died from the " + this.item.getPrimaryName() + "!");
                System.exit(0);
                return;
            }
            else if(time == null){
             GameState.instance().getPlayer().reduceHealth(GameState.instance().getPlayer().getHealth());
            }
            else
                GameState.instance().getPlayer().reduceHealth(GameState.instance().getPlayer().getHealth());
        }
    public void startTimer(){
        if(time != null)
            time.start();
        else
            execute();
    }
            
    public void setHasCalledTimer(boolean x){
        this.hasCalledTimer = x;
    }
    
    public boolean hasCalledTimer(){
        return this.hasCalledTimer;
    }

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}
