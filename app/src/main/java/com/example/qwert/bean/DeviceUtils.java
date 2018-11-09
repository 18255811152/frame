package com.example.qwert.bean;

import android.content.Context;
import android.content.pm.PackageManager;

public class DeviceUtils {

	/**
	 * 获取当前本地apk的版本
	 *
	 * @param mContext
	 * @return
	 */
	public static int getVersionCode(Context mContext) {
		int versionCode = 0;
		try {
			//获取软件版本号，对应AndroidManifest.xml下android:versionCode
			versionCode = mContext.getPackageManager().
					getPackageInfo(mContext.getPackageName(), 0).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}
}
