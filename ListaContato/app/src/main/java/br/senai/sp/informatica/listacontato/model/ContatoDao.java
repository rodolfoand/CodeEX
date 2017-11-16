package br.senai.sp.informatica.listacontato.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by WEB on 01/11/2017.
 */
//TODO: teste
public class ContatoDao {
    public static ContatoDao manager = new ContatoDao();
    private List<Contato> lista;
    private long id = 0;

    private ContatoDao() {
        lista = new ArrayList<>();
        lista.add(new Contato(id++, "Rodolfo Andrade", "rdvieira@gmail.com", new Date(1985, 9, 26)));
        lista.add(new Contato(id++, "Fulano de Tal", "email@email.com", new Date(1985, 10, 26)));
        lista.add(new Contato(id++, "Nome Completo", "email2@email2.com", new Date(1985, 11, 26)));


    }

    public List<Contato> getLista() {
        Collections.sort(lista);
        return Collections.unmodifiableList(lista);
    }

    public Contato getContato(final Long id){
        Contato cont = null;
        for (Contato obj: lista) {
            if (obj.getId() == id){
                cont = obj;
                break;
            }
        }

        //Contato contLocalizado = lista.get(lista.indexOf(new Contato(id)));
        return cont;
    }
    public void salvar(Contato obj){
        if (obj.getId() == null){
            obj.setId(id++);
            lista.add(obj);
        } else {

            int posicao = lista.indexOf(new Contato(obj.getId()));
            lista.set(posicao, obj);
        }


    }

    public void remover(Long id){
        lista.remove(new Contato(id));

    }

    public void apagarSelecionados() {
        List<Contato> contatos = new ArrayList<>();
        for (Contato obj: lista) {
            if (obj.isDel()){
                contatos.add(obj);
            }
        }

        for (Contato obj: contatos) {
            remover(obj.getId());
        }
    }

    public void duplicarSelecionados() {
        List<Contato> contatos = new ArrayList<>();
        for (Contato obj: lista) {
            if (obj.isDel()){
                contatos.add(obj);
            }
        }

        for (Contato obj: contatos) {
            obj.setId(null);
            salvar(obj);
        }
    }

    public boolean existeSelecao() {
        for (Contato obj: lista) {
            if (obj.isDel()){
                return true;
            }
        }
        return false;
    }
}
