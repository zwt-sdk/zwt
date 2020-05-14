package it.unipr.netsec.zwt;



/** A drawable object must implement the method {@link ZwtDrawable#draw(ZwtGraphics, int, int, int, int)}.
  */
public interface ZwtDrawable {
	
	/** Draws this object within a given area.
	  * @param g the graphics where this object has to be drawn onto
	  * @param x the x-coordinate
	  * @param y the y-coordinate
	  * @param width the width of the object
	  * @param height the height of the object
	  * @param area the area of the graphics where the object has to be drawn */
	public void draw(ZwtGraphics g, int x, int y, int width, int height, ZwtRect area);

}
