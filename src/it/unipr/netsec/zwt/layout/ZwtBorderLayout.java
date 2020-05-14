package it.unipr.netsec.zwt.layout;


import java.util.Hashtable;
import java.util.Vector;

import it.unipr.netsec.zwt.ZwtComponent;
import it.unipr.netsec.zwt.ZwtDimension;
import it.unipr.netsec.zwt.ZwtLayout;
import it.unipr.netsec.zwt.ZwtPanel;
import it.unipr.netsec.zwt.ZwtPoint;


/** Border layout.
  */
public class ZwtBorderLayout implements ZwtLayout {
	
	/** NORTH. */
	public static final int NORTH=55;//ZwtGraphics.TOP;
	/** SOUTH. */
	public static final int SOUTH=56;//ZwtGraphics.BOTTOM;
	/** WEST. */
	public static final int WEST=57;//ZwtGraphics.LEFT;
	/** EST. */
	public static final int EST=58;//ZwtGraphics.RIGHT;
	/** CENTER. */
	public static final int CENTER=59;//ZwtGraphics.HCENTER;


	@Override
	public void arrangeComponents(ZwtPanel panel, Vector<ZwtComponent> components, Hashtable<ZwtComponent,ZwtPoint> positions, Hashtable<ZwtComponent,Integer> anchors, Hashtable<ZwtComponent,ZwtDimension> sizes) {
		int borderThickness=(panel.hasBorder())? panel.getBorder().getThickness() : 0;
		int x=borderThickness;
		int y=borderThickness;
		int width=panel.getWidth()-2*borderThickness;
		int height=panel.getHeight()-2*borderThickness;
		positions.clear();
		sizes.clear();
		// north
		for (int i=0; i<components.size(); i++) {
			ZwtComponent c=(ZwtComponent)components.elementAt(i);
			int cAnchor=((Integer)anchors.get(c)).intValue();
			if (cAnchor==NORTH) {
				c.setSize(width,c.getHeight());
				positions.put(c,new ZwtPoint(x,y));
				sizes.put(c,new ZwtDimension(c.getWidth(),c.getHeight()));
				y+=c.getHeight();
				height-=c.getHeight();
			}
		}
		// south
		for (int i=0; i<components.size(); i++) {
			ZwtComponent c=(ZwtComponent)components.elementAt(i);
			int cAnchor=((Integer)anchors.get(c)).intValue();
			if (cAnchor==SOUTH) {
				c.setSize(width,c.getHeight());
				positions.put(c,new ZwtPoint(x,y+height-c.getHeight()));
				sizes.put(c,new ZwtDimension(c.getWidth(),c.getHeight()));
				height-=c.getHeight();
			}
		}
		// west
		for (int i=0; i<components.size(); i++) {
			ZwtComponent c=(ZwtComponent)components.elementAt(i);
			int cAnchor=((Integer)anchors.get(c)).intValue();
			if (cAnchor==WEST) {
				c.setSize(c.getWidth(),height);
				positions.put(c,new ZwtPoint(x,y));
				sizes.put(c,new ZwtDimension(c.getWidth(),c.getHeight()));
				x+=c.getWidth();
				width-=c.getWidth();
			}
		}
		// est
		for (int i=0; i<components.size(); i++) {
			ZwtComponent c=(ZwtComponent)components.elementAt(i);
			int cAnchor=((Integer)anchors.get(c)).intValue();
			if (cAnchor==EST) {
				c.setSize(c.getWidth(),height);
				positions.put(c,new ZwtPoint(x+width-c.getWidth(),y));
				sizes.put(c,new ZwtDimension(c.getWidth(),c.getHeight()));
				width-=c.getWidth();
			}
		}
		// center
		for (int i=0; i<components.size(); i++) {
			ZwtComponent c=(ZwtComponent)components.elementAt(i);
			int cAnchor=((Integer)anchors.get(c)).intValue();
			//if (cAnchor==CENTER)
			if (cAnchor!=NORTH && cAnchor!=SOUTH && cAnchor!=WEST && cAnchor!=EST) {
				c.setSize(width,height);
				positions.put(c,new ZwtPoint(x,y));
				sizes.put(c,new ZwtDimension(c.getWidth(),c.getHeight()));
				//break;
			}
		}
	}

}
