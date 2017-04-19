package Timers;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author Xbox5
 *This class will be used within an event class to control how long
 *the event class last.
 */
public class EventTimer {


	private int seconds = 0;
    private int maxSeconds;
    
	public EventTimer(int ttl){
		this.maxSeconds = ttl;
	}
    
    //These objects control the time.
    //The TimerTask contains the method run to keep
    //the timer going.
    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
            public void run(){
            	
            	seconds++;
            	if(seconds == 10){
                   seconds = 0;
                   
            	}
                
            }
    };
    
	
	
}
