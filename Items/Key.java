package Items;
/**
 * A key will extend Item. It's only purpose is to open locked doors. It can be picked up and
 * dropped. 
 * 
 * @author Team Silver
 * @version (a version number or a date)
 */
public class Key
{
    private String name;
    private int weight; 
    
    /**
     * Constructs a key object
     */
    public Key(String name, int weight)
    {
        this.name = name;
        this.weight = weight;
    }

    /**
     * Uses the key, may or may not disappear 
     * 
     * @param  y   none
     * @return     String message
     */
    public String use()
    {
      return null;   
    }
}
