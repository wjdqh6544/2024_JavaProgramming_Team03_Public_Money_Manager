package Controller;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * FileController: class which runs saving and loading all Member/Group Information.
 * @author: Seo, HyeongCheol
 */

import Entity.Group;
import Entity.abs_Member;
import Service.FileService;
import Exception.ObjectSaveException;
import Exception.ObjectLoadException;
import java.util.ArrayList;

public class FileController {
    private FileService fileService = new FileService();
    public ArrayList<Group> loadGroup(){
        ArrayList<Group> allGroupList = null;
        try {
            allGroupList = fileService.readGroupList();
        } catch (ObjectLoadException e){
            throw new ObjectLoadException("Group");
        } finally {
            return allGroupList;
        }
    }
    public ArrayList<abs_Member> loadMember(){
        ArrayList<abs_Member> allMemberList = null;
        try {
            allMemberList = fileService.readMemberList();
        } catch (ObjectLoadException e){
            throw new ObjectLoadException("Member");
        } finally {
            return allMemberList;
        }
    }
    public boolean saveGroup(ArrayList<Group> allGroupList){
        boolean status = false;
        try {
            fileService.saveGroupList(allGroupList);
            status = true;
        } catch (ObjectSaveException e){
            throw new ObjectSaveException("Group");
        } finally {
            return status;
        }
    }
    public boolean saveMember(ArrayList<abs_Member> allMemberList){
        boolean status = false;
        try {
            fileService.saveMemberList(allMemberList);
            status = true;
        } catch(ObjectSaveException e){
            throw new ObjectSaveException("Member");
        } finally {
            return status;
        }
    }
}
