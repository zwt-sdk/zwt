package it.unipr.netsec.zwt.examples.calc;


import it.unipr.netsec.zwt.ZwtBorder;
import it.unipr.netsec.zwt.ZwtButton;
import it.unipr.netsec.zwt.ZwtButtonListener;
import it.unipr.netsec.zwt.ZwtFrame;
import it.unipr.netsec.zwt.ZwtImage;
import it.unipr.netsec.zwt.ZwtPanel;
import it.unipr.netsec.zwt.ZwtPointer;
import it.unipr.netsec.zwt.layout.ZwtBorderLayout;
import it.unipr.netsec.zwt.menu.ZwtMenuBar;
import it.unipr.netsec.zwt.menu.ZwtMenuItem;
import it.unipr.netsec.zwt.menu.ZwtMenuItemListener;


/** Reverse Polish Notation (RPN) Calculator.
  */
public class RPNCalculator implements ZwtButtonListener, ZwtMenuItemListener {
	
	/** Debug mode */
	public static boolean DEBUG=false;
	
	/** Prints a log message. */
	public void printLog(String str) {
		System.out.println("DEBUG: "+getClass().getSimpleName()+": "+str);
	}


	/** Resources base path */
	public static String RES_PATH="resources";

	/** Pointer icon file */
	static final String POINTER_ICON="/images/pointer.gif";
	
	/** Border thickness (compared to the frame width) */
	static final double BORDER_THICKNESS=0.005;

	/** Background image file */
	static final String BACKGROUND_IMAGE="/images/background.png";


	/** Frame */
	ZwtFrame frame;

	/** Display */
	CalculatorDisplay display;

	/** Menu bar */
	ZwtMenuBar menuBar;


	/** Creates a new Calculator. */
	public RPNCalculator(ZwtFrame frame) {
		this(frame,false);
	}
	
	
	/** Creates a new Calculator. */
	public RPNCalculator(ZwtFrame frame, boolean plus) {
		this.frame=frame;

		// init
		int width=frame.getWidth();
		int height=frame.getHeight();
		if (DEBUG) printLog("window size: "+width+","+height);
		
		ZwtBorder.DEFAULT_THICKNESS=(int)Math.ceil(BORDER_THICKNESS*width);

		// set pointer
		ZwtPointer pointer=null;
		try {
			//pointer=new ZwtPointer(new ZwtImage(RES_PATH+POINTER_ICON));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if (pointer==null) pointer=new ZwtPointer();
		pointer.moveTo(width/2,height/2);
		//((VirtualPointerFrame)frame).setPointer(pointer);

		// create menu bar
		int menuHeight=0;
		if (plus) {
			menuBar=new CalculatorMenuBar(frame,width,this);
			frame.addComponent(menuBar,0,0);
			menuHeight=menuBar.getHeight();
		}

		// create window panel
		ZwtPanel windowPanel=new ZwtPanel(width,height-menuHeight);
		windowPanel.setBorder(null);
		windowPanel.setLayout(new ZwtBorderLayout());
		frame.addComponent(windowPanel,0,menuHeight);
		int windowHeight=windowPanel.getHeight();

		// create display
		display=new CalculatorDisplay(windowHeight/2);
		windowPanel.addComponent(display,ZwtBorderLayout.NORTH);
		 
		// create keyboard
		ZwtImage backgroundImage=null;
		try {
			if (plus) backgroundImage=new ZwtImage(RES_PATH+BACKGROUND_IMAGE);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//ZwtPanel keyboardPanel=new CalculatorKeyboard(width,height,backgroundImage,this);
		ZwtPanel keyboardPanel=new CalculatorKeyboard(width,windowHeight/2,backgroundImage,this);
		windowPanel.addComponent(keyboardPanel,ZwtBorderLayout.CENTER);
	}


	@Override
	public void onMenuItemSelected(ZwtMenuItem item) {
		menuBar.closeAll();
		if (item.getText().equals("Exit"))  {
			exit();
		}
		else
		if (item.getText().equals("Menu item A"))  {
			frame.repaintScreen();
		}
	}
	

	@Override
	public void onButtonPushed(ZwtButton button) {
		String buttonText=button.getText();
		if (DEBUG) printLog("BUTTON PRESSED: "+buttonText);
		try {
			if (buttonText.equals(CalculatorKeyboard.KEY_ENTER) ||
				buttonText.equals(CalculatorKeyboard.KEY_ADD) ||
				buttonText.equals("-") ||
				buttonText.equals(CalculatorKeyboard.KEY_MULT) ||
				buttonText.equals(CalculatorKeyboard.KEY_DIV) ||
				buttonText.equals(CalculatorKeyboard.KEY_SIGN) ||
				buttonText.equals(CalculatorKeyboard.KEY_INV) ||
				buttonText.equals(CalculatorKeyboard.KEY_SQRT) ||
				buttonText.equals(CalculatorKeyboard.KEY_PERCENT) ||
				buttonText.equals(CalculatorKeyboard.KEY_SQR)) {
				if (display.getLabelText().length()>0) {
					display.push(Double.parseDouble(display.getLabelText()));
					display.setLabelText("");
				}
			} 
			if (buttonText.equals(CalculatorKeyboard.KEY_ADD) ||
				buttonText.equals(CalculatorKeyboard.KEY_SUB) ||
				buttonText.equals(CalculatorKeyboard.KEY_MULT) ||
				buttonText.equals(CalculatorKeyboard.KEY_DIV)) {
				if (display.size()>1) {
					double param2=display.pop();
					double param1=display.pop();
					double result=0;
					if (buttonText.equals(CalculatorKeyboard.KEY_ADD)) result=param1+param2;
					else
					if (buttonText.equals(CalculatorKeyboard.KEY_SUB)) result=param1-param2;
					else
					if (buttonText.equals(CalculatorKeyboard.KEY_MULT)) result=param1*param2;
					else
					if (buttonText.equals(CalculatorKeyboard.KEY_DIV)) {
						if (param2!=0) result=param1/param2;
						else {
							display.push(param1);
							result=param2;
						}
					}
					
					display.push(result);
				}
			}
			else
			if (buttonText.equals(CalculatorKeyboard.KEY_SIGN) ||
				buttonText.equals(CalculatorKeyboard.KEY_INV) ||
				buttonText.equals(CalculatorKeyboard.KEY_SQRT) ||
				buttonText.equals(CalculatorKeyboard.KEY_PERCENT) ||
				buttonText.equals(CalculatorKeyboard.KEY_SQR))  {
				if (display.size()>0) {
					double param1=display.pop();
					double result=0;
					if (buttonText.equals(CalculatorKeyboard.KEY_PERCENT)) result=param1/100;
					else
					if (buttonText.equals(CalculatorKeyboard.KEY_SQR)) result=param1*param1;
					else
					if (buttonText.equals(CalculatorKeyboard.KEY_SIGN)) result=-param1;
					else
					if (buttonText.equals(CalculatorKeyboard.KEY_INV)) result=(param1!=0)? 1.0/param1 : param1;
					else
					if (buttonText.equals(CalculatorKeyboard.KEY_SQRT)) result=(param1>=0)? Math.sqrt(param1) : param1;

					display.push(result);
				}
				
			}
			else
			if (buttonText.equals(CalculatorKeyboard.KEY_DOT)) {
				String text=display.getLabelText();
				if (text.length()>0)  {
					if (text.indexOf('.')<0) display.setLabelText(text+".");
				}
				else {
					display.setLabelText("0.");
				}
			}
			else
			if (buttonText.equals(CalculatorKeyboard.KEY_DEL))  {
				String text=display.getLabelText();
				if (text.length()>0)  {
					display.setLabelText(text.substring(0,text.length()-1));
				}
			}
			else
			if (buttonText.equals(CalculatorKeyboard.KEY_ENTER))  {
				// already done.
			}
			else
			if (buttonText.equals(CalculatorKeyboard.KEY_DROP))  {
				display.pop();
			}
			else
			if (buttonText.equals(CalculatorKeyboard.KEY_CLEAR) && buttonText.length()>0)  {
				display.clear();
			}
			else {
				display.setLabelText(display.getLabelText()+buttonText);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	/** Exits. */
	protected void exit() {
		frame.dispose();
		System.exit(0);
	}

}
