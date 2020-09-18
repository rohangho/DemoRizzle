package com.demosample.demorizzle.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.demosample.demorizzle.R;
import com.demosample.demorizzle.model.DisplayModel;
import com.demosample.demorizzle.model.NodeModel;

import java.util.List;

public class MyDisplayAdapter extends RecyclerView.Adapter<MyDisplayAdapter.MyViewHolder> {

    private Context context;
    private List<DisplayModel> allList;
    private List<NodeModel> celebritityContent;

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
    public void onBindViewHolder(@NonNull final MyDisplayAdapter.MyViewHolder holder, final int position) {
        holder.allCOntent.setText(allList.get(position).getName());
        Glide
                .with(context)
                .load(allList.get(position).getImg()).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        })
                .error(R.drawable.ic_no_connection)
                .thumbnail(.1f)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return allList.size();
    }

    public class
    MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView allCOntent;
        private ImageView img;
        private ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            allCOntent = itemView.findViewById(R.id.demoText);
            img = itemView.findViewById(R.id.image);
            progressBar = itemView.findViewById(R.id.progress);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterposition = getAdapterPosition();
            if (adapterposition % 2 != 0) {
                AlertDialog alertDialog = new AlertDialog.Builder(context).create(); //Read Update
                alertDialog.setTitle("Total Worth");
                alertDialog.setMessage(celebritityContent.get((getAdapterPosition() - 1) / 2).getNetWorth());

                alertDialog.show();
            }
        }
    }
}
