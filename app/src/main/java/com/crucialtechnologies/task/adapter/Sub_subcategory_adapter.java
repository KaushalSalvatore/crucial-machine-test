package com.crucialtechnologies.task.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.crucialtechnologies.task.R;
import com.crucialtechnologies.task.pogo.sub_category.Service;

import java.util.List;

public class Sub_subcategory_adapter extends RecyclerView.Adapter<Sub_subcategory_adapter.ViewHolder>{
    private Context context;
    private List<Service> services;


    // RecyclerView recyclerView;
    public Sub_subcategory_adapter(Context context, List<Service> services) {
        this.context = context;
        this.services = services;


    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.sub_subcategory_adapter_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Service service = services.get(position);
        holder.txt_categoryItem.setText(service.getServiceName());
    }


    @Override
    public int getItemCount() {
        return services.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_categoryItem;
        public ViewHolder(View itemView) {
            super(itemView);
            txt_categoryItem = itemView.findViewById(R.id.tv_subcategoryname);
        }
    }
}
