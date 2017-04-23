package Events;

import Items.Item;

public class UseEvent implements Events{
	
	private Item theItem;

	public UseEvent(Item item){
		this.theItem = item;
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
		
		
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "UseEvent";
	}
	
	
}
