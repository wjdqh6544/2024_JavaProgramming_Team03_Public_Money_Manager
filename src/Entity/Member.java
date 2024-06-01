package Entity;

import Exception.NoPermissionException;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * Member: class which can save some information of each member object
 * @author: Seo, HyeongCheol
 */
public class Member extends abs_Member {
    public Member(){
        super();
    }
    public Member(String name, int year, int month, int day, String email) { // Constructor with Parameters
        super(name, year, month, day, email);
    }
    public Member(abs_Member obj){
        super(obj);
    }

    @Override
    public boolean canAddMember(String groupName) throws NoPermissionException {
        throw new NoPermissionException();
    }

    @Override
    public boolean canAddTransaction(String groupName) throws NoPermissionException {
        throw new NoPermissionException();
    }
}
