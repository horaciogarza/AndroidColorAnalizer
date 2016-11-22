package com.example.hernanlopez.proyecto;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class FavoritesActivity extends AppCompatActivity {

	ArrayList<DataModel> dataModels;
	ListView listView;
	private static CustomAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);


		listView = (ListView)findViewById(R.id.list);

		dataModels = new ArrayList<>();

		Realm realm = null;// = Realm.getDefaultInstance();
		realm.init(this);
		realm  = Realm.getDefaultInstance();
		//realm.beginTransaction();
		RealmResults<HistoryRealm> r = realm.where(HistoryRealm.class).findAll();

		for (HistoryRealm item: r){
			dataModels.add(new DataModel(item.getColorName(), item.getHexValue(), item.getRgbValue(), "", item.getHexValue()));
		}


		//dataModels.add(new DataModel("Banana Bread", "Android 1.1", "2","February 9, 2009"));


		adapter= new CustomAdapter(dataModels,getApplicationContext());

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				DataModel dataModel= dataModels.get(position);


			}
		});
	}
}
