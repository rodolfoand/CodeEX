package br.senai.sp.informatica.meusalbuns.view.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseLongArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.control.AdapterInterface;
import br.senai.sp.informatica.meusalbuns.model.Album;
import br.senai.sp.informatica.meusalbuns.model.AlbumDao;
import br.senai.sp.informatica.meusalbuns.view.holder.AlbumViewHolder;
import br.senai.sp.informatica.meusalbuns.view.holder.AlbumViewHolderCard;

/**
 * Created by 34023325821 on 12/01/2018.
 */

public class AlbumAdapterRecyclerCard extends RecyclerView.Adapter<AlbumViewHolderCard> implements AdapterInterface{
//
    private Activity activity;
    private AdapterView.OnItemClickListener listener;
    private SparseLongArray mapa;
    private AlbumDao dao = AlbumDao.manager;
    private boolean editar;

    public AlbumAdapterRecyclerCard(Activity activity, AdapterView.OnItemClickListener listener) {
        this.activity = activity;
        this.listener = listener;
    }

    private void criaMapa(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        String ordem = preferences.getString(activity.getResources().getString(R.string.ordem_key), activity.getResources().getString(R.string.ordem_default));
        mapa = new SparseLongArray();
        List<Album> albuns = dao.getLista(ordem);
        for (int i = 0; i < albuns.size(); i++) {
            mapa.put(i, albuns.get(i).getId());
        }
    }

    @Override
    public AlbumViewHolderCard onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater svc = LayoutInflater.from(parent.getContext());
        View layout = svc.inflate(R.layout.item_lista_rv_card,parent,false);
        return new AlbumViewHolderCard(layout);
    }

    @Override
    public void onBindViewHolder(AlbumViewHolderCard holder, int position) {
        Album album = dao.getAlbum(mapa.get(position));
        holder.setView(album);

    }

    @Override
    public int getItemCount() {
        return mapa.size();
    }

    @Override
    public void setEditar(boolean value) {
        editar = value;
        notificaAtualizacao();
    }

    @Override
    public void notificaAtualizacao() {
        criaMapa();
        notifyDataSetChanged();
    }
}
