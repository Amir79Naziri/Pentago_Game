import java.util.Scanner;

/**
 * this class is for reading from console
 *
 * @author Amir Naziri
 * @version 1.0
 */
public class Reader
{
    private Scanner line;


    /**
     * creates a new Reader instance
     */
    public Reader ()
    {
        line = new Scanner (System.in);
    }


    public Coordinate readFirstLine ()
    {
        System.out.println ("Enter Chosen Taw in format : x y");
        String firstLine = line.nextLine ();
        Coordinate coordinate = new Coordinate ();
        if (coordinate.translator (firstLine))
            return coordinate;
        else
            return readFirstLine ();
    }

    public String[] readSecLine ()
    {
        System.out.println ("Enter Chosen Block and it's direction in format : blockNumber cw/ccw");
        String secondLine = line.nextLine ();
        if (validSecLine (secondLine))
            return secondLine.trim ().split (" ");
        else
            return readSecLine ();
    }

    private boolean validSecLine (String secondLine)
    {
        String[] splits = secondLine.trim ().split (" ");
        if (splits.length != 2)
            return false;
        if (!(splits[0].equals ("1")) && !(splits[0].equals ("2")) && !(splits[0].equals ("3")) &&
                (!splits[0].equals ("4")))
            return false;
        return splits[1].equals ("cw") || splits[1].equals ("ccw");
    }


}

