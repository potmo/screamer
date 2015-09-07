package com.potmo.screamer.screamerService;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import com.potmo.screamer.sound.Sound;
import com.potmo.screamer.sound.SoundPlayer;

import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ScreamerService extends Service {

  private BlockingQueue<Sound> screamQueue;
  private ScreamConsumer screamConsumer;

  public ScreamerService() {

    Log.d("ScreamerService", "start");
  }

  @Override
  public void onCreate() {
    screamQueue = new LinkedBlockingQueue<>();
    SoundPlayer soundPlayer = new SoundPlayer(getBaseContext());
    screamConsumer = new ScreamConsumer(soundPlayer, screamQueue);

    Log.d("ScreamerService", "create");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.d("ScreamerService", "Screaming");
    if (intent == null) {
      //restarting after killed
      return START_STICKY;
    }

    ScreamExtra extra = (ScreamExtra) intent.getSerializableExtra(ScreamExtra.EXTRAS_NAME);

    writeToFile(extra.phoneNumber, extra.message);
    writeBackSms(extra.phoneNumber);
    //sendSMS(extra.phoneNumber, extra.message);

    for (int i = 0; i < extra.message.length(); i++) {
      String character = "" + extra.message.charAt(i);

      boolean found = false;
      for (Sound sound : Sound.values()) {
        if (sound.letter.equalsIgnoreCase(character)) {
          screamQueue.add(sound);
          found = true;
          break;
        }
      }

      // if we havent got that sound then add a random one
      if (!found) {
        Sound[] values = Sound.values();
        int rand = (int) (Math.random() * (values.length - 1));
        Sound randomSound = values[rand];
        screamQueue.add(randomSound);
      }
    }

    Log.d("ScreamerService", "got the message: " + extra.phoneNumber + " " + extra.message);

    return START_STICKY;
  }

  private void writeBackSms(String phoneNumber) {
    SmsManager sms = SmsManager.getDefault();
    try {
      sms.sendTextMessage(phoneNumber,
                          null,
                          "Din vrede har nu kanaliserats ut i universum",
                          null,
                          null);
      Log.e("ScreamerService", "Sent back sms");
    }
    catch (IllegalArgumentException e) {
      Log.e("ScreamerService", "Failed to sms back", e);
    }
  }

  private synchronized void writeToFile(String phoneNumber, String message) {
    String filename = "logfile";
    FileOutputStream outputStream;

    try {
      outputStream = openFileOutput(filename, Context.MODE_APPEND);
      String logMessage = phoneNumber.toString() + " " + message;
      outputStream.write(logMessage.getBytes(Charset.forName("UTF-8")));
      outputStream.close();
      Log.e("ScreamerService", "Wrote sms log");
    }
    catch (Exception e) {
      Log.e("ScreamerService", "Failed to write file", e);
    }
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
}
