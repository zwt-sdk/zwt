package it.unipr.netsec.zwt.examples.calc;

import it.unipr.netsec.zwt.ZwtButton;
import it.unipr.netsec.zwt.ZwtButtonListener;
import it.unipr.netsec.zwt.ZwtColor;
import it.unipr.netsec.zwt.ZwtContext;
import it.unipr.netsec.zwt.ZwtImage;
import it.unipr.netsec.zwt.ZwtKeyboard;
import it.unipr.netsec.zwt.ZwtPanel;
import it.unipr.netsec.zwt.border.ZwtRoundBorder;
import it.unipr.netsec.zwt.floor.ZwtRoundFloor;
import it.unipr.netsec.zwt.layout.ZwtGridLayout;

/** Calculator keyboard.
 */
class CalculatorKeyboard extends ZwtPanel {

	/** Button color */
	static final ZwtColor BUTTON_COLOR=new ZwtColor(0xAAAAAA,0.5F);

	/** Color of button 'enter' */
	static final ZwtColor BUTTON_ENTER_COLOR=new ZwtColor(0xFFD000,1.0F);
	
	/** Button thickness factor (compared to the keyboard height) */
	static final double BUTTON_THICKNESS=0.004;

	/** Button roundness factor (compared to the keyboard height) */
	static final double BUTTON_ROUNDNESS=0.008;

	
	static String KEY_SIGN="+/-";
	static String KEY_DOT=".";
	static String KEY_DIV="/";
	static String KEY_MULT="*";
	static String KEY_SUB="-";
	static String KEY_ADD="+";
	static String KEY_SQRT="sqrt";
	static String KEY_PERCENT="%";
	static String KEY_INV="1/x";
	static String KEY_SQR="x2";
	static String KEY_ENTER="enter";
	static String KEY_DROP="drop";
	static String KEY_DEL="del";
	static String KEY_CLEAR="clear";
	
	/** Creates new CalculatorKeyboard. */
	public CalculatorKeyboard(int width, int height, ZwtImage backgroudImage, ZwtButtonListener listener) {
		
		if (backgroudImage!=null) {
			try {
				setImage(backgroudImage,true);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		setBorder(null);
		ZwtGridLayout layout=new ZwtGridLayout(4,6);
		layout.setHorizontalSpace(width/64);
		layout.setVspaceSpace(height/64);
		setLayout(layout);
		int thickness=(int)Math.ceil(BUTTON_THICKNESS*height);
		int roundness=(int)Math.ceil(BUTTON_ROUNDNESS*height);
		for (int i=0; i<4; i++) {
			for (int j=0; j<6; j++) {
				ZwtButton button=null;
				if (i<3 && j<3) {
					String digit=String.valueOf(7-3*i+j);
					button=createButton(digit,ZwtContext.getKeyboard().getKeyNum(digit.charAt(0)),BUTTON_COLOR,thickness,roundness,listener);
				}
				else
				if (i==3 && j==0) button=createButton("0",ZwtKeyboard.KEY_NUM0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==3 && j==1) button=createButton(KEY_SIGN,0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==3 && j==2) button=createButton(KEY_DOT,0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==0 && j==3) button=createButton(KEY_DIV,0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==1 && j==3) button=createButton(KEY_MULT,0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==2 && j==3) button=createButton(KEY_SUB,0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==3 && j==3) button=createButton(KEY_ADD,0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==0 && j==4) button=createButton(KEY_SQRT,0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==1 && j==4) button=createButton(KEY_PERCENT,0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==2 && j==4) button=createButton(KEY_INV,0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==3 && j==4) button=createButton(KEY_SQR,0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==0 && j==5) button=createButton(KEY_ENTER,0,BUTTON_ENTER_COLOR,thickness,roundness,listener);
				else
				if (i==1 && j==5) button=createButton(KEY_DROP,0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==2 && j==5) button=createButton(KEY_DEL,0,BUTTON_COLOR,thickness,roundness,listener);
				else
				if (i==3 && j==5) button=createButton(KEY_CLEAR,0,BUTTON_COLOR,thickness,roundness,listener);
				
				addComponent(button);
			}
		}
	}

	/** Creates button. */
	private ZwtButton createButton(String label, int key, ZwtColor color, int thickness, int roundness, ZwtButtonListener listener) {
		// ROUND BUTTON
		ZwtButton button=new ZwtButton(label,key,listener);
		ZwtRoundFloor floor=new ZwtRoundFloor();
		floor.setRoundness(roundness);
		floor.setColor(color);
		button.setFloor(floor);
		ZwtRoundBorder border=new ZwtRoundBorder();
		border.setThickness(thickness);
		border.setRoundness(roundness);
		button.setBorder(border);
		// SQUARE BUTTON
		/*ZwtTransparentFloor floor=new ZwtTransparentFloor();
		floor.setColor(color);
		button.setFloor(floor);
		button.getBorder().setThickness(BUTTON_TICKNESS);*/
		return button;
	}
}
