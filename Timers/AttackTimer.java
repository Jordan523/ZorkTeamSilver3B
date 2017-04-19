package Timers;

import java.util.Timer;
import java.util.TimerTask;

import Entities.Enemy;
import Game.GameState;

/**
 * 
 * @author Xbox5
 *
 *AttackTimer class is used to set attack timers for 
 *enemies. This is the amount of time that passes 
 *before an enemy attacks you for being idle. 
 */
public class AttackTimer {

	private int ttl = 0;
    private Enemy e;
   
    
    /**
     * 
     * @param ttl
     * This creates an attack timer that will cause
     * the enemy attack after the time runs out. 
     */
    public AttackTimer(Enemy e, int timeToWait){
    	this.ttl = timeToWait;
    	this.e = e;
    }
    //These objects control the time.
    //The TimerTask contains the method run to keep
    //the timer going.
    
    public void start(){
    	
    	
    	
	    Timer timer = new Timer();
	    TimerTask task = new TimerTask() {
	    	
	    	int countDown = ttl;
	    	
	            public void run(){
	            	
	            	countDown--;
	            	if(countDown == 0){
	                   e.attack(GameState.instance().getPlayer());
	                   timer.cancel();
	                   timer.purge();
	                   
	            	}
	                
	            }
	    };
	    
	    timer.scheduleAtFixedRate(task, 1000, 1000);
    }
    
	

	
	
	
	
}
