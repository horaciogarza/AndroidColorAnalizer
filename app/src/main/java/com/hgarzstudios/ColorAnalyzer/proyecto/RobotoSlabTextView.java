package com.hgarzstudios.ColorAnalyzer.proyecto;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by horaciogarza on 11/21/16.
 */
public class RobotoSlabTextView extends TextView {


	public RobotoSlabTextView(Context context) {
		super(context);

		Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/chiller.ttf");
		setTypeface(tf ,1);
	}

	public RobotoSlabTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RobotoSlabTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

	}



}
