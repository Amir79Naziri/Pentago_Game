import java.util.HashSet;

public class GameHandler
{
    Player player1;
    Player player2;
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
            blocks[i] = new Block (i + 1);
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
        player1.setPoints (tawForPlayer1.size ());
        player2.setPoints (tawForPlayer2.size ());
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
        if (taw.size () < 5)
            return false;
        Coordinate[] tawList = new Coordinate[taw.size ()];
        int sum = 0;

        for (Coordinate point : taw)
        {
            tawList[sum] = point;
            sum++;
        }

        for (int i = 0; i < sum; i++)
            for (int j = i + 1 ; j < sum; j++)
            {
                int dir = dir (tawList[i],tawList[j]);
                if (dir != 0 && checkInDir (tawList[i],tawList[j],dir))
                    return true;
            }
        return false;
    }

    private int dir (Coordinate point1, Coordinate point2)
    {
        if (point1.getMainX () == point2.getMainX ())
        {
            if (point1.getMainY () < point2.getMainY ())
                return 7;
            else
                return 2;
        }
        if (point1.getMainY () == point2.getMainY ())
        {
            if (point1.getMainX () < point2.getMainX ())
                return 5;
            else
                return 4;
        }

        if (point1.getMainX () < point2.getMainX ())
        {
            if (point1.getMainY () < point2.getMainY ())
                return 8;
            else
                return 3;
        }
        if (point1.getMainX () > point2.getMainX ())
        {
            if (point1.getMainY () < point2.getMainY ())
                return 6;
            else
                return 1;
        }
        return 0;
    }

    private boolean checkInDir (Coordinate point1, Coordinate point2,int dir)
    {
        int x = point1.getMainX ();
        int y = point1.getMainY ();
        String color = blocks[point1.getBlockNumber () - 1].
                getTaws ()[point1.getY ()][point1.getX ()].getColor ();

        while (x != point2.getMainX () && y != point2.getMainY ())
        {
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

            Coordinate coordinate = new Coordinate ();
            coordinate.translator (x,y);
            if (!(blocks[coordinate.getBlockNumber () - 1].
                    getTaws ()[coordinate.getY ()][coordinate.getX ()].getColor ().equals (color)))
                return false;
        }
        return true;
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
