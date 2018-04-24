package com.example.apoh.schoolboard;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.example.apoh.schoolboard.banco.Disciplina;

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
    @NonNull
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
    EditText nomeEditTxt,profTxt;

    private RecyclerView listaMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tela_principal);

        FloatingActionButton botaoAdd = (FloatingActionButton)findViewById(R.id.btnNovaMateria);
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayInputDialog();
            }
        });



        dataSource = new ArrayList<>();
        RecyclerView lista = null;

        //Adicionando fixamente os itens, exemplo

        dataSource.add(new ItemListaPrincipal("Dispositivos Moveis", "Silvano", "5"));
        dataSource.add(new ItemListaPrincipal("Engenharia de Software", "Alex", "6"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));
        dataSource.add(new ItemListaPrincipal("Computação Gráfica", "Silvano", "4"));

        lista = (RecyclerView)findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        lista.setItemAnimator(new DefaultItemAnimator());
        lista.setHasFixedSize(true);

        Adaptador adapt = new Adaptador(this, dataSource);
        lista.setAdapter(adapt);

        lista = (RecyclerView) findViewById(R.id.lista);
    }

    //DISPLAY INPUT DIALOG
    private void displayInputDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Insira a disciplina");
        d.setContentView(R.layout.input_dialog);

        nomeEditTxt= (EditText) d.findViewById(R.id.nomeEditText);
        profTxt= (EditText) d.findViewById(R.id.professorEditText);
        Button salvarBtn= (Button) d.findViewById(R.id.salvarBtn);

        //SAVE
        salvarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GET DATA
                String nome = nomeEditTxt.getText().toString();
                String professor = profTxt.getText().toString();

                //SET DATA
                Disciplina d = new Disciplina();
                d.setNome_disciplina(nome);
                d.setProfessor(professor);

                dataSource.add(new ItemListaPrincipal(nome, professor, "4"));

/*
                //SIMPLE VALIDATION
                if(nome != null && nome.length()>0)
                {
                    //THEN SAVE
                    if(helper.save(d))
                    {
                        //IF SAVED CLEAR EDITXT
                        nomeEditTxt.setText("");
                        profTxt.setText("");

                        com.example.apoh.schoolboard.adapter=new MyAdapter(MainActivity.this,helper.retrieve());
                        rv.setAdapter(com.example.apoh.schoolboard.adapter);


                    }
                }else
                {
                    Toast.makeText(MainActivity.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
                }*/

            }
        });

        d.show();
    }



}
