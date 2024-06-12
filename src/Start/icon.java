package Start;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
/*
 * COMP217: Java Programming / Team 03
 * Prof: Suh, Young-Kyoon
 *
 * @author: Roh, Seongmin
 */
public class icon {
	public final static Image geticon() {
		String path = "";
		try {
			path = new File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String realpath = path+"/src/"+"icon.png";
		ImageIcon i = new ImageIcon(realpath);
		return i.getImage();
	}
}
