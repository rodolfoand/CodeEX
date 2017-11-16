package br.senai.sp.informatica.listacontato.control;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import br.senai.sp.informatica.listacontato.R;
import br.senai.sp.informatica.listacontato.model.ContatoDao;

public class ListaContatoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{

    private ListView listView;
    private final int EDITA_CONTATO = 0;
    private final int NOVO_CONTATO = 1;
    private ContatoAdapter itemLista;
    private MenuItem miApagar;
    private MenuItem miEditar;
    private ContatoDao dao = ContatoDao.manager;
    private MenuItem miDuplicar;




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
        String msg = "A " + (requestCode == EDITA_CONTATO ? "alteração" : "inclusão") + " do contato foi ";
        Log.d("ListaContatoActivity","requestCode: " + requestCode);
        if (resultCode == RESULT_OK){
            itemLista.notifyDataSetChanged();
            msg += "um sucesso.";
        } else {
            msg += "cancelada.";
        }
        Snackbar.make(findViewById(android.R.id.content), msg,Snackbar.LENGTH_LONG).show();
    }

    public void adicionaContato(View v){
        Intent tela = new Intent(getBaseContext(), DetalheContatoActivity.class);
        startActivityForResult(tela, NOVO_CONTATO);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_contato, menu);
        miEditar = (MenuItem)menu.findItem(R.id.mi_lista_editar);
        miApagar = (MenuItem)menu.findItem(R.id.mi_lista_apagar);
        miDuplicar = (MenuItem)menu.findItem(R.id.mi_lista_duplicar);
        miApagar.setVisible(false);
        miDuplicar.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.mi_lista_apagar:
                if (dao.existeSelecao()) {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                    alerta.setMessage("Deseja apagar os contatos selecionados?");
                    alerta.setNegativeButton("Não", null);
                    alerta.setPositiveButton("Sim", this);
                    alerta.create();
                    alerta.show();
                } else {
                    itemLista.setLayout(ContatoAdapter.TipoDeDetalhe.EDICAO);
                    miEditar.setVisible(true);
                    miApagar.setVisible(false);
                    miDuplicar.setVisible(false);
                }



                break;
            case R.id.mi_lista_editar:
                itemLista.setLayout(ContatoAdapter.TipoDeDetalhe.EXCLUSAO);
                miEditar.setVisible(false);
                miApagar.setVisible(true);
                miDuplicar.setVisible(true);
                break;
            case R.id.mi_lista_duplicar:
                dao.duplicarSelecionados();
                itemLista.setLayout(ContatoAdapter.TipoDeDetalhe.EDICAO);
                miEditar.setVisible(true);
                miApagar.setVisible(false);
                miDuplicar.setVisible(false);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dao.apagarSelecionados();
        itemLista.setLayout(ContatoAdapter.TipoDeDetalhe.EDICAO);
        miEditar.setVisible(true);
        miApagar.setVisible(false);
        miDuplicar.setVisible(false);

        Snackbar.make(findViewById(android.R.id.content), "Os contatos selecionados foram apagados.",Snackbar.LENGTH_LONG).show();
    }
}
