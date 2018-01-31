package com.example.rodol.android_meusalbuns_sql;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodol.android_meusalbuns_sql.dao.AlbumDao;
import com.example.rodol.android_meusalbuns_sql.model.Album;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;

public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    private Album album;

    private AlbumDao dao = new AlbumDao(this);

    private EditText artista;
    private EditText nome;
    private EditText genero;
    private EditText data;
    private ImageView capa;

    private final static DateFormat fmt = DateFormat.getDateInstance(DateFormat.SHORT);
    private Calendar calendar;
    private DateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        artista = findViewById(R.id.tilArtistaEdit);
        nome = findViewById(R.id.tilNomeEdit);
        genero = findViewById(R.id.tilGeneroEdit);
        data = findViewById(R.id.tilDataEdit);
        capa = findViewById(R.id.ivCapaEdit);

        data.setOnClickListener(this);

        calendar = Calendar.getInstance();
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG);

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
                    if (album.getDtLancamento() != null) {
                        data.setText(fmt.format(album.getDtLancamento()));
                        //calendar.setTime(album.getDtLancamento());
                        //data.setText(album.getDtLancamento());
                    }

                    try {
                        capa.setImageBitmap(Util.bitmapFromBase64(album.getCapa()));
                    } catch (Exception e) {
                        if (!artista.getText().toString().isEmpty()) {
                            capa.setImageBitmap(getBitmapLetra(artista.getText().toString()));
                        }
                    }
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
                album.setDtLancamento(calendar.getTime());
                album.setCapa(Util.bitmapToBase64(((BitmapDrawable)capa.getDrawable()).getBitmap()));

                dao.salvar(album);
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        DateDialog dialog = new DateDialog();
        try {
            calendar.setTime(fmt.parse(data.getText().toString()));
            dialog.setCalendar(calendar);
        } catch (ParseException e) {
            Log.d("Erro parse data: ", e.getMessage());
        }
        dialog.setEditText(data);
        dialog.setView(v);
        dialog.show(getFragmentManager(), "Data de Lançamento");
        try {
            Log.d("AlbumTeste:", data.getText().toString());
            calendar.setTime(fmt.parse(data.getText().toString()));
        } catch (ParseException e) {}
    }

    public Bitmap getBitmapLetra(String texto){
        Bitmap bitmap = Util.circularBitmapAndText(
                ContextCompat.getColor(this
                        , R.color.colorPrimary)
                , 200
                , 200
                , texto.substring(0,1).toUpperCase());
        //Log.d("Album", "Width: " + capa.getWidth() + "Height: " + capa.getHeight());
        return bitmap;
    }
}
