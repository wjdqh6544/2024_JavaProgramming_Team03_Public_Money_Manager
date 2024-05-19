package Service;

import Entity.Member;
import Entity.MemberList;

import java.util.ArrayList;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * MemberServices: class which run service logic relative to Member Object.
 * @author: Seo, HyeongCheol
 */
public class MemberService {
    private MemberList memberList;
    private Member member;
    public ArrayList<Member> findAllMember(){
        return memberList.getAllMemberList();
    }
    public Member findMemberByNameAndEmail(String name, String email) {
        ArrayList<Member> allMemberList = memberList.getAllMemberList();
        for (Member obj : allMemberList) {
            if (obj.getName().equals(name) && obj.getEmail().equals(email)) {
                return new Member(obj);
            }
        }
        return null;
    }
    public boolean createMember(String name, int year, int month, int day, String email) {
        try {
            Member newMember = new Member(name, year, month, day, email);
            memberList.addMemberToList(newMember);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
