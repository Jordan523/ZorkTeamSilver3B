package Entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Dungeon.Dungeon;
import Dungeon.Room;
import Entities.Enemy.NoEnemyException;
import Game.GameState;

public class NPC extends Entity{

	private ArrayList<String> messages = new ArrayList<String>();
	private Scanner s;
	private Room currentRoom;
	private String idle;
	
	public NPC(String name,Scanner s, Dungeon d) throws NoEnemyException{
		super(name);
		
		
		System.out.println();
		while(s.hasNextLine()){
			
		
			
			String next = s.nextLine();
			System.out.println(next);
			if(next.equalsIgnoreCase("==="))
				throw new NoEnemyException();
			String[] roomAndRoomName = next.split(":");
			System.out.println(roomAndRoomName[1]);
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
						System.out.println(message);
						while(!message.contains("-")){
							message += s.nextLine();
							System.out.println(message);
						}
						System.out.println(message.replace("-", ""));
						this.messages.add(message.replace("-", ""));
						message = s.nextLine();
						
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
	
	public Room getCurrentRoom(){
		return this.currentRoom;
	}
	
	public String getIdleMessage(){
		return this.idle;
	}
}

