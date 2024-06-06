package Exception;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * ObjectSaveException: Exception called when failed to save Object (Member, Group ArrayList)
 * @author: Seo, HyeongCheol
 */
public class ObjectSaveException extends RuntimeException{
    public ObjectSaveException(String str, String detail){
        super("Failed to save all " + str + " list. | " + detail);
    }
}