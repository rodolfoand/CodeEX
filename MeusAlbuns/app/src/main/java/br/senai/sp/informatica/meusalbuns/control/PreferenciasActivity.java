package br.senai.sp.informatica.meusalbuns.control;

import android.app.ActionBar;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import br.senai.sp.informatica.meusalbuns.R;

/**
 * Created by 34023325821 on 05/12/2017.
 */

public class PreferenciasActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.preferencias_frag);

        ActionBar bar = getActionBar();
        if (bar != null){
            bar.setHomeButtonEnabled(true);
            bar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home){
            setResult(RESULT_OK);
        }
        finish();
        return true;
    }
}
