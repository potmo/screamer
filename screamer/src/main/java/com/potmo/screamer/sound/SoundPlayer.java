package com.potmo.screamer.sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.SoundPool;
import android.util.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoundPlayer {
    private final SoundPool soundPool;
    private final AudioManager audioManager;
    private final Context context;
    private final Map<Sound, Integer> soundSampleMap = new HashMap<>();
    private final Map<Integer, Boolean> soundLoadedMap = new HashMap<>();

    public SoundPlayer(Context context) {
        this.context = context;
        this.soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);


        MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void load(List<Sound> sounds, final AllSoundsLoadedCallback allLoadedCallback) {

        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                Log.d("SOUND", "done loading sampleID is " + sampleId);
                soundLoadedMap.put(sampleId, true);

                // break if everything is not donee
                for (Boolean loaded : soundLoadedMap.values()) {
                    if (!loaded) return;
                }

                allLoadedCallback.onAllSoundsLoaded();

            }
        });

        for (Sound sound : sounds) {
            int sampleId = soundPool.load(context, sound.resourceId, 1);
            soundSampleMap.put(sound, sampleId);
            soundLoadedMap.put(sampleId, false);
            Log.d("SOUND", "loading" + sound.resourceId + " sound id is " + sampleId);
        }
    }

    public void play(Sound sound) {

        if (!soundSampleMap.containsKey(sound)) {
            throw new RuntimeException("Sound is not loaded");
        }

        Integer sampleId = soundSampleMap.get(sound);


        if (!soundLoadedMap.get(sampleId)) {
            throw new RuntimeException("Sound is not done loading");
        }

        int streamId = soundPool.play(sampleId, 1.0f, 1.0f, 1, 0, 1.0f);

        Log.d("SOUND", "playing" + sound.resourceId + " sound id is " + sampleId + " stream assigned is " + streamId);

    }

}
