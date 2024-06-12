package Login;

import java.awt.BorderLayout;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Roh, Seongmin
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import Controller.MemberController;
import Exception.NotFoundException;
import Exception.ObjectSaveException;
import Home.Home;
import Start.Constants;
import Controller.data;
import Start.icon;
import Start.pbmDemo;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Roh, Seongmin
 */
public class Start extends JFrame implements ActionListener, Constants{

	private data dat = null;
	
	private static boolean noname = true;
	private static boolean noid = true;
	private static boolean noemail = true;
		
	JTextField name = null;
	JTextField id = null;
	JTextField address = null;
	
	JLabel wrong = null;
	JLabel toolong = null;
	JMenu choose = null;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			action(e);
		}catch(Error e2) {
			System.out.println("Action error");
		}
	}
	
	public Start(data data) {
		this.dat = data;
		//창 설정
		setTitle("Public Balance Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		//addWindowListener(new CheckOnExit());
		setResizable(false);
		//Icon
		this.setIconImage(icon.geticon());
		//GridBagLayout
		GridBagLayout grid = new GridBagLayout();
		GridBagConstraints con = new GridBagConstraints();
		setLayout(grid);
		con.fill = GridBagConstraints.BOTH;
		//Caps_Lock on여부 확인
		JLabel capslock = new JLabel();
		capslock.setText("Caps Lock이 켜져있습니다.");
		capslock.setForeground(Color.RED);
		capslock.setVisible(false);
		con.gridx = 1; con.gridy = 7;
		add(capslock,con);
		//글자 길이 경고문
		toolong = new JLabel();
		toolong.setText("입력 정보가 너무 깁니다. 이름은 "+name_limit+"자 이하, 아이디는 "+
							id_limit+"자 이하, 주소는 "+address_limit+"자 이하로 입력해주십시오.");
		toolong.setForeground(Color.RED);
		toolong.setVisible(false);
		con.gridx = 1; con.gridy = 5;
		add(toolong, con);
		//폰트 정의
		Font font_Title = new Font("맑은 고딕", Font.BOLD, 50);
		Font font_Team = new Font("SansSerif", Font.ITALIC, 20);
		Font font_Button = new Font("맑은 고딕", Font.BOLD, 10);
		//타이틀 set
		JLabel title = new JLabel("공금 관리 프로그램");
			//title.setForeground(Color.WHITE);
		title.setFont(font_Title);
		JLabel team = new JLabel("Java programming Team3");
			//team.setForeground(Color.WHITE);
		team.setFont(font_Team);
		JButton new_user = new JButton("처음이신가요?");
		new_user.setActionCommand("New_Profile");
		new_user.addActionListener(this);
		new_user.setBackground(Color.BLACK);
		new_user.setForeground(Color.WHITE);
		new_user.setFont(font_Button);
		//타이틀 레이아웃
		JPanel TITLE = new JPanel();
		TITLE.add(title, BorderLayout.CENTER);
		JPanel TEAM = new JPanel();
		TEAM.add(team, BorderLayout.CENTER);
		JPanel NEW_BUTTON = new JPanel();
		NEW_BUTTON.add(new_user, BorderLayout.CENTER);
		
		con.gridx = 1; con.gridy = 0;
		add(TITLE, con);
		con.gridx = 1; con.gridy = 1;
		add(TEAM, con);
		con.gridx = 1; con.gridy = 2;
		add(NEW_BUTTON, con);
		
		//입력 지점
		name = new JTextField(name_limit);
		name.setForeground(Color.GRAY);
		name.setText("이름을 입력하세요");
		name.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if(noname) {
					name.setText(""); 
					noname = false;
					name.setForeground(Color.BLACK);
					}
				capslock.setVisible(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK));
			}

			@Override
			public void keyPressed(KeyEvent e) {
					
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(name.getText().length() > name_limit) toolong.setVisible(true);
				else if(ifok()) toolong.setVisible(false);
			}
			
		});
		id = new JTextField(id_limit);
		id.setForeground(Color.GRAY);
		id.setText("이메일을 입력하세요");
		id.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if(noid) {
					id.setText(""); 
					id.setForeground(Color.BLACK);
					noid = false;}
				
				capslock.setVisible(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK));
				if(id.getText().length() > id_limit) toolong.setVisible(true);
				else if(ifok()) toolong.setVisible(false);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
		});
		address = new JTextField(address_limit);
		address.setForeground(Color.GRAY);
		address.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if(noemail) {
					address.setText(""); 
					noemail = false;
					choose.setText("직접 입력"); 
					address.setForeground(Color.BLACK);
				}
				capslock.setVisible(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK));
				if(address.getText().length() > address_limit) toolong.setVisible(true);
				else if(ifok()) toolong.setVisible(false);
			}

			@Override
			public void keyPressed(KeyEvent e) {
					
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
		});
		address.setText("manager.com");
		choose = new JMenu("선택하세요");
		//choose.setPreferredSize(new Dimension(77, 15));
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
		setJMenuBar(bar);
		
		JButton next = new JButton("확인");
		next.addActionListener(this);
		next.setBackground(Color.BLACK);
		next.setForeground(Color.WHITE);
		next.setFont(font_Button);
		
		JPanel INPUTS = new JPanel();
		JPanel emails = new JPanel();
		JLabel at = new JLabel("@");
		
		emails.add(id);
		emails.add(at);
		emails.add(address);
		emails.add(bar);
		
		INPUTS.setLayout(new FlowLayout());
		INPUTS.add(name);
		INPUTS.add(emails);
		INPUTS.add(next);
		
	
		
		con.gridx = 1; con.gridy = 4;
		add(INPUTS, con);
		//계정을 찾을 수 없을 시
		wrong = new JLabel();
		wrong.setText("없는 사용자거나 이름과 이메일이 매칭되지 않습니다. 다시 확인해주십시오.");
		wrong.setForeground(Color.RED);
		wrong.setVisible(false);
		con.gridx = 1; con.gridy = 6;
		add(wrong, con);
		
		
	}
	
	private class Exit implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			try {
				dat.saveAndUploadFile();
				
			} catch (ObjectSaveException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"저장에 실패하였습니다.", "Fail to Save Object", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
				
			} catch (JSchException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"저장에 실패하였습니다.", "Fail to Save File", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (SftpException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"저장에 실패하였습니다.", "Fail to Save Upload", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,"저장에 실패하였습니다.", "Fail to Save IO", JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
			}
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}}
	
	public void action(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("google")) {
			address.setText("gmail.com");
			choose.setText("google");
			address.setForeground(Color.BLACK);
			noemail = true;
			toolong.setVisible(false);
		}else if(action.equals("naver")) { 
			address.setText("naver.com");
			choose.setText("naver");
			address.setForeground(Color.BLACK);
			noemail = true;
			if(ifok())toolong.setVisible(false);
		}else if(action.equals("knu")) {
			address.setText("knu.ac.kr");
			choose.setText("knu");
			address.setForeground(Color.BLACK);
			noemail = true;
			if(ifok())toolong.setVisible(false);
		}else if(action.equals("daum")) {
			address.setText("daum.net");
			choose.setText("daum");
			address.setForeground(Color.BLACK);
			noemail = true;
			if(ifok())toolong.setVisible(false);
		}else if(action.equals("kakao")) {
			address.setText("kakao.com");
			choose.setText("kakao");
			address.setForeground(Color.BLACK);
			noemail = true;
			if(ifok())toolong.setVisible(false);
		}else if(action.equals("직접 입력")) {
			address.setText("");
			choose.setText("직접 입력");
			address.setForeground(Color.BLACK);
			noemail = false;
			if(ifok())toolong.setVisible(false);
		}else if(action.equals("New_Profile")) {
			New_Profile n = new New_Profile(this.dat);
			n.setVisible(true);
		}else if(action.equals("확인")) {
			try{
				dat.login(name.getText(), id.getText()+"@"+address.getText());
				Home h = new Home(this.dat);
				h.setVisible(true);
				this.dispose();
			}catch(NotFoundException e1) {
				wrong.setVisible(true);
			}
			
		}
		else {
			wrong.setVisible(true);
		}

		
	}
	private boolean ifok() {
		 return (name.getText().length()<= name_limit || noname)&&
				(id.getText().length() <= id_limit || noid)&&
				(address.getText().length() <= address_limit || noemail);
	}
	}
