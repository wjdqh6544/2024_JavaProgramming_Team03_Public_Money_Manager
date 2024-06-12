package Home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Controller.data;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Roh, Seongmin
 */
public class home_window {
		data dat = null;
		JPanel cen = null;
		JButton prof = null;
		JButton plus = null;
	public home_window(data data) {
		this.dat = data;
	
		JPanel myname = new JPanel();
		myname.setPreferredSize(new Dimension(1000,50));
		myname.setBackground(Color.WHITE);
		myname.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY)));
		
		myname.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.fill = GridBagConstraints.NONE;
		
		JLabel Name = new JLabel("Name");
		Name.setFont(new Font("SansSerif", Font.BOLD, 25));
		
		JLabel user_name = new JLabel("Default");
		user_name.setFont(new Font("SansSerif", Font.PLAIN, 20));
		user_name.setText(dat.getUser().getName());
		
		prof = new JButton(">  ");
		prof.setActionCommand("Profile");
		prof.setBackground(Color.WHITE);
		prof.setBorder(new EmptyBorder(5, 17, 5 ,3));
		
		con.weightx = 0.5; con.weighty = 0;
		myname.add(Name, con);
		con.weightx = 25; con.weighty = 0;
		myname.add(user_name, con);
		con.weightx = 0;
		myname.add(prof, con);
		
		//group
		grouplists lists = new grouplists(400, this.dat);
		JPanel groups = lists.groups;
		plus = lists.plus;
		
		cen.add(myname);
		cen.add(groups);
		cen.setBackground(Color.WHITE);
	}
}
