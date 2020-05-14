package it.unipr.netsec.zwt;



/** Rectangular dimension.
 * It has a width and a height value.
 */
public class ZwtDimension {
	
	/** Width */
	private int width;
	
	/** Height */
	private int height;


	/** Creates a new dimension.
	 * @param width the width
	 * @param height the height */
	public ZwtDimension() {
		setDimension(0,0);
	}

	/** Creates a new dimension. */
	public ZwtDimension(int width, int height) {
		setDimension(width,height);
	}

	/** Creates a new dimension.
	 * @param d the dimension */
	public ZwtDimension(ZwtDimension d) {
		setDimension(d);
	}

	/** Sets new dimension.
	 * @param width new width
	 * @param height new height */
	public void setDimension(int width, int height) {
		this.width=width;
		this.height=height;
	}

	/** Sets new dimension.
	 * @param d the new dimension */
	public void setDimension(ZwtDimension d) {
		setDimension(d.width,d.height);
	}

	/** Gets width.
	 * @return the width */
	public int getWidth() {
		return width;
	}
	
	/** Gets height.
	 * @return the height */
	public int getHeight() {
		return height;
	} 

	@Override
	protected Object clone() {
		return new ZwtDimension(this);
	} 

	@Override
	public boolean equals(Object obj) {
		try {
			ZwtDimension d=(ZwtDimension)obj;
			if (width==d.width && height==d.height) return true;
			else return false;
		}
		catch (Exception e) {  return false;  }
	}

	@Override
	public String toString() {
		return ""+width+"x"+height;
	} 
}
