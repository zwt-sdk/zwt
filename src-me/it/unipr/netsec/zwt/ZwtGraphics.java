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


import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import it.unipr.netsec.zwt.ZwtFont;


/** Graphics.
  */
public class ZwtGraphics {
	
	/** Alignment at TOP */
	public static final int TOP=Graphics.TOP;
	
	/** Alignment at BOTTOM */
	public static final int BOTTOM=Graphics.BOTTOM;
	
	/** Alignment at VCENTER */
	public static final int VCENTER=Graphics.VCENTER;
	
	/** Alignment at LEFT */
	public static final int LEFT=Graphics.LEFT;
	
	/** Alignment at RIGHT */
	public static final int RIGHT=Graphics.RIGHT;
	
	/** Alignment at HCENTER */
	public static final int HCENTER=Graphics.HCENTER;
	
	/** BASELINE */
	public static final int BASELINE=Graphics.BASELINE;
	
	/** DOTTED */
	public static final int DOTTED=Graphics.DOTTED;
	
	/** SOLID */
	public static final int SOLID=Graphics.SOLID;


	/** Image. */
	Image image;

	/** Graphics. */
	Graphics graphics;


	/** Creates a new ZwtGraphics. */
	protected ZwtGraphics() {
		image=Image.createImage(0,0);
		graphics=image.getGraphics();
	}
	
	/** Creates a new ZwtGraphics.
	  * @param width the width
	  * @param height the height */
	protected ZwtGraphics(int width, int height) {
		image=Image.createImage(width,height);
		graphics=image.getGraphics();
	}


	/** Gets the Image of this ZwtGraphics. */
	public Image toImage() {
		return image;
	}
	
	/** Gets the width. */
	public int getWidth() {
		return image.getWidth();
	}

	/** Gets the height. */
	public int getHeight() {
		return image.getHeight();
	}

	/** Obtains ARGB pixel data from the specified region of this image and stores it in the provided array of integers. */
	public int[] getRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height) {
		if (rgbData==null) rgbData=new int[offset+scanlength*height*4];
		image.getRGB(rgbData,offset,scanlength,x,y,width,height);
		return rgbData;
	}


	/** Intersects the current clip with the specified rectangle. */
	public void clipRect(int x,  int y, int width, int height) {
		graphics.clipRect(x,y,width,height);
	}
	
	/** Copies the contents of a rectangular area (xSrc, ySrc, width, height) to a destination area, whose anchor point identified by anchor is located at (xDest, yDest). */
	public void copyArea(int xSrc, int ySrc, int width, int height, int xDest, int yDest, int anchor) {
		graphics.copyArea(xSrc,ySrc,width,height,xDest,yDest,anchor);
	}
	
	/** Draws the outline of a circular or elliptical arc covering the specified rectangle, using the current color and stroke style. */
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		graphics.drawArc(x,y,width,height,startAngle,arcAngle);
	}
	
	/** Draws the specified character using the current font and color. */
	/*public void drawChar(char character, int x, int y, int anchor) {
		graphics.drawChar(character,x,y,anchor);
	}*/
	
	/** Draws the specified characters using the current font and color. */
	/*public void drawChars(char[] data, int offset, int length, int anchor) {
		graphics.drawChars(data,offset,length,x,y,anchor);
	}*/
	
	/** Draws the specified characters using the current font and color. */
	public void drawChars(char[] data, int offset, int length, int x, int y) {
		graphics.drawChars(data,offset,length,x,y,Graphics.TOP|Graphics.LEFT);
	}
	
	/** Draws the specified image by using the anchor point. */
	/*public void drawImage(Image img, int x, int y, int anchor) {
		graphics.drawImage(img,x,y,anchor);
	}*/
	
	/** Draws the specified image. */
	public void drawImage(ZwtImage img, int x, int y) {
		graphics.drawImage(img.getImage(),x,y,Graphics.TOP|Graphics.LEFT);
	}
	
	/** Draws a line between the coordinates (x1,y1) and (x2,y2) using the current color and stroke style. */
	public void drawLine(int x1, int y1, int x2, int y2) {
		graphics.drawLine(x1,y1,x2,y2);
	}
	
	/** Draws the outline of an oval.
	  * @param x the x coordinate of the upper left corner of the oval to be drawn
	  * @param y the y coordinate of the upper left corner of the oval to be drawn
	  * @param width the width of the oval to be drawn
	  * @param height the height of the oval to be drawn */
	public void drawOval(int x, int y, int width, int height) {
		graphics.drawArc(x,y,width,height,0,360);
	}
	
	/** Draws a sequence of connected lines defined by arrays of x and y coordinates.
	  * @param g the graphics where to draw
	  * @param xPoints an array of x coordinates
	  * @param yPoints an array of y coordinates */
	public void drawPolyline(int[] xPoints, int[] yPoints) {
		if (xPoints.length!=yPoints.length) throw new RuntimeException("numbers of x and coordinates differ ("+xPoints.length+"<>"+yPoints.length+")");
		int n=xPoints.length;
		for (int i=0; i<xPoints.length; i++) {
			int j=i+1;
			graphics.drawLine(xPoints[i],yPoints[i],j<n?xPoints[j]:xPoints[0],j<n?yPoints[j]:yPoints[0]);
		}
	}
	
	/** Draws the outline of the specified rectangle using the current color and stroke style. */
	public void drawRect(int x, int y, int width, int height) {
		graphics.drawRect(x,y,width,height);
	}
	
	/** Copies a region of the specified source image to a location within the destination, possibly transforming (rotating and reflecting) the image data using the chosen transform function. */
	public void drawRegion(Image src, int xSrc, int ySrc, int width, int height, int transform, int xDest, int yDest, int anchor) {
		graphics.drawRegion(src,xSrc,ySrc,width,height,transform,xDest,yDest,anchor);
	}
	
	/** Renders a series of device-independent RGB+transparency values in a specified region. */
	public void drawRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height, boolean processAlpha) {
		graphics.drawRGB(rgbData,offset,scanlength,x,y,width,height,processAlpha);
	}
	
	/** Draws the outline of the specified rounded corner rectangle using the current color and stroke style. */
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		graphics.drawRoundRect(x,y,width,height,arcWidth,arcHeight);
	}
	
	/** Draws the specified String using the current font and color. */
	public void drawString(String str, int x, int y, int anchor) {
		graphics.drawString(str,x,y,anchor);
	}
	
	/** Draws the specified String using the current font and color. */
	public void drawString(String str, int x, int y) {
		graphics.drawString(str,x,y,Graphics.TOP|Graphics.LEFT);
	}
	
	/** Draws the specified String using the current font and color. */
	public void drawSubstring(String str, int offset, int len, int x, int y, int anchor) {
		graphics.drawSubstring(str,offset,len,x,y,anchor);
	}
	
	/** Fills a circular or elliptical arc covering the specified rectangle. */
	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		graphics.fillArc(x,y,width,height,startAngle,arcAngle);
	}
	
	/** Fills the outline of an oval.
	  * @param x the x coordinate of the upper left corner of the oval to be drawn
	  * @param y the y coordinate of the upper left corner of the oval to be drawn
	  * @param width the width of the oval to be drawn
	  * @param height the height of the oval to be drawn */
	public void fillOval(int x, int y, int width, int height) {
		graphics.fillArc(x,y,width,height,0,360);
	}
	
	/** Fills the specified rectangle with the current color. */
	public void fillRect(int x, int y, int width, int height) {
		graphics.fillRect(x,y,width,height);
	}
	
	/** Fills the specified rounded corner rectangle with the current color. */
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		graphics.fillRoundRect(x,y,width,height,arcWidth,arcHeight);
	}
	
	/** Fills the specified triangle will the current color. */
	public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		graphics.fillTriangle(x1,y1,x2,y2,x3,y3);
	}
	
	/** Gets the blue component of the current color. */
	public int getBlueComponent() {
		return graphics.getBlueComponent();
	}
	
	/** Gets the height of the current clipping area. */
	public int getClipHeight() {
		return graphics.getClipHeight();
	}
	
	/** Gets the width of the current clipping area. */
	public int getClipWidth() {
		return graphics.getClipWidth();
	}
	
	/** Gets the X offset of the current clipping area, relative to the coordinate system origin of this graphics context. */
	public int getClipX() {
		return graphics.getClipX();
	}
	
	/** Gets the Y offset of the current clipping area, relative to the coordinate system origin of this graphics context. */
	public int getClipY() {
		return graphics.getClipY();
	}
	
	/** Gets the current color. */
	public ZwtColor getColor() {
		return new ZwtColor(graphics.getColor());
	}
	
	/** Gets the color that will be displayed if the specified color is requested. */
	public int getDisplayColor(int color) {
		return graphics.getDisplayColor(color);
	}
	
	/** Gets the current font. */
	public ZwtFont getFont() {
		return ZwtFont.createFont(graphics.getFont());
	}
	
	/** Gets the current grayscale value of the color being used for rendering operations. */
	public int getGrayScale() {
		return graphics.getGrayScale();
	}
	
	/** Gets the green component of the current color. */
	public int getGreenComponent() {
		return graphics.getGreenComponent();
	}
	
	/** Gets the red component of the current color. */
	public int getRedComponent() {
		return graphics.getRedComponent();
	}
	
	/** Gets the stroke style used for drawing operations. */
	public int getStrokeStyle() {
		return graphics.getStrokeStyle();
	}
	
	/** Gets the X coordinate of the translated origin of this graphics context. */
	public int getTranslateX() {
		return graphics.getTranslateX();
	}
	
	/** Gets the Y coordinate of the translated origin of this graphics context. */
	public int getTranslateY() {
		return graphics.getTranslateY();
	}
			
	/** Sets the current clip to the rectangle specified by the given coordinates. */
	public void setClip(int x,  int y, int width, int height) {
		graphics.setClip(x,y,width,height);
	}
	
	/** Sets the current color to the specified RGB values. */
	public void setColor(ZwtColor c) {
		graphics.setColor(c.getRGB());
	}
	
	/** Sets the current color to the specified RGB values. */
	public void setColor(int red, int green, int blue) {
		graphics.setColor(red,green,blue);
	}
	
	/** Sets the font for all subsequent text rendering operations. */
	public void setFont(ZwtFont font) {
		graphics.setFont(font.getFont());
	}
	
	/** Sets the current grayscale to be used for all subsequent rendering operations. */
	public void setGrayScale(int value) {
		graphics.setGrayScale(value);
	}
	
	/** Sets the stroke style used for drawing lines, arcs, rectangles, and rounded rectangles. */
	public void setStrokeStyle(int style) {
		graphics.setStrokeStyle(style);
	}
	
	/** Translates the origin of the graphics context to the point (x, y) in the current coordinate system. */
	public void translate(int x, int y) {
		graphics.translate(x,y);
	}
 
}
