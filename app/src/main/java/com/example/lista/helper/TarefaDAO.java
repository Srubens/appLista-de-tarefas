package com.example.lista.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lista.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements  iTarefaDAO {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper( context );
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());
        try{
            write.insert(DbHelper.TABELA_TAREFAS,null, cv);
            Log.i("SUCESS", "Tarefa cadastrada com sucesso! " );
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar a terefa " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());
        try{
            String[] args = { tarefa.getId().toString() };
            write.update(DbHelper.TABELA_TAREFAS, cv, "id=?", args );
            Log.i("SUCESS", "Tarefa atualizada com sucesso! " );
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar a terefa " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {
        try{
            String[] args = { tarefa.getId().toString() };
            write.delete(DbHelper.TABELA_TAREFAS,  "id=?", args );
            Log.i("SUCESS", "Tarefa atualizada com sucesso! " );
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar a terefa " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Tarefa> listar() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + " ;";
        Cursor c = read.rawQuery(sql, null);
        while( c.moveToNext() ){
            Tarefa tarefa = new Tarefa();

            @SuppressLint("Range") Long id = c.getLong( c.getColumnIndex("id") );
            @SuppressLint("Range") String nomeTarefa = c.getString( c.getColumnIndex("nome") );

            tarefa.setId(id);
            tarefa.setNomeTarefa( nomeTarefa );
            tarefas.add(tarefa);
        }
        return tarefas;
    }
}
