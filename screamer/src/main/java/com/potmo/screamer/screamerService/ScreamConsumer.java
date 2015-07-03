package com.potmo.screamer.screamerService;

import android.util.Log;
import com.potmo.screamer.sound.AllSoundsLoadedCallback;
import com.potmo.screamer.sound.Sound;
import com.potmo.screamer.sound.SoundPlayer;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class ScreamConsumer implements Runnable, AllSoundsLoadedCallback {

    private final SoundPlayer soundPlayer;
    private final BlockingQueue<Sound> queue;
    private final Thread consumerThread;

    public ScreamConsumer(SoundPlayer soundPlayer, BlockingQueue<Sound> queue) {
        this.soundPlayer = soundPlayer;
        this.queue = queue;
        consumerThread = new Thread(this, "ScreamConsumer");

        soundPlayer.load(Arrays.asList(Sound.values()), this);
    }

    @Override
    public void run() {

        try {
            while (true) {
                Sound scream = queue.take();
                soundPlayer.play(scream);
                Thread.sleep(1000); // TODO: Match this to how long the samle is
                Log.d("ScreamConsumer", "screaming");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAllSoundsLoaded() {
        Log.d("ScreamConsumer", "All sounds is loaded so start the consuming");
        consumerThread.start();
    }
}
