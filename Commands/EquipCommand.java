package Commands;

import Items.Item;
import Items.Item.NoItemException;
import Game.GameState;


public class EquipCommand extends Command{

	private String itemToEquip;
	
	public EquipCommand(String itemToEquip){ this.itemToEquip = itemToEquip;}
	
	
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		
		
		try{
			
			Item item = GameState.instance().getItemInVicinityNamed(itemToEquip);
			GameState.instance().getPlayer().equip(item);
			
		}catch(NoItemException e){
			
			return "There is no " + itemToEquip + " to equip.";
		}
		
		
		return itemToEquip + " equiped.";
	}
	
	

}
