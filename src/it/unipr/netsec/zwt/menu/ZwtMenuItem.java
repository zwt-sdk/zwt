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




/** A menu item.
  * It may correspond to an action or a sub-menu.
  * In the former case the menu item is created with a {@link ZwtMenuItemListener menu item listener};
  * when the menu is selected the corresponding {@link ZwtMenuItemListener#onMenuItemSelected(ZMenuItem item)})
  * method is called.
  * Instead, in the latter case the menu item is created with inner {@link ZwtMenu sub-menu}.
  */
public class ZwtMenuItem {
	
	/** Text */
	String text;
	
	/** Sub-menu */
	ZwtMenu subMenu=null;

	/** Item listener */
	ZwtMenuItemListener listener=null;
	

	/** Creates a new ZMenuItem. */
	public ZwtMenuItem(String text, ZwtMenuItemListener listener) {
		this.listener=listener;
		setText(text);
	}

	/** Creates a new ZMenuItem. */
	public ZwtMenuItem(String text, ZwtMenu subMenu) {
		this.subMenu=subMenu;
		setText(text);
	}

	/** Sets text. */
	public void setText(String text) {
		this.text=text;
	}

	/** Gets text. */
	public String getText() {
		return text;
	}

	 /** Gets item listener. */
	public ZwtMenuItemListener getListener() {
		return listener;
	}

  /** Gets sub-menu. */
	public ZwtMenu getSubMenu() {
		return subMenu;
	}

}
