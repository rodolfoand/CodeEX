package br.senai.sp.informatica.listacontato.view;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.senai.sp.informatica.listacontato.R;
import br.senai.sp.informatica.listacontato.model.Contato;
import br.senai.sp.informatica.listacontato.model.ContatoDao;

/**
 * Created by WEB on 07/11/2017.
 */

public class DetalheContatoActivity extends AppCompatActivity {
    private ContatoDao dao = ContatoDao.manager;
    private EditText etNome;
    private EditText etEmail;
    private EditText etDataNascimento;
    private Contato cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detalhe_contato);

        etNome = (EditText)findViewById(R.id.etNome);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etDataNascimento = (EditText)findViewById(R.id.etDataNascimento);

        Intent intent = getIntent();
        if (intent != null){
            Bundle dados = intent.getExtras();
            if (dados != null){
                long id = dados.getLong("id");
                cont = dao.getContato(id);
                if (cont != null){
                    etNome.setText(cont.getNome());
                    etEmail.setText(cont.getEmail());
                    String novaData = new SimpleDateFormat("dd/MM/yy").format(cont.getDtNascimento());
                    etDataNascimento.setText(novaData);
                }
            }
        }



        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalhe_contato, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                setResult(Activity.RESULT_CANCELED);
                break;
            case R.id.mi_salvar:
                if (cont == null){
                    cont = new Contato();
                }
                cont.setNome(etNome.getText().toString());
                cont.setEmail(etEmail.getText().toString());
                SimpleDateFormat novaData = new SimpleDateFormat("dd/MM/yy");
                try {
                    cont.setDtNascimento(novaData.parse(etDataNascimento.getText().toString()));
                } catch (ParseException e){
                    //
                }
                //cont.setDtNascimento(new Date(1985, 9, 26));
                dao.salvar(cont);

                setResult(Activity.RESULT_OK);
                break;
        }
        finish();
        return true;
    }
}
