package morgan.tartreau.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import morgan.tartreau.pokedex.models.Pokemon;

/**
 * Created by Morgan on 03/03/2018.
 */

public class BDDActivity extends AppCompatActivity {
    TextView textviewid;
    TextView textviewname;
    TextView textviewxp;
    TextView textviewheight;
    TextView textviewweight;
    TextView textviewlist;

    Button btajout;

    List<Pokemon> listAllPokemon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bdd_activity);

        btajout = new Button (this);

        //Création d'une instance de ma classe PokemonBDD
        PokemonBDD pokemonBdd = new PokemonBDD(this);

        //Création d'un pokemon
        Pokemon arcanin = new Pokemon(59, "Arcanin", 194, 190, 155);
        Pokemon metamorph = new Pokemon(132, "Métamorph", 101, 3, 4);
        Pokemon Pikachu = new Pokemon(25, "Pikachu", 118, 40, 6);


        //On ouvre la base de données pour écrire dedans
        pokemonBdd.open();
        //On insère le pokemon que l'on vient de créer
        pokemonBdd.insertPokemon(metamorph);
        pokemonBdd.insertPokemon(arcanin);
        pokemonBdd.insertPokemon(Pikachu);



        /**
         * Récupération des chaînes de la table.
         */
        /*public List<Pokemon> getValues() {
            List<Pokemon> listPokemon = new ArrayList<>();
            String[] columns = {"value"};
            // Exécution de la requête pour obtenir les chaînes et récupération d'un curseur sur ces données.
            Cursor cursor = (Cursor) pokemonBdd.selectPokemon();
            // Curseur placé en début des chaînes récupérées.
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                // Récupération d'une chaîne et insertion dans une liste.
                listPokemon.add(cursor.getString(0));
                // Passage à l'entrée suivante.
                cursor.moveToNext();
            }
            // Fermeture du curseur.
            cursor.close();

            return listPokemon;
        }*/





        listAllPokemon = PokemonBDD.getAllPokemon();

        String testList="";

        textviewlist= (TextView) findViewById(R.id.textviewlist);

        for (Pokemon pokemonDeLaListe : listAllPokemon)
        {
            testList = " ID: " + pokemonDeLaListe.getId() + " Nom: " + pokemonDeLaListe.getName() + " XP: " + pokemonDeLaListe.getBase_experience() + " Taille: " + pokemonDeLaListe.getHeight() + " Poids: " + pokemonDeLaListe.getWeight();

            //Writing to log
            Log.d("affichage: ", testList);



            String old=textviewlist.getText().toString();

            String newtxt = old+"\n\n"+testList;

            textviewlist.setText(newtxt);





            /*textviewid=findViewById(R.id.viewid);
            textviewid.setText(Integer.toString(pokemonFromBdd.getId()));

            textviewname=findViewById(R.id.viewname);
            textviewname.setText(pokemonFromBdd.getName());

            textviewxp=findViewById(R.id.viewxp);
            textviewxp.setText(Integer.toString(pokemonFromBdd.getBase_experience()));

            textviewheight=findViewById(R.id.viewheight);
            textviewheight.setText(Integer.toString(pokemonFromBdd.getHeight()));

            textviewweight=findViewById(R.id.viewweight);
            textviewweight.setText(Integer.toString(pokemonFromBdd.getWeight()));*/



           /* LinearLayout layoutligne=(LinearLayout) findViewById(R.id.layoutligne);

            TextView viewid =new TextView (this);
            viewid.setText(Integer.toString(pokemonFromBdd.getId()));
            layoutligne.addView(viewid);

            TextView viewname =new TextView (this);
            viewname.setText(pokemonDeLaListe.getName());
            layoutligne.addView(viewname);

            TextView viewxp =new TextView (this);
            viewxp.setText(Integer.toString(pokemonDeLaListe.getBase_experience()));
            layoutligne.addView(viewxp);

            TextView viewheight =new TextView (this);
            viewheight.setText(Integer.toString(pokemonDeLaListe.getHeight()));
            layoutligne.addView(viewheight);

            TextView viewweight =new TextView (this);
            viewweight.setText(Integer.toString(pokemonDeLaListe.getWeight()));
            layoutligne.addView(viewweight);*/

        }



        //Pour vérifier que l'on a bien créé notre pokemon dans la BDD
        //on extrait le pokemon de la BDD grâce au nom du pokemon que l'on a créé précédemment
        Pokemon pokemonFromBdd = pokemonBdd.getPokemonWithName(metamorph.getName());
        //Si un pokemon est retourné (donc si le pokemon à bien été ajouté à la BDD)
        if(pokemonFromBdd != null){
            //On affiche les infos du pokemon dans un Toast
            Toast.makeText(this, pokemonFromBdd.toString(), Toast.LENGTH_LONG);
            //On modifie le nom du pokemon
            //pokemonFromBdd.setName("Métamorph");
            //Puis on met à jour la BDD
            //pokemonBdd.updatePokemon(pokemonFromBdd.getId(), pokemonFromBdd);
        }

        //On extrait le pokemon de la BDD grâce au nouveau nom
        //pokemonFromBdd = pokemonBdd.getPokemonWithName("Métamorph");
        //S'il existe un pokemon possédant ce nom dans la BDD
        /*if(pokemonFromBdd != null){
            //On affiche les nouvelles informations du pokemon pour vérifier que le nom du pokemon a bien été mis à jour
            Toast.makeText(this, pokemonFromBdd.toString(), Toast.LENGTH_LONG).show();
            //on supprime le pokemon de la BDD grâce à son ID
            pokemonBdd.removePokemonWithID(pokemonFromBdd.getId());
        }*/









        //On essaye d'extraire de nouveau le pokemon de la BDD toujours grâce à son nouveau nom
        pokemonFromBdd = pokemonBdd.getPokemonWithName("Métamorph");
        //Si aucun pokemon n'est retourné
        if(pokemonFromBdd == null){
            //On affiche un message indiquant que le pokemon n'existe pas dans la BDD
            Toast.makeText(this, "Ce pokemon n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        }
        //Si le pokemon existe (mais normalement il ne devrait pas)
        else{
            //on affiche un message indiquant que le pokemon existe dans la BDD
            Toast.makeText(this, "Ce pokemon existe dans la BDD", Toast.LENGTH_LONG).show();
        }

        pokemonBdd.close();
    }

    public void ajouter_pokemon(View v) {

        PokemonBDD pokemonBdd = new PokemonBDD(this);



        EditText inputid;
        EditText inputname;
        EditText inputxp;
        EditText inputheight;
        EditText inputweight;

        inputid= (EditText) findViewById(R.id.inputid);
        inputname= (EditText) findViewById(R.id.inputname);
        inputxp= (EditText) findViewById(R.id.inputxp);
        inputheight= (EditText) findViewById(R.id.inputheight);
        inputweight= (EditText) findViewById(R.id.inputweight);

        int idajout = Integer.parseInt(inputid.getText().toString());
        String nameajout = inputname.getText().toString();
        int xpajout = Integer.parseInt(inputxp.getText().toString());
        int heightajout = Integer.parseInt(inputheight.getText().toString());
        int weightajout = Integer.parseInt(inputweight.getText().toString());

        Pokemon pokemonAjoute = new Pokemon(idajout, nameajout, xpajout, heightajout, weightajout);

        pokemonBdd.open();
        pokemonBdd.insertPokemon(pokemonAjoute);


        listAllPokemon = PokemonBDD.getAllPokemon();

        String testList="";

        textviewlist= (TextView) findViewById(R.id.textviewlist);


        testList = " ID: " + pokemonAjoute.getId() + " Nom: " + pokemonAjoute.getName() + " XP: " + pokemonAjoute.getBase_experience() + " Taille: " + pokemonAjoute.getHeight() + " Poids: " + pokemonAjoute.getWeight();

        String old=textviewlist.getText().toString();

        String newtxt = old+"\n\n"+testList;

        textviewlist.setText(newtxt);






        pokemonBdd.close();

        //Writing to log
        Log.d("id: ", String.valueOf(idajout));
        Log.d("name: ", String.valueOf(nameajout));
        Log.d("xp: ", String.valueOf(xpajout));
        Log.d("height: ", String.valueOf(heightajout));
        Log.d("weight: ", String.valueOf(weightajout));

        Log.d("pokemon: ", pokemonAjoute.toString());
    }


}

