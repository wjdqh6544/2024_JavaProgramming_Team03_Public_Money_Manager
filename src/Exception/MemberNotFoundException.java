package Exception;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * MemberNotFoundException: Exception called when try to find Member which not exists.
 * @author: Seo, HyeongCheol
 */
public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException() {
        super("Member does not exists.");
    }

}
