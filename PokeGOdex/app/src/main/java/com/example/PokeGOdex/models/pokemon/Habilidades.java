package com.example.PokeGOdex.models.pokemon;

import com.example.PokeGOdex.models.general.NameUrl;
import com.google.gson.annotations.SerializedName;

public class Habilidades {

    @SerializedName("ability")
    private NameUrl ability;

    @SerializedName("is_hidden")
    private Boolean is_hidden;

    @SerializedName("slot")
    private double slot;

    public NameUrl getAbility() {
        return ability;
    }

    public Boolean getIs_hidden() {
        return is_hidden;
    }

    public double getSlot() {
        return slot;
    }
}
