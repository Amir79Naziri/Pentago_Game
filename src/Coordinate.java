import java.util.Objects;

/**
 * this class represent coordinates for taw
 *
 * @author Amir Naziri
 */
public class Coordinate
{
    private int x; // x of coordinate
    private int y; // y of coordinate
    private int blockNumber; // block number


    /**
     * get x, y of block in total range and change it to range of block and make a new Object
     * @param x x in board
     * @param y y in board
     */
    public Coordinate (int x, int y)
    {
        translator (x, y);
    }


    /**
     * change a String command to x ,y and make a new object
     * @param command command
     */
    public Coordinate (String command)
    {
        translator (command);
    }


    /**
     * @return x of Taw in block
     */
    public int getX () {
        return x;
    }


    /**
     * @return y of Taw on block
     */
    public int getY () {
        return y;
    }


    /**
     * @return Block Number
     */
    public int getBlockNumber () {
        return blockNumber;
    }

    /**
     * @return x of Taw in total board
     */
    public int getMainX ()
    {
        switch (getBlockNumber ())
        {
            case 1 :
            case 3 :
                return x;
            case 2 :
            case 4 :
                return x + 3;
            default: return -1;
        }
    }

    /**
     * @return y of Taw in total board
     */
    public int getMainY ()
    {
        switch (getBlockNumber ())
        {
            case 1 :
            case 2 :
                return y;
            case 3 :
            case 4 :
                return y + 3;
            default: return -1;
        }
    }

    /**
     * sets Coordinate
     * @param x x in block
     * @param y y in block
     * @param blockNumber block number
     */
    public void setCoordinate (int x, int y,int blockNumber)
    {
        this.x = x;
        this.y = y;
        this.blockNumber = blockNumber;
    }

    /**
     * translate x , y in board to x , y , block number in block
     * @param x x in board
     * @param y y in board
     */
    public void translator (int x, int y)
    {
        if (x < 3 && y < 3)
            setCoordinate (x, y, 1);
        if (x >= 3 && y < 3)
            setCoordinate (x - 3, y, 2);
        if (x < 3 && y >= 3)
            setCoordinate (x, y - 3, 3);
        if (x >= 3 && y >= 3)
            setCoordinate (x - 3, y - 3 ,4);
    }

    /**
     * translate command x , y , block number in block
     * @param command command
     */
    public void translator (String command)
    {
        if (command != null)
        {
            String[] splits = command.trim ().split (" ");
            int x = Integer.parseInt (splits[0]);
            int y = Integer.parseInt (splits[1]);
            translator (x,y);
        }
    }

    /**
     * checks the equality in coordinate
     * @param o input object
     * @return result
     */
    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y && getBlockNumber () == that.getBlockNumber ();
    }

    /**
     * @return hash code for coordinate
     */
    @Override
    public int hashCode () {
        return Objects.hash (x, y,getBlockNumber ());
    }
}
