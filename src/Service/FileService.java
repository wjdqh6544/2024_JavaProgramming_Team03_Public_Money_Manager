package Service;

import Entity.Group;
import Entity.abs_Member;
import Exception.ObjectSaveException;
import Exception.ObjectLoadException;

import java.io.*;
import java.util.ArrayList;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * FileService: class which save all Member and Group data.
 * @author: Seo, HyeongCheol
 */
public class FileService implements Serializable {
    private final String FILE_DIR = "./src/";
    private final String MEMBER_FILE_NAME = "allMemberList.dat";
    private final String GROUP_FILE_NAME = "allGroupList.dat";

    public FileService(){ }
    public ArrayList<abs_Member> readMemberList() throws ObjectLoadException {
        try {
            ArrayList<abs_Member> allMemberList;
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_DIR + MEMBER_FILE_NAME));
            allMemberList = (ArrayList<abs_Member>) inputStream.readObject();
            inputStream.close();
            return allMemberList;
        } catch (FileNotFoundException e){
            System.err.println("File not found.");
            throw new ObjectSaveException("Group");
        } catch (ClassNotFoundException e){
            System.err.println("Class Typecasting Error.");
            throw new ObjectSaveException("Group");
        } catch (IOException e){
            e.printStackTrace();
            throw new ObjectSaveException("Group");
        }
    }
    public ArrayList<Group> readGroupList() throws ObjectLoadException {
        try{
            ArrayList<Group> allGroupList;
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_DIR + GROUP_FILE_NAME));
            allGroupList = (ArrayList<Group>) inputStream.readObject();
            inputStream.close();
            return allGroupList;
        } catch (FileNotFoundException e){
            System.err.println("File not found.");
            throw new ObjectSaveException("Group");
        } catch (ClassNotFoundException e){
            System.err.println("Class Typecasting Error.");
            throw new ObjectSaveException("Group");
        } catch (IOException e){
            e.printStackTrace();
            throw new ObjectSaveException("Group");
        }
    }
    public void saveMemberList(ArrayList<abs_Member> allMemberList) throws ObjectSaveException {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_DIR + MEMBER_FILE_NAME));
            outputStream.writeObject(allMemberList);
            outputStream.close();
        } catch (IOException e){
            e.printStackTrace();
            throw new ObjectSaveException("Member");
        }
    }

    public void saveGroupList(ArrayList<Group> allGroupList) throws ObjectSaveException {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_DIR + GROUP_FILE_NAME));
            outputStream.writeObject(allGroupList);
            outputStream.close();
        } catch (IOException e){
            e.printStackTrace();
            throw new ObjectSaveException("Group");
        }
    }
}
