package Controller;

import Entity.Group;
import Entity.Transaction;
import Entity.abs_Member;
import Service.GroupService;
import Service.TransactionService;
import Exception.*;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * TransactionController: class which is connected with Swing Component, and runs service logic relative to Transaction Object.
 * @author: Seo, HyeongCheol
 */
public class TransactionController {
    private final TransactionService transactionService = new TransactionService();
    private final GroupService groupService = new GroupService();
    public TransactionController(){}
    public int findExpensePerPerson(Transaction transObj){
        try {
            return transactionService.findExpensePerPerson(transObj);
        } catch (NotFoundException | NullPointerException e){
            throw e;
        }
    }
    public void manualAddMember(Group groupObj, Transaction transObj, abs_Member memberObj){
        try {
            TreeMap<String, abs_Member> groupMemberList = groupService.findAllGroupMemberList(groupObj);
            Iterator<Map.Entry<String, abs_Member>> iter = groupMemberList.entrySet().iterator();
            while (iter.hasNext() == true) {
                if (iter.next().getValue().equals(memberObj) == true){
                    transactionService.addMember(transObj, memberObj);
                    return;
                }
            }
            throw new NotFoundException("Member");
        } catch (DuplicatedException e){
            throw e;
        }
    }
    public void changeMemberToFinish(Transaction transObj, abs_Member memberObj){
        try {
            transactionService.removeMember(transObj, memberObj);
            transactionService.addFinishMember(transObj, memberObj);
            transactionService.checkAllMemberFinish(transObj);
        } catch (DuplicatedException e) {
            throw e;
        }
    }
    public TreeMap<String, abs_Member> findFinishMember(Transaction transObj){
        try {
            TreeMap<String, abs_Member> finishMemberList = transactionService.findAllFinishMember(transObj);
            return finishMemberList;
        } catch (NullPointerException e){
            throw e;
        }
    }
    public TreeMap<String, abs_Member> findNotFinishMember(Transaction transObj){
        try {
            TreeMap<String, abs_Member> notFinishMemberList = transactionService.findAllNotFinishMember(transObj);
            return notFinishMemberList;
        } catch (NullPointerException e){
            throw e;
        }
    }
    public TreeMap<String, abs_Member> findAllMember(Transaction transObj){
        try {
            TreeMap<String, abs_Member> resAllMember = transactionService.findAllNotFinishMember(transObj);
            resAllMember.putAll(transactionService.findAllFinishMember(transObj));
            return resAllMember;
        } catch (NullPointerException e){
            throw e;
        }
    }
}
