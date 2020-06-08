# ZWT

ZWT (Zero-change Windows Toolkit) is a cross-platform framework for developing GUIs, for any platform that supports the Java language regardless the underlying UI libraries available for accessing to graphical objects (like AWT and Swing for Java SE, MIDP for Java ME, or Android API for Android).

It provides a light set of well-known APIs (like classic Swing and AWT) for rapidly developing simple and totally cross-platform applications.

It currently supports all three main Java environments: Java SE/EE, Java ME Embedded, and Android, and can be used on any platform that runs any of these environments.

Here youcan find all ZWT resources, including both common and specific source code for Java SE, Java ME, and Android.
Avaiable folders:

* src-common: all common sources, independent from the used platform.
* src-se: ZWT core implementation for Java SE;
* src-me: ZWT core implementation for Java ME;
* src-android: ZWT core implementation for Android.

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
