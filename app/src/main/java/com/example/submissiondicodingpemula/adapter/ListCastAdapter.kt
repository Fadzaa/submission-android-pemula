package com.example.submissiondicodingpemula.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissiondicodingpemula.databinding.ItemCastHorizontalBinding
import com.example.submissiondicodingpemula.model.cast.Cast


class ListCastAdapter(private val cast: List<Cast>) : RecyclerView.Adapter<ListCastAdapter.ListViewHolder>() {

    class ListViewHolder(private val binding: ItemCastHorizontalBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(actorList: Cast) {
            val baseURL = "https://image.tmdb.org/t/p/"

            val imagePath = actorList.profile_path

            with(binding) {
                Glide.with(root.context)
                    .load(baseURL + "w500" + imagePath)
                    .into(ivCastImage)

                tvCastName.text = actorList.name
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder {
        val binding = ItemCastHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val cast: Cast = cast[position]
        holder.bind(cast)
    }

    override fun getItemCount(): Int {
        return cast.size
    }
}