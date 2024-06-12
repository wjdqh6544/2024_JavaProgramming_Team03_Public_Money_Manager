package Entity;

import java.util.TreeMap;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * MemberList: class which can save All Member
 * @author: Seo, HyeongCheol
 */
public class MemberList extends MapMethod {
    private TreeMap<String, abs_Member> allMemberList = null;
    public MemberList() { // default constructor
        setAllMemberList(new TreeMap<String, abs_Member>());
    }
    public MemberList(TreeMap<String, abs_Member> MemberObjList) { // constructor with parameter
        setAllMemberList(MemberObjList);
    }
    public MemberList(MemberList originArrList) { // copy constructor
        setAllMemberList(originArrList.getAllMemberList()); // Copied ArrayList
    }
    public TreeMap<String, abs_Member> getAllMemberList() { return this.allMemberList; }
    public void setAllMemberList(TreeMap<String, abs_Member> allMemberList) { this.allMemberList = allMemberList; }
    public void addMemberToList(abs_Member obj) {
        this.allMemberList.put(getKey(obj), obj);
    }
}
