package Service;

import Entity.*;
import Exception.*;

import java.util.ArrayList;
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
    public void addTransactionHistory(int year, int month, int day, String event, int income, int expense, boolean settlement, ArrayList<abs_Member> memberObjList){
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
    public ArrayList<Group> findAllGroup() throws NullPointerException, EmptyArrayListException {
        ArrayList<Group> allGroupList = groupList.getAllGroupList();
        if (allGroupList == null) {
            throw new NullPointerException();
        } else if (allGroupList.isEmpty() == true) {
            throw new EmptyArrayListException();
        }
        return allGroupList;
    }
    public void setAllGroup(ArrayList<Group> allGroupList) throws NullPointerException, EmptyArrayListException {
        if (allGroupList == null){
            throw new NullPointerException();
        } else if (allGroupList.isEmpty() == true){
            throw new EmptyArrayListException();
        }
        groupList.setAllGroupList(allGroupList);
    }
}
