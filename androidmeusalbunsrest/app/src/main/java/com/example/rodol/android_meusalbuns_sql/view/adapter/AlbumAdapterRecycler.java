package com.example.rodol.android_meusalbuns_sql.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rodol.android_meusalbuns_sql.R;
import com.example.rodol.android_meusalbuns_sql.model.Album;
import com.example.rodol.android_meusalbuns_sql.view.holder.AlbumViewHolder;

import java.util.List;

/**
 * Created by rodol on 17/01/2018.
 */

public class AlbumAdapterRecycler extends RecyclerView.Adapter {

    private List<Album> albumList;
    private Context context;

    public AlbumAdapterRecycler(List<Album> albumList, Context context) {
        this.albumList = albumList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lista_recyclerview, parent, false);
        AlbumViewHolder holder = new AlbumViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumViewHolder viewHolder = (AlbumViewHolder) holder;
        Album album = albumList.get(position);
        viewHolder.preencher(album);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
