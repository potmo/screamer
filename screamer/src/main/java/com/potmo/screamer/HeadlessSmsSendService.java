package com.potmo.screamer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by nissebergman on 15-06-21.
 */
public class HeadlessSmsSendService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
