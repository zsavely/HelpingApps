package ru.zsavely.testingproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Activity1 extends Activity {

	String[] values = new String[] {
			"Транзакция # 1. Можно скроллить. Кликнуть, чтобы перейти на вариант #2.",
			"Транзакция # 2. Можно скроллить. Кликнуть, чтобы перейти на вариант #2.",
			"Транзакция # 3. Можно скроллить. Кликнуть, чтобы перейти на вариант #2.",
			"Транзакция # 4. Можно скроллить. Кликнуть, чтобы перейти на вариант #2.",
			"Транзакция # 5. Можно скроллить. Кликнуть, чтобы перейти на вариант #2.",
			"Транзакция # 6. Можно скроллить. Кликнуть, чтобы перейти на вариант #2.",
			"Транзакция # 7. Можно скроллить. Кликнуть, чтобы перейти на вариант #2.",
			"Транзакция # 8. Можно скроллить. Кликнуть, чтобы перейти на вариант #2." };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity1);
		setListView();
	}

	private void setListView() {
		ListView v = (ListView) findViewById(R.id.listView1);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		v.setAdapter(adapter);

		v.setOnTouchListener(new OnTouchListener() {

			// Setting on Touch Listener for handling the touch inside
			// ScrollView
			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// Disallow the touch request for parent scroll on touch of
				// child view
				v.getParent().requestDisallowInterceptTouchEvent(true);
				return false;
			}

		});

		v.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent(getApplicationContext(), Activity2.class);
				startActivity(i);
			}
		});
	}

}
