package com.deepak.myclock;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

public class PackageUtils {

	public PackageUtils() {
	}

	public static void isPackageInstalled(String s) {
		Intent intent;
		PackageManager packagemanager = MainApplication.applicationContext.getPackageManager();
		try {
			intent = packagemanager.getLaunchIntentForPackage(s);
			if (intent == null)
				throw new PackageManager.NameNotFoundException();
			
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			MainApplication.applicationContext.startActivity(intent);
		} catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception) {
		}
	}
}
