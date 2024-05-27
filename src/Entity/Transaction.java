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
    private ArrayList<abs_Member> memberList;
    private ArrayList<abs_Member> finishMember;
    private int income;
    private int expense;
    private boolean settlement;
    public Transaction(){ // default constructor
        setDate(new Date());
        setEvent("None");
        setIncome(0);
        setExpense(0);
        setSettlement(false);
        setMemberList(new ArrayList<abs_Member>());
        setFinishMember(new ArrayList<abs_Member>());
    }
    public Transaction(int year, int month, int day, String event, int income, int expense, boolean settlement, ArrayList<abs_Member> memberObjList) { // constructor with parameter
        setDate(new Date(year, month, day));
        setEvent(event);
        setIncome(income);
        setExpense(expense);
        setSettlement(settlement);
        setMemberList(memberObjList);
        setFinishMember(new ArrayList<abs_Member>());
    }
    public Transaction(Transaction otherObj) { // copy constructor
        setDate(otherObj.getDate());
        setEvent(otherObj.getEvent());
        setIncome(otherObj.getIncome());
        setExpense(otherObj.getExpense());
        setSettlement(otherObj.isSettlement());
        setMemberList(otherObj.getMemberList());
        setFinishMember(otherObj.getFinishMember());
    }
    @Override
    public ArrayList<abs_Member> cloneArrList(ArrayList<abs_Member> original){
        ArrayList<abs_Member> newArr = new ArrayList<abs_Member>();
        for (abs_Member memberObj : original) {
            if (memberObj instanceof Administrator){
                addMember(new Administrator(memberObj));
            } else if (memberObj instanceof President){
                addMember(new President(memberObj));
            } else {
                addMember((new Member(memberObj)));
            }
        }
        return newArr;
    }
    public Date getDate() { return this.date; }
    public String getEvent() { return this.event; }
    public ArrayList<abs_Member> getMemberList() { return this.memberList; }
    public ArrayList<abs_Member> getFinishMember() { return this.finishMember; }
    public int getIncome() { return this.income; }
    public int getExpense() { return expense; }
    public boolean isSettlement() { return settlement; }
    public void setDate(Date date) { this.date = date; }
    public void setEvent(String event) { this.event = event; }
    public void setMemberList(ArrayList<abs_Member> memberList) { this.memberList = memberList; }
    public void setFinishMember(ArrayList<abs_Member> finishMember) { this.finishMember = finishMember; }
    public void setIncome(int income) { this.income = income; }
    public void setExpense(int expense) { this.expense = expense; }
    public void setSettlement(boolean settlement) { this.settlement = settlement; }
    public void addMember(abs_Member obj) { this.memberList.add(obj); }
    public void addFinishMember(abs_Member obj) { this.finishMember.add(obj); }
}
