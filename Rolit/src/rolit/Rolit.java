package rolit;

import java.util.ArrayList;

//import rolit.ComputerPlayer;
import rolit.Game;
import rolit.HumanPlayer;
import rolit.Mark;
//import rolit.NaiveStrategy;
//import rolit.PerfectStrategy;
import rolit.Player;
//import rolit.SmartStrategy;

/**
 * Executable class for the game Rolit. The game can be played against the computer.
 * Project Module 2
 * 
 * @author Sophie Lathouwers & Kevin Alberts
 * @version $Revision: 1.0 $
 */
public class Rolit {

    /**
     * Creates a player based on the provided kind and mark.
     * <ul>
     * <li>If <code>s.equals("-N")</code> creates a naive computer player.
     * <li>If <code>s.equals("-S")</code> creates a smart computer player.
     * <li>If <code>s.equals("-P")</code> creates a perfect computer player.
     * <li>Otherwise creates a human player.
     * </ul>
     * 
     * @param s - name / kind of player
     * @param m - mark to be used by the player
     * @return the Player object
     */
    private static Player createPlayer(String s, Mark m) {
        //if (s.equals("-N") || s.equals("-n")) {
        //    return new ComputerPlayer(m, new NaiveStrategy());
        //} else if (s.equals("-S") || s.equals("-s")) {
        //    return new ComputerPlayer(m, new SmartStrategy());
        //} else if (s.equals("-P") || s.equals("-p")) {
        //    return new ComputerPlayer(m, new PerfectStrategy());
        //} else {
            return new HumanPlayer(s, m);
        //}
    }

    /** Creates top-level objects and starts the game. */
    public static void main(String[] args) {
        if (args.length < Game.MAX_PLAYERS && args.length != 0) {
            System.out.println("Rolit!");
            System.out.println("--------------------------");
            
            // Decide on number of players based on args.length
            int numPlayers = args.length;
            ArrayList<Player> players = new ArrayList<Player>();
            Mark[] marks = {Mark.RED, Mark.YEL, Mark.BLU, Mark.GRN}; 
            		
            for(int i=0; i<numPlayers; i++){
            	players.add(createPlayer(args[i], marks[i]));
            }
            
            Game spel = new Game(players);
            
            spel.start();
        } else {
            System.out.println("usage: Rolit <name1> <name2> [<name3> [<name4>]]");
        }
    }

}
