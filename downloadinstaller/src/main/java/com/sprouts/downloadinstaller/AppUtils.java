package com.sprouts.downloadinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


/**
 * The type App utils.
 */
public class AppUtils {


    /**
     * Instantiates a new App utils.
     */
    public  AppUtils(){
        super();
    }

    /**
     * 获取应用程序名称  ${APP_NAME}
     *
     * @param context Context
     * @return 当前应用的名称 app name
     */
    public static synchronized String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            return "AppUpdate";
        }
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context Context
     * @return 当前应用的版本名称 version code
     */
    public static synchronized int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * [获取应用程序版本名称信息]
     *
     * @param context context
     * @return 当前应用的版本名称 package name
     */
    public static synchronized String getPackageName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}