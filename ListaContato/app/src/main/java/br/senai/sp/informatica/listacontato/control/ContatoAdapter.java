package br.senai.sp.informatica.listacontato.control;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
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

public class ContatoAdapter extends BaseAdapter implements View.OnClickListener{
    private ContatoDao dao = ContatoDao.manager;
    private Map<Integer, Long> mapa;

    private boolean trocouLayout = false;
    private boolean apagar = false;

    public enum TipoDeDetalhe{
        EDICAO,
        EXCLUSAO;
    }

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
        if (convertView == null || trocouLayout){
            Context ctx = parent.getContext();
            LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            layout = new ConstraintLayout(ctx);
            //TODO: verificar
            if (!apagar){
                inflater.inflate(R.layout.layout_detalhe_lista, layout);
            } else {
                inflater.inflate(R.layout.layout_detalhe_lista_apagar, layout);
            }
            Log.d("ContatoAdapter", "apagar: "+ apagar);

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


        if (apagar){
            CheckBox checkBox = (CheckBox)layout.findViewById(R.id.checkBox);
            checkBox.setTag(cont.getId());
            checkBox.setOnClickListener(this);
        }
        //Log.d("ContatoAdapter", "apagar: " + apagar);
        return layout;
    }

    public void setLayout(TipoDeDetalhe tipo){
        if (tipo == TipoDeDetalhe.EDICAO){
            apagar = false;
        } else {
            apagar = true;
        }
        trocouLayout = true;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        Long id = (Long)v.getTag();
        Contato cont = dao.getContato(id);
        cont.setDel(!cont.isDel());

        //Log.d("ContatoAdapter", "Contato marcado para exclusão [" + cont.isDel() + "] id: " + cont.getNome());

        dao.salvar(cont);
    }
}
