package Manager;

import javax.swing.*;
import javax.swing.border.LineBorder;

import static Start.Constants.height;
import static Start.Constants.width;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Entity.Group;
import Entity.Transaction;
import Entity.abs_Member;
import Controller.data;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Lee, Daeun
 */

public class bm_window extends JPanel implements ActionListener {
    private JPanel cen;
    private JPanel balance;
    private JPanel list;
    private JButton addEventButton = null;
    private data dat = null;
    private Group g;

    public bm_window(Entity.Group g, data data) {
        this.dat = data;
        this.g = g;
        initializeComponents();
    }

    private void initializeComponents() {
        setLayout(new BorderLayout());

        cen = new JPanel(new BorderLayout());
        balance = new JPanel(new BorderLayout());
        balance.setBackground(Color.WHITE);

        JPanel header = createHeader();
        balance.add(header, BorderLayout.NORTH);

        list = createList();
        JScrollPane listScrollPane = new JScrollPane(list);
        listScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        balance.add(listScrollPane, BorderLayout.CENTER);

        cen.add(balance, BorderLayout.CENTER);
        add(cen, BorderLayout.CENTER);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new GridBagLayout());
        header.setPreferredSize(new Dimension(width, 50));
        header.setBackground(Color.WHITE);
        header.setBorder(new LineBorder(Color.LIGHT_GRAY));

        String[] headers = {"Date", "Event", "Members", "Amount", "Balance", "Settlement"};
        int[] labelWidths = {100, 100, 100, 100, 100, 100};

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 70, 0, 0);

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

        int runningBalance = 0;

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

            if (income > 0) {
                rowData.add("입금: " + income);
                runningBalance += income;
            } else if (expense > 0) {
                rowData.add("출금: " + expense);
                runningBalance -= expense;
            } else {
                rowData.add("");
            }

            rowData.add(String.valueOf(runningBalance));

            rowData.add(income > 0 ? "-" : transaction.isSettlement() ? "Y" : "N");

            addRow(list, rowData, i, String.join(", ", fullMemberEmails));
        }

        addEventButton = new JButton("+ Add Event");
        addEventButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        addEventButton.addActionListener(this);
        addEventButton.setBackground(Color.WHITE);
        addEventButton.setBorder(new LineBorder(Color.WHITE));

        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridx = 0;
        buttonConstraints.gridy = transactions.size();
        buttonConstraints.gridwidth = GridBagConstraints.REMAINDER;
        buttonConstraints.anchor = GridBagConstraints.CENTER;
        buttonConstraints.weightx = 1.0;
        buttonConstraints.weighty = 0.1;
        list.add(addEventButton, buttonConstraints);

        GridBagConstraints spacingConstraints = new GridBagConstraints();
        spacingConstraints.gridx = 0;
        spacingConstraints.gridy = transactions.size() + 1;
        spacingConstraints.weightx = 1.0;
        spacingConstraints.weighty = 1.0;
        list.add(new JLabel(), spacingConstraints);

        return list;
    }

    private void addRow(JPanel panel, ArrayList<String> data, int rowIndex, String tooltipText) {
        GridBagConstraints con = new GridBagConstraints();
        con.gridy = rowIndex;
        con.weighty = 0;
        con.anchor = GridBagConstraints.CENTER;
        con.insets = new Insets(0, 5, 0, 5);

        double[] columnWeights = {1.5, 3.0, 3.0, 1.8, 1.2, 1.0};
        int[] labelWidths = {200, 100, 200, 150, 150, 200};

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
            return name + "(" + maskedUsername + "@" + maskedDomain + ")";
        } else {
            return name;
        }
    }

    public JButton getAddEventButton() {
        return addEventButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addEventButton) {
            new AddEvent(g, dat);
        }
    }
}
