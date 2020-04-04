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
            gameHandler.showMap ();
            boolean result = gameHandler.chooseTaw (reader.readFirstLine ());
            if (!result)
                continue;
            gameHandler.showMap ();
            String[] splits = reader.readSecLine ().split (" ");
            gameHandler.rotate (Integer.parseInt (splits[0]),splits[1]);
            changeTurn ();
        }
    }
}
