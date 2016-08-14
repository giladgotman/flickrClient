package com.gg.flickertask.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Gilad on 8/14/2016.
 */
public class DeviceServices {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
