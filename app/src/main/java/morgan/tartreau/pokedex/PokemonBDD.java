package morgan.tartreau.pokedex;

/**
 * Created by Morgan on 03/03/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import morgan.tartreau.pokedex.models.Pokemon;

public class PokemonBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "pokemon.db";

    private static final String TABLE_POKEMON = "table_pokemon";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "Name";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_BASE_XP = "Base_Experience";
    private static final int NUM_COL_BASE_XP = 2;
    private static final String COL_HEIGHT = "Height";
    private static final int NUM_COL_HEIGHT = 3;
    private static final String COL_WEIGHT = "Weight";
    private static final int NUM_COL_WEIGHT = 4;

    public static SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public PokemonBDD(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertPokemon(Pokemon pokemon){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID, pokemon.getId());
        values.put(COL_NAME, pokemon.getName());
        values.put(COL_BASE_XP, pokemon.getBase_experience());
        values.put(COL_HEIGHT, pokemon.getHeight());
        values.put(COL_WEIGHT, pokemon.getWeight());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_POKEMON, null, values);
    }

    public int updatePokemon(int id, Pokemon pokemon){
        //La mise à jour d'un pokemon dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel pokemon on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_NAME, pokemon.getName());
        values.put(COL_BASE_XP, pokemon.getBase_experience());
        values.put(COL_HEIGHT, pokemon.getHeight());
        values.put(COL_WEIGHT, pokemon.getWeight());
        return bdd.update(TABLE_POKEMON, values, COL_ID + " = " +id, null);
    }

    public int removePokemonWithID(int id){
        //Suppression d'un pokemon de la BDD grâce à l'ID
        return bdd.delete(TABLE_POKEMON, COL_ID + " = " +id, null);
    }

    public Pokemon getPokemonWithName(String name){
        //Récupère dans un Cursor les valeurs correspondant à un pokemon contenu dans la BDD (ici on sélectionne le pokemon grâce à son nom)
        Cursor c = bdd.query(TABLE_POKEMON, new String[] {COL_ID, COL_NAME, COL_BASE_XP, COL_HEIGHT, COL_WEIGHT}, COL_NAME + " LIKE \"" + name +"\"", null, null, null, null);
        return cursorToPokemon(c);
    }

    public Pokemon selectPokemon(){
        //Récupère dans un Cursor les valeurs correspondant à un pokemon contenu dans la BDD
        Cursor c = bdd.query(TABLE_POKEMON, new String[] {COL_ID, COL_NAME, COL_BASE_XP, COL_HEIGHT, COL_WEIGHT}, null, null, null, null, null);
        return cursorToPokemon(c);
    }

    public static List<Pokemon> getAllPokemon()
    {
        List<Pokemon> dataList = new ArrayList<Pokemon>();

        Cursor cursor = bdd.rawQuery("SELECT * FROM " + TABLE_POKEMON, null);

        if (cursor.moveToFirst())
        {
            do
            {
                Pokemon data = new Pokemon(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4)); dataList.add(data);
            }

            while (cursor.moveToNext());
        }
        return dataList;
    }


    //Cette méthode permet de convertir un cursor en un pokemon
    private Pokemon cursorToPokemon(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un pokemon
        Pokemon pokemon = new Pokemon();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        pokemon.setId(c.getInt(NUM_COL_ID));
        pokemon.setName(c.getString(NUM_COL_NAME));
        pokemon.setBase_experience(c.getInt(NUM_COL_BASE_XP));
        pokemon.setHeight(c.getInt(NUM_COL_HEIGHT));
        pokemon.setWeight(c.getInt(NUM_COL_WEIGHT));
        //On ferme le cursor
        c.close();

        //On retourne le pokemon
        return pokemon;
    }
}