package com.example.apoh.schoolboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


class Adaptador extends RecyclerView.Adapter<ItemHolder>{

    Context contexto = null;
    ArrayList<ItemListaPrincipal> lista = null;

    Adaptador(Context contexto, ArrayList<ItemListaPrincipal> lista){

        this.contexto = contexto;
        this.lista = lista;
    }

    //METODO CHAMADO N VEZES PARA INFLAR O XML DA CELULA E RETORNAR UM OBJETO DE LAYOUT
    /* Método que deverá retornar layout criado pelo ViewHolder já inflado em uma view. */
    //@NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View celula = LayoutInflater.from(contexto).inflate(R.layout.layout_item_lista_principal, parent,false );
        ItemHolder item = new ItemHolder(celula);
        return item;
    }
    /*
     * Método que recebe o ViewHolder e a posição da lista.
     * Aqui é recuperado o objeto da lista de Objetos pela posição e associado à ViewHolder.
     * É onde a mágica acontece!
     * */
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        ItemListaPrincipal item = lista.get(position);

        holder.textoDisciplina.setText(item.disciplina);
        holder.textoProfessor.setText(item.professor);
        holder.textoContador.setText(item.contador);
    }

    //METODO QUE DETERMINA QUANTOS ITENS VAI TER NA LISTA
    /*
     * Método que deverá retornar quantos itens há na lista.
     * Aconselha-se verificar se a lista não está nula como no exemplo,
     * pois ao tentar recuperar a quantidade da lista nula pode gerar
     * um erro em tempo de execução (NullPointerException).
     * */
    @Override
    public int getItemCount() {

        return (lista != null)? lista.size() : 0;
    }
}

public class TelaPrincipal extends AppCompatActivity {
    ArrayList<ItemListaPrincipal> dataSource = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_principal);

        dataSource = new ArrayList<>();
        RecyclerView lista = null;

        //Adicionando fixamente os itens, exemplo

        dataSource.add(new ItemListaPrincipal("Dispositivos Moveis", "Silvano", "5"));
        dataSource.add(new ItemListaPrincipal("Engenharia de Software", "Alex", "6"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));

        lista = (RecyclerView)findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setItemAnimator(new DefaultItemAnimator());
        lista.setHasFixedSize(true);

        Adaptador adapt = new Adaptador(this, dataSource);
        lista.setAdapter(adapt);

    }
}
