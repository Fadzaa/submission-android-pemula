package com.example.submissiondicodingpemula.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.submissiondicodingpemula.R
import com.example.submissiondicodingpemula.databinding.FragmentUpcomingBinding
import com.example.submissiondicodingpemula.databinding.ItemVerticalUpcomingBinding
import com.example.submissiondicodingpemula.model.MovieList

class ListMovieAdapter(private val listMovie: ArrayList<MovieList>): RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {
    class ListViewHolder(private val binding: ItemVerticalUpcomingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieList: MovieList) {
            with(binding) {
                tvTitleMovie.text = movieList.title
                tvGenreMovie.text = movieList.genre
                tvDurationMovie.text = movieList.duration
                tvReleaseDateMovie.text = movieList.release
                tvOverviewMovie.text = movieList.overview
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListMovieAdapter.ListViewHolder {
        val binding = ItemVerticalUpcomingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListMovieAdapter.ListViewHolder, position: Int) {
        val listMovie:MovieList = listMovie[position]
        holder.bind(listMovie)

    }

    override fun getItemCount(): Int {
        return listMovie.size
    }


}