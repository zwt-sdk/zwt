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


import java.util.Vector;


/** Text area.
 * A multi-line region that displays text. It can be set to allow editing or to be read-only.
 */
public class ZwtMessageArea extends ZwtLabel {
	
	/** Vertical alignment TOP */
	public static final int ALIGN_TOP=1;
	
	/** Vertical alignment DOWN */
	public static final int ALIGN_DOWN=-1;
	
	/** Vertical alignment VCENTER */
	public static final int ALIGN_VCENTER=0;


	/** Whether it is editable */
	boolean isEditable=true;

	/** Type of keyboard */
	boolean keyboardQwerty=false;

	/** Key repetition time [millisecs] */
	protected static long KEY_TIME=1000;

	/** Type of keyboard */
	boolean keyboardNumeric=false;

	/** Last pressed key */
	long lastKey=0;

	/** When last key was pressed */
	long lastKeyTime=0;

	/** Counter of last key repetitions */
	int lastKeyCount=0;

	/** Indexes of line breaks (as Vector<Integer>) */
	private Vector<Integer> lineBreaks;

	/** Line width */
	private int lineWidth;

	/** Line height */
	private int lineHeight;

	/** Max width */
	private int maxWidth;

	/** Max height */
	private int maxHeight;

	/** Max lines */
	private int maxLines;

	/** Vertical alignment of the text */
	int verticalAlignment=ALIGN_TOP;


	/** Creates a new text area.
	 * @param width component width
	 * @param height component height
	 * @param text the text */
	public ZwtMessageArea(int width, int height, String text) {
		super(null);
		maxWidth=width;
		maxHeight=height;
		setColor(ZwtColor.WHITE);
		setAlignment(ALIGN_LEFT);
		setText(text);
		setBorder(null);
	}


	/** Whether it is editable. */
	public void setEditable(boolean isEditable) {
		this.isEditable=isEditable;
	}


	/** Sets qwerty keyboard. */
	public void setKeyboardQwerty(boolean isQwerty) {
		keyboardQwerty=isQwerty;
	}


	/** Gets vertical alignment. */
	public int getVerticalAlignment() {
		return verticalAlignment;
	}


	/** Sets vertical alignment. */
	public void setVerticalAlignment(int alignment) {
		this.verticalAlignment=alignment;
	}


	@Override
	protected void update() {
		this.width=0;
		this.height=0;
		lineWidth=maxWidth-2*HOFFSET;
		lineHeight=font.getHeight()+VOFFSET;
		maxLines = maxHeight/lineHeight;
		lineBreaks=new Vector<Integer>();
		if (text==null)
			return;
		if (!text.equals("")) {
			height = lineHeight+VOFFSET;
			char[] buff=text.toCharArray();
			int index=0;
			int len=0;
			int oldLen=0;
			while (len<text.length()) {
				if (width<font.charsWidth(buff,index,len))
					width = font.charsWidth(buff,index,len);
				
				if (text.indexOf(" ", len+1) != -1)
					len = text.indexOf(" ", len+1);
				else
					len = text.length();
				if (font.charsWidth(buff,index,len)>lineWidth) {
					height += lineHeight;
					if (font.charsWidth(buff,oldLen,len)>lineWidth) {
						width = lineWidth;
						if (index != oldLen) {
							oldLen++;
							height += lineHeight;
							lineBreaks.addElement(new Integer(oldLen));
							index = oldLen;
						}
						len = oldLen;
						while ((font.charsWidth(buff,oldLen,len)<lineWidth) && (len<text.length())) {
							len++;
						}
						oldLen = len-1;
						lineBreaks.addElement(new Integer(oldLen));
						index = oldLen;
					}
					else {
						index = oldLen;
						len = index;
						lineBreaks.addElement(new Integer(index+1));
					}
				} else {
					oldLen = len;
				}
			}
			if (width<font.charsWidth(buff,index,len))
				width = font.charsWidth(buff,index,len);
		}
		repaint();
	}


	@Override
	public int keyPressed(int keyCode) {
		System.out.println();
		if (!isEditable) return keyCode;
		ZwtKeyboard keyboard=ZwtContext.getKeyboard();
		char[] chars = keyboard.getChars(keyCode);
        if (chars != null) {
            char charPressed = chars[0];
            String buff = text+charPressed;
            int lastBreak=0;
            int lines = 1;
            if (!lineBreaks.isEmpty()) {
            	lastBreak = ((Integer)lineBreaks.lastElement()).intValue();
            	lines = lineBreaks.size()+1;
            	System.out.println("Last_break "+lastBreak+", lines "+lines);
            }
            if ((font.charsWidth(buff.toCharArray(), lastBreak, buff.length())<lineWidth) || (lines<maxLines))
            	text = buff;
        } else if (keyCode==ZwtKeyboard.KEY_DEL && text.length()>0) {
            text = text.substring(0, text.length()-1);
        }
        update();
        return 0;
	}


	/** Draws label text. */
	protected void drawLabelText(ZwtGraphics g, int x, int y) {
		if (text!=null)  {
			ZwtFont fontSaved=g.getFont();
			g.setFont(font);
			ZwtColor colorSaved=g.getColor();
			g.setColor(ink);
			int x0, y0;
			int anchor;
			switch (alignment) {
				case ALIGN_LEFT : x0=x+HOFFSET;  anchor=ZwtAnchor.TOP|ZwtAnchor.LEFT;  break;
				case ALIGN_RIGHT : x0=x+(width-HOFFSET);  anchor=ZwtAnchor.TOP|ZwtAnchor.RIGHT;  break;
				case ALIGN_HCENTER : x0=x+width/2;  anchor=ZwtAnchor.TOP|ZwtAnchor.HCENTER;  break;
				default : x0=x+HOFFSET;  anchor=ZwtAnchor.TOP|ZwtAnchor.LEFT;
			}
			switch (verticalAlignment) {
				case ALIGN_TOP : y0=y+VOFFSET;  break;
				case ALIGN_DOWN : y0=y+height-lineHeight*(lineBreaks.size()+1)-VOFFSET;  break;
				case ALIGN_VCENTER : y0=y+(height-lineHeight*(lineBreaks.size()+1))/2;  break;
				default : y0=y+VOFFSET;
			}
			int index=0;
			for (int i=0; i<lineBreaks.size(); i++) {
				int lineBreak=((Integer)lineBreaks.elementAt(i)).intValue();
				int len=lineBreak;
				g.drawString(text.substring(index,index+len),x0,y0,anchor);
				index=lineBreak;
				y0+=lineHeight;
			}
			g.drawString(text.substring(index,text.length()),x0,y0,anchor);
			g.setFont(fontSaved);
			g.setColor(colorSaved);
		}
	}

}
