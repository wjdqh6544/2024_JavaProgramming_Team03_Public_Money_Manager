package Home;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.relation.Role;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Entity.PositionList;
import Entity.President;
import Exception.NotFoundException;
import Group.Group;
import Controller.data;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Roh, Seongmin
 */
public class new_grouplist  implements ActionListener {
	JButton one;
	Entity.Group group = null;
	data dat = null;
	JButton onej = null;
	JLabel await = null;
	String name = null;
	public new_grouplist (Entity.Group g, data data){
		group = g;
		dat = data;
		name = g.getGroupName();
		President representative= g.getPresident();
		boolean joined;
		String role = null;
		try{
			PositionList r = dat.getPositionOfGroup(g);
			role = r.toString();
			joined = true;
			}catch(NotFoundException e) {
				joined = false;
				role = "Not joined";
			}
		
		one = new JButton();
		one.addActionListener(this);
		one.setActionCommand(name);
		one.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		one.setPreferredSize(new Dimension(1000, 50));
		one.setBackground(Color.WHITE);
		JLabel onen = new JLabel(name);
		onen.setPreferredSize(new Dimension(500, 30));
		onen.setFont(new Font("SansSerif", Font.BOLD, 15));
		JLabel oner = new JLabel(role);//
		oner.setPreferredSize(new Dimension(100,30));
		oner.setFont(new Font("SansSerif", Font.BOLD, 12));
		JLabel onep = new JLabel(representative.getName());
		onep.setFont(new Font("SansSerif", Font.BOLD, 15));
	
		con.weightx = 2; con.weighty = 0;
		one.add(onen, con);
		con.weightx = 50; con.weighty = 0;
		one.add(oner, con);
		con.weightx = 100; con.weighty = 0;
		one.add(onep, con);
		
		JPanel Button = new JPanel();
		Button.setPreferredSize(new Dimension(70,30));
		Button.setBackground(Color.WHITE);
		con.weightx = 1;
		one.add(Button, con);	
		
		if(!joined) {
			await = new JLabel("Awaiting...");
			Button.add(await);
			if(!dat.checkIfInAwait(g)) {
				await.setVisible(false);
				onej = new JButton("Join");
				onej.addActionListener(this);
				onej.setBackground(Color.BLACK);
				onej.setForeground(Color.WHITE);
				Button.add(onej);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(command.equals(name)){
			try{
				dat.getPositionOfGroup(group);
				Group a = new Group(group, this.dat);
				a.setVisible(true);
			}catch(NotFoundException e1) {
				JOptionPane.showMessageDialog(null,"권한이 없습니다.", "Fail to Access", JOptionPane.ERROR_MESSAGE);
			}
			
			
		}
		else if(command.equals("Join")) {
			dat.requestJoinGroup(group);
			onej.setVisible(false);
			await.setVisible(true);
		}
		
	}
}
