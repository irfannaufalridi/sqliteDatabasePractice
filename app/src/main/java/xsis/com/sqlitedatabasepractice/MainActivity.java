package xsis.com.sqlitedatabasepractice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xsis.com.sqlitedatabasepractice.adapter.AdapterDataRecyclerMain;
import xsis.com.sqlitedatabasepractice.model.ModelData;
import xsis.com.sqlitedatabasepractice.sqlitedb.MyDB;
import xsis.com.sqlitedatabasepractice.sqlitedb.SqliteDatabasePractice;
import xsis.com.sqlitedatabasepractice.viewholder.ViewHolderRecyclerMain;

public class MainActivity extends Activity {

    static EditText inputName;
    static EditText inputAge;
    static EditText inputGender;
    static EditText inputHeight;
    Button saveButton, showButton, updateButton,fbButton;
    public static MyDB dbPractice;
    Context context = this;
    RecyclerView recyclerView;

    public static AdapterDataRecyclerMain adapterDataRecyclerMain;
    public List<ModelData> listData = new ArrayList<>();
    public static ModelData tempDataEdit = new ModelData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbPractice = new MyDB(context);

        inputName = (EditText) findViewById(R.id.inputName);
        inputAge = (EditText) findViewById(R.id.inputAge);
        inputGender = (EditText) findViewById(R.id.inputGender);
        inputHeight = (EditText) findViewById(R.id.inputHeight);
        saveButton = (Button) findViewById(R.id.buttonCreate);
        showButton = (Button) findViewById(R.id.buttonShow);
        updateButton = (Button) findViewById(R.id.buttonUpdate);
        fbButton = (Button) findViewById(R.id.buttonFB);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerListData);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        pushedButton();

    }

    public void pushedButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputAge.getText().toString().isEmpty() ||
                        inputGender.getText().toString().isEmpty() ||
                        inputHeight.getText().toString().isEmpty() ||
                        inputName.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Isi Seluruh isian Form", Toast.LENGTH_SHORT).show();
                } else {
                    long tempDBInsert = dbPractice.createRecords(inputName.getText().toString(),
                            Integer.parseInt(inputAge.getText().toString()),
                            inputGender.getText().toString(),
                            Integer.parseInt(inputHeight.getText().toString()));

                    inputName.setText("");
                    inputAge.setText("");
                    inputGender.setText("");
                    inputHeight.setText("");

                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listData = dbPractice.showRecords();

                Log.i("recyclerData", listData.size() + "");

                adapterDataRecyclerMain = new AdapterDataRecyclerMain(context, listData);
                recyclerView.setAdapter(adapterDataRecyclerMain);

                adapterDataRecyclerMain.notifyDataSetChanged();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputAge.getText().toString().isEmpty() ||
                        inputGender.getText().toString().isEmpty() ||
                        inputHeight.getText().toString().isEmpty() ||
                        inputName.getText().toString().isEmpty()) {

                    Toast.makeText(context, "Isi Seluruh isian Form", Toast.LENGTH_SHORT).show();
                } else {
                    updateDataList();
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FireBaseActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void updateDataList() {

        long deleted = dbPractice.editRecords(listData, tempDataEdit.getId(),
                inputName.getText().toString(),
                Integer.parseInt(inputAge.getText().toString()),
                inputGender.getText().toString(),
                Integer.parseInt(inputHeight.getText().toString()));

        adapterDataRecyclerMain.notifyDataSetChanged();
    }

    public static void editRecordsFromList(final ModelData list) {
        inputName.setText(list.getName());
        inputAge.setText(list.getAge() + "");
        inputGender.setText(list.getGender());
        inputHeight.setText(list.getHeight() + "");

        tempDataEdit = list;
    }

    public static void deleteRecordsFromList(final List<ModelData> list, final int id) {
        dbPractice.deleteRecords(list, id);
        adapterDataRecyclerMain.notifyDataSetChanged();
    }
}
