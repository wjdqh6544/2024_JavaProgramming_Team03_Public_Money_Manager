package Service;

import Entity.Transaction;
import Entity.abs_Member;
import Exception.*;

import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * TransactionServices: class which run service logic relative to Transaction Object.
 * @author: Seo, HyeongCheol
 */
public class TransactionService {
    public void checkAllMemberFinish(Transaction transObj) throws NullPointerException {
        try {
            if (findAllNotFinishMember(transObj).isEmpty() == true) {
                transObj.setSettlement(true);
            }
        } catch (NullPointerException e){
            throw e;
        }
    }
    public int findExpensePerPerson(Transaction transObj) throws NullPointerException, NotFoundException {
        try {
            if ((findAllFinishMember(transObj).size() + findAllNotFinishMember(transObj).size()) == 0) {
                throw new NotFoundException("Member subject to settlement");
            }
            return (int) Math.ceil((double)transObj.getExpense() / (findAllFinishMember(transObj).size() + findAllNotFinishMember(transObj).size()));
        } catch (NullPointerException e) {
            throw e;
        }
    }
    public void removeMember(Transaction transObj, abs_Member memberObj) throws NotFoundException {
        TreeMap<String, abs_Member> memberList = findAllNotFinishMember(transObj);
        Iterator<Map.Entry<String, abs_Member>> iter = memberList.entrySet().iterator();
        while (iter.hasNext() == true){
            if (iter.next().getValue().equals(memberObj) == true){
                transObj.removeMember(memberObj);
                return;
            }
        }
        throw new NotFoundException("Member");
    }
    public void addMember(Transaction transObj, abs_Member memberObj) throws DuplicatedException {
        TreeMap<String, abs_Member> memberList = findAllNotFinishMember(transObj);
        Iterator<Map.Entry<String, abs_Member>> iter = memberList.entrySet().iterator();
        while (iter.hasNext() == true) {
            if (iter.next().getValue().equals(memberObj) == true){
                throw new DuplicatedException("Member in memberList of transaction");
            }
        }
        transObj.addMember(memberObj);
    }
    public void addFinishMember(Transaction transObj, abs_Member memberObj) throws DuplicatedException {
        TreeMap<String, abs_Member> finishMemberList = findAllFinishMember(transObj);
        Iterator<Map.Entry<String, abs_Member>> iter = finishMemberList.entrySet().iterator();
        while (iter.hasNext() == true) {
            if (iter.next().getValue().equals(memberObj) == true){
                throw new DuplicatedException("Member in FinishMemberList of transaction");
            }
        }
        transObj.addFinishMember(memberObj);
    }
    public TreeMap<String, abs_Member> findAllFinishMember(Transaction transObj) throws NullPointerException {
        TreeMap<String, abs_Member> finishMemberList = transObj.getFinishMember();
        if (finishMemberList == null) {
            throw new NullPointerException();
        }
        return finishMemberList;
    }
    public TreeMap<String, abs_Member> findAllNotFinishMember(Transaction transObj) throws NullPointerException {
        TreeMap<String, abs_Member> memberList = transObj.getMemberList();
        if (memberList == null){
            throw new NullPointerException();
        }
        return memberList;
    }
}
