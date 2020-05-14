package test;


import it.unipr.netsec.zwt.ZwtFrame;
import it.unipr.netsec.zwt.ZwtView;
import test.hello.Hello;

import javax.microedition.midlet.MIDlet;


public class HelloMIDlet extends MIDlet {
	
	public HelloMIDlet() {
		ZwtFrame zf=new ZwtFrame(new ZwtView(this),-1,-1);
		new Hello(zf);
		zf.repaintScreen();
	}

	public void startApp() {}  
	
	public void pauseApp() {}  
	
	public void destroyApp(boolean unconditional) {}

}
