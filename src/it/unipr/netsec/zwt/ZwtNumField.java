package it.unipr.netsec.zwt;



/** An editable text component that can contain only  numerical character.
 * It can be given a piece of text that is used as the initial value.
 */
public class ZwtNumField extends ZwtLabel {
	
	/** Whether it is editable */
	boolean isEditable=true;


	/** Creates a new field.
	  * @param text Text of the button */
	public ZwtNumField(String text) {
		super(text);
		setColor(ZwtColor.WHITE);
		setBorder(null);
	}


	/** Whether it is editable. */
	public void setEditable(boolean isEditable) {
		this.isEditable=isEditable;
	}


	@Override
	public int keyPressed(int keyCode) {
		if (!isEditable) return keyCode;
		// else
		keyCode=ZwtContext.getKeyboard().getKeyNum(keyCode);
		if ((keyCode>='0' && keyCode<='9') || keyCode=='*' || keyCode=='#') {
			String text=getText();
			if (text.length()==getMaximumTextLength()) return 0;
			setText(text+(char)keyCode);
			return 0;
		}
		else
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
