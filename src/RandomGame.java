import java.util.Random;

/**
 * this class bring random start for Game
 *
 * @author Amir Naziri
 * @version 1.0
 */
public class RandomGame
{

    /**
     * makes a random integer number between [1, 2]
     * @return random number
     */
    private int randomMaker ()
    {
        Random random = new Random();
        return random.nextInt (2) + 1;
    }

    /**
     * give random turn to players (who start game)
     * @param player1 player1
     * @param player2 player2
     */
    private void randomStarter (Player player1, Player player2)
    {
        if (randomMaker () == 1)
            player1.makeTurn ();
        else
            player2.makeTurn ();
    }

    /**
     * give random color to players
     * @param player1 player1
     * @param player2 player2
     */
    private void randomColor (Player player1, Player player2)
    {
        if (randomMaker () == 1) {
            player1.setTawColor ("red");
            player2.setTawColor ("black");
        } else {
            player1.setTawColor ("black");
            player2.setTawColor ("red");
        }
    }


    /**
     * this method is for two player game and gives a random color and turn to player
     * @param player1 player1
     * @param player2 player2
     */
    public void startRandomGame (Player player1 ,Player player2)
    {
        randomColor (player1,player2);
        randomStarter (player1,player2);
    }
}
