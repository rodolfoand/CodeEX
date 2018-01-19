package com.example.rodol.android_meusalbuns_sql.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodol.android_meusalbuns_sql.R;
import com.example.rodol.android_meusalbuns_sql.dao.AlbumDao;
import com.example.rodol.android_meusalbuns_sql.model.Album;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by rodol on 18/01/2018.
 */

public class AlbumAdapter extends BaseAdapter {

    private Activity activity;

    private List<Album> albumList;

    private final static DateFormat fmt = DateFormat.getDateInstance(DateFormat.SHORT);

    public AlbumAdapter(Activity activity, List<Album> albumList) {
        this.activity = activity;
        this.albumList = albumList;
    }

    @Override
    public int getCount() {
        return albumList.size();
    }

    @Override
    public Object getItem(int position) {
        return albumList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return albumList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_lista_listview, parent, false);

        TextView artista = view.findViewById(R.id.tvArtistaItem);
        TextView nome = view.findViewById(R.id.tvNomeItem);
        TextView genero = view.findViewById(R.id.tvGeneroItem);
        TextView data = view.findViewById(R.id.tvDataItem);
        ImageView capa = view.findViewById(R.id.ivCapaItem);

        Album album = albumList.get(position);
        artista.setText(album.getArtista());
        nome.setText(album.getNome());
        genero.setText(album.getGenero());
        data.setText(fmt.format(album.getDtLancamento()));

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
