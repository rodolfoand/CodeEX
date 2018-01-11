package br.senai.sp.informatica.meusalbuns.view.holder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.control.DetalheAlbum;
import br.senai.sp.informatica.meusalbuns.lib.Util;
import br.senai.sp.informatica.meusalbuns.model.Album;
import br.senai.sp.informatica.meusalbuns.model.AlbumDao;
import br.senai.sp.informatica.meusalbuns.view.adapter.AlbumAdapterRecycler;

/**
 * Created by 34023325821 on 09/01/2018.
 */

public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    private TextView tvArtista;
    private TextView tvAlbum;
    private TextView tvGenero;
    private TextView tvLancamento;
    private ImageView ivCapaRv;
    private Long albumId;
    private AlbumAdapterRecycler adapterRecycler;
    private AlbumDao dao = AlbumDao.manager;

    private final int EDITA_ALBUM = 0;

    public AlbumViewHolder(View itemView, AlbumAdapterRecycler adapterHolder) {
        super(itemView);
        this.adapterRecycler = adapterHolder;

        tvArtista = itemView.findViewById(R.id.tvArtistaRv);
        tvAlbum = itemView.findViewById(R.id.tvAlbumRv);
        tvGenero = itemView.findViewById(R.id.tvGeneroRv);
        tvLancamento = itemView.findViewById(R.id.tvLancamentoRv);
        ivCapaRv = itemView.findViewById(R.id.ivCapaRv);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void preencher(Album album){
        albumId = album.getId();
        tvArtista.setText(album.getArtista());
        tvAlbum.setText(album.getAlbum());
        tvGenero.setText(album.getGenero());

        Date data = album.getDtLancamento();
        if(data != null) {
            tvLancamento.setText(fmt.format(data));
        }

        try {
            ivCapaRv.setImageBitmap(Util.bitmapFromBase64(album.getCapa()));
        } catch (Exception e) {
            String nomeAlbum = tvAlbum.getText().toString();
            if (!nomeAlbum.isEmpty()) {
                Bitmap bitmap = Util.circularBitmapAndText(
                        ContextCompat.getColor(itemView.getContext(), R.color.colorPrimary)
                        , 200
                        , 600
                        , nomeAlbum.substring(0, 1).toUpperCase());
                ivCapaRv.setImageBitmap(bitmap);
            }
        }
    }


    @Override
    public void onClick(View v) {
        //Toast.makeText(v.getContext(), albumId.toString(),Toast.LENGTH_SHORT).show();
        //Log.d("ID", albumId.toString());

        apresentaItem();
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
                        //Log.d("Editar", albumId.toString());
                        apresentaItem();
                        break;
                    case R.id.mi_lista_rv_deletar:
                        //Log.d("Deletar", albumId.toString());
                        apagarItem();
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
        return false;
    }
    public void apresentaItem(){
        Activity activity = (Activity)itemView.getContext();
        Intent tela = new Intent(activity, DetalheAlbum.class);
        tela.putExtra("id", albumId);
        //activity.startActivity(tela);
        activity.startActivityForResult(tela, EDITA_ALBUM);
    }

    public void apagarItem(){
        List<Long> apagarAlbum;
        apagarAlbum = new ArrayList<>();
        apagarAlbum.add(albumId);
        dao.retiraSelecionados(apagarAlbum);
        adapterRecycler.removerItem(albumId.intValue());
    }



}
