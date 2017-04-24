package Commands;

import Entities.NPC;
import Entities.Player;
import Game.GameState;

public class SpeakCommand extends Command{

	private NPC entity;
	private Player player = GameState.instance().getPlayer();
	private String entityName;
	
	public SpeakCommand(String name){
		
		this.entityName = name;
		
		try{
		for(NPC x : GameState.instance().getDungeon().getNPCs()){
			
			if(x.getName().equalsIgnoreCase(name)){
				this.entity = x;
			}
		}
		}catch(Exception e){
			System.out.println("Unable to speak with " +name+".\n");
		}
		
	}
	
	@Override
	public String execute() {
	
		try{
		if(this.entity.getCurrentRoom().equals(player.getAdventurersCurrentRoom()))
			this.entity.speakTo();
		else
			return "Unable to speak with " + this.entity.getName()+".\n";
		return "\n";
		}catch(Exception e){
			return "Unable to speak with " + entityName +".\n";
		}
	}

}
