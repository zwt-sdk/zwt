package test.hello;


import it.unipr.netsec.zwt.ZwtButton;
import it.unipr.netsec.zwt.ZwtButtonListener;
import it.unipr.netsec.zwt.ZwtColor;
import it.unipr.netsec.zwt.ZwtFrame;
import it.unipr.netsec.zwt.ZwtKeyboard;
import it.unipr.netsec.zwt.ZwtLabel;
import it.unipr.netsec.zwt.layout.ZwtBorderLayout;


/** Displays "Hello world" message.
 */
public class Hello {

	public Hello(final ZwtFrame frame) {
		frame.setLayout(new ZwtBorderLayout());
		ZwtLabel label=new ZwtLabel("Hello world");
		label.setColor(ZwtColor.WHITE);
		label.setAlignment(ZwtLabel.ALIGN_HCENTER);
		frame.addComponent(label,ZwtBorderLayout.CENTER);
		ZwtButtonListener listener=new ZwtButtonListener() {
			@Override
			public void onButtonPushed(ZwtButton arg0) {
				frame.dispose();
				System.exit(0);
			}			
		};
		ZwtButton button=new ZwtButton("Ok",ZwtKeyboard.KEY_SELECT,listener);
		frame.addComponent(button,ZwtBorderLayout.SOUTH);
	}
}
