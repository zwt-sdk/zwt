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


import java.util.Vector;

import it.unipr.netsec.zwt.ZwtAnchor;
import it.unipr.netsec.zwt.ZwtColor;
import it.unipr.netsec.zwt.ZwtFont;
import it.unipr.netsec.zwt.ZwtGraphics;
import it.unipr.netsec.zwt.ZwtPanel;
import it.unipr.netsec.zwt.ZwtRect;


/** Menu bar.
  * It contains a list of {@link ZwtMenu menus}.
  */
public class ZwtMenuBar extends ZwtMenuContainer {
	
	/** Horizontal offset */
	static final int HOFFSET=4;

	/** Vertical offset */
	static final int VOFFSET=4;

	/** The panel where possible sub-menus will be dynamically displayed */
	ZwtPanel contextPanel;

	/** Font color */
	ZwtColor ink=ZwtColor.BLACK;

	/** Font type */
	ZwtFont font=ZwtFont.getDefaultFont();

	/** List of name widths (as Vector<Integer>) */
	Vector<Integer> nameWidths=new Vector<Integer>();



	/** Creates a new ZMenuBar 
	  * @param contextPanel the panel where possible sub-menus will be dynamically displayed;
	  * normally this is the same panel where the menu bar is attached to */
	public ZwtMenuBar(ZwtPanel contextPanel) {
		super();
		this.contextPanel=contextPanel;
		setBorder(null);
	}


	@Override
	protected void update() {
		width=0;
		for (int i=0; i<items.size(); i++) {
			String name=((ZwtMenuItem)items.elementAt(i)).getText();
			int nameWidth=HOFFSET+font.charsWidth(name.toCharArray(),0,name.length())+HOFFSET;
			if (nameWidths.size()>i) nameWidths.setElementAt(new Integer(nameWidth),i);
			else nameWidths.addElement(new Integer(nameWidth));
			width+=nameWidth;
		}
		if (width<minWidth) width=minWidth;
		height=font.getHeight()+2*VOFFSET;
		if (height<minHeight) height=minHeight;
		repaint();
	}


	/** Closes all menus. */
	public void closeAll() {
		closeSubMenus();
	}


	@Override
	public boolean pointerPressed(int x, int y) {
		int index=0;
		for (int i=0; i<items.size(); i++) {
			int nameWidth=((Integer)nameWidths.elementAt(i)).intValue();
			if (x<(index+nameWidth)) {
				for (int j=0; j<items.size(); j++) {
					ZwtMenuItem item=(ZwtMenuItem)items.elementAt(j);
					ZwtMenu menu=item.getSubMenu();
					if (j!=i) {
						menu.close();
					}
					else {
						if (contextPanel.hasComponent(menu)) menu.close();
						else {
							closeSubMenus();
							contextPanel.addComponent(menu,index,height);
						}
					}
				}
				return false;
			}
			else index+=nameWidth;
		}
		// else
		closeSubMenus();
		return false;
	}


	@Override
	public void paint(ZwtGraphics g, int x, int y, ZwtRect area) {
		if (!area.contains(x,y)) return;
		// else
		super.paint(g,x,y,area);
		drawMenuContainer(g,x,y);
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
				String name=((ZwtMenuItem)items.elementAt(i)).getText();
				g.drawString(name,x0,y0,ZwtAnchor.TOP|ZwtAnchor.LEFT);
				x0+=((Integer)nameWidths.elementAt(i)).intValue();
			}
			g.setFont(fontSaved);
			g.setColor(colorSaved);
		}
	}


	/*@Override
	public void dispose() {
		nameWidths.clear();
		contextPanel=null;
		super.dispose();
	}*/

}
