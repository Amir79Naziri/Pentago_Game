/**
 * this class represents two player game
 *
 * @author Amir Naziri
 */
public class GameTwoPlayer extends Game
{

    /**
     * creates a new two player game
     */
    public GameTwoPlayer ()
    {
        super();
    }


    /**
     * play game
     */
    public void play ()
    {
        Reader reader = new Reader ();
        while (stopPlay ())
        {
            getPrinter ().showMap (true,getGameBase ().getBlocks (),
                    getGameBase ().getPlayer1 (),getGameBase ().getPlayer2 ());
            boolean result = getGameBase ().chooseTaw (reader.readFirstLine ());
            if (!result)
                continue;
            if (!stopPlay ())
                return;
            getPrinter ().showMap (true,getGameBase ().getBlocks (),
                    getGameBase ().getPlayer1 (),getGameBase ().getPlayer2 ());
            String[] splits = reader.readSecLine ();
            getGameBase ().rotate (Integer.parseInt (splits[0]),splits[1]);
            changeTurn ();
        }
    }
}
