package test.hello;

import javax.swing.JFrame;

import it.unipr.netsec.zwt.ZwtFrame;

public class HelloMain {

	public static void main(String[] args) {
		JFrame jframe=new JFrame();
		ZwtFrame frame=new ZwtFrame(jframe,200,100);
		new Hello(frame);
	}
}
