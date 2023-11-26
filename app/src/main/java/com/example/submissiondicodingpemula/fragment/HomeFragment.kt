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

        binding.rvUpcoming.setHasFixedSize(true)
        binding.rvUpcoming.layoutManager = LinearLayoutManager(requireContext())

        val upcomingService = RetrofitInstance.instance.create(UpcomingInterface::class.java)
        val genresService = RetrofitInstance.instance.create(GenreInterface::class.java)


        lifecycleScope.launchWhenCreated {
            binding.progressBarUpcoming.isVisible = true

            val upcomingResponse = try {
                upcomingService.getUpcoming(getString(R.string.api_key))
            } catch (e: Exception) {
                binding.progressBarUpcoming.isVisible = false
                Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
                return@launchWhenCreated
            }

            val genresResponse = try {
                genresService.getGenres(getString(R.string.api_key))
            } catch (e: Exception) {
                binding.progressBarUpcoming.isVisible = false
                Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show()
                return@launchWhenCreated
            }


            if (upcomingResponse.isSuccessful && upcomingResponse.body() != null && genresResponse.isSuccessful && genresResponse.body() != null) {
                val upcomingList = upcomingResponse.body()!!
                val listOfGenre = genresResponse.body()!!
                binding.progressBarUpcoming.isVisible = false
                binding.rvUpcoming.adapter = ListMovieAdapter(upcomingList.results, listOfGenre.genres)
            } else {
                binding.progressBarUpcoming.isVisible = false
            }


        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}