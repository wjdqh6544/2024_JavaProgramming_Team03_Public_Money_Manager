package Home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

import Exception.ObjectSaveException;
import Login.Start;
import Start.Constants;
import Controller.data;
import Start.icon;
import Start.pbmDemo;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Roh, Seongmin
 */
public class Home extends JFrame implements ActionListener, Constants{
	private data dat = null;
	
	JPanel sample = null;
	JPanel CENTER = null;
	JButton home = null;
	JButton profile = null;
	JButton grouplist = null;
	JButton add_group = null;
	JButton logout = null;
	Color BLUE_GRAY = new Color(230, 230, 235);
	
	JPanel cen = null;
	
	boolean changed = false;
	String lastcommand = null;
	
	public Home(data data) {
		this.dat = data;
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setResizable(false);
		setLayout(new BorderLayout());
		addWindowListener(new Exit());
		//상단 바
		JPanel up = new JPanel();
		up.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY)));
		this.setIconImage(icon.geticon());
		
		JLabel window_name = new JLabel("Home");
		window_name.setOpaque(true);
		window_name.setFont(new Font("SansSerif", Font.PLAIN, 18));
		window_name.setBackground(Color.WHITE);
		window_name.setHorizontalAlignment(JLabel.CENTER);
		
		up.setLayout(new BorderLayout());
		up.add(window_name, BorderLayout.CENTER);
		//up.add(prev, BorderLayout.WEST); 
		
		add(up, BorderLayout.NORTH);
		
		CENTER = new JPanel();
		CENTER.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.fill = GridBagConstraints.BOTH;
		
		//좌측 메뉴
		JPanel LEFT = new JPanel();
		LEFT.setBackground(Color.WHITE);
		LEFT.setLayout(new GridLayout(8,1));
		
		JLabel AccountManager = new JLabel("  Account Manager");
		AccountManager.setFont(new Font("SansSerif", Font.BOLD, 25));
		
		JPanel MENU = new JPanel();
		MENU.setLayout(new GridLayout(3,1));
		MENU.setBackground(Color.WHITE);
		JLabel menu = new JLabel("  Menu");
		menu.setFont(new Font("SansSerif", Font.BOLD, 17));
		home = new JButton("   Home");
		home.setActionCommand("Home");
		home.addActionListener(this);
		home.setHorizontalAlignment(SwingConstants.LEFT);
		home.setFont(new Font("SansSerif", Font.BOLD, 13));
		selected(home);
		profile = new JButton("  Profile");
		profile.setActionCommand("Profile");
		profile.addActionListener(this);
		profile.setHorizontalAlignment(SwingConstants.LEFT);
		profile.setFont(new Font("Sanserif", Font.BOLD, 13));
		profile.setBackground(Color.WHITE);
		
		JPanel BLANK = new JPanel();
		BLANK.setBackground(Color.WHITE);
		JPanel BLANK2 = new JPanel();
		BLANK2.setBackground(Color.WHITE);
		JPanel BLANK3 = new JPanel();
		BLANK3.setBackground(Color.WHITE);
		JPanel BLANK4 = new JPanel();
		BLANK4.setBackground(Color.WHITE);

		JPanel GROUP = new JPanel();
		GROUP.setLayout(new GridLayout(3,1));
		GROUP.setBackground(Color.WHITE);
		JLabel group = new JLabel("  Group");
		group.setFont(new Font("SansSerif", Font.BOLD, 17));
		grouplist = new JButton("   Grouplists");
		grouplist.setActionCommand("Grouplists");
		grouplist.addActionListener(this);
		grouplist.setHorizontalAlignment(SwingConstants.LEFT);
		grouplist.setFont(new Font("Sanserif", Font.BOLD, 13));
		grouplist.setBackground(Color.WHITE);
		
		add_group = new JButton("  + New Group");
		add_group.setActionCommand("New");
		add_group.addActionListener(this);
		add_group.setFont(new Font("Sanserif", Font.BOLD, 17));
		add_group.setHorizontalAlignment(SwingConstants.LEFT);
		add_group.setBackground(Color.WHITE);
		add_group.setBorder(new TitledBorder(new LineBorder(Color.WHITE)));
		
		
		JPanel btn = new JPanel();
		btn.setBackground(Color.WHITE);
		btn.setLayout(new FlowLayout(FlowLayout.LEFT));
		logout = new JButton("Log out");
		logout.addActionListener(this);
		logout.setBackground(Color.BLACK);
		logout.setForeground(Color.WHITE);
		btn.add(logout);
		
		MENU.add(menu);
		MENU.add(home);
		MENU.add(profile);
		GROUP.add(group);
		GROUP.add(grouplist);
		GROUP.add(add_group);
		
		LEFT.add(AccountManager);
		LEFT.add(MENU);
		LEFT.add(BLANK);
		LEFT.add(GROUP);
		LEFT.add(BLANK2);
		LEFT.add(BLANK3);
		LEFT.add(BLANK4);
		LEFT.add(btn);
		
		con.weightx = 0.05; con.weighty = 0;
		CENTER.add(LEFT, con);
		
		sample = new JPanel();
		con.weightx = 2; con.weighty = 0;
		sample.setBackground(Color.WHITE);
		sample.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY)));
		
		CENTER.add(sample, con);
		
		add(CENTER, BorderLayout.CENTER);
		
		this.home_window();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("Home")) {
			unselected();
			selected(home);
			this.home_window();
		}
		else if(action.equals("Profile")) {
			unselected();
			selected(profile);
			this.profile_window();
		}
		else if(action.equals("Grouplists")) {
			unselected();
			selected(grouplist);
			this.grouplists();
		}
		else if(action.equals("New")) {
			changed = true;
			New_Group nw = new New_Group(this.dat);
			nw.setVisible(true);
		}
		else if(action.equals("Log out")) {
			Start s = new Start(this.dat);
			s.setVisible(true);
			this.dispose();
		}
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public void selected(JButton s) {
		s.setBackground(BLUE_GRAY);
		s.setBorder(new EmptyBorder(5, 17, 5 ,3));
	}
	public void unselected(JButton s) {
		s.setBackground(Color.WHITE);
		s.setBorder(new JButton().getBorder());
	}
	public void unselected() {
		unselected(home);
		unselected(profile);
		unselected(grouplist);
	}
	public void home_window() {
		if(cen != null)sample.remove(cen);
		home_window hw = new home_window(this.dat);
		cen = hw.cen;
		JButton prof = hw.prof;
		JButton plus = hw.plus;
		prof.addActionListener(this);
		plus.addActionListener(this);
		sample.setLayout(new BorderLayout());
		sample.setBorder(BorderFactory.createEmptyBorder(10 , 30 , 30 , 30));
		sample.add(cen, BorderLayout.CENTER);	
	
	}
	public void profile_window() {
		if(cen != null)sample.remove(cen);
		profile_window pw = new profile_window(this.dat);
		cen = pw.cen;
		sample.add(cen, BorderLayout.CENTER);
		
	}
	public void grouplists() {
		if(cen != null)sample.remove(cen);
		grouplists grl = new grouplists(550, this.dat);
		sample.setLayout(new BorderLayout());
		sample.setBorder(BorderFactory.createEmptyBorder(10 , 30 , 30 , 30));
		cen = grl.groups;
		JButton plus = grl.plus;
		plus.addActionListener(this);
		sample.add(cen, BorderLayout.CENTER);
	}
}
