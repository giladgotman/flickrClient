package com.gg.flickertask.util;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Gilad on 8/14/2016.
 */
public final class DeviceServices {

    private DeviceServices() {
    }
    public static boolean isNetworkConnected(final Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
