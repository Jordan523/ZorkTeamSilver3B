package Commands;

public class HelpCommand extends Command{

	
	public HelpCommand(){
		
	}
	
	@Override
	public String execute() {
		System.out.println("Helpful commands: \n" + 
						   "Describe current room --------------- Describe , Desc\n" +
						   "Attack enemy ------------------------ Attack + enemy name\n" + 
						   "Pickup items ------------------------ take + item name\n" + 
						   "Drop items -------------------------- drop + item name\n" + 
						   "Weild an item as a weapon ------------ equip/hold + item name\n" +
						   "Check time --------------------------- get + time , time\n" +
						   "Check score -------------------------- get + score, score\n" +
							"Good Luck!!!");
		return "";
	}

}
