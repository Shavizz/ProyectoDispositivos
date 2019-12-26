package com.example.PokeGOdex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.PokeGOdex.controller.PokeClient;
import com.example.PokeGOdex.controller.PokeapiService;
import com.example.PokeGOdex.models.general.NameUrl;
import com.example.PokeGOdex.models.pokemon.PokemonRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ListaPokemonAdapter.ItemClickListener{

    private static final String TAG = "POKEDEX";

    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;

    private int offset;
//    private boolean aptoParaCargar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id. recyclerView);
        listaPokemonAdapter = new ListaPokemonAdapter(this, this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this,3);//3 es el tamaño de las columnas
        recyclerView.setLayoutManager(layoutManager);

//        Método para cargar los datos de 20 en 20 cuando la llamada a service.obtenerListaPokemon tiene limit 20 y offset+=20
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
//            @Override
//                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//
//                    if (dy>0){
//                        int visibleItemCount = layoutManager.getChildCount();
//                        int totalItemCount = layoutManager.getItemCount();
//                        int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
//
//                        if(aptoParaCargar){
//                            if ((visibleItemCount + pastVisibleItems) >= totalItemCount){
//                                aptoParaCargar = false;
//                                offset += 20;
//                                obtenerDatos(offset);
//                            }
//                        }
//                    }
//                }
//            }
//        );
//        aptoParaCargar = true;
        offset = 0;
        obtenerDatos(offset);

    }

    private void obtenerDatos(int offset) {
        PokeapiService service = PokeClient.getRetrofit().create(PokeapiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon(807,offset);

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
//                aptoParaCargar = true;

                if(response.isSuccessful()){
                    PokemonRespuesta pokemonRespuesta = response.body();
                    ArrayList<NameUrl> listaPokemon = pokemonRespuesta.getResults();

                    listaPokemonAdapter.addListaPokemon(listaPokemon);

                }else{
                    Log.e(TAG, "onResponse :" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
//                aptoParaCargar = true;
                Log.e(TAG, "onFailure :" + t.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int clickedItemIndex) {
        Intent intentPokemonActivity = new Intent(MainActivity.this, PokemonActivity.class);
        intentPokemonActivity.putExtra("id", clickedItemIndex+1);
        startActivity(intentPokemonActivity);
    }
}
