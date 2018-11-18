package org.michaelbel.buildapp;

import android.util.Log;

import androidx.annotation.NonNull;

public class log {

    private static final String TAG = "2580";

    public static void e(@NonNull String message) {
        Log.e(TAG, message);
    }
}