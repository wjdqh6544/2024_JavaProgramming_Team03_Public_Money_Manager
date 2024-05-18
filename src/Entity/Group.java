package Entity;
import java.util.ArrayList;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * Group: class which can save some information of each Group object
 * @author: Seo, HyeongCheol
 */
public class Group implements cloneArrayList{
    private String groupName;
    private ArrayList<Member> groupMemberList;
    private ArrayList<Transaction> transactionList;
    public Group() { // default constructor
        setGroupName("None");
        setMemberList(new ArrayList<Member>());
        setTransactionList(new ArrayList<Transaction>());
    }
    public Group(String name, Member... memberObjList) { // Constructor with parameter
        setGroupName(name);
        setMemberList(new ArrayList<Member>());
        for (Member memberObj : memberObjList) {
            addMember(new Member(memberObj));
        }
        setTransactionList(new ArrayList<Transaction>());
    }
    public Group(Group otherObj){ // copy constructor
        setGroupName(otherObj.getGroupName());
        setMemberList(otherObj.getMemberList()); // Copied ArrayList
        setTransactionList(otherObj.getTransactionList()); // Copied ArrayList
    }
    public ArrayList<Member> cloneArrList(ArrayList<Member> original){
        ArrayList<Member> newArr = new ArrayList<Member>();
        for (Member obj : original) {
            newArr.add(new Member(obj));
        }
        return newArr;
    }
    public ArrayList<Transaction> cloneTransList(ArrayList<Transaction> original){
        ArrayList<Transaction> newArr = new ArrayList<Transaction>();
        for (Transaction transaction : original) {
            newArr.add(new Transaction(transaction));
        }
        return newArr;
    }
    public String getGroupName() { return this.groupName; }
    public ArrayList<Member> getMemberList() { return cloneArrList(this.groupMemberList); }

    public ArrayList<Transaction> getTransactionList() { return cloneTransList(this.transactionList); }

    private void setGroupName(String name) { this.groupName = name; }
    private void setMemberList(ArrayList<Member> groupMemberList) { this.groupMemberList = groupMemberList; }
    private void setTransactionList(ArrayList<Transaction> transactionList) { this.transactionList = transactionList; }
    public void addMember(Member obj) { this.groupMemberList.add(obj); }
    public void addTransaction(Transaction obj) { this.transactionList.add(obj); }
}
