package it.unipr.netsec.zwt.layout;


import java.util.Vector;

import it.unipr.netsec.zwt.ZwtComponent;
import it.unipr.netsec.zwt.ZwtDimension;
import it.unipr.netsec.zwt.ZwtLayout;
import it.unipr.netsec.zwt.ZwtPanel;
import it.unipr.netsec.zwt.ZwtPoint;

import java.util.Hashtable;


/** Grid layout.
  */
public class ZwtGridLayout implements ZwtLayout {
	
	/** Horizontal space */
	int hspace=2;

	/** vertical space */
	int vspace=2;

	/** Number of rows */
	int rows;

	/** Number of columns */
	int columns;


	/** Creates a new ZwtGridLayout. */
	public ZwtGridLayout(int rows, int columns) {
		this.rows=rows;
		this.columns=columns;
	}

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
		int width=panel.getWidth()-hspace-2*borderThickness;
		int height=panel.getHeight()-vspace-2*borderThickness;
		int rows=(this.rows>0)? this.rows : (components.size()+columns-1)/columns;
		int columns=(this.columns>0)? this.columns : (components.size()+rows-1)/rows;
		int rowHeight=height/rows;
		int columnWidth=width/columns;
		positions.clear();
		sizes.clear();
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				int index=i*columns+j;
				if (index<components.size()) {
					ZwtComponent c=(ZwtComponent)components.elementAt(index);
					c.setSize(columnWidth-hspace,rowHeight-vspace);
					positions.put(c,new ZwtPoint(x+j*width/columns,y+i*height/rows));
					sizes.put(c,new ZwtDimension(c.getWidth(),c.getHeight()));
				}
			}
		}
	}

}
