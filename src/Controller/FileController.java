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
import java.util.TreeMap;

public class FileController {
    private FileService fileService = new FileService();
    public TreeMap<String, Group> loadGroup(){
        TreeMap<String, Group> allGroupList = null;
        try {
            allGroupList = fileService.readGroupList();
            return allGroupList;
        } catch (ObjectLoadException e){
            throw new ObjectLoadException("Group");
        }
    }
    public TreeMap<String, abs_Member> loadMember(){
        TreeMap<String, abs_Member> allMemberList = null;
        try {
            allMemberList = fileService.readMemberList();
            return allMemberList;
        } catch (ObjectLoadException e){
            throw new ObjectLoadException("Member");
        }
    }
    public boolean saveGroup(TreeMap<String, Group> allGroupList){
        try {
            fileService.saveGroupList(allGroupList);
            return true;
        } catch (ObjectSaveException e){
            throw new ObjectSaveException("Group");
        }
    }
    public boolean saveMember(TreeMap<String, abs_Member> allMemberList){
        try {
            fileService.saveMemberList(allMemberList);
            return true;
        } catch(ObjectSaveException e){
            throw new ObjectSaveException("Member");
        }
    }
}
