package it.unipr.netsec.zwt.layout;


import java.util.Vector;

import it.unipr.netsec.zwt.ZwtComponent;
import it.unipr.netsec.zwt.ZwtDimension;
import it.unipr.netsec.zwt.ZwtPanel;
import it.unipr.netsec.zwt.ZwtPoint;

import java.util.Hashtable;


/** 2D flow layout.
  */
public class Zwt2DFlowLayout extends ZwtFlowLayout {
	
	@Override
	public void arrangeComponents(ZwtPanel panel, Vector<ZwtComponent> components, Hashtable<ZwtComponent,ZwtPoint> positions, Hashtable<ZwtComponent,Integer> anchors, Hashtable<ZwtComponent,ZwtDimension> sizes) {
		int borderThickness=(panel.hasBorder())? panel.getBorder().getThickness() : 0;
		int x=hspace+borderThickness;
		int y=vspace+borderThickness;
		int maxHeight=0;
		int maxWidth=0;
		positions.clear();
		//sizes.clear();
		for (int i=0; i<components.size(); i++) {
			ZwtComponent c=components.elementAt(i);
			int cWidth=c.getWidth();
			int cHeight=c.getHeight();
			if (isHorizontal) {
				if ((x+cWidth)>=panel.getWidth()-borderThickness) {
					y+=maxHeight+vspace;
					x=hspace+borderThickness;
					maxHeight=0;
				}
			}
			else {
				if ((y+cHeight)>=panel.getHeight()-borderThickness) {
					x+=maxWidth+hspace;
					y=vspace+borderThickness;
					maxWidth=0;
				}
			} 
			positions.put(c,new ZwtPoint(x,y));
			//sizes.put(c,new ZwtDimension(c.getWidth(),c.getHeight()));
			if (isHorizontal) {
				x+=cWidth+hspace;
				if (maxHeight<cHeight) maxHeight=cHeight;
			}
			else {
				y+=cHeight+vspace;
				if (maxWidth<cWidth) maxWidth=cWidth;
			}
		}
	}

}
