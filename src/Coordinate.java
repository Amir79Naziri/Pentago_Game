import java.util.Objects;

public class Coordinate
{
    private int x;
    private int y;
    private int blockNumber;



    public Coordinate (int x, int y)
    {
        translator (x, y);
    }


    public Coordinate (String command)
    {
        translator (command);
    }



    public int getX () {
        return x;
    }


    public int getY () {
        return y;
    }


    public int getBlockNumber () {
        return blockNumber;
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

    public void setCoordinate (int x, int y,int blockNumber)
    {
        this.x = x;
        this.y = y;
        this.blockNumber = blockNumber;
    }

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

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y && getBlockNumber () == that.getBlockNumber ();
    }

    @Override
    public int hashCode () {
        return Objects.hash (x, y,getBlockNumber ());
    }
}
