package com.example.PokeGOdex.models.pokeMoves;

import com.example.PokeGOdex.models.general.Nombres;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Tipo {

    @SerializedName("names")
    private ArrayList<Nombres> names;

    public ArrayList<Nombres> getNames() {
        return names;
    }
}
