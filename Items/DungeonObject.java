package Items;
/**
 * A dungeon object will be something like a switch or a moveable object. 
 * 
 * @author Team Silver
 * @version 1
 */
public class DungeonObject
{
    // instance variables - replace the example below with your own
    private String name;
    
    /**
     * Constructor for objects of class DungeonObject
     */
    public DungeonObject(String name)
    {
        this.name = name;
    }

    /**
     * This method allows the user to 'use' the particular object in it's own unique way
     * 
     * @param  y   none
     * @return     a String message
     */
    public String use()
    {
        return null;
    }
}
