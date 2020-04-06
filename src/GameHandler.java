
public class GameHandler
{
    private Player player1;
    private Player player2;
    private Block[] blocks;

    public GameHandler (Player player1, Player player2)
    {
        this.player1 = player1;
        this.player2 = player2;
        blocks = new Block[4];
        makeAllBlocks ();
    }


    private void makeAllBlocks ()
    {
        for (int i = 0; i < 4; i++)
            blocks[i] = new Block ();
    }


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


    public boolean chooseTaw (Coordinate taw)
    {
        String color;
        if (player1.isTurn ())
            color = player1.getTawColor ();
        else
            color = player2.getTawColor ();
        return blocks[taw.getBlockNumber () - 1].chooseTaw (taw,color);
    }

    public int checksForFinish ()
    {
        boolean playerOneWon = false;
        boolean playerTwoWon = false;
        for (int y = 0; y < 6; y++)
            for (int x = 0; x < 6; x++)
            {
                Coordinate coordinate = new Coordinate ();
                coordinate.translator (x,y);
                if (blocks[coordinate.getBlockNumber () - 1].
                        getTaws ()[coordinate.getY ()][coordinate.getX ()].
                        getColor ().equals (player1.getTawColor ()) && checkForATaw (coordinate))
                    playerOneWon = true;
                if (blocks[coordinate.getBlockNumber () - 1].
                        getTaws ()[coordinate.getY ()][coordinate.getX ()].
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

    private boolean checkForATaw (Coordinate taw)
    {
        int x = taw.getMainX ();
        int y = taw.getMainY ();
        String color = blocks[taw.getBlockNumber () - 1].
                getTaws ()[taw.getY ()][taw.getX ()].getColor ();
        Coordinate coordinate = new Coordinate ();
        if (x - 1 >= 0 && y - 1 >= 0)
        {
            coordinate.translator (x - 1,y - 1);
            if (blocks[coordinate.getBlockNumber ()  -1].
                    getTaws ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                if (checkInDir (color,taw,1) >= 5)
                    return true;
        }
        if ( y - 1 >= 0 )
        {
            coordinate.translator (x,y - 1);
            if (blocks[coordinate.getBlockNumber ()  -1].
                    getTaws ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                if (checkInDir (color,taw,2) >= 5)
                    return true;
        }
        if (y - 1 >= 0 && x + 1 < 6)
        {
            coordinate.translator (x + 1,y - 1);
            if (blocks[coordinate.getBlockNumber ()  -1].
                    getTaws ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                if (checkInDir (color,taw,3) >= 5)
                    return true;
        }
        if ( x - 1 >= 0)
        {
            coordinate.translator (x - 1,y);
            if (blocks[coordinate.getBlockNumber ()  -1].
                    getTaws ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                if (checkInDir (color,taw,4) >= 5)
                    return true;
        }
        if (x + 1 < 6)
        {
            coordinate.translator (x + 1,y);
            if (blocks[coordinate.getBlockNumber ()  -1].
                    getTaws ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                if (checkInDir (color,taw,5) >= 5)
                    return true;
        }
        if (y + 1 < 6 )
        {
            coordinate.translator (x,y + 1);
            if (blocks[coordinate.getBlockNumber ()  -1].
                    getTaws ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                if (checkInDir (color,taw,6) >= 5)
                    return true;
        }
        if (y + 1 < 6 )
        {
            coordinate.translator (x,y + 1);
            if (blocks[coordinate.getBlockNumber ()  -1].
                    getTaws ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                if (checkInDir (color,taw,7) >= 5)
                    return true;
        }
        if (y + 1 < 6 && x + 1 < 6 )
        {
            coordinate.translator (x + 1,y + 1);
            if (blocks[coordinate.getBlockNumber ()  -1].
                    getTaws ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color))
                return checkInDir (color, taw, 8) >= 5;
        }
        return false;
    }

    private int checkInDir (String color, Coordinate taw,int dir)
    {

        int x = taw.getMainX ();
        int y = taw.getMainY ();

        int counter = 0;
        while (x >= 0 && x <= 5 && y >= 0 && y <= 5)
        {
            Coordinate coordinate = new Coordinate ();
            coordinate.translator (x,y);
            if (!(blocks[coordinate.getBlockNumber () - 1].
                    getTaws ()[coordinate.getY ()][coordinate.getX ()]).getColor ().equals (color))
                return counter;
            else
                counter++;

            switch (dir)
            {
                case 1 : x--; y--;
                    break;
                case 2 : y--;
                    break;
                case 3 : x++; y--;
                    break;
                case 4 : x--;
                    break;
                case 5 : x++;
                    break;
                case 6 : x--; y++;
                    break;
                case 7 : y++;
                    break;
                case 8 : x++; y++;
                    break;
            }
        }
        return counter;
    }


    public void showMap (boolean showTurn)
    {
        System.out.println ("\n\n\n\n");
        for (int y = 0; y < 6; y++)
        {
            if (y == 3)
                System.out.println ("-----------------------------------------\n");
            for (int x = 0; x < 6; x++)
            {
                if (x == 3)
                    System.out.print (" |  ");
                Coordinate taw = new Coordinate ();
                if (taw.translator (x,y))
                    blocks[taw.getBlockNumber () - 1].
                            getTaws ()[taw.getY ()][taw.getX ()].printTaw ();
                System.out.print (" ");
            }
            System.out.println ("\n");
        }
        System.out.println ("\n");
        if (showTurn)
        {
            if (player1.isTurn ())
                player1.printPlayerColor ();
            else
                player2.printPlayerColor ();
        }
        else
            System.out.println ();

    }

}
