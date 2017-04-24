package Events;

import Entities.Player;
import Game.GameState;
import Items.Item;
import Items.Item.NoItemException;

public class UseEvent implements Events{
	
	private Item theItem;
	private String newItem;
	private Player player = GameState.instance().getPlayer();

	public UseEvent(Item item){
		this.theItem = item;
	}
	
	public UseEvent(String item, String newItem){
		try {
			theItem = GameState.instance().getDungeon().getItem(item);
			this.newItem = newItem;
		} catch (NoItemException e) {
			e.printStackTrace();
		}
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
		
		//if(this.theItem.getAmount() > 1){
			//this.theItem.reduceAmount();
		//}
		//else
			//new DisappearEvent(this.theItem).execute();
		
		for(Item x : player.getInventory()){
			if(x.equals(theItem)){
				
				new TransformEvent(newItem, theItem);
				return;
			}
		}
		
		
		
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "UseEvent";
	}
	
	
}
