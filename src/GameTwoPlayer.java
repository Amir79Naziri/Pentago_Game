public class GameTwoPlayer extends Game
{

    public GameTwoPlayer ()
    {
        super();
    }

    public void play ()
    {
        Reader reader = new Reader ();
        while (stopPlay ())
        {
            getUserInterface ().showMap (true,getGameBase ().getBlocks (),
                    getGameBase ().getPlayer1 (),getGameBase ().getPlayer2 ());
            boolean result = getGameBase ().chooseTaw (reader.readFirstLine ());
            if (!result)
                continue;
            if (!stopPlay ())
                return;
            getUserInterface ().showMap (true,getGameBase ().getBlocks (),
                    getGameBase ().getPlayer1 (),getGameBase ().getPlayer2 ());
            String[] splits = reader.readSecLine ();
            getGameBase ().rotate (Integer.parseInt (splits[0]),splits[1]);
            changeTurn ();
        }
    }
}
