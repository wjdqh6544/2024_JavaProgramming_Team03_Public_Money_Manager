package Exception;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * DuplicatedEmailException: Exception called when input email duplicated with other object's.
 * @author: Seo, HyeongCheol
 */
public class DuplicatedException extends RuntimeException{
    public DuplicatedException(String target){
        super(target + " is duplicated with other object's.");
    }
}