package br.senai.sp.informatica.meusalbuns.control;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.model.Album;
import br.senai.sp.informatica.meusalbuns.model.AlbumDao;

/**
 * Created by 34023325821 on 16/11/2017.
 */

public class DetalheAlbum extends AppCompatActivity implements View.OnClickListener{

    private AlbumDao dao = AlbumDao.manager;
    private EditText etArtista;
    private EditText etAlbum;
    private EditText etGenero;
    private EditText etDtLancamento;
    private ImageView ivCapa;
    //TODO: Ajustar apresentacao de capa
    private static DateFormat fmt = DateFormat.getDateInstance(DateFormat.LONG);

    private Button btLancamento;

    private Album album;

    private Calendar dataLancamento;

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
        etDtLancamento = (EditText)findViewById(R.id.etDtLancamento);
        ivCapa = (ImageView)findViewById(R.id.ivCapa);

        btLancamento = (Button)findViewById(R.id.btLancamento);
        btLancamento.setOnClickListener(this);

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
                    etDtLancamento.setText(fmt.format(album.getDtLancamento()));
                    //TODO: Ajustar apresentação de capa
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
                //album.setDtLancamento(etDtLancamento.getText());


                try {
                    dataLancamento = Calendar.getInstance();
                    dataLancamento.setTime(fmt.parse(etDtLancamento.getText().toString()));
                    album.setDtLancamento(dataLancamento.getTime());
                } catch (java.text.ParseException ex) {}

                //album.setDtLancamento(new Date(fmt.format(etDtLancamento.getText().toString())));
                //TODO: Ajustar imagem;
                dao.salvar(album);
                setResult(Activity.RESULT_OK);
                break;
        }
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        DateDialog dialog = new DateDialog();
        try {
            dataLancamento = Calendar.getInstance();
            dataLancamento.setTime(fmt.parse(etDtLancamento.getText().toString()));
            dialog.setCalendar(dataLancamento);
        } catch (java.text.ParseException ex) {}
        dialog.setEditText(etDtLancamento);
        dialog.setView(v);
        dialog.show(getFragmentManager(), "Data de lançamento");
    }
}
