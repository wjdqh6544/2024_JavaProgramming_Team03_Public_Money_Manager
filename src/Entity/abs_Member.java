package Entity;

import Exception.NoPermissionException;
import java.io.Serializable;
import java.util.TreeMap;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * abs_Member: abstract class which can save some information of each member object
 * @author: Seo, HyeongCheol
 */
public abstract class abs_Member extends MapMethod implements Serializable {
    private String name;
    private Date birthday;
    private String email;
    private TreeMap<String, MemberPosition> groupList;

    public abs_Member(){
        setName("None");
        setBirthday(new Date());
        setEmail("None");
        setGroupList(new TreeMap<String, MemberPosition>());
    }
    public abs_Member(String name, int year, int month, int day, String email) { // Constructor with Parameters
        setName(name);
        setBirthday(new Date(year, month, day));
        setEmail(email);
        setGroupList(new TreeMap<String, MemberPosition>());
    }
    public abs_Member(abs_Member otherObj){ // copy constructor
        setName(otherObj.getName());
        setBirthday(otherObj.getBirthday());
        setEmail(otherObj.getEmail());
        setGroupList(otherObj.getGroupList());
    }

    public String getName() { return this.name; }
    public Date getBirthday() { return this.birthday;}
    public String getEmail() { return this.email; }
    public TreeMap<String, MemberPosition> getGroupList() { return this.groupList; }
    public void setName(String name) { this.name = name; }
    public void setBirthday(Date obj) { this.birthday = obj; }
    public void setEmail(String email) { this.email = email; }
    public void setGroupList(TreeMap<String, MemberPosition> groupList) { this.groupList = groupList; }
    public void addGroup(MemberPosition obj) { this.groupList.put(getKey(obj), obj); }
    public abstract void addMember() throws NoPermissionException;
    public abstract void addTransaction() throws NoPermissionException;
    @Override
    public String toString() {
        return "\nName: " + getName() +
                "\nDate: " + getBirthday() +
                "\nEmail: " + getEmail() +
                "\nGroup: " + getGroupList();
    }
}
