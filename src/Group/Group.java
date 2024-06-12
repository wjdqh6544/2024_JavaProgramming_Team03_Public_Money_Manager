package Group;

import static Start.Constants.height;
import static Start.Constants.width;

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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import Entity.MemberPosition;
import Entity.abs_Member;
import Exception.NotChangePositionException;
import Exception.NotFoundException;
import Exception.PermissionException;
import Manager.Balance_Man;
import Manager.Balance_Mem;
import Start.Constants;
import Controller.data;
import Start.icon;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Roh, Seongmin
 */
public class Group extends JFrame implements ActionListener, Constants{
	data dat = null;
	Entity.Group g;
	public Group(Entity.Group g, data data) {
		this.dat = data;
		this.g = g;
	
		dat.selectGroup(g);
		setTitle(g.getGroupName());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(width, height);
		setResizable(false);
		setLayout(new GridLayout(2,1));
		this.setIconImage(icon.geticon());
		
		JPanel upside = new JPanel();
		upside.setLayout(new GridLayout(2,1));
		
		JPanel title = new JPanel();
		title.setBorder(BorderFactory.createEmptyBorder(0 , 40 , 0 , 50));
		title.setLayout(new GridLayout(2,1));
		JPanel prof = new JPanel();
		prof.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.fill = GridBagConstraints.BOTH;
		JLabel role = new JLabel("Role");
		role.setOpaque(true);
		role.setBackground(Color.BLACK);
		role.setForeground(Color.WHITE);
		role.setFont(new Font("SansSerif", Font.PLAIN, 25));
		role.setPreferredSize(new Dimension(10, 60));
		role.setHorizontalAlignment(JLabel.CENTER);
		JLabel name = new JLabel("Default");
		name.setText(dat.getPositionOfGroup(g).toString());
		name.setOpaque(true);
		name.setBackground(Color.WHITE);
		name.setFont(new Font("SansSerif", Font.PLAIN, 20));
		name.setBorder(BorderFactory.createEmptyBorder(0 , 40 , 0 , 50));
		con.weightx =1; con.weighty = 0;
		prof.add(role, con);
		con.weightx =3; con.weighty = 0;
		prof.add(name, con);
		JPanel grp = new JPanel();
		grp.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel gn = new JLabel("Group Name");
		gn.setText(g.getGroupName());
		JLabel blank = new JLabel();
		blank.setPreferredSize(new Dimension(20, 0));
		gn.setFont(new Font("SansSerif", Font.BOLD, 50));
		JButton Balance = new JButton("Balance");
		Balance.setBackground(Color.BLACK);
		Balance.setForeground(Color.WHITE);
		Balance.addActionListener(this);
		Balance.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		grp.add(gn);
		grp.add(blank);
		grp.add(Balance);
		title.add(prof);
		title.add(grp);
		
		
		JPanel func_member = new JPanel();
		func_member.setBorder(BorderFactory.createEmptyBorder(0 , 70 , 0 , 50));
		func_member.setLayout(new GridLayout(2,1));
		JPanel repre = new JPanel();
		repre.setLayout(new GridLayout(2,1));
		JLabel r = new JLabel("President");
		r.setFont(new Font("SansSerif", Font.BOLD, 20));
		JLabel rep = new JLabel("Default");
		rep.setText(g.getPresident().getName() + "(" + g.getPresident().getEmail() + ")");
		rep.setOpaque(true);
		rep.setBackground(Color.WHITE);
		rep.setForeground(Color.DARK_GRAY);
		rep.setFont(new Font("SansSerif", Font.PLAIN, 23));
		repre.add(r);
		repre.add(rep);
		JPanel manager = new JPanel();
		manager.setLayout(new GridLayout(2,1));
		JLabel m = new JLabel("Administrator");
		m.setFont(new Font("SansSerif", Font.BOLD, 20));
		//총무 수정 가능
		JComboBox man = new JComboBox();
		//MemberLists
		man.setFont(new Font("SansSerif", Font.PLAIN, 23));
		man.addItem("총무를 지정하지 않습니다.");
		TreeMap<String, abs_Member> mems = dat.getAllMemberOfGroup();
		Iterator<String> iter = mems.keySet().iterator();
		while(iter.hasNext()) {
			String mm = iter.next();
			man.addItem(mems.get(mm).getName() + "(" +mems.get(mm).getEmail() + ")");
		}
		try{
			man.setSelectedItem(g.getAdministrator().getName() + "(" + g.getAdministrator().getEmail()+")");
		}catch(NullPointerException e0) {
			man.setSelectedItem("총무를 지정하지 않습니다.");
		}
		man.setBackground(Color.WHITE);
		man.setForeground(Color.DARK_GRAY);
		man.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				String str = man.getSelectedItem().toString();
				if(str.equals("총무를 지정하지 않습니다.")) {
					if(g.getAdministrator() != null) {
						JOptionPane.showMessageDialog(null,"총무를 설정해주세요", "Null Error", JOptionPane.WARNING_MESSAGE);
						man.setSelectedItem(g.getAdministrator().getName() + "(" + g.getAdministrator().getEmail()+")");
					}
				}
				else {
					StringTokenizer s = new StringTokenizer(str, "()");
					String name = s.nextToken();
					String email = s.nextToken();
					try{
						abs_Member a = dat.findMemberByNameAndEmail(name,email);
						dat.setAdministrator(a);
					}catch(NotChangePositionException e2) {
						try{
							man.setSelectedItem(g.getAdministrator().getName() + "(" + g.getAdministrator().getEmail()+")");
						}catch(NullPointerException e0) {
							man.setSelectedItem("총무를 지정하지 않습니다.");
						}
						JOptionPane.showMessageDialog(null,"대표는 총무가 될 수 없습니다..", "Not Allowed", JOptionPane.WARNING_MESSAGE);
					}	
			};
			}
		});
		//총무 수정 불가능
		JLabel man2 = new JLabel();
		try{
			man2.setText(g.getAdministrator().getName() + "(" + g.getAdministrator().getEmail() + ")");
		}catch(NullPointerException e1) {
			man2.setText("지정된 총무가 없습니다");
		}
		//man.setText
		man2.setFont(new Font("SansSerif", Font.PLAIN, 23));
		man2.setBackground(Color.WHITE);
		man2.setForeground(Color.DARK_GRAY);
		man2.setOpaque(true);
		
		
		manager.add(m);
		
		
		func_member.add(repre);
		func_member.add(manager);
		
		upside.add(title);
		upside.add(func_member);
		
		JPanel downside = new JPanel();
		downside.setBorder(BorderFactory.createEmptyBorder(20 , 70 , 30 , 50));
		downside.setLayout(new BorderLayout());
		JLabel member= new JLabel("Member");
		member.setFont(new Font("SansSerif", Font.BOLD, 20));
		//표 만들기
		JPanel list = new JPanel();
		list.setLayout(new GridBagLayout());
		int i = 0;
		con.gridx = 0; con.gridy = i;
		
		TreeMap<String, abs_Member> M = dat.getAllMemberOfGroup();
		Iterator<String> it = M.keySet().iterator();
		while(it.hasNext()) {
			abs_Member aa = M.get(it.next());
			con.gridx = 0; con.gridy = i;
			JPanel bb = new new_member(aa, true, dat).firstp;
			list.add(bb, con);
			i++;
		}
		
		TreeMap<String, abs_Member> M2 = dat.getAllAwaitMemberOfGroup();
		Iterator<String> it2 = M2.keySet().iterator();
		while(it2.hasNext()) {
			abs_Member aa2 = M2.get(it2.next());
			con.gridx = 0; con.gridy = i;
			JPanel bb2 = new new_member(aa2, false,dat).firstp;
			list.add(bb2, con);
			i++;
		}
		
		
		
		JPanel blanks = new JPanel();
		blanks.setPreferredSize(new Dimension(0, 233));
		con.gridx= 0; con.gridy = i+1;
		list.add(blanks, con);
		
		JScrollPane mem_list = new JScrollPane(list);
		mem_list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		mem_list.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		downside.add(member, BorderLayout.NORTH);
		add(upside);
		
		switch(dat.getPositionOfGroup(g)) {
		case PRESIDENT:
			manager.add(man);
			downside.add(mem_list, BorderLayout.CENTER);
			add(downside);
			break;
		case ADMINISTRATOR:
			downside.add(mem_list, BorderLayout.CENTER);
			add(downside);
		case EMPLOYEE:
			manager.add(man2);
			break;
	}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    String command = e.getActionCommand();
	    if (command.equals("Balance")) {
	        switch (dat.getPositionOfGroup(g)) {
	            case ADMINISTRATOR:
	                Balance_Man manager = new Balance_Man(g, dat);
	                manager.setVisible(true);
	                break;
	            case PRESIDENT:
	            case EMPLOYEE:
	                Balance_Mem member = new Balance_Mem(g, dat);
	                member.setVisible(true);
	                break;
	        }
	    }
	}
}
