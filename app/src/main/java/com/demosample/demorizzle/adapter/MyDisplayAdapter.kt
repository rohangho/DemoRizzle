package com.demosample.demorizzle.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.demosample.demorizzle.R
import com.demosample.demorizzle.adapter.MyDisplayAdapter.MyViewHolder
import com.demosample.demorizzle.model.DisplayModel
import com.demosample.demorizzle.model.NodeModel

class MyDisplayAdapter(
    private val context: Context,
    private val allList: List<DisplayModel>,
    private val celebritityContent: List<NodeModel>
) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.inside_layoutfile, parent, false)
        return MyViewHolder(v, context)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.allCOntent.text = allList[position].name
        Glide
            .with(context)
            .load(allList[position].img).addListener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }
            })
            .error(R.drawable.ic_no_connection)
            .thumbnail(.1f)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.img)
    }

    override fun getItemCount(): Int {
        return allList.size
    }

    inner class MyViewHolder(itemView: View, context: Context?) : ViewHolder(itemView),
        View.OnClickListener {
        val allCOntent: TextView
        val img: ImageView
        val progressBar: ProgressBar
        override fun onClick(v: View) {
            val adapterposition = adapterPosition
            if (adapterposition % 2 != 0) {
                val alertDialog = AlertDialog.Builder(
                    context
                ).create() //Read Update
                alertDialog.setTitle("Total Worth")
                alertDialog.setMessage(celebritityContent[(adapterPosition - 1) / 2].netWorth)
                alertDialog.show()
            }
        }

        init {
            allCOntent = itemView.findViewById(R.id.demoText)
            img = itemView.findViewById(R.id.image)
            progressBar = itemView.findViewById(R.id.progress)
            itemView.setOnClickListener(this)
        }
    }
}