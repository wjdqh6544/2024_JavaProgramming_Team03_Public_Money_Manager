package Controller;
import Entity.*;
import Exception.*;
import Service.GroupService;

import java.util.ArrayList;
import java.util.TreeMap;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * GroupController: class which is connected with Swing Component, and runs service logic relative to Group Object.
 * @author: Seo, HyeongCheol
 */
public class GroupController {
    private final GroupService groupService = new GroupService();
    public GroupController(){}
    public ArrayList<Transaction> findAllTransaction(Group groupObj) throws NullPointerException {
        try {
            return groupService.findAllTransaction(groupObj);
        } catch (NullPointerException e){
            throw e;
        }
    }
    public void addTransactionHistory(Group groupObj, int year, int month, int day, String event, int income, int expense, ArrayList<abs_Member> memberObjList) throws NotFoundException {
        try {
            groupService.addTransactionHistory(groupObj, year, month, day, event, income, expense, memberObjList);
        } catch (NotFoundException e){
            throw e;
        }
    }
    public void setAdministrator(Group groupObj, abs_Member memberObj) throws NotFoundException, NotChangePositionException {
        try {
            groupService.setGroupAdministrator(groupObj, memberObj);
        } catch (NotFoundException | NotChangePositionException e){
            throw e;
        }
    }
    public void removeAwaitMember(Group groupObj, abs_Member memberObj) throws NotFoundException {
        try {
            groupService.removeAwaitMember(groupObj, memberObj);
        } catch (NotFoundException e) {
            throw e;
        }
    }
    public void addMemberToGroup(Group groupObj, abs_Member memberObj) throws NotFoundException, DuplicatedException {
        try {
            groupService.removeAwaitMember(groupObj, memberObj);
            groupService.addMember(groupObj, memberObj);
        } catch (NotFoundException | DuplicatedException e){
            throw e;
        }
    }
    public void addAwaitMemberToGroup(Group groupObj, abs_Member memberObj) throws DuplicatedException {
        try {
            groupService.addAwaitMember(groupObj, memberObj);
        } catch (DuplicatedException e){
            throw e;
        }
    }
    public Group findGroupByName(String name) throws NotFoundException {
        Group res = null;
        try {
            res = groupService.findGroupByName(name);
            return res;
        } catch (NotFoundException e) {
            throw e;
        }
    }
    public void createGroup(String name, abs_Member memberObj) throws DuplicatedException {
        try {
            groupService.createGroup(name, memberObj);
            memberObj.addGroup(new MemberPosition(name, PositionList.PRESIDENT));
        } catch (DuplicatedException e){
            throw e;
        }
    }
    public TreeMap<String, abs_Member> findAwaitMemberList(Group groupObj) throws NullPointerException {
        TreeMap<String, abs_Member> awaitMemberList = null;
        try {
            awaitMemberList = groupService.findAwaitMemberList(groupObj);
            return awaitMemberList;
        } catch (NullPointerException e){
            throw e;
        }
    }
    public TreeMap<String, abs_Member> findAllGroupMemberList(Group groupObj) throws NullPointerException {
        TreeMap<String, abs_Member> allGroupMemberList = null;
        try {
            allGroupMemberList = groupService.findAllGroupMemberList(groupObj);
            return allGroupMemberList;
        } catch (NullPointerException e){
            throw e;
        }
    }
    public TreeMap<String, Group> findAllGroup() throws NullPointerException {
        TreeMap<String, Group> allGroupList = null;
        try {
            allGroupList = groupService.findAllGroup();
            return allGroupList;
        } catch (NullPointerException e){
            throw e;
        }
    }
    public void setAllGroupList(TreeMap<String, Group> allGroupList) throws NullPointerException {
        try {
            groupService.setAllGroup(allGroupList);
        } catch (NullPointerException e){
            throw e;
        }
    }
}
