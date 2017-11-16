package br.senai.sp.informatica.meusalbuns.control;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.model.Album;
import br.senai.sp.informatica.meusalbuns.model.AlbumDao;

/**
 * Created by 34023325821 on 16/11/2017.
 */

public class DetalheAlbum extends AppCompatActivity {

    private AlbumDao dao = AlbumDao.manager;
    private EditText etArtista;
    private EditText etAlbum;
    private EditText etGenero;
    private EditText etDtLancamento;
    //TODO: Ajustar apresentacao de capa
    private Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detalhe_album);

        ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        etArtista = (EditText)findViewById(R.id.etArtista);
        etAlbum = (EditText)findViewById(R.id.etAlbum);
        etGenero = (EditText)findViewById(R.id.etGenero);
        etDtLancamento = (EditText)findViewById(R.id.tvDtLancamento);

        Intent intent = getIntent();
        if (intent != null){
            Bundle dados = intent.getExtras();
            if (dados != null){
                long id = dados.getLong("id");
                album = dao.getAlbum(id);
                if (album != null){
                    etArtista.setText(album.getArtista());
                    etAlbum.setText(album.getAlbum());
                    etGenero.setText(album.getGenero());
                    //etDtLancamento.setText(album.getDtLancamento());
                    //TODO: Ajustar apresentação de data e capa
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalhes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.mi_det_salvar:
                if (album == null){
                    album = new Album();
                }
                album.setArtista(etArtista.getText().toString());
                album.setAlbum(etAlbum.getText().toString());
                album.setGenero(etGenero.getText().toString());
                //TODO: Ajustar data e imagem;
                dao.salvar(album);
                setResult(Activity.RESULT_OK);
                break;
        }
        finish();
        return true;
    }
}
