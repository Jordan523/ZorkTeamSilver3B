package Dungeon;
 
import java.util.Scanner;


public class Exit {

    class NoExitException extends Exception {}

    private String dir;
    private Room src, dest;

    Exit(String dir, Room src, Room dest) {
        init();
        this.dir = dir;
        this.src = src;
        this.dest = dest;
        src.addExit(this);
    }

    /** Given a Scanner object positioned at the beginning of an "exit" file
        entry, read and return an Exit object representing it. 
        @param d The dungeon that contains this exit (so that Room objects 
        may be obtained.)
        @throws NoExitException The reader object is not positioned at the
        start of an exit entry. A side effect of this is the reader's cursor
        is now positioned one line past where it was.
        @throws IllegalDungeonFormatException A structural problem with the
        dungeon file itself, detected when trying to read this room.
     */
    Exit(Scanner s, Dungeon d) throws NoExitException,
        Dungeon.IllegalDungeonFormatException {

        init();
        String srcTitle = s.nextLine();
        if (srcTitle.equals(Dungeon.TOP_LEVEL_DELIM)) {
            throw new NoExitException();
        }
        src = d.getRoom(srcTitle);
        dir = s.nextLine();
        dest = d.getRoom(s.nextLine());
        
        // I'm an Exit object. Great. Add me as an exit to my source Room too,
        // though.
        src.addExit(this);

        // throw away delimiter
        if (!s.nextLine().equals(Dungeon.SECOND_LEVEL_DELIM)) {
            throw new Dungeon.IllegalDungeonFormatException("No '" +
                Dungeon.SECOND_LEVEL_DELIM + "' after exit.");
        }
    }

    // Common object initialization tasks.
    private void init() {
    }

    String describe() {
        if (src.isBlocked()||src.getCovered()) {
            return "There is a blocked doorway on the " + dir + " wall.";
        } else if (!dest.getLight()) {
            return "You could go " + dir + ", but the passage is too dark to read.";
        } else {
            return "You can go " + dir + " to " + dest.getTitle() + ".";
        }
    }

    String getDir() { return dir; }
    Room getSrc() { return src; }
    Room getDest() {
        if (dest.isBlocked()) {
            System.out.println("You try to force your way past with no avail.");
            return null;
        } else if (dest.getCovered()) {
            System.out.println("The exit is covered by debris from the earthquake, the only way"
                + "\n through is to dig.");
            return null;
        } else if (!dest.getLight()) {
            // add check for if player can illuminate the room anyways?
            //System.out.println("You tremble at the though of what might hide in the shadows beyond.");
            return dest;
        } else {
            return dest;
        }
    }
    Room peekAtDest() {
        return this.dest;
    }
}
