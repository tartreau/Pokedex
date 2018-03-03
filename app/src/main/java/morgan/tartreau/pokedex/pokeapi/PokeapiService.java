package morgan.tartreau.pokedex.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import morgan.tartreau.pokedex.models.PokemonReponse;

/**
 *
 *
 */
//on utilise l'API Pokeapi

public interface PokeapiService {
    //on utilise le paramètre GET de Pokéapi et on construit le reste de l'url pour afficher les pokémon
    @GET("pokemon")
    //pour lister les Pokémon
    Call<PokemonReponse> ObtenirListePokemon(@Query("limit") int limit, @Query("offset") int offset); //liste
    Call<PokemonReponse> obtenirDonneesUnItem(@Query("id") int id); //details sur 1 pokemon
}