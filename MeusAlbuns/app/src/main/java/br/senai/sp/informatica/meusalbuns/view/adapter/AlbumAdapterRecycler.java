package br.senai.sp.informatica.meusalbuns.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.control.AdapterInterface;
import br.senai.sp.informatica.meusalbuns.model.Album;
import br.senai.sp.informatica.meusalbuns.view.holder.AlbumViewHolder;

/**
 * Created by 34023325821 on 09/01/2018.
 */

public class AlbumAdapterRecycler extends RecyclerView.Adapter{

    private List<Album> albumList;
    private Context context;

    public AlbumAdapterRecycler(List<Album> albumList, Context context) {
        this.albumList = albumList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lista_rv, parent, false);
        AlbumViewHolder holder = new AlbumViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumViewHolder viewHolder = (AlbumViewHolder)holder;
        Album album = albumList.get(position);
        ((AlbumViewHolder) holder).preencher(album);

    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public void removerItem(int position){
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}
