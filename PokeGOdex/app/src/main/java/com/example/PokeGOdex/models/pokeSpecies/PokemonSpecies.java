package com.example.PokeGOdex.models.pokeSpecies;

import com.example.PokeGOdex.models.general.FlavorText;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PokemonSpecies {

    @SerializedName("flavor_text_entries")
    ArrayList<FlavorText> flavor_text_entries;

    public ArrayList<FlavorText> getFlavor_text_entries() {
        return flavor_text_entries;
    }
}
