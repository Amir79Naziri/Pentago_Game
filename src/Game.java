public class Game
{
    Player player1;
    Player player2;
    GameHandler gameHandler;


    public Game ()
    {
        player1 = new Player ();
        player2 = new Player ();
        gameHandler = new GameHandler ();
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


    public void startPlay ()
    {
        RandomGame randomGame = new RandomGame ();
        randomGame.startRandomGame (player1, player2);
    }

    public void stopPlay ()
    {

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

}
