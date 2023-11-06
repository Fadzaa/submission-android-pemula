package com.example.submissiondicodingpemula.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissiondicodingpemula.databinding.ItemVerticalUpcomingBinding
import com.example.submissiondicodingpemula.model.MovieResult



class ListMovieAdapter(private val movies: List<MovieResult>): RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {
    class ListViewHolder(private val binding: ItemVerticalUpcomingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieList: MovieResult) {
            val baseURL = "https://image.tmdb.org/t/p/"

            val imagePath = movieList.poster_path
            with(binding) {
                Glide.with(root.context)
                    .load(baseURL + "w500" + imagePath)
                    .into(ivPosterMovie)
                tvTitleMovie.text = movieList.title
                tvGenreMovie.text = movieList.genre_ids.toString()
//                tvDurationMovie.text = movieList.duration
                tvReleaseDateMovie.text = movieList.release_date
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
        val movies:MovieResult = movies[position]
        holder.bind(movies)

    }

    override fun getItemCount(): Int {
        return movies.size
    }


}