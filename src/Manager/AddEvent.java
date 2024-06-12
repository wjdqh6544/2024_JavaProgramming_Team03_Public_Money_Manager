package Manager;

import Entity.abs_Member;
import Entity.Group;
import Exception.NotAllowNegativeException;
import Exception.NotFoundException;
import Exception.PermissionException;
import Controller.data;
import Start.icon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Lee, Daeun
 */

public class AddEvent extends JFrame implements ActionListener {
    private JComboBox<Integer> yearComboBox;
    private JComboBox<Integer> monthComboBox;
    private JComboBox<Integer> dayComboBox;
    private JTextField eventField;
    private JTextField incomeField;
    private ArrayList<JCheckBox> memberCheckboxes;
    private String savedEventText;

    private JButton depositButton;
    private JButton withdrawButton;
    private boolean isDeposit = false;

    private data dat = null;
    private Group g;

    public AddEvent(Group g, data data) {
        this.dat = data;
        this.g = g;

        setTitle("Add Event");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        this.setIconImage(icon.geticon());

        JPanel aPanel = new JPanel();
        aPanel.setBackground(Color.LIGHT_GRAY);
        aPanel.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        JLabel title = new JLabel("Add Event");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        titlePanel.add(title, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Details"));

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel dateLabel = new JLabel("날짜");
        datePanel.add(dateLabel);

        yearComboBox = new JComboBox<>();
        monthComboBox = new JComboBox<>();
        dayComboBox = new JComboBox<>();

        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();
        int currentDay = now.getDayOfMonth();

        for (int i = now.getYear(); i > now.getYear() - 65; i--) {
            yearComboBox.addItem(i);
        }
        for (int i = 1; i <= 12; i++) {
            monthComboBox.addItem(i);
        }

        updateDays();

        yearComboBox.setSelectedItem(currentYear);
        monthComboBox.setSelectedItem(currentMonth);
        dayComboBox.setSelectedItem(currentDay);

        monthComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateDays();
            }
        });

        yearComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updateDays();
            }
        });


        datePanel.add(yearComboBox);
        datePanel.add(new JLabel("년  "));
        datePanel.add(monthComboBox);
        datePanel.add(new JLabel("월  "));
        datePanel.add(dayComboBox);
        datePanel.add(new JLabel("일"));

        JPanel eventLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel eventLabel = new JLabel("이벤트 이름");
        eventLabelPanel.add(eventLabel);

        eventField = new JTextField();
        eventField.setPreferredSize(new Dimension(800, 30));

        JPanel incomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel incomeLabel = new JLabel("입출금액 입력");
        incomePanel.add(incomeLabel);

        incomeField = new JTextField();
        incomeField.setPreferredSize(new Dimension(300, 30));
        incomePanel.add(incomeField);

        depositButton = new JButton("입금");
        withdrawButton = new JButton("출금");
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        incomePanel.add(depositButton);
        incomePanel.add(withdrawButton);

        JPanel memberLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel memberLabel = new JLabel("멤버 체크 리스트");
        memberLabelPanel.add(memberLabel);

        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
        memberCheckboxes = new ArrayList<>();

        TreeMap<String, abs_Member> members = dat.getAllMemberOfGroup();
        if (members != null) {
            for (abs_Member member : members.values()) {
                JCheckBox checkBox = new JCheckBox(member.getName() + "(" + member.getEmail() + ")");
                memberCheckboxes.add(checkBox);
                memberPanel.add(checkBox);
            }
        } else {
            throw new NullPointerException("Member list is null");
        }

        JScrollPane memberScrollPane = new JScrollPane(memberPanel);
        memberScrollPane.setPreferredSize(new Dimension(800, 300));
        memberScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        memberScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton saveButton = new JButton("Save Event");
        saveButton.setPreferredSize(new Dimension(800, 70));
        saveButton.addActionListener(this);

        centerPanel.add(datePanel);
        centerPanel.add(eventLabelPanel);
        centerPanel.add(eventField);
        centerPanel.add(incomePanel);
        centerPanel.add(memberLabelPanel);
        centerPanel.add(memberScrollPane);

        aPanel.add(titlePanel, BorderLayout.NORTH);
        aPanel.add(centerPanel, BorderLayout.CENTER);
        aPanel.add(saveButton, BorderLayout.SOUTH);

        add(aPanel);

        setVisible(true);
    }

    private void updateDays() {
        if (yearComboBox.getSelectedItem() != null && monthComboBox.getSelectedItem() != null) {
            int selectedYear = (int) yearComboBox.getSelectedItem();
            int selectedMonth = (int) monthComboBox.getSelectedItem();
            int daysInMonth = getDaysInMonth(selectedYear, selectedMonth);

            dayComboBox.removeAllItems();
            for (int i = 1; i <= daysInMonth; i++) {
                dayComboBox.addItem(i);
            }
        }
    }

    private int getDaysInMonth(int year, int month) {
        switch (month) {
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("입금")) {
            isDeposit = true;
            depositButton.setEnabled(false);
            withdrawButton.setEnabled(true);
        } else if (action.equals("출금")) {
            isDeposit = false;
            depositButton.setEnabled(true);
            withdrawButton.setEnabled(false);
        } else if (action.equals("Save Event")) {
            if (depositButton.isEnabled() && withdrawButton.isEnabled()) {
                JOptionPane.showMessageDialog(null, "입출금을 선택해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            savedEventText = eventField.getText();
            if (savedEventText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "이벤트명을 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int amount;
            try {
                amount = Integer.parseInt(incomeField.getText());
                if (amount <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "금액을 양수로 입력해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            ArrayList<abs_Member> selectedMembers = new ArrayList<>();

            for (JCheckBox checkBox : memberCheckboxes) {
                if (checkBox.isSelected()) {
                    String str = checkBox.getText();
                    StringTokenizer s = new StringTokenizer(str, "()");
                    String memberName = s.nextToken();
                    String memberEmail = s.nextToken();
                    abs_Member member = dat.findMemberByNameAndEmail(memberName, memberEmail);
                    selectedMembers.add(member);
                }
            }

            if (selectedMembers.isEmpty()) {
                JOptionPane.showMessageDialog(null, "멤버를 선택해주세요.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int year = (Integer) yearComboBox.getSelectedItem();
            int month = (Integer) monthComboBox.getSelectedItem();
            int day = (Integer) dayComboBox.getSelectedItem();

            int lastBalance = g.getBalance();
            int totalExpense;

            if (isDeposit) {
                totalExpense = lastBalance + amount;
            } else {
                if (lastBalance < amount) {
                    JOptionPane.showMessageDialog(null, "잔액이 부족합니다.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                totalExpense = lastBalance - amount;
            }

            try {
                dat.addTransactionHistory(year, month, day, savedEventText, isDeposit ? amount : 0, isDeposit ? 0 : amount, selectedMembers);
                g.setBalance(totalExpense);

                JOptionPane.showMessageDialog(null, "이벤트가 성공적으로 저장되었습니다.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (NotFoundException | NotAllowNegativeException | PermissionException ex) {
                JOptionPane.showMessageDialog(null, "이벤트를 저장하는 도중 오류가 발생했습니다: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
