package com.tnodev.worldnews.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tnodev.worldnews.adapter.MainAdapter
import com.tnodev.worldnews.databinding.FragmentDashboardBinding
import com.tnodev.worldnews.repository.NewsDatabase
import com.tnodev.worldnews.repository.NewsRepo
import com.tnodev.worldnews.util.AppCons.Companion.detailArticle
import com.tnodev.worldnews.viewmodel.MainViewModel
import com.tnodev.worldnews.viewmodel.MyViewModelFactory

class NewsFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private var _binding: FragmentDashboardBinding? = null

    var home:Home? = null;
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



        home = activity as Home;
        val newsRepository = NewsRepo(NewsDatabase(requireView().context))
        mainViewModel =  ViewModelProvider(this, MyViewModelFactory(newsRepository))
            .get(MainViewModel::class.java);


var adapter = MainAdapter();

        adapter.setOnItemClickListener {

           it.let {

              detailArticle = it;

               goDatail(home!!);
           }


        }

        binding.recyclerview.adapter = adapter;

mainViewModel.getBreakingNews(item,1)
        val progress = binding.progressDialog
        mainViewModel.newsList
            .observe(viewLifecycleOwner, Observer {
it.let {

    progress.visibility = View.INVISIBLE;
    adapter.setNewsItems(it.articles);
    adapter.notifyDataSetChanged();
}
            })
    }

    fun goDatail(start : Home){
        val intent = Intent(start , DetailView::class.java);
        start.startActivity(intent);

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}