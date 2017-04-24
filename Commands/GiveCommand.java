package Commands;

import Entities.NPC;
import Entities.Player;
import Game.GameState;
import Items.Item;
import Items.Item.NoItemException;

public class GiveCommand extends Command{

	private String npcName;
	private String item;
	
	public GiveCommand(String[] parts){
		int connectorPlace = 0;
		 
		item = parts[1];
		
		for(String x : parts){
			if(x.equalsIgnoreCase("to"))
				npcName = parts[connectorPlace+1];
			connectorPlace++;
		}
		
	}
	
	@Override
	public String execute() {
		
		try{
			NPC npc = GameState.instance().getDungeon().getNPC(npcName);
			Item theItem = GameState.instance().getDungeon().getItem(item);
			Player player = GameState.instance().getPlayer();
			
			npc.giveItem(theItem);
			player.removeFromInventory(theItem);
			
			System.out.println("You have given the " + item + " to " + npc.getName());
			
		}catch(NoItemException e){
			System.out.println("You don't have that item to give.");
		}
		catch(Exception e){
			System.out.println("You can not give away the " + item+".");
		}
		
		
		return "\n";
	}

}
