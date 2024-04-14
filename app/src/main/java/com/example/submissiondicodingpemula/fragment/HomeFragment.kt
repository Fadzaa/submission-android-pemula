package com.example.submissiondicodingpemula.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submissiondicodingpemula.R
import com.example.submissiondicodingpemula.adapter.ListMovieAdapter
import com.example.submissiondicodingpemula.databinding.FragmentHomeBinding
import com.example.submissiondicodingpemula.service.GenreInterface
import com.example.submissiondicodingpemula.service.RetrofitInstance
import com.example.submissiondicodingpemula.service.UpcomingInterface
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val upcomingService = RetrofitInstance.instance.create(UpcomingInterface::class.java)
        val genresService = RetrofitInstance.instance.create(GenreInterface::class.java)


        lifecycleScope.launchWhenCreated {
            binding.progressBarUpcoming.isVisible = true

            val upcomingResponse = callService { upcomingService.getUpcoming(getString(R.string.api_key)) }
            val genresResponse = callService { genresService.getGenres(getString(R.string.api_key)) }

            if (upcomingResponse?.isSuccessful == true && genresResponse?.isSuccessful == true) {
                val upcomingList = upcomingResponse.body()!!.results
                val listOfGenre = genresResponse.body()!!.genres
                val listMovieAdapter = ListMovieAdapter(upcomingList, listOfGenre)

                with(binding) {
                    progressBarUpcoming.isVisible = false
                    rvUpcoming.adapter = listMovieAdapter
                    rvUpcoming.setHasFixedSize(true)
                    rvUpcoming.layoutManager = LinearLayoutManager(requireContext())
                }
            } else {
                binding.progressBarUpcoming.isVisible = false
            }
        }
    }

    private suspend inline fun <reified T> callService(serviceCall: suspend () -> Response<T>): Response<T>? {
        return try {
            serviceCall.invoke()
        } catch (e: Exception) {
            binding.progressBarUpcoming.isVisible = false
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
            null
        }
    }







}