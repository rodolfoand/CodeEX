package br.senai.sp.informatica.meusalbuns.control;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.senai.sp.informatica.meusalbuns.R;
import br.senai.sp.informatica.meusalbuns.model.Album;
import br.senai.sp.informatica.meusalbuns.model.AlbumDao;

/**
 * Created by 34023325821 on 16/11/2017.
 */

public class AlbumAdapter extends BaseAdapter {
    private AlbumDao dao = AlbumDao.manager;
    private Map<Integer, Long> mapa;

    private boolean alteraLayout = false;
    private boolean editar = false;

    public void criaMapa(){
        mapa = new HashMap<>();
        List<Album> listaAlbuns =dao.getLista();
        for (int i = 0; i < listaAlbuns.size(); i++) {
            if (listaAlbuns.get(i).isAtivo()){
                mapa.put(i,listaAlbuns.get(i).getId());
            }
        }
    }
    public AlbumAdapter(){
        criaMapa();
    }

    @Override
    public int getCount() {
        return mapa.size();
    }

    @Override
    public Object getItem(int position) {
        return dao.getAlbum(mapa.get(position));
    }

    @Override
    public long getItemId(int position) {
        return mapa.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layout;
        if (convertView == null || alteraLayout == true){
            Context ctx = parent.getContext();
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = new ConstraintLayout(ctx);
            if (!editar){
                inflater.inflate(R.layout.layout_lista_detalhe, layout);
            } else {
                inflater.inflate(R.layout.layout_lista_detalhe_edit, layout);
            }
        } else {
            layout = (ConstraintLayout)convertView;
        }

        TextView tvArtista = layout.findViewById(R.id.etArtista);
        TextView tvNome = layout.findViewById(R.id.tvNome);
        TextView tvGenero = layout.findViewById(R.id.tvGenero);
        //TextView tvDtLancamento = layout.findViewById(R.id.tvDtLancamento);

        Album album = dao.getAlbum(mapa.get(position));

        tvArtista.setText(album.getArtista());
        tvNome.setText(album.getAlbum());
        tvGenero.setText(album.getGenero());
        //tvDtLancamento.setText(album.getDtLancamento());
        //TODO: Ajustar apresentação de data e capa

        return layout;
    }

    @Override
    public void notifyDataSetChanged() {
        criaMapa();
        super.notifyDataSetChanged();
    }

    enum TipoLista {
        EDITAR,
        APAGAR;
    }

    public void setLayout(TipoLista tipo){
        if (tipo == TipoLista.EDITAR){
            editar = true;
        } else {
            editar = false;
        }
        alteraLayout = true;
        notifyDataSetChanged();
    }
}
