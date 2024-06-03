package Service;
import Entity.Group;
import Entity.abs_Member;
import java.io.*;
import java.util.TreeMap;
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
    public boolean existGroupListFile(){
        File file = new File(FILE_DIR + GROUP_FILE_NAME);
        return file.exists();
    }
    public boolean existMemberListFile() {
        File file = new File(FILE_DIR + MEMBER_FILE_NAME);
        return file.exists();
    }
    public TreeMap<String, abs_Member> readMemberList() throws FileNotFoundException, ClassNotFoundException, IOException {
        try {
            TreeMap<String, abs_Member> allMemberList;
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_DIR + MEMBER_FILE_NAME));
            allMemberList = (TreeMap<String, abs_Member>) inputStream.readObject();
            inputStream.close();
            return allMemberList;
        } catch (FileNotFoundException | ClassNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }
    public TreeMap<String, Group> readGroupList() throws FileNotFoundException, ClassNotFoundException, IOException {
        try{
            TreeMap<String, Group> allGroupList;
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_DIR + GROUP_FILE_NAME));
            allGroupList = (TreeMap<String, Group>) inputStream.readObject();
            inputStream.close();
            return allGroupList;
        } catch (FileNotFoundException | ClassNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }
    public void saveMemberList(TreeMap<String, abs_Member> allMemberList) throws IOException {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_DIR + MEMBER_FILE_NAME));
            outputStream.writeObject(allMemberList);
            outputStream.close();
        } catch (IOException e){
            throw e;
        }
    }
    public void saveGroupList(TreeMap<String, Group> allGroupList) throws IOException {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_DIR + GROUP_FILE_NAME));
            outputStream.writeObject(allGroupList);
            outputStream.close();
        } catch (IOException e){
            throw e;
        }
    }
}
