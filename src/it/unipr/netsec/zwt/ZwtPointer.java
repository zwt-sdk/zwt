package it.unipr.netsec.zwt;



/** A pointer.
  */
public class ZwtPointer {
	
	/** Icon */
	ZwtImage icon;

	/** Position */
	ZwtPoint pos;


	/** Creates a new ZwtPointer. */
	public ZwtPointer() {
		/*int N=10;
		int[] argbData=new int[N*N];
		for (int y=0; y<N; y++)
			for (int x=0; x<N; x++) {
				//argbData[x+N*y]=(x==0 || y==0 || x==y)? 0xff000000 : 0x00ffffff;
				argbData[x+N*y]=(x==0 || y==0)? argb=0xff000000 : (x==1 || y==1 || x==y)? argb=0xffffffff : (x==y-1 || x==y+1 || x==2 || y==2)? argb=0xff000000 : 0;
			}*/
		int N=10;
		int[] argbData=new int[N*N];
		for (int y=0; y<N; y++)
			for (int x=0; x<N; x++) {
				int argb=0;
				if (x==0 && y<N) argb=0xff000000;
				else if (y==0 && x<N) argb=0xff000000;
				else if (x>=y-2 && x<=y+2 && x+y<2*N-4) argb=0xffffffff;
				else if (x==N-y) argb=0xff000000;
				else if (x<=N-y) argb=0xffffffff;
				else if (x==y-3 || x==y+3) argb=0xff000000;
				else if (x+y==2*N-4) argb=0xff000000;
				argbData[x+N*y]=argb;
			}  
		//icon=new ZwtImage(argbData,N,N);
		pos=new ZwtPoint();
	}

	/** Creates a new ZwtPointer.
	  * @param icon the pointer icon */
	public ZwtPointer(ZwtImage icon) {
		this.icon=icon;
		pos=new ZwtPoint();
	}

	/** Gets coordinates.
	  * @return the pointer position */
	public ZwtPoint getCoordinates() {
		return pos;
	}

	/** Moves to new coordinates.
	  * @param x the x coordinate of the new pointer position
	  * @param y the y coordinate of the new pointer position */
	public void moveTo(int x, int y) {
		pos.setXY(x,y);
	}

	/** Moves to new coordinates.
	  * @param p the new pointer position */
	public void moveTo(ZwtPoint p) {
		pos.setXY(p);
	}

	/** Moves up by a given distance.
	  * @param dx the distance for the x coordinate
	  * @param dy the distance for the y coordinate */
	public void moveUp(int dx, int dy) {
		pos.setXY(pos.getX()+dx,pos.getY()+dy);
	}

	/** Gets width.
	  * @return the width of the pointer icon */
	public int getWidth() {
		return icon.getWidth();
	}

	/** Gets height.
	  * @return the height of the pointer icon */
	public int getHeight() {
		return icon.getHeight();
	}

	/** Gets icon.
	* @return the pointer icon */
	public ZwtImage getIcon() {
		return icon;
	}

	/** Renders the pointer at a specific position. */
	/*public void paint(ZwtGraphics g, int x, int y) {
		icon.paint(g,x+pos.getX(),y+pos.getY());
	}*/

}
