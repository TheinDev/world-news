package com.tnodev.worldnews.view.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tnodev.worldnews.api_service.RetrofitService
import com.tnodev.worldnews.databinding.FragmentDashboardBinding
import com.tnodev.worldnews.repository.NewsDatabase
import com.tnodev.worldnews.repository.NewsRepo
import com.tnodev.worldnews.viewmodel.MainViewModel
import com.tnodev.worldnews.viewmodel.MyViewModelFactory

class NewsFragment : Fragment() {

    private lateinit var dashboardViewModel: MainViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {






        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var data = savedInstanceState;
        val getBd = arguments;
        var item: String = getBd?.getString("item") ?: "";



        val newsRepository = NewsRepo(NewsDatabase(requireView().context))
        dashboardViewModel =  ViewModelProvider(this, MyViewModelFactory(newsRepository))
            .get(MainViewModel::class.java);



        val progress = binding.progressDialog
        dashboardViewModel.newsList
            .observe(viewLifecycleOwner, Observer {

            })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}