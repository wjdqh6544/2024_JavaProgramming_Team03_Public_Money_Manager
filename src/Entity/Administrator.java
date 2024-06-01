package Entity;

import Exception.NoPermissionException;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * Administrator: class which have methods that only can be used by Administrator. (this class is inherited "Member" abstract class.)
 * @author: Seo, HyeongCheol
 */
public class Administrator extends abs_Member {
    public Administrator(){
        super();
    }
    public Administrator(String name, int year, int month, int day, String email){
        super(name, year, month, day, email);
    }
    public Administrator(abs_Member obj){
        super(obj);
    }

    @Override
    public boolean canAddMember(String groupName) throws NoPermissionException {
        throw new NoPermissionException();
    }

    @Override
    public boolean canAddTransaction(String groupName) throws NoPermissionException {
        MemberPosition obj = getGroupList().get(groupName);
        if (obj == null) {
            throw new NullPointerException();
        } else if (obj.getPosition().equals(PositionList.ADMINISTRATOR)) {
                return true;
        } else {
            throw new NoPermissionException();
        }
    }
}

