package Service;

import Entity.Transaction;
import Entity.abs_Member;

import java.util.ArrayList;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * TransactionServices: class which run service logic relative to Transaction Object.
 * @author: Seo, HyeongCheol
 */
public class TransactionService {
    private Transaction transaction;
    public void addFinishMember(abs_Member obj){
        transaction.addFinishMember(obj);
    }
    public ArrayList<abs_Member> getAllFinishMember() {
        return transaction.getFinishMember();
    }
    public ArrayList<abs_Member> getAllMember(){
        return transaction.getMemberList();
    }
    public int getExpensePerPeople(Transaction transObj) {
        return Math.round(transObj.getExpense() / transObj.getMemberList().size());
    }
}
