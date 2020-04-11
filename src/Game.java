/**
 * this class represents a new Game with start and stop game , play game , ... methods
 *
 * @author Amir Naziri
 */
public abstract class Game
{
    private GameBase gameBase; // game base
    private Printer printer; // painter


    /**
     * creates a new game
     */
    public Game ()
    {
        gameBase = new GameBase ();
        printer = new Printer ();
        startPlay ();
    }


    /**
     * @return game base
     */
    public GameBase getGameBase () {
        return gameBase;
    }

    /**
     * @return printer
     */
    public Printer getPrinter () {
        return printer;
    }

    /**
     * starts game
     */
    private void startPlay ()
    {
        RandomGame randomGame = new RandomGame ();
        randomGame.startRandomGame (gameBase.getPlayer1 (), gameBase.getPlayer2 ());
    }

    /**
     * checks the condition for stop game
     * @return can game continue
     */
    public boolean stopPlay ()
    {
        int result = gameBase.checksForFinish ();
        switch (result)
        {
            case 0 : return true;
            case 1 :
                printer.showMap (false,getGameBase ().getBlocks (),
                        getGameBase ().getPlayer1 (),getGameBase ().getPlayer2 ());
                getPrinter ().printTawForTurn (getGameBase ().getPlayer1 ().getTawColor (),
                        "won");
                return false;
            case 2 :
                printer.showMap (false,getGameBase ().getBlocks (),
                        getGameBase ().getPlayer1 (),getGameBase ().getPlayer2 ());
                getPrinter ().printTawForTurn (getGameBase ().getPlayer2 ().getTawColor (),
                        "won");
                return false;
            case 3 :
                printer.showMap (false,getGameBase ().getBlocks (),
                        getGameBase ().getPlayer1 (),getGameBase ().getPlayer2 ());
                System.out.println ("Draw");
                return false;
        }

        if (getGameBase ().mapFull ())
        {
            printer.showMap (false,getGameBase ().getBlocks (),
                    getGameBase ().getPlayer1 (),getGameBase ().getPlayer2 ());
            System.out.println ("Draw");
            return false;
        }
        return true;
    }

    /**
     * change turn
     */
    public void changeTurn ()
    {
        if (gameBase.getPlayer1 ().isTurn ())
        {
            gameBase.getPlayer1 ().doneTurn ();
            gameBase.getPlayer2 ().makeTurn ();
        }
        else
        {
            gameBase.getPlayer2 ().doneTurn ();
            gameBase.getPlayer1 ().makeTurn ();
        }
    }

    /**
     * play a game
     */
    abstract public void play ();
}
