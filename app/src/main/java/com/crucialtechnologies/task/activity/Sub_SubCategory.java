package com.crucialtechnologies.task.activity;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crucialtechnologies.task.R;
import com.crucialtechnologies.task.adapter.SubCategory_adapter;
import com.crucialtechnologies.task.adapter.Sub_subcategory_adapter;
import com.crucialtechnologies.task.pogo.sub_category.Subcategory;
import com.crucialtechnologies.task.service.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sub_SubCategory extends AppCompatActivity {
    @BindView(R.id.txt_category)
    TextView txt_category;
    @BindView(R.id.recycler_category)
    RecyclerView recycler_category;

    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sub_category);
        ButterKnife.bind(this);
        txt_category.setText(getIntent().getStringExtra("category")+
                " > "+getIntent().getStringExtra("Subcategory")+
                " > "+getIntent().getStringExtra("name"));
        getCategories(getIntent().getStringExtra("id"));
    }
    private void getCategories(String id) {
        progress_bar.setVisibility(View.VISIBLE);
        Call<Subcategory> call = RetrofitClient.getInstance().getMyApi().getSubcategory(id);
        call.enqueue(new Callback<Subcategory>() {
            @Override
            public void onResponse(Call<Subcategory> call, Response<Subcategory> response) {
                progress_bar.setVisibility(View.GONE);
                Subcategory subcategory = response.body();
                if(subcategory.getSuccess().equalsIgnoreCase("true")){
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
                    recycler_category.setLayoutManager(linearLayoutManager);
                    Sub_subcategory_adapter sub_subcategory_adapter = new Sub_subcategory_adapter(Sub_SubCategory.this,subcategory.getServices() );
                    recycler_category.setAdapter(sub_subcategory_adapter); // set the Adapter to RecyclerView
                    recycler_category.addItemDecoration(new DividerItemDecoration(recycler_category.getContext(), DividerItemDecoration.VERTICAL));

                }else{
                    Toast.makeText(getApplicationContext(),"No Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Subcategory> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}