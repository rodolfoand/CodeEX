package br.senai.sp.informatica.meusalbuns.control;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import br.senai.sp.informatica.meusalbuns.R;

/**
 * Created by 34023325821 on 05/12/2017.
 */

public class PreferenciasFrag extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias_pref_screen);
    }
}
