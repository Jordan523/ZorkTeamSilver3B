package Events;
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
           System.out.println("You win!");
           System.exit(0);
    }

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	};
    
}
