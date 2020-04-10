import java.util.Scanner;

/**
 * this class is for reading input by user
 *
 * @author Amir Naziri
 */
public class Reader
{
    private Scanner line; // line


    /**
     * creates a new Reader instance
     */
    public Reader ()
    {
        line = new Scanner (System.in);
    }


    /**
     * read first command for choosing a Taw
     * @return translate of command to Coordinate
     */
    public Coordinate readFirstLine ()
    {
        System.out.println ("Enter Chosen Taw in format : x y");
        String firstLine = line.nextLine ();
        if (validLine (firstLine,1))
            return new Coordinate (firstLine);
        else
            return readFirstLine ();
    }

    /**
     * read second command for choosing a Block for rotate
     * @return translate of command to Coordinate
     */
    public String[] readSecLine ()
    {
        System.out.println ("Enter Chosen Block and it's direction in format : blockNumber cw/ccw");
        String secondLine = line.nextLine ();
        if (validLine (secondLine,2))
            return secondLine.trim ().split (" ");
        else
            return readSecLine ();
    }

    /**
     * checks if command is in valid format or not
     * @param inputLine input Line (command)
     * @param type type 1 is for first line and type 2 is for second line
     * @return valid or not
     */
    private boolean validLine (String inputLine, int type)
    {
        if (inputLine != null)
        {
            String[] splits = inputLine.trim ().split (" ");
            if (splits.length != 2)
                return false;

            if (type == 1)
            {
                if (!(splits[0].equals ("0")) && !(splits[0].equals ("1")) &&
                        !(splits[0].equals ("2")) && !splits[0].equals ("3") &&
                        !splits[0].equals ("4") && !splits[0].equals ("5"))
                    return false;
                return  splits[1].equals ("0") || splits[1].equals ("1") ||
                        splits[1].equals ("2") || splits[1].equals ("3") ||
                        splits[1].equals ("4") || splits[1].equals ("5");
            }
            else
            {
                if (!(splits[0].equals ("1")) && !(splits[0].equals ("2")) &&
                        !(splits[0].equals ("3")) && (!splits[0].equals ("4")))
                    return false;
                return splits[1].equals ("cw") || splits[1].equals ("ccw");
            }

        }
        else
            return false;
    }
}

