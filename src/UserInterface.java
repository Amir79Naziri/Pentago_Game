public class UserInterface
{


    public void showMap (boolean showTurn, Block[] blocks, Player player1, Player player2 )
    {
        System.out.println ("\n\n\n\n");
        for (int y = 0; y < 6; y++)
        {

            for (int x = 0; x < 6; x++)
            {
                if (x == 3)
                    System.out.print ("| ");
                Coordinate taw = new Coordinate (x,y);
                printTaw (blocks[taw.getBlockNumber () - 1].
                        getTaw ()[taw.getY ()][taw.getX ()]);
                System.out.print (" ");
            }
            if (y == 2)
                System.out.println ("\n----------------------------------------");
            else
                System.out.println ("\n");
        }
        System.out.println ("\n");
        if (showTurn)
        {
            if (player1.isTurn ())
                printTawForTurn (player1.getTawColor (),"turn");
            else
                printTawForTurn (player2.getTawColor (),"turn");
        }
        else
            System.out.println ();
        System.out.println ();
    }


    private void printTaw (Taw taw)
    {
        if (taw == null)
            return;
        if (taw.getColor ().equals ("white"))
            System.out.print ("  " + '\u26AB' + "  ");
        if (taw.getColor ().equals ("black"))
            System.out.print ("  " + '\u26AA' + "  ");
        if (taw.getColor ().equals ("red"))
            System.out.print ("  " + "\u001B[31m" + '\u26AB' + "\u001B[0m" + "  ");
    }

    public void printTawForTurn (String color, String what)
    {
        if (color == null)
            return;
        if (color.equals ("black"))
            System.out.print ("  " + '\u26AA' + " " + what +"\n");
        if (color.equals ("red"))
            System.out.print ("  " + "\u001B[31m" + '\u26AB' + "\u001B[0m" + " " + what +"\n");
    }
}
