package com.gravata.netconsul;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;


public class Utils {

	private static String ACENTOS = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿ ";

	public static String removeAcentosEspacos(String str) {

		String tmp= "";

		for(int i=0;i<str.length();i++){
			String caracter=String.valueOf(str.charAt(i));
			if(!ACENTOS.contains(caracter))
				tmp+=caracter;
		}
		return tmp;
	}


	public static int getOriginalColor(View view){
		int originalColor = Color.TRANSPARENT;
		Drawable background = view.getBackground();
		if (background instanceof ColorDrawable)
			originalColor = ((ColorDrawable) background).getColor();

		return originalColor;

	}


}