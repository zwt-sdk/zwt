package it.unipr.netsec.zwt.examples.calc;

import java.util.Vector;

import it.unipr.netsec.zwt.ZwtBorder;
import it.unipr.netsec.zwt.ZwtColor;
import it.unipr.netsec.zwt.ZwtLabel;
import it.unipr.netsec.zwt.ZwtPanel;
import it.unipr.netsec.zwt.layout.ZwtGridLayout;

/** Calculator display.
 */
class CalculatorDisplay extends ZwtPanel {
	
	/** Number of display rows */
	static final int DISPLAY_ROWS=7;

	/** Display color */
	static final ZwtColor DISPLAY_COLOR=new ZwtColor(0xE0E0E0);

	/** Display ink */
	static final ZwtColor DISPLAY_INK=ZwtColor.BLACK;

	/** Display stack color */
	static final ZwtColor DISPLAY_STACK_COLOR=ZwtColor.BLACK;

	/** Display stack ink */
	static final ZwtColor DISPLAY_STACK_INK=new ZwtColor(0,255,0);

	
	/** Display first label */
	ZwtLabel displayLabel;

	/** Display stack (as Vector<ZLabel>) */
	Vector displayStack=new Vector();

	/** Stack (as Vector<Double>) */
	Vector stack=new Vector();


	/** Creates new CalculatorDisplay. */
	public CalculatorDisplay(int height) {
		super(0,height);
		setBorder(new ZwtBorder());
		getBorder().setFront(false);
		setColor(DISPLAY_STACK_COLOR);
		setLayout(new ZwtGridLayout(0,1));
		((ZwtGridLayout)getLayout()).setVspaceSpace(0);
		((ZwtGridLayout)getLayout()).setHorizontalSpace(0);
		for (int i=0; i<DISPLAY_ROWS-1; i++) {
			ZwtLabel label=createDisplayLabel();
			displayStack.insertElementAt(label,0);
			addComponent(label);
		}
		displayLabel=createDisplayLabel();
		displayLabel.setColor(DISPLAY_COLOR);
		displayLabel.setTextColor(DISPLAY_INK);
		addComponent(displayLabel);
	}

	/** Creates display panel. */
	private ZwtLabel createDisplayLabel() {
		ZwtLabel label=new ZwtLabel("");
		//label.setBorder(new ZBorder());
		//label.getBorder().setFront(false);
		label.setColor(DISPLAY_STACK_COLOR);
		label.setTextColor(DISPLAY_STACK_INK);
		label.setAlignment(ZwtLabel.ALIGN_RIGHT);
		return label;
	}

	/** Gets label text. */
	public String getLabelText() {
		return displayLabel.getText();
	}

	/** Sets label text. */
	public void setLabelText(String text) {
		displayLabel.setText(text);
		repaint();
	}

	/** Pushes new value. */
	public void push(double value) {
		stack.insertElementAt(new Double(value),0);
		update();
	}

	/** Gets size. */
	public int size() {
		return stack.size();
	}

	/** Clears all. */
	public void clear() {
		stack=new Vector();
		update();
		setLabelText("");
		repaint();
	}

	/** Drops top value. */
	public double pop() {
		double result=0;
		if (stack.size()>0) {
			result=((Double)stack.elementAt(0)).doubleValue();
			stack.removeElementAt(0);
			update();
		}
		return result;
	}

	/** Updates display. */
	private void update() {
		for (int i=0; i<displayStack.size(); i++) {
			((ZwtLabel)displayStack.elementAt(i)).setText((i<stack.size())? stack.elementAt(i).toString() : "");
		}
		repaint();
	}

}
