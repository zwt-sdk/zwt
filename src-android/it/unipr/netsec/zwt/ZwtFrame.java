package it.unipr.netsec.zwt;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;


/** The UI frame.
 * It includes a screen as output interface, and possibly command buttons
 * and/or a pointer as input interface.
 * <p>
 * In order to display the ZwtFrame you need to attach it to a proper device-dependent
 * graphic object (e.g. a Java swing JFrame in case of Java SE platform,
 * or a MIDlet in case of Java ME platform with CLDC and MIDP); this is performed through the
 * <code>setDisplay()<code> method.
 */
public class ZwtFrame extends ZwtPanel {

	/** Whether running press events in a new thread (for testing purpose) */
	private static boolean RUN_PRESS_EVENTS_IN_NEW_THREAD=false;

	/** Pointer minimum perturbation for movement detection */
	private static int POINTER_MIN_PERTURBATION=20;

	/** Virtual pointer */
	protected ZwtPointer pointer=null;

	/** Last pressed point */
	private ZwtPoint lastPressed=null;

	/** Whether the pointer has moved */
	private boolean pointerMoved=false;

	/** Blank rectangular area of the screen onto which the application can draw or from which the application can trap input events from the user */
	private ImageView imageView;
	
	/** Frame graphics */
	private ZwtGraphics graphics;

	/** App Context */
	private Context context;

	/** The frame title */
	private String title=null;

	/** The listener */
	private ZwtFrameListener listener=null;

	/** The main frame */
	private static ZwtFrame FRAME;

    /** Time of the last repaint of the screen */
    private long lastRepaint=0;

    /** Whether the screen is going to be repainted soon */
    private boolean willRepaint=false;


    /** Repaints a given area of a component of a frame.
	 * The component is searched amongst all frames.
	 * The component may be directly attached to the frame or to a sub-component.
	 * @param c the component
	 * @param area the area */
	public static void repaintFrameComponent(ZwtComponent c, ZwtRect area) {
		if (FRAME==c || FRAME.getContainerOf(c)!=null) {
			FRAME.repaintComponentArea(c,area);
			return;
		}
	}


	/** Creates a new frame with a given width and height.
	 * The result is the same as creating a ZwtFrame, and then calling method {@link ZwtFrame#setSize(int, int)}.
	 * @view the platform-dependent view
	 * @param width the component width
	 * @param height the component height */
	public ZwtFrame(ZwtView view, int width, int height) {
		this(view.iw,width,height);
	}

	/** Creates a new frame with a given width and height.
	 * The result is the same as creating a ZwtFrame, and then calling method {@link ZwtFrame#setSize(int, int)}.
	 * @iw the widget image view
	 * @param width the component width
	 * @param height the component height */
	public ZwtFrame(ImageView iw, int width, int height) {
		super(width,height);
		init(iw, width, height);
	}

	/** Initializes the frame. */
	@SuppressLint("ClickableViewAccessibility")
    private void init(ImageView iw, int screenWidth, int screenHeight) {
	    context = iw.getContext();
	    imageView = iw;
        graphics = new ZwtGraphics(screenWidth, screenHeight);
        repaintScreen();
        FRAME = this;

        iw.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				Rect rect = new Rect();
				imageView.getWindowVisibleDisplayFrame(rect);
				screenResized(rect.width(), rect.height());
			}
		});

        iw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                processTouch(event);
				return true;
            }
        });

        iw.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				processKeyEvent(event);
				return false;
			}
		});
	}

	/** Sets the listener of this frame.
	 * @param listener the listener to set */
	public void setListener(ZwtFrameListener listener) {
		this.listener=listener;
	}

	/** Sets title.
	 * 	Not working on Android version.
	 * 	In order to change title on Android version you have to change android:label param
	 * 	in AndroidManifest.xml file
	 * @param title the new title */
	public void setTitle(String title) {
	}

    /** Tells the system to show the keyboard */
    public void requestKeyboard() {
        imageView.setFocusable(true);
        imageView.setFocusableInTouchMode(true);
        if (imageView.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(imageView, InputMethodManager.SHOW_IMPLICIT);
        }
    }

	/** Tells the system to hide the keyboard */
	public void hideKeyboard() {
		imageView.setFocusable(true);
		imageView.setFocusableInTouchMode(true);
		if (imageView.requestFocus()) {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(imageView.getWindowToken(), 0);
		}
	}

	/** Processes key event.
	 * @param e the key event */
	private void processKeyEvent(KeyEvent e) {
        // KeyCode 57 is called when a special character is pressed. It is LEFT ALT KeyCode
        if (e.getKeyCode() == 57)
            return;

        int code = e.getUnicodeChar();
        if (e.getCharacters() != null) {
            code = e.getCharacters().charAt(0);
        } else if (code == 0)
            code = -e.getKeyCode();

        if (e.getAction() == KeyEvent.ACTION_DOWN) {
            keyPressed(code);
        } else if (e.getAction()==KeyEvent.ACTION_UP) {
            keyReleased(code);
        } else if (e.getAction()==KeyEvent.ACTION_MULTIPLE) {
            keyReleased(code);
        }
	}

	/** Processes mouse event.
	 * @param e the mouse event */
	private void processTouch(MotionEvent e) {
	    int x=(int)e.getX();
	    int y=(int)e.getY();

	    if (e.getAction() == MotionEvent.ACTION_DOWN) {
			lastPressed=new ZwtPoint(x,y);
			pointerMoved=false;
			pointerPressed(x, y);
		}
	    else if (e.getAction() == MotionEvent.ACTION_UP) {
			if (lastPressed!=null) {
				pointerReleased(x,y,lastPressed.getX(),lastPressed.getY(),pointerMoved);
				lastPressed=null;
			}
			else pointerReleased(x,y,x,y,false);
		}
		else if (e.getAction() == MotionEvent.ACTION_MOVE) {
			if (lastPressed!=null) {
				double distance=lastPressed==null? 0 : Math.sqrt((x-lastPressed.getX())*(x-lastPressed.getX())+(y-lastPressed.getY())*(y-lastPressed.getY()));
				if (distance>POINTER_MIN_PERTURBATION) {
					pointerMoved=true;
				}
				pointerMoved(x,y,lastPressed.getX(),lastPressed.getY());
			}
		}
	}

	@Override
	public void setSize(int width, int height) {
		if (this.width!=width || this.height!=height) {
			this.width=width;
			this.height=height;
			graphics=new ZwtGraphics(width,height);
			repaintScreen();
		}
	}

	/** Clears the frame. */
	public void clear() {
		System.out.println("clear()");
		super.clear();
		repaintScreen();
	}

	//int count=0;

	/** Repaints the screen. */
	public void repaintScreen() {
		System.out.println("repaintScreen()");
		if (width>0 && height>0) repaintScreenArea(new ZwtRect(0,0,width-1,height-1));
	}

	/** Repaints a given area of the screen.
	 * @param area the area */
	public void repaintScreenArea(ZwtRect area) {
		System.out.println("repaintScreenArea(): "+area);
		// repaint all contents on the ZwtGraphics
		paint(graphics,0,0,area);
		// repaint the ZwtGraphics on the screen
		if (RUN_PRESS_EVENTS_IN_NEW_THREAD) {
			imageView.post(new Runnable() {
				@Override
				public void run() {
					imageView.setImageBitmap(graphics.toImage());
				}
			});
		}
		else imageView.setImageBitmap(graphics.toImage());
	}

	/** Repaints a specific area of a given component.
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
					System.out.println("ZwtFrame: repaintComponentArea() 1: "+c+" in "+u+": p="+p+" area="+area);
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
						System.out.println("ZwtFrame: repaintComponentArea() 2: "+c+" in "+u+": p="+p+" area="+area);
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
		repaintScreenArea(area);
		// * Note:
		// * in case no method is available for repainting a specified area,
		// * the entire area could be repainted (e.g. by calling canvas.repaint()).
	}

	/** Disposes this component. */
	public void dispose() {
		FRAME=null;
	}

	@Override
	public int keyPressed(final int code) {
		if (RUN_PRESS_EVENTS_IN_NEW_THREAD) {
			new Thread(new Runnable() {
				@Override
				public void run() { ZwtFrame.super.keyPressed(code); }
			}).start();
			return code;
		}
		else return super.keyPressed(code);
	}

	@Override
	public int keyReleased(final int code) {
	    if (RUN_PRESS_EVENTS_IN_NEW_THREAD) {
	    	new Thread(new Runnable() {
				@Override
				public void run() { ZwtFrame.super.keyReleased(code); }
			}).start();
			return code;
		}
		else return super.keyReleased(code);
	}

	@Override
	public boolean pointerPressed(final int x, final int y) {
		if (RUN_PRESS_EVENTS_IN_NEW_THREAD) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					ZwtFrame.super.pointerPressed(x, y);
				}
			}).start();
			return true;
		}
		else return super.pointerPressed(x, y);
	}

	@Override
	public boolean pointerMoved(final int x, final int y, final int pressedX, final int pressedY) {
		if (RUN_PRESS_EVENTS_IN_NEW_THREAD) {
			new Thread(new Runnable() {
					@Override
					public void run() { ZwtFrame.super.pointerMoved(x,y,pressedX,pressedY); }
				}).start();
			return true;
		}
		else return super.pointerMoved(x,y,pressedX,pressedY);
	}

	@Override
	public boolean pointerReleased(final int x, final int y, final int pressedX, final int pressedY, final boolean moved) {
		if (RUN_PRESS_EVENTS_IN_NEW_THREAD) {
			new Thread(new Runnable() {
				@Override
				public void run() { ZwtFrame.super.pointerReleased(x,y,pressedX,pressedY,moved); }
			}).start();
			return true;
		}
		else return super.pointerReleased(x,y,pressedX,pressedY,moved);
	}

}
