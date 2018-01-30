package com.example.rodol.android_meusalbuns_sql;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by rodol on 18/01/2018.
 */

public class PreferenceFragment extends android.preference.PreferenceFragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.album_preferencias);
    }
}
