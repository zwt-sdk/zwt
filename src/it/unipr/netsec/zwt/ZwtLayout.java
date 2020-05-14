package it.unipr.netsec.zwt;


import java.util.Vector;
import java.util.Hashtable;


/** Layout.
  */
public interface ZwtLayout {
	
	/** Arranges component within a panel.
	  * @param panel the panel
	  * @param components vector of components (Vector of ZwtComponent)
	  * @param positions table that maps components to current component positions (Hashtable of ZwtComponent,ZwtPoint)
	  * @param anchors table that maps components to component anchors (Hashtable of ZwtComponent,Integer)
	  * @param sizes table that maps components to component sizes (Hashtable of ZwtComponent,ZwtDimension) */
	public void arrangeComponents(ZwtPanel panel, Vector<ZwtComponent> components, Hashtable<ZwtComponent,ZwtPoint> positions, Hashtable<ZwtComponent,Integer> anchors, Hashtable<ZwtComponent,ZwtDimension> sizes);

}
