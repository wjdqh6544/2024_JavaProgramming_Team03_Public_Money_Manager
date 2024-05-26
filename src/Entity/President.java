package Entity;

import javax.naming.NoPermissionException;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * President: class which have methods that only can be used by President. (this class is inherited "Member" abstract class.)
 * @author: Seo, HyeongCheol
 */
public class President extends abs_Member {
    public President(){
        super();
    }
    public President(String name, int year, int month, int day, String email){
        super(name, year, month, day, email);
    }
    public President(abs_Member obj){
        super(obj);
    }

    @Override
    public void addMember() throws NoPermissionException {
    }

    @Override
    public void addTransaction() throws NoPermissionException {
        throw new NoPermissionException();
    }
}
