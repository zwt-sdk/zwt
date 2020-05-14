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

package it.unipr.netsec.zwt.frame;


import it.unipr.netsec.zwt.ZwtFrame;
import it.unipr.netsec.zwt.ZwtKeyboard;
import it.unipr.netsec.zwt.ZwtPoint;
import it.unipr.netsec.zwt.ZwtPointer;
import it.unipr.netsec.zwt.ZwtRect;
import it.unipr.netsec.zwt.ZwtView;


/** Frame with a virtual pointer.
  * The pointer is controlled by the user through the standard arrow keys (UP, DOWN, LEFT, RIGHT).
  */
public class VirtualPointerFrame extends ZwtFrame {
	
	
	/** Creates a new VirtualPointerFrame. */
	public VirtualPointerFrame(ZwtView view, int width, int height) {
		super(view,width,height);
	}

	/** Sets pointer. */
	public void setPointer(ZwtPointer p) {
		this.pointer=p;
	}

	@Override
	public int keyPressed(int keyCode) {
		//ZwtKeyboard keyboard=ZwtContext.getKeyboard();
		keyCode=super.keyPressed(keyCode);
		if (pointer!=null) {
			if (keyCode==ZwtKeyboard.KEY_SELECT) {
				ZwtPoint p=pointer.getCoordinates();
				pointerPressed(p.getX(),p.getY());
			}
			else
			if (keyCode==ZwtKeyboard.KEY_DOWN || keyCode==ZwtKeyboard.KEY_UP || keyCode==ZwtKeyboard.KEY_LEFT || keyCode==ZwtKeyboard.KEY_RIGHT) {
				int delta=10;
				int dx=0;
				int dy=0;
				if (keyCode==ZwtKeyboard.KEY_DOWN) dy+=delta;
				else
				if (keyCode==ZwtKeyboard.KEY_UP) dy-=delta;
				else
				if (keyCode==ZwtKeyboard.KEY_LEFT) dx-=delta;
				else
				if (keyCode==ZwtKeyboard.KEY_RIGHT) dx+=delta;
				ZwtPoint p=pointer.getCoordinates();
				int xMax=getWidth()-2;
				int yMax=getHeight()-2;
				if (p.getX()+dx>xMax) dx=xMax-p.getX(); else if (p.getX()+dx<0) dx=-p.getX();
				if (p.getY()+dy>yMax) dy=yMax-p.getY(); else if (p.getY()+dy<0) dy=-p.getY();
				pointer.moveUp(dx,dy);
				p=pointer.getCoordinates();
				//repaintGraphicsArea(p.getX()+((dx>0)?-dx:0),p.getY()+((dy>0)?-dy:0),pointer.getWidth()+Math.abs(dx),pointer.getHeight()+Math.abs(dy));
				ZwtPoint pointerPos=new ZwtPoint(p.getX()+((dx>0)?-dx:0),p.getY()+((dy>0)?-dy:0));
				int pointerWidth=pointer.getWidth()+Math.abs(dx);
				int pointerHeight=pointer.getHeight()+Math.abs(dy);
				if (pointerWidth>0 && pointerHeight>0) repaintGraphicsArea(new ZwtRect(pointerPos,pointerWidth,pointerHeight));					
			}
		}
		return keyCode;
	}

	@Override
	public int keyReleased(int keyCode) {
		//ZwtKeyboard keyboard=ZwtContext.getKeyboard();
		if (pointer!=null) {
			if (keyCode==ZwtKeyboard.KEY_SELECT) {
				ZwtPoint p=pointer.getCoordinates();
				pointerReleased(p.getX(),p.getY(),p.getX(),p.getY(),false);
				return 0;
			}
		}
		// else
		return super.keyReleased(keyCode);
	}

	/*@Override
	public void paint(ZwtGraphics g, int x, int y) {
		super.paint(g,x,y);
		drawPointer(g,x,y);
	}*/

	/** Draws pointer. */
	/*protected void drawPointer(ZwtGraphics g, int x, int y) {
		if (pointer==null) return;
		// else
		pointer.paint(g,x,y);
	}*/

}
