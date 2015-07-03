package com.potmo.screamer.screamerService;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.potmo.screamer.sound.Sound;
import com.potmo.screamer.sound.SoundPlayer;

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

        for (int i = 0; i < extra.message.length(); i++) {
            String character = "" + extra.message.charAt(i);
            for (Sound sound : Sound.values()) {
                if (sound.letter.equalsIgnoreCase(character)) {
                    screamQueue.add(sound);
                }
            }
        }

        Log.d("ScreamerService", "got the message: " + extra.phoneNumber + " " + extra.message);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
