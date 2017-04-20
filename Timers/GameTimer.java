package Timers;
import Events.*;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jordan.smith
 * This class can be used to create timers that execute events when the
 * time to live expires.
 */
public class GameTimer {
    
    private int ttl;
    private Events event;
    
    private Timer timer;
    private TimerTask task;
   
    
    
    /**
     * 
     * @param e Event given
     * @param ttl How long to wait before executing the event
     */
    public GameTimer(Events e, int ttl){
        this.ttl = ttl;
        this.event = e;
    }
    
    
  
    /**
     * This starts the timer when it is called.
     */
    public void start(){
        
         timer = new Timer();
        
        task = new TimerTask() {
            public void run(){
            	ttl--;
                if(ttl == 0){
                    timer.cancel();
                    timer.purge();
                    task.cancel();
                    event.setHasCalledTimer(true);
                    event.execute();
                }
                    
            }
        };
 
        timer.scheduleAtFixedRate(task, 1000, 1000);
        
        
    }
    
    public void stop(){
        timer.cancel();
        timer.purge();
        task.cancel();
    }
    
/**
 * 
 * @return returns the time left.
 */
    public int getTimeLeft(){
        return this.ttl;
    }
    
    /**
     * 
     * @param timeToAdd 
     * Adds time to the timer if called.
     */
    public void addTime(int timeToAdd){
      this.ttl += timeToAdd;
    }
    
    
        
}
