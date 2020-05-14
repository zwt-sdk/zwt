/*
 * Copyright (c) 2018 NetSec Lab - University of Parma (Italy)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND. IN NO EVENT
 * SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 * Author(s):
 * Luca Veltri (luca.veltri@unipr.it)
 */

package it.unipr.netsec.zwt;


import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

import it.unipr.netsec.zwt.ZwtComponent;
import it.unipr.netsec.zwt.ZwtContainer;
import it.unipr.netsec.zwt.ZwtDimension;
import it.unipr.netsec.zwt.ZwtFont;
import it.unipr.netsec.zwt.ZwtFrame;
import it.unipr.netsec.zwt.ZwtFrameListener;
import it.unipr.netsec.zwt.ZwtGraphics;
import it.unipr.netsec.zwt.ZwtPanel;
import it.unipr.netsec.zwt.ZwtPoint;
import it.unipr.netsec.zwt.ZwtPointer;

import java.util.Enumeration;
import java.util.Vector;


/** A graphical frame representing the main container of the GUI.
 * It includes a screen as output interface, and possibly command buttons
 * and/or a pointer as input interface.
 * <p/>
 * In order to display the ZwtFrame you need to attach the ZwtFrame to a proper device-dependent
 * graphic object (e.g. a Java swing JFrame in case of Java SE platform,
 * or a MIDlet in case of Java ME platform with CLDC and MIDP); this is performed through the
 * <i>setDisplay(object)</i> method. 
 */
public class ZwtFrame extends ZwtPanel {
	
	
	/** Set of all frames (as Vector<ZwtFrame>) */
	protected static Vector FRAMES=new Vector();

	
	/** MIDlet */
	MIDlet midlet;
	
	/** UI's canvas */
	protected Canvas canvas;
	
	/** UI's screen */
	protected ZwtGraphics screen;

	/** Pointer */
	protected ZwtPointer pointer=null;

	/** Last pressed point */
	ZwtPoint lastPressed=null;
		
	/** The listener */
	ZwtFrameListener listener=null;


	/** Repaints a given area of a component of a frame.
	 * The component is searched amongst all frames.
	 * The component may be directly attached to the frame or to a sub-component.
	 * @param c the component
	 * @param area the component area */
	public static void repaintFrameComponent(ZwtComponent c, ZwtRect area) {
		for (Enumeration e=FRAMES.elements(); e.hasMoreElements(); ) {
			ZwtFrame frame=(ZwtFrame)e.nextElement();
			if (frame==c || frame.getContainerOf(c)!=null) {
				frame.repaintComponentArea(c,area);
				return;
			}
		}
	}
	
	/** Creates a new ZwtFrame with a given width and height.
	 * The result is the same as creating a ZwtFrame, and then calling method {@link ZwtFrame#setSize(int, int)}.
	 * @param view platform-dependent view
	 * @param width the component width
	 * @param height the component height */
	public ZwtFrame(ZwtView view, int width, int height) {
		super();
		init();
		if (width>0 && height>0) setSize(width,height);
		initMidlet(view.midlet);
	}

	/** Inits the frame. */
	private void init() {
		FRAMES.addElement(this);
		ZwtFont.setDefaultFont(ZwtFont.getFont(ZwtFont.FACE_PROPORTIONAL,ZwtFont.STYLE_BOLD,ZwtFont.SIZE_SMALL));
		canvas=new Canvas() {
			protected void paint(Graphics g) {
				ZwtFrame.this.paintScreen(g);
			}
			protected void keyPressed(int keyCode) {
				ZwtFrame.this.keyPressed(keyCode);
			}
			protected void keyRepeated(int keyCode) {
				ZwtFrame.this.keyPressed(keyCode);
			}
			protected void keyReleased(int keyCode) {
				ZwtFrame.this.keyReleased(keyCode);
			}
			protected void pointerPressed(int x, int y) {
				lastPressed=new ZwtPoint(x,y);
				ZwtFrame.this.pointerPressed(x,y);
			}
			protected void pointerReleased(int x, int y) {
				if (lastPressed!=null) ZwtFrame.this.pointerReleased(x,y,lastPressed.getX(),lastPressed.getY(),false);
				else ZwtFrame.this.pointerReleased(x,y,x,y,false);
			}
		};
		width=canvas.getWidth();
		height=canvas.getHeight();
		screen=new ZwtGraphics(width,height);
		setBorder(null);
		repaintScreen();
	}

	/** Sets the listener oof this frame.
	 * @param listener the listener to set */
	public void setListener(ZwtFrameListener listener) {
		this.listener=listener;
	}

	/** Sets title.
	 * @param title the new title */
	public void setTitle(String title) {
		canvas.setTitle(title);
	}

	/** Requests a graphical keyboard. */
	public void requestKeyboard() {
	}

	/** Hides the graphical keyboard. */
	public void hideKeyboard() {
	}

	/** Clears the current UI. */
	public void clear() {
		super.clear();
		repaintScreen();
	}

	/** Paints the screen.
	 * @param g the graphic context */
	void paintScreen(Graphics g) {
		//paint(screen,0,0);
		g.drawImage(screen.toImage(),0,0,Graphics.TOP|Graphics.LEFT);
		if (pointer!=null) {
			ZwtPoint p=pointer.getCoordinates();
			g.drawImage(pointer.getIcon().getImage(),p.getX(),p.getY(),Graphics.TOP|Graphics.LEFT);
		}
	}

	/** Repaints a given area of the graphics.
	 * @param area the area */
	protected void repaintGraphicsArea(ZwtRect area) {
		canvas.repaint(area.x1(),area.y1(),area.width(),area.height());
	}

	/** Repaints a given component area on the canvas. */
	/*public void repaintCanvas(ZwtComponent c, int x, int y, int width, int height) {
		ZwtPoint p=(c==this)? new ZwtPoint(0,0) : getCoordinates(c);
		if (p!=null) {
			c.paint(screen,p.getX(),p.getY());
			canvas.repaint(p.getX()+x,p.getY()+y,width,height);
		}
	}*/

	/** Repaints the screen. */
	public void repaintScreen() {
		if (width>0 && height>0) paint(screen,0,0,new ZwtRect(0,0,width-1,height-1));
		canvas.repaint();
	}

	/** Repaints a given area of the screen.
	 * @param area the area */
	public void repaintScreenArea(ZwtRect area) {
		paint(screen,0,0,area);
		canvas.repaint(area.x1(),area.y1(),area.width(),area.height());
	}

	/** Repaints a given area of a component.
	 * The component may be directly attached to the frame or to a sub-component.
	 * @param c the component
	 * @param area component area */
	public void repaintComponentArea(ZwtComponent c, ZwtRect area) {
		if (c==this) {
			repaintScreenArea(area);
		}
		else {
			ZwtContainer u=getContainerOf(c);
			if (u!=null) {
				ZwtDimension size=u.getSize(c);
				if (size!=null && size.equals(c.getSize())) {
					// the size of the component c may be changed: repaint only the selected area
					ZwtPoint p=getCoordinates(c);
					if (p!=null) {
						int x=p.getX();
						int y=p.getY();
						ZwtRect gArea=area.move(x,y);
						c.paint(screen,x,y,gArea);
						repaintGraphicsArea(gArea);
					}
				}
				else {
					// the size of the component c may be changed: repaint also the container u of c
					if (u==this) repaint();
					else {
						ZwtPoint p=getCoordinates((ZwtComponent)u);
						if (p!=null) {
							int x=p.getX();
							int y=p.getY();
							ZwtRect gArea=area.move(x,y);
							((ZwtComponent)u).paint(screen,x,y,gArea);
							repaintGraphicsArea(gArea);
						}
					}
				}
			}
		}
	}

	/** Sets display on the given MIDlet.
	 * @param midlet the MIDlet that will contain the display */
	private void initMidlet(MIDlet midlet) {
		this.midlet=midlet;
		Display.getDisplay(midlet).setCurrent(canvas);
	}

	/** Disposes this component. */
	public void dispose() {
		midlet.notifyDestroyed();
		FRAMES.removeElement(this);
	}

}
