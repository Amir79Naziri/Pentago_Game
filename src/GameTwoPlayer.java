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
            Coordinate chosenTaw = reader.readFirstLine ();
            String[] splits = reader.readSecLine ().split (" ");
            boolean result = gameHandler.act (chosenTaw,
                    Integer.parseInt (splits[0]),splits[1]);
            if (result)
                changeTurn ();
        }
    }
}
