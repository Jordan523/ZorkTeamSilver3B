package Events;
import Game.GameState;
import Items.*;
/**
 *
 * @author Nathan
 */
public class TransformEvent implements Events {
    private String itemName;
    private Item orig;
    
    public TransformEvent(String itemName, Item orig) {
        this.itemName = itemName;
        this.orig = orig;
    }
    
    @Override
    public void execute() {
        GameState gs = GameState.instance();
        Item newItem;
        boolean found = true;
        
        // Check the player's inventory
        try {
            newItem = GameState.instance().getDungeon().getItem(itemName);
            
            if (gs.getItemFromInventoryNamed(orig.getPrimaryName()) != null) {
                gs.getPlayer().removeFromInventory(orig);
                gs.getPlayer().addToInventory(newItem);
            }
        } catch (Item.NoItemException ex) {
            found = false;
        }
        
        // If it's not in the inventory, then check the room
        if (!found) {
            try {
                newItem = GameState.instance().getDungeon().getItem(itemName);
                
                if (gs.getAdventurersCurrentRoom().getItemNamed(orig.getPrimaryName()) != null) {
                    gs.getAdventurersCurrentRoom().remove(orig);
                    gs.getAdventurersCurrentRoom().add(newItem);
                }
            } catch (Item.NoItemException ex) {
                
            }
        }
    }

    @Override
    public boolean hasCalledTimer() { return false; }

    @Override
    public void setHasCalledTimer(boolean x) {}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}
}
