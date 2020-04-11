/**
 * this class represents a Player
 *
 * @author Amir Naziri
 */
public class Player
{
    private boolean turn; // is turn
    private String tawColor; // taw color


    /**
     * creates a new Player
     */
    public Player ()
    {
        this.tawColor = "non-color";
        turn = false;
    }


    /**
     * sets a color for Player
     * @param color input Color
     */
    public void setTawColor (String color)
    {
        tawColor = color;
    }

    /**
     * @return isTurn of Player
     */
    public boolean isTurn () {
        return turn;
    }

    /**
     * @return taw color of a player
     */
    public String getTawColor () {
        return tawColor;
    }

    /**
     * done it's turn
     */
    public void doneTurn ()
    {
        this.turn = false;
    }

    /**
     * now it's turn
     */
    public void makeTurn ()
    {
        this.turn = true;
    }
}
