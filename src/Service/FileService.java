package Service;

import Entity.Group;
import Entity.abs_Member;
import com.jcraft.jsch.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.Properties;
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
    private final String CONN_INFO_FILE_NAME = "connectionInfo.json";
    private final String FILE_DIR_IN_SERVER = "/home/";
    private Channel channel = null;
    private ChannelSftp channelSftp = null;
    private Session session = null;
    public FileService(){ }
    public void downloadFileFromSFTP(TreeMap<String, String> conData, String type) throws SftpException, IOException {
        try {
            int readCnt;
            byte[] buffer = new byte[1024];
            File localFile = null;
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            channelSftp.cd(FILE_DIR_IN_SERVER + conData.get("sftp_user_id"));
            if (type.equals("Member")){
                localFile = new File(FILE_DIR + MEMBER_FILE_NAME);
                bis = new BufferedInputStream(channelSftp.get(MEMBER_FILE_NAME));
                bos = new BufferedOutputStream(new FileOutputStream(localFile));
                while ((readCnt = bis.read(buffer)) > 0){
                    bos.write(buffer, 0, readCnt);
                }
                bis.close();
                bos.close();
                System.out.println(MEMBER_FILE_NAME + " File downloaded Successfully.");
            } else if (type.equals("Group")){
                localFile = new File(FILE_DIR + GROUP_FILE_NAME);
                bis = new BufferedInputStream(channelSftp.get(GROUP_FILE_NAME));
                bos = new BufferedOutputStream(new FileOutputStream(localFile));
                while ((readCnt = bis.read(buffer)) > 0){
                    bos.write(buffer, 0, readCnt);
                }
                bis.close();
                bos.close();
                System.out.println(GROUP_FILE_NAME + " File downloaded Successfully.");
            }



        } catch (SftpException e) {
            throw e;
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }
    public void uploadFileToSFTP(TreeMap<String, String> conData) throws SftpException, IOException {
        try{
            FileInputStream file = new FileInputStream(FILE_DIR + MEMBER_FILE_NAME);
            channelSftp.cd(FILE_DIR_IN_SERVER + conData.get("sftp_user_id") + "/");
            channelSftp.put(file, MEMBER_FILE_NAME);
            System.out.println(MEMBER_FILE_NAME + " Uploaded Successfully.");
            file.close();
            file = new FileInputStream(FILE_DIR + GROUP_FILE_NAME);
            channelSftp.put(file, GROUP_FILE_NAME);
            System.out.println(GROUP_FILE_NAME + " Uploaded Successfully.");
            file.close();
        } catch (FileNotFoundException e) {
            throw e;
        } catch (SftpException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }
    public void sftpInit(TreeMap<String, String> conData) throws IOException, JSchException {
        try {
            final int TIMEOUT = 10000;
            JSch jsch = new JSch();
            InetAddress local = InetAddress.getLocalHost();
            session = jsch.getSession(conData.get("sftp_user_id"), conData.get("sftp_host"), Integer.parseInt(conData.get("sftp_host_port")));
            session.setPassword(conData.get("sftp_user_pw"));
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setTimeout(TIMEOUT);
            System.out.println("Connecting...");
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            System.out.println("Connected Successfully.");
        } catch (JSchException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
    }
    public void disconnect(){
        if (channelSftp != null){
            channelSftp.quit();
        }
        if (channel != null){
            channel.disconnect();
        }
        if (session != null){
            session.disconnect();
        }
    }
    public TreeMap<String, String> readConnInfoFile() throws FileNotFoundException, IOException, ParseException {
        try {
            JSONParser parser = new JSONParser();
            Reader jsonFile = new FileReader(FILE_DIR + CONN_INFO_FILE_NAME);
            JSONObject jsonObj = (JSONObject) parser.parse(jsonFile);
            TreeMap<String, String> conData = new TreeMap<String, String >();
            Iterator iter = jsonObj.keySet().iterator();
            while (iter.hasNext() == true) {
                String key = (String) iter.next();
                conData.put(key, (String) jsonObj.get(key));
            }
            jsonFile.close();
            return conData;
        } catch (FileNotFoundException e){
            throw e;
        } catch (IOException e) {
            throw e;
        } catch (ParseException e) {
            throw e;
        }
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
        } catch (IOException e) {
            throw e;
        }
    }
}
