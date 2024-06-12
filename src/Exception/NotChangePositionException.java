package Exception;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * NotChangePositionException: Exception called when try changing member's position (from president to administrator).
 * @author: Seo, HyeongCheol
 */
public class NotChangePositionException extends RuntimeException {
    public NotChangePositionException(){
        super("Can't change the position of member, from President to Administrator");
    }
}