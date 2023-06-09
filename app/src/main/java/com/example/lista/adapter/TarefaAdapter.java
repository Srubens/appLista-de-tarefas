package com.example.lista.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lista.R;
import com.example.lista.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.MyViewHolder> {

    private List<Tarefa> listaDeTarefa = new ArrayList<>();

    public TarefaAdapter(List<Tarefa> lista) {
        this.listaDeTarefa = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lista_tarefa_adapter, viewGroup, false );
        return new MyViewHolder( itemLista );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Tarefa tarefa = listaDeTarefa.get(i);
        myViewHolder.tarefa.setText( tarefa.getNomeTarefa() );
    }

    @Override
    public int getItemCount() {
        return this.listaDeTarefa.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView tarefa;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tarefa = itemView.findViewById(R.id.txtTarefa);
        }
    }

}
