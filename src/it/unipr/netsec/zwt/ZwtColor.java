package it.unipr.netsec.zwt;


/** A sRGB color.
 * It is a RGB color with alpha value defining the transparency.
 * The alpha component can be represented by a float value in the range 0.0 - 1.0 or integer between 0 - 255.
 *  An alpha value of 1.0 or 255 means that the color is completely opaque
 *  and an alpha value of 0 or 0.0 means that the color is completely transparent
 * <p>
 * An implicit alpha value of 1.0 is supposed, if not explicitly provided in the constructor.
 */
public class ZwtColor {
	
	/** Black */
	public static final ZwtColor BLACK=new ZwtColor(0,0,0);

	/** White */
	public static final ZwtColor WHITE=new ZwtColor(255,255,255);

	/** Red component */
	int red;

	/** Green component */
	int green;

	/** Blue component */
	int blue;

	/** Alpha component (1.0 is completely opaque) */
	float alpha;

	
	/** Creates a new color.
	 * @param red red component
	 * @param green green component
	 * @param blue blue component
	 * @param alpha component (1.0 is completely opaque) */
	public ZwtColor(int red, int green, int blue, float alpha) {
		this.alpha=alpha;
		this.red=red;
		this.green=green;
		this.blue=blue;
	}

	/** Creates a new opaque color. */
	public ZwtColor(int red, int green, int blue) {
		this(red,green,blue,1.0F);
	}
	
	/** Creates a new color.
	 * @param alpha component (1.0 is completely opaque)
	 * @param rgb 24bit RGB value */
	public ZwtColor(int rgb, float alpha) {
		this((rgb>>16)&0xff,(rgb>>8)&0xff,rgb&0xff,alpha);
	}

	/** Creates a new opaque color.
	 * @param rgb 24bit RGB value */
	public ZwtColor(int rgb) {
		this(rgb,1.0F);
	}

	/** Creates a new color.
	 * @param srgb 32bit sRGB value
	 * @param hasalpha whether there is the alpha component (bits 24-31) */
	public ZwtColor(long srgb, boolean hasalpha) {
		this((int)srgb,hasalpha?((srgb>>24)&0xff)/255.0F:1.0F);
	}

	/** Gets RGB.
	 * @return RGB value */
	public int getRGB() {
		return (red<<16)|(green<<8)|blue;
	}

	/** Gets sRGB.
	 * @return sRGB value */
	public int getSRGB() {
		return ((int)(alpha*255)<<24)|(red<<16)|(green<<8)|blue;
	}

	/** Gets alpha.
	 * @return alpha value, between 0.0 and 1.0 (1.0 is completely opaque) */
	public float getAlpha() {
		return alpha;
	}

	/** Gets red component.
	 * @return red value */
	public int getRed() {
		return red;
	}

	/** Gets green component.
	 * @return green value */
	public int getGreen() {
		return green;
	}

	/** Gets blue component.
	 * @return blue value */
	public int getBlue() {
		return blue;
	}

	/** Adds two colors.
	 * The alpha value of the second color is used for the sum, while the alpha value of the first color is used for the result.
	 * @param c1 the background color
	 * @param c2 the ink color
	 * @return the sum */
	public static ZwtColor sum(ZwtColor c1, ZwtColor c2) {
		float a=c2.getAlpha();
		float b=1.0F-a;
		return new ZwtColor((int)(c1.getRed()*b+c2.getRed()*a),(int)(c1.getGreen()*b+c2.getGreen()*a),(int)(c1.getBlue()*b+c2.getBlue()*a),c1.getAlpha());
	}

	@Override
	public String toString() {
		return "0x"+Integer.toHexString(getSRGB());
	}

}
