package ru.zsavely.helper.coding.timeout;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTimeout(getTimeOutId());
		finish();
	}

	private int getTimeOutId() {
		int current = android.provider.Settings.System.getInt(
				getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, -1);
		int tenMin = 600000;

		if (current == tenMin) {
			Toast.makeText(this, "Current: 1 minute.", Toast.LENGTH_SHORT)
					.show();
			return 2;
		}
		Toast.makeText(this, "Current: 10 minutes.", Toast.LENGTH_SHORT).show();
		return 4;
	}

	private void setTimeout(int screenOffTimeout) {
		int time;
		switch (screenOffTimeout) {
		case 0:
			time = 15000;
			break;
		case 1:
			time = 30000;
			break;
		case 2:
			time = 60000;
			break;
		case 3:
			time = 120000;
			break;
		case 4:
			time = 600000;
			break;
		case 5:
			time = 1800000;
			break;
		default:
			time = -1;
		}
		android.provider.Settings.System.putInt(getContentResolver(),
				Settings.System.SCREEN_OFF_TIMEOUT, time);
	}
}