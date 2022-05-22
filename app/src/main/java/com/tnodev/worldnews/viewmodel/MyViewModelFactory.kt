package com.tnodev.worldnews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tnodev.worldnews.repository.NewsRepo
import java.lang.IllegalArgumentException

class MyViewModelFactory constructor( private  val respository: NewsRepo
) : ViewModelProvider.Factory{



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  if(modelClass.isAssignableFrom(MainViewModel::class.java)){


            MainViewModel(this.respository) as T;
        }
        else{

            throw  IllegalArgumentException("view model not found")
        }
    }


}