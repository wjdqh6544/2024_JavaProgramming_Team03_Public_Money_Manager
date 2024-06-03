package Service;
import Controller.FileController;
import Entity.Group;
import Entity.Transaction;
import Entity.abs_Member;
import Exception.*;
import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * TransactionServices: class which run service logic relative to Transaction Object.
 * @author: Seo, HyeongCheol
 */
public class TransactionService {
    private final FileController fileController = new FileController();
    public void sendEmailToMember(Group groupObj, Transaction transObj) throws AddressException, MessagingException, UnsupportedEncodingException {
        int count = 0;
        try {
            TreeMap<String, String> conData = fileController.loadConnectionInfo();
            Properties prop = new Properties();
            prop.put("mail.transport.protocol", "smtp");
            prop.put("mail.smtp.host", conData.get("mail_host"));
            prop.put("mail.smtp.port", conData.get("mail_host_port"));
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
            prop.put("mail.smtp.ssl.channel", "starttls");
            Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(conData.get("mail_user_id"), conData.get("mail_user_pw"));
                }
            });
            session.setDebug(false);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(conData.get("mail_user_id"), conData.get("sender"), "UTF-8"));
            InternetAddress[] emailList = getEmailList(transObj);
            message.addRecipients(Message.RecipientType.TO, emailList);
            message.setSubject("새로운 정산 요청이 생성되었습니다. (Group: " + groupObj.getGroupName() + ")");
            message.setText(getEmailContext(groupObj, transObj));
            Transport.send(message);
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (AddressException e) {
            throw e;
        } catch (MessagingException e) {
            throw e;
        } catch (ObjectLoadException e){
            throw e;
        }
    }
    private String getEmailContext(Group groupObj, Transaction transObj) {
        String res = "";
        int cnt = 0;
        res += "\"" + groupObj.getGroupName() + " (대표: " + groupObj.getPresident().getName() + ")\" 회원 여러분 안녕하세요.\n\n" +
                transObj.getDate() + "에 공금 지출이 발생하였으며, 총무(" + groupObj.getAdministrator().getName() + ")가 정산을 요청하였습니다." +
                "\n\n아래 내역을 확인하시고, 문제가 없다면 정산해 주시기 바랍니다.\n\n" +
                "\t- 지출일자: " + transObj.getDate() + "\n\t- 지출내용: " + transObj.getEvent() + "\n\t- 지출금액: " + transObj.getExpense() +
                "\n\t- 정산인원: " + (transObj.getMemberList().size() + transObj.getFinishMember().size()) + "명" +
                "\n\t- 정산 대상자 (미정산 인원): \n\t\t";
        TreeMap<String, abs_Member> list = transObj.getMemberList();
        Iterator<Map.Entry<String,abs_Member>> iter = list.entrySet().iterator();
        while (iter.hasNext() == true) {
            res += iter.next().getValue().getName() + ", ";
            cnt++;
            if (cnt > 10){
                cnt = 0;
                res += "\n\t\t";
            }
        }
        res = res.substring(0, res.length()-2) + "\n\n";
        res += "문의사항이 있으시면, 총무 " + groupObj.getAdministrator().getName() + " 편으로 연락 부탁드립니다. 감사합니다.\n\n" +
                groupObj.getGroupName() + " 대표 " + groupObj.getPresident().getName() + ", 총무 " + groupObj.getAdministrator().getName() + " 드림.\n\n";
        res += "※ 이 메일은 시스템에 의해 자동으로 발송되었습니다. 답장하지 말아주세요.\n";
        return res;
    }
    private InternetAddress[] getEmailList(Transaction transObj) throws AddressException {
        int cnt = 0;
        String tmp = "";
        Iterator<Map.Entry<String, abs_Member>> iterator = transObj.getMemberList().entrySet().iterator();
        while (iterator.hasNext() == true){
            tmp += iterator.next().getValue().getEmail() + " & ";
        }
        String[] tmpList = tmp.split("&");
        InternetAddress[] emailList = new InternetAddress[tmpList.length - 1];
        for (String email : tmpList) {
            if (email.trim().isEmpty() == false) {
                emailList[cnt++] = new InternetAddress(email.trim());
            }
        }
        return emailList;
    }
    public void checkAllMemberFinish(Transaction transObj) throws NullPointerException {
        try {
            if (findAllNotFinishMember(transObj).isEmpty() == true) {
                transObj.setSettlement(true);
            }
        } catch (NullPointerException e){
            throw e;
        }
    }
    public int findExpensePerPerson(Transaction transObj) throws NullPointerException, NotFoundException {
        try {
            if ((findAllFinishMember(transObj).size() + findAllNotFinishMember(transObj).size()) == 0) {
                throw new NotFoundException("Member subject to settlement");
            }
            return (int) Math.ceil((double)transObj.getExpense() / (findAllFinishMember(transObj).size() + findAllNotFinishMember(transObj).size()));
        } catch (NullPointerException e) {
            throw e;
        }
    }
    public void removeMember(Transaction transObj, abs_Member memberObj) throws NotFoundException {
        TreeMap<String, abs_Member> memberList = findAllNotFinishMember(transObj);
        Iterator<Map.Entry<String, abs_Member>> iter = memberList.entrySet().iterator();
        while (iter.hasNext() == true){
            if (iter.next().getValue().equals(memberObj) == true){
                transObj.removeMember(memberObj);
                return;
            }
        }
        throw new NotFoundException("Member");
    }
    public void addMember(Transaction transObj, abs_Member memberObj) throws DuplicatedException {
        TreeMap<String, abs_Member> memberList = findAllNotFinishMember(transObj);
        Iterator<Map.Entry<String, abs_Member>> iter = memberList.entrySet().iterator();
        while (iter.hasNext() == true) {
            if (iter.next().getValue().equals(memberObj) == true){
                throw new DuplicatedException("Member in memberList of transaction");
            }
        }
        transObj.addMember(memberObj);
    }
    public void addFinishMember(Transaction transObj, abs_Member memberObj) throws DuplicatedException {
        TreeMap<String, abs_Member> finishMemberList = findAllFinishMember(transObj);
        Iterator<Map.Entry<String, abs_Member>> iter = finishMemberList.entrySet().iterator();
        while (iter.hasNext() == true) {
            if (iter.next().getValue().equals(memberObj) == true){
                throw new DuplicatedException("Member in FinishMemberList of transaction");
            }
        }
        transObj.addFinishMember(memberObj);
    }
    public TreeMap<String, abs_Member> findAllFinishMember(Transaction transObj) throws NullPointerException {
        TreeMap<String, abs_Member> finishMemberList = transObj.getFinishMember();
        if (finishMemberList == null) {
            throw new NullPointerException();
        }
        return finishMemberList;
    }
    public TreeMap<String, abs_Member> findAllNotFinishMember(Transaction transObj) throws NullPointerException {
        TreeMap<String, abs_Member> memberList = transObj.getMemberList();
        if (memberList == null){
            throw new NullPointerException();
        }
        return memberList;
    }
}
