package Controller;

import Entity.abs_Member;
import Service.MemberService;
import Exception.*;

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
        abs_Member res = null;
        try {
            res = memberService.findMemberByNameAndEmail(name, email);
        } catch (MemberNotFoundException e) {
            throw new MemberNotFoundException();
        } finally {
            return res;
        }
    }
    public void createMember(String name, int year, int month, int day, String email){
        try {
            memberService.createMember(name, year, month, day, email);
        } catch (DuplicatedEmailException e) {
            throw new DuplicatedEmailException();
        }

    }
    public ArrayList<abs_Member> findAllMember(){
        ArrayList<abs_Member> allMemberList = null;
        try {
             allMemberList = memberService.findAllMember();
        } catch (MemberNotFoundException e) {
            throw new MemberNotFoundException();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } finally {
            return allMemberList;
        }
    }

    public void setAllMemberList(ArrayList<abs_Member> allMemberList){
        try {
            memberService.setAllMember(allMemberList);
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } catch (EmptyArrayListException e) {
            throw new EmptyArrayListException();
        }
    }
}
