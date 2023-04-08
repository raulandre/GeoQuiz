package com.example.geoquiz_v4_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QuestaoDB {

    private Context mContext;
    private static Context mStaticContext;
    private SQLiteDatabase mDatabase;

    public QuestaoDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mStaticContext = mContext;
        mDatabase = new QuestoesDBHelper(mContext).getWritableDatabase();
    }
    private static ContentValues getValoresConteudo(Questao q){
        ContentValues valores = new ContentValues();

        // pares chave-valor: nomes das colunas - valores
        valores.put(QuestoesDbSchema.QuestoesTbl.Cols.UUID, q.getId().toString());
        valores.put(QuestoesDbSchema.QuestoesTbl.Cols.TEXTO_QUESTAO,
                mStaticContext.getString(q.getTextoRespostaId())); // recupera valor do recurso string pelo id
        valores.put(QuestoesDbSchema.QuestoesTbl.Cols.QUESTAO_CORRETA, q.isRespostaCorreta());
        return valores;
    }

    private static ContentValues getValoresConteudo(Resposta r){
        ContentValues valores = new ContentValues();
        valores.put(QuestoesDbSchema.RespostasTbl.Cols.UUID, r.GetUUID().toString());
        valores.put(QuestoesDbSchema.RespostasTbl.Cols.ACERTOU, r.GetAcertou());
        valores.put(QuestoesDbSchema.RespostasTbl.Cols.GABARITO, r.GetGabarito());
        valores.put(QuestoesDbSchema.RespostasTbl.Cols.COLOU, r.GetColou());
        return valores;
    }

    public void addQuestao(Questao q){
        ContentValues valores = getValoresConteudo(q);
        mDatabase.insert(QuestoesDbSchema.QuestoesTbl.NOME, null, valores);
    }

    public void addResposta(Resposta r) {
        ContentValues valores = getValoresConteudo(r);
        mDatabase.insert(QuestoesDbSchema.RespostasTbl.NOME, null, valores);
    }

    public void updateQuestao(Questao q){
        String uuidString = q.getId().toString();
        ContentValues valores = getValoresConteudo(q);
       // mDatabase.update(QuestoesDbSchema.QuestoesTbl.NOME, valores, QuestoesDbSchema.QuestoesTbl.Cols.UUID +" = ?",
        //        new String[] {uuidString});
    }
    public Cursor queryQuestao(String clausulaWhere, String[] argsWhere){
        Cursor cursor = mDatabase.query(QuestoesDbSchema.QuestoesTbl.NOME,
                null,  // todas as colunas
                    clausulaWhere,
                    argsWhere,
                null, // sem group by
                null, // sem having
                null  // sem order by
                );
                return cursor;
    }

    public Cursor queryResposta(String clausulaWhere, String[] argsWhere) {
        Cursor cursor = mDatabase.query(QuestoesDbSchema.RespostasTbl.NOME, null, clausulaWhere, argsWhere, null, null, null);
        return cursor;
    }

    void removeBanco(){
        //mDatabase.delete(QuestoesDbSchema.QuestoesTbl.NOME, null, null);
        mDatabase.delete(QuestoesDbSchema.RespostasTbl.NOME,null, null);
    }
}
