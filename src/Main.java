import Controller.FileController;
import Controller.GroupController;
import Controller.MemberController;
import Controller.TransactionController;
import Entity.Group;
import Entity.Transaction;
import Entity.abs_Member;
import org.junit.Test;

import Exception.*;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * Main: class for testing.
 * @author: Seo, HyeongCheol
 */
public class Main {
    private final GroupController groupController = new GroupController();
    private final MemberController memberController = new MemberController();
    private final FileController fileController = new FileController();
    private final TransactionController transactionController = new TransactionController();
    @Test
    public void macro(){
        fileInitializerTest();
        createTest();
        saveMemberTest();
        createGroupTest();
        saveGroupTest();
        System.out.println("---- addAwaitMemberToGroupTestTemp ----");
        addAwaitMemberToGroupTestTmp();
        System.out.println("---- addMemberToGroupTestTmp ----");
        addMemberToGroupTestTmp();
        setAdministratorTestTmp();
        System.out.println("---- addTransactionTestTmp ----");
        addTransactionTestTmp();
        System.out.println("---- changeMemberToFinishTest ----");
        System.out.println("---- manualAddMemberTest ----");
        manualAddMemberTest();
        saveGroupTest();
        loadGroupTest();
        System.out.println("---- findExpensePerPersonTest ----");
        findExpensePerPersonTest();
        System.out.println("---- sendEmailToMemberTest ----");
        sendEmailToMemberTest();
        changeMemberToFinishTest();
        sendEmailToMemberTest();
        changeMemberToFinishTest2();
        sendEmailToMemberTest();
        uploadDataFileTest();
    }
    @Test
    public void fileInitializerTest(){
        fileController.fileInitializer();
    }
    @Test
    public void downloadDataFileTest(){
        try{
            fileController.downloadDataFile("");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void uploadDataFileTest() {
        try {
            fileController.uploadDataFile();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    @Test
    public void sendEmailToMemberTest(){
        loadGroupTest();
        loadMemberTest();
        Group groupObj = groupController.findGroupByName("G1");
        Transaction transObj = groupController.findAllTransaction(groupObj).get(0);
        sendEmailToMember(groupObj, transObj);
        System.out.println("Send Successfully!");
    }
    public void sendEmailToMember(Group groupObj, Transaction transObj) {
        try {
            transactionController.sendEmailToMember(groupObj, transObj);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e){
            e.printStackTrace();
        } catch (ObjectLoadException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findExpensePerPersonTest(){
        Group groupObj = groupController.findGroupByName("G1");
        Transaction transObj = groupController.findAllTransaction(groupObj).get(0);
        System.out.println(findExpensePerPerson(transObj));
    }
    public int findExpensePerPerson(Transaction transObj){
        try {
            return transactionController.findExpensePerPerson(transObj);
        } catch (NotFoundException | NullPointerException e){
            e.printStackTrace();
            return -1;
        }
    }
    @Test
    public void manualAddMemberTest(){ // tested
        Group groupObj = groupController.findGroupByName("G1");
        Transaction transObj = groupController.findAllTransaction(groupObj).get(0);
        abs_Member memberObj = memberController.findMemberByNameAndEmail("TEST2", "wjdqh6544@gmail.com");
        manualAddMember(groupObj, transObj, memberObj);
    }
    // tested
    public void manualAddMember(Group groupObj, Transaction transObj, abs_Member memberObj){
        try {
            transactionController.manualAddMember(groupObj, transObj, memberObj);
        } catch (NotFoundException | DuplicatedException e){
            e.printStackTrace();
        }
    }
    @Test
    public void findNotSettlementMemberTmp(){ // tested
        System.out.println("-------------------------------");
        System.out.println(findNotSettlementMember(groupController.findAllTransaction(groupController.findGroupByName("G1")).get(1)));
    }
    public TreeMap<String, abs_Member> findNotSettlementMember(Transaction transObj){ // tested
        try {
            TreeMap<String, abs_Member> allNotSettlementMember = transactionController.findNotFinishMember(transObj);
            return allNotSettlementMember;
        } catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }
    @Test
    public void findAllSettlementMemberTmp(){ // tested
        System.out.println("-------------------------------");
        System.out.println(findAllSettlementMember(groupController.findAllTransaction(groupController.findGroupByName("G1")).get(0)));
    }
    public TreeMap<String, abs_Member> findAllSettlementMember(Transaction transObj){ // tested
        try {
            TreeMap<String, abs_Member> allSettleMember = transactionController.findFinishMember(transObj);
            return allSettleMember;
        } catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }
    @Test
    public void changeMemberToFinishTest2() { // tested
        System.out.println("+++++++++++++++++++++++++++++");
        ArrayList<Transaction> transObj = groupController.findAllTransaction(groupController.findGroupByName("G1"));
        changeMemberToFinish(groupController.findAllTransaction(groupController.findGroupByName("G1")).get(0), memberController.findMemberByNameAndEmail("TEST3", "wjdqh6544@knu.ac.kr"));
    }
    @Test
    public void changeMemberToFinishTest() { // tested
        System.out.println("+++++++++++++++++++++++++++++");
        ArrayList<Transaction> transObj = groupController.findAllTransaction(groupController.findGroupByName("G1"));
        changeMemberToFinish(groupController.findAllTransaction(groupController.findGroupByName("G1")).get(0), memberController.findMemberByNameAndEmail("TEST", "wjdqh6544@naver.com"));
    }
    public void changeMemberToFinish(Transaction transObj, abs_Member memberObj){ // tested
        try {
            transactionController.changeMemberToFinish(transObj, memberObj);
        } catch (DuplicatedException e){
            e.printStackTrace();
        }
    }
    @Test
    public void addTransactionTestTmp(){ // tested
        try {
            ArrayList<abs_Member> tmp = new ArrayList<>();
            TreeMap<String, abs_Member> allMemberList = groupController.findAllGroupMemberList(groupController.findGroupByName("G1"));
            Iterator<Map.Entry<String , abs_Member>> iter = allMemberList.entrySet().iterator();
            while (iter.hasNext() == true){
                tmp.add(iter.next().getValue());
                if(tmp.get(tmp.size()-1).getName().equals("TEST2") == true){
                    tmp.remove(tmp.size()-1);
                }
            }
            addTransactionTest(groupController.findGroupByName("G1"), 2024, 9, 9, "TransActionTest1", 10000, 10000, tmp);
            addTransactionTest(groupController.findGroupByName("G1"), 2024, 10, 9, "TransActionTest2", 20000, 20000, tmp);
            addTransactionTest(groupController.findGroupByName("G1"), 2024, 9, 10, "TransActionTest3", 20000, 20000, tmp);
            addTransactionTest(groupController.findGroupByName("G1"), 2024, 10, 10, "TransActionTest4", 20000, 20000, tmp);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    //tested
    public void addTransactionTest(Group groupObj, int year, int month, int day, String event, int income, int expense, ArrayList<abs_Member> memberObjList) {
        try {
            groupController.addTransactionHistory(groupObj, year, month, day, event, income, expense, memberObjList);
        } catch (NotFoundException e){
            e.printStackTrace();
        }

    }
    @Test
    public void setAdministratorTestTmp(){ // tested.
        loadGroupTest();
        loadMemberTest();
        setAdministratorTest(groupController.findGroupByName("G1"), memberController.findMemberByNameAndEmail("TEST3", "wjdqh6544@knu.ac.kr"));
        System.out.println(memberController.findAllMember());
        System.out.println("---------");
        System.out.println(groupController.findAllGroup());
        saveGroupTest();
        saveMemberTest();
    }
    public void setAdministratorTest(Group groupObj, abs_Member memberObj){ // tested.
        try {
            groupController.setAdministrator(groupObj, memberObj);
        } catch (NotFoundException | NotChangePositionException e){
            e.printStackTrace();
        }
    }
    @Test
    public void addMemberToGroupTestTmp(){ // tested.
        loadMemberTest();
        loadGroupTest();
        System.out.println("--------------------------\n[MEMBER]");
        System.out.println("G1 " + groupController.findGroupByName("G1").getMemberList());
        System.out.println("[AWAIT]");
        System.out.println("G1 " + groupController.findGroupByName("G1").getAwaitMemberList());
        System.out.println("[MEMBER]\nG2 " + groupController.findGroupByName("G2").getMemberList());
        System.out.println("[AWAIT]");
        System.out.println("G2 " + groupController.findAwaitMemberList(groupController.findGroupByName("G2")));
        System.out.println("[Transaction - G1]");
        System.out.println(groupController.findAllTransaction(groupController.findGroupByName("G1")));
        System.out.println("*************************\n[MEMBER]");
        addMemberToGroup(groupController.findGroupByName("G1"), memberController.findMemberByNameAndEmail("TEST3", "wjdqh6544@knu.ac.kr"));
        addMemberToGroup(groupController.findGroupByName("G1"), memberController.findMemberByNameAndEmail("TEST2", "wjdqh6544@gmail.com"));
        addMemberToGroup(groupController.findGroupByName("G2"), memberController.findMemberByNameAndEmail("TEST3", "wjdqh6544@knu.ac.kr"));
        System.out.println(groupController.findGroupByName("G1").getMemberList());
        System.out.println("[AWAIT]");
        System.out.println(groupController.findGroupByName("G1").getAwaitMemberList());
        saveMemberTest();
        saveGroupTest();
    }
    public void addMemberToGroup(Group groupObj, abs_Member memberObj){ // tested.
        try {
            groupController.addMemberToGroup(groupObj, memberObj);
            memberController.addGroup(memberObj, groupObj.getGroupName());
        } catch (DuplicatedException e){
            throw e;
        }
    }
    @Test
    public void addAwaitMemberToGroupTestTmp(){ // tested.
        loadMemberTest();
        loadGroupTest();
        Group groupObj = groupController.findGroupByName("G1");
        abs_Member memberObj = memberController.findMemberByNameAndEmail("TEST3", "wjdqh6544@knu.ac.kr");
        addAwaitMemberToGroupTest(groupObj, memberObj);
        addAwaitMemberToGroupTest(groupObj, memberController.findMemberByNameAndEmail("TEST2", "wjdqh6544@gmail.com"));
        System.out.println(groupObj.getGroupName() + " " + groupObj.getAwaitMemberList());
        groupObj = groupController.findGroupByName("G2");
        addAwaitMemberToGroupTest(groupObj, memberObj);
        System.out.println(groupObj.getGroupName() + " " + groupObj.getAwaitMemberList());
        System.out.println("--------------------------------");
        saveGroupTest();
        loadGroupTest();
    }
    // tested.
    public void addAwaitMemberToGroupTest(Group groupObj, abs_Member memberObj){
        try {
            groupController.addAwaitMemberToGroup(groupObj, memberObj);
        } catch (DuplicatedException | NullPointerException e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Test
    public void createGroupTest(){ // tested.
        loadMemberTest();
        loadGroupTest();
        System.out.println("-----------------------------------------");
        groupController.createGroup("G1", findMemberByNameAndEmailTest("TEST", "wjdqh6544@naver.com"));
        groupController.createGroup("G2", findMemberByNameAndEmailTest("TEST2", "wjdqh6544@gmail.com"));
    }
    @Test
    public void createTest(){ // tested.
        memberController.createMember("TEST", 2000, 1, 1, "wjdqh6544@naver.com");
        memberController.createMember("TEST2", 2000, 1, 1, "wjdqh6544@gmail.com");
        memberController.createMember("TEST3", 2000, 1, 1, "wjdqh6544@knu.ac.kr");
    }

    // tested.
    public abs_Member findMemberByNameAndEmailTest(String name, String email) {
        //loadMemberTest();
        return memberController.findMemberByNameAndEmail(name, email);
    }

    @Test
    public void saveMemberTest() { // tested.
        //createTest();
        if (fileController.saveMember(memberController.findAllMember()) == true) {
            System.out.println("Member File Saved Successfully");
        }
    }
    @Test
    public void saveGroupTest(){ // tested.
        //createGroupTest();
        if (fileController.saveGroup(groupController.findAllGroup()) == true){
            System.out.println("Group File saved Successfully");
        }
        saveMemberTest();
    }

    @Test
    public void loadMemberTest(){ // tested.
        memberController.setAllMemberList(fileController.loadMember());
        TreeMap<String, abs_Member> allMemberList = memberController.findAllMember();
        Iterator<Map.Entry<String, abs_Member>> iter = allMemberList.entrySet().iterator();
        while (iter.hasNext() == true){
            Map.Entry<String, abs_Member> obj = iter.next();
            System.out.println(obj.getValue());
        }
    }
    @Test
    public void loadGroupTest(){ // tested.
        groupController.setAllGroupList(fileController.loadGroup());
        TreeMap<String, Group> allGroupList = groupController.findAllGroup();
        Iterator<Map.Entry<String, Group>> iter = allGroupList.entrySet().iterator();
        while (iter.hasNext() == true){
            Map.Entry<String, Group> obj = iter.next();
            System.out.println(obj.getValue().getGroupName());
            System.out.print("Member: ");
            System.out.println(obj.getValue().getMemberList());
            System.out.print("Await: ");
            System.out.println(obj.getValue().getAwaitMemberList());
            System.out.println("Admin: " + obj.getValue().getAdministrator());
            System.out.println("TransAction: " + groupController.findAllTransaction(obj.getValue()));
            Iterator<Map.Entry<String, abs_Member>> it = obj.getValue().getMemberList().entrySet().iterator();
        }
    }
}