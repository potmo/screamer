package com.potmo.screamer.sound;

import com.potmo.screamer.R;

public enum Sound {

    A(R.raw.a, "a"),
    B(R.raw.b, "b"),
    C(R.raw.c, "c"),
    D(R.raw.d, "d"),
    E(R.raw.e, "e");

    public final int resourceId;
    public final String letter;

    Sound(int resourceId, String letter) {
        this.resourceId = resourceId;
        this.letter = letter;
    }
}
