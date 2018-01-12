package br.senai.sp.informatica.meusalbuns.view.holder;

import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.lib.Util;
import br.senai.sp.informatica.meusalbuns.model.Album;

/**
 * Created by 34023325821 on 12/01/2018.
 */

public class AlbumViewHolderCard extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView ivCapaCard;
    private TextView tvArtistaCard;
    private TextView tvAlbumCard;
    private TextView tvGeneroCard;
    private TextView tvLancamentoCard;
    private View view;

    private Long albumId;
    private static DateFormat fmt = DateFormat.getDateInstance(DateFormat.LONG);

    public AlbumViewHolderCard(View itemView) {
        super(itemView);
        this.view = view;

        ivCapaCard = itemView.findViewById(R.id.ivCapaCard);
        tvArtistaCard = itemView.findViewById(R.id.tvArtistaCard);
        tvAlbumCard = itemView.findViewById(R.id.tvAlbumCard);
        tvGeneroCard = itemView.findViewById(R.id.tvGeneroCard);
        tvLancamentoCard = itemView.findViewById(R.id.tvGeneroCard);
    }

    public void setView(final Album album){

        albumId = album.getId();
        tvArtistaCard.setText(album.getArtista());
        tvAlbumCard.setText(album.getAlbum());
        tvGeneroCard.setText(album.getGenero());

        Date data = album.getDtLancamento();
        if(data != null) {
            tvLancamentoCard.setText(fmt.format(data));
        }

        try {
            ivCapaCard.setImageBitmap(Util.bitmapFromBase64(album.getCapa()));
        } catch (Exception e) {
            String nomeAlbum = tvAlbumCard.getText().toString();
            if (!nomeAlbum.isEmpty()) {
                Bitmap bitmap = Util.circularBitmapAndText(
                        ContextCompat.getColor(itemView.getContext(), R.color.colorPrimary)
                        , 200
                        , 600
                        , nomeAlbum.substring(0, 1).toUpperCase());
                ivCapaCard.setImageBitmap(bitmap);
            }
        }
    }


    @Override
    public void onClick(View v) {

    }
}
