package com.crucialtechnologies.task.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.crucialtechnologies.task.MainActivity;
import com.crucialtechnologies.task.R;
import com.crucialtechnologies.task.adapter.Category_adapter;
import com.crucialtechnologies.task.pogo.OtherService;
import com.crucialtechnologies.task.pogo.Service;
import com.crucialtechnologies.task.pogo.Service__1;
import com.crucialtechnologies.task.pogo.Success;
import com.crucialtechnologies.task.service.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category extends AppCompatActivity implements Category_adapter.categoryItemCLick {

    @BindView(R.id.image_select_category)
    ImageView image_select_category;
    @BindView(R.id.txt_category)
    TextView txt_category;
    @BindView(R.id.recycler_category)
    RecyclerView recycler_category;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;
    String get_1,get_2,getOther;

    private ArrayList<Service> tranding_services = new ArrayList<Service>();
    private ArrayList<Service> other_services = new ArrayList<Service>();
    private ArrayList<Service> most_services = new ArrayList<Service>();

    Category_adapter category_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        getCategories();
    }
    @OnClick(R.id.image_select_category)
    public void selectCategory()
    {showCategoriesDialog();}

    private void getCategories() {
        progress_bar.setVisibility(View.VISIBLE);
        Call<Success> call = RetrofitClient.getInstance().getMyApi().getcategories();
        call.enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                progress_bar.setVisibility(View.GONE);
                Success getegorylist = response.body();
                if(getegorylist.getSuccess().equalsIgnoreCase("true")){
                    get_1=getegorylist.getServices().get_1().getTitle();
                    get_2=getegorylist.getServices().get_2().getTitle();
                    getOther=getegorylist.getServices().getOther().getTitle();
                    txt_category.setText(getegorylist.getServices().get_1().getTitle());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3, LinearLayoutManager.VERTICAL,false);
                    recycler_category.setLayoutManager(gridLayoutManager);
                    most_services = (ArrayList<Service>) getegorylist.getServices().get_1().getService();
                    tranding_services = (ArrayList<Service>) getegorylist.getServices().get_2().getServices();
                    other_services = (ArrayList<Service>) getegorylist.getServices().getOther().getOtherServices();

                    category_adapter = new Category_adapter(Category.this, most_services,Category.this);
                    recycler_category.setAdapter(category_adapter); // set the Adapter to RecyclerView
                }else{
                    Toast.makeText(getApplicationContext(),"No Data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showCategoriesDialog(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.categories_dialog_layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();
        TextView txt_category_1 = dialog.findViewById(R.id.txt_category_1);
        TextView txt_category_2 = dialog.findViewById(R.id.txt_category_2);
        TextView txt_category_other= dialog.findViewById(R.id.txt_category_other);
        txt_category_1.setText(get_1);
        txt_category_2.setText(get_2);
        txt_category_other.setText(getOther);

        txt_category_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_category.setText(get_1);
                dialog.dismiss();
                category_adapter.notifyList(most_services);
            }
        });

        txt_category_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_category.setText(get_2);
                dialog.dismiss();
                category_adapter.notifyList(tranding_services);
            }
        });

        txt_category_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_category.setText(getOther);
                dialog.dismiss();
                category_adapter.notifyList(other_services);
            }
        });


    }

    @Override
    public void categoryItemCLick(String name,String id) {
        Intent i = new Intent(Category.this, Sub_Category.class);
        i.putExtra("name", name);
        i.putExtra("id", id);
        i.putExtra("category", txt_category.getText().toString());
        startActivity(i);

    }
}