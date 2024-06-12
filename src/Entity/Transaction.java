package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * Transaction: class which can save information of each transaction history
 * @author: Seo, HyeongCheol
 */
public class Transaction extends MapMethod implements Serializable {
    private Date date;
    private String event;
    private TreeMap<String, abs_Member> memberList;
    private TreeMap<String, abs_Member> finishMember;
    private int income;
    private int expense;
    private boolean settlement;
    public Transaction(){ // default constructor
        setDate(new Date());
        setEvent("None");
        setIncome(0);
        setExpense(0);
        setSettlement(false);
        setMemberList(new TreeMap<String, abs_Member>());
        setFinishMember(new TreeMap<String, abs_Member>());
    }
    public Transaction(int year, int month, int day, String event, int income, int expense, ArrayList<abs_Member> memberObjList) { // constructor with parameter
        setDate(new Date(year, month, day));
        setEvent(event);
        setIncome(income);
        setExpense(expense);
        setSettlement(false);
        setMemberList(new TreeMap<String, abs_Member>());
        for (abs_Member obj : memberObjList){
            addMember(obj);
        }
        setFinishMember(new TreeMap<String, abs_Member>());
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
    public String toString(){
        return "Date: " + getDate() + " | Event: " + getEvent() + " | Income: " + getIncome() + " | Expense: " + getExpense() +
                " | Settlement: " + isSettlement() + "\n\t MemberList: " + getMemberList() + "\n\t FinishMember: " + getFinishMember() + "\n";
    }
    public Date getDate() { return this.date; }
    public String getEvent() { return this.event; }
    public TreeMap<String, abs_Member> getMemberList() { return this.memberList; }
    public TreeMap<String, abs_Member> getFinishMember() { return this.finishMember; }
    public int getIncome() { return this.income; }
    public int getExpense() { return expense; }
    public boolean isSettlement() { return settlement; }
    public void setDate(Date date) { this.date = date; }
    public void setEvent(String event) { this.event = event; }
    public void setMemberList(TreeMap<String, abs_Member> memberList) { this.memberList = memberList; }
    public void setFinishMember(TreeMap<String, abs_Member> finishMember) { this.finishMember = finishMember; }
    public void setIncome(int income) { this.income = income; }
    public void setExpense(int expense) { this.expense = expense; }
    public void setSettlement(boolean settlement) { this.settlement = settlement; }
    public void addMember(abs_Member obj) { this.memberList.put(getKey(obj), obj); }
    public void addFinishMember(abs_Member obj) { this.finishMember.put(getKey(obj), obj); }
    public void removeMember(abs_Member obj) { this.memberList.remove(getKey(obj)); }
}
