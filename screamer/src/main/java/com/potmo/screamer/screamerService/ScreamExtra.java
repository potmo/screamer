package com.potmo.screamer.screamerService;

import java.io.Serializable;

public class ScreamExtra implements Serializable {

    public static String INTENT_NAME = "Scream";
    public static String EXTRAS_NAME = "ScreamExtra";

    public final String phoneNumber;
    public final String message;

    public ScreamExtra(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }


}
