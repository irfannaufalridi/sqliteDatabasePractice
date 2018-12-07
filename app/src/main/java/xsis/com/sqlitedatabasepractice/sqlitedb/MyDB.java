package xsis.com.sqlitedatabasepractice.sqlitedb;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Display;

import java.util.ArrayList;
import java.util.List;

import xsis.com.sqlitedatabasepractice.model.ModelData;

/**
 * Created by Irfan Naufal Ridi on 01/11/2018.
 */

public class MyDB {

    private SqliteDatabasePractice dbHelper;

    private SQLiteDatabase database;

    public final static String TABLE_NAME = SqliteDatabasePractice.tb_DataPractice; // name of table

    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String GENDER = "gender";
    public static final String HEIGHT = "height";

    public MyDB(Context context) {
        this.dbHelper = new SqliteDatabasePractice(context);
        this.database = dbHelper.getWritableDatabase();
    }

    public long createRecords(String name, int age, String gender, int height) {
        this.database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, name);
        values.put(AGE, age);
        values.put(GENDER, gender);
        values.put(HEIGHT, height);

//        String queryInsert = "INSERT INTO DataPractice VALUES ("+name+", "+age+", "+gender+", "+height+");";

        return database.insert(TABLE_NAME, null, values);
//        database.execSQL(queryInsert);
    }

    public List<ModelData> showRecords() {
        this.database = dbHelper.getReadableDatabase();
        List<ModelData> dataList = new ArrayList<>();

        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        for (int i = 0; i < cursor.getCount(); i++) {
            ModelData tempData = new ModelData();

//            if (cursor.getCount()>0){
            tempData.setId(Integer.parseInt(cursor.getString(0)));
            tempData.setName(cursor.getString(1));
            tempData.setGender(cursor.getString(2));
            tempData.setAge(Integer.parseInt(cursor.getString(3)));
            tempData.setHeight(Integer.parseInt(cursor.getString(4)));
//            }
            cursor.moveToNext();
            dataList.add(tempData);
        }

        cursor.close();
        return dataList;
    }

    public void deleteRecords(List<ModelData> listOfData, int id) {
        // Define 'where' part of query.
        String selection = SqliteDatabasePractice.key_id + " LIKE ?";

        // Specify arguments in placeholder order.
        String[] selectionArgs = {id + ""};

        // Issue SQL statement.
        int deletedRows = database.delete(SqliteDatabasePractice.tb_DataPractice, selection, selectionArgs);

        for (int i = 0; i < listOfData.size(); i++) {
            if (id == listOfData.get(i).getId()) {
                listOfData.remove(listOfData.get(i));
            }
        }
    }

    public long editRecords(List<ModelData> listOfData, int id, String name, int age, String gender, int height) {
        // Define 'where' part of query.
        String selection = SqliteDatabasePractice.key_id + " LIKE ?";

        // Specify arguments in placeholder order.
        String[] selectionArgs = {id + ""};

        ContentValues values = new ContentValues();
        values.put(NAME, name);
        values.put(AGE, age);
        values.put(GENDER, gender);
        values.put(HEIGHT, height);

        // Issue SQL statement.
        int deletedRows = database.update(SqliteDatabasePractice.tb_DataPractice, values, selection, selectionArgs);
//        Log.i("updateInfo",listOfData.get(id).toString());

        for (int i = 0; i < listOfData.size(); i++) {
            if (id == listOfData.get(i).getId()) {

                listOfData.get(i).setName(name);
                listOfData.get(i).setAge(age);
                listOfData.get(i).setGender(gender);
                listOfData.get(i).setHeight(height);
            }
        }

        return deletedRows;
    }

}
