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
    private ArrayList<Member> allMemberList;
    public MemberList() { // default constructor
        setAllMemberList(new ArrayList<Member>());
    }
    public MemberList(Member... MemberObjList) { // constructor with parameter
        setAllMemberList(new ArrayList<Member>());
        for (Member memberObj : MemberObjList) {
            addMemberToList(memberObj);
        }
    }
    public MemberList(MemberList originArrList) { // copy constructor
        setAllMemberList(originArrList.getAllMemberList()); // Copied ArrayList
    }
    @Override
    public ArrayList<Member> cloneArrList(ArrayList originalArrList) {
        ArrayList<Member> newArr = new ArrayList<Member>();
        for (Member obj : allMemberList) {
            newArr.add(obj);
        }
        return newArr;
    }
    public ArrayList<Member> getAllMemberList() { return cloneArrList(this.allMemberList); }
    private void setAllMemberList(ArrayList<Member> allMemberList) { this.allMemberList = allMemberList; }
    public void addMemberToList(Member obj) {
        // Setter 대신 Adder 추가. (리스트에 Member 추가)
        this.allMemberList.add(obj);
    }
}
