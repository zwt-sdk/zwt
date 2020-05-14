package test.hello;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

import it.unipr.netsec.zwt.ZwtFrame;

public class HelloMainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello_main);

		int statusBarHeight = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) statusBarHeight = getResources().getDimensionPixelSize(resourceId);

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y - statusBarHeight;

		ImageView imageView = findViewById(R.id.myImageView);

		ZwtFrame frame = new ZwtFrame(imageView, width, height);

		new Hello(frame);
	}
}
