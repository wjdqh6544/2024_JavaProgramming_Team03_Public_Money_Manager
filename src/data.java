import Controller.FileController;
import Controller.GroupController;
import Controller.MemberController;
import Controller.TransactionController;
import Entity.*;
import Exception.*;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.TreeMap;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * Feature: class which connected with Swing. (Swing call the method in this class).
 * @author: Seo, HyeongCheol
 */
public class data {
    FileController fileController = new FileController();
    GroupController groupController = new GroupController();
    MemberController memberController = new MemberController();
    TransactionController transactionController = new TransactionController();
    private abs_Member sessionMember;
    private Group sessionGroup;
    private Transaction sessionTrans;
    
    public data(){}

    public void initialization() throws ObjectLoadException{
        fileController.fileInitializer();
        fileController.loadMember();
        fileController.loadGroup();
    }
    public void saveAndUploadFile() throws ObjectSaveException, JSchException, SftpException, IOException {
        fileController.saveMember(memberController.findAllMember());
        fileController.saveGroup(groupController.findAllGroup());
        fileController.uploadDataFile();
    }
    public void login(String name, String email) throws NotFoundException {
        this.sessionMember = memberController.findMemberByNameAndEmail(name, email);
    }
    public void selectGroup(Group groupObj){
        this.sessionGroup = groupObj;
    }
    public void createMember(String name, int year, int month, int day, String email) throws DuplicatedException {
        memberController.createMember(name, year, month, day, email);
    }
    public void createGroup(String name) throws DuplicatedException {
        groupController.createGroup(name, this.sessionMember);
    }
    public void requestJoinGroup(Group groupObj) throws DuplicatedException {
        groupController.addAwaitMemberToGroup(groupObj, this.sessionMember);
    }
    public void acceptJoinGroup(abs_Member targetMemberObj) throws NotFoundException, DuplicatedException, PermissionException {
        if (this.sessionMember.equals(this.sessionGroup.getPresident()) == true){
            groupController.addMemberToGroup(this.sessionGroup, targetMemberObj);
            memberController.addGroup(targetMemberObj, this.sessionGroup.getGroupName());
        } else {
            throw new PermissionException();
        }
    }
    public void rejectJoinGroup(abs_Member targetMemberObj) throws NotFoundException, PermissionException {
        if (this.sessionMember.equals(this.sessionGroup.getPresident()) == true) {
            groupController.removeAwaitMember(this.sessionGroup, targetMemberObj);
        } else {
            throw new PermissionException();
        }
    }
    public TreeMap<String, MemberPosition> getAllGroupOfMember(){
        return this.sessionMember.getGroupList();
    }
    public void setAdministrator(abs_Member adminObj) throws PermissionException {
        if (this.sessionMember.equals(this.sessionGroup.getPresident()) == true){
            groupController.setAdministrator(this.sessionGroup, adminObj);
        } else {
            throw new PermissionException();
        }
    }
    public ArrayList<Transaction> getTransactionList() throws NullPointerException {
        return groupController.findAllTransaction(this.sessionGroup);
    }
    public void addTransactionHistory(int year, int month, int day, String event, int income, int expense, ArrayList<abs_Member> memberObjList) throws PermissionException, NotFoundException {
        if (this.sessionMember.equals(this.sessionGroup.getAdministrator()) == true) {
            groupController.addTransactionHistory(this.sessionGroup, year, month, day, event, income, expense, memberObjList);
        } else {
            throw new PermissionException();
        }
    }
    public PositionList getPositionOfGroup(Group groupObj) throws NotFoundException {
        return memberController.getPositionOfGroup(this.sessionMember, groupObj);
    }
    public void selectTransaction(Transaction transaction) {
        this.sessionTrans = transaction;
    }
    public void sendEmailToMember() throws MessagingException, UnsupportedEncodingException, ObjectLoadException {
        if (this.sessionMember.equals((this.sessionGroup.getAdministrator())) == true) {
            transactionController.sendEmailToMember(this.sessionGroup, this.sessionTrans);
        } else {
            throw new PermissionException();
        }
    }
    public TreeMap<String, abs_Member> getAllMemberOfGroup() throws NullPointerException {
        return groupController.findAllGroupMemberList(this.sessionGroup);
    }
    public TreeMap<String, abs_Member> getAllAwaitMemberOfGroup() throws NullPointerException {
        return groupController.findAwaitMemberList(this.sessionGroup);
    }
    public TreeMap<String, Group> getAllGroupList() throws NullPointerException {
        return groupController.findAllGroup();
    }
    public boolean checkIfInAwait(Group groupObj){
        return groupController.checkIfInAwait(groupObj, this.sessionMember);
    }
}
