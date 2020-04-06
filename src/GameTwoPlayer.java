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
            getGameHandler ().showMap (true);
            boolean result = getGameHandler ().chooseTaw (reader.readFirstLine ());
            if (!result)
                continue;
            if (!stopPlay ())
                return;
            getGameHandler ().showMap (true);
            String[] splits = reader.readSecLine ();
            getGameHandler ().rotate (Integer.parseInt (splits[0]),splits[1]);
            changeTurn ();
        }
    }
}
