package Commands;
import Game.GameState;


public class GetCommand extends Command{

	private String whatToGet;
	
	GetCommand(String variable){
		this.whatToGet = variable;
	}
	
	public String execute() {
		
		if(whatToGet.equalsIgnoreCase("health")){
			return "Your current health is " + GameState.instance().getPlayer().getHealth()+"\n";
		}
		else if(whatToGet.equalsIgnoreCase("score")){
			return "Your current score is " + GameState.instance().getScore()+"\n";
		}
		else
			return "I dont know what " + whatToGet + " is.\n";
		
	}

}
