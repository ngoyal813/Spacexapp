package com.example.spacex.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.spacex.R;
import com.example.spacex.model.crew_model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class crew_adapter extends RecyclerView.Adapter<crew_adapter.viewholder> {

    private List<crew_model> crew_list;
    private Context context;

    public crew_adapter(List<crew_model> crew_list, Context context) {
        this.crew_list = crew_list;
        this.context = context;
    }

    public void setCrew_list(List<crew_model> crew_list) {
        this.crew_list = crew_list;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_layout,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewholder holder, int position) {
        holder.crew_name.setText("NAME : "+this.crew_list.get(position).getName());
        holder.crew_agency.setText("AGENCY : "+this.crew_list.get(position).getAgency());
        holder.crew_status.setText("STATUS : "+this.crew_list.get(position).getStatus());
        Glide.with(context).load(this.crew_list.get(position).getImgae()).into(holder.crew_image);

        holder.crew_wikipedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = crew_list.get(position).getWikipedia_link();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(this.crew_list !=null){
            return this.crew_list.size();
        }
        return 0;
    }

    public class viewholder extends RecyclerView.ViewHolder{
        private ImageView crew_image;
        private TextView crew_name;
        private TextView crew_agency;
        private TextView crew_wikipedia;
        private TextView crew_status;

        public viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            crew_image = itemView.findViewById(R.id.crew_image);
            crew_name = itemView.findViewById(R.id.crew_name);
            crew_agency = itemView.findViewById(R.id.crew_agency);
            crew_wikipedia = itemView.findViewById(R.id.crew_wikipedia);
            crew_status = itemView.findViewById(R.id.crew_status);
        }
    }
}
