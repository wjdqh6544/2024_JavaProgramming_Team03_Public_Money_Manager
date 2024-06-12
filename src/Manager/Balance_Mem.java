package Manager;

import static Start.Constants.height;
import static Start.Constants.width;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Entity.Transaction;
import Entity.abs_Member;
import Controller.data;
import Start.icon;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Lee, Daeun
 */

public class Balance_Mem extends JFrame {
    private data dat;
    Entity.Group g;

    public Balance_Mem(Entity.Group g, data data) {
        this.dat = data;
        this.g = g;

        setTitle("Balance");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.setIconImage(icon.geticon());

        JPanel balance = new JPanel();
        balance.setBackground(Color.WHITE);
        balance.setLayout(new BorderLayout());

        JPanel header = createHeader();
        balance.add(header, BorderLayout.NORTH);

        JPanel list = createList();
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        balance.add(listScrollPane, BorderLayout.CENTER);

        pack();
        setSize(width, height);
        add(balance);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(width, 50));
        header.setBackground(Color.WHITE);
        header.setBorder(new LineBorder(Color.LIGHT_GRAY));
        header.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 5, 0, 5);

        JLabel emptyLabel = new JLabel();
        emptyLabel.setPreferredSize(new Dimension(50, 30));
        header.add(emptyLabel, gbc);
        gbc.gridx++;

        String[] headers = {"Date", "Event", "Members", "Amount", "Balance", "Settlement"};
        int[] labelWidths = {150, 150, 150, 150, 150, 150};

        for (int i = 0; i < headers.length; i++) {
            JLabel headerLabel = new JLabel(headers[i]);
            headerLabel.setPreferredSize(new Dimension(labelWidths[i], 30));
            headerLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
            
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(0, 5, 0, 5);
            
            header.add(headerLabel, gbc);
            gbc.gridx++;
        }

        return header;
    }

    private JPanel createList() {
        JPanel list = new JPanel();
        list.setBackground(Color.WHITE);
        list.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();
        con.fill = GridBagConstraints.HORIZONTAL;
        con.anchor = GridBagConstraints.CENTER;
        con.gridx = 0;
        con.gridy = 0;
        con.weightx = 1.0;
        con.weighty = 0;

        ArrayList<Transaction> transactions = g.getTransactionList();
        
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            ArrayList<String> rowData = new ArrayList<>();
            rowData.add(transaction.getDate().toString());
            rowData.add(transaction.getEvent());

            Set<String> uniqueMembers = new HashSet<>();
            Set<String> fullMemberEmails = new HashSet<>();

            for (abs_Member member : transaction.getMemberList().values()) {
                String maskedEmail = maskEmail(member.getName(), member.getEmail());
                uniqueMembers.add(maskedEmail);
                fullMemberEmails.add(member.getName() + "(" + member.getEmail() + ")");
            }
            for (abs_Member member : transaction.getFinishMember().values()) {
                String maskedEmail = maskEmail(member.getName(), member.getEmail());
                uniqueMembers.add(maskedEmail);
                fullMemberEmails.add(member.getName() + "(" + member.getEmail() + ")");
            }

            StringBuilder members = new StringBuilder();
            for (String memberInfo : uniqueMembers) {
                if (members.length() > 0) {
                    members.append(", ");
                }
                members.append(memberInfo);
            }
            rowData.add(members.toString());

            int income = transaction.getIncome();
            int expense = transaction.getExpense();
            int runningBalance = g.getBalance();
            
            if (income > 0) {
                rowData.add("입금: " + String.valueOf(income));
            } else if (expense > 0) {
                rowData.add("출금: " + String.valueOf(expense));
            } else {
                rowData.add("");
            }

            rowData.add(String.valueOf(runningBalance));

            rowData.add(income > 0 ? "-" : transaction.isSettlement() ? "Y" : "N");

            addRow(list, rowData, con, i, String.join(", ", fullMemberEmails));
        }

        con.weighty = 1.0;
        con.gridy = transactions.size();
        list.add(new JLabel(), con);
        
        return list;
    }

    private void addRow(JPanel panel, ArrayList<String> data, GridBagConstraints con, int rowIndex, String tooltipText) {
        con.gridy = rowIndex;
        con.weighty = 0;
        con.anchor = GridBagConstraints.CENTER;
        con.insets = new Insets(0, 5, 0, 5);
        

        double[] columnWeights = {1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
        int[] labelWidths = {150, 150, 150, 150, 150, 150};

        for (int i = 0; i < data.size(); i++) {
            JLabel label = new JLabel(data.get(i));
            label.setPreferredSize(new Dimension(labelWidths[i], 30));
            label.setFont(new Font("SansSerif", Font.PLAIN, 15));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            con.weightx = columnWeights[i];
            con.gridx = i;
            con.anchor = GridBagConstraints.CENTER;
            con.insets = new Insets(0, 5, 0, 5);
            
            if (i == 2) {
                label.setToolTipText(tooltipText);
            }
            panel.add(label, con);
        }
    }

    private String maskEmail(String name, String email) {
        int atIndex = email.indexOf('@');
        if (atIndex != -1) {
            String username = email.substring(0, atIndex);
            String domain = email.substring(atIndex + 1);
            String maskedUsername = username.length() > 2 ? username.substring(0, 2) + "*" : username + "*";
            String maskedDomain = domain.length() > 2 ? domain.substring(0, 2) + "*" : domain + "*";
            return name + " (" + maskedUsername + "@" + maskedDomain + ")";
        } else {
            return name;
        }
    }
}
