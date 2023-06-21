package com.crucialtechnologies.task.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.crucialtechnologies.task.R;
import com.crucialtechnologies.task.pogo.Service;

import java.util.List;

public class Category_adapter extends RecyclerView.Adapter<Category_adapter.ViewHolder>{
    private Context context;
    private List<Service> services;
    private categoryItemCLick categoryItemCLick;

    public Category_adapter(Context context, List<Service> services,categoryItemCLick categoryItemCLick) {
        this.context=context;
        this.services=services;
        this.categoryItemCLick=categoryItemCLick;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.category_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Service services1 = services.get(position);
        holder.txt_categoryItem.setText(services1.getServiceName());
       holder.relative_technician.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               categoryItemCLick.categoryItemCLick(services1.getServiceName(),services1.getServiceId());
           }
       });



    }


    @Override
    public int getItemCount() {
        return services.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relative_technician;
        ImageView iv_technician;
        TextView txt_categoryItem;

        public ViewHolder(View itemView) {
            super(itemView);
            relative_technician = itemView.findViewById(R.id.relative_technician);
            iv_technician = itemView.findViewById(R.id.iv_technician);
            txt_categoryItem = itemView.findViewById(R.id.txt_categoryItem);

        }
    }

    public interface categoryItemCLick{
        void categoryItemCLick(String name,int id);

    }
}
