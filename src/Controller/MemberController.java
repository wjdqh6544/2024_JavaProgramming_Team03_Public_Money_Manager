package Controller;

import Entity.abs_Member;
import Service.MemberService;
import Exception.MemberNotFoundException;

import java.util.ArrayList;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * MemberController: class which is connected with Swing Component, and runs service logic relative to Member Object.
 * @author: Seo, HyeongCheol
 */

public class MemberController {
    private MemberService memberService = new MemberService();
    public MemberController(){
    }
    public abs_Member findMemberByNameAndEmail(String name, String email){
        return memberService.findMemberByNameAndEmail(name, email);
    }
    public void createMember(String name, int year, int month, int day, String email){
        memberService.createMember(name, year, month, day, email);
    }
    public ArrayList<abs_Member> findAllMember(){
        ArrayList<abs_Member> allMemberList = null;
        try {
             allMemberList = memberService.findAllMember();
        } catch (MemberNotFoundException e) {
            throw new MemberNotFoundException();
        }
        return allMemberList;
    }

    public void setAllMemberList(ArrayList<abs_Member> allMemberList){
        memberService.setAllMember(allMemberList);
    }
}
