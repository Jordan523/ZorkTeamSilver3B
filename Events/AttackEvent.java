package Events;

import java.util.Random;

public class AttackEvent implements Events{

	Random rand = new Random();
	private int maxDmg;
	private int critDmg = maxDmg * 2;
	private int minDmg;
	
	public AttackEvent(int minDmg, int maxDmg){
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
	}
	
	@Override
	public boolean hasCalledTimer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHasCalledTimer(boolean x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "AttackEvent";
	}
	
	public int getDamage(){
		
		int damageChance = rand.nextInt(2) + 1;
        int critChance = rand.nextInt(100) + 1;
        
      
        
        switch(damageChance){
        
        case 1:
        	if(critChance < 6)
        		return critDmg;
        	else
        		return rand.nextInt(maxDmg) + minDmg;
        case 2:
        	if(critChance < 6)
        		return critDmg;
        	else
        		return minDmg;
        }
        
        return minDmg;
	}
	
	public int getMaxDamage(){
		return this.maxDmg;
	}
	
	public int getMinDamage(){
		return this.minDmg;
	}
}
