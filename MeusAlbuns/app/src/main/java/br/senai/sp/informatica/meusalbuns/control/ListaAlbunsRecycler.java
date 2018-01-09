package br.senai.sp.informatica.meusalbuns.control;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.model.Album;
import br.senai.sp.informatica.meusalbuns.model.AlbumDao;
import br.senai.sp.informatica.meusalbuns.view.adapter.AlbumAdapterRecycler;

public class ListaAlbunsRecycler extends AppCompatActivity {

    private AlbumDao dao = AlbumDao.manager;
    private RecyclerView rvAlbuns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_albuns_rv);

        rvAlbuns = (RecyclerView)findViewById(R.id.rvAlbuns);

        List<Album> albumList = dao.getLista("Album");

        rvAlbuns.setAdapter(new AlbumAdapterRecycler(albumList, this));

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //rvAlbuns.setLayoutManager(layoutManager);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvAlbuns.setLayoutManager(staggeredGridLayoutManager);

        android.app.ActionBar actionBar = getActionBar();
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
}
