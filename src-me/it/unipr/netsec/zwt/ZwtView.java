package it.unipr.netsec.zwt;


import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;


/** Platform-dependent view.
 */
public class ZwtView {

	/** Screen size */
	private static ZwtDimension SCREEN_SIZE=null;
	
	/** MIDlet */
	MIDlet midlet;

	
	/** Creates a new view.
	 * @param midlet the MIDlet that will contain the display */
	public ZwtView(MIDlet midlet) {
		this.midlet=midlet;
    }
	
	
	/** Gets the screen size. */
	public static ZwtDimension getScreenSize() {
		if (SCREEN_SIZE==null) {
			Canvas c=new Canvas() {
				protected void paint(Graphics g) {
				}
			};
			SCREEN_SIZE=new ZwtDimension(c.getWidth(),c.getHeight());
		}
		return SCREEN_SIZE;
	}

}
