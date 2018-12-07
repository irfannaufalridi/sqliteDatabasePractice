package xsis.com.sqlitedatabasepractice.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import xsis.com.sqlitedatabasepractice.MainActivity;
import xsis.com.sqlitedatabasepractice.R;
import xsis.com.sqlitedatabasepractice.model.ModelData;
import xsis.com.sqlitedatabasepractice.sqlitedb.MyDB;

/**
 * Created by Irfan Naufal Ridi on 07/11/2018.
 */

public class ViewHolderRecyclerMain extends RecyclerView.ViewHolder {

    TextView nameText, ageText, genderText, heightText;
    public static TextView editTextClickable, deleteTextClickable;
    Context context;

    public ViewHolderRecyclerMain(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();

        nameText = (TextView) itemView.findViewById(R.id.recyclerName);
        ageText = (TextView) itemView.findViewById(R.id.recyclerAge);
        genderText = (TextView) itemView.findViewById(R.id.recyclerGender);
        heightText = (TextView) itemView.findViewById(R.id.recyclerHeight);
        editTextClickable = (TextView) itemView.findViewById(R.id.recyclerEditTextClickable);
        deleteTextClickable = (TextView) itemView.findViewById(R.id.recyclerDeleteTextClickable);
        deleteTextClickable.setClickable(true);
        editTextClickable.setClickable(true);

    }

    public void setModelData(ModelData data) {
        nameText.setText(data.getName());
        ageText.setText(data.getAge() + "");
        genderText.setText(data.getGender());
        heightText.setText(data.getHeight() + "");
    }

    public void deleteEditClicked(final List<ModelData> list, final int id, final ModelData data) {
        deleteTextClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.deleteRecordsFromList(list, id);
            }
        });

        editTextClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.editRecordsFromList(data);
            }
        });
    }
}
