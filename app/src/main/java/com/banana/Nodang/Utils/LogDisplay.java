package com.banana.Nodang.Utils;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class LogDisplay {
    public static final String TAG_ESKIM = "eskim";

    public static void setLog(final String strMsg) {
        Log.d(TAG_ESKIM, strMsg);
    }

    public static void setToast(final Activity activity, final String strMsg) {
        try {
            if (activity == null)
                return;
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity.getApplicationContext(), strMsg, Toast.LENGTH_SHORT).show();
                    Log.d(TAG_ESKIM, strMsg);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
