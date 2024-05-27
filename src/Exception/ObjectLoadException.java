package Exception;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * ObjectLoadException: Exception called when failed to Load Object (Member, Group ArrayList)
 * @author: Seo, HyeongCheol
 */
public class ObjectLoadException extends RuntimeException {
    public ObjectLoadException(String str){
        super("Failed to load all " + str + " list.");
    }
}
