package com.example.submissiondicodingpemula.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.submissiondicodingpemula.R
import com.example.submissiondicodingpemula.adapter.ListCastAdapter
import com.example.submissiondicodingpemula.databinding.ActivityDetailMovieBinding
import com.example.submissiondicodingpemula.model.movie.Movie
import com.example.submissiondicodingpemula.service.CastInterface
import com.example.submissiondicodingpemula.service.DetailInterface
import com.example.submissiondicodingpemula.service.RetrofitInstance
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat

import java.util.Locale

class DetailMovieActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityDetailMovieBinding

    private val IMAGE_BASE = "https://image.tmdb.org/t/p/original/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataMovie = extractMovieFromIntent()


        getDetailsAndCast(dataMovie)
    }

    private fun extractMovieFromIntent(): Movie? {

        return if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("movie", Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("movie")
        }
    }

    private fun setupUI(dataMovie: Movie?) {

        dataMovie?.let {
            binding.apply {

                ivShare.setOnClickListener {
                    dataMovie.let { movie ->
                        shareMovieDetails(movie)
                    }
                }

                actionShare.setOnClickListener{
                    finish()
                }

                formatAndDisplayReleaseDate(it.release_date)

                formatAndDisplayVoteAverage(it.vote_average)

                tvDetailMovieTitle.text = it.title
                tvDetailMovieOverview.text = it.overview
                tvDetailMovieVoteCount.text = String.format("(%s)", it.vote_count)


                Glide.with(this@DetailMovieActivity)
                    .load(IMAGE_BASE + it.backdrop_path)
                    .into(ivDetailMovieImage)


            }
        }
    }

    private fun shareMovieDetails(dataMovie: Movie) {
        val movieDetails = """
        Title: ${dataMovie.title}
        Overview: ${dataMovie.overview}
        Release Date: ${dataMovie.release_date}
        Vote Average: ${dataMovie.vote_average}
        """.trimIndent()




        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, movieDetails)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun formatAndDisplayReleaseDate(releaseDate: String?) {

        try {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(releaseDate!!)
            val outputDate = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(date!!)
            binding.tvReleaseDate.text = this.getString(R.string.movie_release_date, outputDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    private fun formatAndDisplayVoteAverage(voteAverage: Double?) {
        voteAverage?.let {
            val numVoteAvg = it

            when {
                numVoteAvg < 7 -> binding.tvDetailMovieVoteAverage.setTextAppearance(R.style.HeadingText_VoteRed)
                numVoteAvg < 8 -> binding.tvDetailMovieVoteAverage.setTextAppearance(R.style.HeadingText_VoteYellow)
                else -> binding.tvDetailMovieVoteAverage.setTextAppearance(R.style.HeadingText_VoteGreen)
            }

            val voteAverageFormat = String.format(Locale.US, "%.1f", numVoteAvg)
            binding.tvDetailMovieVoteAverage.text = voteAverageFormat
        }
    }

    private fun getDetailsAndCast(dataMovie: Movie?) {

        val detailService = RetrofitInstance.instance.create(DetailInterface::class.java)
        val castService = RetrofitInstance.instance.create(CastInterface::class.java)

        lifecycleScope.launchWhenCreated {

            binding.pbMovieDetails.isVisible = true

            val detailResponse = callService { detailService.getDetail(dataMovie?.id ?: 0, getString(R.string.api_key)) }
            val castResponse = callService { castService.getCredits(dataMovie?.id ?: 0, getString(R.string.api_key)) }

            if (detailResponse?.isSuccessful == true && castResponse?.isSuccessful == true) {

                setupUI(dataMovie)

                val detailMovie = detailResponse.body()!!
                val castMovie = castResponse.body()!!.cast


                val numRuntime = detailMovie.runtime.toInt()
                val hours = numRuntime / 60
                val remainingMinutes = numRuntime % 60
                val runtimeFormat = String.format("%dh %02dm", hours, remainingMinutes)

                val genreList = detailMovie.genres
                val genreNames = genreList.take(4).joinToString(", ") { it.name }

                val listCastAdapter = ListCastAdapter(castMovie)
                binding.pbMovieDetails.isVisible = false
                binding.rvCast.adapter = listCastAdapter
                binding.rvCast.setHasFixedSize(true)
                binding.rvCast.layoutManager = LinearLayoutManager(this@DetailMovieActivity, LinearLayoutManager.HORIZONTAL, false)

                binding.tvDetailMovieRuntime.text = runtimeFormat
                binding.tvDetailMovieGenre.text = genreNames

                binding.pbMovieDetails.isVisible = false
            } else {

                binding.pbMovieDetails.isVisible = false
            }
        }
    }


    private  inline fun <reified T> callService(serviceCall: () -> Response<T>): Response<T>? {
        return try {
            serviceCall.invoke()
        } catch (e: Exception) {
            // Handle exceptions and show a Toast
            binding.pbMovieDetails.isVisible = false
            Toast.makeText(this@DetailMovieActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            null
        }
    }

}