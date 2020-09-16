package com.demosample.demorizzle.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.demosample.demorizzle.R;
import com.demosample.demorizzle.model.DisplayModel;
import com.demosample.demorizzle.model.NodeModel;

import java.util.List;

public class MyDisplayAdapter extends RecyclerView.Adapter<MyDisplayAdapter.MyViewHolder> {

    private Context context;
    private List<DisplayModel> allList;
    private List<NodeModel> celebritityContent;
    private int counter = 0;

    public MyDisplayAdapter(Context context, List<DisplayModel> allList, List<NodeModel> celebritityContent) {
        this.context = context;
        this.allList = allList;
        this.celebritityContent = celebritityContent;
    }

    @NonNull
    @Override
    public MyDisplayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inside_layoutfile, parent, false);
        return new MyViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDisplayAdapter.MyViewHolder holder, final int position) {
        holder.allCOntent.setText(allList.get(position).getName());
        Glide
                .with(context)
                .load(allList.get(position).getImg())
                .error(R.drawable.ic_no_connection)
                .thumbnail(.1f)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
        if (position % 2 != 0) {
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create(); //Read Update
                    alertDialog.setTitle("Total Worth");
                    alertDialog.setMessage(celebritityContent.get(counter).getNetWorth());
                    counter++;
                    alertDialog.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return allList.size();
    }

    public class
    MyViewHolder extends RecyclerView.ViewHolder {

        private TextView allCOntent;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            allCOntent = itemView.findViewById(R.id.demoText);
            img = itemView.findViewById(R.id.image);
        }
    }
}
