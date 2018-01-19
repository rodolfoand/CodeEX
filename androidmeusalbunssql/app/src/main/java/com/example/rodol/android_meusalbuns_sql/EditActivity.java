package com.example.rodol.android_meusalbuns_sql;

import android.app.ActionBar;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodol.android_meusalbuns_sql.dao.AlbumDao;
import com.example.rodol.android_meusalbuns_sql.model.Album;

public class EditActivity extends AppCompatActivity {

    private Album album;

    private AlbumDao dao = new AlbumDao(this);

    private EditText artista;
    private EditText nome;
    private EditText genero;
    private EditText data;
    private ImageView capa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        artista = findViewById(R.id.tilArtistaEdit);
        nome = findViewById(R.id.tilNomeEdit);
        genero = findViewById(R.id.tilGeneroEdit);
        data = findViewById(R.id.tilDataEdit);
        capa = findViewById(R.id.ivCapaEdit);

        Intent intent = getIntent();
        if (intent != null){
            Bundle dados = intent.getExtras();
            if (dados != null){
                long id = dados.getLong("id");
                album = dao.getAlbum(id);
                if (album != null){
                    artista.setText(album.getArtista());
                    nome.setText(album.getNome());
                    genero.setText(album.getGenero());
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
        getMenuInflater().inflate(R.menu.activity_edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.miSalvarEdit:
                if (album == null) {
                    album = new Album();
                }
                album.setAtivo(true);
                album.setArtista(artista.getText().toString());
                album.setNome(nome.getText().toString());
                album.setGenero(genero.getText().toString());
                dao.salvar(album);
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }
}
