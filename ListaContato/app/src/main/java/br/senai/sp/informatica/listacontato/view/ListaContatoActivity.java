package br.senai.sp.informatica.listacontato.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import br.senai.sp.informatica.listacontato.R;

public class ListaContatoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listView;
    private final int EDITA_CONTATO = 0;
    private BaseAdapter itemLista;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lista_contato);


        itemLista = new ContatoAdapter();


        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(itemLista);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent tela = new Intent(getBaseContext(), DetalheContatoActivity.class);
        tela.putExtra("id", id);
        startActivityForResult(tela, EDITA_CONTATO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            itemLista.notifyDataSetChanged();
        }
    }
}
