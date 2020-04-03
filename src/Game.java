public abstract class Game
{
    Player player1;
    Player player2;
    GameHandler gameHandler;


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
            case 0 : return false;
            case 1 :
                System.out.println ("Player1 won");
                return true;
            case 2 :
                System.out.println ("Player2 won");
                return true;
            case 3 :
                System.out.println ("Draw");
                return true;
        }

        if (player1.getPoints () + player2.getPoints () == 36)
            System.out.println ("Draw");
        return true;
    }

    protected void changeTurn ()
    {
        if (player1.isTurn ())
        {
            player1.doneTurn ();
            player2.makeTurn ();
        }
        else
        {
            player2.doneTurn ();
            player1.makeTurn ();
        }
    }

    abstract public void play ();
}
