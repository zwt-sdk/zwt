package it.unipr.netsec.zwt.floor;


import it.unipr.netsec.zwt.ZwtColor;
import it.unipr.netsec.zwt.ZwtFloor;
import it.unipr.netsec.zwt.ZwtGraphics;
import it.unipr.netsec.zwt.ZwtRect;


/** Transparent floor.
  * A transparent floor has a background color and an opacity from 0.0 (completely transparent) to 1.0 (completely opaque).
  */
public class ZwtTransparentFloor extends ZwtFloor {
	
	/** Background argb data */
	int[] backArgbData=null;


	/** Creates a new floor. */
	public ZwtTransparentFloor() {
		super();
		setOpacity(0.2F);
	}

	/** Creates a new floor.
	 * @param opacity floor opacity */
	public ZwtTransparentFloor(float opacity) {
		super();
		setOpacity(opacity);
	}

	/** Sets only the opacity component of the color. Opacity ranges from 0.0 (completely transparent) to 1.0 (completely opaque). */
	public void setOpacity(float opacity) {
		color=new ZwtColor(color.getRGB(),opacity);
	}

	/** Gets only the opacity component of the color. */
	public float getOpacity() {
		return color.getAlpha();
	}

	/** Sets only the transparency component of the color. Transparency ranges from 0x00 (completely opaque) to 0xFF (completely transparent). */
	/*public void setTransparency(int transparency) {
		setOpacity((255-transparency)/255.0f);
	}*/

	/** Gets only the transparency component of the color. */
	/*public int getTransparency() {
		return 255-(int)(getOpacity()*255);
	}*/

	@Override
	public void draw(ZwtGraphics g, int x, int y, int width, int height, ZwtRect area) {
		// TODO: limit to the given area
		int dataSize=width*height;
		if (backArgbData==null || backArgbData.length!=dataSize) {
			backArgbData=new int[dataSize];
			g.getRGB(backArgbData,0,width,x,y,width,height);
		} 
		float alpha=color.getAlpha();
		if (alpha<=0) {
			return;
		}
		// else
		if (alpha>=1) {
			g.setColor(color);
			g.fillRect(x,y,width,height);
			return;
		}
		// else
		int[] argbData=new int[width*height];
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				ZwtColor backgroud=new ZwtColor(backArgbData[i+j*width],true);
				argbData[i+j*width]=ZwtColor.sum(backgroud,color).getSRGB();
			}
		}
		g.drawRGB(argbData,0,width,x,y,width,height,false);
	}
	
}
