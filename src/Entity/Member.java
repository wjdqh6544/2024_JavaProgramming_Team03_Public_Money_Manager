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
    private PositionList position;
    private ArrayList<String> groupList;
    public Member(){ // default Constructor
        setName("None");
        setBirthday(new Date());
        setEmail("None");
        setPosition(PositionList.NONE);
        setGroupList(new ArrayList<String>());
    }
    public Member(String name, int year, int month, int day, String email, PositionList position, String... inputGroupList) { // Constructor using Information
        setName(name);
        setBirthday(new Date(year, month, day));
        setEmail(email);
        setPosition(position);
        setGroupList(new ArrayList<String>());
        for (String group : inputGroupList) {
            addGroup(group);
        }
    }
    public Member(Member otherObj){ // copy constructor
        setName(otherObj.getName());
        setBirthday(otherObj.getBirthday()); // Copied Date Obj
        setEmail(otherObj.getEmail());
        setPosition(otherObj.getPosition());
        setGroupList(otherObj.getGroupList()); // Copied ArrayList
    }
    public ArrayList<String> cloneArrList(ArrayList<String> originalArrList) { // ArrayList Deep Copy
        ArrayList<String> newArr = new ArrayList<String>();
        for (String obj : originalArrList) {
            newArr.add(obj);
        }
        return newArr;
    }
    public String getName(){ return this.name; }
    public Date getBirthday() {return new Date(this.birthday); }
    public String getEmail() { return this.email; }
    public PositionList getPosition() { return this.position; }
    public ArrayList<String> getGroupList() { return cloneArrList(groupList); }
    private void setName(String name) { this.name = name; }
    private void setBirthday(Date birthday) { this.birthday = new Date(birthday); }
    private void setEmail(String email) { this.email = email; }
    private void setPosition(PositionList position) { this.position = position; }
    private void setGroupList (ArrayList<String> groupList) { this.groupList = groupList; }
    public void addGroup(String groupName) { this.groupList.add(groupName); }
}
