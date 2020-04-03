public class Block
{
    private Taw[][] taws;
    private int blockNumber;


    public Block (int blockNumber)
    {
        taws = new Taw[3][3];
        this.blockNumber = blockNumber;
        makeAllTaw ();
    }

    private void  makeAllTaw ()
    {
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 3; x++)
                taws[y][x] = new Taw ();
    }


    public int getBlockNumber () {
        return blockNumber;
    }

    public Taw[][] getTaws () {
        return taws;
    }


    public boolean chooseTaw (Coordinate taw , String color)
    {
        if (!(validChosenTaw (taw)))
            return false;
        taws[taw.getY ()][taw.getX ()].setColor (color);
        return true;
    }

    private boolean validChosenTaw (Coordinate taw)
    {
        return taws[taw.getY ()][taw.getX ()].getColor ().equals ("white");
    }

    public boolean isBlockEmpty ()
    {
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 3; x++)
                if (!(taws[y][x].getColor ().equals ("white")))
                    return false;
        return true;
    }

    public void clockWiseChange ()
    {
        transposedColor ();
        for (int i = 0; i < 3; i++)
        {
            String color = taws[i][0].getColor ();
            taws[i][0].setColor (taws[i][2].getColor ());
            taws[i][2].setColor (color);
        }
    }

    public void counterClockWiseChange ()
    {
        transposedColor ();
        for (int i = 0; i < 3; i++)
        {
            String color = taws[0][i].getColor ();
            taws[0][i].setColor (taws[2][i].getColor ());
            taws[2][i].setColor (color);
        }
    }

    private void transposedColor ()
    {
        for (int i = 0; i < 3; i++)
            for (int j = i; j < 3; j++)
            {
                String color = taws[i][j].getColor ();
                taws[i][j].setColor (taws[j][i].getColor ());
                taws[j][i].setColor (color);
            }
    }
}
