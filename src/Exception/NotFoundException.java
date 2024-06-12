package Exception;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * MemberNotFoundException: Exception called when try to find Member which not exists.
 * @author: Seo, HyeongCheol
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String target) {
        super(target + " does not exists.");
    }
}