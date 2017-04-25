package Events;

import Entities.Player;
import Game.GameState;
import Items.Item;
import Items.Item.NoItemException;

public class UseEvent implements Events{
	
	private Item theItem;
	private String itemString;
	private Player player = GameState.instance().getPlayer();
	
	public UseEvent(Item item){
		this.theItem = item;
	}
	
	public UseEvent(String itemString){
		
		this.itemString = itemString;
		
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
		
		String[] sep = itemString.split("-");
		String item = sep[0];
		String newItem = sep[1];
		
		try {
			theItem = GameState.instance().getDungeon().getItem(item);
		} catch (NoItemException e) {
			System.out.println("What?");
		}
		
		for(Item x : player.getInventory()){
			if(x.equals(theItem)){
				
				new TransformEvent(newItem, theItem).execute();
				System.out.println(item + " was changed to a " + newItem);
				return;
			}
		}
		
		System.out.println("You don't have the necessary item to use this.");
		
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "UseEvent";
	}
	
	
}
