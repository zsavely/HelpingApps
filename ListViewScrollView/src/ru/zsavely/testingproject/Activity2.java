package ru.zsavely.testingproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Activity2 extends Activity {

	String[] values = new String[] {
			"Транзакция # 1. Кликнуть, чтобы перейти на вариант #1.",
			"Транзакция # 2. Кликнуть, чтобы перейти на вариант #1.",
			"Транзакция # 3. Кликнуть, чтобы перейти на вариант #1.",
			"Транзакция # 4. Кликнуть, чтобы перейти на вариант #1.",
			"Транзакция # 5. Кликнуть, чтобы перейти на вариант #1.",
			"Транзакция # 6. Кликнуть, чтобы перейти на вариант #1.",
			"Транзакция # 7. Кликнуть, чтобы перейти на вариант #1.",
			"Транзакция # 8. Кликнуть, чтобы перейти на вариант #1." };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity2);
		setListView();
	}

	private void setListView() {
		final ListView v = (ListView) findViewById(R.id.listView1);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);

		View header = getLayoutInflater().inflate(R.layout.header, null);
		// View footer = getLayoutInflater()
		// .inflate(R.layout.footer, null);

		v.addHeaderView(header);
		// v.addFooterView(footer);

		v.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				finish();
			}
		});

		v.setAdapter(adapter);
	}
}
