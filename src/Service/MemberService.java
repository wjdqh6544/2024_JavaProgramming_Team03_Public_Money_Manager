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
    private MemberList memberList;
    private abs_Member member;
    public ArrayList<abs_Member> findAllMember(){
        return memberList.getAllMemberList();
    }
    public abs_Member findMemberByNameAndEmail(String name, String email) {
        ArrayList<abs_Member> allMemberList = memberList.getAllMemberList();
        for (abs_Member obj : allMemberList) {
            if (obj.getName().equals(name) && obj.getEmail().equals(email)) {
                if (obj instanceof Administrator){
                    return new Administrator(obj);
                } else if (obj instanceof President){
                    return new President(obj);
                } else {
                    return new Member(obj);
                }

            }
        }
        return null;
    }
    public boolean createMember(String name, int year, int month, int day, String email) {
        try {
            abs_Member newMember = new Member(name, year, month, day, email);
            memberList.addMemberToList(newMember);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
