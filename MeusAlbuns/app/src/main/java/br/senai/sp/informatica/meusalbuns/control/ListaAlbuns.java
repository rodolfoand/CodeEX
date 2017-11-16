package br.senai.sp.informatica.meusalbuns.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.senai.sp.informatica.meusalbuns.R;

public class ListaAlbuns extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listaAlbuns;
    private AlbumAdapter albumAdapter;

    private MenuItem miEditar;
    private MenuItem miApagar;

    private final int EDITA_ALBUM = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista_albuns);

        listaAlbuns = (ListView)findViewById(R.id.listAlbuns);

        albumAdapter = new AlbumAdapter();
        listaAlbuns.setAdapter(albumAdapter);
        listaAlbuns.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent tela = new Intent(getBaseContext(), DetalheAlbum.class);
        tela.putExtra("id", id);
        startActivityForResult(tela, EDITA_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            albumAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista, menu);

        miEditar = (MenuItem)menu.findItem(R.id.mi_lis_editar);
        miApagar = (MenuItem)menu.findItem(R.id.mi_lis_apagar);
        miApagar.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mi_lis_editar:
                albumAdapter.setLayout(AlbumAdapter.TipoLista.EDITAR);
                miEditar.setVisible(false);
                miApagar.setVisible(true);
                break;
            case R.id.mi_lis_apagar:
                albumAdapter.setLayout(AlbumAdapter.TipoLista.APAGAR);
                miEditar.setVisible(true);
                miApagar.setVisible(false);
                break;
        }

        return true;
    }
}
