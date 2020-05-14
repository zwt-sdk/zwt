package it.unipr.netsec.zwt;


import java.awt.event.KeyEvent;


/** Standard keyboard.
  */
public class ZwtKeyboard {
		
	/** ITU-T Key 0 */
	public static final int KEY_NUM0=KeyEvent.VK_NUMPAD0;

	/** ITU-T Key 1 */
	public static final int KEY_NUM1=KeyEvent.VK_NUMPAD1;

	/** ITU-T Key 2 */
	public static final int KEY_NUM2=KeyEvent.VK_NUMPAD2;

	/** ITU-T Key 3 */
	public static final int KEY_NUM3=KeyEvent.VK_NUMPAD3;

	/** ITU-T Key 4 */
	public static final int KEY_NUM4=KeyEvent.VK_NUMPAD4;

	/** ITU-T Key 5 */
	public static final int KEY_NUM5=KeyEvent.VK_NUMPAD5;

	/** ITU-T Key 6 */
	public static final int KEY_NUM6=KeyEvent.VK_NUMPAD6;

	/** ITU-T Key 7 */
	public static final int KEY_NUM7=KeyEvent.VK_NUMPAD7;

	/** ITU-T Key 8 */
	public static final int KEY_NUM8=KeyEvent.VK_NUMPAD8;

	/** ITU-T Key 9 */
	public static final int KEY_NUM9=KeyEvent.VK_NUMPAD9;

	/** ITU-T Key POUND */
	public static final int KEY_POUND=KeyEvent.VK_QUOTE;

	/** ITU-T Key STAR */
	public static final int KEY_STAR=KeyEvent.VK_MULTIPLY;

	/** Key UP */
	public static final int KEY_UP=KeyEvent.VK_UP;

	/** Key DOWN */
	public static final int KEY_DOWN=KeyEvent.VK_DOWN;

	/** Key LEFT */
	public static final int KEY_LEFT=KeyEvent.VK_LEFT;

	/** Key RIGHT */
	public static final int KEY_RIGHT=KeyEvent.VK_RIGHT;

	/** Key SELECT (alias ENTER) */
	public static final int KEY_SELECT=KeyEvent.VK_ENTER;

	/** Key CANC */
	public static final int KEY_CANC=KeyEvent.VK_DELETE;

	/** Key DEL (alias BACKSPACE) */
	public static final int KEY_DEL=KeyEvent.VK_BACK_SPACE;

	/** Key NUMLOCK (alias F11) */
	public static final int KEY_NUMLOCK=KeyEvent.VK_NUM_LOCK;

	/** Key ESC */
	public static final int KEY_ESC=KeyEvent.VK_ESCAPE;

	/** Key SOFT1 (alias F1) */
	public static final int KEY_SOFT1=KeyEvent.VK_F1;

	/** Key SOFT2 (alias F2) */
	public static final int KEY_SOFT2=KeyEvent.VK_F2;


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
		if (keyCode>=32) {
			char c=(char)keyCode;
			char[] ret={c};
			return ret;
		}
		return null;
	}

}
