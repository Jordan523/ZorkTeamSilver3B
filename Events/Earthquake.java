package Events;
import Dungeon.*;
import Game.*;
import java.util.Random;

/**
 * 
 * @author Matt Kline
 *
 *This class will initiate a Storm to occur in 
 *the dungeon.
 */
public class Earthquake implements Events{

       private int ran1;
       private int ran2;
       private Room a;
       private Room b;
       private String[] room;
	
	/**
	 * This method searches the rooms in the Dungeon
	 * to see what rooms will be affected by an Earthquake.
	 * It will also close certain exits. 
	 */
	public String execute1(){
	  Random rand = new Random();
          room = GameState.instance().getDungeon().getHash();
          ran1 = rand.nextInt(room.length);	
	  if(room.length>3){
              if(ran1 == (room.length-1)){
                  ran2 = ran1 - 1;
              }else{
                  ran2 = ran1 +1;
              }
               a = GameState.instance().getDungeon().getRoom(room[ran1-1]);
               b = GameState.instance().getDungeon().getRoom(room[ran2-1]);
               if(a == GameState.instance().getPlayer().getAdventurersCurrentRoom()){
                   a = GameState.instance().getDungeon().getRoom(room[ran1-2]);
                   a.setCoverage(true);
               }else if(b == GameState.instance().getPlayer().getAdventurersCurrentRoom()){
                  b = GameState.instance().getDungeon().getRoom(room[ran2 - 3]);
                   b.setCoverage(true); 
               }else{
              a.setCoverage(true);
              b.setCoverage(true);
               }
              return ("An earthquake has occured, "+ a.getTitle() +" and "+ 
                       b.getTitle()+" are covered in debris");
          }
          else{
               a = GameState.instance().getDungeon().getRoom(room[ran1-1]);
               if(a == GameState.instance().getPlayer().getAdventurersCurrentRoom()){
                   a = GameState.instance().getDungeon().getRoom(room[ran1-2]);
                   a.setCoverage(true);
               }else{
                 a.setCoverage(true);
               }
              return ("An earthquake has occured, "+ a.getTitle() + " is covered in debris");	
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
		return null;
	}

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}
