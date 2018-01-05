package morgan.tartreau.pokedex.models;

import java.util.ArrayList;


/**
 * Created by Morgan on 18/12/2017.
 */
//on a la liste des Pokémon
public class PokemonReponse extends Pokemon {

    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }


}
