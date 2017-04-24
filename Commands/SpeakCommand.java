package Commands;

import Entities.NPC;
import Entities.Player;
import Game.GameState;

public class SpeakCommand extends Command{

	private NPC entity;
	private Player player = GameState.instance().getPlayer();
	private String entityName;
	
	public SpeakCommand(String[] commandLine){
		
		int connectorPlace = 0;
		//Connector check
		try{
			for(String x : commandLine){
				if(x.equalsIgnoreCase("with") || x.equalsIgnoreCase("to"))
					this.entityName = commandLine[connectorPlace+1];
				connectorPlace++;
			}
		}catch(Exception e){
			System.out.println("Whoops, don't know how that happened.");
			System.exit(0);
		}
		
		try{
		for(NPC x : GameState.instance().getDungeon().getNPCs()){
			
			if(x.getName().equalsIgnoreCase(this.entityName)){
				this.entity = x;
			}
		}
		}catch(Exception e){
			System.out.println("What?.\n");
		}
		
	}
	
	@Override
	public String execute() {
	
		try{
		if(this.entity.getCurrentRoom().equals(player.getAdventurersCurrentRoom()))
			this.entity.speakTo();
		else
			return "Speak with what?\n";
		return "\n";
		}catch(Exception e){
			return "What?\n";
		}
	}

}
