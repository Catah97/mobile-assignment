package com.example.rocketapp.rocket.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.rocketapp.R
import com.example.rocketapp.databinding.RowRocketPhotoBinding

class RocketPhotosAdapter(
    diffCallback: DiffUtil.ItemCallback<String> = RocketPhotosAdapterDiffItemCallback()
) : ListAdapter<String, RocketPhotosAdapter.RocketPhotosViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketPhotosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowRocketPhotoBinding.inflate(inflater, parent, false)
        return RocketPhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RocketPhotosViewHolder, position: Int) {
        val item = getItem(position)
        val imgRocketPhoto = holder.imgRocketPhoto
        val roundRadius = imgRocketPhoto.context.resources.getDimensionPixelSize(R.dimen.rocket_photo_cornes_round)
        Glide
            .with(imgRocketPhoto)
            .load(item)
            .centerCrop()
            .transform(RoundedCorners(roundRadius))
            .placeholder(R.drawable.rocket_photo_placeholder)
            .into(imgRocketPhoto)
    }

    class RocketPhotosViewHolder(binding: RowRocketPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgRocketPhoto = binding.imgRocketPhoto
    }

    class RocketPhotosAdapterDiffItemCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            // There is no way how to detect if photo has changed
            return true
        }
    }
}
