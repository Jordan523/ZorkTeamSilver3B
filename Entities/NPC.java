package Entities;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

import Dungeon.Dungeon;
import Dungeon.Room;
import Entities.Enemy.NoEnemyException;
import Entities.Player.TooHeavyException;
import Events.Events;
import Events.Win;
import Game.GameState;
import Items.Item;
import Items.Item.NoItemException;

public class NPC extends Entity{

	private ArrayList<String> messages = new ArrayList<String>();
	private Scanner s;
	private Room currentRoom;
	private String idle;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private Hashtable<Item, Events> ite = new Hashtable<>();
	
	public NPC(String name,Scanner s, Dungeon d) throws NoEnemyException{
		super(name);
		
		
		System.out.println();
		while(s.hasNextLine()){
			
		
			
			String next = s.nextLine();
			if(next.equalsIgnoreCase("==="))
				throw new NoEnemyException();
			String[] roomAndRoomName = next.split(":");
			this.currentRoom = d.getRoom(roomAndRoomName[1]);
			currentRoom.addNPC(this);
			next = s.nextLine();
			if(next.contains("Idle:")){
				String[] sep = next.split(":");
				this.idle = sep[1];
				next = s.nextLine();
			}
			
			if(next.equalsIgnoreCase("---")){
				break;
			}
			else{
				String message = next;
				
				if(message.equalsIgnoreCase("Messages:")){
					message = s.nextLine();
					while(true){
						if(message.equals("---"))
							break;
						while(!message.contains("-")){
							message += s.nextLine();
						}
						this.messages.add(message.replace("-", ""));
						message = s.nextLine();
						if(message.equalsIgnoreCase("Events:")){
							String nextEvent = s.nextLine();
							while(!nextEvent.equals("---")){
								String[] sep = nextEvent.split(":");
								if(sep[0].equalsIgnoreCase("Win")){
									try {
										this.ite.put(d.getItem(sep[1]), new Win());
									} catch (NoItemException e) {}
									nextEvent = s.nextLine();
								}
								message = nextEvent;
							}
						}
						
					}
				}
				
				break;
			}
			
		}
		
	}
	
	
	
	
	public void speakTo(){
		
		Random rand = new Random();
		
		int messageNum = rand.nextInt(messages.size()) + 0;
		
		
		System.out.println(messages.get(messageNum));
		
		
		
		
	}
	
	public void update(){
		
		try{
			for(Item x : inventory){
				
				this.ite.get(x).execute();
				
			}
		}catch(Exception e){}
		
	}
	
	public void giveItem(Item item){
		this.inventory.add(item);
		this.update();
	}
	
	public void takeItem(Item item){
		try {
			GameState.instance().getPlayer().addToInventory(item);
			inventory.remove(item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("I don't know how to do that.");
		}
		
	}
	
	public Room getCurrentRoom(){
		return this.currentRoom;
	}
	
	public String getIdleMessage(){
		return this.idle;
	}
	
	public ArrayList<Item> getInventory(){
		return inventory;
	}
}

