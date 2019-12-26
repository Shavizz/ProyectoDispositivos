package com.example.PokeGOdex.models.general;

import com.google.gson.annotations.SerializedName;

public class NameUrl {

    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    private int number;//Con esto cogemos el id del pokemon en @GET("pokemon")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length -1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }
}