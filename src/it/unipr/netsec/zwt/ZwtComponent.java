package it.unipr.netsec.zwt;



/** Graphical object that can be displayed on the screen and that may interact with the user.
 * Examples of components are the labels, buttons, and text fields, of a typical graphical user interface.
 */
public abstract class ZwtComponent {
	

	/** Default minimum width */
	protected static final int MIN_WIDTH=10;

	/** Default minimum height */
	protected static final int MIN_HEIGHT=10;

	/** Width */
	protected int width;

	/** Height */
	protected int height;

	/** Minimum width */
	protected int minWidth=MIN_WIDTH;

	/** Minimum height */
	protected int minHeight=MIN_HEIGHT;



	/** Creates a new component. */
	protected ZwtComponent() {
		width=0;
		height=0;
	}

	/** Creates a new component.
	 * @param width component width
	 * @param height component height */
	protected ZwtComponent(int width, int height) {
		this.width=(width<minWidth)? minWidth : width;
		this.height=(height<minHeight)? minHeight : height;
	}

	/** Processes the event when the screen is resized (e.g. for showing keyboard).
	 * @param width the new width of the screen
	 * @param height the new height of the screen
	 * @return <i>false</i> if the event is consumed */
	public boolean screenResized(int width, int height) {
		return true;
	}

	/** Processes the event when a key is pressed.
	  * @param keyCode the key code
	  * @return the key code passed to the next components or 0 if the event is consumed */
	public int keyPressed(int keyCode) {
		return keyCode;
	}

	/** Processes the event when a key is released.
	  * @param keyCode the key code
	  * @return the key code passed to the next components or 0 if the event is consumed */
	public int keyReleased(int keyCode) {
		return keyCode;
	}

	/** Processes the event when the pointer is pressed.
	  * @param x the x-coordinate of the pointer
	  * @param y the y-coordinate of the pointer
	  * @return <i>false</i> if the event is consumed */
	public boolean pointerPressed(int x, int y) {
		return true;
	}

	/** Processes the event when the pointer is moved.
	 * @param x the x-coordinate of the pointer
	 * @param y the y-coordinate of the pointer
	  * @param pressedX the x-coordinate of the pointer when it was pressed
	  * @param pressedY the y-coordinate of the pointer when it was pressed
	 * @return <i>false</i> if the event is consumed */
	public boolean pointerMoved(int x, int y, int pressedX, int pressedY) {
		return true;
	}

	/** Processes the event when the pointer is released.
	  * @param x the x-coordinate of the pointer
	  * @param y the y-coordinate of the pointer
	  * @param pressedX the x-coordinate of the pointer when it was pressed
	  * @param pressedY the y-coordinate of the pointer when it was pressed
	  * @param moved whether the pointer has moved
	  * @return <i>false</i> if the event is consumed */
	public boolean pointerReleased(int x, int y, int pressedX, int pressedY, boolean moved) {
		return true;
	}

	/** Sets size.
	  * @param width the new width
	  * @param height the new height */
	public void setSize(int width, int height) {
		this.width=width;
		this.height=height;
	}
	
	/** Sets size.
	  * @param d the new size */
	public void setSize(ZwtDimension d) {
		setSize(d.getWidth(),d.getHeight());
	}
	
	/** Gets size.
	  * @return the current size */
	public ZwtDimension getSize() {
		return new ZwtDimension(width,height);
	}

	/** Gets width.
	  * @return the current width */
	public int getWidth() {
		return width;
	}

	/** Gets height.
	  * @return the current height */
	public int getHeight() {
		return height;
	}

	/** Sets minimum width.
	  * @param minWidth the minimum width */
	public void setMinimumWidth(int minWidth) {
		this.minWidth=minWidth;
		if (width<minWidth) width=minWidth;
	}

	/** Gets minimum width.
	  * @return the minimum width */
	public int getMinimumWidth() {
		return minWidth;
	}

	/** Sets minimum height.
	  * @param minHeight the minimum height */
	public void setMinimumHeight(int minHeight) {
		this.minHeight=minHeight;
		if (height<minHeight) height=minHeight;
	}

	/** Gets minimum height.
	  * @return the minimum height */
	public int getMinimumHeight() {
		return minHeight;
	}

	/** Repaints this component. */
	protected void repaint() {
		if (width>0 && height>0) repaint(new ZwtRect(0,0,width-1,height-1));
	}

	/** Repaints a given area of this component.
	  * @param area the component area */
	protected void repaint(ZwtRect area) {
		ZwtFrame.repaintFrameComponent(this,area);
	}

	/** Renders the component at a specific position of a graphics.
	  * @param g the graphic context
	  * @param x the x-coordinate of the component within the graphics
	  * @param y the y-coordinate of the component within the graphics */
	//public abstract void paint(ZwtGraphics g, int x, int y);
	
	
	/** Renders a rectangular area of the component.
	  * @param g the graphics where this object has to be drawn onto
	  * @param x the x-coordinate of the component on the graphics
	  * @param y the y-coordinate of the component on the graphics
	  * @param rect the rectangular area of the graphics to be painted */
	public abstract void paint(ZwtGraphics g, int x, int y, ZwtRect rect);


	
	/** Disposes this component and all possible subcomponents. */
	//public abstract void dispose();


}
