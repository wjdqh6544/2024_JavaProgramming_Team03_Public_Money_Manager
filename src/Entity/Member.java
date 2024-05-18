package Entity;
import java.util.ArrayList;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * Member: class which can save some information of each member object
 * @author: Seo, HyeongCheol
 */
public class Member {
    private String name;
    private Date birthday;
    private String email;
    private ArrayList<MemberPosition> groupList;
    public Member(){ // default Constructor
        setName("None");
        setBirthday(new Date());
        setEmail("None");
        setGroupList(new ArrayList<MemberPosition>());
    }
    public Member(String name, int year, int month, int day, String email, MemberPosition... inputGroupList) { // Constructor with Parameters
        setName(name);
        setBirthday(new Date(year, month, day));
        setEmail(email);
        setGroupList(new ArrayList<MemberPosition>());
        for (MemberPosition group : inputGroupList) {
            addGroup(group);
        }
    }
    public Member(Member otherObj){ // copy constructor
        setName(otherObj.getName());
        setBirthday(otherObj.getBirthday()); // Copied Date Obj
        setEmail(otherObj.getEmail());
        setGroupList(otherObj.getGroupList()); // Copied ArrayList
    }
    public ArrayList<MemberPosition> cloneArrList(ArrayList<MemberPosition> originalArrList) { // ArrayList Deep Copy
        ArrayList<MemberPosition> newArr = new ArrayList<MemberPosition>();
        for (MemberPosition obj : originalArrList) {
            newArr.add(obj);
        }
        return newArr;
    }
    public String getName(){ return this.name; }
    public Date getBirthday() {return new Date(this.birthday); }
    public String getEmail() { return this.email; }
    public ArrayList<MemberPosition> getGroupList() { return cloneArrList(groupList); }
    private void setName(String name) { this.name = name; }
    private void setBirthday(Date birthday) { this.birthday = new Date(birthday); }
    private void setEmail(String email) { this.email = email; }
    private void setGroupList (ArrayList<MemberPosition> groupList) { this.groupList = groupList; }
    public void addGroup(MemberPosition groupName) { this.groupList.add(groupName); }
}
