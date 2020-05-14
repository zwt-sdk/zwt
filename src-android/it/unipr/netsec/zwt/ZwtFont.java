package it.unipr.netsec.zwt;


import android.graphics.Paint;
import android.graphics.Typeface;


/** Font type.
 */
public class ZwtFont {
	
	/** The bold style constant. */
	//static int BOLD=Typeface.BOLD;
	
	/** The baseline used in ideographic scripts like Chinese, Japanese, and Korean when laying out text. */
	//static int CENTER_BASELINE=Font.CENTER_BASELINE;
	
	/** The baseline used in Devanigiri and similar scripts when laying out text. */
	//static int HANGING_BASELINE=Font.HANGING_BASELINE;
	
	/** The italicized style constant. */
	//static int ITALIC=Typeface.ITALIC;
	
	/** The plain style constant. */
	//static int PLAIN=Typeface.NORMAL;
	
	/** The baseline used in most Roman scripts when laying out text. */
	//static int ROMAN_BASELINE=Font.ROMAN_BASELINE;
	
	/** Create a Font of type TRUETYPE. */
	//static int TRUETYPE_FONT=Font.TRUETYPE_FONT;
	
	/** The logical name of this Font, as passed to the constructor. */
	//protected String name;
	
	/** The point size of this Font in float. */
	//protected float pointSize;
	
	/** The point size of this Font, rounded to integer. */
	//protected int size;
	
	/** The style of this Font, as passed to the constructor. */
	//protected int style;
	
	/** The default font. */
	//static ZwtFont DEFAULT_FONT=new ZwtFont(new Font(null,PLAIN,12));
	static ZwtFont DEFAULT_FONT=getDefaultFont();//new ZwtFont(new Paint());

	/** The default font render context. */
	//static final FontRenderContext DEFAULT_FONT_RENDER_CONTEXT=((Graphics2D)new BufferedImage(1,1,BufferedImage.TYPE_4BYTE_ABGR).getGraphics()).getFontRenderContext();;


	/** Font */
	//Font font;
    Paint fontPaint;

	
	/** Creates a new font. */
	protected ZwtFont() {
	}

	/** Creates a new font.
	 * @param paint contains the selected font */
	/*protected ZwtFont(Font font) {
		this.font=font;
	}*/
	protected ZwtFont(Paint paint) {
	    this.fontPaint = paint;
    }

	/** Creates a new font.
	 * @param f the selected font */
	protected ZwtFont(ZwtFont f) {
		this.fontPaint=f.getPaint();
	}

	/** Gets the default font of the system. */
	public static ZwtFont getDefaultFont() {
		Paint paint = new Paint();
        paint.setTextSize(40);
        return new ZwtFont(paint);
	}

	/** Sets the default font of the system.
	 * @param f the selected font */
	public static void setDefaultFont(ZwtFont f) {
		DEFAULT_FONT=f;
	}

	/** Creates a new ZwtFont.
	 * @param paint the font */
	public static ZwtFont createFont(Paint paint) {
		ZwtFont f=new ZwtFont();
		f.fontPaint=paint;
		return new ZwtFont(f);
	}

	/** Gets the AWT font.
	 * @return the font */
	public Paint getPaint() {
	    return fontPaint;
    }

	/** Gets the advance width of a set characters, starting at the specified offset and for the specified number of characters (length).
	 * @param ch the array of characters
	 * @param offset offset within the array
	 * @param length length of the set of characters
	 * @return the advance width of the characters */
	public int charsWidth(char[] ch, int offset, int length) {
		//return (int)font.getStringBounds(ch,offset,length,DEFAULT_FONT_RENDER_CONTEXT).getWidth();
		//return (int)font.getStringBounds(ch,offset,length,ZwtContext.getFontRenderContext()).getWidth();
        //Rect bounds = new Rect();
        //fontPaint.getTextBounds(ch, offset, length, bounds);
		length -= offset;
		return (int) fontPaint.measureText(ch, offset, length); //bounds.width();
	}
	
	/** Gets the standard height of a line of text in this font.
	 * @return the height of the line */
	public int getHeight() {
		//return font.getSize();
        return (int)fontPaint.getTextSize();
	}

	/** Compares this Font object to the specified Object. */
	public boolean equals(Object obj) {
		return fontPaint.equals(obj);
	}
	
	/** Returns the point size of this Font, rounded to an integer. */
	public int getSize() {
        return (int) fontPaint.getTextSize();
	}
	
	/** Gets the style of this Font.
	 * @return the style of this Font */
	public /*int*/Paint.Style getStyle() {
		//return font.getStyle();
        return fontPaint.getStyle();
	}
	
	/** Gets a hashcode for this Font.
	* @return the hashcode */
	/*public int hashCode() {
		return font.hashCode();
	}
	
	/** Checks whether or not this Font has uniform line metrics.
	* @return <i>true</i> if this Font has uniform line metrics; <i>false</i> otherwise */
	/*public boolean hasUniformLineMetrics() {
		return font.hasUniformLineMetrics();
	}*/
	
	/** Indicates whether or not this Font object's style is BOLD.
	* @return <i>true</i> if this Font has a BOLD style; <i>false</i> otherwise */
	public boolean isBold() {
		//return font.isBold();
        return fontPaint.getTypeface().isBold();
	}

	/** Indicates whether or not this Font object's style is ITALIC.
	 * @return <i>true</i> if this Font has an ITALIC style; <i>false</i> otherwise */
	public boolean isItalic() {
		//return font.isItalic();
        return fontPaint.getTypeface().isItalic();
	}

	/** Indicates whether or not this Font object's style is PLAIN.
	 * @return <i>true</i> if this Font has a PLAIN style; <i>false</i> otherwise */
	public boolean isPlain() {
		//return font.isPlain();
		return !(fontPaint.getTypeface().isBold() || fontPaint.getTypeface().isItalic());
	}

	/** Set this Font object's style to BOLD. */
	public void setBold() {
		fontPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
	}

	/** Set this Font object's style to ITALIC. */
	public void setItalic() {
		fontPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.ITALIC));
	}

	/** Set this Font object's style to BOLD-ITALIC. */
	public void setBoldItalic() {
		fontPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC));
	}

	/** Set this Font object's style to PLAIN. */
	public void setPlain() {
		fontPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
	}

	/** Converts this Font object to a String representation.
	 * @return a String representation of this Font object */
	public String toString() {
		return fontPaint.toString();
	}
	
}
