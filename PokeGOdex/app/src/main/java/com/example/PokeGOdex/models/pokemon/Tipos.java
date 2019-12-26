package com.example.PokeGOdex.models.pokemon;

import com.example.PokeGOdex.models.general.NameUrl;
import com.google.gson.annotations.SerializedName;

public class Tipos {

    @SerializedName("slot")
    private double slot;

    private NameUrl type;

    public double getSlot() {
        return slot;
    }

    public NameUrl getType() {
        return type;
    }
}
