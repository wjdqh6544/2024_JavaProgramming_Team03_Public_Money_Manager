package Home;

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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Login.New_Profile;
import Controller.data;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Roh, Seongmin
 */
public class profile_window implements ActionListener{
	data dat = null;
	JPanel cen;
	public profile_window(data data) {
		this.dat = data;
		cen = new JPanel();
		cen.setBorder(BorderFactory.createEmptyBorder(30 , 30 , 30 , 30));
		cen.setBackground(Color.WHITE);
		cen.setLayout(new GridLayout(5,1));
		
		JPanel T = new JPanel();
		T.setLayout(new FlowLayout(FlowLayout.LEFT));
		T.setBackground(Color.WHITE);
		JLabel title = new JLabel("Profile");
		title.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		JLabel blank = new JLabel();
		blank.setPreferredSize(new Dimension(30, 50));
		blank.setBackground(Color.WHITE);
		
		T.add(title);
		T.add(blank);
		
		cen.add(T);
		
		JPanel U = new JPanel();
		U.setBackground(Color.WHITE);
		U.setLayout(new GridLayout(2, 1));
		JPanel leftsub1 = new JPanel();
		leftsub1.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftsub1.setBackground(Color.WHITE);
		JLabel subtitle1 = new JLabel("Username");
		subtitle1.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		leftsub1.add(subtitle1);
		JLabel username = new JLabel("Default");
		username.setText(dat.getUser().getName());
		username.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		username.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		username.setPreferredSize(new Dimension(800, 30));
		JPanel leftuser = new JPanel();
		leftuser.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftuser.setBackground(Color.WHITE);
		leftuser.add(username);
		U.add(leftsub1);
		U.add(leftuser);
		cen.add(U);
		
		JPanel E = new JPanel();
		E.setBackground(Color.WHITE);
		E.setLayout(new GridLayout(2, 1));
		JPanel leftsub2 = new JPanel();
		leftsub2.setBackground(Color.WHITE);
		leftsub2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel subtitle2 = new JLabel("Email");
		subtitle2.setBackground(Color.WHITE);
		subtitle2.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		leftsub2.add(subtitle2);
		JPanel address = new JPanel();
		address.setBackground(Color.WHITE);
		address.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel id = new JLabel("default@knu.ac.kr");
		id.setText(dat.getUser().getEmail());
		id.setBorder(BorderFactory.createEmptyBorder(0, 30, 0,0));
		id.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		address.add(id);
		E.add(leftsub2);
		E.add(address);
		cen.add(E);
		
		JPanel B = new JPanel();
		B.setBackground(Color.WHITE);
		B.setLayout(new GridLayout(2, 1));
		JPanel leftsub3 = new JPanel();
		leftsub3.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftsub3.setBackground(Color.WHITE);
		JLabel subtitle3 = new JLabel("Birth");
		subtitle3.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		leftsub3.add(subtitle3);
		JLabel birthday = new JLabel("MM//DD//YY");
		birthday.setText(dat.getUser().getBirthday().toString());
		birthday.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		birthday.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
		birthday.setPreferredSize(new Dimension(800, 30));
		JPanel leftuser3 = new JPanel();
		leftuser3.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftuser3.setBackground(Color.WHITE);
		leftuser3.add(birthday);
		B.add(leftsub3);
		B.add(leftuser3);
		cen.add(B);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if (command.equals("Edit")) {
			New_Profile np = new New_Profile(this.dat);
			np.setVisible(true);
		}
	}
}
