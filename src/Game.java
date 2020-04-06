public abstract class Game
{
    private Player player1;
    private Player player2;
    private GameHandler gameHandler;


    public Game ()
    {
        player1 = new Player ();
        player2 = new Player ();
        startPlay ();
        gameHandler = new GameHandler (player1,player2);
    }

    public Player getPlayer1 () {
        return player1;
    }

    public Player getPlayer2 () {
        return player2;
    }

    public GameHandler getGameHandler () {
        return gameHandler;
    }


    private void startPlay ()
    {
        RandomGame randomGame = new RandomGame ();
        randomGame.startRandomGame (player1, player2);
    }

    public boolean stopPlay ()
    {
        int result = gameHandler.checksForFinish ();
        switch (result)
        {
            case 0 : return true;
            case 1 :
                getGameHandler ().showMap (false);
                System.out.println ("player1 won");
                return false;
            case 2 :
                getGameHandler ().showMap (false);
                System.out.println ("Player2 won");
                return false;
            case 3 :
                getGameHandler ().showMap (false);
                System.out.println ("Draw");
                return false;
        }

        if (player1.getPoints () + player2.getPoints () == 36)
        {
            getGameHandler ().showMap (false);
            System.out.println ("Draw");
            return false;
        }
        return true;
    }

    protected void changeTurn ()
    {
        if (getPlayer1 ().isTurn ())
        {
            getPlayer1 ().doneTurn ();
            getPlayer2 ().makeTurn ();
        }
        else
        {
            getPlayer2 ().doneTurn ();
            getPlayer1 ().makeTurn ();
        }
    }

    abstract public void play ();
}
