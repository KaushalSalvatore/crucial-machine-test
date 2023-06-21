package com.crucialtechnologies.task.activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.crucialtechnologies.task.R;
import com.crucialtechnologies.task.adapter.Joblist_adapter;
import com.crucialtechnologies.task.pogo.customer_record.CustomerRecords;
import com.crucialtechnologies.task.pogo.customer_record.Job;
import com.crucialtechnologies.task.service.EndlessRecyclerOnScrollListener;
import com.crucialtechnologies.task.service.RetrofitClient;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobList extends AppCompatActivity {
    @BindView(R.id.recycler_joblist)
    RecyclerView recycler_joblist;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    int  scrollPossition = 1;
    boolean isLoading = false;

    ArrayList<Job> rowsArrayList = new ArrayList<>();
    Joblist_adapter joblist_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);
        ButterKnife.bind(this);
        jobList(24,scrollPossition);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_joblist.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                Log.e("TAG", "onLoadMore: "+current_page );
            }
        });




    }
    private void jobList(int perpagecount,int count) {
        progress_bar.setVisibility(View.VISIBLE);
        Call<CustomerRecords> call = RetrofitClient.getInstance().getMyApi().getJobList(perpagecount,count);
        call.enqueue(new Callback<CustomerRecords>() {
            @Override
            public void onResponse(Call<CustomerRecords> call, Response<CustomerRecords> response) {
                progress_bar.setVisibility(View.GONE);
                CustomerRecords CustomerRecords = response.body();
                if(CustomerRecords.getSuccess().equalsIgnoreCase("true") && CustomerRecords.getMessage().equalsIgnoreCase("Record found")){
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
                    recycler_joblist.setLayoutManager(linearLayoutManager);
                    rowsArrayList = (ArrayList<Job>) CustomerRecords.getJobList();
                    joblist_adapter = new Joblist_adapter(JobList.this,rowsArrayList);
                    recycler_joblist.setAdapter(joblist_adapter); // set the Adapter to RecyclerView
                }else{
                    Toast.makeText(getApplicationContext(),"No Data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CustomerRecords  > call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}