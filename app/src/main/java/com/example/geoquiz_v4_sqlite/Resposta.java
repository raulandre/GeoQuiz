package com.example.geoquiz_v4_sqlite;

import java.util.UUID;

public class Resposta {
    private UUID mUUID;
    private boolean mAcertou; //RESPOSTA_CORRETA
    private boolean mGabarito; //RESPOSTA_OFERECIDA
    private boolean mColou;

    public Resposta(UUID _UUID, boolean acertou, boolean gabarito, boolean colou) {
        this.mUUID = _UUID;
        this.mAcertou = acertou;
        this.mGabarito = gabarito;
        this.mColou = colou;
    }

    public UUID GetUUID() {
        return mUUID;
    }

    public boolean GetAcertou() {
        return mAcertou;
    }

    public boolean GetGabarito() {
        return mGabarito;
    }

    public boolean GetColou() {
        return mColou;
    }
}
