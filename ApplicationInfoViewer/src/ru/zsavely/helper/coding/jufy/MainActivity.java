package ru.zsavely.helper.coding.jufy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			showInstalledAppDetails(this, "ru.belonika");
			Toast.makeText(this, "Opened", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(this, "Couldn't open settings", Toast.LENGTH_SHORT)
					.show();
		}
		finish();
	}

	private static final String SCHEME = "package";

	private static final String APP_PKG_NAME_21 = "com.android.settings.ApplicationPkgName";

	private static final String APP_PKG_NAME_22 = "pkg";

	private static final String APP_DETAILS_PACKAGE_NAME = "com.android.settings";

	private static final String APP_DETAILS_CLASS_NAME = "com.android.settings.InstalledAppDetails";

	public static void showInstalledAppDetails(Context context,
			String packageName) {
		Intent intent = new Intent();
		final int apiLevel = Build.VERSION.SDK_INT;
		if (apiLevel >= 9) { // above 2.3
			intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
			Uri uri = Uri.fromParts(SCHEME, packageName, null);
			intent.setData(uri);
		} else { // below 2.3
			final String appPkgName = (apiLevel == 8 ? APP_PKG_NAME_22
					: APP_PKG_NAME_21);
			intent.setAction(Intent.ACTION_VIEW);
			intent.setClassName(APP_DETAILS_PACKAGE_NAME,
					APP_DETAILS_CLASS_NAME);
			intent.putExtra(appPkgName, packageName);
		}
		context.startActivity(intent);
	}
}
