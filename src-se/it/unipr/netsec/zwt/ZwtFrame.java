package it.unipr.netsec.zwt;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import it.unipr.netsec.zwt.ZwtComponent;
import it.unipr.netsec.zwt.ZwtContainer;
import it.unipr.netsec.zwt.ZwtDimension;
import it.unipr.netsec.zwt.ZwtFrameListener;
import it.unipr.netsec.zwt.ZwtPanel;
import it.unipr.netsec.zwt.ZwtPoint;
import it.unipr.netsec.zwt.ZwtPointer;


/** The UI frame.
 * It includes a screen as output interface, possibly command buttons,
 * and/or a pointer as input interface.
 * <p>
 * In order to display the ZwtFrame you need to pass to the constructor a {@link ZwtView}
 * - a device-dependent graphic object.
 */
public class ZwtFrame extends ZwtPanel {
	
	/** Pointer minimum perturbation for movement detection */
	private static int POINTER_MIN_PERTURBATION=5;

	/** Virtual pointer */
	protected ZwtPointer pointer=null;

	/** Last pressed point */
	private ZwtPoint lastPressed=null;

	/** Whether the pointer has moved */
	private boolean pointerMoved=false;

	/** Blank rectangular area of the screen onto which the application can draw or from which the application can trap input events from the user.
	 * Here it is used for capturing requests, from the underlying system, for repainting the screen or for handling input events (pointer and keyboard). */
	private Canvas canvas;
	
	/** Frame graphics */
	private ZwtGraphics graphics;

	/** The frame title */
	private String title=null;
		
	/** The listener */
	private ZwtFrameListener listener=null;

	/** The Java swing JFrame used for displaying this frame */
	private JFrame jframe=null;

	/** The Java window listener */
	private WindowAdapter thisWindowListener=null;

	
	/** The main frame */
	private static ZwtFrame FRAME;

	
	/** Repaints a given area of a component.
	 * The component is searched amongst all frames.
	 * The component may be directly attached to the frame or to a sub-component.
	 * @param c the component
	 * @param area the component area */
	public static void repaintFrameComponent(ZwtComponent c, ZwtRect area) {
		if (FRAME==c || FRAME.getContainerOf(c)!=null) {
			FRAME.repaintComponentArea(c,area);
			return;
		}
	}

	
	/** Creates a new frame. */
	public ZwtFrame() {
		super();
		init();
		initJFrame(null);
	}

	/** Creates a new frame with a given width and height.
	 * The result is the same as creating a ZwtFrame, and then calling method {@link ZwtFrame#setSize(int, int)}.
	 * @param view platform-dependent view
	 * @param width the component width
	 * @param height the component height */
	public ZwtFrame(ZwtView view, int width, int height) {
		this(view.frame,width,height);
	}

	/** Creates a new frame with a given width and height.
	 * The result is the same as creating a ZwtFrame, and then calling method {@link ZwtFrame#setSize(int, int)}.
	 * @param frame platform-dependent frame
	 * @param width the component width
	 * @param height the component height */
	public ZwtFrame(JFrame frame, int width, int height) {
		super(width,height);
		init();
		initJFrame(frame);
	}

	/** Initializes the frame. */
	private void init() {
		FRAME=this;
		canvas=new Canvas() {
			{ enableEvents(MouseEvent.BUTTON1_MASK | KeyEvent.KEY_EVENT_MASK | MouseEvent.MOUSE_MOTION_EVENT_MASK); }
			@Override
			public void paint(Graphics g) { ZwtFrame.this.paintGraphics(g); }
			@Override
			protected void processKeyEvent(KeyEvent e) { ZwtFrame.this.processKeyEvent(e); }
			@Override
			protected void processMouseEvent(MouseEvent e) { ZwtFrame.this.processMouseEvent(e); }
			@Override
			protected void processMouseMotionEvent(MouseEvent e) { ZwtFrame.this.processMouseEvent(e); }
		};	
		canvas.setSize(width,height);
		graphics=new ZwtGraphics(width,height);
		setBorder(null);
		repaintScreen();
		
		thisWindowListener=new WindowAdapter() {
			public void windowClosing(WindowEvent e) { onWindowClosing(e); }
		};
	}

	/** Sets the listener of this frame.
	 * @param listener the listener to set */
	public void setListener(ZwtFrameListener listener) {
		this.listener=listener;
	}

	/** Sets title.
	 * @param title the new title */
	public void setTitle(String title) {
		//canvas.setTitle(title);
		this.title=title;
		if (jframe!=null) jframe.setTitle(title);
	}
	
	/** Requests a graphical keyboard. */
	public void requestKeyboard() {
	}

	/** Hides the graphical keyboard. */
	public void hideKeyboard() {
	}

	/** Processes key event.
	 * @param e the key event */
	private void processKeyEvent(KeyEvent e) {
		int code=e.getKeyChar();
		if (code==KeyEvent.CHAR_UNDEFINED) code=e.getKeyCode();
		if (e.getID()==KeyEvent.KEY_PRESSED) {
			keyPressed(code);
		}
		else
		if (e.getID()==KeyEvent.KEY_RELEASED) {
			keyReleased(code);
		}
	}

	/** Processes mouse event.
	 * @param e the mouse event */
	private void processMouseEvent(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		if (e.getID()==MouseEvent.MOUSE_PRESSED) {
			lastPressed=new ZwtPoint(x,y);
			pointerMoved=false;
			pointerPressed(x,y);
		}
		else
		if (e.getID()==MouseEvent.MOUSE_RELEASED) {
			if (lastPressed!=null) {
				pointerReleased(x,y,lastPressed.getX(),lastPressed.getY(),pointerMoved);
				lastPressed=null;
			}
			else pointerReleased(x,y,x,y,false);
		}
		else
		if ((e.getID()==MouseEvent.MOUSE_MOVED) || (e.getID()==MouseEvent.MOUSE_DRAGGED)) {
			if (lastPressed!=null) {
				double distance=lastPressed==null? 0 : Math.sqrt((x-lastPressed.getX())*(x-lastPressed.getX())+(y-lastPressed.getY())*(y-lastPressed.getY()));
				if (distance>POINTER_MIN_PERTURBATION) pointerMoved=true;
				pointerMoved(x,y,lastPressed.getX(),lastPressed.getY());
			}
		}
	}

	@Override
	public void setSize(int width, int height) {
		if (this.width!=width || this.height!=height) {
			this.width=width;
			this.height=height;
			canvas.setSize(width,height);
			graphics=new ZwtGraphics(width,height);
			repaintScreen();
		}
	}

	/** Clears the frame. */
	public void clear() {
		super.clear();
		repaintScreen();
	}

	/** Paints graphics on the actual AWT graphics.
	 * @param g the graphics */
	private void paintGraphics(Graphics g) {
		// first an image is obtained from the ZwtGraphic, then the image is drawn on the AWT graphics
		g.drawImage(graphics.toImage(),0,0,null);
		if (pointer!=null) {
			ZwtPoint p=pointer.getCoordinates();
			g.drawImage(pointer.getIcon().getImage(),p.getX(),p.getY(),null);
		}
	}

	/** Repaints the screen. */
	public void repaintScreen() {
		// repaint all contents on the ZwtGraphics
		if (width>0 && height>0) paint(graphics,0,0,new ZwtRect(0,0,width-1,height-1));
		// repaint the AWT graphics
		// * Note:
		// * Canvas#repaint() causes the call the method Canvas#paint(Graphics g)
		// * that in turn calls the method ZwtFrame#paintGraphics(Graphics g) that paints the ZwtGraphics on the AWT Graphics.
		canvas.repaint();
	}

	/** Repaints a given area of the screen.
	 * @param area the area */
	public void repaintScreenArea(ZwtRect area) {
		// repaint all contents on the ZwtGraphics
		//paint(graphics,0,0);
		paint(graphics,0,0,area);
		// repaint the given area of the AWT graphics
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
						c.paint(graphics,x,y,gArea);
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
							((ZwtComponent)u).paint(graphics,x,y,gArea);
							repaintGraphicsArea(gArea);
						}
					}				
				}
			}
		}
	}

	/** Repaints a given area of the graphics.
	 * @param area the area */
	protected void repaintGraphicsArea(ZwtRect area) {
		// repaint the given area of the AWT graphics
		canvas.repaint(area.x1(),area.y1(),area.width(),area.height());
		// * Note:
		// * in case no method is available for repainting a specified area,
		// * the entire area could be repainted (e.g. by calling canvas.repaint()).
	}

	/** Displays the frame within a given JFrame.
	 * @param f the JFrame that will contain this ZwtFrame */
	private void initJFrame(JFrame f) {
		if (jframe!=null) return;
		if (f==null) f=new JFrame();
		this.jframe=f;
		jframe.setResizable(false);
		jframe.setVisible(true);
		Insets insets=jframe.getInsets();
		jframe.setResizable(true);
		jframe.setSize(width+insets.left+insets.right,height+insets.top+insets.bottom);
		jframe.setResizable(false);
		jframe.getContentPane().add(canvas,BorderLayout.CENTER);
		if (title!=null) jframe.setTitle(title);
		jframe.addWindowListener(thisWindowListener);
	}

	/** Moves the frame at the given point of the display.
	 * @param x the x-coordinate of the point corresponding to the upper left corner of the external frame
	 * @param y the y-coordinate  the point corresponding to the upper left corner of the external frame */
	public void displayAt(int x, int y) {
		jframe.setLocation(x,y);
	}

	/** Moves the frame at the center of the display. */
	public void displayAtCenter() {
		ZwtDimension screenSize=ZwtView.getScreenSize();
		jframe.setLocation((screenSize.getWidth()-jframe.getWidth())/2 - 40,(screenSize.getHeight()-jframe.getHeight())/2 - 40);
	}

	
	/** Invoked when the user attempts to close the window from the window's system menu.
	 * @param e window event */
	private void onWindowClosing(WindowEvent e) {
		if (listener!=null) listener.onFrameClosing((ZwtFrame)this);
		else dispose();
	}
	
	/** Disposes this component. */
	public void dispose() {
		jframe.dispose();
		FRAME=null;
	}

}
