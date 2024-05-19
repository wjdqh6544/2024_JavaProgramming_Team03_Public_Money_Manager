package Entity;
import java.util.ArrayList;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * Transaction: class which can save information of each transaction history
 * @author: Seo, HyeongCheol
 */
public class Transaction implements cloneArrayList {
    private Date date;
    private String event;
    private ArrayList<Member> memberList;
    private int income;
    private int expense;
    private boolean settlement;
    public Transaction(){ // default constructor
        setDate(new Date());
        setEvent("None");
        setIncome(0);
        setExpense(0);
        setSettlement(false);
        setMemberList(new ArrayList<Member>());
    }
    public Transaction(int year, int month, int day, String event, int income, int expense, boolean settlement, Member[] memberObjList) { // constructor with parameter
        setDate(new Date(year, month, day));
        setEvent(event);
        setIncome(income);
        setExpense(expense);
        setSettlement(settlement);
        setMemberList(new ArrayList<Member>());
        for (Member memberObj : memberObjList) {
            addMember(new Member(memberObj));
        }
    }
    public Transaction(Transaction otherObj) { // copy constructor
        setDate(otherObj.getDate());
        setEvent(otherObj.getEvent());
        setIncome(otherObj.getIncome());
        setExpense(otherObj.getExpense());
        setSettlement(otherObj.isSettlement());
        setMemberList(otherObj.getMemberList()); // Copied ArrayList
    }
    @Override
    public ArrayList<Member> cloneArrList(ArrayList<Member> original){
        ArrayList<Member> newArr = new ArrayList<Member>();
        for (Member obj : original) {
            newArr.add(new Member(obj));
        }
        return newArr;
    }
    public Date getDate() { return new Date(this.date); }
    public String getEvent() { return this.event; }
    public ArrayList<Member> getMemberList() { return cloneArrList(this.memberList); }
    public int getIncome() { return this.income; }
    public int getExpense() { return expense; }
    public boolean isSettlement() { return settlement; }
    private void setDate(Date date) { this.date = new Date(date); }
    private void setEvent(String event) { this.event = event; }
    private void setMemberList(ArrayList<Member> memberList) { this.memberList = memberList; }
    private void setIncome(int income) { this.income = income; }
    private void setExpense(int expense) { this.expense = expense; }
    private void setSettlement(boolean settlement) { this.settlement = settlement; }
    public void addMember(Member obj) { this.memberList.add(obj); }
}
