package Service;

import Entity.Group;
import Entity.GroupList;
import Entity.Member;
import Entity.President;
import Entity.abs_Member;
import Entity.Transaction;

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
    public void addTransactionHistory(int year, int month, int day, String event, int income, int expense, boolean settlement, abs_Member[] memberObjList){
        Transaction transObj = new Transaction(year, month, day, event, income, expense, settlement, memberObjList);
        group.addTransaction(transObj);
    }
    public void addMember(abs_Member obj){
        group.addMember(new Member(obj));
    }
    public boolean createGroup(String name, abs_Member presidentObj) {
        try {
            Group newGroup = new Group(name, new President(presidentObj));
            groupList.addGroupToList(newGroup);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    public ArrayList<Group> findAllGroup() { return groupList.getAllGroupList(); }
}
