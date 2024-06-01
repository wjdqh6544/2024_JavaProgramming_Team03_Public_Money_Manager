package Service;

import Entity.*;
import Exception.*;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * MemberServices: class which run service logic relative to Member Object.
 * @author: Seo, HyeongCheol
 */
public class MemberService {
    private MemberList memberList = new MemberList();
    private abs_Member member;
    public MemberService(){}
    public void removeGroup(abs_Member memberObj, Group groupObj) throws NotFoundException {
        TreeMap<String, MemberPosition> posList = memberObj.getGroupList();
        Iterator<Map.Entry<String, MemberPosition>> iter = posList.entrySet().iterator();
        while (iter.hasNext() == true) {
            if (iter.next().getValue().getGroupName().equals(groupObj.getGroupName()) == true){
                memberObj.removeGroup(groupObj);
                return;
            }
        }
        throw new NotFoundException("Group");
    }
    public void addGroup(abs_Member memberObj, MemberPosition pos) throws DuplicatedException {
        TreeMap<String, MemberPosition> posList = memberObj.getGroupList();
        Iterator<Map.Entry<String, MemberPosition>> iter = posList.entrySet().iterator();
        while (iter.hasNext() == true){
            if (iter.next().getValue().getGroupName().equals(pos.getGroupName()) == true){
                throw new DuplicatedException("Group");
            }
        }
        memberObj.addGroup(pos);
    }
    public void editBirthDay(abs_Member memberObj, int year, int month, int day){
        memberObj.getBirthday().setYear(year);
        memberObj.getBirthday().setMonth(month);
        memberObj.getBirthday().setDay(day);
    }
    public void editEmail(abs_Member memberObj, String email) throws DuplicatedException {
        if (emailExists(email) == true){
            throw new DuplicatedException("Email");
        } else {
            memberObj.setEmail(email);
        }
    }
    public boolean emailExists(String email) throws NullPointerException {
        try {
            TreeMap<String, abs_Member> allMemberList = findAllMember();
            Iterator<Map.Entry<String, abs_Member>> iter = allMemberList.entrySet().iterator();
            while (iter.hasNext() == true) {
                Map.Entry<String, abs_Member> obj = iter.next();
                if (obj.getValue().getEmail().equals(email)) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e){
            throw e;
        }
    }
    public void setAllMember(TreeMap<String, abs_Member> allMemberList) throws NullPointerException {
        if (allMemberList == null) {
            throw new NullPointerException();
        }
        memberList.setAllMemberList(allMemberList);
    }
    public TreeMap<String, abs_Member> findAllMember() throws NullPointerException {
        TreeMap<String, abs_Member> allMemberList = null;
        try {
            allMemberList = memberList.getAllMemberList();
        } catch (NullPointerException e) {
            memberList = new MemberList();
            allMemberList = memberList.getAllMemberList();
        }
        return allMemberList;
    }
    public abs_Member findMemberByNameAndEmail(String name, String email) throws NotFoundException {
        TreeMap<String, abs_Member> allMemberList = memberList.getAllMemberList();
        Iterator<Map.Entry<String, abs_Member>> iter = allMemberList.entrySet().iterator();
        while (iter.hasNext() == true) {
            Map.Entry<String, abs_Member> obj = iter.next();
            if (obj.getValue().getName().equals(name) && obj.getValue().getEmail().equals(email)) {
                return obj.getValue();
            }
        }
        throw new NotFoundException("Member");
    }

    public void createMember(String name, int year, int month, int day, String email) throws DuplicatedException {
        abs_Member newMember;
        if (emailExists(email) == true){
            throw new DuplicatedException("Email");
        } else {
            newMember = new Member(name, year, month, day, email);
        }
        memberList.addMemberToList(newMember);
    }
}
