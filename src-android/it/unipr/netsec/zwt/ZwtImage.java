package it.unipr.netsec.zwt;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;

public class ZwtImage extends ZwtComponent {

	/** The image */
    protected Bitmap image;

    /** Creates a new ZwtImage.
     * @param path the path of the selected image */
    public ZwtImage(String path) throws IOException {
        try {
			setImage(BitmapFactory.decodeFile(path));
		}
		catch (RuntimeException e) { throw new IOException(e); }
    }

	/** Creates a new image.
	 * @param img the image */
	public ZwtImage(ZwtImage img) {
		setImage(img.image);
	}

	/** Creates a new ZwtImage.
     * @param resources the res folder of the selected image
     * @param id the id of the selected image */
    public ZwtImage(Resources resources, int id) {
		setImage(BitmapFactory.decodeResource(resources, id));
	}

    /** Creates a new ZwtImage.
     * @param image the selected image */
    public ZwtImage(Bitmap image) {
		setImage(image);
	}

    /** Creates a new ZwtImage.
     * @param image the selected image
     * @param x the left coordinate of the cropped image
     * @param y the top coordinate of the cropped image
     * @param width the width of the cropped image
     * @param height the height of the cropped image */
    public ZwtImage(Bitmap image, int x, int y, int width, int height) {
		setImage(Bitmap.createBitmap(image, x, y, width, height));
	}

    /** Crops the image.
     * @param x the left coordinate of the cropped image
     * @param y the top coordinate of the cropped image
     * @param width the width of the cropped image
     * @param height the height of the cropped image */
    public void cropImage(int x, int y, int width, int height) {
		setImage(Bitmap.createBitmap(image, x, y, width, height));
	}

    /** Scale the image.
     * @param width the width of the scaled image
     * @param height the height of the scaled image
     */
    public void scaleImage(int width, int height) {
		setImage(Bitmap.createScaledBitmap(image, width, height, false));
	}

    /** Sets a new ZwtImage.
     * @param path the path of the selected image */
    public void setImage(String path) {
    	setImage(BitmapFactory.decodeFile(path));
  }

    /** Sets a new ZwtImage.
     * @param resources the res folder of the selected image
     * @param id the id of the selected image */
    public void setImage(Resources resources, int id) {
        setImage(BitmapFactory.decodeResource(resources, id));
	}

    /** Sets a new image.
     * @param image the selected image */
    public void setImage(Bitmap image) {
    	this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

    /** Sets a new image.
     * @param image the selected image
     * @param x the left coordinate of the cropped image
     * @param y the top coordinate of the cropped image
     * @param width the width of the cropped image
     * @param height the height of the cropped image */
    public void setImage(Bitmap image, int x, int y, int width, int height) {
        setImage(Bitmap.createBitmap(image, x, y, width, height));
	}

    /** Gets the image */
    public Bitmap getImage() {
        return image;
    }

    @Override
    public void paint(ZwtGraphics g, int x, int y, ZwtRect area) {
        //super.paint(g,x,y);
        if (image!=null  && area.contains(x,y)) {
            g.drawImage(new ZwtImage(this),x,y);
        }
    }

}
