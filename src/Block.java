/**
 * this class represents a block in game
 * which has 9 taw in it.
 *
 * @author Amir Naziri
 */
public class Block
{
    private Taw[][] taw;  // list of Taw in a block


    /**
     * creating a new Block
     */
    public Block ()
    {
        taw = new Taw[3][3];
        makeAllTaw ();
    }


    /**
     * make all Taw in block
     */
    private void  makeAllTaw ()
    {
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 3; x++)
                taw[y][x] = new Taw ();
    }

    /**
     * @return list of Taw
     */
    public Taw[][] getTaw () {
        return taw;
    }

    /**
     * choosing a Taw in Block
     * @param taw chosen Taw
     * @param color color for new Taw
     * @return was this choose successful or not
     */
    public boolean chooseTaw (Coordinate taw , String color)
    {
        if (!(validChosenTaw (taw)))
            return false;
        this.taw[taw.getY ()][taw.getX ()].setColor (color);
        return true;
    }

    /**
     * checks if choose valid ro not
     * @param taw chosen Taw
     * @return valid or not
     */
    private boolean validChosenTaw (Coordinate taw)
    {
        if (taw != null)
        {
            return this.taw[taw.getY ()][taw.getX ()].getColor ().equals ("white");
        }
        else
            return false;
    }

    /**
     * Is this block empty
     * @return result of Is this block empty
     */
    public boolean isBlockEmpty ()
    {
        for (int y = 0; y < 3; y++)
            for (int x = 0; x < 3; x++)
                if (!(taw[y][x].getColor ().equals ("white")))
                    return false;
        return true;
    }

    /**
     * rotate block clock Wise
     */
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

    /**
     * rotate block counter clock Wise
     */
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

    /**
     * transpose the matrix of Taw
     */
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
