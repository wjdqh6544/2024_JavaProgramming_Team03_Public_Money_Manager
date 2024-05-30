package Service;

import Entity.Date;
import Entity.MemberList;
import Entity.abs_Member;
import Entity.Member;
import Exception.*;

import java.util.ArrayList;
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
    public boolean emailExists(String email){
        try {
            ArrayList<abs_Member> allMemberList = findAllMember();
            for (abs_Member obj : allMemberList) {
                if (obj.getEmail().equals(email)) {
                    return true;
                }
            }
            return false;
        } catch (EmptyArrayListException e) {
            return false;
        }

    }
    public void setAllMember(ArrayList<abs_Member> allMemberList) {
        if (allMemberList == null) {
            throw new NullPointerException();
        } else if (allMemberList.isEmpty() == true){
            throw new EmptyArrayListException();
        }
        memberList.setAllMemberList(allMemberList);
    }
    public ArrayList<abs_Member> findAllMember() throws MemberNotFoundException {
        ArrayList<abs_Member> allMemberList = memberList.getAllMemberList();
        if (allMemberList == null) {
            throw new NullPointerException();
        } else if (allMemberList.isEmpty() == true){
            throw new EmptyArrayListException();
        }
        return allMemberList;
    }
    public abs_Member findMemberByNameAndEmail(String name, String email) throws MemberNotFoundException {
        ArrayList<abs_Member> allMemberList = memberList.getAllMemberList();
        for (abs_Member obj : allMemberList) {
            if (obj.getName().equals(name) && obj.getEmail().equals(email)) {
                return obj;
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
