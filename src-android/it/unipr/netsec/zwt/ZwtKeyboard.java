package it.unipr.netsec.zwt;


import android.view.KeyEvent;


/** Standard keyboard.
  */
public class ZwtKeyboard {
		
	/** ITU-T Key 0 */
	public static final int KEY_NUM0=KeyEvent.KEYCODE_NUMPAD_0;

	/** ITU-T Key 1 */
	public static final int KEY_NUM1=KeyEvent.KEYCODE_NUMPAD_1;

	/** ITU-T Key 2 */
	public static final int KEY_NUM2=KeyEvent.KEYCODE_NUMPAD_2;

	/** ITU-T Key 3 */
	public static final int KEY_NUM3=KeyEvent.KEYCODE_NUMPAD_3;

	/** ITU-T Key 4 */
	public static final int KEY_NUM4=KeyEvent.KEYCODE_NUMPAD_4;

	/** ITU-T Key 5 */
	public static final int KEY_NUM5=KeyEvent.KEYCODE_NUMPAD_5;

	/** ITU-T Key 6 */
	public static final int KEY_NUM6=KeyEvent.KEYCODE_NUMPAD_6;

	/** ITU-T Key 7 */
	public static final int KEY_NUM7=KeyEvent.KEYCODE_NUMPAD_7;

	/** ITU-T Key 8 */
	public static final int KEY_NUM8=KeyEvent.KEYCODE_NUMPAD_8;

	/** ITU-T Key 9 */
	public static final int KEY_NUM9=KeyEvent.KEYCODE_NUMPAD_9;

	/** ITU-T Key POUND */
	public static final int KEY_POUND=KeyEvent.KEYCODE_POUND;

	/** ITU-T Key STAR */
	public static final int KEY_STAR=KeyEvent.KEYCODE_STAR;

	/** Key UP */
	public static final int KEY_UP=-KeyEvent.KEYCODE_DPAD_UP;

	/** Key DOWN */
	public static final int KEY_DOWN=-KeyEvent.KEYCODE_DPAD_DOWN;

	/** Key LEFT */
	public static final int KEY_LEFT=-KeyEvent.KEYCODE_DPAD_LEFT;

	/** Key RIGHT */
	public static final int KEY_RIGHT=-KeyEvent.KEYCODE_DPAD_RIGHT;

	/** Key SELECT (alias ENTER) */
	public static final int KEY_SELECT=10;

	/** Key CANC */
	public static final int KEY_CANC=-KeyEvent.KEYCODE_CLEAR;

	/** Key DEL (alias BACKSPACE) */
	public static final int KEY_DEL=-KeyEvent.KEYCODE_DEL;

	/** Key NUMLOCK (alias F11) */
	public static final int KEY_NUMLOCK=-KeyEvent.KEYCODE_NUM_LOCK;

	/** Key ESC */
	public static final int KEY_ESC=-KeyEvent.KEYCODE_ESCAPE;

	/** Key SOFT1 (alias F1) */
	public static final int KEY_SOFT1=-KeyEvent.KEYCODE_F1;

	/** Key SOFT2 (alias F2) */
	public static final int KEY_SOFT2=-KeyEvent.KEYCODE_F2;


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
