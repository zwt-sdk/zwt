package it.unipr.netsec.zwt;


import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;


/** Font type.
 */
public class ZwtFont {
	
	/** The bold style constant. */
	static int BOLD=Font.BOLD;
	
	/** The baseline used in ideographic scripts like Chinese, Japanese, and Korean when laying out text. */
	static int CENTER_BASELINE=Font.CENTER_BASELINE;
	
	/** The baseline used in Devanigiri and similar scripts when laying out text. */
	static int HANGING_BASELINE=Font.HANGING_BASELINE;
	
	/** The italicized style constant. */
	static int ITALIC=Font.ITALIC;
	
	/** The plain style constant. */
	static int PLAIN=Font.PLAIN;
	
	/** The baseline used in most Roman scripts when laying out text. */
	static int ROMAN_BASELINE=Font.ROMAN_BASELINE;
	
	/** Create a Font of type TRUETYPE. */
	static int TRUETYPE_FONT=Font.TRUETYPE_FONT;
	
	/** The logical name of this Font, as passed to the constructor. */
	//protected String name;
	
	/** The point size of this Font in float. */
	//protected float pointSize;
	
	/** The point size of this Font, rounded to integer. */
	//protected int size;
	
	/** The style of this Font, as passed to the constructor. */
	//protected int style;
	
	/** The default font. */
	static ZwtFont DEFAULT_FONT=new ZwtFont(new ZwtFont(new Font(null,PLAIN,12)));

	/** The default font render context. */
	static final FontRenderContext DEFAULT_FONT_RENDER_CONTEXT=((Graphics2D)new BufferedImage(1,1,BufferedImage.TYPE_4BYTE_ABGR).getGraphics()).getFontRenderContext();


	/** Font */
	Font font;

	
	/** Creates a new font. */
	protected ZwtFont() {
	}

	/** Creates a new font.
	 * @param font the selected font */
	protected ZwtFont(Font font) {
		this.font=font;
	}

	/** Creates a new font.
	 * @param font the selected font */
	protected ZwtFont(ZwtFont f) {
		this.font=f.font;
	}

	/** Gets the default font of the system. */
	public static ZwtFont getDefaultFont() {
		return DEFAULT_FONT;
	}

	/** Sets the default font of the system.
	 * @param f the selected font */
	public static void setDefaultFont(ZwtFont f) {
		DEFAULT_FONT=f;
	}

	/** Creates a new ZwtFont.
	 * @param font the font */
	public static ZwtFont createFont(Font font) {
		ZwtFont f=new ZwtFont();
		f.font=font;
		return new ZwtFont(new ZwtFont(f));
	}

	/** Gets the AWT font.
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
		return (int)font.getStringBounds(ch,offset,offset+length,DEFAULT_FONT_RENDER_CONTEXT).getWidth();
		//return (int)font.getStringBounds(ch,offset,offset+length,ZwtContext.getFontRenderContext()).getWidth();
	}
	
	/** Gets the standard height of a line of text in this font.
	 * @return the height of the line */
	public int getHeight() {
		return font.getSize();
	}

	/** Compares this Font object to the specified Object. */
	public boolean equals(Object obj) {
		return font.equals(obj);
	}
	
	/** Returns the font size, rounded to an integer. */
	public int getSize() {
		return font.getSize();
	}
	
	/** Sets the font size. */
	public void setSize(int size) {
		font=new Font(font.getName(),font.getStyle(),size);
	}
	
	/** Gets the style of this Font.
	 * @return the style of this Font */
	public int getStyle() {
		return font.getStyle();
	}
	
	/** Gets a hashcode for this Font.
	* @return the hashcode */
	public int hashCode() {
		return font.hashCode();
	}
	
	/** Checks whether or not this Font has uniform line metrics.
	* @return <i>true</i> if this Font has uniform line metrics; <i>false</i> otherwise */
	public boolean hasUniformLineMetrics() {
		return font.hasUniformLineMetrics();
	}
	
	/** Indicates whether or not this Font object's style is BOLD.
	* @return <i>true</i> if this Font has a BOLD style; <i>false</i> otherwise */
	public boolean isBold() {
		return font.isBold();
	}
	
	/** Indicates whether or not this Font object's style is ITALIC.
	 * @return <i>true</i> if this Font has an ITALIC style; <i>false</i> otherwise */
	public boolean isItalic() {
		return font.isItalic();
	}
	
	/** Indicates whether or not this Font object's style is PLAIN.
	 * @return <i>true</i> if this Font has a PLAIN style; <i>false</i> otherwise */
	public boolean isPlain() {
		return font.isPlain();
	}

	/** Set this Font object's style to BOLD. */
	public void setBold() {
		font=font.deriveFont(BOLD);
	}

	/** Set this Font object's style to ITALIC. */
	public void setItalic() {
		font=font.deriveFont(ITALIC);
	}

	/** Set this Font object's style to BOLD-ITALIC. */
	public void setBoldItalic() {
		font=font.deriveFont(BOLD);
	}

	/** Set this Font object's style to PLAIN. */
	public void setPlain() {
		font=font.deriveFont(PLAIN);
	}
	
	/** Converts this Font object to a String representation.
	 * @return a String representation of this Font object */
	public String toString() {
		return font.toString();
	}
	
}
