/**
 * A troll is a creature that collects tolls!
 * The trolls share all these tolls together in a shared Troll Toll Treasury.
 * This class represents the trolls, tolls, and treasury.
 * 
 * @author Grady McPeak 
 * @version One
 */
public class Troll
{
    static double total = 0;
    double money = 0;
    
    /**
     * Method for a troll collecting a monetary toll.
     * @param deposit - the money to be added to the troll's personal tally and the treasury
     */
    public void collect(double deposit){ // collect money
        money = money + deposit;
        total = total + deposit;
    }
    
    /**
     * Returns the amount of the money the troll has personally collected.
     * @return money - the amount of money in this specific troll's personal tally
     */
    public double myMoney(){ // returns this troll's money
        return money;
    }
    
    /**
     * Returns the total money collected by all instances of troll
     * @return total - the total amount of money in the treasury
     */
    public static double treasury() { // returns total money collected by all trolls
        return total;
    }
    
    /**
     * Empties the entire troll toll treasury.
     */
    public static void emptyTreasury() { // sets the total money collected by all trolls to 0
        total = 0;
    }
}
