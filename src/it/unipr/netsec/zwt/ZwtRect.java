package it.unipr.netsec.zwt;


/** A rectangular area. */
public class ZwtRect {

	private int x1,y1,x2,y2;

	
	/** Creates a new rectangular area.
	 * @param x1 left coordinate
	 * @param y1 top coordinate
	 * @param x2 right coordinate
	 * @param y2 bottom coordinate */
	public ZwtRect(int x1, int y1, int x2, int y2) {
		if (x1>x2 || y1>y2) throw new RuntimeException("Invalid rectungular area: ("+x1+","+y1+") ("+x2+","+y2+")");
		// else
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	/** Creates a new rectangular area.
	 * @param p1 left-top coordinates
	 * @param p2 right-bottom coordinates */
	public ZwtRect(ZwtPoint p1, ZwtPoint p2) {
		this(p1.getX(),p1.getY(),p2.getX(),p2.getY());
	}
	
	/** Creates a new rectangular area.
	 * @param p left-top coordinates
	 * @param width width
	 * @param height height */
	public ZwtRect(ZwtPoint p, int width, int height) {
		this(p.getX(),p.getY(),p.getX()+width-1,p.getY()+height-1);
	}
	
	/** Gets the left coordinate x1.
	 * @return x1 value */
	public int x1() {
		return x1;
	}

	/** Gets the top coordinate y1.
	 * @return y1 value */
	public int y1() {
		return y1;
	}

	/** Gets the right coordinate x2.
	 * @return x2 value */
	public int x2() {
		return x2;
	}

	/** Gets the bottom coordinate y2.
	 * @return y2 value */
	public int y2() {
		return y2;
	}

	/** Gets the width.
	 * @return the width */
	public int width() {
		return x2-x1+1;
	}

	/** Gets the height.
	 * @return the height */
	public int height() {
		return y2-y1+1;
	}
	
	/** Whether it contains a given point.
	 * @param x x-coordinate of the point
	 * @param y y-coordinate of the point
	 * @return 'true' in case the point is within the area */
	public boolean contains(int x, int y) {
		return x>=x1 && x<=x2 && y>=y1 && y<=y2;
	}

	/** Whether it contains a given point.
	 * @param p the point
	 * @return 'true' in case the point is within the area */
	public boolean contains(ZwtPoint p) {
		return contains(p.getX(),p.getY());
	}

	/** Whether it intersects a given rectangular area.
	 * @param r the second rectangular area
	 * @return 'true' if intersection is not null */
	public boolean hasIntersection(ZwtRect r) {
		return x1<=r.x2 && x2>=r.x1 && y1<=r.y2 && y2>=r.y1;  
	}
	
	/** Whether it intersects a given rectangular area.
	 * @param x the left coordinate of the second rectangular area
	 * @param y the top coordinate of the second rectangular area
	 * @param width the width of the second rectangular area
	 * @param height the height of the second rectangular area
	 * @return 'true' if intersection is not null */
	public boolean hasIntersection(int x, int y, int width, int height) {
		return hasIntersection(new ZwtRect(x,y,x+width-1,y+height-1));
	}
	
	/** Gets the intersection of two rectangular areas.
	 * @param r the second rectangular area
	 * @return the intersection */
	public ZwtRect intersection(ZwtRect r) {
		try {
			return new ZwtRect(Math.max(x1,r.x1),Math.max(y1,r.y1),Math.min(x2,r.x2),Math.min(y2,r.y2));
		}
		catch (Exception e) {
			return null;			
		}
	}
	
	/** Gets the intersection of two rectangular areas.
	 * @param x the left coordinate of the second rectangular area
	 * @param y the top coordinate of the second rectangular area
	 * @param width the width of the second rectangular area
	 * @param height the height of the second rectangular area
	 * @return the intersection */
	public ZwtRect intersection(int x, int y, int width, int height) {
		return intersection(new ZwtRect(x,y,x+width-1,y+height-1));
	}
	
	/** Moves the area.
	 * @param x the horizontal shift
	 * @param y the vertical shift
	 * @return the new area */
	public ZwtRect move(int x, int y) {
		return new ZwtRect(x1+x,y1+y,x2+x,y2+y);
	}
	
	@Override
	public String toString() {
		return ""+x1+","+y1+","+x2+","+y2;
	} 

		
}
