package xsis.com.sqlitedatabasepractice;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import xsis.com.sqlitedatabasepractice.adapter.AdapterDataRecyclerFirebase;
import xsis.com.sqlitedatabasepractice.model.ModelData;
import xsis.com.sqlitedatabasepractice.utility.FirebaseUtilityForPractice;

public class FireBaseActivity extends Activity {

    Context context = this;
    EditText inputNameFB,inputGenderFB,inputAgeFB,inputHeightFB;
//    TextView displayName,displayGender,displayAge,displayHeight;
    Button saveFB,updateFB;
    RecyclerView recyclerViewFB;

    private DatabaseReference databaseReference;
    List<ModelData> listData = new ArrayList<>();
    AdapterDataRecyclerFirebase adapterDataRecyclerFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base);

        inputNameFB = (EditText)findViewById(R.id.inputNameFB);
        inputGenderFB = (EditText)findViewById(R.id.inputGenderFB);
        inputAgeFB = (EditText)findViewById(R.id.inputAgeFB);
        inputHeightFB = (EditText)findViewById(R.id.inputHeightFB);

//        displayName = (TextView)findViewById(R.id.displayNameFB);
//        displayGender = (TextView)findViewById(R.id.displayGenderFB);
//        displayAge = (TextView)findViewById(R.id.displayAgeFB);
//        displayHeight = (TextView)findViewById(R.id.displayHeightFB);

        saveFB = (Button)findViewById(R.id.saveButtonFB);
        updateFB = (Button)findViewById(R.id.updateButtonFB);

        recyclerViewFB = (RecyclerView)findViewById(R.id.recyclerListDataFB);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerViewFB.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference();

//        dataShow(listData);
        dataShowRecycler();
        pushedButtons();

    }

    private void pushedButtons(){
        saveFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputAgeFB.getText().toString().isEmpty() ||
                        inputGenderFB.getText().toString().isEmpty() ||
                        inputHeightFB.getText().toString().isEmpty() ||
                        inputNameFB.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Isi Seluruh isian Form", Toast.LENGTH_SHORT).show();

                }
                else {
                    dataCreateToFirebase(Integer.parseInt(inputAgeFB.getText().toString()),
                            inputGenderFB.getText().toString(),
                            Integer.parseInt(inputHeightFB.getText().toString()),
                            inputNameFB.getText().toString());

                    inputNameFB.setText("");
                    inputAgeFB.setText("");
                    inputGenderFB.setText("");
                    inputHeightFB.setText("");

                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void dataCreateToFirebase(final int age, final String gender, final int height, final String name){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.getChildrenCount()>0){
                    long temp=dataSnapshot.getChildren().iterator().next().getChildren().iterator().next().getChildrenCount();
                    Log.i("tempVal",temp+"");

                    if(temp>0){
                        final ModelData tempData = new ModelData(name,gender,height,age,Integer.parseInt(temp+""));
                        databaseReference.child(FirebaseUtilityForPractice.ROOT_FIRST_CHILD)
                                .child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
                                .child("model"+temp).setValue(tempData);
                    }
                }
                else{
                    final ModelData tempData = new ModelData(name,gender,height,age,0);
                    databaseReference.child(FirebaseUtilityForPractice.ROOT_FIRST_CHILD)
                            .child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
                            .child("model"+0).setValue(tempData);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        databaseReference.child(FirebaseUtilityForPractice.ROOT_FIRST_CHILD)
//                .child(FirebaseUtilityForPractice.DATA_CHILD_ROOT).setValue(tempData);
    }

    //ga pake recycler
    private void dataShow(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.i("tagFirebase",dataSnapshot.getChildren().iterator().next().getChildren().iterator().next().getKey().toString());

//                displayAge.setText(dataSnapshot.getChildren().iterator().next()
//                        .getChildren().iterator().next()
//                        .child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
//                        .child(FirebaseUtilityForPractice.DATA_AGE).getValue().toString());
//
//                displayGender.setText(dataSnapshot.getChildren().iterator().next().getChildren().iterator().next()
//                        .child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
//                        .child(FirebaseUtilityForPractice.DATA_GENDER).getValue().toString());
//
//                displayHeight.setText(dataSnapshot.getChildren().iterator().next().getChildren().iterator().next()
//                        .child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
//                        .child(FirebaseUtilityForPractice.DATA_HEIGHT).getValue().toString());
//
//                displayName.setText(dataSnapshot.getChildren().iterator().next().getChildren().iterator().next()
//                        .child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
//                        .child(FirebaseUtilityForPractice.DATA_NAME).getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        databaseReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                String tempKey = databaseReference.child(FirebaseUtilityForPractice.DATA_CHILD_ROOT).push().getKey();
//                Log.i("tagFirebase",dataSnapshot.getChildren().iterator().next().getKey().toString());
//                displayAge.setText(dataSnapshot.getChildren().iterator().next()
//                        .child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
//                        .child(FirebaseUtilityForPractice.DATA_AGE).getValue().toString());
//                displayGender.setText(dataSnapshot.getChildren().iterator().next()
//                        .child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
//                        .child(FirebaseUtilityForPractice.DATA_GENDER).getValue().toString());
//                displayHeight.setText(dataSnapshot.getChildren().iterator().next()
//                        .child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
//                        .child(FirebaseUtilityForPractice.DATA_HEIGHT).getValue().toString());
//                displayName.setText(dataSnapshot.getChildren().iterator().next()
//                        .child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
//                        .child(FirebaseUtilityForPractice.DATA_NAME).getValue().toString());
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


    }

    //pake recycler
    private void dataShowRecycler(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.i("tagFirebase",dataSnapshot.getChildren().iterator().next().getChildren().iterator().next().getKey().toString());

                if(dataSnapshot.getChildrenCount()>0){
                    if(dataSnapshot.getChildren().iterator().next().getChildrenCount()<=0){
                        recyclerViewFB.setVisibility(View.INVISIBLE);
                    }
                    else{
                        recyclerViewFB.setVisibility(View.VISIBLE);
                        DataSnapshot result = dataSnapshot.getChildren().iterator().next();

                        Log.e("resultData",result.getKey()+"");

                        List<ModelData> tempListData = new ArrayList<>();

                        for (int i=0;i<result.getChildren().iterator().next().getChildrenCount();i++){
                            Log.e("resultData",i+"");

                            int tempHeight = Integer.parseInt(result.child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
                                    .child("model"+i).child(FirebaseUtilityForPractice.DATA_HEIGHT).getValue()+"");

                            int tempAge = Integer.parseInt(result.child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
                                    .child("model"+i).child(FirebaseUtilityForPractice.DATA_AGE).getValue()+"");

                            int tempID = Integer.parseInt(result.child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
                                    .child("model"+i).child(FirebaseUtilityForPractice.DATA_ID).getValue()+"");

                            String tempName = result.child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
                                    .child("model"+i).child(FirebaseUtilityForPractice.DATA_NAME).getValue()+"";

                            String tempGender = result.child(FirebaseUtilityForPractice.DATA_CHILD_ROOT)
                                    .child("model"+i).child(FirebaseUtilityForPractice.DATA_GENDER).getValue()+"";

                            ModelData tempData = new ModelData(tempName,tempGender,tempHeight,tempAge,tempID);

                            tempListData.add(tempData);

                        }

                        listData = tempListData;

                        adapterDataRecyclerFirebase = new AdapterDataRecyclerFirebase(context,listData);
                        adapterDataRecyclerFirebase.notifyDataSetChanged();

                        recyclerViewFB.setAdapter(adapterDataRecyclerFirebase);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void updateDataRecycler(int id){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
