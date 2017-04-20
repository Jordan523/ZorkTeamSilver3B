package Commands;
import Game.GameState;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jordan.smith
 */
public class DescribeCommand extends Command{

    @Override
    public String execute() {
        return GameState.instance().getPlayer().getAdventurersCurrentRoom().fullDescribe();
    }
    
}
