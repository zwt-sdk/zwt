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


import javax.microedition.lcdui.Canvas;


/** Standard keyboard.
 */
public class ZwtKeyboard {
	
	/** ITU-T Key 0 */
	public static final int KEY_NUM0=Canvas.KEY_NUM0;
	
	/** ITU-T Key 1 */
	public static final int KEY_NUM1=Canvas.KEY_NUM1;
	
	/** ITU-T Key 2 */
	public static final int KEY_NUM2=Canvas.KEY_NUM2;
	
	/** ITU-T Key 3 */
	public static final int KEY_NUM3=Canvas.KEY_NUM3;
	
	/** ITU-T Key 4 */
	public static final int KEY_NUM4=Canvas.KEY_NUM4;
	
	/** ITU-T Key 5 */
	public static final int KEY_NUM5=Canvas.KEY_NUM5;
	
	/** ITU-T Key 6 */
	public static final int KEY_NUM6=Canvas.KEY_NUM6;
	
	/** ITU-T Key 7 */
	public static final int KEY_NUM7=Canvas.KEY_NUM7;
	
	/** ITU-T Key 8 */
	public static final int KEY_NUM8=Canvas.KEY_NUM8;
	
	/** ITU-T Key 9 */
	public static final int KEY_NUM9=Canvas.KEY_NUM9;
	
	/** ITU-T Key POUND */
	public static final int KEY_POUND=Canvas.KEY_POUND;
	
	/** ITU-T Key STAR */
	public static final int KEY_STAR=Canvas.KEY_STAR;

	/** Key UP */
	public static final int KEY_UP=-1;

	/** Key DOWN */
	public static final int KEY_DOWN=-2;

	/** Key LEFT */
	public static final int KEY_LEFT=-3;

	/** Key RIGHT */
	public static final int KEY_RIGHT=-4;

	/** Key SELECT */
	public static final int KEY_SELECT=-5;

	/** Key CANC */
	public static final int KEY_CANC=-8;  

	/** Key DEL (from Nokia E61) */
	public static final int KEY_DEL=8;

	/** Key NUMLOCK (shift key in Nokia E61) */
	public static final int KEY_NUMLOCK=-50;

	/** Key ESC */
	public static final int KEY_ESC=-8;  

	/** Key SOFT1 */
	public static final int KEY_SOFT1=-6;

	/** Key SOFT2 */
	public static final int KEY_SOFT2=-7;


	/** Singletone */
	private static ZwtKeyboard INSTANCE=null;


	/** Gets the ZwtKeyboardIT. */
	public static ZwtKeyboard getInstance() {
		if (INSTANCE==null) INSTANCE=new ZwtKeyboard();
		return INSTANCE;
	}

	/** Gets the numeric key code associated to a given key.
	 * @param keyCode the key code
	 * @return the numeric key code */
	public int getKeyNum(int keyCode) {			
		if (keyCode>='0' && keyCode<='9') {
			return keyCode;
		}
		// else
		switch (keyCode) {
			case ZwtKeyboard.KEY_NUM0 : return '0';
			case ZwtKeyboard.KEY_NUM1 : return '1';
			case ZwtKeyboard.KEY_NUM2 : return '2';
			case ZwtKeyboard.KEY_NUM3 : return '3';
			case ZwtKeyboard.KEY_NUM4 : return '4';
			case ZwtKeyboard.KEY_NUM5 : return '5';
			case ZwtKeyboard.KEY_NUM6 : return '6';
			case ZwtKeyboard.KEY_NUM7 : return '7';
			case ZwtKeyboard.KEY_NUM8 : return '8';
			case ZwtKeyboard.KEY_NUM9 : return '9';
			case ZwtKeyboard.KEY_STAR : return '*';
			case ZwtKeyboard.KEY_POUND : return '#';
		}
		return keyCode;
	}
		
	/** Gets chars associated to a given key.
	 * @param keyCode the key code
	 * @return array of chars associated to the key */
	public char[] getChars(int keyCode) {
		if (keyCode>0) {
			char c=(char) keyCode;
			char[] ret={c};
			return ret;
		}
		return null;
	}

}
