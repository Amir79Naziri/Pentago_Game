import java.util.Objects;

/**
 * this class is for storing a x and y of a command by changing a command from "I C" to x, y
 *
 * @author Amir Naziri
 * @version 1.0
 */
public class Coordinate
{
    private int x; // X of Coordinate
    private int y; // Y of Coordinate
    private int blockNumber;
    /**
     * creates a Coordinate instance with x = -1, y == -1, block number = 0
     */
    public Coordinate ()
    {
        x = -1;
        y = -1;
        blockNumber = 0;
    }


    /**
     * creates a Coordinate instance with input x, y
     * @param x X of Coordinate
     * @param y Y of Coordinate
     */
    public Coordinate (int x, int y, int blockNumber)
    {
        this.x = x;
        this.y = y;
        this.blockNumber = blockNumber;
    }

    /**
     * sets new value for x, y
     * @param x X of Coordinate
     * @param y Y of Coordinate
     */
    public void setCoordinate (int x, int y,int blockNumber)
    {
        this.x = x;
        this.y = y;
        this.blockNumber = blockNumber;
    }


    /**
     * Translates a command into x, y
     * @param command input command
     * @return if it was successful return true  OW false
     */
    public boolean translator (String command)
    {
        if (!validCommand (command))
            return false;
        String[] splits = command.trim ().split (" ");
        int x = Integer.parseInt (splits[0]);
        int y = Integer.parseInt (splits[2]);

        return translator (x,y);
    }

    public boolean translator (int x, int y)
    {
        if (x < 3 && y < 3)
            setCoordinate (x, y, 1);
        if (x >= 3 && y < 3)
            setCoordinate (x - 3, y, 2);
        if (x < 3 && y >= 3)
            setCoordinate (x, y - 3, 3);
        if (x >= 3 && y >= 3)
            setCoordinate (x - 3, y - 3 ,4);
        return true;
    }
    /**
     * Translate a x ,y into command
     * @return Command
     */
    public String toString ()
    {
        if (x == -1 || y == -1)
            return null;

        return getMainX () + " "  + getMainY ();
    }

    /**
     * is input command valid or not ?
     * @param command input command
     * @return if valid returns true   else return false
     */
    private boolean validCommand (String command)
    {
        if (command != null) {

            char[] parts = command.trim ().toCharArray ();
            if (parts.length != 3)
                return false;
            if (parts[0] != '0' &&parts[0] != '1' && parts[0] != '2' && parts[0] != '3'
                    && parts[0] != '4' && parts[0] != '5')
                return false;
            if (parts[1] != ' ')
                return false;
            return parts[2] == '0' || parts[2] == '1' || parts[2] == '2' || parts[2] == '3'
                    || parts[2] == '4' || parts[2] == '5';
        }
        else return false;
    }

    /**
     * @return X of Coordination
     */
    public int getX () {
        return x;
    }

    /**
     * @return Y of Coordination
     */
    public int getY () {
        return y;
    }


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

    public int distance (Coordinate that)
    {
        int sum = 0;
        int x = Math.abs (this.getMainX () - that.getMainX ());
        int y = Math.abs (this.getMainY () - that.getMainX ());

        if (x == 0)
            sum += y;
        else
            sum += x;
        return sum + 1;
    }


    public int getBlockNumber () {
        return blockNumber;
    }


    /**
     * checks equality of two Coordination by checking x and y
     * @param o input object
     * @return result of equality
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
     * @return hashCode for Coordination
     */
    @Override
    public int hashCode () {
        return Objects.hash (x, y,getBlockNumber ());
    }
}
