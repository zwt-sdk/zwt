package it.unipr.netsec.zwt.examples.calc;

import it.unipr.netsec.zwt.ZwtColor;
import it.unipr.netsec.zwt.ZwtFrame;
import it.unipr.netsec.zwt.border.ZwtRoundBorder;
import it.unipr.netsec.zwt.floor.ZwtRoundFloor;
import it.unipr.netsec.zwt.menu.ZwtMenu;
import it.unipr.netsec.zwt.menu.ZwtMenuBar;
import it.unipr.netsec.zwt.menu.ZwtMenuItem;
import it.unipr.netsec.zwt.menu.ZwtMenuItemListener;

/** Calculator menu bar.
 */
class CalculatorMenuBar extends ZwtMenuBar {
	
	/** Menu color */
	static final ZwtColor MENU_COLOR=new ZwtColor(0xCCD0D0FF,true); // = (color:0xD0D0FF,opacity:0.8)
	
	/** Menu roundness factor (compared to the menu bar width) */
	static final double MENU_ROUNDNESS=0.08;

	/** Menu width factor (compared to the menu bar width) */
	static final double MENU_WIDTH=0.25;

	
	/** Creates new CalculatorMenuBar. */
	public CalculatorMenuBar(ZwtFrame frame, int width, ZwtMenuItemListener itemListener) {
		super(frame);
		int widthMin=(int)(width*MENU_WIDTH);
		int roundness=(int)(width*MENU_ROUNDNESS);
		// view menu
		ZwtMenu viewMenu=createMenu(frame,widthMin,roundness);
		viewMenu.addMenuItem(new ZwtMenuItem("Standard",itemListener));
		viewMenu.addMenuItem(new ZwtMenuItem("Scientific",itemListener));
		viewMenu.addMenuItem(new ZwtMenuItem("Programmer",itemListener));
		viewMenu.addMenuItem(new ZwtMenuItem("Artist",itemListener));
		viewMenu.addMenuItem(new ZwtMenuItem("Dummy",itemListener));
		viewMenu.addMenuItem(new ZwtMenuItem("Exit",itemListener));
		// edit menu
		ZwtMenu editMenu=createMenu(frame,widthMin,roundness);
		editMenu.addMenuItem(new ZwtMenuItem("Copy",itemListener));
		editMenu.addMenuItem(new ZwtMenuItem("Cut",itemListener));
		editMenu.addMenuItem(new ZwtMenuItem("Paste",itemListener));
		// help menu
		ZwtMenu helpMenu=createMenu(frame,widthMin,roundness);
		helpMenu.addMenuItem(new ZwtMenuItem("Help",itemListener));
		helpMenu.addMenuItem(new ZwtMenuItem("About",itemListener));
		// this menu bar
		addMenuItem(new ZwtMenuItem("View   ",viewMenu));
		addMenuItem(new ZwtMenuItem("Edit   ",editMenu));
		addMenuItem(new ZwtMenuItem("?   ",helpMenu));
		setSize(width,getHeight());
	}
 
	/** Creates menu. */
	private ZwtMenu createMenu(ZwtFrame frame, int widthMin, int roundness) {
		ZwtMenu menu=new ZwtMenu(frame,null);
		menu.setMinimumWidth(widthMin);
		ZwtRoundFloor floor=new ZwtRoundFloor();
		floor.setRoundness(roundness);
		floor.setColor(MENU_COLOR);
		menu.setFloor(floor);
		ZwtRoundBorder border=new ZwtRoundBorder();
		border.setRoundness(roundness);
		menu.setBorder(border);
		return menu;
	}

}
