package Commands;
import Game.GameState;
import Events.*;
import Items.*;
import java.util.*; 

/**
 * 
 * @author jordan.smith
 * This class will execute commands specific to items
 */
class ItemSpecificCommand extends Command {

    private String verb;
    private String noun;
    private ArrayList<Events> events;
                        
/**
 * 
 * @param verb verb to indicate command
 * @param noun object to use verb on
 */
    ItemSpecificCommand(String verb, String noun) {
        this.verb = verb;
        this.noun = noun;
        events = new ArrayList<>();
    }
    
/**
 * 
 * @return returns the message corresponding to the noun given
 * This will execute the command or return the appropriate message.
 */
    public String execute() {
        
        Item itemReferredTo = null;
        try {
            itemReferredTo = GameState.instance().getItemInVicinityNamed(noun);
            
        } catch (Item.NoItemException e) {
            return "There's no " + noun + " here.";
        }
        
        String msg = itemReferredTo.getMessageForVerb(verb);
      
        try {
            Set<Events> keys = GameState.instance().getItemInVicinityNamed(noun).itemEvents().keySet();
            
            for (Events e : keys) {
                if (GameState.instance().getItemInVicinityNamed(noun).itemEvents().get(e).equalsIgnoreCase(verb)) {
                    this.events.add(e);
                }
            }
        } catch (Item.NoItemException e) {
            
        }
        
        //Execute all events associated with this command
        for (Events e : events) {
            e.execute();
        }
        
        return (msg == null ? "Sorry, you can't " + verb + " the " + noun + "." : msg) + "\n";
    }
}