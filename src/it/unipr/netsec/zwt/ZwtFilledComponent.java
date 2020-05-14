package it.unipr.netsec.zwt;



/** Filled component.
  * A filled component has a floor and a border.
  */
public abstract class ZwtFilledComponent extends ZwtComponent {
	
	/** Floor */
	ZwtFloor floor=new ZwtFloor();

	/** Border */
	ZwtBorder border=new ZwtBorder();



	/** Creates a new ZwtFilledComponent. */
	protected ZwtFilledComponent() {
		super();
	}

	/** Creates a new ZwtFilledComponent.
	 * @param width the component width
	 * @param height the component height */
	protected ZwtFilledComponent(int width, int height) {
		super(width,height);
	}

	/** Sets floor.
	 * @param floor the new component floor */
	public void setFloor(ZwtFloor floor) {
		this.floor=floor;
	}

	/** Gets floor.
	 * @return the component floor */
	public ZwtFloor getFloor() {
		return floor;
	}

	/** Whether it has a floor.
	 * @return <b>true</b> if the component has a floor, <b>false</b> otherwise */
	public boolean hasFloor() {
		return floor!=null;
	}

	/** Sets border.
	 * @param border the new component border */
	public void setBorder(ZwtBorder border) {
		this.border=border;
	}

	/** Gets border.
	 * @return the component border */
	public ZwtBorder getBorder() {
		return border;
	}

	/** Whether it has a border.
	* @return <b>true</b> if the component has a border, <b>false</b> otherwise */
	public boolean hasBorder() {
		return border!=null;
	}

	/** Sets floor color.
	 * @param color the new floor color */
	public void setColor(ZwtColor color) {
		if (floor!=null) floor.setColor(color);
	}

	/** Gets floor color.
	 * @return the current floor color */
	public ZwtColor getColor() {
		if (floor!=null) return floor.getColor();
		else return ZwtColor.BLACK;
	}

	/** Draws panel floor. */
	/*protected static void drawFloor(ZwtGraphics g, int x, int y, int width, int height, int color) {
		int transparency=(color&0xFF000000)>>>24;
		if (transparency<0xFF) return;
		// else
		g.setColor(color);
		g.fillRect(x,y,width,height);
	}*/

	/** Draws panel border. */
	/*protected static void drawBorder(ZwtGraphics g, int x, int y, int width, int height, int border) {
		if (border==0) return;
		// else
		int color_NW=(border>0)? 0x00F0F0F0 : 0x00404040;
		int color_SE=(border>0)? 0x00404040 : 0x00F0F0F0;
		int x1,x2,y1,y2;
		x1=x; 
		y1=y;
		x2=x+width-1;
		y2=y+height-1;
		g.setColor(color_NW);
		g.drawLine(x1,y2,x1,y1);
		g.drawLine(x1,y1,x2,y1);
		g.setColor(color_SE);
		g.drawLine(x2,y1,x2,y2);
		g.drawLine(x2,y2,x1,y2);
	}*/

	@Override
	public void paint(ZwtGraphics g, int x, int y, ZwtRect area) {
		//super.paint(g,x,y);
		if (area.hasIntersection(x,y,width,height)) {
			ZwtColor colorSaved=g.getColor();
			if (floor!=null) floor.draw(g,x,y,width,height,area);
			if (border!=null) border.draw(g,x,y,width,height,area);
			g.setColor(colorSaved);			
		}
	}

	/*@Override
	public void dispose() {
		// nothing to do
	}*/

}
