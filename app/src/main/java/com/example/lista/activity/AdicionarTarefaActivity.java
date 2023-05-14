package com.example.lista.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lista.R;
import com.example.lista.helper.TarefaDAO;
import com.example.lista.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        //EDICAO DE TAREFA
        editTarefa = findViewById(R.id.textTarefa);
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");
        if( tarefaAtual != null ){
            editTarefa.setText(tarefaAtual.getNomeTarefa());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch ( item.getItemId() ){
            case R.id.itemSalvar:
                TarefaDAO tarefaDAO = new TarefaDAO( getApplicationContext() );

                String nomeTarefa = editTarefa.getText().toString();
                if( tarefaAtual != null ){
                    if( !nomeTarefa.isEmpty() ){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa( nomeTarefa );
                        tarefa.setId( tarefaAtual.getId() );
                        if( tarefaDAO.atualizar(tarefa) ){
                            finish();
                            Toast.makeText(getApplicationContext(),"Sucesso ao atualizar", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Erro ao atualizar", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    if( !nomeTarefa.isEmpty() ){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        if( tarefaDAO.salvar( tarefa ) ){
                            finish();
                            Toast.makeText(getApplicationContext(),"Sucesso ao salvar tarefa!", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"Erro ao Salvar. Digite algo para salva nova tarefa!", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}