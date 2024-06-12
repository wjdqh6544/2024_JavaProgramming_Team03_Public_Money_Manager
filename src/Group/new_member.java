package Group;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Entity.abs_Member;
import Exception.NotFoundException;
import Controller.data;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Roh, Seongmin
 */
public class new_member implements ActionListener{
	data dat = null;
	JPanel firstp;
	abs_Member member = null;
	JLabel role1;
	JButton add1;
	JButton reject1;
	public new_member(abs_Member m, boolean joined, data data){
		member = m;
		this.dat = data;
		String role;
		try{
			 role = dat.getPositionOfGroup(m).toString();
		}catch(NotFoundException e1) {
			role = null;
		}
		String name = m.getName() + "(" + m.getEmail() + ")";
		firstp = new JPanel(); 
		firstp.setLayout(new FlowLayout(FlowLayout.LEFT));
		firstp.setPreferredSize(new Dimension(0, 50));
		firstp.setBackground(Color.WHITE);
		firstp.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		role1 = new JLabel();
		if(role != null) {
			role1.setText(role);
			role1.setForeground(Color.GRAY);
		}
		else {
			role1.setText("Requested");

			role1.setForeground(Color.BLUE);
		}
		role1.setPreferredSize(new Dimension(150,40));
		role1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		role1.setBackground(Color.WHITE);
		role1.setOpaque(true);
		role1.setHorizontalAlignment(JLabel.CENTER);
		JLabel name1 = new JLabel("Default");
		name1.setText(name);
		name1.setPreferredSize(new Dimension(900,40));
		name1.setFont(new Font("SansSerif", Font.BOLD, 15));
		name1.setBackground(Color.WHITE);
		name1.setOpaque(true);
		
		firstp.add(role1);
		firstp.add(name1);
		if(!joined) {

			add1 = new JButton("permit");
			add1.addActionListener(this);
			add1.setBackground(Color.WHITE);
			firstp.add(add1);
			reject1 = new JButton("reject");
			reject1.addActionListener(this);
			reject1.setBackground(Color.BLACK);
			reject1.setForeground(Color.WHITE);
			firstp.add(reject1);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
			case "permit":
				dat.acceptJoinGroup(member);
				add1.setVisible(false);
				reject1.setVisible(false);
				role1.setText(dat.getPositionOfGroup(member).toString());
				role1.setForeground(Color.GRAY);
				break;
			case "reject":
				dat.rejectJoinGroup(member);
				add1.setVisible(false);
				reject1.setVisible(false);
				role1.setText("Rejected");
				role1.setForeground(Color.RED);
				break;
		}
	}
}