package it.unipr.netsec.zwt;


import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


/** Panel.
  * Is is a rectangular area that may have a specific size, a background color,
  * an image, and other separated component.
  * <p>
  * Any Component (panel, label, etc.) can be added to a panel at a specific
  * relative position by means of the {@link #addComponent(ZwtComponent, int, int)} method.
  */
public class ZwtPanel extends ZwtFilledComponent implements ZwtContainer {


	/** Background image */
	ZwtImage image=null;

	/** Whether the background image is replicated all over the panel or just centered */
	boolean imageReplicated=false;

	/** List of added components */
	Vector<ZwtComponent> components=new Vector<>();

	/** Table of component positions */
	Hashtable<ZwtComponent,ZwtPoint> componentPositions=new Hashtable<>();

	/** Table of component sizes */
	Hashtable<ZwtComponent,ZwtDimension> componentSizes=new Hashtable<>();

	/** Table of component anchors */
	Hashtable<ZwtComponent,Integer> componentAnchors=new Hashtable<>();

	/** Layout */
	ZwtLayout layout=null;


	/** Creates a new ZwtPanel. */
	protected ZwtPanel() {
		super();
	}

	/** Creates a new ZwtPanel.
	 * @param width the panel width
	 * @param height the panel height */
	public ZwtPanel(int width, int height) {
		this.width=width;
		this.height=height;
	}

	/** Sets background image.
	 * @param image the new background image */
	public void setImage(ZwtImage image) {
		setImage(image,false);
	}

	/** Sets whether the background image has to be horizontally and vertically replicated. 
	 * @param image the new background image
	 * @param imageReplicated <b>true</b> if the image has to be replicated, <b>false</b> otherwise */
	public void setImage(ZwtImage image, boolean imageReplicated) {
		this.image=image;
		this.imageReplicated=imageReplicated;
	}

	/** Gets background image.
	 * @return the background image, if any */
	public ZwtImage getImage() {
		return image;
	}

	/** Sets layout.
	 * @param layout the new layout */
	public void setLayout(ZwtLayout layout) {
		this.layout=layout;
		repaint();
	}

	/** Gets the layout.
	 * @return the layout */
	public ZwtLayout getLayout() {
		return layout;
	}

	/** Clears the current UI.
	 * It removes all components that have been previously added. */
	public void clear() {
		components=new Vector<ZwtComponent>();
		componentPositions=new Hashtable<ZwtComponent,ZwtPoint>();
		componentAnchors=new Hashtable<ZwtComponent,Integer>();
		componentSizes=new Hashtable<ZwtComponent,ZwtDimension>();
	}

	/** Whether there is a given component.
	 * @param c the component
	 * @return <code>true</code> if the given component is present, <code>false</code> otherwise */
	public boolean hasComponent(ZwtComponent c) {
		/*for (int i=0; i<components.size(); i++) {
			if (((ZwtComponent)components.elementAt(i)).equals(c)) return true;
		}
		return false;*/
		return componentPositions.containsKey(c);
	}

	@Override
	public void addComponent(ZwtComponent c) {
		addComponent(c,0,0);
	}

	@Override
	public void addComponent(ZwtComponent c, int x, int y) {
		if (hasComponent(c)) return;
		// else
		synchronized (components) {
			components.addElement(c);
			componentPositions.put(c,new ZwtPoint(x,y));
			componentAnchors.put(c,new Integer(0));
			componentSizes.put(c,new ZwtDimension(c.getWidth(),c.getHeight()));
		}
		if (layout==null) c.repaint();
		else repaint();
	}

	@Override
	public void addComponent(ZwtComponent c, int anchor) {
		if (hasComponent(c)) return;
		// else
		int x=0;
		int y=0;
		if ((anchor&ZwtAnchor.LEFT)!=0) x=0;
		else
		if ((anchor&ZwtAnchor.RIGHT)!=0) x=width-c.getWidth();
		else
		if ((anchor&ZwtAnchor.HCENTER)!=0) x=(width-c.getWidth())/2;

		if ((anchor&ZwtAnchor.TOP)!=0) y=0;
		else
		if ((anchor&ZwtAnchor.BOTTOM)!=0) y=height-c.getHeight();
		else
		if ((anchor&ZwtAnchor.VCENTER)!=0) y=(height-c.getHeight())/2;
		// add component
		synchronized (components) {
			components.addElement(c);
			componentPositions.put(c,new ZwtPoint(x,y));
			componentAnchors.put(c,new Integer(anchor));
		}
		if (layout==null) c.repaint();
		else repaint();
	}

	@Override
	public void removeComponent(ZwtComponent c) {
		synchronized (components) {
			for (int i=0; i<components.size(); i++) {
				if (((ZwtComponent)components.elementAt(i)).equals(c)) {
					components.removeElementAt(i);
					ZwtPoint p=(ZwtPoint)componentPositions.get(c);
					componentPositions.remove(c);
					componentAnchors.remove(c);
					componentSizes.remove(c);
					if (c.getWidth()>0 && c.getHeight()>0) repaint(new ZwtRect(p,c.getWidth(),c.getHeight()));
					return;
				}
			}
		}
	}

	/** Moves a given component.
	 * @param c the component to move
	 * @param dx the distance for the x coordinate
	 * @param dy the distance for the y coordinate */
	public void moveComponent(ZwtComponent c, int dx, int dy) {
		synchronized (components) {
			if (componentPositions.containsKey(c)) {
				ZwtPoint coordinates=getCoordinates(c);
				int x=coordinates.getX();
				int y=coordinates.getY();
				componentPositions.remove(c);
				componentPositions.put(c,new ZwtPoint(x+dx,y+dy));
			}
		}
		repaint();
	}
	
	/** Moves all components.
	 * @param dx the distance for the x coordinate
	 * @param dy the distance for the y coordinate */
	public void moveComponents(int dx, int dy) {
		synchronized (components) {
			for (int i=0; i<components.size(); i++) {
				ZwtComponent c=components.elementAt(i);
				ZwtPoint coordinates=getCoordinates(c);
				int x=coordinates.getX();
				int y=coordinates.getY();
				componentPositions.remove(c);
				componentPositions.put(c,new ZwtPoint(x+dx,y+dy));
			}
		}
		repaint();
	}
	
	@Override
	public Enumeration<ZwtComponent> getComponents() {
		return components.elements();
	}

	@Override
	public ZwtPoint getCoordinates(ZwtComponent c) {
		synchronized (components) {
			if (componentPositions.containsKey(c)) return (ZwtPoint)componentPositions.get(c);
			// else
			for (int i=components.size()-1; i>=0; i--) {
				ZwtComponent u=(ZwtComponent)components.elementAt(i);
				if (u instanceof ZwtContainer) {
					ZwtPoint p=((ZwtContainer)u).getCoordinates(c);
					if (p!=null) {
						ZwtPoint q=(ZwtPoint)componentPositions.get(u);
						return new ZwtPoint(q.getX()+p.getX(),q.getY()+p.getY());
					}
				}
			}
		}
		// else
		return null;
	}

	@Override
	public ZwtDimension getSize(ZwtComponent c) {
		synchronized (components) {
			if (components.contains(c)) return (ZwtDimension)componentSizes.get(c);
			// else
			ZwtContainer u=getContainerOf(c);
			if (u!=null) return u.getSize(c);
			else return (ZwtDimension)null;
		}
	}

	@Override
	public ZwtComponent getComponentAt(int x, int y) {
		synchronized (components) {
			for (int i=components.size()-1; i>=0; i--) {
				ZwtComponent c=(ZwtComponent)components.elementAt(i);
				ZwtPoint p=(ZwtPoint)componentPositions.get(c);
				int xi=x-p.getX();
				int yi=y-p.getY();
				if (xi>=0 && xi<c.getWidth() && yi>=0 && yi<c.getHeight()) {
					if (c instanceof ZwtContainer) return ((ZwtContainer)c).getComponentAt(xi,yi);
					// else
					return c;
				}
			}
		}
		return null;
	}

	@Override
	public ZwtContainer getContainerOf(ZwtComponent c) {
		synchronized (components) {
			if (components.contains(c)) return this;
			// else
			for (int i=components.size()-1; i>=0; i--) {
				ZwtComponent u=(ZwtComponent)components.elementAt(i);
				if (u instanceof ZwtContainer) {
					ZwtContainer container=((ZwtContainer)u).getContainerOf(c);
					if (container!=null) return container;
				}
			}
		}
		// else
		return null;
	}

	@Override
	public boolean screenResized(int width, int height) {
		boolean returnValue = true;
		synchronized (components) {
			for (int i=components.size()-1; i>=0; i--) {
				ZwtComponent c=(ZwtComponent)components.elementAt(i);
				returnValue=c.screenResized(width,height);
			}
		}
		return returnValue;
	}

	@Override
	public int keyPressed(int keyCode) {
		synchronized (components) {
			for (int i=components.size()-1; i>=0 && keyCode!=0; i--) {
				ZwtComponent c=(ZwtComponent)components.elementAt(i);
				keyCode=c.keyPressed(keyCode);
			}
		}
		return keyCode;
	}

	@Override
	public int keyReleased(int keyCode) {
		synchronized (components) {
			for (int i=components.size()-1; i>=0 && keyCode!=0; i--) {
				ZwtComponent c=(ZwtComponent)components.elementAt(i);
				keyCode=c.keyReleased(keyCode);
			}
		}
		return keyCode;
	}

	@Override
	public boolean pointerPressed(int x, int y) {
		/*ZwtComponent c=getComponentAt(x,y);
		if (c!=null) {
			ZwtPoint p=getCoordinates(c);
			return c.pointerPressed(x-p.getX(),y-p.getY());
		}
		else return true;
		*/
		boolean pointerAvailable=true;
		synchronized (components) {
			for (int i=components.size()-1; i>=0 && pointerAvailable; i--) {
				ZwtComponent c=(ZwtComponent)components.elementAt(i);
				ZwtPoint p=(ZwtPoint)componentPositions.get(c);
				int xi=x-p.getX();
				int yi=y-p.getY();
				if (xi>=0 && xi<c.getWidth() && yi>=0 && yi<c.getHeight()) pointerAvailable=c.pointerPressed(xi,yi);
			}
		}
		return pointerAvailable;
	}

	@Override
	public boolean pointerMoved(int x, int y, int pressedX, int pressedY) {
		/*ZwtComponent c=getComponentAt(x,y);
		if (c!=null) {
			ZwtPoint p=getCoordinates(c);
			return c.pointerMoved(x-p.getX(),y-p.getY());
		}
		else return true;
		*/
		boolean pointerAvailable=true;
		synchronized (components) {
			for (int i=components.size()-1; i>=0 && pointerAvailable; i--) {
				ZwtComponent c=(ZwtComponent)components.elementAt(i);
				ZwtPoint p=(ZwtPoint)componentPositions.get(c);
				int xi=x-p.getX();
				int yi=y-p.getY();
				if (xi>=0 && xi<c.getWidth() && yi>=0 && yi<c.getHeight()) pointerAvailable=c.pointerMoved(xi,yi,pressedX-p.getX(),pressedY-p.getY());
			}
		}
		return pointerAvailable;
	}

	@Override
	public boolean pointerReleased(int x, int y, int pressedX, int pressedY, boolean moved) {
		/*ZwtComponent c=getComponentAt(x,y);
		if (c!=null) {
			ZwtPoint p=getCoordinates(c);
			return c.pointerReleased(x-p.getX(),y-p.getY());
		}
		else return true;
		*/
		boolean pointerAvailable=true;
		synchronized (components) {
			for (int i=components.size()-1; i>=0 && pointerAvailable; i--) {
				ZwtComponent c=(ZwtComponent)components.elementAt(i);
				ZwtPoint p=(ZwtPoint)componentPositions.get(c);
				int xi=x-p.getX();
				int yi=y-p.getY();
				if (xi>=0 && xi<c.getWidth() && yi>=0 && yi<c.getHeight()) pointerAvailable=c.pointerReleased(xi,yi,pressedX-p.getX(),pressedY-p.getY(),moved);
			}
		}
		return pointerAvailable;
	}

	@Override
	public void paint(ZwtGraphics g, int x, int y, ZwtRect area) {
		if (!area.hasIntersection(x,y,width,height)) return;
		// else
		super.paint(g,x,y,area);
		drawImange(g,x,y,area);
		if (layout!=null) layout.arrangeComponents(this,components,componentPositions,componentAnchors,componentSizes);
		drawComponents(g,x,y,area);
	}

	/** Draws the background image.
	 * @param g the graphic context
	 * @param x the x-coordinate within the graphics where the image has to be drawn
	 * @param y the y-coordinate within the graphics where the image has to be drawn
	 * @param area the graphics area to be drawn */
	protected void drawImange(ZwtGraphics g, int x, int y, ZwtRect area) {
		if (image==null) return;
		// else
		int imageWidth=image.getWidth();
		int imageHeight=image.getHeight();
		if (imageWidth<=0 || imageHeight<=0) return;
		// else
		if (imageReplicated) {
			for (int dx=0; dx<width; dx+=imageWidth)
				for (int dy=0; dy<height; dy+=imageHeight) image.paint(g,x+dx,y+dy,area);
		}
		else {
			x+=(width-imageWidth)/2;
			y+=(height-imageHeight)/2;
			image.paint(g,x,y,area);
		}
	}

	/** Draws all inner components that are at given position.
	 * @param g the graphic context
	 * @param x the x-coordinate of the position
	 * @param y the y-coordinate of the position
	 * @param area the graphics area to be drawn */
	protected void drawComponents(ZwtGraphics g, int x, int y, ZwtRect area) {
		synchronized (components) {
			for (int i=0; i<components.size(); i++) {
				ZwtComponent c=(ZwtComponent)components.elementAt(i);
				ZwtPoint p=(ZwtPoint)componentPositions.get(c);
				if (p!=null) c.paint(g,x+p.getX(),y+p.getY(),area);
			}
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName()+"["+getColor()+"]";
	}

}
