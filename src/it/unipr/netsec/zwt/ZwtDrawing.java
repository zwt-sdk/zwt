package it.unipr.netsec.zwt;


import java.text.AttributedCharacterIterator;


/** Collects methods for drawing some shapes.
 */
public class ZwtDrawing {

	/** Draws a circular or elliptical arc covering the specified rectangle.
	  * @param g the graphics where to draw
	  * @param rect the rectangle
	  * @param startAngle the beginning angle
	  * @param arcAngle the angular extent of the arc, relative to the start angle */
	public static void drawArc(ZwtGraphics g, ZwtRect rect, int startAngle, int arcAngle) {
		drawArc(g,rect.x1(),rect.y1(),rect.width(),rect.height(),startAngle,arcAngle);
	}

	/** Draws the outline of a circular or elliptical arc covering the specified rectangle.
	  * @param g the graphics where to draw
	  * @param x the x-coordinate of the upper-left corner of the arc
	  * @param y the y-coordinate of the upper-left corner of the arc
	  * @param width the width of the arc
	  * @param height the height of the arc
	  * @param startAngle  the beginning angle
	  * @param arcAngle the angular extent of the arc, relative to the start angle */
	public static void drawArc(ZwtGraphics g, int x, int y, int width, int height, int startAngle, int arcAngle) {
		g.drawArc(x,y,width,height,startAngle,arcAngle);
	}
	
	/** Draws the text given by the specified character array, using this graphics context's current font and color.
	  * @param g the graphics where to draw
	  * @param data the array of characters to be drawn
	  * @param offset the start offset in the data
	  * @param length the number of characters to be drawn
	  * @param x the x coordinate of the baseline of the text
	  * @param y the y coordinate of the baseline of the text */
	public static void drawChars(ZwtGraphics g, char[] data, int offset, int length, int x, int y) {
		g.drawChars(data,offset,length,x,y);
	}
	
	/** Draws the specified image.
	  * @param g the graphics where to draw
	 * @param image
	 * @param x
	 * @param y */
	public static void drawImage(ZwtGraphics g, ZwtImage image, int x, int y) {
		g.drawImage(image,x,y);
	}
	
	/** Draws a line, using the current color, between the points (x1, y1) and (x2, y2) in this graphics context's coordinate system.
	  * @param g the graphics where to draw
	  * @param x1 the first point's x coordinate
	  * @param y1 the first point's y coordinate
	  * @param x2 the second point's x coordinate
	  * @param y2 the second point's y coordinate */
	public static void drawLine(ZwtGraphics g, int x1, int y1, int x2, int y2) {
		g.drawLine(x1,y1,x2,y2);
	}
	
	/** Draws an oval bounded by the specified rectangle with the current color.
	  * @param g the graphics where to draw
	  * @param rect the rectangle containing the oval */
	public static void drawOval(ZwtGraphics g, ZwtRect rect) {
		drawOval(g,rect.x1(),rect.y1(),rect.width(),rect.height());
	}
	
	/** Draws an oval bounded by the specified rectangle with the current color.
	  * @param g the graphics where to draw
	  * @param x the x coordinate of the upper left corner of the oval to be drawn
	  * @param y the y coordinate of the upper left corner of the oval to be drawn
	  * @param width the width of the oval
	  * @param height the height of the oval */
	public static void drawOval(ZwtGraphics g, int x, int y, int width, int height) {
		g.drawOval(x,y,width,height);
	}
	
	/** Draws a sequence of connected lines defined by arrays of x and y coordinates.
	  * @param g the graphics where to draw
	  * @param points an array of points */
	public static void drawPolyline(ZwtGraphics g, ZwtPoint... points) {
		int[] xPoints=new int[points.length];
		int[] yPoints=new int[points.length];
		for (int i=0; i<points.length; i++) {
			xPoints[i]=points[i].getX();
			yPoints[i]=points[i].getY();
		}
		drawPolyline(g,xPoints,yPoints);
	}
	
	/** Draws a sequence of connected lines defined by arrays of x and y coordinates.
	  * @param g the graphics where to draw
	  * @param xPoints an array of x coordinates
	  * @param yPoints an array of y coordinates */
	public static void drawPolyline(ZwtGraphics g, int[] xPoints, int[] yPoints) {
		g.drawPolyline(xPoints,yPoints);
	}
	
	/** Draws the specified rectangle.
	  * @param g the graphics where to draw
	  * @param rect the rectangle */
	public static void drawRect(ZwtGraphics g, ZwtRect rect) {
		drawRect(g,rect.x1(),rect.y1(),rect.width(),rect.height());
	}
	
	/** Draws the specified rectangle.
	  * @param g the graphics where to draw
	  * @param x the x coordinate of the rectangle
	  * @param y the y coordinate of the rectangle
	  * @param width the width of the rectangle
	  * @param height the height of the rectangle */
	public static void drawRect(ZwtGraphics g, int x, int y, int width, int height) {
		g.drawRect(x,y,width,height);
	}
	
	/** Draws the specified rounded corner rectangle with the current color.
	  * @param g the graphics where to draw
	  * @param rect the rectangle
	  * @param arcWidth the horizontal diameter of the arc at the four corners
	  * @param arcHeight the vertical diameter of the arc at the four corners */
	public static void drawRoundRect(ZwtGraphics g, ZwtRect rect, int arcWidth, int arcHeight) {
		drawRoundRect(g,rect.x1(),rect.y1(),rect.width(),rect.height(),arcWidth,arcHeight);
	}
	
	/** Draws the specified rounded corner rectangle with the current color.
	  * @param g the graphics where to draw
	  * @param x the x coordinate of the rectangle
	  * @param y the y coordinate of the rectangle
	  * @param width the width of the rectangle
	  * @param height the height of the rectangle
	  * @param arcWidth the horizontal diameter of the arc at the four corners
	  * @param arcHeight the vertical diameter of the arc at the four corners */
	public static void drawRoundRect(ZwtGraphics g, int x, int y, int width, int height, int arcWidth, int arcHeight) {
		g.drawRoundRect(x,y,width,height,arcWidth,arcHeight);
	}
	
	/** Draws the text given by the specified string, using this graphics context's current font and color.
	  * @param g the graphics where to draw
	  * @param str the string
	  * @param x the x-coordinate of the position of the string
	  * @param y the y-coordinate of the position of the string */
	public static void drawString(ZwtGraphics g, String str, int x, int y) {
		g.drawString(str,x,y);
	}
	
	/** Draws the specified triangle.
	  * @param g the graphics where to draw
	  * @param points three points */
	public static void drawTriangle(ZwtGraphics g, ZwtPoint... points) {
		if (points.length!=3) throw new RuntimeException("Invalid number of points ("+points.length+")");
		drawPolyline(g,points);
	}
	
	/** Draws the specified triangle.
	  * @param g the graphics where to draw
	  * @param x1 the x-coordinate of the first vertex
	  * @param y1 the y-coordinate of the first vertex
	  * @param x2 the x-coordinate of the second vertex
	  * @param y2 the y-coordinate of the second vertex
	  * @param x3 the x-coordinate of the third vertex
	  * @param y3 the y-coordinate of the third vertex */
	public static void drawTriangle(ZwtGraphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
		int[] xPoints={x1,x2,x3};
		int[] yPoints={y1,y2,y3};
		drawPolyline(g,xPoints,yPoints);
	}

	/** Fills a circular or elliptical arc covering the specified rectangle.
	  * @param g the graphics where to draw
	  * @param rect the rectangle
	  * @param startAngle the beginning angle
	  * @param arcAngle the angular extent of the arc, relative to the start angle */
	public static void fillArc(ZwtGraphics g, ZwtRect rect, int startAngle, int arcAngle) {
		fillArc(g,rect.x1(),rect.y1(),rect.width(),rect.height(),startAngle,arcAngle);
	}
	
	/** Fills a circular or elliptical arc covering the specified rectangle.
	  * @param g the graphics where to draw
	  * @param x the x-coordinate of the upper-left corner of the arc
	  * @param y the y-coordinate of the upper-left corner of the arc
	  * @param width the width of the arc
	  * @param height the height of the arc
	  * @param startAngle  the beginning angle
	  * @param arcAngle the angular extent of the arc, relative to the start angle */
	public static void fillArc(ZwtGraphics g, int x, int y, int width, int height, int startAngle, int arcAngle) {
		g.fillArc(x,y,width,height,startAngle,arcAngle);
	}
	
	/** Fills an oval bounded by the specified rectangle with the current color.
	  * @param g the graphics where to draw
	  * @param rect the rectangle containing the oval to be drawn */
	public static void fillOval(ZwtGraphics g, ZwtRect rect) {
		fillOval(g,rect.x1(),rect.y1(),rect.width(),rect.height());
	}
	
	/** Fills an oval bounded by the specified rectangle.
	  * @param g the graphics where to draw
	  * @param x the x coordinate of the upper left corner of the oval to be drawn
	  * @param y the y coordinate of the upper left corner of the oval to be drawn
	  * @param width the width of the oval to be drawn
	  * @param height the height of the oval to be drawn */
	public static void fillOval(ZwtGraphics g, int x, int y, int width, int height) {
		g.fillOval(x,y,width,height);
	}
	
	/** Fills the specified rectangle.
	  * @param g the graphics where to draw
	  * @param rect the rectangle */
	public static void fillRect(ZwtGraphics g, ZwtRect rect) {
		fillRect(g,rect.x1(),rect.y1(),rect.width(),rect.height());
	}
	
	/** Fills the specified rectangle.
	  * @param g the graphics where to draw
	  * @param x the x coordinate of the rectangle
	  * @param y the y coordinate of the rectangle
	  * @param width the width of the rectangle
	  * @param height the height of the rectangle */
	public static void fillRect(ZwtGraphics g, int x, int y, int width, int height) {
		g.fillRect(x,y,width,height);
	}
	
	/** Fills the specified rounded corner rectangle.
	  * @param g the graphics where to draw
	  * @param rect the rectangle
	  * @param arcWidth the horizontal diameter of the arc at the four corners
	  * @param arcHeight the vertical diameter of the arc at the four corners */
	public static void fillRoundRect(ZwtGraphics g, ZwtRect rect, int arcWidth, int arcHeight) {
		fillRoundRect(g,rect.x1(),rect.y1(),rect.width(),rect.height(),arcWidth,arcHeight);
	}
	
	/** Fills the specified rounded corner rectangle.
	  * @param g the graphics where to draw
	  * @param x the x coordinate of the rectangle
	  * @param y the y coordinate of the rectangle
	  * @param width the width of the rectangle
	  * @param height the height of the rectangle
	  * @param arcWidth the horizontal diameter of the arc at the four corners
	  * @param arcHeight the vertical diameter of the arc at the four corners */
	public static void fillRoundRect(ZwtGraphics g, int x, int y, int width, int height, int arcWidth, int arcHeight) {
		g.fillRoundRect(x,y,width,height,arcWidth,arcHeight);
	}

	/** Fills the specified triangle.
	  * @param g the graphics where to draw
	  * @param points three points */
	public static void fillTriangle(ZwtGraphics g, ZwtPoint... points) {
		if (points.length!=3) throw new RuntimeException("Invalid number of points ("+points.length+")");
		g.fillTriangle(points[0].getX(),points[0].getY(),points[1].getX(),points[1].getY(),points[2].getX(),points[2].getY());
	}

	/** Fills the specified triangle.
	  * @param g the graphics where to draw
	  * @param x1 the x-coordinate of the first vertex
	  * @param y1 the y-coordinate of the first vertex
	  * @param x2 the x-coordinate of the second vertex
	  * @param y2 the y-coordinate of the second vertex
	  * @param x3 the x-coordinate of the third vertex
	  * @param y3 the y-coordinate of the third vertex */
	public static void fillTriangle(ZwtGraphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
		g.fillTriangle(x1,y1,x2,y2,x3,y3);
	}


}
