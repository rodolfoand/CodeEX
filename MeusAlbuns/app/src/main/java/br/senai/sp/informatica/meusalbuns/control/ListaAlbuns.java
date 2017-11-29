package br.senai.sp.informatica.meusalbuns.control;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.model.AlbumDao;

public class ListaAlbuns extends AppCompatActivity implements AdapterView.OnItemClickListener
        ,DialogInterface.OnClickListener
        ,AdapterView.OnItemLongClickListener{

    private ListView listaAlbuns;
    private AlbumAdapter albumAdapter;

    private MenuItem miEditar;
    private MenuItem miApagar;

    private final int EDITA_ALBUM = 0;
    private final int ADICIONA_ALBUM = 0;

    private AlbumDao dao = AlbumDao.manager;
    private List<Long> listaSelecionados;

    private boolean edicao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista_albuns);

        listaAlbuns = (ListView)findViewById(R.id.listAlbuns);

        albumAdapter = new AlbumAdapter();
        listaAlbuns.setAdapter(albumAdapter);
        listaAlbuns.setOnItemClickListener(this);
        listaAlbuns.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (edicao == true) {
            Intent tela = new Intent(getBaseContext(), DetalheAlbum.class);
            tela.putExtra("id", id);
            startActivityForResult(tela, EDITA_ALBUM);
        } else {
            albumAdapter.setItemSelecionado(position);
        }
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
                edicao = true;

                break;
            case R.id.mi_lis_apagar:
                listaSelecionados = albumAdapter.getListaSelecionados();
                if (listaSelecionados.size() > 0){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                    alerta.setMessage("Deseja apagar os albuns selecionados?");
                    alerta.setNegativeButton("Não", this);
                    alerta.setPositiveButton("Sim", this);
                    alerta.create();
                    alerta.show();
                } else {
                    albumAdapter.setLayout(AlbumAdapter.TipoLista.APAGAR);
                    miEditar.setVisible(true);
                    miApagar.setVisible(false);
                }
                edicao = false;
                break;
        }

        return true;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == -1) {
            listaSelecionados = albumAdapter.getListaSelecionados();
            dao.retiraSelecionados(listaSelecionados);
        }
        albumAdapter.setLayout(AlbumAdapter.TipoLista.APAGAR);
        miEditar.setVisible(true);
        miApagar.setVisible(false);
        //Log.d("Teste Dialog","Valor recebido: " + which);
    }

    public void onClickAdicionar(View v){
        Intent tela = new Intent(getBaseContext(), DetalheAlbum.class);
        startActivityForResult(tela, ADICIONA_ALBUM);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        albumAdapter.setLayout(AlbumAdapter.TipoLista.EDITAR, position);
        miEditar.setVisible(false);
        miApagar.setVisible(true);
        return false;
    }
}
