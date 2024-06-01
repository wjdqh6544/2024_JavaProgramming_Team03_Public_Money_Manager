package Entity;

import java.util.TreeMap;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * GroupList: class which can save All Group
 * @author: Seo, HyeongCheol
 */
public class GroupList extends MapMethod {
    private TreeMap<String, Group> allGroupList = null;
    public GroupList() { // default constructor
        setAllGroupList(new TreeMap<String, Group>());
    }
    public GroupList(TreeMap<String, Group> groupObjList) { // constructor with parameter
        setAllGroupList(groupObjList);
    }
    public GroupList(GroupList otherObj) { // copy constructor
        setAllGroupList(otherObj.getAllGroupList());
    }
    public TreeMap<String, Group> getAllGroupList() { return this.allGroupList; }
    public void setAllGroupList(TreeMap<String, Group> allGroupList) { this.allGroupList = allGroupList; }
    public void addGroupToList(Group obj){
        this.allGroupList.put(getKey(obj), obj);
    }
}
