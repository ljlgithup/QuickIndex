package com.example.quick.index.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
	
	private static Toast toast;

	/**
	 * 要显示的单例吐司
	 * @param letter
	 */
	public static void ShowToast(Context context,String letter){
		if(toast==null){
			toast = Toast.makeText(context, "", 0);
		}
		toast.setText(letter);
		toast.show();
	}
}
