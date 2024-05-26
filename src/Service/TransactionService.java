package Service;

import Entity.Transaction;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * TransactionServices: class which run service logic relative to Transaction Object.
 * @author: Seo, HyeongCheol
 */
public class TransactionService {
    private Transaction transaction;
    public int getExpensePerPeople(Transaction transObj) {
        return Math.round(transObj.getExpense() / transObj.getMemberList().size());
    }
}
