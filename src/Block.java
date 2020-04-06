public class Block
{
    private Taw[][] taw;


    public Block ()
    {
        taw = new Taw[3][3];
        makeAllTaw ();
    }

    private void  makeAllTaw ()
    {
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 3; x++)
                taw[y][x] = new Taw ();
    }



    public Taw[][] getTaws () {
        return taw;
    }


    public boolean chooseTaw (Coordinate taw , String color)
    {
        if (!(validChosenTaw (taw)))
            return false;
        this.taw[taw.getY ()][taw.getX ()].setColor (color);
        return true;
    }

    private boolean validChosenTaw (Coordinate taw)
    {
        return this.taw[taw.getY ()][taw.getX ()].getColor ().equals ("white");
    }

    public boolean isBlockEmpty ()
    {
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 3; x++)
                if (!(taw[y][x].getColor ().equals ("white")))
                    return false;
        return true;
    }

    public void clockWiseChange ()
    {
        transposedColor ();
        for (int i = 0; i < 3; i++)
        {
            String color = taw[i][0].getColor ();
            taw[i][0].setColor (taw[i][2].getColor ());
            taw[i][2].setColor (color);
        }
    }

    public void counterClockWiseChange ()
    {
        transposedColor ();
        for (int i = 0; i < 3; i++)
        {
            String color = taw[0][i].getColor ();
            taw[0][i].setColor (taw[2][i].getColor ());
            taw[2][i].setColor (color);
        }
    }

    private void transposedColor ()
    {
        for (int i = 0; i < 3; i++)
            for (int j = i; j < 3; j++)
            {
                String color = taw[i][j].getColor ();
                taw[i][j].setColor (taw[j][i].getColor ());
                taw[j][i].setColor (color);
            }
    }
}
