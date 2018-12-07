package xsis.com.sqlitedatabasepractice.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xsis.com.sqlitedatabasepractice.R;
import xsis.com.sqlitedatabasepractice.model.ModelData;
import xsis.com.sqlitedatabasepractice.viewholder.ViewHolderRecyclerFirebase;
import xsis.com.sqlitedatabasepractice.viewholder.ViewHolderRecyclerMain;

/**
 * Created by Irfan Naufal Ridi on 07/11/2018.
 */

public class AdapterDataRecyclerFirebase extends RecyclerView.Adapter<ViewHolderRecyclerFirebase> {

    Context context;
    List<ModelData> modelData;

    public AdapterDataRecyclerFirebase(Context context, List<ModelData> modelData) {
        this.context = context;
        this.modelData = modelData;
    }

    @NonNull
    @Override
    public ViewHolderRecyclerFirebase onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_recycler_list_firebase, viewGroup, false);
        return new ViewHolderRecyclerFirebase(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecyclerFirebase viewHolderRecyclerFirebase, int i) {
        final ModelData tempData = modelData.get(i);
        viewHolderRecyclerFirebase.setModelData(tempData);
    }

    @Override
    public int getItemCount() {
        return modelData.size();
    }


}
