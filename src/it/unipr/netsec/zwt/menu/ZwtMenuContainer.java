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

import it.unipr.netsec.zwt.ZwtColor;
import it.unipr.netsec.zwt.ZwtFilledComponent;
import it.unipr.netsec.zwt.ZwtFont;
import it.unipr.netsec.zwt.ZwtGraphics;
import it.unipr.netsec.zwt.ZwtKeyboard;
import it.unipr.netsec.zwt.ZwtRect;


/** Container of a list of {@link ZwtMenuItem menu items}.
  * Two examples of ZMenuContainer are: {@link ZwtMenu} and {@link ZwtMenuBar}.
  */
abstract public class ZwtMenuContainer extends ZwtFilledComponent {
	
	/** List of menu items (as Vector<ZMenuItem>) */
	protected Vector<ZwtMenuItem> items=new Vector<ZwtMenuItem>();

	/** Item index */
	int index=0;

	/** Font color */
	ZwtColor ink=ZwtColor.BLACK;

	/** Font type */
	ZwtFont font=ZwtFont.getDefaultFont();



	/** Creates a new ZMenuContainer */
	protected ZwtMenuContainer() {
		super();
	}


	/** Updates the MenuContainer to the current font size and menu items. */
	abstract protected void update();


	/** Closes all sub menus. */
	protected void closeSubMenus() {
		for (int i=0; i<items.size(); i++) {
			ZwtMenu subMenu=((ZwtMenuItem)items.elementAt(i)).getSubMenu();
			if (subMenu!=null) subMenu.close();
		} 
	}

	/** Sets font type. */
	public void setFont(ZwtFont font) {
		this.font=font;
		/*for (int i=0; i<items.size(); i++) {
			ZMenu subMenu=((ZMenuItem)items.elementAt(i)).getSubMenu();
			if (subMenu!=null) subMenu.setFont(font);
		}*/
		update();
	}


	/** Gets font type. */
	public ZwtFont getFont() {
		return font;
	}


	/** Sets ink color. */
	public void setTextColor(ZwtColor ink) {
		this.ink=ink;
		/*for (int i=0; i<items.size(); i++) {
			ZMenu subMenu=((ZMenuItem)items.elementAt(i)).getSubMenu();
			if (subMenu!=null) subMenu.setTextColor(rgb);
		}*/
	}


	/** Gets ink color. */
	public ZwtColor getTextColor() {
		return ink;
	}


	/** Returns the number of menu items. */
	public int size() {
		return items.size();
	}


	/** Adds a new menu item. */
	public void addMenuItem(ZwtMenuItem item) {
		items.addElement(item);
		/*ZMenu subMenu=item.getSubMenu();
		if (subMenu!=null) {
			subMenu.setFont(font);
			subMenu.setColor(getColor());
			subMenu.setTextColor(getTextColor());
			subMenu.setMinimumWidth(getMinimumWidth());
		}*/
		update();
	}


	/** Inserts a new menu item. */
	public void insertMenuItemAt(ZwtMenuItem item, int i) {
		items.insertElementAt(item,i);
		/*ZMenu subMenu=item.getSubMenu();
		if (subMenu!=null) {
			subMenu.setFont(font);
			subMenu.setColor(getColor());
			subMenu.setTextColor(getTextColor());
			subMenu.setMinimumWidth(getMinimumWidth());
		}*/
		update();
	}


	/** Adds a new menu item. */
	public void removeMenuItemAt(int i) {
		items.removeElementAt(i);
		update();
	}


	/** Gets menu item at position <i>i</i>. */
	public ZwtMenuItem getMenuItemAt(int i) {
		return (ZwtMenuItem)items.elementAt(i);
	}


	/** Gets selected menu item. */
	public ZwtMenuItem getSelectedMenuItem() {
		return (ZwtMenuItem)items.elementAt(index);
	}


	/** Returns index of the selected menu item. */
	public int indexOfSelectedMenuItem() {
		return index;
	}


	/** Selects menu item at index <i></i>. */
	public void selectMenuItem(int i) {
		index=i;
	}


	/** Performs action of selected menu item. */
	public void selectedItemAction() {
		keyPressed(ZwtKeyboard.KEY_SELECT);
	}


	@Override
	public void paint(ZwtGraphics g, int x, int y, ZwtRect area) {
		//if (!area.contains(x,y)) return;
		if (!area.hasIntersection(x,y,width,height)) return;
		// else
		super.paint(g,x,y,area);
		drawMenuContainer(g,x,y);
	}


	/*@Override
	public void dispose() {
		closeSubMenus();
		for (Enumeration e=items.elements(); e.hasMoreElements(); ) {
			ZMenu menu=((ZMenuItem)e.nextElement()).getSubMenu();
			if (menu!=null) menu.dispose();
		}
		items.clear();
		super.dispose();
	}*/


	/** Draws the menu container. */
	abstract protected void drawMenuContainer(ZwtGraphics g, int x, int y);

}
