package com.crucialtechnologies.task.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.crucialtechnologies.task.R;
import com.crucialtechnologies.task.pogo.Service;
import com.crucialtechnologies.task.pogo.customer_record.Job;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class Joblist_adapter extends RecyclerView.Adapter<Joblist_adapter.ViewHolder> {
    private Context context;
    private List<Job> job;

    public Joblist_adapter(Context context,List<Job> job) {
        this.context=context;
        this.job=job;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.joblist_item, parent, false);
        Joblist_adapter.ViewHolder viewHolder = new Joblist_adapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Job joblist = job.get(position);
        if(joblist.getCustomerName() != null && !joblist.getCustomerName().equalsIgnoreCase("")){
            holder.txt_cusotmerName.setText("Name: " +joblist.getCustomerName());
        }
        if(joblist.getStatusTitle() != null && !joblist.getStatusTitle().equalsIgnoreCase("")){
            holder.txt_statustitle.setText("Title: " +joblist.getStatusTitle());
        }
        if(joblist.getServicesNames() != null && !joblist.getServicesNames().equalsIgnoreCase("")){
            holder.txt_servicesnames.setText("Service: " +joblist.getServicesNames());
        }
        if(joblist.getBookingAddressByGoogle() != null && !joblist.getBookingAddressByGoogle().equalsIgnoreCase("")){
            holder.txt_bookingaddress.setText("Address: " +joblist.getBookingAddressByGoogle());
        }
        if(joblist.getCustomerImage() != null && !joblist.getCustomerImage().equalsIgnoreCase("")){
            Glide.with(context)
                    .load(Uri.parse(joblist.getCustomerImage()))
                    .into(holder.customer_image);
        }

    }

    @Override
    public int getItemCount() {
        return job.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView customer_image;
        TextView txt_cusotmerName,txt_statustitle,txt_servicesnames,txt_bookingaddress;

        public ViewHolder(View itemView) {
            super(itemView);
            customer_image = itemView.findViewById(R.id.customer_image);
            txt_cusotmerName = itemView.findViewById(R.id.txt_cusotmerName);
            txt_statustitle = itemView.findViewById(R.id.txt_statustitle);
            txt_servicesnames = itemView.findViewById(R.id.txt_servicesnames);
            txt_bookingaddress = itemView.findViewById(R.id.txt_bookingaddress);
        }
    }
}
