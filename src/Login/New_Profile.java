package Login;

import Controller.MemberController;
import Exception.DuplicatedException;
import Start.Constants;
import Controller.data;
import Start.icon;
import Start.pbmDemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Roh, Seongmin
 */
public class New_Profile extends JFrame implements ActionListener, Constants{
	
	private data dat = null;
	
	JTextField username = null;
	JTextField id = null;
	JTextField email = null;
	
	JComboBox YY = null;
	JComboBox MM = null;
	JComboBox DD = null;
	
	JMenu choose = null;
	JLabel toolong = null;
	JLabel invalid = null;
	//JLabel valid = null;
	
	@SuppressWarnings("rawtypes")
	public New_Profile(data data) {
		this.dat = data;
		setTitle("New Profile");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(width, height);
		setResizable(false);
		setLayout(new BorderLayout());
		this.setIconImage(icon.geticon());
		//상단부
		JPanel up = new JPanel();
		
		JButton prev = new JButton("<");
		prev.setActionCommand("Back");
		prev.addActionListener(this);
		prev.setBackground(Color.WHITE);
		prev.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		JLabel window_name = new JLabel("Profile");
		window_name.setOpaque(true);
		window_name.setFont(new Font("SansSerif", Font.PLAIN, 18));
		window_name.setBackground(Color.WHITE);
		window_name.setHorizontalAlignment(JLabel.CENTER);
		
		up.setLayout(new BorderLayout());
		up.add(window_name, BorderLayout.CENTER);
		up.add(prev, BorderLayout.WEST); 
		
		add(up, BorderLayout.NORTH);
		
		//좌우 여백
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		add(left, BorderLayout.WEST);
		add(right, BorderLayout.EAST);
		
		//중심부
		JPanel profile = new JPanel();
		profile.setLayout(new GridLayout(10, 1));
		//상단부 여백
		JPanel upper = new JPanel();
		profile.add(upper);
		
		JPanel T = new JPanel();
		T.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel title = new JLabel("Profile");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		T.add(title);
		profile.add(T);
		
		JPanel U = new JPanel();
		U.setLayout(new GridLayout(2, 1));
		JPanel leftsub1 = new JPanel();
		leftsub1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel subtitle1 = new JLabel("Username");
		subtitle1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		leftsub1.add(subtitle1);
		username = new JTextField(name_limit);
		username.addKeyListener(new KeyListener() {

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
				if(username.getText().length() > name_limit) toolong.setVisible(true);
				else if(ifok()) toolong.setVisible(false);
			//	valid.setVisible(false);
			}
			
		});
		JPanel leftuser = new JPanel();
		leftuser.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftuser.add(username);
		U.add(leftsub1);
		U.add(leftuser);
		profile.add(U);
		
		JPanel E = new JPanel();
		E.setLayout(new GridLayout(2, 1));
		JPanel leftsub2 = new JPanel();
		leftsub2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel subtitle2 = new JLabel("Email");
		subtitle2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		leftsub2.add(subtitle2);
		JPanel address = new JPanel();
		address.setLayout(new FlowLayout(FlowLayout.LEFT));
		id = new JTextField(id_limit);
		id.addKeyListener(new KeyListener() {

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
				if(id.getText().length() > id_limit) toolong.setVisible(true);
				else if(ifok()) toolong.setVisible(false);
				//valid.setVisible(false);
			}
		});
		JLabel at = new JLabel("@");
		email = new JTextField(address_limit);
		email.addKeyListener(new KeyListener() {

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
				if(email.getText().length() > address_limit) toolong.setVisible(true);
				else if(ifok()) toolong.setVisible(false);
				//valid.setVisible(false);
			}
		});
		choose = new JMenu("선택하세요");
		JMenuBar bar = new JMenuBar();
		
		JMenuItem google = new JMenuItem("google");
		google.addActionListener(this);
		choose.add(google);
		JMenuItem naver = new JMenuItem("naver");
		naver.addActionListener(this);
		choose.add(naver);
		JMenuItem knu = new JMenuItem("knu");
		knu.addActionListener(this);
		choose.add(knu);
		JMenuItem daum = new JMenuItem("daum");
		daum.addActionListener(this);
		choose.add(daum);
		JMenuItem kakao = new JMenuItem("kakao");
		kakao.addActionListener(this);
		choose.add(kakao);
		JMenuItem others = new JMenuItem("직접 입력");
		others.addActionListener(this);
		choose.add(others);
		
		bar.add(choose);
		address.add(id);
		address.add(at);
		address.add(email);
		address.add(bar);
		E.add(leftsub2);
		E.add(address);
		profile.add(E);
		
		JPanel B = new JPanel();
		B.setLayout(new GridLayout(2, 1));
		JPanel leftsub3 = new JPanel();
		leftsub3.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel subtitle3 = new JLabel("Birth");
		subtitle3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		leftsub3.add(subtitle3);
		JPanel birth = new JPanel();
		birth.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		LocalDate now = LocalDate.now();
				
		YY = new JComboBox();
		MM = new JComboBox();
		DD = new JComboBox();
		
		for(int i = now.getYear(); i  > now.getYear() - 65; i--) {
			YY.addItem(i);
		}
		for(int i = 1; i  <= 12 ; i++) {
			MM.addItem(i);
		}
		for(int i = 1; i <= 31 ; i++) {
			DD.addItem(i);
		}
		
		JLabel year = new JLabel("년  ");
		YY.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				datecalculate();
			}
			
		});
		MM.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				datecalculate();
			}	
			
		});
		DD.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		
		JLabel month = new JLabel("월  ");
		JLabel day = new JLabel("일");
		year.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		month.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		day.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		
		birth.add(YY); birth.add(year);
		birth.add(MM); birth.add(month);
		birth.add(DD); birth.add(day);
		B.add(leftsub3);
		B.add(birth);
		profile.add(B);
		
		JPanel inproper = new JPanel();
		inproper.setLayout(new GridLayout(3, 1));
		JPanel inproper1 = new JPanel();
		inproper1.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolong = new JLabel("입력 정보가 너무 깁니다. 이름은 "+name_limit+"자 이하, 아이디는 "+
				id_limit+"자 이하, 주소는 "+address_limit+"자 이하로 입력해주십시오.");
		toolong.setForeground(Color.RED);
		toolong.setVisible(false);
		inproper1.add(toolong);
		inproper.add(inproper1);
		JPanel inproper2 = new JPanel();
		inproper2.setLayout(new FlowLayout(FlowLayout.LEFT));
		invalid = new JLabel("이미 존재하는 계정이나 부적절한 입력입니다. 다시 확인해주십시오.");
		invalid.setForeground(Color.RED);
		invalid.setVisible(false);
		inproper2.add(invalid);
		inproper.add(inproper2);		
		JPanel proper = new JPanel();
		proper.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		inproper.add(proper);
		profile.add(inproper);
		
		JPanel btn = new JPanel();
		btn.setLayout(new FlowLayout(FlowLayout.LEFT));
		JButton save = new JButton("Save changes");
		save.addActionListener(this);
		save.setBackground(Color.BLACK);
		save.setForeground(Color.WHITE);
		btn.add(save);
		profile.add(btn);
		
		
		
		JPanel layout = new JPanel();
		layout.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.fill = GridBagConstraints.BOTH;
		JPanel blank1 = new JPanel();
		con.weightx = 0.1; con.weighty = 1.0;
		layout.add(blank1, con);
		con.weightx = 1.0;
		layout.add(profile, con);
		
		add(layout, BorderLayout.CENTER);

		

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("Back")) {
			this.dispose();
		}
		else if(action.equals("google")) {
			email.setText("gmail.com");
			choose.setText("google");
			if(ifok())toolong.setVisible(false);
		}else if(action.equals("naver")) { 
			email.setText("naver.com");
			choose.setText("naver");
			if(ifok())toolong.setVisible(false);
		}else if(action.equals("knu")) {
			email.setText("knu.ac.kr");
			choose.setText("knu");
			if(ifok())toolong.setVisible(false);
		}else if(action.equals("daum")) {
			email.setText("daum.net");
			choose.setText("daum");
			if(ifok())toolong.setVisible(false);
		}else if(action.equals("kakao")) {
			email.setText("kakao.com");
			choose.setText("kakao");
			if(ifok())toolong.setVisible(false);
		}else if(action.equals("직접 입력")) {
			email.setText("");
			choose.setText("직접 입력");
			if(ifok())toolong.setVisible(false);
		}else if(action.equals("Save changes")) {
			if(isproper()) {
				
				try{
					dat.createMember(username.getText(),
						(int) YY.getSelectedItem(),(int) MM.getSelectedItem(), (int) DD.getSelectedItem(), 
						id.getText()+"@"+email.getText());
					JOptionPane.showMessageDialog(null,"회원가입이 완료되었습니다.", "Complete", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
				}catch(DuplicatedException e1) {
					invalid.setVisible(true);
				}
				
			}
			else {
				invalid.setVisible(true);
			}
		}
	}
	private boolean ifok() {
		 return (username.getText().length()<= name_limit)&&
				(id.getText().length() <= id_limit)&&
				(email.getText().length() <= address_limit);
	}
	private void datecalculate() {
				int a = (int) MM.getSelectedItem();
				if(a == 1 || a == 3 || a == 5 || a == 7 || a == 8 || a == 10 || a == 12) {
					switch(DD.getItemCount()) {
						case 28: DD.addItem(29);
						case 29: DD.addItem(30);
						case 30: DD.addItem(31); 
					}
					    
				}
				else if(a == 4 || a == 6 || a == 9 || a == 11) {
					switch(DD.getItemCount()) {
					case 28: DD.addItem(29);
					case 29: DD.addItem(30); break;
					case 31: DD.removeItem(31); 
					}				
				}
				else {
					int y = (int) YY.getSelectedItem();
					if(( y % 4 == 0 && y / 100 != 0) || (y / 400 == 0)) {
						switch(DD.getItemCount()) {
						case 31: DD.removeItem(31);
						case 30: DD.removeItem(30); break;
						case 28: DD.addItem(29); 
						}
					}
					else {
						switch(DD.getItemCount()) {
						case 31: DD.removeItem(31);
						case 30: DD.removeItem(30);
						case 29: DD.removeItem(29); 
						}
					}
				
			}	
			
	}
	private boolean isproper() {
		return !toolong.isVisible() &&
				(!username.getText().equals("")) &&
				(!id.getText().equals("")) &&
				(!email.getText().equals(""));
	}
	
}
