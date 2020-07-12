package com.example.androishaper.volleyrecyclerviewapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import android.app.ProgressDialog;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    String url="https://next.json-generator.com/api/json/get/NJzFSDgPu";
      String url="https://next.json-generator.com/api/json/get/4knO1PKwO";

    RecyclerView recyclerView;
    private List<MyItem> listItem;
    MyAdapter myAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recylerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItem=new ArrayList<>();
        LoadData();

        //swipe program
//        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    public void LoadData()
    {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loooding...");
        progressDialog.show();

        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
               try {
                   JSONObject jsonObject=new JSONObject(response);
                   JSONArray jsonArray=jsonObject.getJSONArray("MyData");
                   for(int i=0;i<jsonArray.length();i++)
                   {
                       JSONObject rcive=jsonArray.getJSONObject(i);
                       MyItem Item=new MyItem(rcive.getString("headText"),
                               rcive.getString("descText"),
                               rcive.getString("imageLocation")
                               );
                       listItem.add(Item);
                   }
                   myAdapter=new MyAdapter(listItem,getApplicationContext());

                   recyclerView.setAdapter(myAdapter);

               }catch (JSONException e)
               {
                   e.printStackTrace();
               }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this,"Server error",Toast.LENGTH_SHORT).show();
            }
        }


        );
        RequestQueue quque= Volley.newRequestQueue(this);
        quque.add(stringRequest);

    }

    //swipe program

//    ItemTouchHelper.SimpleCallback simpleCallback= new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
//        @Override
//        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//            return false;
//        }
//
//        @Override
//        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//            final int position=viewHolder.getAdapterPosition();
//
//            switch (direction){
//                case ItemTouchHelper.LEFT:
//                   final String deletDesc=listItem.get(position).getDesc();
//                    final String deletHead= listItem.get(position).getHead();
//                    final String deletImage=listItem.get(position).getImageUrl();
//
//                    myAdapter.notifyItemRemoved(position);
//                    Toast.makeText(MainActivity.this,deletHead,Toast.LENGTH_SHORT).show();
//                    final MyItem item=new MyItem(deletHead,deletDesc,deletImage);
//                    //Undo Item
//                    Snackbar.make(recyclerView, deletHead,Snackbar.LENGTH_LONG)
//                            .setAction("undo", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    listItem.add(position,item);
//                                    myAdapter.notifyItemInserted(position);
//
//
//                                }
//                            }).show();
//                    listItem.remove(position);
//                    break;
//                case ItemTouchHelper.RIGHT:
//                    break;
//            }
//
//        }
//
//        @Override
//        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
//                    .addBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent))
//                    .addActionIcon(R.drawable.ic_baseline_delete_24)
//                    .create()
//                    .decorate();
//            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//        }
//    };


}
