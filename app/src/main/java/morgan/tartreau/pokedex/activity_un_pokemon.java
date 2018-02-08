package morgan.tartreau.pokedex;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import morgan.tartreau.pokedex.models.PokemonDetailsReponse;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import morgan.tartreau.pokedex.models.Pokemon;
import morgan.tartreau.pokedex.models.PokemonReponse;
import morgan.tartreau.pokedex.pokeapi.PokeapiService;

public class activity_un_pokemon extends AppCompatActivity {

    private static final String TAG = "error";
    TextView nom_du_pokemon;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_pokemon);

        //après avoir récupérer l'id du Pokémon, on refait un insert avec Retrofit
        //en utilisant l'id pour récupérer des infos

        //fonction DetailPokémon ici

        Intent monIntent = getIntent();

        String monId = monIntent.getStringExtra("id");
        TextView numero = (TextView) findViewById(R.id.numero_du_pokemon);
        numero.setText(monId);

        String nomPokemon = monIntent.getStringExtra("nomPokemon");
        TextView nom = (TextView) findViewById(R.id.nom_du_pokemon);
        nom.setText(nomPokemon);

/*

        retrofit = new Retrofit.Builder()
                //.baseUrl("http://pokeapi.co/api/v2")
                .baseUrl("http://pokeapi.co/api/v2" +numero.getText())
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        //obtenirDonneesUnItem(Integer.parseInt(monId));


    }
//ici je bloque pour afficher des données
    /*private void obtenirDonneesUnItem(int id) {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonReponse> PokemonReponseCall = service.obtenirDonneesUnItem(id);

        PokemonReponseCall.enqueue(new Callback<PokemonReponse>() {
            @Override
            public void onResponse(Call<PokemonReponse> call, Response<PokemonReponse> response) {

                if (response.isSuccessful()) {

                    Pokemon reponse = response.body();
                    int detailPokemon = reponse.getWeight();
                    TextView poidsPokemon = (TextView) findViewById(R.id.poids_du_pokemon);


                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonReponse> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }*/

}


