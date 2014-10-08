package ru.zsavely.batterystate;

import java.text.NumberFormat;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Context mContext;

	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mContext = this;

		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = mContext.registerReceiver(null, ifilter);

		// Are we charging / charged?
		int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

		boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
				|| status == BatteryManager.BATTERY_STATUS_FULL;

		// How are we charging?
		int chargePlug = batteryStatus.getIntExtra(
				BatteryManager.EXTRA_PLUGGED, -1);
		boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
		boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

		if (usbCharge) {
			setTimeout(5);
		} else {
			setTimeout(2);
		}
		finish();

	}

	@SuppressWarnings("unused")
	private int getTimeOutId() {
		int current = android.provider.Settings.System.getInt(
				mContext.getContentResolver(),
				Settings.System.SCREEN_OFF_TIMEOUT, -1);
		int tenMin = 600000;

		if (current == tenMin) {
			Toast.makeText(mContext, "Current: 1 minute.", Toast.LENGTH_SHORT)
					.show();
			return 2;
		}
		Toast.makeText(mContext, "Current: 10 minutes.", Toast.LENGTH_SHORT)
				.show();
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

		double minutes = time / 1000 / 60;

		NumberFormat numberFormatter;
		Locale currentLocale = Locale.FRANCE;
		numberFormatter = NumberFormat.getNumberInstance(currentLocale);

		numberFormatter.setMaximumFractionDigits(2);
		numberFormatter.setMinimumFractionDigits(0);

		Toast.makeText(mContext,
				"Current: " + numberFormatter.format(minutes) + " minute(s).",
				Toast.LENGTH_SHORT).show();

		android.provider.Settings.System.putInt(mContext.getContentResolver(),
				Settings.System.SCREEN_OFF_TIMEOUT, time);
	}

}
