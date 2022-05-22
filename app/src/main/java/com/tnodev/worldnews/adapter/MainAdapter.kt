package com.tnodev.worldnews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tnodev.worldnews.databinding.NewsRecyclerItemBinding
import com.tnodev.worldnews.model.Article


class MainAdapter:
    RecyclerView.Adapter<MainViewHolder>() {

    var newsList = mutableListOf<Article>();

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
       val inflater = LayoutInflater.from(parent.context);
        val binding = NewsRecyclerItemBinding.inflate(inflater,parent,false)

        return  MainViewHolder(binding);
    }
fun setNewsItems(newsList:List<Article>){

    this.newsList = newsList.toMutableList();
    notifyDataSetChanged();
}
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val movie = newsList[position];



   holder.binding.newsTxt.setText(movie.title);
        Glide.with(
            holder.itemView.context)
            .load(newsList[position].urlToImage)
            .into(holder.binding.newsImage);

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(newsList[position]) } // Make ViewHolder clickable
        }
    }

    override fun getItemCount(): Int {
     return  newsList.size;
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}

class  MainViewHolder(val binding:NewsRecyclerItemBinding): RecyclerView.ViewHolder(binding.root){


}