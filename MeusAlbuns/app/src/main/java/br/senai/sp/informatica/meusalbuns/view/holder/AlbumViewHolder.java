package br.senai.sp.informatica.meusalbuns.view.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.model.Album;
import br.senai.sp.informatica.meusalbuns.view.adapter.AlbumAdapterRecycler;

/**
 * Created by 34023325821 on 09/01/2018.
 */

public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    private TextView tvArtista;
    private TextView tvAlbum;
    private TextView tvGenero;
    private TextView tvLancamento;
    private Long albumId;
    private AlbumAdapterRecycler adapterRecycler;

    public AlbumViewHolder(View itemView, AlbumAdapterRecycler adapterHolder) {
        super(itemView);
        this.adapterRecycler = adapterHolder;

        tvArtista = itemView.findViewById(R.id.tvArtistaRv);
        tvAlbum = itemView.findViewById(R.id.tvAlbumRv);
        tvGenero = itemView.findViewById(R.id.tvGeneroRv);
        tvLancamento = itemView.findViewById(R.id.tvLancamentoRv);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void preencher(Album album){
        albumId = album.getId();
        tvArtista.setText(album.getArtista());
        tvAlbum.setText(album.getAlbum());
        tvGenero.setText(album.getGenero());
        //TODO: Preencher Data de Lancamento
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), albumId.toString(),Toast.LENGTH_SHORT).show();
        Log.d("ID", albumId.toString());
    }

    @Override
    public boolean onLongClick(View v) {
        PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
        popupMenu.getMenuInflater().inflate(R.menu.options_lista_rv, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mi_lista_rv_editar:
                        Log.d("Editar", albumId.toString());
                        break;
                    case R.id.mi_lista_rv_deletar:
                        Log.d("Deletar", albumId.toString());
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
        return false;
    }
}
