# ZWT

ZWT (Zero-change Windows Toolkit) is a cross-platform framework for developing GUIs, for any system that supports the Java language regardless the underlying UI libraries available for accessing graphical objects (like AWT and Swing for Java SE, MIDP for Java ME, or Android API for Android).

It provides a light set of well-known APIs (like classic Swing and AWT) for rapidly developing simple and totally cross-platform applications.

It supports all three main Java environments: Java SE/EE, Java ME Embedded, and Android, and can be used on any system that runs any of these environments.

<img src="images/zwt-architecture.png" width="50%">

Here you can find all ZWT resources, including both common source code and specific source code for Java SE, Java ME, and Android.
In particular:

* [src-common](/src-common): all ZWT common sources that are independent from the used platform.
* [src-se](/src-se): ZWT core sources specific for Java SE;
* [src-me](/src-me): ZWT core sources specific for Java ME;
* [src-android](/src-android): ZWT core sources specific for Android.

In order to show the simplicity of running the same application on different platforms here we report some sample codes used for ran an app on Java SE, Java ME, and Android.

Since the ZWT library resembles other well-known GUI libraries, writing an app with CP UI is very simple. Hereafter, just as very simple example of code of a *Hello World* application, that displays a label "Hello world" and a "Ok" button for exiting, is shown:

```java
import it.unipr.netsec.zwt.*;
import it.unipr.netsec.zwt.layout.ZwtBorderLayout;

public class Hello {

	public Hello(ZwtFrame frame) {
		frame.setLayout(new ZwtBorderLayout());
		ZwtLabel label=new ZwtLabel("Hello world");
		label.setColor(ZwtColor.WHITE);
		label.setAlignment(ZwtLabel.ALIGN_HCENTER);
		frame.addComponent(label,ZwtBorderLayout.CENTER);
		ZwtButtonListener listener=new ZwtButtonListener() {
			@Override
			public void onButtonPushed(ZwtButton arg0) {
				System.exit(0);
			}			
		};
		ZwtButton button=new ZwtButton("Ok",ZwtKeyboard.KEY_SELECT,listener);
		frame.addComponent(button,ZwtBorderLayout.SOUTH);
	}
}
```
