package br.senai.sp.informatica.listacontato.view;

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

import br.senai.sp.informatica.listacontato.R;
import br.senai.sp.informatica.listacontato.model.Contato;
import br.senai.sp.informatica.listacontato.model.ContatoDao;

/**
 * Created by WEB on 01/11/2017.
 */

public class ContatoAdapter extends BaseAdapter {
    private ContatoDao dao = ContatoDao.manager;
    private Map<Integer, Long> mapa;

    public ContatoAdapter() {
        criaMapa();
    }

    private void criaMapa(){
        mapa = new HashMap<>();
        List<Contato> lista = dao.getLista();
        for (int i = 0; i < lista.size(); i++) {
            mapa.put(i,lista.get(i).getId());
        }
    }

    @Override
    public void notifyDataSetChanged() {
        criaMapa();
        super.notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return mapa.get(position);
    }

    @Override
    public Object getItem(int position) {
        return dao.getContato((long)position);
    }

    @Override
    public int getCount() {
        return mapa.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layout;
        if (convertView == null){
            Context ctx = parent.getContext();
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            layout = new ConstraintLayout(ctx);
            inflater.inflate(R.layout.layout_detalhe_lista, layout);

        } else {
            layout = (ConstraintLayout)convertView;
        }
        TextView tvNome = layout.findViewById(R.id.tvNome);
        TextView tvEmail = layout.findViewById(R.id.tvEmail);

        //Contato cont = dao.getContato(mapa.get(position));

        Long id = mapa.get(position);
        Contato cont = dao.getContato(id);

        tvNome.setText(cont.getNome());
        tvEmail.setText(cont.getEmail());

        return layout;
    }

}
