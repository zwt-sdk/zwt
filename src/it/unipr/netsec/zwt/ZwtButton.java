package it.unipr.netsec.zwt;



/** Button.
  */
public class ZwtButton extends ZwtLabel {
	
	/** Button listener */
	ZwtButtonListener listener;

	/** Key code */
	//int keyCode;
	int[] keyCodes;

	/** Button pressed */
	boolean pressed=false;



	/** Creates a new ZwtButton.
	  * @param text Text of the button
	  * @param keyCode Key code associated with this button
	  * @param listener Button listener */
	public ZwtButton(String text, int keyCode, ZwtButtonListener listener) {
		this(text,new int[]{keyCode},listener);
	}

	/** Creates a new ZwtButton.
	  * @param text Text of the button
	  * @param keyCodes Array of key codes associated with this button
	  * @param listener Button listener */
	public ZwtButton(String text, int[] keyCodes, ZwtButtonListener listener) {
		super(text);
		this.keyCodes=keyCodes;
		this.listener=listener;
		border=new ZwtBorder();
		//border.setThickness(ZwtContext.DEFAULT_BORDER_THICKNESS);
	}

	@Override
	public int keyPressed(int keyCode) {
		if (keyCodes==null) return keyCode;
		// else
		for (int i=0; i< keyCodes.length; i++) {
			if (keyCodes[i]==keyCode) {
				if (!pressed) {
					border.setFront(!(pressed=true));
					repaint();
					if (listener!=null) listener.onButtonPushed(this);
				}
				return 0;
			}
		}
		// else
		return keyCode;
	}

	@Override
	public int keyReleased(int keyCode) {
		if (keyCodes==null) return keyCode;
		// else
		for (int i=0; i< keyCodes.length; i++) {
			if (keyCodes[i]==keyCode) {
				if (pressed) {
					border.setFront(!(pressed=false));
					repaint();
				}
				return 0;
			}
		}
		// else
		return keyCode;
	}

	@Override
	public boolean pointerPressed(int x, int y) {
		if (!pressed) {
			border.setFront(!(pressed=true));
			repaint();
		}
		return false;
	}

	@Override
	public boolean pointerReleased(int x, int y, int pressedX, int pressedY, boolean moved) {
		if (pressed) {
			border.setFront(!(pressed=false));
			repaint();
			if (listener!=null) listener.onButtonPushed(this);
		}
		return false;
	}

	/*@Override
	public void paint(ZwtGraphics g, int x, int y) {
		super.paint(g,x,y);
		int x0=(pressed)? x-1 : x;
		int y0=(pressed)? y-1 : y;
		drawLabelText(g,x0,y0);
	}*/

	@Override
	protected void drawLabelText(ZwtGraphics g, int x, int y, ZwtRect rect) {
		int x0=(pressed)? x : x-1;
		int y0=(pressed)? y : y-1;
		super.drawLabelText(g,x0,y0,rect);
	}

	/** Draws a button. */
	/*protected static void drawButton(ZwtGraphics g, int x, int y, int width, int height, int color, boolean pressed) {
		int transparency=(color&0xFF000000)>>24;
		if (transparency<0xFF) drawFloor(g,x,y,width,height,color);
		int border=(pressed)? -1 : 1;
		for (int b=0; b<BORDER_THICKNESS; b++) drawBorder(g,x+b,y+b,width-2*b,height-2*b,border);
	}*/

	@Override
	public String toString() {
		return getClass().getSimpleName()+"["+getText()+"]";
	}

}
