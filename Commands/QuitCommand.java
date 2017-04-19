package Commands;
import java.util.Scanner;

/**
 * 
 * @author Jordan.Smith
 * This command quits the program but asks the player if they want to save first.
 */
public class QuitCommand extends Command{

	
	
	public QuitCommand(){
		execute();
	}
	
	public String execute(){
		
		Scanner in = new Scanner(System.in);
		System.out.println("Would you like to save before quiting?: ");
		String ans = in.nextLine();
		
		if (ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("y")){
			new SaveCommand("zork").execute();
			System.out.println("Progress saved.");
		}
		
		System.out.println("Bye!");
		System.exit(0);
		return "Bye!";
	}
}
