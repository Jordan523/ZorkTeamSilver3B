package Timers;
import Game.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author jordan.smith
 * @
 * This class controls the time of day for the Dungeon.
 * It holds the minutes, seconds and whether or not the 
 * player initiated an action that pauses the game. 
 */
public class DayTimer {
    
    private int seconds = 0;
    private int minutes = 0;
    private boolean paused = false;
    
    
    //These objects control the time.
    //The TimerTask contains the method run to keep
    //the timer going.
    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
            public void run(){
            	if(!paused){
            		seconds++;
            		if(seconds == 59){
                    
            			seconds = 0;
            			if(minutes == 23)
            				minutes = 0;
            			else 
            				minutes++;
                    
            		}
            	}
                //System.out.println(minutes +":" + seconds);
                if(minutes > 6 && minutes < 19)
                    GameState.instance().setLight(false);
                else
                    GameState.instance().setLight(true);
            }
    };
    
    /**
     * 
     * @param min
     * @param sec
     * Constructor that starts the time with the given
     * minutes and seconds. 
     */
    public DayTimer(int min, int sec){
        this.minutes = min;
        this.seconds = sec;
        
    }
    
    /**
     * Starts the timer with the desired rate.
     */
    public void start(){
        
        timer.scheduleAtFixedRate(task, 500, 500);
    }
    
    /**
     * prints out the current time 
     */
    public String getTime(){
    	if(seconds > 9)
    		return minutes + ":" + seconds;
    	else
    		return minutes + ":0" + seconds;
    }
    
    /**
     * 
     * @return
     * Returns the current minute value
     */
    public int getMinutes(){
        return this.minutes;
    }
    
    /**
     * 
     * @return
     * Returns the current second value
     */
    public int getSeconds(){
        return this.seconds;
    }
    
    /**
     * 
     * @param min
     * @param sec
     * Sets the time
     */
    public void setTime(int min, int sec){
        this.minutes = min;
        this.seconds = sec;
    }
    
    public void pause(){
    	this.paused = true;
    }
    
    public void unPause(){
    	this.paused = false;
    }
    
}
