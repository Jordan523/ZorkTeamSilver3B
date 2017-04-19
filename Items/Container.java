package Items;
import java.util.ArrayList;
/**
 * A container will hold a certain amount of items, similar to an inventory. It will function as such,
 * and some of them may be able to be brought along and even added to the user's inventory.
 * 
 * @author Team Silver
 * @version 1
 */
public class Container 
{
    private String name;
    private int weight;
    private int size;
    private boolean canCarry;
    private ArrayList<Item> contents; 
    /**
     * The container constructor will have all required fields passed in and initialize them.
     */
    public Container(String name, int weight, int size, boolean canCarry, ArrayList<Item> contents)
    {
        this.name = name;
        this.weight = weight;
        this.size = size;
        this.canCarry = canCarry;
        this.contents =  new ArrayList<Item>();
    }

    /**
     * this method will determine if a container can be added to a user's inventory
     * 
     * @param  y   none
     * @return     none
     */
    public void bringAlong()
    {
        if(weight < 10)  
        {
            canCarry = true;
        }
    }
    /**
     * this method will be used if a user wants to add an item to a container. It will function
     * in a similar way to drop, but it will be place in a container and not just a room. 
     * Inventory management*
     * 
     * @param  y   none
     * @return     none
     */
    public void add(Item item)
    {
        contents.add(item);
    }
    /**
     * this method will be used if a user wants to remove an item from the container back into the inventory
     * 
     * if the container is not able to be carried, it will not work
     * 
     * 
     */
    public void remove(Item item)
    {
        if(canCarry == false)
            System.out.print("That won't work");
        else    
            contents.remove(item);
    }
}
