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

package it.unipr.netsec.zwt.menu;


import it.unipr.netsec.zwt.ZwtAnchor;
import it.unipr.netsec.zwt.ZwtColor;
import it.unipr.netsec.zwt.ZwtFont;
import it.unipr.netsec.zwt.ZwtGraphics;
import it.unipr.netsec.zwt.ZwtKeyboard;
import it.unipr.netsec.zwt.ZwtPanel;
import it.unipr.netsec.zwt.ZwtPoint;


/** Menu.
  * It contains a list of {@link ZwtMenuItem menu items}.
  */
public class ZwtMenu extends ZwtMenuContainer {
	
	/** Default minimum width */
	static final int MENU_MIN_WIDTH=60;

	/** Horizontal offset */
	static final int HOFFSET=8;//4;
	
	/** Vertical offset */
	static final int VOFFSET=4;
	
	/** Arrow horizontal space */
	static final int ARROW_HSPACE=8;

	/** Horizontal submenu overlap */
	static final int OVERLAP=3;

	/** The panel where possible sub-menus will be dynamically displayed */
	ZwtPanel contextPanel;
	
	/** Text width */
	private int textWidth;
	
	/** Text height */
	private int textHeight;

	/** Arrow width */
	private int arrowWidth; 
	
	/** Arrow height */
	private int arrowHeight;

	/** Listener */
	ZwtMenuListener listener;


	/** Creates a new ZMenu.
	  * @param contextPanel the panel where possible sub-menus will be dynamiccaly displayed
	  * @param listener the menu listener */
	public ZwtMenu(ZwtPanel contextPanel, ZwtMenuListener listener) {
		super();
		this.contextPanel=contextPanel;
		this.listener=listener;
		minWidth=MENU_MIN_WIDTH;
	}


	@Override
	public void setMinimumWidth(int minWidth) {
		super.setMinimumWidth(minWidth);
		/*for (int i=0; i<items.size(); i++) {
			ZMenu subMenu=((ZMenuItem)items.elementAt(i)).getSubMenu();
			if (subMenu!=null) subMenu.setMinimumWidth(minWidth);
		}*/
		update();
	}


	@Override
	protected void update() {
		textHeight=font.getHeight();
		arrowWidth=textHeight/2;
		arrowHeight=(textHeight*2)/3;
		for (int i=0; i<items.size(); i++) {
			ZwtMenuItem item=(ZwtMenuItem)items.elementAt(i);
			String itemText=((ZwtMenuItem)items.elementAt(i)).getText();
			int itemWidth=font.charsWidth(itemText.toCharArray(),0,itemText.length());
			if (item.getSubMenu()!=null) itemWidth+=arrowWidth+ARROW_HSPACE;
			if (itemWidth>textWidth) textWidth=itemWidth;
		}
		width=textWidth+2*HOFFSET;
		if (width<minWidth) textWidth=(width=minWidth)-2*HOFFSET;
		//if (widthMax>0 && width>widthMax) textWidth=(width=widthMax)-2*HOFFSET;
		height=items.size()*(textHeight+VOFFSET)+VOFFSET;
		if (height<minHeight) height=minHeight;
		//if (heightMax>0 && height>heightMax) height=heightMax;
	}


	/** Closes the menu and all sub menus. */
	public void close() {
		closeSubMenus();
		contextPanel.removeComponent(this);
		if (listener!=null) listener.onMenuClosed(this);
	}


	@Override
	public int keyPressed(int keyCode) {
		if (keyCode==ZwtKeyboard.KEY_UP) {
			if((--index)<0) index=items.size()-1;
			repaint();
			return 0;
		}
		// else
		if (keyCode==ZwtKeyboard.KEY_DOWN)  {
			if((++index)>=items.size()) index=0;
			repaint();
			return 0;
		}
		// else
		if (keyCode==ZwtKeyboard.KEY_LEFT || keyCode==ZwtKeyboard.KEY_ESC || keyCode==ZwtKeyboard.KEY_CANC || keyCode==ZwtKeyboard.KEY_DEL) {
			close();
			return 0;
		}
		// else
		if (keyCode==ZwtKeyboard.KEY_RIGHT || keyCode==ZwtKeyboard.KEY_SELECT) {
			ZwtMenuItem item=(ZwtMenuItem)items.elementAt(index);
			ZwtMenu subMenu=item.getSubMenu();
			if (subMenu!=null) {
				closeSubMenus();
				ZwtPoint p=contextPanel.getCoordinates(this);
				int dx=width-OVERLAP;
				if ((p.getX()+dx+subMenu.getWidth())>contextPanel.getWidth()) dx=-subMenu.getWidth()+OVERLAP;
				if ((p.getX()+dx)<0) dx=-p.getX();
				int dy=index*(textHeight+VOFFSET)+VOFFSET;
				if ((p.getY()+dy+subMenu.getHeight())>contextPanel.getHeight()) dy=contextPanel.getHeight()-p.getY()-subMenu.getHeight();
				contextPanel.addComponent(subMenu,p.getX()+dx,p.getY()+dy);
			}
			else {
				if (keyCode==ZwtKeyboard.KEY_SELECT) {
					ZwtMenuItemListener itemListener=item.getListener();
					if (itemListener!=null) itemListener.onMenuItemSelected(item);
				}
				else {
					close();
				}
			}
			return 0;
		}
		// else
		return keyCode;
	}

	@Override
	public boolean pointerPressed(int x, int y) {
		return false;
	}

	@Override
	public boolean pointerReleased(int x, int y, int pressedX, int pressedY, boolean moved) {
		index=(int)(items.size()*(y-VOFFSET)/(float)(getHeight()-2*VOFFSET));
		repaint();
		keyPressed(ZwtKeyboard.KEY_SELECT);
		return false;
	}

	@Override
	protected void drawMenuContainer(ZwtGraphics g, int x, int y) {
		if (items.size()>0)  {
			ZwtFont fontSaved=g.getFont();
			g.setFont(font);
			ZwtColor colorSaved=g.getColor();
			g.setColor(ink);
			int x0=x+HOFFSET;
			int y0=y+VOFFSET; 
			for (int i=0; i<items.size(); i++) {
				ZwtMenuItem item=(ZwtMenuItem)items.elementAt(i);
				// draw selection
				if (i==index) {
					int xoff=HOFFSET/4;//HOFFSET/2;
					int yoff=VOFFSET/4;//VOFFSET/2;
					drawSelection(g,x0-xoff,y0-yoff,textWidth+2*xoff,textHeight+2*yoff);
				}
				// draw item text
				String text=item.getText();
				g.drawString(text,x0,y0,ZwtAnchor.TOP|ZwtAnchor.LEFT);
				// draw sub-menu icon (arrow)
				if (item.getSubMenu()!=null) {
					int x1=x+(width-arrowWidth)-HOFFSET;
					g.fillTriangle(x1,y0+(textHeight-arrowHeight),x1+arrowWidth,y0+textHeight-arrowHeight/2,x1,y0+textHeight);
				}
				y0+=textHeight+VOFFSET;
			}
			g.setFont(fontSaved);
			g.setColor(colorSaved);
		}
	}

	/** Draws selection. */
	protected void drawSelection(ZwtGraphics g, int x, int y, int width, int height) {
		final ZwtColor color=new ZwtColor(0xDDDDDD);
		ZwtColor colorSaved=g.getColor();
		g.setColor(color); 
		g.fillRect(x,y,width,height);
		g.setColor(colorSaved);
	}


	/*@Override
	public void dispose() {
		contextPanel=null;
		listener=null;
		super.dispose();
	}*/

}
