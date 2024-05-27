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
        try {
            ArrayList<Group> allGroupList = fileService.readGroupList();
            return allGroupList;
        } catch (ObjectLoadException e){
            System.err.println("File not loaded.");
            return null;
        }
    }
    public ArrayList<abs_Member> loadMember(){
        try {
            ArrayList<abs_Member> allMemberList = fileService.readMemberList();
            return allMemberList;
        } catch (ObjectLoadException e){
            System.err.println("File not loaded.");
            return null;
        }
    }
    public boolean saveGroup(ArrayList<Group> allGroupList){
        try {
            fileService.saveGroupList(allGroupList);
            return true;
        } catch (ObjectSaveException e){
            System.err.println("File not saved.");
            return false;
        }
    }
    public boolean saveMember(ArrayList<abs_Member> allMemberList){
        try {
            fileService.saveMemberList(allMemberList);
            return true;
        } catch(ObjectSaveException e){
            System.err.println("File not saved.");
            return false;
        }


    }
}
