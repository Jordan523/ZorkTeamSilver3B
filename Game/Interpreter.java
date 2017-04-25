package Game;
import Dungeon.*;
import Commands.*;
import Events.*;
import Entities.*;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author ZorkTeamSilver
 * This is the main of the program. User input is taken in here and executed.
 *
 */
public class Interpreter {

    private static GameState state; // not strictly necessary; GameState is 
                                    // singleton

    public static String USAGE_MSG = 
        "Usage: Interpreter borkFile.bork|saveFile.sav.";
    public static String[] INTRO = {
        "Welcome to Trinkle: Silver Edition!",
        "You are a student at UMW.",
        "You are currently playing as another UMW CS student.",
        "As this other CS student, you must navigate the rooms of Trinkle Hall.",
        "Your goal? Learn the ultimate meaning of being a student.",
        "Helpful commands for getting started: 'help', 'describe', and of \ncourse, 'eat'",
        "Enjoy!"
    };

    public static void main(String args[]) {

        String command;
        Scanner commandLine = new Scanner(System.in);
        
        System.out.println("What adventure file would you like to play?");
        System.out.print("> ");
        String filename = commandLine.nextLine();

        System.out.println("What's your name, adventurer?: ");
        String playerName = commandLine.nextLine();
        
        try {
            int count = 0;
            state = GameState.instance();
            state.initPlayer(playerName);
            
            if (filename.endsWith(".zork")) {
                for (String line : INTRO) {
                    System.out.print(line+"\n  [Press enter to continue...]");
                    commandLine.nextLine();
                }
                state.initialize(new Dungeon(filename));
                System.out.println("\nWelcome to " + 
                    state.getDungeon().getName() + "!");
            } else if (filename.endsWith(".sav")) {
                state.restore(filename);
                System.out.println("\nWelcome back to " + 
                    state.getDungeon().getName() + "!");
            } else {
                System.err.println(USAGE_MSG);
                System.exit(2);
            }

            System.out.print("\n" + 
                state.getPlayer().getAdventurersCurrentRoom().describe() + "\n");

            command = promptUser(commandLine);

            boolean running = true;
            
            while (running) {
                Random rand = new Random();
                int x = rand.nextInt(100);
                if(count == 5){
                   if(GameState.instance().getEarthquake()==false){
                   int q = rand.nextInt(10);
                   if(q==1){
                       Earthquake eq = new Earthquake();
                       System.out.println(eq.execute1());
                   }
                   GameState.instance().setEarthquake(true);
                   }
                   count =0;
               }

                System.out.print(
                           CommandFactory.instance().parse(command).execute());
                          
                 if(GameState.instance().getPlayer().getHealth() < 1){
                    System.out.println("You Died!");
                    System.exit(0);
                }
                
                
                GameState.instance().getDungeon().updateDungeon();
                command = promptUser(commandLine);
                
                
             count++;  
            }

            System.out.println("Bye!");

        } catch(Exception e) { 
            e.printStackTrace(); 
        }
    }

    private static String promptUser(Scanner commandLine) {

        System.out.print("> ");
        return commandLine.nextLine();
    }

}
