package com.crucialtechnologies.task;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.crucialtechnologies.task.activity.Category;
import com.crucialtechnologies.task.activity.JobList;
import com.crucialtechnologies.task.activity.LazyLoading;
import com.crucialtechnologies.task.activity.MapDirection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {
//    @BindView(R.id.txt_lazyload)
//    TextView txt_lazyload;
//    @BindView(R.id.txt_categories)
//    TextView txt_categories;
//    @BindView(R.id.txt_location)
//    TextView txt_location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.txt_lazyload)
    public void LazyLoading()
    {
        Intent i = new Intent(MainActivity.this, JobList.class);
        startActivity(i);

    }
    @OnClick(R.id.txt_categories)
    public void Category()
    {
        Intent i = new Intent(MainActivity.this, Category.class);
        startActivity(i);

    }
    @OnClick(R.id.txt_location)
    public void MapDirection()
    {
        Intent i = new Intent(MainActivity.this, MapDirection.class);
        startActivity(i);

    }


}