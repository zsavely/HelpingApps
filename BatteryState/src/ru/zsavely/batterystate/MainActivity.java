package ru.zsavely.batterystate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Toast.makeText(this,
				"USB Plugged:     10/30 minutes;\nUSB Unplugged: 1 minute.",
				Toast.LENGTH_SHORT).show();
		finish();
	}

}