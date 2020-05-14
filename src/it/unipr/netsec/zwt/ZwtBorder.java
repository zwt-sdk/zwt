package it.unipr.netsec.zwt;



/** Border.
  */
public class ZwtBorder implements ZwtDrawable {
	
	/** Gray bright */
	protected static final ZwtColor GRAY_BRIGHT=new ZwtColor(0xF0F0F0);

	/** Gray dark */
	protected static final ZwtColor GRAY_DARK=new ZwtColor(0x404040);

	/** Default thickness */
	public static int DEFAULT_THICKNESS=1;
	
	
	/** Versus */
	protected boolean front=true;

	/** Thickness */
	protected int thickness=DEFAULT_THICKNESS;


	/** Creates a new border. */
	public ZwtBorder() {
	}

	/** Sets front. */
	public void setFront(boolean front) {
		this.front=front;
	}

	/** Gets front. */
	public boolean getFront() {
		return front;
	}

	/** Sets thickness. */
	public void setThickness(int thickness) {
		this.thickness=thickness;
	}

	/** Gets thickness. */
	public int getThickness() {
		return thickness;
	}

	@Override
	public void draw(ZwtGraphics g, int x, int y, int width, int height, ZwtRect area) {
		ZwtColor color_NW=(front)? GRAY_BRIGHT : GRAY_DARK;
		ZwtColor color_SE=(front)? GRAY_DARK : GRAY_BRIGHT;
		for (int t=0; t<thickness; t++) {
			int x1=x; 
			int y1=y;
			int x2=x+width-1;
			int y2=y+height-1;
			ZwtRect rect=area.intersection(x,y,width,height);
			/*g.setColor(color_NW);
			g.drawLine(x1,y2,x1,y1);
			g.drawLine(x1,y1,x2,y1);
			g.setColor(color_SE);
			g.drawLine(x2,y1,x2,y2);
			g.drawLine(x2,y2,x1,y2);*/
			// west
			g.setColor(color_NW);
			if (x1>=area.x1() && x1<=area.x2()) g.drawLine(x1,rect.y2(),x1,rect.y1());
			// north
			if (y1>=area.y1() && y1<=area.y2()) g.drawLine(rect.x1(),y1,rect.x2(),y1);
			// east
			g.setColor(color_SE);
			if (x2>=area.x1() && x2<=area.x2()) g.drawLine(x2,rect.y1(),x2,rect.y2());
			// south
			if (y2>=area.y1() && y2<=area.y2()) g.drawLine(rect.x2(),y2,rect.x1(),y2);
			x++;
			y++;
			width-=2;
			height-=2;
		}
	}

	/*@Override
	public void redraw(ZwtGraphics g, int x, int y, int width, int height) {
		draw(g,x,y,width,height);
	}*/

}
