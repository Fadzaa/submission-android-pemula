package com.example.submissiondicodingpemula.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissiondicodingpemula.databinding.ItemVerticalBinding
import com.example.submissiondicodingpemula.model.MovieResult

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale


class ListMovieAdapter( private val movies: List<MovieResult>): RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {
    class ListViewHolder(private val binding: ItemVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieList: MovieResult) {
            val baseURL = "https://image.tmdb.org/t/p/"

            val imagePath = movieList.poster_path

            val releaseDate = movieList.release_date

            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

            val maxCharacterLimit = 120
            val originalText = movieList.overview
            val truncatedText = if (originalText.length > maxCharacterLimit) {
                originalText.substring(0, maxCharacterLimit) + "..."
            } else {
                originalText
            }

            with(binding) {
                Glide.with(root.context)
                    .load(baseURL + "w500" + imagePath)
                    .into(ivPosterMovie)


                tvTitleMovie.text = movieList.title

                tvGenreMovie.text = movieList.genre_ids.toString()

//                tvDurationMovie.text = movieList.duration
                try {
                    val date = inputFormat.parse(releaseDate)
                    val outputDate = outputFormat.format(date!!)
                    println(outputDate)


                    tvReleaseDateMovie.text = root.context.getString(
                        com.example.submissiondicodingpemula.R.string.release_date,
                        outputDate
                    )
                } catch (e: ParseException) {
                    e.printStackTrace()
                }




                tvOverviewMovie.text = truncatedText
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder {
        val binding = ItemVerticalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val movies:MovieResult = movies[position]
        holder.bind(movies)

    }

    override fun getItemCount(): Int {
        return movies.size
    }


}