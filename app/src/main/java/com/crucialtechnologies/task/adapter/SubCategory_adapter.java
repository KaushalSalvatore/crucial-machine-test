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
import com.crucialtechnologies.task.pogo.sub_category.Service;

import java.util.List;


public class SubCategory_adapter extends RecyclerView.Adapter<SubCategory_adapter.ViewHolder>{

    private Context context;
    private List<Service> services;
    private subCategoryItemCLick subCategoryItemCLick;


    // RecyclerView recyclerView;
    public SubCategory_adapter(Context context, List<Service> services, subCategoryItemCLick subCategoryItemCLick) {
        this.context = context;
        this.services = services;
        this.subCategoryItemCLick = subCategoryItemCLick;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.subcategory_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Service service = services.get(position);
        holder.txt_categoryItem.setText(service.getServiceName());
        holder.relative_technician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subCategoryItemCLick.subCategoryItemCLick(service.getServiceName(),service.getServiceId());
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
    public interface subCategoryItemCLick{
        void subCategoryItemCLick(String name,int id);

    }
}
