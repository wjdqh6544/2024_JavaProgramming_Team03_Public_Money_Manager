package Start;

import javax.swing.JOptionPane;

import Home.Home;
import Login.Start;
import Controller.data;

public class pbmDemo {
	public static data dat = new data();
	public static void main(String[] args) {
		dat.initialization();
		Start screen = new Start(dat);
		screen.setVisible(true);
	}

}
