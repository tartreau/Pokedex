package morgan.tartreau.pokedex;

/**
 * Created by Morgan on 03/03/2018.
 */


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MaBaseSQLite extends SQLiteOpenHelper {

    private static final String TABLE_POKEMON = "table_pokemon";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "Name";
    private static final String COL_BASE_XP = "Base_Experience";
    private static final String COL_HEIGHT = "Height";
    private static final String COL_WEIGHT = "Weight";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_POKEMON + " ("
            + COL_ID + " INTEGER PRIMARY KEY, " + COL_NAME + " TEXT NOT NULL, "
            + COL_BASE_XP + " INTEGER, "+ COL_HEIGHT + " INTEGER, "+ COL_WEIGHT + " INTEGER);";

    public MaBaseSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_POKEMON + ";");
        onCreate(db);
    }

}