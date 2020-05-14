package it.unipr.netsec.zwt;



/** UI context.
 */
public class ZwtContext {
	
	/** Keyboard */
	protected static ZwtKeyboard KEYBOARD=null;

	/** AWT rendering context of the Font within Graphics2D context */
	//protected static FontRenderContext FONT_RENDER_CONTEXT=null;

	
	/** Sets keyboard. 
	 * @param keyboard the keyboard */
	public static synchronized void setKeyboard(ZwtKeyboard keyboard) {
		KEYBOARD=keyboard;
	}

	/** Gets keyboard. 
	 * @return the keyboard */
	public static synchronized ZwtKeyboard getKeyboard() {
		if (KEYBOARD==null) KEYBOARD=ZwtKeyboard.getInstance();
		return KEYBOARD;
	}

	/** Gets the AWT rendering context of the Font within Graphics2D context. 
	 * @return the font rendering context */
	/*public static synchronized FontRenderContext getFontRenderContext() {
		if (FONT_RENDER_CONTEXT==null) FONT_RENDER_CONTEXT=((Graphics2D)new BufferedImage(1,1,BufferedImage.TYPE_4BYTE_ABGR).getGraphics()).getFontRenderContext();
		return FONT_RENDER_CONTEXT;
	}*/
	
}
