package com.example.rodol.android_meusalbuns_sql.view.holder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodol.android_meusalbuns_sql.EditActivity;
import com.example.rodol.android_meusalbuns_sql.R;
import com.example.rodol.android_meusalbuns_sql.dao.AlbumDao;
import com.example.rodol.android_meusalbuns_sql.model.Album;
import com.example.rodol.android_meusalbuns_sql.view.adapter.AlbumAdapterRecycler;

import java.text.DateFormat;

/**
 * Created by rodol on 17/01/2018.
 */

public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView artista;
    private TextView nome;
    private TextView genero;
    private TextView data;
    private ImageView capa;

    private Long albumId;
    private AlbumAdapterRecycler adapter;

    private final int EDITA_ALBUM = 0;

    private final static DateFormat fmt = DateFormat.getDateInstance(DateFormat.LONG);

    public AlbumViewHolder(View itemView, AlbumAdapterRecycler adapterRecycler) {
        super(itemView);
        adapter = adapterRecycler;

        artista = itemView.findViewById(R.id.tvArtistaItem);
        nome = itemView.findViewById(R.id.tvNomeItem);
        genero = itemView.findViewById(R.id.tvGeneroItem);
        data = itemView.findViewById(R.id.tvDataItem);
        capa = itemView.findViewById(R.id.ivCapaItem);

        itemView.setOnClickListener(this);
    }

    public void preencher(Album album){
        albumId = album.getId();
        artista.setText(album.getArtista());
        nome.setText(album.getNome());
        genero.setText(album.getGenero());
        data.setText(fmt.format(album.getDtLancamento()));
        //TODO: Data e capa


    }

    @Override
    public void onClick(View v) {
        Activity activity = (Activity) v.getContext();
        Intent intent = new Intent(activity, EditActivity.class);
        intent.putExtra("id", albumId);
        activity.startActivityForResult(intent, EDITA_ALBUM);
    }
}
