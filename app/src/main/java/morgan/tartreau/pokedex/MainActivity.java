package morgan.tartreau.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import morgan.tartreau.pokedex.models.Pokemon;
import morgan.tartreau.pokedex.models.PokemonReponse;
import morgan.tartreau.pokedex.pokeapi.PokeapiService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "POKEDEX";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListePokemonAdapter listePokemonAdapter;
    private ListePokemonAdapter.OnItemClickListener listener;
    private int offset;
    private boolean aptoParaCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent monIntent = new Intent(this, activity_un_pokemon.class);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listePokemonAdapter = new ListePokemonAdapter(this, listener);
        recyclerView.setAdapter(listePokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Fin de la liste des Pokémon.");

                            aptoParaCargar = false;
                            //dès qu'on scroll, 5 pokémon s'affichent en plus
                            offset += 5;
                            obtenirDonneesListe(offset);
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        aptoParaCargar = true;
        //début de la liste
        offset = 0;
        obtenirDonneesListe(offset);
    }



    private void obtenirDonneesListe(int offset) {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonReponse> PokemonReponseCall = service.ObtenirListePokemon(30, offset);

        PokemonReponseCall.enqueue(new Callback<PokemonReponse>() {
            @Override
            public void onResponse(Call<PokemonReponse> call, Response<PokemonReponse> response) {
                aptoParaCargar = true;
                if (response.isSuccessful()) {

                    PokemonReponse pokemonReponse = response.body();
                    ArrayList<Pokemon> listePokemon = pokemonReponse.getResults();

                    listePokemonAdapter.ajouterListePokemon(listePokemon);

                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonReponse> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }


}
