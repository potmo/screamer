package com.potmo.screamer.sound;

import com.potmo.screamer.R;

public enum Sound {

  A(R.raw.sound001, 2332, "a"),
  B(R.raw.sound002, 3181, "b"),
  C(R.raw.sound003, 2742, "c"),
  D(R.raw.sound004, 3735, "d"),
  E(R.raw.sound005, 2998, "e"),
  F(R.raw.sound006, 3142, "f"),
  G(R.raw.sound007, 2839, "g"),
  H(R.raw.sound008, 2790, "h"),
  I(R.raw.sound009, 2805, "i"),
  J(R.raw.sound010, 2998, "j"),
  K(R.raw.sound011, 2434, "k"),
  L(R.raw.sound012, 3060, "l"),
  M(R.raw.sound013, 2757, "m"),
  N(R.raw.sound014, 3152, "n"),
  O(R.raw.sound015, 3287, "o"),
  P(R.raw.sound016, 3451, "p"),
  Q(R.raw.sound017, 2516, "q"),
  RR(R.raw.sound018, 2511, "r"),
  S(R.raw.sound019, 1855, "s"),
  T(R.raw.sound020, 2212, "t"),
  U(R.raw.sound021, 2882, "u"),
  V(R.raw.sound022, 323, "v"),
  W(R.raw.sound023, 728, "w"),
  X(R.raw.sound024, 641, "x"),
  Y(R.raw.sound025, 742, "y"),
  Z(R.raw.sound026, 670, "z"),
  Å(R.raw.sound027, 892, "å"),
  Ä(R.raw.sound028, 882, "ä"),
  Ö(R.raw.sound029, 2410, "ö"),
  OO(R.raw.sound030, 1614, "1");

  public final int resourceId;
  public final int lengthMillis;
  public final String letter;

  Sound(int resourceId, int lengthMillis, String letter) {
    this.resourceId = resourceId;
    this.lengthMillis = lengthMillis;
    this.letter = letter;
  }
}
