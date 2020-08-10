package it.unipr.netsec.zwt.floor;


import it.unipr.netsec.zwt.ZwtColor;
import it.unipr.netsec.zwt.ZwtGraphics;
import it.unipr.netsec.zwt.ZwtRect;


/** Round floor.
  */
public class ZwtRoundFloor extends ZwtTransparentFloor {
	
	/** Round width */
	public static int ROUND_WIDTH=20;

	/** Round height */
	public static int ROUND_HEIGHT=20;

	
	/** Round width */
	int roundWidth=ROUND_WIDTH;

	/** Round height */
	int roundHeight=ROUND_HEIGHT;



	/** Creates a new floor. */
	public ZwtRoundFloor() {
		super();
	}

	/** Creates a new floor.
	 * @param roundWidth round width
	 * @param roundHeight round height
	 * @param opacity floor opacity */
	public ZwtRoundFloor(int roundWidth, int roundHeight, float opacity) {
		super(opacity);
		setRoundness(roundWidth,roundHeight);
	}

	/** Creates a new floor.
	 * @param roundWidth round width
	 * @param roundHeight round height */
	public ZwtRoundFloor(int roundWidth, int roundHeight) {
		super();
		setRoundness(roundWidth,roundHeight);
	}

	/** Creates a new floor.
	 * @param roundness round width and height */
	public ZwtRoundFloor(int roundness) {
		super();
		setRoundness(roundness);
	}

	/** Sets roundness.
	 * @param roundWidth round width
	 * @param roundHeight round height */
	public void setRoundness(int roundWidth, int roundHeight) {
		this.roundWidth=roundWidth;
		this.roundHeight=roundHeight;
	}

	/** Sets roundness.
	 * @param roundness round width and height */
	public void setRoundness(int roundness) {
		setRoundness(roundness,roundness);
	}

	/** Gets round width. */
	public int getRoundWidth() {
		return roundWidth;
	}

	/** Gets round height. */
	public int getRoundHeight() {
		return roundHeight;
	}

	@Override
	public void draw(ZwtGraphics g, int x, int y, int width, int height, ZwtRect area) {
		// TODO: limit to the given area
		int dataSize=width*height;
		if (backArgbData==null || backArgbData.length!=dataSize) {
			backArgbData=new int[dataSize];
			g.getRGB(backArgbData,0,width,x,y,width,height);
		} 
		float alpha0=color.getAlpha();
		if (alpha0<=0) {
			return;
		}
		// else
		float rx=(float)roundWidth/2;
		float ry=(float)roundHeight/2;
		if (alpha0>=1 && rx==0 && ry==0) {
			g.setColor(color);
			g.fillRect(x,y,width,height);
			return;
		}
		// else
		int[] argbData=new int[width*height];
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				boolean paint=false;
				if (rx==0 || ry==0 || (i>=rx && i<=(width-rx)) || (j>=ry && j<=(height-ry))) paint=true;
				else {
					float xo=(i<rx)? rx-i : rx-width+i;
					float yo=(j<ry)? ry-j : ry-height+j;
					float yp=ry*(float)Math.sqrt(1-xo*xo/(rx*rx));
					if (yo<=yp) paint=true;
				}
				int index=i+j*width;
				if (paint) {
					ZwtColor backgroud=new ZwtColor(backArgbData[index],true);
					argbData[index]=ZwtColor.sum(backgroud,color).getSRGB();
				}
				else argbData[index]=backArgbData[index];
			}
		}
		g.drawRGB(argbData,0,width,x,y,width,height,false);
	}
	
}
