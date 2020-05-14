package it.unipr.netsec.zwt;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

import java.text.AttributedCharacterIterator;


/** A graphic context used to draw onto graphic objects (lines, arcs, images, text, etc.).
  */
public class ZwtGraphics {

	/** Bitmap containing the graphics */
    Bitmap image;

	/** Canvas for drawing onto the bitmap */
	Canvas canvas;

	/** Paint object */
	Paint paint;


	/** Creates a new graphics. */
	protected ZwtGraphics() {
		image = Bitmap.createBitmap(0, 0, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(image);
		paint = new Paint();
		paint.setStrokeWidth(2);
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);
	}
	
	/** Creates a new graphics.
	  * @param width the width
	  * @param height the height */
	public ZwtGraphics(int width, int height) {
        image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		canvas = new Canvas(image);
        paint = new Paint();
        paint.setStrokeWidth(2);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
	}

	/** Gets the image of this graphics.
	  * @return an image representing the graphics */
	public Bitmap toImage() {
		return image;
	}
	
	/** Gets the width.
	  * @return the width */
	public int getWidth() {
		return image.getWidth();
	}

	/** Gets the height.
	  * @return the height */
	public int getHeight() {
		return image.getHeight();
	}

	/** Sets the current color for this graphics.
	  * @param c the color */
	public void setColor(ZwtColor c) {
		paint.setColor(c.getSRGB());
	}

	/** Gets the current color for this graphics.
	  * @return the color */
	public ZwtColor getColor() {
		return new ZwtColor(paint.getColor(),true);
	}

	/** Clears the specified rectangle by filling it with the background color of the current drawing surface.
	  * @param x the x-coordinate of the upper-left corner of the rectangle
	  * @param y the y-coordinate of the upper-left corner of the rectangle
	  * @param width the width of the rectangle
	  * @param height the height of the rectangle */
	public void clearRect(int x, int y, int width, int height) {
		Rect clearRect = new Rect(x, y, x+width, y+height);
        Paint clearPaint = new Paint();
        clearPaint.setColor(Color.WHITE);
        clearPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		canvas.drawRect(clearRect, clearPaint);
	}
	
	/** Intersects the current clip with the specified rectangle.
	  * @param x the x-coordinate of the upper-left corner of the rectangle
	  * @param y the y-coordinate of the upper-left corner of the rectangle
	  * @param width the width of the rectangle
	  * @param height the height of the rectangle */
	public void clipRect(int x, int y, int width, int height) {
		canvas.clipRect(x,y,x+width,y+height);
	}
	
	/** Copies an area of the component by a given distance.
	  * @param x the x-coordinate of the upper-left corner of the area
	  * @param y the y-coordinate of the upper-left corner of the area
	  * @param width the width of the area
	  * @param height the height of the area
	  * @param dx the x-distance
	  * @param dy the y-distance */
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
		Bitmap copiedArea = Bitmap.createBitmap(image, x, y, width, height);
		canvas.drawBitmap(copiedArea, x+dx, y+dy, paint);
	}

	/** Obtains ARGB pixel data from the specified region of this image and stores it in the provided array of integers.
	 * @param rgbData array for returning the the RGB pixel data
	 * @param offset the offset within the array
	 * @param scanlength scanline stride for the RGB array
	 * @param x the x-coordinate of the upper-left corner of the region
	 * @param y the y-coordinate of the upper-left corner of the region
	 * @param width the width of the region
	 * @param height the height of the region
	 * @return the array containing the RGB pixel data */
	public int[] getRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height) {
		if (rgbData==null) rgbData=new int[offset+scanlength*height];
		//image.getRGB(rgbData,offset,scanlength,x,y,width,height);
		image.getPixels(rgbData, offset, scanlength, x, y, width, height);
		return rgbData;
	}

	/** Renders a series of device-independent RGB+transparency values in a specified region.
	 * @param rgbData array containing the RGB+transparency data
	 * @param offset the offset within the array
	 * @param scanlength scanline stride of the RGB array
	 * @param x the x-coordinate of the upper-left corner of the region
	 * @param y the y-coordinate of the upper-left corner of the region
	 * @param width the width of the region
	 * @param height the height of the region
	 * @param processAlpha whether processing the <i>alpha</i> values */
	public void drawRGB(int[] rgbData, int offset, int scanlength, int x, int y, int width, int height, boolean processAlpha) {
		if ((x+width)>image.getWidth()) width=image.getWidth()-x;
		if ((y+height)>image.getHeight()) height=image.getHeight()-y;
		image.setPixels(rgbData, offset, scanlength, x, y, width, height);
	}

	/** Draws the outline of a circular or elliptical arc covering the specified rectangle.
	  * @param x the x-coordinate of the upper-left corner of the arc
	  * @param y the y-coordinate of the upper-left corner of the arc
	  * @param width the width of the arc
	  * @param height the height of the arc
	  * @param startAngle  the beginning angle
	  * @param arcAngle the angular extent of the arc, relative to the start angle */
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		//graphics.drawArc(x,y,width,height,startAngle,arcAngle);
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawArc(x, y, x+width, y+height, startAngle, arcAngle, false, paint);
	}
	
	/** Draws the text given by the specified character array, using this graphics context's current font and color.
	  * @param data the array of characters to be drawn
	  * @param offset the start offset in the data
	  * @param length the number of characters to be drawn
	  * @param x the x coordinate of the baseline of the text
	  * @param y the y coordinate of the baseline of the text */
	public void drawChars(char[] data, int offset, int length, int x, int y) {
		//graphics.drawChars(data,offset,length,x,y);
		canvas.drawText(data, offset, length, x, y, paint);
	}
	
	/** Draws a line, using the current color, between the points (x1, y1) and (x2, y2) in this graphics context's coordinate system.
	  * @param x1 the first point's x coordinate
	  * @param y1 the first point's y coordinate
	  * @param x2 the second point's x coordinate
	  * @param y2 the second point's y coordinate */
	public void drawLine(int x1, int y1, int x2, int y2) {
		canvas.drawLine(x1,y1,x2,y2, paint);
	}
	
	/** Draws the outline of an oval.
	  * @param x the x coordinate of the upper left corner of the oval to be drawn
	  * @param y the y coordinate of the upper left corner of the oval to be drawn
	  * @param width the width of the oval to be drawn
	  * @param height the height of the oval to be drawn */
	public void drawOval(int x, int y, int width, int height) {
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawOval(x,y,x+width,y+height, paint);
	}
	
	/** Draws a sequence of connected lines defined by arrays of x and y coordinates.
	  * @param xPoints an array of x points
	  * @param yPoints an array of y points */
	public void drawPolyline(int[] xPoints, int[] yPoints) {
		if (xPoints.length!=yPoints.length) throw new RuntimeException("numbers of x and coordinates differ ("+xPoints.length+"<>"+yPoints.length+")");
		paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(xPoints[0], yPoints[0]);
        for (int i=1; i<xPoints.length; i++){
            path.lineTo(xPoints[i], yPoints[i]);
        }
		canvas.drawPath(path, paint);
	}
	
	/** Draws the outline of the specified rectangle.
	  * @param x the x coordinate of the rectangle
	  * @param y the y coordinate of the rectangle
	  * @param width the width of the rectangle
	  * @param height the height of the rectangle */
	public void drawRect(int x, int y, int width, int height) {
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRect(x, y, x+width, y+height, paint);
	}
	
	/** Draws an outlined round-cornered rectangle using this graphics context's current color.
	  * @param x the x coordinate of the rectangle to be drawn
	  * @param y the y coordinate of the rectangle to be drawn
	  * @param width the width of the rectangle to be drawn
	  * @param height the height of the rectangle to be drawn
	  * @param arcWidth the horizontal diameter of the arc at the four corners
	  * @param arcHeight the vertical diameter of the arc at the four corners */
	public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		paint.setStyle(Paint.Style.STROKE);
		canvas.drawRoundRect(x, y, x+width, y+height, arcWidth, arcHeight, paint);
	}
	
	/** Draws the text given by the specified iterator, using this graphics context's current color.
	  * @param iterator the iterator whose text is to be drawn
	  * @param x the x coordinate
	  * @param y the y coordinate */
	public void drawString(AttributedCharacterIterator iterator, int x, int y) {
		canvas.drawText(iterator.toString(), x, y, paint);
	}
	
	/** Draws the text given by the specified string, using this graphics context's current font and color.
	  * @param str the string
	  * @param x the x-coordinate of the position of the string
	  * @param y the y-coordinate of the position of the string */
	public void drawString(String str, int x, int y) {
		canvas.drawText(str, x, y, paint);
	}

	/** Draws the specified string using the current font and color.
	 * @param str the string
	 * @param x the x-coordinate of the position of the string
	 * @param y the y-coordinate of the position of the string
	 * @param anchor integer value that specifies the alignment (it should be an "OR" combination of TOP, BOTTOM, VCENTER, LEFT, RIGHT, HCENTER values) */
	public void drawString(String str, int x, int y, int anchor) {
		Rect bounds = new Rect();
		paint.getTextBounds(str, 0, str.length(), bounds);
		int h = (int)paint.getTextSize();

		int x0=x; // LEFT
		if ((anchor&ZwtAnchor.LEFT)!=0) x0=x;
		else
		if ((anchor&ZwtAnchor.RIGHT)!=0) x0=x-bounds.width();
		else
		if ((anchor&ZwtAnchor.HCENTER)!=0) x0=x-bounds.width()/2;
		// get y0
		int y0=y+h; // TOP
		if ((anchor&ZwtAnchor.TOP)!=0) y0=y+h;
		else
		if ((anchor&ZwtAnchor.BOTTOM)!=0) y0=y;
		else
		if ((anchor&ZwtAnchor.VCENTER)!=0) y0=y+h/2;

		// draw string at x,y
		canvas.drawText(str, x0, y0, paint);
	}

	/** Fills a circular or elliptical arc covering the specified rectangle.
	  * @param x the x-coordinate of the upper-left corner of the arc
	  * @param y the y-coordinate of the upper-left corner of the arc
	  * @param width the width of the arc
	  * @param height the height of the arc
	  * @param startAngle  the beginning angle
	  * @param arcAngle the angular extent of the arc, relative to the start angle */
	public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		canvas.drawArc(x, y, x+width, y+height, startAngle, arcAngle, true, paint);
	}
	
	/** Fills an oval bounded by the specified rectangle with the current color.
	  * @param x the x coordinate of the upper left corner of the oval to be drawn
	  * @param y the y coordinate of the upper left corner of the oval to be drawn
	  * @param width the width of the oval to be drawn
	  * @param height the height of the oval to be drawn */
	public void fillOval(int x, int y, int width, int height) {
		paint.setStyle(Paint.Style.FILL);
		canvas.drawOval(x, y, x+width, y+height, paint);
	}
	
	/** Fills the specified rectangle.
	  * @param x the x coordinate of the rectangle
	  * @param y the y coordinate of the rectangle
	  * @param width the width of the rectangle
	  * @param height the height of the rectangle */
	public void fillRect(int x, int y, int width, int height) {
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRect(x, y, x+width, y+height, paint);
	}
	
	/** Fills the specified rounded corner rectangle with the current color.
	  * @param x the x coordinate of the rectangle
	  * @param y the y coordinate of the rectangle
	  * @param width the width of the rectangle
	  * @param height the height of the rectangle
	  * @param arcWidth the horizontal diameter of the arc at the four corners
	  * @param arcHeight the vertical diameter of the arc at the four corners */
	public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
		paint.setStyle(Paint.Style.FILL);
		canvas.drawRoundRect(x, y, x+width, y+height, arcWidth, arcHeight, paint);
	}

	/** Fills the specified triangle will the current color.
	 * @param x1 the x-coordinate of the first vertex
	 * @param y1 the y-coordinate of the first vertex
	 * @param x2 the x-coordinate of the second vertex
	 * @param y2 the y-coordinate of the second vertex
	 * @param x3 the x-coordinate of the third vertex
	 * @param y3 the y-coordinate of the third vertex */
	public void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		paint.setStyle(Paint.Style.FILL);
		Path path = new Path();
		path.setFillType(Path.FillType.EVEN_ODD);
		path.moveTo(x1, y1);
		path.lineTo(x2, y2);
		path.lineTo(x3, y3);
		path.close();
		canvas.drawPath(path, paint);
	}

	/** Draws an Image at the specified coordinates.
      * @param image the ZwtImage
      * @param x the x coordinate of the image
      * @param y the y coordinate of the image*/
	public void drawImage(ZwtImage image, int x, int y){
		canvas.drawBitmap(image.getImage(), x, y, paint);
    };
	
	/** Disposes of this graphics context once it is no longer referenced. */
	public void finalize() {
		//graphics.finalize();
	}
	

	/** Gets the current font.
	  * @return the current font */
	public ZwtFont getFont() {
		return new ZwtFont(new ZwtFont(paint));
	}
	
	/** Sets this graphics context's font to the specified font.
	  * @param font the font */
	public void setFont(ZwtFont font) {
		paint = font.getPaint();
	}
	
	/** Gets a string representing this graphics context. */
	public String toString() {
		return canvas.toString();
	}

}
