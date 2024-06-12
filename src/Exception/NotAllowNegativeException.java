package Exception;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * NotAllowNegativeException: Exception called when balance of group is negative.
 * @author: Seo, HyeongCheol
 */
public class NotAllowNegativeException extends RuntimeException {
    public NotAllowNegativeException(){
        super("Balance not allow negative.");
    }
}