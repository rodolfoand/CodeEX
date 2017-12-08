package br.senai.sp.informatica.meusalbuns.control;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sp.informatica.meusalbuns.R;

public class Perfil extends AppCompatActivity {
    private EditText etNomePerfil;
    private EditText etEmailPerfil;

    private static String NOME_PERFIL = "nome";
    private static String EMAIL_PERFIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        etNomePerfil = (EditText)findViewById(R.id.etNomePerfil);
        etEmailPerfil = (EditText)findViewById(R.id.etEmailPerfil);

        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        etNomePerfil.setText(preferences.getString(NOME_PERFIL,""));
        etEmailPerfil.setText(preferences.getString(EMAIL_PERFIL,""));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                //Toast.makeText(this, ">>", Toast.LENGTH_LONG).show();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString(NOME_PERFIL, etNomePerfil.getText().toString());
                editor.putString(EMAIL_PERFIL, etEmailPerfil.getText().toString());

                editor.apply();
                setResult(RESULT_OK);
                break;
        }
        finish();
        return true;
    }
}
