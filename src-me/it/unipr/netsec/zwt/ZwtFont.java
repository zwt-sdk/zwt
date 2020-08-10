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


import javax.microedition.lcdui.Font;

import it.unipr.netsec.zwt.ZwtFont;


/** Font.
  */
public class ZwtFont {
	
  
	/** The "monospace" font face. */
	static int FACE_MONOSPACE=Font.FACE_MONOSPACE;
	
	/** The "proportional" font face. */
	public static int FACE_PROPORTIONAL=Font.FACE_PROPORTIONAL;
	
	/** The "system" font face. */
	public static int FACE_SYSTEM=Font.FACE_SYSTEM;
	
	/** Font specifier used by the implementation to draw text input by a user. */
	public static int FONT_INPUT_TEXT=Font.FONT_INPUT_TEXT;
	
	/** Default font specifier used to draw Item and Screen contents. */
	public static int FONT_STATIC_TEXT=Font.FONT_STATIC_TEXT;
	
	/** The "large" system-dependent font size. */
	public static int SIZE_LARGE=Font.SIZE_LARGE;
	
	/** The "medium" system-dependent font size. */
	public static int SIZE_MEDIUM=Font.SIZE_MEDIUM;
	
	/** The "small" system-dependent font size. */
	public static int SIZE_SMALL=Font.SIZE_SMALL;
	
	/** The bold style constant. */
	public static int STYLE_BOLD=Font.STYLE_BOLD;
	
	/** The italicized style constant. */
	public static int STYLE_ITALIC=Font.STYLE_ITALIC;
	
	/** The plain style constant. */
	public static int STYLE_PLAIN=Font.STYLE_PLAIN;
	
	/** The underlined style constant. */
	public static int STYLE_UNDERLINED=Font.STYLE_UNDERLINED;

	/** The default font. */
	static ZwtFont DEFAULT_FONT=createFont(Font.getDefaultFont());


	/** Font */
	Font font;

	
	/** Creates a new ZwtFont. */
	protected ZwtFont() {
	}

	/** Creates a new ZwtFont. */
	protected ZwtFont(ZwtFont f) {
		this.font=f.font;
	}


	/** Gets the default font of the system. */
	public static ZwtFont getDefaultFont() {
		return new ZwtFont(DEFAULT_FONT);
	}

	/** Sets the default font of the system. */
	public static void setDefaultFont(ZwtFont f) {
		DEFAULT_FONT=f;
	}

	/** Gets the Font used by the high level user interface for the fontSpecifier passed in. */
	public static ZwtFont getFont(int fontSpecifier) {
		return createFont(Font.getFont(fontSpecifier));
	}
	
	/** Obtains an object representing a font having the specified face, style, and size. */
	public static ZwtFont getFont(int face, int style, int size) {
		return createFont(Font.getFont(face,style,size));
	}
	
	
	/** Creates a new ZwtFont.
	 * @param font the lcdui font */
	public static ZwtFont createFont(Font font) {
		ZwtFont f=new ZwtFont();
		f.font=font;
		return new ZwtFont(f);
	}

	/** Gets the lcdui font.
	 * @return the font */
	public Font getFont() {
		return font;
	}

	/** Gets the advance width of a set characters, starting at the specified offset and for the specified number of characters (length).
	 * @param ch the array of characters
	 * @param offset offset within the array
	 * @param length length of the set of characters
	 * @return the advance width of the characters */
	public int charsWidth(char[] ch, int offset, int length) {
		return font.charsWidth(ch,offset,length);
	}
	
	/** Gets the advance width of the specified character in this Font. */
	public int charWidth(char ch) {
		return font.charWidth(ch);
	}
	
	/** Gets the distance in pixels from the top of the text to the text's baseline. */
	public int getBaselinePosition() {
		return font.getBaselinePosition();
	}
	
	/** Gets the face of the font. */
	public int getFace() {
		return font.getFace();
	}
	
	/** Gets the standard height of a line of text in this font. */
	public int getHeight() {
		return font.getHeight();
	}
	
	/** Gets the size of the font. */
	public int getSize() {
		return font.getSize();
	}
	
	/** Sets the size of the font. */
	public void setSize(int size) {
		// TODO
	}
	
	/** Gets the style of the font. */
	public int getStyle() {
		return font.getStyle();
	}
	
	/** Returns true if the font is bold. */
	public boolean isBold() {
		return font.isBold();
	}
	
	/** Returns true if the font is italic. */
	public boolean isItalic() {
		return font.isItalic();
	}
	
	/** Returns true if the font is plain. */
	public boolean isPlain() {
		return font.isPlain();
	}
	

	/** Set this Font object's style to BOLD. */
	public void setBold() {
		font=Font.getFont(font.getFace(),Font.STYLE_BOLD,font.getSize());
	}

	/** Set this Font object's style to ITALIC. */
	public void setItalic() {
		font=Font.getFont(font.getFace(),Font.STYLE_ITALIC,font.getSize());
	}

	/** Set this Font object's style to BOLD-ITALIC. */
	public void setBoldItalic() {
		font=Font.getFont(font.getFace(),Font.STYLE_BOLD,font.getSize());
	}

	/** Set this Font object's style to PLAIN. */
	public void setPlain() {
		font=Font.getFont(font.getFace(),Font.STYLE_PLAIN,font.getSize());
	}
	
	/** Returns true if the font is underlined. */
	public boolean isUnderlined() {
		return font.isUnderlined();
	}
	
	/** Gets the total advance width for showing the specified String in this Font. */
	public int stringWidth(String str) {
		return font.stringWidth(str);
	}
	
	/** Gets the total advance width for showing the specified substring in this Font. */
	public int substringWidth(String str, int offset, int len) {
		return font.substringWidth(str,offset,len);
	}

	
}
