package Controller;
import Entity.Group;
import Entity.abs_Member;
import Service.FileService;
import Exception.ObjectSaveException;
import Exception.ObjectLoadException;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.json.simple.parser.ParseException;

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
    private final FileService fileService = new FileService();
    public FileController(){}
    public void downloadDataFile(String type) throws IOException, SftpException, JSchException {
        try {
            TreeMap<String, String> conData = loadConnectionInfo();
            fileService.sftpInit(conData);
            fileService.downloadFileFromSFTP(conData, type);
            fileService.disconnect();
        } catch (JSchException e) {
            throw e;
        } catch (SftpException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }
    public void uploadDataFile() throws SftpException, IOException, JSchException {
        try {
            TreeMap<String, String> conData = loadConnectionInfo();
            fileService.sftpInit(conData);
            fileService.uploadFileToSFTP(conData);
            fileService.disconnect();
        } catch (SftpException e) {
            throw e;
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (JSchException e) {
            throw e;
        }

    }
    public TreeMap<String, String> loadConnectionInfo() {
        TreeMap<String, String> conData = null;
        try {
            conData = fileService.readConnInfoFile();
            return conData;
        } catch (FileNotFoundException e) {
            throw new ObjectLoadException("Connection Info", "File Not Found.");
        } catch (IOException e) {
            throw new ObjectLoadException("Connection Info", "File Reading Error.");
        } catch (ParseException e) {
            throw new ObjectLoadException("Connection Info", "Parse Error.");
        }
    }
    public void fileInitializer() {
        TreeMap<String, String> conData = loadConnectionInfo();
        try {
            downloadDataFile("Member");
            System.out.println("Member File downloaded Successfully.");
        } catch (JSchException | IOException | SftpException e){
            System.out.println("Member File not exist. Create empty file.\n");
            saveMember(new TreeMap<String, abs_Member>());
        }
        try {
            downloadDataFile("Group");
            System.out.println("Group File downloaded Successfully.");
        } catch (JSchException | IOException | SftpException e) {
            System.out.println("Group File not exist. Create empty file.\n");
            saveGroup(new TreeMap<String, Group>());
        }
    }
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
