package br.senai.sp.informatica.meusalbuns.control;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.lib.Util;
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

    private static final int REQUEST_PERMISSION_EX_STORAGE = 0;
    private static final int INTENT_IMAGE_CHOOSER = 1;

    private boolean capaAlterada = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_album);

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
        ivCapa.setOnClickListener(this);

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
                    //etDtLancamento.setText(fmt.format(album.getDtLancamento()));

                    Date data = album.getDtLancamento();
                    if(data != null) {
                        etDtLancamento.setText(fmt.format(data));
                    }

                    //etDtLancamento.setText(fmt.format(album.getDtLancamento()));



                    try {
                        ivCapa.setImageBitmap(Util.bitmapFromBase64(album.getCapa()));
                    } catch (Exception e) {
                        String nomeAlbum = etAlbum.getText().toString();
                        Bitmap bitmap;
                        if (!nomeAlbum.isEmpty()) {
                            bitmap = getBitmapLetra(nomeAlbum);
                        }else {
                            bitmap = getBitmapLetra("A");
                        }
                        ivCapa.setImageBitmap(bitmap);
                    }

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
                //album.setDtLancamento(new Date(fmt.format(etDtLancamento.getText().toString())));

                try {
                    dataLancamento = Calendar.getInstance();
                    dataLancamento.setTime(fmt.parse(etDtLancamento.getText().toString()));
                    album.setDtLancamento(dataLancamento.getTime());
                } catch (java.text.ParseException ex) {}


                //TODO: Ajustar imagem;
                if (capaAlterada == true) {
                    album.setCapa(Util.bitmapToBase64(((BitmapDrawable)ivCapa.getDrawable()).getBitmap()));
                    capaAlterada = false;
                }
                dao.salvar(album);
                //Intent intent = new Intent(this, DetalheAlbum.class);
                //intent.putExtra("id", album.getId());
                setResult(Activity.RESULT_OK);
                break;
        }
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        //Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
        //Log.d("OnClick","" +id);
        switch (id) {
            case R.id.btLancamento:
                DateDialog dialog = new DateDialog();
                try {
                    dataLancamento = Calendar.getInstance();
                    dataLancamento.setTime(fmt.parse(etDtLancamento.getText().toString()));
                    dialog.setCalendar(dataLancamento);
                } catch (java.text.ParseException ex) {
                }
                dialog.setEditText(etDtLancamento);
                dialog.setView(v);
                dialog.show(getFragmentManager(), "Data de lançamento");
                break;
            case R.id.ivCapa:
                /*Bitmap bitmap = getBitmapLetra(etAlbum.getText().toString());
                ivCapa.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
                ivCapa.setImageBitmap(bitmap);
                Toast.makeText(this,""+ivCapa.getHeight(), Toast.LENGTH_LONG).show();
                */
                getImagemGaleria();
                break;
        }
    }

    public Bitmap getBitmapLetra(String texto){
        Bitmap bitmap = Util.circularBitmapAndText(
                ContextCompat.getColor(this
                        , R.color.colorPrimary)
                , 200
                , 600
                , texto.substring(0,1).toUpperCase());
        return bitmap;
    }

    public void getImagemGaleria(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        if (intent.resolveActivity(getPackageManager()) != null){
            if ((ContextCompat.checkSelfPermission(getBaseContext()
                  , android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){
                ActivityCompat.requestPermissions(this
                        , new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}
                        , REQUEST_PERMISSION_EX_STORAGE);
            } else {
                startActivityForResult(Intent.createChooser(intent, "Selecione a imagem para capa")
                        , INTENT_IMAGE_CHOOSER);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INTENT_IMAGE_CHOOSER){
            if (data != null){
                try {
                    Uri capaURI = data.getData();
                    Bitmap bitmap = Util.setPic(ivCapa.getWidth(), ivCapa.getHeight(), capaURI, this);
                    ivCapa.setImageBitmap(bitmap);
                    ivCapa.invalidate();
                    capaAlterada = true;
                } catch (IOException e){}
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
