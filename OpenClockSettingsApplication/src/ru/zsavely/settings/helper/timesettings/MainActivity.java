package ru.zsavely.settings.helper.timesettings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			startActivity(new Intent(
					android.provider.Settings.ACTION_DATE_SETTINGS));
		} catch (Exception e) {
			Toast.makeText(this, "Couldn't open time settings.",
					Toast.LENGTH_SHORT).show();
		}
		finish();
	}
}
