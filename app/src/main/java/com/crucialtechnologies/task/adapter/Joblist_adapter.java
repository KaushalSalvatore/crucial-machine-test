package com.crucialtechnologies.task.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crucialtechnologies.task.R;
import com.crucialtechnologies.task.pogo.customer_record.Job;

import java.util.List;

public class Joblist_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Job> job;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;


    public Joblist_adapter(Context context,List<Job> job) {
        this.context=context;
        this.job=job;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joblist_item, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof ItemViewHolder) {

            populateItemRows((ItemViewHolder) viewHolder, position);
        } else if (viewHolder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) viewHolder, position);
        }

    }
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View listItem= layoutInflater.inflate(R.layout.joblist_item, parent, false);
//        Joblist_adapter.ViewHolder viewHolder = new Joblist_adapter.ViewHolder(listItem);
//        return viewHolder;
//    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Job joblist = job.get(position);
//        if(joblist.getCustomerName() != null && !joblist.getCustomerName().equalsIgnoreCase("")){
//            holder.txt_cusotmerName.setText("Name: " +joblist.getCustomerName());
//        }
//        if(joblist.getStatusTitle() != null && !joblist.getStatusTitle().equalsIgnoreCase("")){
//            holder.txt_statustitle.setText("Title: " +joblist.getStatusTitle());
//        }
//        if(joblist.getServicesNames() != null && !joblist.getServicesNames().equalsIgnoreCase("")){
//            holder.txt_servicesnames.setText("Service: " +joblist.getServicesNames());
//        }
//        if(joblist.getBookingAddressByGoogle() != null && !joblist.getBookingAddressByGoogle().equalsIgnoreCase("")){
//            holder.txt_bookingaddress.setText("Address: " +joblist.getBookingAddressByGoogle());
//        }
//        if(joblist.getCustomerImage() != null && !joblist.getCustomerImage().equalsIgnoreCase("")){
//            Glide.with(context)
//                    .load(Uri.parse(joblist.getCustomerImage()))
//                    .into(holder.customer_image);
//        }
//
//    }

    @Override
    public int getItemCount() {
        return job == null ? 0 : job.size();
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView customer_image;
        TextView txt_cusotmerName,txt_statustitle,txt_servicesnames,txt_bookingaddress;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_image = itemView.findViewById(R.id.customer_image);
            txt_cusotmerName = itemView.findViewById(R.id.txt_cusotmerName);
            txt_statustitle = itemView.findViewById(R.id.txt_statustitle);
            txt_servicesnames = itemView.findViewById(R.id.txt_servicesnames);
            txt_bookingaddress = itemView.findViewById(R.id.txt_bookingaddress);

        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) {
        Job joblist = job.get(position);
        if(joblist.getCustomerName() != null && !joblist.getCustomerName().equalsIgnoreCase("")){
            viewHolder.txt_cusotmerName.setText("Name: " +joblist.getCustomerName());
        }
        if(joblist.getStatusTitle() != null && !joblist.getStatusTitle().equalsIgnoreCase("")){
            viewHolder.txt_statustitle.setText("Title: " +joblist.getStatusTitle());
        }
        if(joblist.getServicesNames() != null && !joblist.getServicesNames().equalsIgnoreCase("")){
            viewHolder.txt_servicesnames.setText("Service: " +joblist.getServicesNames());
        }
        if(joblist.getBookingAddressByGoogle() != null && !joblist.getBookingAddressByGoogle().equalsIgnoreCase("")){
            viewHolder.txt_bookingaddress.setText("Address: " +joblist.getBookingAddressByGoogle());
        }
        if(joblist.getCustomerImage() != null && !joblist.getCustomerImage().equalsIgnoreCase("")){
            Glide.with(context)
                    .load(Uri.parse(joblist.getCustomerImage()))
                    .into(viewHolder.customer_image);
        }


    }

}
