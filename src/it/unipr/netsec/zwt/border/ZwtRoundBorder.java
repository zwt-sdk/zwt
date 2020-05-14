package it.unipr.netsec.zwt.border;


import it.unipr.netsec.zwt.ZwtBorder;
import it.unipr.netsec.zwt.ZwtColor;
import it.unipr.netsec.zwt.ZwtGraphics;
import it.unipr.netsec.zwt.ZwtRect;


/** Round border.
  */
public class ZwtRoundBorder extends ZwtBorder {
	
	/** Round width */
	int roundWidth=20;

	/** Round height */
	int roundHeight=20;



	/** Creates a new border. */
	public ZwtRoundBorder() {
		
	}

	/** Sets roundness. */
	public void setRoundness(int roundWidth, int roundHeight) {
		this.roundWidth=roundWidth;
		this.roundHeight=roundHeight;
	}

	/** Sets roundness. */
	public void setRoundness(int roundness) {
		setRoundness(roundness,roundness);
	}

	/** Gets round width. */
	public int getRoundWidth(int roundWidth) {
		return roundWidth;
	}

	/** Gets round height. */
	public int getRoundHeight(int roundHeight) {
		return roundHeight;
	}

	@Override
	public void draw(ZwtGraphics g, int x, int y, int width, int height, ZwtRect area) {
		// TODO: limit to the given area
		ZwtColor color_NW=(front)? GRAY_BRIGHT : GRAY_DARK;
		ZwtColor color_SE=(front)? GRAY_DARK : GRAY_BRIGHT;
		int x0,x1,x2,x3,x4,x5,x6,x7,y0,y1,y2,y3,y4,y5,y6,y7;
		int rx=roundWidth/2;
		int ry=roundHeight/2;
		
		for (int t=0; t<thickness; t++) {
			
			// rows
			x0=x7=x;
			x1=x6=x+rx;
			x2=x5=x+width-1-rx;
			x3=x4=x+width-1;  
			y0=y3=y+ry;
			y1=y2=y;
			y4=y7=y+height-1-ry;
			y5=y6=y+height-1;
			// left and top
			g.setColor(color_NW);
			g.drawLine(x7,y7,x0,y0);
			g.drawLine(x1,y1,x2,y2);
			// right and down
			g.setColor(color_SE);
			g.drawLine(x3,y3,x4,y4);
			g.drawLine(x5,y5,x6,y6);
	
			// arcs
			ZwtColor color;
			int gray, i, j;
			for (int xo=0; xo<rx; xo++) {
				//int yo=(int)(ry*Math.sqrt(1-(double)xo*xo/(rx*rx)));
				int yo=(int)Math.ceil(ry*Math.sqrt(1-(double)xo*xo/(rx*rx)));
				// left top
				i=rx-xo;
				j=ry-yo;
				g.setColor(color_NW);
				i+=x;
				j+=y;
				g.drawLine(i,j,i,j);  
				// right top
				i=width-1-rx+xo;
				j=ry-yo;
				//gray=gray_NW-(gray_NW-gray_SE)*j/ry;
				//color=new ZwtColor(gray,gray,gray);
				//g.setColor(color);
				i+=x;
				j+=y;
				g.drawLine(i,j,i,j);  
				// right down
				i=width-1-rx+xo;
				j=height-1-ry+yo;
				g.setColor(color_SE);
				i+=x;
				j+=y;
				g.drawLine(i,j,i,j);  
				// left down
				i=rx-xo;
				j=height-1-ry+yo;
				//gray=gray_NW-(gray_NW-gray_SE)*i/rx;
				//color=new ZwtColor(gray,gray,gray);
				//g.setColor(color);
				i+=x;
				j+=y;
				g.drawLine(i,j,i,j);  
			}
			for (int yo=0; yo<ry; yo++) {
				//int xo=(int)(rx*Math.sqrt(1-(double)yo*yo/(ry*ry)));
				int xo=(int)Math.ceil(rx*Math.sqrt(1-(double)yo*yo/(ry*ry)));
				// left top
				i=rx-xo;
				j=ry-yo;
				g.setColor(color_NW);
				i+=x;
				j+=y;
				g.drawLine(i,j,i,j);  
				// right top
				i=width-1-rx+xo;
				j=ry-yo;
				//gray=gray_NW-(gray_NW-gray_SE)*j/ry;
				//color=new ZwtColor(gray,gray,gray);
				//g.setColor(color);
				i+=x;
				j+=y;
				g.drawLine(i,j,i,j);  
				// right down
				i=width-1-rx+xo;
				j=height-1-ry+yo;
				g.setColor(color_SE);
				i+=x;
				j+=y;
				g.drawLine(i,j,i,j);  
				// left down
				i=rx-xo;
				j=height-1-ry+yo;
				//gray=gray_NW-(gray_NW-gray_SE)*i/rx;
				//color=new ZwtColor(gray,gray,gray);
				//g.setColor(color);
				i+=x;
				j+=y;
				g.drawLine(i,j,i,j);  
			}
			x++;
			y++;
			width-=2;
			height-=2;
		}
	}
	
}
