package br.senai.sp.informatica.meusalbuns.control;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.lib.Util;

public class Perfil extends AppCompatActivity //{
        implements View.OnClickListener {
    private EditText etNomePerfil;
    private EditText etEmailPerfil;
    private ImageView ivPerfil;

    private File fotoUrl;

    private static final int REQUEST_PERMISSION_EX_STORAGE = 100;
    private static final int INTENT_IMAGE_CHOOSER = 101;

    /*
    private static final int REQUEST_PERMISSION_CAMERA = 0;
    private static final int INTENT_IMAGE_CAPTURE = 1;
    */
    /*
    private static String NOME_PERFIL = "nome";
    private static String EMAIL_PERFIL = "email";
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        etNomePerfil = (EditText)findViewById(R.id.etNomePerfil);
        etEmailPerfil = (EditText)findViewById(R.id.etEmailPerfil);
        ivPerfil = (ImageView)findViewById(R.id.ivPerfil);

        ivPerfil.setOnClickListener(this);

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
        /*
        etNomePerfil.setText(preferences.getString(NOME_PERFIL,""));
        etEmailPerfil.setText(preferences.getString(EMAIL_PERFIL,""));
        */
        etNomePerfil.setText(preferences.getString(this.getResources().getString(R.string.nome_perfil_key),this.getResources().getString(R.string.nome_perfil_default)));
        etEmailPerfil.setText(preferences.getString(this.getResources().getString(R.string.email_perfil_key),this.getResources().getString(R.string.email_perfil_default)));

        String fotoString = preferences.getString(this.getResources().getString(R.string.foto_perfil_key), null);
        if (fotoString != null){
            Bitmap bitmap = Util.bitmapFromBase64(fotoString.getBytes());
            ivPerfil.setImageBitmap(bitmap);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                //Toast.makeText(this, ">>", Toast.LENGTH_LONG).show();
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = preferences.edit();

                /*
                editor.putString(NOME_PERFIL, etNomePerfil.getText().toString());
                editor.putString(EMAIL_PERFIL, etEmailPerfil.getText().toString());
                */
                editor.putString(this.getResources().getString(R.string.nome_perfil_key), etNomePerfil.getText().toString());
                editor.putString(this.getResources().getString(R.string.email_perfil_key), etEmailPerfil.getText().toString());

                Bitmap bitmap = Util.bitmapFromImageView(ivPerfil);
                if (bitmap != null){
                    editor.putString(this.getResources().getString(R.string.foto_perfil_key)
                            ,new String(Util.bitmapToBase64(bitmap)));
                } else {
                    editor.putString(this.getResources().getString(R.string.foto_perfil_key)
                            , null);
                }

                editor.apply();
                setResult(RESULT_OK);
                break;
        }
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        getImagemGaleria();
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
                    Uri perfilURI = data.getData();
                    Bitmap bitmap = Util.setPic(ivPerfil.getWidth(), ivPerfil.getHeight(), perfilURI, this);
                    ivPerfil.setImageBitmap(bitmap);
                    ivPerfil.invalidate();
                } catch (IOException e) {}
            }
        }
    }

    /*
    @Override
    public void onClick(View v) {
        //Toast.makeText(this, ">>",Toast.LENGTH_LONG).show();
        String[] permissoes = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE
                , Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.CAMERA};

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null){
            if ((ContextCompat.checkSelfPermission(getBaseContext()
                        , Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                    || (ContextCompat.checkSelfPermission(getBaseContext()
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                    || (ContextCompat.checkSelfPermission(getBaseContext()
                        , Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED))
            {
                ActivityCompat.requestPermissions(this, permissoes, REQUEST_PERMISSION_CAMERA);
            } else {
                try {
                    fotoUrl = Util.createImageFile();
                    int currentApiVersion = Build.VERSION.SDK_INT;
                    if (currentApiVersion > Build.VERSION_CODES.KITKAT){
                        String pkgName = getApplicationContext().getPackageName();
                        Uri local = FileProvider.getUriForFile(this, pkgName + ".provider", fotoUrl);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, local);
                    } else {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fotoUrl));
                    }

                    startActivityForResult(intent, INTENT_IMAGE_CAPTURE);
                } catch (IOException e){
                    Toast.makeText(this, "Falha ao carregar arquivo.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean autorizado = true;

        for (int resultado : grantResults){
            if (resultado == PackageManager.PERMISSION_DENIED){
                autorizado = false;
            }
        }

        switch (requestCode){
            case REQUEST_PERMISSION_CAMERA:
                if (autorizado){
                    //
                } else {
                    Toast.makeText(this, "É preciso dar permissão à Meus Albuns.",Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == INTENT_IMAGE_CAPTURE){
                Intent intent = new Intent(this, );

            }
        }
    }
    */
}
