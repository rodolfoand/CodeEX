package br.senai.sp.informatica.meusalbuns.control;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.model.Album;
import br.senai.sp.informatica.meusalbuns.model.AlbumDao;
import br.senai.sp.informatica.meusalbuns.view.adapter.AlbumAdapter;
import br.senai.sp.informatica.meusalbuns.view.adapter.AlbumAdapterRecycler;

public class NavDrawAlbum extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        , AdapterView.OnItemClickListener
        , DialogInterface.OnClickListener
        , AdapterView.OnItemLongClickListener{

    private ListView listaAlbuns;
    private AlbumAdapter albumAdapter;

    private MenuItem miEditar;
    private MenuItem miApagar;

    private final int EDITA_ALBUM = 0;
    private final int ADICIONA_ALBUM = 0;

    private AlbumDao dao = AlbumDao.manager;
    private List<Long> listaSelecionados;

    private boolean edicao = false;

    private TextView etNomeNav;
    private TextView etEmailNav;

    private String tipoLista;

    private RecyclerView rvListaAlbuns;

    /*
    private static String NOME_PERFIL = "nome";
    private static String EMAIL_PERFIL = "email";
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nav_draw_album);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAdicionar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        etNomeNav = (TextView)header.findViewById(R.id.etNomeNav);
        etEmailNav = (TextView)header.findViewById(R.id.etEmailNav);



        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        tipoLista = preferences.getString(this.getResources().getString(R.string.lista_key)
                , this.getResources().getString(R.string.lista_default));
        if (tipoLista.equals("ListView")){
            listaAlbuns = (ListView)findViewById(R.id.listAlbuns);

            albumAdapter = new AlbumAdapter(this);
            listaAlbuns.setAdapter(albumAdapter);
            listaAlbuns.setOnItemClickListener(this);
            listaAlbuns.setOnItemLongClickListener(this);
        }
        if (tipoLista.equals("RecyclerView")){
            rvListaAlbuns = (RecyclerView)findViewById(R.id.rvListaAlbuns);

            List<Album> albumList = dao.getLista("Album");

            rvListaAlbuns.setAdapter(new AlbumAdapterRecycler(albumList, this));

            //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            //rvAlbuns.setLayoutManager(layoutManager);
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            rvListaAlbuns.setLayoutManager(staggeredGridLayoutManager);
        }
        Log.d("Lista",tipoLista);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        /*
        etNomeNav.setText(preferences.getString(NOME_PERFIL, ""));
        etEmailNav.setText(preferences.getString(EMAIL_PERFIL,""));
         */
        etNomeNav.setText(preferences.getString(this.getResources().getString(R.string.nome_perfil_key), this.getResources().getString(R.string.nome_perfil_default)));
        etEmailNav.setText(preferences.getString(this.getResources().getString(R.string.email_perfil_key),this.getResources().getString(R.string.email_perfil_default)));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_draw_album, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_preferencias) {
            Intent intent = new Intent(this, PreferenciasActivity.class);
            startActivityForResult(intent, 25);
        } else if (id == R.id.nav_perfil) {
            Intent intent = new Intent(this, Perfil.class);
            startActivity(intent);

        } else if (id == R.id.nav_recyclerview) {
            Intent intent = new Intent(this, ListaAlbunsRecycler.class);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!edicao) {
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
            //listaSelecionados = albumAdapter.getListaSelecionados();
            //Toast.makeText(this, ">> "+listaSelecionados, Toast.LENGTH_LONG).show();
            dao.retiraSelecionados(listaSelecionados);
        }
        albumAdapter.limpaListaSelecionada();
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
        albumAdapter.setLayout(AlbumAdapter.TipoLista.EDITAR);
        //albumAdapter.setItemSelecionado(position);
        miEditar.setVisible(false);
        miApagar.setVisible(true);
        edicao = true;
        return false;
    }
}
