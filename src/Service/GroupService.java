package Service;

import Entity.*;
import Exception.*;

import java.util.TreeMap;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * GroupServices: class which run service logic relative to Group Object.
 * @author: Seo, HyeongCheol
 */
public class GroupService {
    private Group group;
    private Transaction transaction;
    private GroupList groupList;
    public void setGroupAdministrator(abs_Member memberObj) {
        group.setAdministrator((Administrator) memberObj);
    }
    public void addTransactionHistory(int year, int month, int day, String event, int income, int expense, boolean settlement, TreeMap<String, abs_Member> memberObjList){
        Transaction transObj = new Transaction(year, month, day, event, income, expense, settlement, memberObjList);
        group.addTransaction(transObj);
    }
    public void addMember(abs_Member memberObj){
        group.addMember(memberObj);
    }
    public void createGroup(String name, abs_Member presidentObj) {
        Group newGroup = new Group(name, (President) presidentObj);
        if (groupList.getAllGroupList() == null){
            groupList = new GroupList();
        }
        groupList.addGroupToList(newGroup);
    }
    public TreeMap<String, Group> findAllGroup() throws NullPointerException, EmptyMapException {
        TreeMap<String, Group> allGroupList = groupList.getAllGroupList();
        if (allGroupList == null) {
            throw new NullPointerException();
        } else if (allGroupList.isEmpty() == true) {
            throw new EmptyMapException();
        }
        return allGroupList;
    }
    public void setAllGroup(TreeMap<String, Group> allGroupList) throws NullPointerException, EmptyMapException {
        if (allGroupList == null){
            throw new NullPointerException();
        } else if (allGroupList.isEmpty() == true){
            throw new EmptyMapException();
        }
        groupList.setAllGroupList(allGroupList);
    }
}
