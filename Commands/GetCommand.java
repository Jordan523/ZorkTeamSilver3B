package Commands;
import Entities.Player;
import Game.GameState;


public class GetCommand extends Command{

	private String whatToGet;
	private Player player = GameState.instance().getPlayer();
	
	GetCommand(String variable){
		this.whatToGet = variable;
	}
	
	public String execute() {
		
		if(whatToGet.equalsIgnoreCase("health")){
			return "Your current health is " + player.getHealth()+"\n";
		}
		else if(whatToGet.equalsIgnoreCase("score")){
			return "Your current score is " + player.getScore()+"\n";
		}
		else if(whatToGet.equalsIgnoreCase("time"))
			return "The current time: " + player.getTime()+"\n";
		else
			return "I dont know what " + whatToGet + " is.\n";
		
	}

}
