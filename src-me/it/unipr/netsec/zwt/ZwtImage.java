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


import javax.microedition.lcdui.Image;


/** Image.
  */
public class ZwtImage extends ZwtComponent {
	
	/** Image */
	Image image=null;


	/** Creates a new ZwtImage. */
	protected ZwtImage() {	
	}

	/** Creates a new ZwtImage.
	 * @param argbData ARGB data
	 * @param width the image width 
	 * @param height the image height */
	protected ZwtImage(int[] argbData, int width, int height) {
		setImage(Image.createRGBImage(argbData,width,height,false));
	}

	/** Creates a new ZwtImage. */
	public ZwtImage(String file) throws java.io.IOException  {
		setImage(file);
	}

	/** Creates a new ZwtImage.
	 * @param img the image */
	public ZwtImage(ZwtImage img) {
		setImage(img.image);
	}

	/** Creates a new ZwtImage.
	 * @param image the image */
	public static ZwtImage createImage(Image image) {
		ZwtImage img=new ZwtImage();
		img.setImage(image);
		return new ZwtImage(img);
	}

	/** Sets image. */
	protected void setImage(Image image) {
		this.image=image;
		width=image.getWidth();
		height=image.getHeight();
	}

	/** Gets the image. */
	public Image getImage() {
		return image;
	}

	/** Sets image. */
	public void setImage(String file) throws java.io.IOException  {
		setImage(Image.createImage(getClass().getResourceAsStream("/"+file)));
	}

	/** Scales the image.
	 * @param width the width of the scaled image
	 * @param height the height of the scaled image */
	public void scaleImage(int width, int height) {
		// TODO
		//setImage(newImage);
	}

    /** From ZwtComponent. Renders the component at a specific position. */
	public void paint(ZwtGraphics g, int x, int y, ZwtRect area) {
		if (image!=null && area.hasIntersection(x,y,width,height)) {
			g.drawImage(this,x,y);
		}
	}
 
}
