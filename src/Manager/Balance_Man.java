package Manager;

import static Start.Constants.height;
import static Start.Constants.width;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Controller.data;
import Start.icon;

/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Lee, Daeun
 */

public class Balance_Man extends JFrame implements ActionListener {
	private data dat;
	Entity.Group g;
	private JPanel sample;

	JButton bm = new JButton();
	JButton sett = new JButton();

	public Balance_Man(Entity.Group g, data data) {
		this.dat = data;
		this.g = g;

		setTitle("Balance Manager");
		setSize(width, height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		this.setIconImage(icon.geticon());

		JPanel balance = new JPanel();
		balance.setBackground(Color.WHITE);
		balance.setLayout(new BorderLayout());

		JPanel topLeftPanel = new JPanel(new GridBagLayout());
		topLeftPanel.setBackground(Color.WHITE);
		topLeftPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		topLeftPanel.setPreferredSize(new Dimension(400, 50));

		GridBagConstraints con = new GridBagConstraints();
		con.gridx = 0;
		con.gridy = 0;
		con.insets = new Insets(0, 0, 0, 10);

		bm = new JButton("Balance Manager");
		bm.setActionCommand("Balance Manager");
		bm.addActionListener(this);
		bm.setFont(new Font("SansSerif", Font.BOLD, 13));
		bm.setPreferredSize(new Dimension(200, 30));
		selected(bm);
		topLeftPanel.add(bm, con);

		con.gridx = 1;
		sett = new JButton("Settlement");
		sett.setActionCommand("Settlement");
		sett.addActionListener(this);
		sett.setFont(new Font("SansSerif", Font.BOLD, 13));
		sett.setPreferredSize(new Dimension(150, 30));
		sett.setBackground(Color.WHITE);
		topLeftPanel.add(sett, con);

		JPanel wrapperPanel = new JPanel(new BorderLayout());
		wrapperPanel.setBackground(Color.WHITE);
		wrapperPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		wrapperPanel.add(topLeftPanel, BorderLayout.WEST);

		balance.add(wrapperPanel, BorderLayout.NORTH);

		sample = new JPanel();
		sample.setBackground(Color.WHITE);
		sample.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY)));
		sample.setPreferredSize(new Dimension(400, 50));
		sample.setLayout(new BorderLayout());
		balance.add(sample, BorderLayout.CENTER);

		add(balance);

		setVisible(true);
		this.bm_window();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals("Balance Manager")) {
			unselected();
			selected(bm);
			this.bm_window();
			
		} else if (action.equals("Settlement")) {
			unselected();
			selected(sett);
			this.sett_window();
		}
	}

	public void selected(JButton s) {
		Color BLUE_GRAY = new Color(230, 230, 235);
		s.setBackground(BLUE_GRAY);
		s.setBorder(new EmptyBorder(5, 17, 5, 3));
	}

	public void unselected(JButton s) {
		s.setBackground(Color.WHITE);
		s.setBorder(new JButton().getBorder());
	}

	public void unselected() {
		unselected(bm);
		unselected(sett);
	}

	public void bm_window() {
	    sample.removeAll();
	    bm_window bw = new bm_window(g, dat);
	    sample.add(bw, BorderLayout.CENTER);
	    sample.revalidate();
	    sample.repaint();
	    setTitle("Balance Manager");
	    
	    JButton addEventButton = bw.getAddEventButton();
	    addEventButton.addActionListener(this);
	}


	public void sett_window() {
		sample.removeAll();
		sett_window sw = new sett_window(g, dat);
		sample.add(sw, BorderLayout.CENTER);
		sample.revalidate();
		sample.repaint();
		setTitle("Settlement");
	}
}
