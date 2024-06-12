package Home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Entity.Group;
import Controller.data;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Roh, Seongmin
 */
public class grouplists {
	data dat = null;
	public JPanel groups = null;
	public JButton plus = null;
	private JCheckBox my = null;
	public grouplists(int n, data data) {
		this.dat = data;
		groups = new JPanel();
		groups.setBackground(Color.WHITE);
		groups.setLayout(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		
		JPanel g = new JPanel();
		g.setLayout(new FlowLayout(FlowLayout.LEFT));
		g.setPreferredSize(new Dimension(950, 100));
		g.setBackground(Color.WHITE);
		JLabel gl = new JLabel("Group");
		gl.setPreferredSize(new Dimension(780, 100));
		gl.setLayout(new FlowLayout(FlowLayout.LEFT));
		gl.setFont(new Font("SansSerif", Font.BOLD, 40));
		g.add(gl);
		
		JPanel list = new JPanel();
		list.setPreferredSize(new Dimension(1000,n));
		list.setBackground(Color.WHITE);
		list.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY)));
		list.setLayout(new BorderLayout());
		
		JPanel t = new JPanel();
		t.setPreferredSize(new Dimension(1000, 50));
		t.setBackground(Color.WHITE);
		t.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY)));
		t.setLayout(new GridBagLayout());
		
		JLabel gn = new JLabel("Group Name");
		gn.setPreferredSize(new Dimension(500, 30));
		gn.setFont(new Font("SansSerif", Font.BOLD, 15));
		JLabel role = new JLabel("Role");
		role.setFont(new Font("SansSerif", Font.BOLD, 15));
		JLabel representative = new JLabel("Representative");
		representative.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		con.weightx = 2; con.weighty = 0;
		t.add(gn, con);
		con.weightx = 50; con.weighty = 0;
		t.add(role, con);
		con.weightx = 100; con.weighty = 0;
		t.add(representative, con);
		
		list.add(t, BorderLayout.NORTH);
		
		JPanel gs = new JPanel();
		gs.setLayout(new GridBagLayout());
		int i = 0;

		TreeMap<String, Group> grouplist ;
		
		try{
			grouplist = dat.getAllGroupList();
			Iterator<String> iter = grouplist.keySet().iterator();
			while(iter.hasNext()) {
				String nm = iter.next();
				new_grouplist a = new new_grouplist(grouplist.get(nm), dat);
				con.gridx = 0; con.gridy = i;
				gs.add(a.one, con);
				i++;
			}
			
		}catch(NullPointerException e) {
			JLabel nul = new JLabel("Empty"); 
			con.gridx = 0; con.gridy = i;
			gs.add(nul, con);
			i++;
		}

		plus = new JButton("+ New Group");
		plus.setFont(new Font("SansSerif", Font.BOLD, 15));
		plus.setBackground(Color.WHITE);
		plus.setPreferredSize(new Dimension(1000,50));
		plus.setHorizontalAlignment(SwingConstants.LEFT);
		plus.setActionCommand("New");
		
		con.gridx = 0; con.gridy = i;
		gs.add(plus,con);
		
		JLabel blank = new JLabel();
		blank.setPreferredSize(new Dimension(1000,n-105));
		con.gridx = 0; con.gridy = i+1;
		gs.add(blank, con);
		
		
		JScrollPane group_list = new JScrollPane(gs);
		group_list.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		group_list.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		list.add(group_list, BorderLayout.CENTER);
		
		
		con.gridx = 0; con.gridy = 0;
		groups.add(g, con);
		con.gridx = 0; con.gridy = 1;
		groups.add(list, con);
	}
}