package it.unipr.netsec.zwt;


import java.util.Enumeration;


/** A container of graphical components.
 * Components can be added and removed.
 */
public interface ZwtContainer {
	
	/** Clears this container removing all components. */
	public void clear();

	/** Whether a given component is present in the container.
	  * @param c the component to be added
	  * @return <i>true</i> if the component is present, <i>false</i> otherwise */
	public boolean hasComponent(ZwtComponent c);

	/** Adds a new component.
	  * @param c the component to be added */
	public void addComponent(ZwtComponent c);

	/** Adds a new component at the given coordinates.
	  * @param c the component to be added
	  * @param x the x-coordinate
	  * @param y the y-coordinate */
	public void addComponent(ZwtComponent c, int x, int y);

	/** Adds a new component at the given position.
	  * @param c the component to be added
	  * @param anchor integer value that specifies the alignment (it should be an "OR" combination of TOP, BOTTOM, VCENTER, LEFT, RIGHT, HCENTER values) */
	public void addComponent(ZwtComponent c, int anchor);

	/** Removes a component.
	  * @param c the component to be removed */
	public void removeComponent(ZwtComponent c);

	/** Gets all components.
	  * @return list of all components */
	public Enumeration<ZwtComponent> getComponents();

	/** Gets coordinates of a given component.
	  * The target component may be directly attached to this container or to some sub-container.
	  * @param c the component
	  * @return the coordinates */
	public ZwtPoint getCoordinates(ZwtComponent c);

	/** Gets size of a given component.
	  * The target component may be directly attached to this container or to some sub-container.
	  * @param c the component
	  * @return the size */
	public ZwtDimension getSize(ZwtComponent c);

	/** Gets the container of a given component.
	  * @param c the component
	  * @return the container */
	public ZwtContainer getContainerOf(ZwtComponent c);

	/** Gets the component that contains the <i>x,y</i> position.
	  * The top-most child component or sub-component is returned in the case where there is overlap in the components.
	  * @param x the x-coordinate
	  * @param y the y-coordinate
	  * @return the component */
	public ZwtComponent getComponentAt(int x, int y);
	
}
