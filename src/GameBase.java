/**
 * this class is the central management for game and implements rules of game
 *
 * @author Amir Naziri
 */
public class GameBase
{
    private Player player1; // player1
    private Player player2; // player2
    private Block[] blocks; // list of blocks


    /**
     * creates a new instance of gameBase
     */
    public GameBase ()
    {
        this.player1 = new Player ();
        this.player2 = new Player ();
        blocks = new Block[4];
        makeAllBlocks ();
    }


    /**
     * @return player1
     */
    public Player getPlayer1 () {
        return player1;
    }

    /**
     * @return player2
     */
    public Player getPlayer2 () {
        return player2;
    }

    /**
     * @return list of blocks
     */
    public Block[] getBlocks () {
        return blocks;
    }

    /**
     * creates all four block
     */
    private void makeAllBlocks ()
    {
        for (int i = 0; i < 4; i++)
            blocks[i] = new Block ();
    }

    /**
     * rotate act
     * @param blockNumber blockNumber
     * @param dir cw or ccw
     */
    public void rotate (int blockNumber, String dir) // dir : ccw or cw
    {
        if (!(blocks[blockNumber - 1].isBlockEmpty ()))
        {
            if (dir.equals ("cw"))
                blocks[blockNumber - 1].clockWiseChange ();
            else
                blocks[blockNumber - 1].counterClockWiseChange ();
        }
    }

    /**
     * choose Taw act
     * @param taw choosen Taw
     * @return was it successful
     */
    public boolean chooseTaw (Coordinate taw)
    {
        String color;
        if (player1.isTurn ())
            color = player1.getTawColor ();
        else
            color = player2.getTawColor ();
        return blocks[taw.getBlockNumber () - 1].chooseTaw (taw,color);
    }

    /**
     * checks the rules for finishing game
     * @return 1 means player1 won , 2 means player2 won , 0 means game should continue ,
     3 means draw
     */
    public int checksForFinish ()
    {
        boolean playerOneWon = false;
        boolean playerTwoWon = false;
        for (int y = 0; y < 6; y++)
            for (int x = 0; x < 6; x++)
            {
                Coordinate coordinate = new Coordinate (x,y);
                if (blocks[coordinate.getBlockNumber () - 1].
                        getTaw ()[coordinate.getY ()][coordinate.getX ()].
                        getColor ().equals (player1.getTawColor ()) && checkForATaw (coordinate))
                    playerOneWon = true;
                if (blocks[coordinate.getBlockNumber () - 1].
                        getTaw ()[coordinate.getY ()][coordinate.getX ()].
                        getColor ().equals (player2.getTawColor ()) && checkForATaw (coordinate))
                    playerTwoWon = true;
            }
        if (playerOneWon && playerTwoWon)
            return 3;
        if (playerOneWon)
            return 1;
        if (playerTwoWon)
            return 2;
        else
            return 0;
    }

    /**
     * checking for finding five Taw in row or col or ...
     * @param taw Taw
     * @return is reached 5
     */
    private boolean checkForATaw (Coordinate taw)
    {
        if (taw != null)
        {
            int x = taw.getMainX ();
            int y = taw.getMainY ();
            String color = blocks[taw.getBlockNumber () - 1].
                    getTaw ()[taw.getY ()][taw.getX ()].getColor ();

            if (x - 1 >= 0 && y - 1 >= 0) {
                Coordinate coordinate = new Coordinate (x - 1, y - 1);
                if (blocks[coordinate.getBlockNumber () - 1].
                        getTaw ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                    if (checkInDir (color, taw, 1) >= 5)
                        return true;
            }
            if (y - 1 >= 0) {
                Coordinate coordinate = new Coordinate (x, y - 1);
                if (blocks[coordinate.getBlockNumber () - 1].
                        getTaw ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                    if (checkInDir (color, taw, 2) >= 5)
                        return true;
            }
            if (y - 1 >= 0 && x + 1 < 6) {
                Coordinate coordinate = new Coordinate (x + 1, y - 1);
                if (blocks[coordinate.getBlockNumber () - 1].
                        getTaw ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                    if (checkInDir (color, taw, 3) >= 5)
                        return true;
            }
            if (x - 1 >= 0) {
                Coordinate coordinate = new Coordinate (x - 1, y);
                if (blocks[coordinate.getBlockNumber () - 1].
                        getTaw ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                    if (checkInDir (color, taw, 4) >= 5)
                        return true;
            }
            if (x + 1 < 6) {
                Coordinate coordinate = new Coordinate (x + 1, y);
                if (blocks[coordinate.getBlockNumber () - 1].
                        getTaw ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                    if (checkInDir (color, taw, 5) >= 5)
                        return true;
            }
            if (y + 1 < 6) {
                Coordinate coordinate = new Coordinate (x, y + 1);
                if (blocks[coordinate.getBlockNumber () - 1].
                        getTaw ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                    if (checkInDir (color, taw, 6) >= 5)
                        return true;
            }
            if (y + 1 < 6) {
                Coordinate coordinate = new Coordinate (x, y + 1);
                if (blocks[coordinate.getBlockNumber () - 1].
                        getTaw ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                    if (checkInDir (color, taw, 7) >= 5)
                        return true;
            }
            if (y + 1 < 6 && x + 1 < 6) {
                Coordinate coordinate = new Coordinate (x + 1, y + 1);
                if (blocks[coordinate.getBlockNumber () - 1].
                        getTaw ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                    return checkInDir (color, taw, 8) >= 5;
            }
        }
        return false;
    }

    /**
     * checking for finding five Taw in row or col or ...
     * @param color color of player in turn
     * @param taw start Taw
     * @param dir northwest , north , ...  : (1,2, .. . , 8)
     * @return number of sequence
     */
    private int checkInDir (String color, Coordinate taw,int dir)
    {
        if (color != null && taw != null)
        {
            int x = taw.getMainX ();
            int y = taw.getMainY ();

            int counter = 0;
            while (x >= 0 && x <= 5 && y >= 0 && y <= 5) {
                Coordinate coordinate = new Coordinate (x, y);
                if (!(blocks[coordinate.getBlockNumber () - 1].
                        getTaw ()[coordinate.getY ()][coordinate.getX ()]).
                        getColor ().equals (color))
                    return counter;
                else
                    counter++;

                switch (dir) {
                    case 1:
                        x--;
                        y--;
                        break;
                    case 2:
                        y--;
                        break;
                    case 3:
                        x++;
                        y--;
                        break;
                    case 4:
                        x--;
                        break;
                    case 5:
                        x++;
                        break;
                    case 6:
                        x--;
                        y++;
                        break;
                    case 7:
                        y++;
                        break;
                    case 8:
                        x++;
                        y++;
                        break;
                }
            }
            return counter;
        }
        else return 0;
    }
}
