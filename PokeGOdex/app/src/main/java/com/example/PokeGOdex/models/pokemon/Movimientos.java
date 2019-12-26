package com.example.PokeGOdex.models.pokemon;

import com.example.PokeGOdex.models.general.NameUrl;
import com.google.gson.annotations.SerializedName;

public class Movimientos {

    @SerializedName("move")
    private NameUrl move;

    public NameUrl getMove() {
        return move;
    }
}
