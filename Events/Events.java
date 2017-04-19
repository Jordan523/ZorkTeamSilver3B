package Events;
/**
 * 
 * @author Xbox5
 * Event Interface. This class makes sure that every event class
 */
public interface Events {

        
        public boolean hasCalledTimer();
        
        public void setHasCalledTimer(boolean x);
    
        public void execute();
        
        public String getType();
	
}
