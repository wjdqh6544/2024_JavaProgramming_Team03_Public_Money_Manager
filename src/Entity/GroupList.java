package Entity;
import java.util.ArrayList;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * GroupList: class which can save All Group
 * @author: Seo, HyeongCheol
 */
public class GroupList {
    private ArrayList<Group> allGroupList = null;
    public GroupList() { // default constructor
        setAllGroupList(new ArrayList<Group>());
    }
    public GroupList(ArrayList<Group> groupObjList) { // constructor with parameter
        setAllGroupList(groupObjList);
    }
    public GroupList(GroupList otherObj) { // copy constructor
        setAllGroupList(otherObj.getAllGroupList());
    }
    public ArrayList<Group> cloneArrList(ArrayList<Group> allGroupList) {
        ArrayList<Group> newArr = new ArrayList<Group>();
        for (Group groupObj : allGroupList) {
            newArr.add(new Group(groupObj));
        }
        return newArr;
    }
    public ArrayList<Group> getAllGroupList() { return this.allGroupList; }
    private void setAllGroupList(ArrayList<Group> allGroupList) { this.allGroupList = allGroupList; }
    public void addGroupToList(Group obj){
        this.allGroupList.add(obj);
    }
}
