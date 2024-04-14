package com.example.submissiondicodingpemula.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submissiondicodingpemula.activity.DetailMovieActivity
import com.example.submissiondicodingpemula.databinding.ItemVerticalBinding
import com.example.submissiondicodingpemula.model.genre.Genre
import com.example.submissiondicodingpemula.model.movie.Movie

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale


class ListMovieAdapter(private val movies: List<Movie>, private val listOfGenres: List<Genre>): RecyclerView.Adapter<ListMovieAdapter.ListViewHolder>() {


    class ListViewHolder(private val binding: ItemVerticalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieList: Movie, genreIds: List<Int>, listOfGenres: List<Genre> = listOf()) {

            val baseURL = "https://image.tmdb.org/t/p/"

            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

            val imagePath = movieList.poster_path
            val releaseDate = movieList.release_date
            val originalText = movieList.overview

            val maxCharacterLimit = 120
            val truncatedText = if (originalText.length > maxCharacterLimit) {
                originalText.substring(0, maxCharacterLimit) + "..."
            } else {
                originalText
            }

            val genreNames = mapGenreIdsToNames(genreIds, listOfGenres)

            with(binding) {

                Glide.with(root.context)
                    .load("$baseURL/w500$imagePath")
                    .into(ivPosterMovie)

                tvTitleMovie.text = movieList.title

                tvGenreMovie.text = genreNames.joinToString(", ")

                try {
                    val date = inputFormat.parse(releaseDate)
                    val outputDate = outputFormat.format(date!!)
                    tvReleaseDateMovie.text = root.context.getString(
                        com.example.submissiondicodingpemula.R.string.movie_release_date,
                        outputDate
                    )
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

                tvOverviewMovie.text = truncatedText
            }
        }

        private fun mapGenreIdsToNames(genreIds: List<Int>, genres: List<Genre>): List<String> {
            return genreIds.mapNotNull { genreId ->
                genres.find { it.id == genreId }?.name
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
        val movies: Movie = movies[position]

        holder.bind(movies, movies.genre_ids, listOfGenres)

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailMovieActivity::class.java)
            intentDetail.putExtra("movie", movies)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }


}