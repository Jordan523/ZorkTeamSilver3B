package Events;
import Game.GameState;
/**
 * 
 * @author Xbox5
 * This class will cause the player to receive damage when executed.
 */
public class Wound implements Events{

	
	private int damageAmount;
	
	/**
	 * @param damageAmount the amount of damage dealt to the player
	 * Creats a wound event with a damage amount.
	 */
	public Wound(int damageAmount){
		this.damageAmount = damageAmount;
	}
	
	
	/**
	 * This will decrease the players health by the given amount.
	 */
	public void execute() { 
		if(this.damageAmount < 0)
			GameState.instance().getPlayer().addToHealth(damageAmount*-1);
		else
			GameState.instance().getPlayer().reduceHealth(damageAmount*-1);
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
