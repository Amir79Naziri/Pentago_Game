public class Taw
{
    private String color; // color of Taw



    public Taw ()
    {
        this.color = "white";
    }


    public String getColor () {
        return color;
    }


    public void setColor (String color)
    {
        this.color = color;
    }


    public void printTaw ()
    {
        if (getColor ().equals ("white"))
            System.out.print ("  " + '\u26AB' + "  ");
        if (getColor ().equals ("black"))
            System.out.print ("  " + '\u26AA' + "  ");
        if (getColor ().equals ("red"))
            System.out.print ("  " + "\u001B[31m" + '\u26AB' + "\u001B[0m" + "  ");

    }




}
