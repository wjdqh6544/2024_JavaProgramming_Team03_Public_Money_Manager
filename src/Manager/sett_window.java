package Manager;

import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import Entity.Group;
import Entity.Transaction;
import Entity.abs_Member;
import Exception.ObjectLoadException;
import Exception.PermissionException;
import Service.TransactionService;
import Exception.DuplicatedException;
import Exception.NotFoundException;
import Start.Constants;
import Controller.data;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Lee, Daeun
 */

public class sett_window extends JPanel implements Constants {
    private JPanel cen;
    private JPanel sett;
    private JPanel list;
    private data dat;
    private Group g;
    private Transaction selectedTransaction;
    private abs_Member selectedMember;
    private TransactionService transactionService;
    
    private boolean isFirstMemberAdded;
    public sett_window(Entity.Group g, data data) {
        this.dat = data;
        this.g = g;
        this.transactionService = new TransactionService();
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        cen = new JPanel(new BorderLayout());
        sett = new JPanel(new BorderLayout());
        sett.setBackground(Color.WHITE);

        JPanel header = createHeader();
        sett.add(header, BorderLayout.NORTH);

        list = createList();
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sett.add(listScrollPane, BorderLayout.CENTER);

        cen.add(sett, BorderLayout.CENTER);
        add(cen, BorderLayout.CENTER);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new GridBagLayout());
        header.setPreferredSize(new Dimension(width, 50));
        header.setBackground(Color.WHITE);
        header.setBorder(new LineBorder(Color.LIGHT_GRAY));

        String[] headers = {"Date", "Message", "Member", "Amount", "Settlement"};
        int[] labelWidths = {100, 100, 100, 100, 100};

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 100, 0, 0);

        for (int i = 0; i < headers.length; i++) {
            JLabel headerLabel = new JLabel(headers[i]);
            headerLabel.setPreferredSize(new Dimension(labelWidths[i], 30));
            headerLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
            header.add(headerLabel, gbc);
            gbc.gridx++;
        }

        return header;
    }

    private JPanel createList() {
        JPanel list = new JPanel(new GridBagLayout());
        list.setBackground(Color.WHITE);

        GridBagConstraints con = new GridBagConstraints();
        con.fill = GridBagConstraints.HORIZONTAL;
        con.anchor = GridBagConstraints.NORTH;
        con.gridx = 0;
        con.gridy = 0;
        con.weightx = 1.0;
        con.weighty = 0;

        ArrayList<Transaction> transactions = g.getTransactionList();
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            int expense = transaction.getExpense();
            if (expense <= 0 || transaction.isSettlement()) {
                continue;
            }

            ArrayList<String> rowData = new ArrayList<>();
            rowData.add(transaction.getDate().toString());
            rowData.add(transaction.getEvent() + " 정산해주세요");

            int expensePerPerson;
            try {
                expensePerPerson = transactionService.findExpensePerPerson(transaction);
            } catch (NotFoundException e) {
                expensePerPerson = 0;
            }

            isFirstMemberAdded = false;

            for (abs_Member member : transaction.getMemberList().values()) {
                String maskedEmail = maskEmail(member.getName(), member.getEmail());
                if (!isFirstMemberAdded) {
                    rowData.add(maskedEmail);
                    rowData.add(String.valueOf(expensePerPerson));
                    rowData.add(transaction.isSettlement() ? "Y" : "N");
                    addRow(list, rowData, con.gridy++, transaction, member);
                    isFirstMemberAdded = true;
                } else {
                    continue;
                }
            }

            for (abs_Member member : transaction.getMemberList().values()) {
                if (member.equals(transaction.getMemberList().values().toArray()[0])) {
                    continue;
                }
                String maskedEmail = maskEmail(member.getName(), member.getEmail());
                ArrayList<String> memberRowData = new ArrayList<>();
                memberRowData.add("");
                memberRowData.add("");
                memberRowData.add(maskedEmail);
                memberRowData.add(String.valueOf(expensePerPerson));
                memberRowData.add(transaction.isSettlement() ? "Y" : "N");

                addRow(list, memberRowData, con.gridy++, transaction, member);
            }
        }

        GridBagConstraints spacingConstraints = new GridBagConstraints();
        spacingConstraints.gridx = 0;
        spacingConstraints.gridy = con.gridy;
        spacingConstraints.weightx = 1.0;
        spacingConstraints.weighty = 1.0;
        list.add(new JLabel(), spacingConstraints);

        return list;
    }

    private void addRow(JPanel panel, ArrayList<String> data, int rowIndex, Transaction transaction, abs_Member member) {
        GridBagConstraints con = new GridBagConstraints();
        con.gridy = rowIndex;
        con.weighty = 0;
        double[] columnWeights = {1.5, 3.0, 3.0, 1.8, 1.2, 1.0};
        int[] labelWidths = {200, 200, 200, 50, 50, 100};
        for (int i = 0; i < data.size(); i++) {
            JLabel label = new JLabel(data.get(i));
            label.setPreferredSize(new Dimension(labelWidths[i], 30));
            label.setFont(new Font("SansSerif", Font.PLAIN, 15));
            con.weightx = columnWeights[i];
            con.gridx = i;
            panel.add(label, con);
            con.weightx = columnWeights[i];
            con.gridx = i + 1;
            con.insets = new Insets(0, 5, 0, 0);
            if (data.get(i).equals("N")) {
            	if(!isFirstMemberAdded) {
                JButton sendButton = new JButton("Send Message");
                sendButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
                sendButton.setBackground(Color.WHITE);
                sendButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
                sendButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            dat.selectTransaction(transaction);
                            int confirmDialogResult = JOptionPane.showConfirmDialog(null, "정말 이메일을 보내시겠습니까?", "Send Email", JOptionPane.YES_NO_OPTION);
                            if (confirmDialogResult == JOptionPane.YES_OPTION) {
                                dat.sendEmailToMember();
                            }
                        } catch (MessagingException | UnsupportedEncodingException | ObjectLoadException | PermissionException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                panel.add(sendButton, con);
            	}
                JButton removeButton = new JButton("Remove Member");
                removeButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
                removeButton.setBackground(Color.WHITE);
                removeButton.setBorder(new LineBorder(Color.LIGHT_GRAY));
                removeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            selectedTransaction = transaction;
                            selectedMember = member;

                            dat.selectTransaction(selectedTransaction);
                            int confirmDialogResult = JOptionPane.showConfirmDialog(null, "정산 완료된 멤버를 삭제합니다.", "Remove Member", JOptionPane.YES_NO_OPTION);
                            if (confirmDialogResult == JOptionPane.YES_OPTION) {
                                dat.changeMemberToFinish(selectedMember);
                                JOptionPane.showMessageDialog(null, "Member removed successfully.");
                            }
                        } catch (NotFoundException ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Unexpected error: " + ex.getMessage());
                        }
                    }
                });


                con.gridx = i + 2;
                panel.add(removeButton, con);
            }
        }
    }

    private void sendEmail(Transaction transaction) {
        dat.selectTransaction(transaction);
        int confirmDialogResult = JOptionPane.showConfirmDialog(this, "정말 이메일을 보내시겠습니까?", "Send Email", JOptionPane.YES_NO_OPTION);
        if (confirmDialogResult == JOptionPane.YES_OPTION) {
            try {
                dat.sendEmailToMember();
            } catch (MessagingException | UnsupportedEncodingException | ObjectLoadException | PermissionException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        // Handling button clicks
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            if (button.getText().equals("Send Message")) {
                sendEmail(selectedTransaction);
            } else if (button.getText().equals("Remove Member")) {
                try {
                    if (selectedTransaction == null) {
                        System.out.println("selectedTransaction is null");
                    } else {
                        System.out.println("selectedTransaction: " + selectedTransaction.toString());
                    }
                    if (selectedMember == null) {
                        System.out.println("selectedMember is null");
                    } else {
                        System.out.println("selectedMember: " + selectedMember.getEmail());
                    }

                    dat.selectTransaction(selectedTransaction);
                    dat.changeMemberToFinish(selectedMember);
                    JOptionPane.showMessageDialog(null, "Member removed successfully.");
                } catch (NotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Unexpected error: " + ex.getMessage());
                }
            }
        }
    }

    private String maskEmail(String name, String email) {
        int atIndex = email.indexOf('@');
        if (atIndex != -1) {
            String username = email.substring(0, atIndex);
            String domain = email.substring(atIndex + 1);
            String maskedUsername = username.length() > 2 ? username.substring(0, 2) + "*" : username + "*";
            String maskedDomain = domain.length() > 2 ? domain.substring(0, 2) + "*" : domain + "*";
            return name + "(" + maskedUsername + "@" + maskedDomain + ")";
        } else {
            return name;
        }
    }
}
