package ru.zsavely.batterystate;

import java.text.NumberFormat;
import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.provider.Settings;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		Intent batteryStatus = context.getApplicationContext()
				.registerReceiver(null, ifilter);

		// Are we charging / charged?
		// int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS,
		// -1);

		// boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING
		// || status == BatteryManager.BATTERY_STATUS_FULL;

		// How are we charging?
		int chargePlug = batteryStatus.getIntExtra(
				BatteryManager.EXTRA_PLUGGED, -1);
		boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
		// boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

		// setTimeout(usbCharge ? 5 : 2);
		if (usbCharge) {
			setTimeout(5, context);
		} else {
			setTimeout(2, context);
		}
	}

	private void setTimeout(int screenOffTimeout, Context mContext) {
		double time;
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
				Settings.System.SCREEN_OFF_TIMEOUT, (int) time);
	}
}
