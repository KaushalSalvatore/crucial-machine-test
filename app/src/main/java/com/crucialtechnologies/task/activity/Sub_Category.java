package com.crucialtechnologies.task.activity;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crucialtechnologies.task.MainActivity;
import com.crucialtechnologies.task.R;
import com.crucialtechnologies.task.adapter.Category_adapter;
import com.crucialtechnologies.task.adapter.SubCategory_adapter;
import com.crucialtechnologies.task.pogo.Success;
import com.crucialtechnologies.task.pogo.sub_category.Subcategory;
import com.crucialtechnologies.task.service.RetrofitClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sub_Category extends AppCompatActivity implements SubCategory_adapter.subCategoryItemCLick {
    @BindView(R.id.txt_searchCatgory)
    EditText txt_searchCatgory;
    @BindView(R.id.txt_category)
    TextView txt_category;
    @BindView(R.id.recycler_category)
    RecyclerView recycler_category;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    String category ="";
    String Subcategory ="";
    String id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        ButterKnife.bind(this);
        category = getIntent().getStringExtra("category");
        Subcategory = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");
        Log.e(TAG, "onCreate: "+id+""+Subcategory+""+category );
        txt_category.setText(getIntent().getStringExtra("category")+ " > "+getIntent().getStringExtra("name"));
        getCategories();

    }
    private void getCategories() {
        progress_bar.setVisibility(View.VISIBLE);
        Call<Subcategory> call = RetrofitClient.getInstance().getMyApi().getSubcategory(id);
        call.enqueue(new Callback<Subcategory>() {
            @Override
            public void onResponse(Call<Subcategory> call, Response<Subcategory> response) {
                progress_bar.setVisibility(View.GONE);
                Subcategory subcategory = response.body();
                if(subcategory.getSuccess().equalsIgnoreCase("true")){
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3, LinearLayoutManager.VERTICAL,false);
                    recycler_category.setLayoutManager(gridLayoutManager);
                    SubCategory_adapter category_adapter = new SubCategory_adapter(Sub_Category.this,subcategory.getServices() ,Sub_Category.this);
                    recycler_category.setAdapter(category_adapter); // set the Adapter to RecyclerView
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

    @Override
    public void subCategoryItemCLick(String name, String id) {
        Intent i = new Intent(Sub_Category.this, Sub_SubCategory.class);
        i.putExtra("category", category);
        i.putExtra("Subcategory", Subcategory);
        i.putExtra("name", name);
        i.putExtra("id", id);
        startActivity(i);
    }
}