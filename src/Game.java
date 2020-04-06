public abstract class Game
{
    private GameBase gameBase;
    private UserInterface userInterface;

    public Game ()
    {
        gameBase = new GameBase ();
        userInterface = new UserInterface ();
        startPlay ();
    }

    public GameBase getGameBase () {
        return gameBase;
    }

    public UserInterface getUserInterface () {
        return userInterface;
    }

    private void startPlay ()
    {
        RandomGame randomGame = new RandomGame ();
        randomGame.startRandomGame (gameBase.getPlayer1 (), gameBase.getPlayer2 ());
    }

    public boolean stopPlay ()
    {
        int result = gameBase.checksForFinish ();
        switch (result)
        {
            case 0 : return true;
            case 1 :
                userInterface.showMap (false,getGameBase ().getBlocks (),
                        getGameBase ().getPlayer1 (),getGameBase ().getPlayer2 ());
                getUserInterface ().printTawForTurn (getGameBase ().getPlayer1 ().getTawColor (),
                        "won");
                return false;
            case 2 :
                userInterface.showMap (false,getGameBase ().getBlocks (),
                        getGameBase ().getPlayer1 (),getGameBase ().getPlayer2 ());
                getUserInterface ().printTawForTurn (getGameBase ().getPlayer2 ().getTawColor (),
                        "won");
                return false;
            case 3 :
                userInterface.showMap (false,getGameBase ().getBlocks (),
                        getGameBase ().getPlayer1 (),getGameBase ().getPlayer2 ());
                System.out.println ("Draw");
                return false;
        }

        if (gameBase.getPlayer1 ().getPoints () + gameBase.getPlayer2 ().getPoints () == 36)
        {
            userInterface.showMap (false,getGameBase ().getBlocks (),
                    getGameBase ().getPlayer1 (),getGameBase ().getPlayer2 ());
            System.out.println ("Draw");
            return false;
        }
        return true;
    }

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

    abstract public void play ();
}
