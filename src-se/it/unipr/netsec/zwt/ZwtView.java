package it.unipr.netsec.zwt;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


/** Platform-dependent view.
 * It is normally created using a graphical object specific for the target platform
 * (e.g. a Java swing JFrame in case of Java SE platform, an ImageView in case of Android, or a MIDlet in case of Java ME platform with CLDC and MIDP).
 */
public class ZwtView {

	/** Screen size */
	private static ZwtDimension SCREEN_SIZE=null;

	/** Frame */
	JFrame frame;

	
	/** Creates a new view.
	 * @param frame a frame. */
	public ZwtView(JFrame frame) {
		this.frame=frame;
    }
	
	
	/** Gets the screen size. */
	public static ZwtDimension getScreenSize() {
		if (SCREEN_SIZE==null) {
			Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
			SCREEN_SIZE=new ZwtDimension((int)d.getWidth(),(int)d.getHeight());
		}
		return SCREEN_SIZE;
	}
	
}
