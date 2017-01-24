package com.hgarzstudios.ColorAnalyzer.proyecto;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by horaciogarza on 11/22/16.
 */
public class HistoryRealm extends RealmObject {




	private String colorName;
	private String hexValue;
	private String rgbValue;
	private boolean isFav;

		@Ignore
		private int             sessionId;



	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getHexValue() {
		return hexValue;
	}

	public void setHexValue(String hexValue) {
		this.hexValue = hexValue;
	}

	public String getRgbValue() {
		return rgbValue;
	}

	public void setRgbValue(String rgbValue) {
		this.rgbValue = rgbValue;
	}

	public boolean isFav() {
		return isFav;
	}

	public void setFav(boolean fav) {
		isFav = fav;
	}
}
