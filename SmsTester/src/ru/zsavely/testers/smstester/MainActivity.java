package ru.zsavely.testers.smstester;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static final String SMS_OUTBOX = "outbox";
	public static final String SMS_INBOX = "inbox";
	public static final String SMS_DRAFTS = "draft";
	public static final String SMS_ALL = "all";
	public static final String SMS_SENT = "sent";

	@SuppressWarnings("unused")
	private ArrayList<RawSMS> inbox;

	@SuppressWarnings("unused")
	private ArrayList<RawSMS> outbox;

	// Inbox = "content://sms/inbox"
	// Failed = "content://sms/failed"
	// Queued = "content://sms/queued"
	// Sent = "content://sms/sent"
	// Draft = "content://sms/draft"
	// Outbox = "content://sms/outbox"
	// Undelivered = "content://sms/undelivered"
	// All = "content://sms/all"
	// Conversations = "content://sms/conversations"
	// "content://mms-sms/conversations"

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getAllSms(this, SMS_OUTBOX);
		getAllSms(this, SMS_INBOX);
		getAllSms(this, SMS_SENT);
		getAllSms(this, SMS_DRAFTS);
		getTelephone();
		startService();
	}

	public void sendSms() {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:5880"));
		intent.putExtra("sms_body", "");
		startActivity(intent);
	}

	public void dial() {
		startActivity(new Intent("android.intent.action.CALL",
				Uri.parse("tel:*110*9166449665" + Uri.encode("#"))));
	}

	public void startService() {
		Intent i = new Intent(this, TelephoneService.class);
		startService(i);
	}

	public void getTelephone() {
		TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		((TextView) findViewById(R.id.textViewPerson)).setText(String
				.valueOf(tMgr.getLine1Number()));
	}

	public void getAllSms(final Context context, final String type) {
		new Thread(new Runnable() {
			public void run() {
				ArrayList<RawSMS> lstSms = new ArrayList<RawSMS>();
				RawSMS objSms = new RawSMS();
				Uri message = Uri.parse("content://sms/" + type);
				ContentResolver cr = context.getContentResolver();

				Cursor c = cr.query(message, null, null, null, null);
				// getApplicationContext().startManagingCursor(c);
				int totalSMS = c.getCount();

				Log.i("Sms from " + type, "Total sms: " + totalSMS);

				if (c.moveToFirst()) {
					for (int i = 0; i < totalSMS; i++) {

						objSms = new RawSMS();
						objSms.setId(c.getString(c.getColumnIndexOrThrow("_id")));
						objSms.setAddress(c.getString(c
								.getColumnIndexOrThrow("address")));
						objSms.setMsg(c
								.getString(c.getColumnIndexOrThrow("body"))
								.replaceAll("\r", "").replaceAll("\t", " ")
								.replaceAll("\n", " "));
						objSms.setReadState(c.getString(c
								.getColumnIndex("read")));
						objSms.setTime(c.getString(c
								.getColumnIndexOrThrow("date")));
						objSms.setPerson(c.getString(c
								.getColumnIndexOrThrow("person")));

						if (objSms.getAddress().contains("9166449665")) {
							Log.i("SmsFrom" + type,
									"Sender: " + objSms.getAddress());
							Log.i("SmsFrom" + type,
									"Message: " + objSms.getMsg());
							Log.i("SmsFrom" + type,
									"Sender: " + objSms.getAddress());
							Log.i("SmsFrom" + type,
									"Sender: " + objSms.getAddress());
						}

						lstSms.add(objSms);
						c.moveToNext();
					}
				}
				c.close();

				if (type.equals(SMS_OUTBOX))
					outbox = lstSms;
				else if (type.equals(SMS_INBOX))
					inbox = lstSms;
			}
		}).start();
	}
}