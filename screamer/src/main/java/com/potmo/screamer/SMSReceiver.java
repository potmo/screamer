package com.potmo.screamer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import com.potmo.screamer.screamerService.ScreamExtra;
import com.potmo.screamer.screamerService.ScreamerService;

public class SMSReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: " + phoneNumber + "; message: " + message);

                    // start a service for it
                    ScreamExtra screamExtra = new ScreamExtra(phoneNumber, message);
                    Intent screamIntent = new Intent(context, ScreamerService.class);
                    screamIntent.putExtra(ScreamExtra.EXTRAS_NAME, screamExtra);
                    context.startService(screamIntent);


                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);

        }
    }
}
