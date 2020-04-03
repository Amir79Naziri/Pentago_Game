import java.util.HashSet;

public class GameHandler
{
    private Block[] blocks;

    public GameHandler ()
    {
        blocks = new Block[4];
        makeAllBlocks ();
    }


    private void makeAllBlocks ()
    {
        for (int i = 0; i < 4; i++)
            blocks[i] = new Block (i + 1);
    }


    public boolean act (Coordinate taw, int blockNumber, String dir, Player player1, Player player2)
    {
        String color;
        if (player1.isTurn ())
            color = player1.getTawColor ();
        else
            color = player2.getTawColor ();
        boolean result = chooseTaw (taw, color);
        if (!result)
            return false;
        rotate (blockNumber,dir);
        return true;
    }


    private void rotate (int blockNumber, String dir) // dir : ccw or cw
    {
        if (!(blocks[blockNumber - 1].isBlockEmpty ()))
        {
            if (dir.equals ("cw"))
                blocks[blockNumber - 1].clockWiseChange ();
            else
                blocks[blockNumber - 1].counterClockWiseChange ();
        }
    }


    private boolean chooseTaw (Coordinate taw, String color)
    {
        return blocks[taw.getBlockNumber () - 1].chooseTaw (taw,color);
    }

    public int checksForFinish (Player player1, Player player2)
    {
        HashSet<Coordinate> tawForPlayer1 = new HashSet<> ();
        HashSet<Coordinate> tawForPlayer2 = new HashSet<> ();

        for (int y = 0; y < 6; y++)
            for (int x = 0; x < 6; x++)
            {
                Coordinate taw = new Coordinate ();
                taw.translator (x, y);
                if (blocks[taw.getBlockNumber () - 1].getTaws ()[taw.getY ()][taw.getX ()].
                getColor ().equals (player1.getTawColor ()))
                    tawForPlayer1.add (taw);
                if (blocks[taw.getBlockNumber () - 1].getTaws ()[taw.getY ()][taw.getX ()].
                        getColor ().equals (player2.getTawColor ()))
                    tawForPlayer2.add (taw);
            }
        boolean resultForPlayer1 = checkForDistance (tawForPlayer1);
        boolean resultForPlayer2 = checkForDistance (tawForPlayer2);

        if (resultForPlayer1 && resultForPlayer2)
            return 3;
        if (resultForPlayer1)
            return 1;
        if (resultForPlayer2)
            return 2;
        return 0;
    }

    private boolean checkForDistance (HashSet<Coordinate> taw)
    {
        for (Coordinate point1 : taw)
            for (Coordinate point2 : taw)
            {
                if (isInCorrectDir (point1, point2) && point1.distance (point2) >= 5)
                    return true;
            }
        return false;
    }

    private boolean isInCorrectDir (Coordinate point1, Coordinate point2)
    {
        if (point1.getMainX () == point2.getMainX ())
            return true;
        if (point1.getMainY () == point2.getMainY ())
            return true;
        return Math.abs (point2.getMainY () - point1.getMainY ()) ==
                Math.abs (point2.getMainX ()) - point1.getMainX ();
    }

    public void showMap ()
    {
        for (int y = 0; y < 6; y++)
        {
            if (y == 3)
                System.out.println ("------------------------------------");
            for (int x = 0; x < 6; x++)
            {
                if (x == 3)
                    System.out.print (" | ");
                Coordinate taw = new Coordinate ();
                if (taw.translator (x,y))
                    blocks[taw.getBlockNumber () - 1].getTaws ()[taw.getY ()][taw.getX ()].printTaw ();
            }
            System.out.println ();
        }
    }
}
