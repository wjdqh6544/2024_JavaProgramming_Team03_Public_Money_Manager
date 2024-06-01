package Controller;

import Entity.abs_Member;
import Service.MemberService;
import Exception.*;
import java.util.TreeMap;

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
    public void editMemberInfo(abs_Member memberObj, String email, int year, int month, int day){
        try {
            memberService.editEmail(memberObj, email);
            memberService.editBirthDay(memberObj, year, month, day);
        } catch (DuplicatedEmailException e){
            throw new DuplicatedEmailException();
        }
    }

    public abs_Member findMemberByNameAndEmail(String name, String email) throws MemberNotFoundException {
        abs_Member res = null;
        try {
            res = memberService.findMemberByNameAndEmail(name, email);
            return res;
        } catch (MemberNotFoundException e) {
            throw new MemberNotFoundException();
        }
    }
    public void createMember(String name, int year, int month, int day, String email){
        try {
            memberService.createMember(name, year, month, day, email);
        } catch (DuplicatedEmailException e) {
            throw new DuplicatedEmailException();
        }

    }
    public TreeMap<String, abs_Member> findAllMember() {
        TreeMap<String, abs_Member> allMemberList = null;
        try {
             allMemberList = memberService.findAllMember();
             return allMemberList;
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } catch (MemberNotFoundException e) {
            throw new MemberNotFoundException();
        }
    }

    public void setAllMemberList(TreeMap<String, abs_Member> allMemberList) {
        try {
            memberService.setAllMember(allMemberList);
        } catch (NullPointerException e) {
            throw new NullPointerException();
        } catch (EmptyMapException e) {
            throw new EmptyMapException();
        }
    }
}
