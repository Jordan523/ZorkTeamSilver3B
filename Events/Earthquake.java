package Events;

/**
 * 
 * @author Xbox5
 *
 *This class will initiate a Storm to occur in 
 *the dungeon.
 */
public class Earthquake implements Events{


	
	/**
	 * This method searches the rooms in the Dungeon
	 * to see what rooms will be affected by an Earthquake.
	 * It will also close certain exits. 
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
