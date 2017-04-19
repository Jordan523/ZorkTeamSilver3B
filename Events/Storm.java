package Events;
/**
 * 
 * @author Xbox5
 *
 *This class will initiate a Storm to occur in 
 *the dungeon.
 */
public class Storm implements Events{

	private int timeToLive;
	
	/**
	 * 
	 * @param ttl
	 * This will create a Storm that last for as
	 * long as the time given in seconds.  
	 */
	public Storm(int ttl){
		this.timeToLive = ttl;
	}
	
	
	/**
	 * This methods changes values in the GameState to 
	 * initiate a weather change. This will
	 * also have a lightning side effect that randomly
	 * hits.
	 */
	public void execute(){
		
		
		
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
	
}
