package Service;

import Entity.Administrator;
import Entity.MemberList;
import Entity.President;
import Entity.abs_Member;
import Entity.Member;

import java.util.ArrayList;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * MemberServices: class which run service logic relative to Member Object.
 * @author: Seo, HyeongCheol
 */
public class MemberService {
    private MemberList memberList = null;
    private abs_Member member;
    public ArrayList<abs_Member> findAllMember(){
        return memberList.getAllMemberList();
    }
    public abs_Member findMemberByNameAndEmail(String name, String email) {
        ArrayList<abs_Member> allMemberList = memberList.getAllMemberList();
        for (abs_Member obj : allMemberList) {
            if (obj.getName().equals(name) && obj.getEmail().equals(email)) {
                if (obj instanceof Administrator){
                    return (Administrator) obj;
                } else if (obj instanceof President){
                    return (President) obj;
                } else {
                    return (Member) obj;
                }

            }
        }
        return null;
    }
    public void createMember(String name, int year, int month, int day, String email) {
        Member newMember = new Member(name, year, month, day, email);
        if (memberList.getAllMemberList() == null){
            memberList = new MemberList();
        }
        memberList.addMemberToList(newMember);
    }
}
