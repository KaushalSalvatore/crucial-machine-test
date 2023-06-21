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
import com.crucialtechnologies.task.service.RetrofitClient;

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

    @BindView(R.id.idNestedSV)
    NestedScrollView nestedSV;
    int  scrollPossition = 1;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;
    LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);
        ButterKnife.bind(this);
        jobList(24,scrollPossition);
//        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                // on scroll change we are checking when users scroll as bottom.
//                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
//                    scrollPossition++;
//                        jobList(24,scrollPossition);
//
//                }
//            }
//        });

//        recycler_joblist.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//
//                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
//                {
//                    isScrolling = true;
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                currentItems = manager.getChildCount();
//                totalItems = manager.getItemCount();
//                scrollOutItems = manager.findFirstVisibleItemPosition();
//
//                if(isScrolling && (currentItems + scrollOutItems == totalItems))
//                {
//                    isScrolling = false;
//                    scrollPossition++;
//                    jobList( 24,scrollPossition);
//                }
//            }
//        });

//        jobList(24,scrollPossition);
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
                    Joblist_adapter joblist_adapter = new Joblist_adapter(JobList.this,CustomerRecords.getJobList());
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