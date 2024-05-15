package Entity;
import java.util.ArrayList;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * Group: class which can save some information of each Group object
 * @author: Seo, HyeongCheol
 */
public class Group implements cloneArrayList{
    private String groupName;
    private ArrayList<Member> groupMemberList;
    public Group() { // default constructor
        setGroupName("None");
        setMemberList(new ArrayList<Member>());
    }
    public Group(String name, Member... memberObjList) { // Constructor with parameter
        setGroupName(name);
        setMemberList(new ArrayList<Member>());
        for (Member memberObj : memberObjList) {
            addMember(memberObj);
        }
    }
    public Group(Group otherObj){ // copy constructor
        setGroupName(otherObj.getGroupName());
        setMemberList(otherObj.getMemberList()); // Copied ArrayList
    }
    public ArrayList<Member> cloneArrList(ArrayList<Member> original){
        ArrayList<Member> newArr = new ArrayList<Member>();
        for (Member obj : original) {
            newArr.add(obj);
        }
        return newArr;
    }
    public String getGroupName() { return this.groupName; }
    public ArrayList<Member> getMemberList() { return cloneArrList(this.groupMemberList); }
    private void setGroupName(String name) { this.groupName = name; }
    private void setMemberList(ArrayList<Member> groupMemberList) { this.groupMemberList = groupMemberList; }
    public void addMember(Member obj) { this.groupMemberList.add(obj); }
}
