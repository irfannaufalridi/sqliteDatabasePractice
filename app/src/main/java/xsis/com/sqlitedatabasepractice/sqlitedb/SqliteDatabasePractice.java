package xsis.com.sqlitedatabasepractice.sqlitedb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Irfan Naufal Ridi on 01/11/2018.
 */

public class SqliteDatabasePractice extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DBName";

    private static final int DATABASE_VERSION = 33;

    public static final String tb_DataPractice = "DataPractice";
    public static final String key_id = "id";
    private static final String name = "name";
    private static final String age = "age";
    private static final String gender = "gender";
    private static final String height = "height";

    private static final String DATABASE_CREATE = "CREATE TABLE " + tb_DataPractice + " (" +
            key_id + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
            name + " TEXT, " +
            gender + " TEXT, " +
            age + " INTEGER, " +
            height + " INTEGER);";

    public SqliteDatabasePractice(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("databaseLog", DATABASE_CREATE);
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DataPractice");
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
