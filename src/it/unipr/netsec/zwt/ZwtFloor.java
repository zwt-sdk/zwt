package it.unipr.netsec.zwt;



/** Floor.
  * Each floor has a background color.
  */
public class ZwtFloor implements ZwtDrawable {
	
	/** Color */
	protected ZwtColor color=new ZwtColor(0xaaaaaa);


	/** Creates a new ZwtFloor. */
	public ZwtFloor() {
		
	}

	/** Sets the color.
	 * @param color the new color */
	public void setColor(ZwtColor color) {
		this.color=color;
	}

	/** Gets the color.
	 * @return the color */
	public ZwtColor getColor() {
		return color;
	}

	@Override
	public void draw(ZwtGraphics g, int x, int y, int width, int height, ZwtRect area) {
		ZwtColor savedColor=g.getColor();
		g.setColor(color);
		ZwtRect rect=area.intersection(x,y,width,height);
		g.fillRect(rect.x1(),rect.y1(),rect.width(),rect.height());
		g.setColor(savedColor);
	}

}
