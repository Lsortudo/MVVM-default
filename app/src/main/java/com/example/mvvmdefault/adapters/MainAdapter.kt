package com.example.mvvmdefault.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmdefault.Models.Photo
import com.example.mvvmdefault.R
import com.example.mvvmdefault.databinding.ItemPhotoBinding

class MainAdapter(private val onItemClicked: (Photo) -> Unit) :
    RecyclerView.Adapter<MainViewHolder>() {

    private var photos = mutableListOf<Photo>()

    fun setPhotoList(photos: List<Photo>) {
        this.photos = photos.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val photo = photos[position]
        holder.bind(photo, onItemClicked)
    }

    override fun getItemCount(): Int {
        return photos.size
    }


}

class MainViewHolder (val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind (photo: Photo, onItemClicked: (Photo) -> Unit) {

        binding.tvAuthor.text = photo.author
        binding.tvId.text = photo.id.toString()
        binding.tvWidth.text = photo.width.toString()
        binding.tvHeight.text = photo.height.toString()

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(photo.thumbnailUrl)
            .into(binding.ivThumbnail)

        itemView.setOnClickListener{
            onItemClicked(photo)
        }

    }

}
