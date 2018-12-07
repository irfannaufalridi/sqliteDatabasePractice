package xsis.com.sqlitedatabasepractice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xsis.com.sqlitedatabasepractice.MainActivity;
import xsis.com.sqlitedatabasepractice.R;
import xsis.com.sqlitedatabasepractice.model.ModelData;
import xsis.com.sqlitedatabasepractice.sqlitedb.MyDB;
import xsis.com.sqlitedatabasepractice.viewholder.ViewHolderRecyclerMain;

/**
 * Created by Irfan Naufal Ridi on 07/11/2018.
 */

public class AdapterDataRecyclerMain extends RecyclerView.Adapter<ViewHolderRecyclerMain> {

    Context context;
    List<ModelData> modelData;

    public AdapterDataRecyclerMain(Context context, List<ModelData> modelData) {
        this.context = context;
        this.modelData = modelData;
    }

    @NonNull
    @Override
    public ViewHolderRecyclerMain onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recycler_list, viewGroup, false);
        return new ViewHolderRecyclerMain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecyclerMain viewHolderRecyclerMain, int i) {
        final ModelData tempData = modelData.get(i);
        viewHolderRecyclerMain.setModelData(tempData);
        viewHolderRecyclerMain.deleteEditClicked(modelData, modelData.get(i).getId(), tempData);
    }

    @Override
    public int getItemCount() {
        return modelData.size();
    }


}
