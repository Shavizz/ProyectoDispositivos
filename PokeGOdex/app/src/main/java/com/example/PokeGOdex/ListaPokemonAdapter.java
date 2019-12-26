package com.example.PokeGOdex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.PokeGOdex.models.general.NameUrl;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private static final String TAG = "POKE";
    private final ItemClickListener mOnClickListener;

    private Context context;

    private ArrayList<NameUrl> pokeList;

    public ListaPokemonAdapter(Context context,ItemClickListener mOnClickListener) {
        this.context = context;
        this.mOnClickListener = mOnClickListener;
        pokeList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pokemon, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        NameUrl pokemon = pokeList.get(i);
        viewHolder.nombrePokemon.setText(pokemon.getName());

        //Imágenes con Glide
        Glide.with(context).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ pokemon.getNumber() +".png")
        .centerCrop()
        .transition(new DrawableTransitionOptions()
                .crossFade())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(viewHolder.fotoPokemon);

        Log.i(TAG, "Numero Pokemones: " + pokemon.getName() + pokemon.getNumber());
    }

    @Override
    public int getItemCount() {
        return pokeList.size();
    }

    public void addListaPokemon(ArrayList<NameUrl> listaPokemon) {
        pokeList.addAll(listaPokemon);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView fotoPokemon;
        private TextView nombrePokemon;

        public ViewHolder(View itemView){
            super(itemView);

            fotoPokemon = itemView.findViewById(R.id.fotoPokemon);
            nombrePokemon = itemView.findViewById(R.id.nombrePokemon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onItemClick(clickedPosition);
        }
    }

    public interface ItemClickListener{
        void onItemClick(int clickedItemIndex);
    }
}
