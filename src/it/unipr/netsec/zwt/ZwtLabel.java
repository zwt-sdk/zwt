package it.unipr.netsec.zwt;



/** A label.
 * A single line of read-only text, with a given font type, color, and alignment.
 */
public class ZwtLabel extends ZwtFilledComponent {
	
	/** Horizontal offset */
	static final int HOFFSET=4;

	/** Vertical offset */
	static final int VOFFSET=4;

	/** Horizontal alignment LEFT */
	public static final int ALIGN_LEFT=1;

	/** Horizontal alignment RIGHT */
	public static final int ALIGN_RIGHT=-1;

	/** Horizontal alignment HCENTER */
	public static final int ALIGN_HCENTER=0;


	/** Font color */
	ZwtColor ink=new ZwtColor(0x000000);

	/** Font type */
	ZwtFont font=ZwtFont.getDefaultFont();

	/** Text */
	String text;

	/** Maximum text length */
	int maximumLength=-1;
	
	/** Horizontal alignment of the text */
	int alignment=ALIGN_HCENTER;

	/** Text width */
	private int textWidth;

	/** Text height */
	private int textHeight;



	/** Creates a new label. 
	 * @param text the label text */
	public ZwtLabel(String text) {
		super();
		setBorder(null);
		setText(text);
	}

	/** Updates the label to the current font size and text length. */
	protected void update() {
		// area to be repainted
		int updatedAreaWidth=width;
		int updatedAreaHeight=height;
		// update size
		if (text!=null)  {
			textWidth=font.charsWidth(text.toCharArray(),0,text.length());
			textHeight=font.getHeight();
		}
		else {
			textWidth=0;
			textHeight=0;
		}
		width=textWidth+2*HOFFSET;
		height=textHeight+2*VOFFSET;
		if (width<minWidth) width=minWidth;
		if (height<minHeight) height=minHeight;
		//if (widthMax>0 && width>widthMax) width=widthMax;
		//if (heightMax>0 && height>heightMax) height=heightMax;
		if (updatedAreaWidth<width) updatedAreaWidth=width;
		if (updatedAreaHeight<height) updatedAreaHeight=height;
		// repaint updated area
		if (updatedAreaWidth>0 && updatedAreaHeight>0) repaint(new ZwtRect(0,0,updatedAreaWidth-1,updatedAreaHeight-1));
	}

	/** Gets text. */
	public String getText() {
		return text;
	}

	/** Sets text.
	 * @param text the new text */
	public void setText(String text) {
		if (maximumLength>=0 && text.length()>maximumLength) this.text=text.substring(0,maximumLength);
		else this.text=text;
		update();
	}

	/** Gets the Maximum text length. 
	 * @return the maximum length (number of chars). Value -1 if there is no limit for the text */
	public int getMaximumTextLength() {
		return maximumLength;
	}

	/** Sets the Maximum text length.
	 * @param len the maximum length (number of chars) */
	public void setMaximumTextLength(int len) {
		maximumLength=len;
		if (maximumLength>=0 && text.length()>maximumLength) text=text.substring(0,maximumLength);
		update();
	}

	/** Gets font type.
	 * @return the font */
	public ZwtFont getFont() {
		return font;
	}

	/** Sets font type.
	 * @param font the new font */
	public void setFont(ZwtFont font) {
		this.font=font;
		update();
	}

	/** Gets alignment.
	 * @return the alignment type */
	public int getAlignment() {
		return alignment;
	}

	/** Sets alignment.
	 * @param alignment the alignment type ({@link #ALIGN_LEFT}, {@link #ALIGN_HCENTER}, {@link #ALIGN_RIGHT}) */
	public void setAlignment(int alignment) {
		this.alignment=alignment;
	}

	/** Gets ink color.
	 * @return the text color */
	public ZwtColor getTextColor() {
		return ink;
	}

	/** Sets ink color.
	 * @param ink the text color */
	public void setTextColor(ZwtColor ink) {
		this.ink=ink;
	}

	@Override
	public void paint(ZwtGraphics g, int x, int y, ZwtRect area) {
		super.paint(g,x,y,area);
		drawLabelText(g,x,y,area);
	}

	/** Draws label text.
	 * @param g the graphics
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param area the available graphics area */
	protected void drawLabelText(ZwtGraphics g, int x, int y, ZwtRect area) {
		//if (text!=null && area.contains(x+1,y+1)) {
		if (text!=null && area.hasIntersection(x,y,width,height)) {
			ZwtFont fontSaved=g.getFont();
			g.setFont(font);
			ZwtColor colorSaved=g.getColor();
			g.setColor(ink);
			int x0, y0;
			switch (alignment) {
				case ALIGN_LEFT : x0=x+HOFFSET;  break;
				case ALIGN_RIGHT : x0=x+(width-textWidth)-HOFFSET;  break;
				case ALIGN_HCENTER : x0=x+(width-textWidth)/2;  break;
				default : x0=x+HOFFSET;
			}
			y0=y+(height-textHeight)/2; 
			g.drawString(text,x0,y0,ZwtAnchor.TOP|ZwtAnchor.LEFT);
			g.setFont(fontSaved);
			g.setColor(colorSaved);
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName()+"["+getText()+"]";
	}

}
