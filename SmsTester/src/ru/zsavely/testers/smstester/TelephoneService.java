package ru.zsavely.testers.smstester;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class TelephoneService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void showPhoneNumberToast() {
		TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		Toast.makeText(this, String.valueOf(tMgr.getLine1Number()),
				Toast.LENGTH_LONG).show();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		showPhoneNumberToast();
		return Service.START_NOT_STICKY;
	}

}
