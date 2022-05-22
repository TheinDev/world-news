package com.tnodev.worldnews.view

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.bumptech.glide.Glide
import com.tnodev.worldnews.R
import com.tnodev.worldnews.databinding.ActivityDetailViewBinding
import com.tnodev.worldnews.repository.NewsDatabase
import com.tnodev.worldnews.repository.NewsRepo
import com.tnodev.worldnews.viewmodel.MainViewModel
import com.tnodev.worldnews.viewmodel.MyViewModelFactory

class DetailView : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDetailViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var mainViewModel : MainViewModel?;



        val newsRepository = NewsRepo(NewsDatabase(this));

        mainViewModel =  ViewModelProvider(this, MyViewModelFactory(newsRepository))
            .get(MainViewModel::class.java);
        var article = mainViewModel.detailArticle;
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Added To Favorite", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            article?.let {

                mainViewModel.saveArticle(it);
            }
        }

        Glide.with(applicationContext)
            .load(article?.urlToImage)
            .into(binding.detailImage);

        binding.detailTittle.setText(article?.title);
        binding.detailDate.setText(article?.publishedAt);
        binding.detailText.setText(article?.description);

    }


}