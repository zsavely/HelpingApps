package ru.zsavely.insertsms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Random;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.widget.Toast;

public class ActivityMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		saveSms();
		// finish();
	}

	private void saveSms() {
		String[] sms = readFileFromAssets("sms.txt");

		for (String s : sms) {
			insertSms(s);
		}

		Toast.makeText(this, "Готово!", Toast.LENGTH_SHORT).show();
	}

	@TargetApi(Build.VERSION_CODES.KITKAT)
	private void check() {
		try {
			final String myPackageName = getPackageName();

			int currentapiVersion = android.os.Build.VERSION.SDK_INT;

			if (currentapiVersion == android.os.Build.VERSION_CODES.KITKAT) {
				if (!Telephony.Sms.getDefaultSmsPackage(this).equals(
						myPackageName)) {
					// App is not default.
					// Show the "not currently set as the default SMS app"
					// interface
					// View viewGroup = findViewById(R.id.not_default_app);
					// viewGroup.setVisibility(View.VISIBLE);
					//
					// // Set up a button that allows the user to change the
					// default
					// SMS
					// app
					// Button button = (Button)
					// findViewById(R.id.change_default_app);
					// button.setOnClickListener(new View.OnClickListener() {
					// public void onClick(View v) {
					Intent intent = new Intent(
							Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT);
					intent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME,
							myPackageName);
					startActivity(intent);
					// }
					// });
				} else {
					// App is the default.
					// Hide the "not currently set as the default SMS app"
					// interface
					// View viewGroup = findViewById(R.id.not_default_app);
					// viewGroup.setVisibility(View.GONE);
				}
			}
		} catch (Exception e) {

		}
	}

	private void insertSms(String message) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -31 + (new Random()).nextInt(31));

		ContentValues values = new ContentValues();
		values.put("address", "VTB24");
		values.put("body", message);
		values.put("date", String.valueOf(c.getTimeInMillis()));
		getContentResolver().insert(Uri.parse("content://sms/inbox"), values);
	}

	private String[] readFileFromAssets(String fileName) {
		String tContents = "";

		try {
			InputStream stream = getAssets().open(fileName);

			int size = stream.available();
			byte[] buffer = new byte[size];
			stream.read(buffer);
			stream.close();
			tContents = new String(buffer);
		} catch (IOException e) {

		}

		return tContents.split("\n");
	}

	@Override
	protected void onResume() {
		check();
		super.onResume();
	}

}
