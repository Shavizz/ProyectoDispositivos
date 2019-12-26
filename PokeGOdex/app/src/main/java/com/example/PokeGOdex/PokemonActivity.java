package com.example.PokeGOdex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.PokeGOdex.controller.PokeClient;
import com.example.PokeGOdex.controller.PokeapiService;
import com.example.PokeGOdex.models.pokeSpecies.PokemonSpecies;
import com.example.PokeGOdex.models.pokemon.Pokemon;
import com.example.PokeGOdex.models.pokemon.Stats;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonActivity extends AppCompatActivity {

    private static final String TAG = "Poke ";
    private int id;

    private TextView nombrePokemon, tipoPokemon, tipoPokemon2, infoPokemon, statsPokemon, descripcionPokemon;
    private ImageView fotoPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("id");
        }

        nombrePokemon = findViewById(R.id.nombrePokemon);
        tipoPokemon = findViewById(R.id.tipoPokemon);
        tipoPokemon2 = findViewById(R.id.tipoPokemon2);
        infoPokemon = findViewById(R.id.infoPokemon);
        statsPokemon = findViewById(R.id.statsPokemon);
        descripcionPokemon = findViewById(R.id.descriptionPokemon);
        fotoPokemon = findViewById(R.id.spritePokemon);

        obtenerPokemon(id);
    }

    private void obtenerPokemon(final int pokeId){
        final PokeapiService service = PokeClient.getRetrofit().create(PokeapiService.class);
        Call<Pokemon> pokemonCall = service.obtenerPokemon(pokeId);

        pokemonCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()){
                        Pokemon pokemonResponse = response.body();
                        nombrePokemon.setText(pokemonResponse.getName());

                        if (pokemonResponse.getTypes().size() == 1) {
                            tipoPokemon.setText(pokemonResponse.getTypes().get(0).getType().getName());

                        }else{
                            tipoPokemon.setText(pokemonResponse.getTypes().get(0).getType().getName());
                            tipoPokemon2.setText(pokemonResponse.getTypes().get(1).getType().getName());
                        }

                        Glide.with(PokemonActivity.this)//pokemonResponse.getSprites().getFront_default() algunos null en la api
                                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ pokeId +".png")
                                .into(fotoPokemon);

                        infoPokemon.setText(
                                "Id: " + pokemonResponse.getId()+"\n"+
                                "Peso: " + pokemonResponse.getWeight()+"\n"+
                                "Altura: " + pokemonResponse.getHeight()+"\n"+
                                "Especie: " + pokemonResponse.getSpecies().getName()+"\n"+
                                "Habilidades Pasivas: \n"
                        );

                        if (pokemonResponse.getAbilities().size() == 1){
                            infoPokemon.append("\t\t\t" + pokemonResponse.getAbilities().get(0).getAbility().getName() + "\n");
                        }else {
                            infoPokemon.append("\t\t\t" + pokemonResponse.getAbilities().get(0).getAbility().getName() + "\n");
                            infoPokemon.append("\t\t\t" + pokemonResponse.getAbilities().get(1).getAbility().getName() + "\n");
                        }

                    for (Stats stats : pokemonResponse.getStats()) {
                        statsPokemon.append(
                                stats.getStat().getName()+ " " + stats.getBase_stat() + "\n");
                    }
                }else{
                    Log.e(TAG, "onResponse:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });

        Call<PokemonSpecies> pokemonSpeciesCall = service.obtenerPokemonSpecies(pokeId);

        pokemonSpeciesCall.enqueue(new Callback<PokemonSpecies>() {
            @Override
            public void onResponse(Call<PokemonSpecies> call, Response<PokemonSpecies> response) {
                if (response.isSuccessful()){
                    PokemonSpecies especie = response.body();
                    for(int i=0 ; i <= especie.getFlavor_text_entries().size(); i++){
                        if (especie.getFlavor_text_entries().get(i).getLanguage().getName().equals("en")){
                            descripcionPokemon.setText(especie.getFlavor_text_entries().get(i).getFlavor_text());
                            break;
                        }else{
                            descripcionPokemon.setText("");
                        }
                    }
                }else {
                    Log.e(TAG,  "onResponse: " + response.errorBody());

                }
            }

            @Override
            public void onFailure(Call<PokemonSpecies> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
            }

            
        });
    }
}
