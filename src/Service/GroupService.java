package Service;
import Entity.*;
import Exception.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * GroupServices: class which run service logic relative to Group Object.
 * @author: Seo, HyeongCheol
 */
public class GroupService {
    private MemberService memberService = new MemberService();
    private GroupList groupList = new GroupList();
    public GroupService(){}
    public ArrayList<Transaction> findAllTransaction(Group groupObj) throws NullPointerException {
        try {
            return groupObj.getTransactionList();
        } catch (NullPointerException e){
            throw e;
        }
    }
    public Group findGroupByName(String name) throws NullPointerException, NotFoundException {
        try {
            TreeMap<String, Group> allGroupList = groupList.getAllGroupList();
            Iterator<Map.Entry<String, Group>> iter = allGroupList.entrySet().iterator();
            while (iter.hasNext() == true) {
                Group obj = iter.next().getValue();
                if (obj.getGroupName().equals(name) == true){
                    return obj;
                }
            }
            throw new NotFoundException("Group");
        } catch (NullPointerException e){
            throw e;
        }
    }
    public boolean groupExist(String groupName) throws NullPointerException {
        try {
            TreeMap<String, Group> allGroupList = findAllGroup();
            Iterator<Map.Entry<String, Group>> iter = allGroupList.entrySet().iterator();
            while (iter.hasNext() == true){
                if (iter.next().getKey().equals(groupName)) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e){
            return false;
        }
    }
    public void setGroupAdministrator(Group groupObj, abs_Member memberObj) throws NotFoundException, NotChangePositionException {
        try {
            if (groupObj.getPresident().equals(memberObj) == true){
                throw new NotChangePositionException();
            }
            memberService.removeGroup(memberObj, groupObj);
            memberService.addGroup(memberObj, new MemberPosition(groupObj.getGroupName(), PositionList.ADMINISTRATOR));
            groupObj.setAdministrator(new Administrator(memberObj));
            TreeMap<String, Group> allGroupList = findAllGroup();
            Iterator<Map.Entry<String, Group>> iter = allGroupList.entrySet().iterator();
            while (iter.hasNext() == true) {
                Group obj = iter.next().getValue();
                obj.removeMember(memberObj);
                obj.addMember(memberObj);
            }
        } catch (NotFoundException e) {
            throw e;
        }

    }
    public void addTransactionHistory(Group groupObj, int year, int month, int day, String event, int income, int expense, ArrayList<abs_Member> memberObjList) throws NotFoundException {
        if (memberObjList.isEmpty() == true){
            throw new NotFoundException("Member subject to settlement");
        } else if (event.replaceAll(" ", "").isEmpty() == true) {
            throw new NotFoundException("TransAction event content");
        }
        Transaction transObj = new Transaction(year, month, day, event, income, expense, memberObjList);
        groupObj.addTransaction(transObj);
    }
    public void removeAwaitMember(Group groupObj, abs_Member memberObj) throws NotFoundException {
        TreeMap<String, abs_Member> awaitMemberList = groupObj.getAwaitMemberList();
        Iterator<Map.Entry<String, abs_Member>> iter = awaitMemberList.entrySet().iterator();
        while (iter.hasNext() == true) {
            abs_Member obj = iter.next().getValue();
            if (obj.equals(memberObj) == true) {
                groupObj.removeAwaitMember(memberObj);
                return;
            }
        }
        throw new NotFoundException("Member");
    }
    public void addAwaitMember(Group groupObj, abs_Member memberObj) throws DuplicatedException {
        TreeMap<String, abs_Member> awaitMemberList = groupObj.getAwaitMemberList();
        Iterator<Map.Entry<String, abs_Member>> iter = awaitMemberList.entrySet().iterator();
        while (iter.hasNext() == true){
            abs_Member obj = iter.next().getValue();
            if (obj.equals(memberObj) == true){
                throw new DuplicatedException("Member in memberList");
            }
        }
        groupObj.addAwaitMember(memberObj);
    }
    public void addMember(Group groupObj, abs_Member memberObj) throws DuplicatedException {
        TreeMap<String, abs_Member> memberList = groupObj.getMemberList();
        Iterator<Map.Entry<String, abs_Member>> iter = memberList.entrySet().iterator();
        while (iter.hasNext() == true){
            abs_Member obj = iter.next().getValue();
            if (obj.equals(memberObj) == true){
                throw new DuplicatedException("Member in awaitMemberList");
            }
        }
        groupObj.addMember(memberObj);
    }
    public void createGroup(String name, abs_Member presidentObj) throws DuplicatedException {
        if (groupExist(name) == true) {
            throw new DuplicatedException("Group Name");
        } else {
            Group newGroup = new Group(name, new President(presidentObj));
            newGroup.addMember(presidentObj);
            memberService.addGroup(presidentObj, new MemberPosition(name, PositionList.PRESIDENT));
            TreeMap<String, Group> list = null;
            try {
                list = findAllGroup();
            } catch (NullPointerException e){
                list = new TreeMap<String, Group>();
                groupList.setAllGroupList(list);
            } finally {
                groupList.addGroupToList(newGroup);
            }
        }
    }
    public TreeMap<String, abs_Member> findAwaitMemberList(Group groupObj) throws NullPointerException {
        TreeMap<String, abs_Member> awaitMemberList = groupObj.getAwaitMemberList();
        if (awaitMemberList == null) {
            throw new NullPointerException();
        }
        return awaitMemberList;
    }
    public TreeMap<String, abs_Member> findAllGroupMemberList(Group groupObj) throws NullPointerException {
        TreeMap<String, abs_Member> allGroupMemberList = groupObj.getMemberList();
        if (allGroupMemberList == null) {
            throw new NullPointerException();
        }
        return allGroupMemberList;
    }
    public TreeMap<String, Group> findAllGroup() throws NullPointerException {
        TreeMap<String, Group> allGroupList = groupList.getAllGroupList();
        if (allGroupList == null) {
            throw new NullPointerException();
        }
        return allGroupList;
    }
    public void setAllGroup(TreeMap<String, Group> allGroupList) throws NullPointerException {
        if (allGroupList == null){
            throw new NullPointerException();
        }
        groupList.setAllGroupList(allGroupList);
    }
}
