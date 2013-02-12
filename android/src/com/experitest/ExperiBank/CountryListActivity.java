package com.experitest.ExperiBank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CountryListActivity extends Activity {

	private String[] countriesArray = new String[] { "India", "USA", "Iceland", "Greenland", "Switzerland", "Norway", "New Zealand", "Greece", "Italy", "Ireland", "China", "Japan", "France", "Russia", "Australlia", "Canada" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.countrylist);
		
		ListView listView = (ListView) findViewById(R.id.countryList);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, countriesArray);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = getIntent();
				intent.putExtra("SelectedCountry", countriesArray[position]);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}
}
