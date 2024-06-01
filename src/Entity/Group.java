package Entity;
import java.util.ArrayList;
import java.util.TreeMap;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * Group: class which can save some information of each Group object
 * @author: Seo, HyeongCheol
 */
public class Group extends MapMethod {
    private String groupName;
    private President president;
    private Administrator administrator;
    private TreeMap<String, abs_Member> MemberList;
    private TreeMap<String, abs_Member> awaitMemberList;
    private ArrayList<Transaction> transactionList;
    public Group() { // default constructor
        setGroupName("None");
        setPresident(null);
        setMemberList(new TreeMap<String, abs_Member>());
        setAwaitMemberList(new TreeMap<String, abs_Member>());
        setTransactionList(new ArrayList<Transaction>());
    }
    public Group(String name, President president) { // Constructor with parameter
        setGroupName(name);
        setPresident(president);
        setMemberList(new TreeMap<String, abs_Member>());
        setAwaitMemberList(new TreeMap<String, abs_Member>());
        setTransactionList(new ArrayList<Transaction>());
    }
    public Group(Group otherObj){ // copy constructor
        setGroupName(otherObj.getGroupName());
        setPresident(otherObj.getPresident());
        setMemberList(otherObj.getMemberList());
        setTransactionList(otherObj.getTransactionList());
    }
    public String getGroupName() { return this.groupName; }
    public President getPresident() {return this.president; }
    public Administrator getAdministrator() { return this.administrator; }
    public TreeMap<String, abs_Member> getMemberList() { return this.MemberList; }
    public TreeMap<String, abs_Member> getAwaitMemberList() { return this.awaitMemberList; }
    public ArrayList<Transaction> getTransactionList() { return this.transactionList; }
    public void setGroupName(String name) { this.groupName = name; }
    public void setPresident(President president) { this.president = president; }
    public void setAdministrator(Administrator administrator) { this.administrator = administrator; }
    public void setMemberList(TreeMap<String, abs_Member> groupMemberList) { this.MemberList = groupMemberList; }
    public void setAwaitMemberList(TreeMap<String, abs_Member> awaitMemberList) { this.awaitMemberList = awaitMemberList; }
    public void setTransactionList(ArrayList<Transaction> transactionList) { this.transactionList = transactionList; }
    public void addMember(abs_Member obj) { this.MemberList.put(getKey(obj), obj); }
    public void addTransaction(Transaction obj) { this.transactionList.add(obj); }
}
