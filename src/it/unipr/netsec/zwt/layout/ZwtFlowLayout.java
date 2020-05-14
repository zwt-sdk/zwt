package it.unipr.netsec.zwt.layout;


import java.util.Vector;

import it.unipr.netsec.zwt.ZwtComponent;
import it.unipr.netsec.zwt.ZwtDimension;
import it.unipr.netsec.zwt.ZwtLayout;
import it.unipr.netsec.zwt.ZwtPanel;
import it.unipr.netsec.zwt.ZwtPoint;

import java.util.Hashtable;


/** Flow layout.
  */
public class ZwtFlowLayout implements ZwtLayout {
	
	/** Whether is horizontal */
	boolean isHorizontal=true;

	/** Horizontal space */
	int hspace=4;

	/** vertical space */
	int vspace=4;


	/** Sets horizontal space */
	public void setHorizontalSpace(int hspace) {
		this.hspace=hspace;
	}

	/** Sets vertical space */
	public void setVspaceSpace(int vspace) {
		this.vspace=vspace;
	}

	@Override
	public void arrangeComponents(ZwtPanel panel, Vector<ZwtComponent> components, Hashtable<ZwtComponent,ZwtPoint> positions, Hashtable<ZwtComponent,Integer> anchors, Hashtable<ZwtComponent,ZwtDimension> sizes) {
		int borderThickness=(panel.hasBorder())? panel.getBorder().getThickness() : 0;
		int x=hspace+borderThickness;
		int y=vspace+borderThickness;
		positions.clear();
		sizes.clear();
		for (int i=0; i<components.size(); i++) {
			ZwtComponent c=(ZwtComponent)components.elementAt(i);
			int cWidth=c.getWidth();
			int cHeight=c.getHeight();
			if (isHorizontal) cHeight=panel.getHeight()-2*vspace;
			else cWidth=panel.getWidth()-2*hspace;
			c.setSize(cWidth,cHeight);
			positions.put(c,new ZwtPoint(x,y));
			sizes.put(c,new ZwtDimension(c.getWidth(),c.getHeight()));
			if (isHorizontal) x+=cWidth+hspace;
			else y+=cHeight+vspace;
		}
	}

}
