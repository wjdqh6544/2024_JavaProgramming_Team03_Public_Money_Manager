package Home;

import static Start.Constants.height;
import static Start.Constants.width;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import Entity.Transaction;
import Entity.abs_Member;
import Exception.DuplicatedException;
import Exception.NotFoundException;
import Exception.PermissionException;
import Group.Group;
import Start.Constants;
import Controller.data;
import Start.icon;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Roh, Seongmin
 */
public class New_Group  extends JFrame implements ActionListener, Constants{
	data dat = null;
	JLabel toolong = null;
	JLabel invalid = null;
	JLabel warnin = null;
	JTextField name = null;
	JTextField inbal = null;
	public New_Group(data data){
		this.dat = data;
		setTitle("New Group");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(width, height);
		setResizable(false);
		this.setIconImage(icon.geticon());
		JPanel wind = new JPanel();
		wind.setLayout(new GridLayout(6,1));
		
		JLabel new_group = new JLabel("New Group");
	
		new_group.setBorder(BorderFactory.createEmptyBorder(40 , 140 , 0 , 0));
		new_group.setFont(new Font("SansSerif", Font.BOLD, 50));
		
		
		JPanel group_name = new JPanel();
		group_name.setLayout(new GridLayout(2,1));
		JLabel gn = new JLabel("Group Name");
		gn.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		JPanel n = new JPanel();
		n.setPreferredSize(new Dimension(700,50));
		n.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolong = new JLabel("그룹명은 " + address_limit + "자 이하로 작성하여 주십시오");
		toolong.setForeground(Color.RED);
		toolong.setVisible(false);
		name = new JTextField(groupname_limit);
		name.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(name.getText().length() > address_limit) toolong.setVisible(true);
				else if (name.getText().length() <= address_limit) toolong.setVisible(false);
				
			}
		});
		name.setPreferredSize(new Dimension(1000, 20));
		n.add(name);
		n.add(toolong);
		group_name.setBorder(BorderFactory.createEmptyBorder(0 , 170 , 0 , 80));
		group_name.add(gn);
		group_name.add(n);
		
		
		JPanel repre = new JPanel();
		repre.setBorder(BorderFactory.createEmptyBorder(0 , 170 , 0 , 80));
		JLabel r = new JLabel("Representative");
		r.setFont(new Font("SansSerif", Font.BOLD, 25));
		JLabel myname = new JLabel("Default");
		//getter
		myname.setFont(new Font("SansSerif", Font.PLAIN, 20));
		myname.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
		repre.setLayout(new GridLayout(2,1));
		repre.add(r);
		repre.add(myname);
		
		JPanel balance = new JPanel();
		balance.setBorder(BorderFactory.createEmptyBorder(0,170,0,0));
		balance.setLayout(new GridLayout(2,1));
		JPanel balt = new JPanel();
		balt.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel bal = new JLabel("Balance");
		bal.setFont(new Font("맑은 고딕", Font.BOLD, 25));/*
		NumberFormatter range = new NumberFormatter();
		range.setMinimum(new Integer(-999999999));
		range.setMaximum(new Integer(999999999));
		range.setValueClass(Integer.class);
		JFormattedTextField inbal = new JFormattedTextField(range);
		inbal.setColumns(15);*/
		inbal = new JTextField(15);
		inbal.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(inbal.getText().length() > balance_limit || 
						!inbal.getText().matches("[0-9]+")) {
					warnin.setVisible(true);
				}
				else if(inbal.getText().length()<= balance_limit &&
						inbal.getText().matches("[0-9]+")) {
					warnin.setVisible(false);
				}
				if(inbal.getText().equals("")) warnin.setVisible(false);
			}});
		JLabel won = new JLabel("원");
		warnin = new JLabel("     예산이 적절하지 않거나 너무 많습니다");
		warnin.setForeground(Color.RED);
		warnin.setVisible(false);
		balt.add(inbal);
		balt.add(won);
		balt.add(warnin);
		balance.add(bal);
		balance.add(balt);
		
		JPanel mw = new JPanel();
		mw.setLayout(new BorderLayout());
		
		JPanel make = new JPanel();
		make.setLayout(new FlowLayout(FlowLayout.LEFT));
		make.setBorder(BorderFactory.createEmptyBorder(0,170,0,0));
		JButton mg = new JButton("Make a Group");
		mg.setBackground(Color.BLACK);
		mg.setForeground(Color.WHITE);
		mg.addActionListener(this);
		mg.setActionCommand("TEST");
		
		make.add(mg);
		
		JPanel Warning = new JPanel();
		Warning.setLayout(new BorderLayout());
		Warning.setBorder(BorderFactory.createEmptyBorder(0,170,0,0));
		invalid = new JLabel("이미 있는 그룹입니다.");
		invalid.setForeground(Color.RED);
		Warning.add(invalid, BorderLayout.NORTH);
		invalid.setVisible(false);
		mw.add(make, BorderLayout.CENTER); mw.add(Warning, BorderLayout.NORTH);
		//JOptionPane.showMessageDialog(null, "되..되나?");
		wind.add(new_group);
		wind.add(group_name);
		wind.add(repre);
		wind.add(balance);
		wind.add(mw);
		//add(blank);
		
		add(wind);
		wind.setBorder(BorderFactory.createEmptyBorder(40,0,0,0));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    String command = e.getActionCommand();
	    if(command.equals("TEST")) {
	        try{
	            this.dat.createGroup(name.getText());
	            Entity.Group g = dat.findGroupByName(name.getText());
	            g.setBalance(0);
	            
	            LocalDate now = LocalDate.now();
	            ArrayList<abs_Member> repre = new ArrayList<abs_Member>();
	            repre.add(dat.getUser());
	            int initialBalance = Integer.parseInt(inbal.getText());
	            if (initialBalance < 0) {
	                JOptionPane.showMessageDialog(this, "초기 자금은 양수값이어야 합니다.", "Error", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            g.setBalance(initialBalance);
	            g.addTransaction(new Transaction(now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
	                    "초기 자금", initialBalance, 0, repre));
	            Group a = new Group(g, dat);
	            a.setVisible(true);
	            dispose();
	        } catch(DuplicatedException e1) {
	            invalid.setVisible(true);
	        } catch(PermissionException | NotFoundException e2) {
	        }
	    }   
	}
}