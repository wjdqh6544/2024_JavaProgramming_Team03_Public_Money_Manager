package Controller;
import Entity.*;
import Exception.*;
import Service.GroupService;

import javax.jws.soap.SOAPBinding;
import java.util.TreeMap;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * GroupController: class which is connected with Swing Component, and runs service logic relative to Group Object.
 * @author: Seo, HyeongCheol
 */
public class GroupController {
    private GroupService groupService = new GroupService();
    public GroupController(){}
    public void setAdministrator(Group groupObj, abs_Member memberObj){
        try {
            groupService.setGroupAdministrator(groupObj, memberObj);
        } catch (NotFoundException | NotChangePositionException e){
            throw e;
        }
    }
    public void removeAwaitMember(Group groupObj, abs_Member memberObj) {
        try {
            groupService.removeAwaitMember(groupObj, memberObj);
        } catch (NotFoundException e) {
            throw e;
        }
    }
    public void addMemberToGroup(Group groupObj, abs_Member memberObj) {
        try {
            groupService.removeAwaitMember(groupObj, memberObj);
            groupService.addMember(groupObj, memberObj);
        } catch (NotFoundException | DuplicatedException e){
            throw e;
        }
    }
    public void addAwaitMemberToGroup(Group groupObj, abs_Member memberObj){
        try {
            groupService.addAwaitMember(groupObj, memberObj);
        } catch (DuplicatedException e){
            throw e;
        }
    }
    public Group findGroupByName(String name){
        Group res = null;
        try {
            res = groupService.findGroupByName(name);
            return res;
        } catch (NotFoundException e) {
            throw e;
        }
    }
    public void createGroup(String name, abs_Member memberObj){
        try {
            groupService.createGroup(name, memberObj);
            memberObj.addGroup(new MemberPosition(name, PositionList.PRESIDENT));
        } catch (DuplicatedException e){
            throw e;
        }
    }
    public TreeMap<String, abs_Member> findAwaitMemberList(Group groupObj){
        TreeMap<String, abs_Member> awaitMemberList = null;
        try {
            awaitMemberList = groupService.findAwaitMemberList(groupObj);
            return awaitMemberList;
        } catch (NullPointerException e){
            throw e;
        }
    }
    public TreeMap<String, abs_Member> findAllGroupMemberList(Group groupObj) {
        TreeMap<String, abs_Member> allGroupMemberList = null;
        try {
            allGroupMemberList = groupService.findAllGroupMemberList(groupObj);
            return allGroupMemberList;
        } catch (NullPointerException e){
            throw e;
        }
    }
    public TreeMap<String, Group> findAllGroup(){
        TreeMap<String, Group> allGroupList = null;
        try {
            allGroupList = groupService.findAllGroup();
            return allGroupList;
        } catch (NullPointerException e){
            throw e;
        }
    }
    public void setAllGroupList(TreeMap<String, Group> allGroupList){
        try {
            groupService.setAllGroup(allGroupList);
        } catch (NullPointerException e){
            throw e;
        }
    }
}
