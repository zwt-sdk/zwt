package it.unipr.netsec.zwt;




/** A point representing a location in (X,Y) integer coordinate space.
  */
public class ZwtPoint {
	
	/** X */
	private int x;
	
	/** Y */
	private int y;


	/** Creates a new point. */
	public ZwtPoint() {
		setXY(0,0);
	}

	/** Creates a new point. */
	public ZwtPoint(int x, int y) {
		setXY(x,y);
	}

	/** Creates a new point. */
	public ZwtPoint(ZwtPoint p) {
		setXY(p);
	}

	/** Sets new coordinates. */
	public void setXY(int x, int y) {
		this.x=x;
		this.y=y;
	}

	/** Sets new coordinates. */
	public void setXY(ZwtPoint p) {
		setXY(p.x,p.y);
	}

	/** Gets the X coordinate. */
	public int getX() {
		return x;
	}
	
	/** Gets the Y coordinate. */
	public int getY() {
		return y;
	} 

	/** Gets a new clone this object. */
	protected Object clone() {
		return new ZwtPoint(this);
	} 

	/** Whether this Point is equal to Object <i>obj</i> */
	public boolean equals(Object obj) {
		try {
			ZwtPoint p=(ZwtPoint)obj;
			if (x==p.x && y==p.y) return true;
			else return false;
		}
		catch (Exception e) {  return false;  }
	}

	/** Gets a String value for this object. */
	public String toString() {
		return "("+x+","+y+")";
	} 
}
