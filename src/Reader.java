import java.util.Scanner;

public class Reader
{
    private Scanner line;



    public Reader ()
    {
        line = new Scanner (System.in);
    }



    public Coordinate readFirstLine ()
    {
        System.out.println ("Enter Chosen Taw in format : x y");
        String firstLine = line.nextLine ();
        if (validLine (firstLine,1))
            return new Coordinate (firstLine);
        else
            return readFirstLine ();
    }

    public String[] readSecLine ()
    {
        System.out.println ("Enter Chosen Block and it's direction in format : blockNumber cw/ccw");
        String secondLine = line.nextLine ();
        if (validLine (secondLine,2))
            return secondLine.trim ().split (" ");
        else
            return readSecLine ();
    }

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

