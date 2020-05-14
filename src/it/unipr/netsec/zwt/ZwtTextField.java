package it.unipr.netsec.zwt;



/** An editable text field.
 * Text component that allows for the editing of a single line of text.
  */
public class ZwtTextField extends ZwtNumField {
	
	/** Key repetition time [millisecs] */
	protected static long KEY_TIME=1000;

	/** Type of keyboard */
	boolean keyboardNumeric=false;

	/** Last pressed key */
	long lastKey=0;

	/** When last key was pressed */
	long lastKeyTime=0;

	/** Counter of last key repetitions */
	int lastKeyCount=0;


	/** Creates a new ZTextField.
	  * @param text Text of the button */
	public ZwtTextField(String text) {
		super(text);
	}

	
	/** Sets numeric keypad. */
	public void setKeyboardNumeric(boolean isNumeric) {
		keyboardNumeric=isNumeric;
	}

	
	@Override
	public int keyPressed(int keyCode) {
		if (!isEditable) return keyCode;
		// else
		if (keyCode==ZwtKeyboard.KEY_NUMLOCK) {
			keyboardNumeric=!keyboardNumeric;
			return 0;
		}
		// else
		if (keyboardNumeric) return super.keyPressed(keyCode);
		// else
		ZwtKeyboard keyboard=ZwtContext.getKeyboard();
		long now=System.currentTimeMillis();
		if (lastKey==keyCode && now<lastKeyTime+KEY_TIME) lastKeyCount++; else lastKeyCount=0;
		lastKey=keyCode;
		lastKeyTime=now;
		char[] chars=keyboard.getChars(keyCode);
		if (chars!=null) {
			String text=getText();
			if (lastKeyCount>0 && chars.length>1) text=text.substring(0,text.length()-1);
			else
			if (text.length()==getMaximumTextLength()) {
				lastKey=0;
				return 0;
			}
			char c=chars[(lastKeyCount%chars.length)];
			setText(text+c);
			return 0;
		}
		// else
		if (keyCode==ZwtKeyboard.KEY_CANC || keyCode==ZwtKeyboard.KEY_DEL) {
			String text=getText();
			if (text.length()>0) {
				setText(text.substring(0,text.length()-1));
			}
			return 0;
		}
		// else
		return keyCode;
	}

}
