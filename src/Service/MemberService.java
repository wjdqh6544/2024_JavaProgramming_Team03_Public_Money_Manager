package Service;

import Entity.MemberList;
import Entity.abs_Member;
import Entity.Member;
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
    public void editBirthDay(abs_Member memberObj, int year, int month, int day){
        memberObj.getBirthday().setYear(year);
        memberObj.getBirthday().setMonth(month);
        memberObj.getBirthday().setDay(day);
    }
    public void editEmail(abs_Member memberObj, String email) throws DuplicatedEmailException {
        if (emailExists(email) == true){
            throw new DuplicatedEmailException();
        } else {
            memberObj.setEmail(email);
        }
    }
    public boolean emailExists(String email) throws EmptyMapException {
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
        } catch (EmptyMapException e) {
            return false;
        }

    }
    public void setAllMember(TreeMap<String, abs_Member> allMemberList) throws EmptyMapException, NullPointerException {
        if (allMemberList == null) {
            throw new NullPointerException();
        } else if (allMemberList.isEmpty() == true){
            throw new EmptyMapException();
        }
        memberList.setAllMemberList(allMemberList);
    }
    public TreeMap<String, abs_Member> findAllMember() throws MemberNotFoundException, NullPointerException {
        TreeMap<String, abs_Member> allMemberList = memberList.getAllMemberList();
        if (allMemberList == null) {
            throw new NullPointerException();
        } else if (allMemberList.isEmpty() == true){
            throw new EmptyMapException();
        }
        return allMemberList;
    }
    public abs_Member findMemberByNameAndEmail(String name, String email) throws MemberNotFoundException {
        TreeMap<String, abs_Member> allMemberList = memberList.getAllMemberList();
        Iterator<Map.Entry<String, abs_Member>> iter = allMemberList.entrySet().iterator();
        while (iter.hasNext() == true) {
            Map.Entry<String, abs_Member> obj = iter.next();
            if (obj.getValue().getName().equals(name) && obj.getValue().getEmail().equals(email)) {
                return obj.getValue();
            }
        }
        throw new MemberNotFoundException();
    }

    public void createMember(String name, int year, int month, int day, String email) throws DuplicatedEmailException {
        Member newMember;
        if (emailExists(email) == true){
            throw new DuplicatedEmailException();
        } else {
            newMember = new Member(name, year, month, day, email);
        }
        memberList.addMemberToList(newMember);
    }
}
