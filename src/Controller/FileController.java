package Controller;
import Entity.Group;
import Entity.abs_Member;
import Service.FileService;
import Exception.ObjectSaveException;
import Exception.ObjectLoadException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * FileController: class which runs saving and loading all Member/Group Information.
 * @author: Seo, HyeongCheol
 */
public class FileController {
    private FileService fileService = new FileService();
    public TreeMap<String, Group> loadGroup(){
        TreeMap<String, Group> allGroupList = null;
        try {
            allGroupList = fileService.readGroupList();
            return allGroupList;
        } catch (FileNotFoundException e) {
            throw new ObjectLoadException("Group", "File Not Found.");
        } catch (ClassNotFoundException e) {
            throw new ObjectLoadException("Group", "Class Typecasting Error.");
        } catch (IOException e) {
            throw new ObjectLoadException("Group", "I/O Exception.");
        }
    }
    public TreeMap<String, abs_Member> loadMember(){
        TreeMap<String, abs_Member> allMemberList = null;
        try {
            allMemberList = fileService.readMemberList();
            return allMemberList;
        } catch (FileNotFoundException e) {
            throw new ObjectLoadException("Member", "File Not Found.");
        } catch (ClassNotFoundException e) {
            throw new ObjectLoadException("Member", "Class Typecasting Error.");
        } catch (IOException e) {
            throw new ObjectLoadException("Member", "I/O Exception.");
        }
    }
    public boolean saveGroup(TreeMap<String, Group> allGroupList){
        try {
            fileService.saveGroupList(allGroupList);
            return true;
        } catch (IOException e){
            throw new ObjectSaveException("Group", "I/O Exception.");
        }
    }
    public boolean saveMember(TreeMap<String, abs_Member> allMemberList){
        try {
            fileService.saveMemberList(allMemberList);
            return true;
        } catch(IOException e){
            throw new ObjectSaveException("Group", "I/O Exception.");
        }
    }
}
