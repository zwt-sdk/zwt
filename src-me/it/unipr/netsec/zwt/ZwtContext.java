/*
 * Copyright (c) 2018 NetSec Lab - University of Parma (Italy)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND. IN NO EVENT
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * Author(s):
 * Luca Veltri (luca.veltri@unipr.it)
 */

package it.unipr.netsec.zwt;


import it.unipr.netsec.zwt.keyboard.NokiaQwertyKeyboard;


/** ZWT context.
 */
public class ZwtContext {
	
	/** Keyboard */
	protected static ZwtKeyboard keyboard=null;

	/** Type of keyboard */
	protected static boolean keyboardQwerty=false;

	/** AWT rendering context of the Font within Graphics2D context */
	//protected static FontRenderContext fontRenderContext=null;

	/** Border thickness */
	public static int DEFAULT_BORDER_THICKNESS=1;

	
	/** Sets keyboard. 
	 * @param kbd the keyboard */
	public static synchronized void setKeyboard(ZwtKeyboard kbd) {
		keyboard=kbd;
	}

	/** Gets keyboard. 
	 * @return the keyboard */
	public static synchronized ZwtKeyboard getKeyboard() {
		if (keyboard==null) keyboard=NokiaQwertyKeyboard.getInstance();
		return keyboard;
	}

	/** Gets the AWT rendering context of the Font within Graphics2D context. 
	 * @return the font rendering context */
	/*public static synchronized FontRenderContext getFontRenderContext() {
		if (fontRenderContext==null) fontRenderContext=((Graphics2D)new BufferedImage(1,1,BufferedImage.TYPE_4BYTE_ABGR).getGraphics()).getFontRenderContext();
		return fontRenderContext;
	}*/

}
