package it.unipr.netsec.zwt;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import it.unipr.netsec.zwt.ZwtComponent;


/** Image.
  */
public class ZwtImage extends ZwtComponent {
	
	/** Image */
	protected Image image=null;

	/** Maximum loading waiting time [millisecs] */
	//static final int MAX_LOADING_TIME=200;


	/** Creates a new image. */
	protected ZwtImage() {	
	}

	/** Creates a new image.
	 * @param img the image */
	public ZwtImage(ZwtImage img) {
		setImage(img.image);
	}

	/** Creates a new image.
	 * @param file the name of the file containing the image */
	public ZwtImage(String file) throws java.io.IOException  {
		setImage(file);
	}

	/** Creates a new image.
	 * @param argbData ARGB data
	 * @param width the image width 
	 * @param height the image height */
	public ZwtImage(int[] argbData, int width, int height) {
		BufferedImage bi=new BufferedImage(width,height,java.awt.image.BufferedImage.TYPE_INT_ARGB);
		//for (int y=0; y<height; y++)
		//for (int x=0; x<width; x++) bi.setRGB(x,y,argbData[x+width*y]);
		bi.setRGB(0,0,width,height,argbData,0,width);
		setImage(bi);
	}

	/** Creates a new image.
	 * @param image the image */
	public ZwtImage(Image image) {
		setImage(image);
	}

	/** Sets image.
	 * @param image the image */
	void setImage(Image image) {
		this.image=image;
		width=image.getWidth(null);
		height=image.getHeight(null);
	}

	/** Gets the image.
	 * @return the image */
	Image getImage() {
		return image;
	}

	/** Sets image.
	* @param file the name of the file containing the image */
	private void setImage(String file) throws java.io.IOException  {
		Image image=null;
		if (file.toLowerCase().startsWith("http://") || file.toLowerCase().startsWith("https://")) {
			URL url=new URL(file);
			url.openConnection().connect();
			//image=java.awt.Toolkit.getDefaultToolkit().getImage(url);
			image=ImageIO.read(url);
		}
		else {
			//image=java.awt.Toolkit.getDefaultToolkit().getImage(file);
			image=ImageIO.read(new File(file));
			//image=ImageIO.read(ZwtImage.class.getResourceAsStream(file));
		}
		// Note: next two lines are required if the image is loaded throw the java.awt.Toolkit, due to reading delay:
		//int sleepTime=80;
		//for (int i=0; i<MAX_LOADING_TIME/sleepTime && image.getWidth(null)<0; i++) try {  Thread.sleep(sleepTime);  } catch (Exception e) {}
		setImage(image);
	}
	
	/** Scales the image.
	 * @param width the width of the scaled image
	 * @param height the height of the scaled image */
	public void scaleImage(int width, int height) {
		setImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}

	@Override
	public void paint(ZwtGraphics g, int x, int y, ZwtRect area) {
		//super.paint(g,x,y);
		//if (image!=null && area.contains(x,y)) {
		if (image!=null && area.hasIntersection(x,y,width,height)) {
			g.drawImage(new ZwtImage(this),x,y);
		}
	}
 
}
