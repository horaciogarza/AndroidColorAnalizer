package com.hgarzstudios.ColorAnalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.hgarzstudios.ColorAnalyzer.proyecto.FavoritesActivity;
import com.hgarzstudios.ColorAnalyzer.proyecto.MainActivity;
import com.example.hernanlopez.proyecto.R;

public class HomeActivity extends AppCompatActivity {



	Button analyzeButton = null;
	Button pinnedButton = null;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		AdView mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);



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
