package Entity;
import java.util.ArrayList;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * MemberList: class which can save All Member
 * @author: Seo, HyeongCheol
 */
public class MemberList implements cloneArrayList{
    private ArrayList<abs_Member> allMemberList;
    public MemberList() { // default constructor
        setAllMemberList(new ArrayList<abs_Member>());
    }
    public MemberList(ArrayList<abs_Member> MemberObjList) { // constructor with parameter
        setAllMemberList(MemberObjList);
    }
    public MemberList(MemberList originArrList) { // copy constructor
        setAllMemberList(originArrList.getAllMemberList()); // Copied ArrayList
    }
    @Override
    public ArrayList<abs_Member> cloneArrList(ArrayList originalArrList) {
        ArrayList<abs_Member> newArr = new ArrayList<abs_Member>();
        for (abs_Member obj : allMemberList) {
            if (obj instanceof Administrator){
                addMemberToList(new Administrator(obj));
            } else if (obj instanceof President){
                addMemberToList(new President(obj));
            } else {
                addMemberToList((new Member(obj)));
            }
        }
        return newArr;
    }
    public ArrayList<abs_Member> getAllMemberList() { return this.allMemberList; }
    private void setAllMemberList(ArrayList<abs_Member> allMemberList) { this.allMemberList = allMemberList; }
    public void addMemberToList(abs_Member obj) {
        this.allMemberList.add(obj);
    }
}
