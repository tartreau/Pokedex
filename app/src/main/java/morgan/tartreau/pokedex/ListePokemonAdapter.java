package morgan.tartreau.pokedex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import morgan.tartreau.pokedex.models.Pokemon;

/**
 *
 *
 */
public class ListePokemonAdapter extends RecyclerView.Adapter<ListePokemonAdapter.ViewHolder> {



    private ArrayList<Pokemon> dataset;
    private OnItemClickListener listener;
    private Context context;

    public interface OnItemClickListener{
        void onItemClick(Button item_pokemon);
    }


    public ListePokemonAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.liste_pokemon, parent, false);
        return new ViewHolder(view);
    }


    //affichage du Pokemon dans la liste
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //on recupere sa position
        Pokemon p = dataset.get(position);
        //on affiche son nom en anglais
        holder.nombreTextView.setText(p.getName());

        //on affiche une image en fonction du Pokemon
        Glide.with(context)
                .load("http://pokeapi.co/media/sprites/pokemon/" + p.getNumber() + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.spriteImageView);
        holder.numeroTextView.setText(""+p.getNumber());

        holder.bind(dataset.get(position), listener);
    }


    //on recupere le nombre total de pokemon
    @Override
    public int getItemCount() {
        return dataset.size();
    }


    //on créé la liste de Pokemon
    public void ajouterListePokemon(ArrayList<Pokemon> listePokemon) {
        dataset.addAll(listePokemon);
        notifyDataSetChanged();
    }


    //on fait les itemView pour chaque pokemon
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView spriteImageView;
        private TextView nombreTextView;
        private TextView numeroTextView;

        public ViewHolder(View itemView) {
            super(itemView);


            spriteImageView = (ImageView) itemView.findViewById(R.id.spriteImageView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
            numeroTextView = (TextView) itemView.findViewById(R.id.numeroTextView);


        }

        public void bind(final Pokemon liste_pokemon, final OnItemClickListener listener){
           //on applique le setOnClickListener à chaque éléments
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent monIntent = new Intent(v.getContext(), activity_un_pokemon.class);
                    nombreTextView = (TextView) itemView.findViewById(R.id.numeroTextView);
                    String monId = nombreTextView.getText().toString();
                    monIntent.putExtra("id", monId);

                    nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
                    String nomPokemon = nombreTextView.getText().toString();
                    monIntent.putExtra("nomPokemon", nomPokemon);

                    //faire passer l'image d'un endroit à un autre
                    //recuperer l'ID de l'image et le faire passer dans l'autre intent

                  /*  spriteImageView = (ImageView) itemView.findViewById(R.id.spriteImageView);
                    ImageView imagePokemon = spriteImageView.getText().toString();
                    monIntent.putExtra("spriteDuPokemon", imagePokemon);*/
                    v.getContext().startActivity(monIntent);


//                    listener.onItemClick(liste_pokemon);
                    //listener.onItemClick(voirDetail);
                    }
                });
            }//fin methode bind*/
        }


    }







