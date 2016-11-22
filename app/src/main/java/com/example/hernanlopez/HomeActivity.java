package com.example.hernanlopez;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.hernanlopez.proyecto.FavoritesActivity;
import com.example.hernanlopez.proyecto.HistoryRealm;
import com.example.hernanlopez.proyecto.MainActivity;
import com.example.hernanlopez.proyecto.R;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class HomeActivity extends AppCompatActivity {


	Button historyButton = null;
	Button analyzeButton = null;
	Button pinnedButton = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);


		analyzeButton = (Button) findViewById(R.id.analyzeButton);
		pinnedButton = (Button) findViewById(R.id.pinnedButton);

		analyzeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				startActivity(new Intent(HomeActivity.this, MainActivity.class));
			}
		});

		pinnedButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				startActivity(new Intent(HomeActivity.this, FavoritesActivity.class));
			}
		});



	}


}
