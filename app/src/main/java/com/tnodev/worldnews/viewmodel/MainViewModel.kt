package com.tnodev.worldnews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tnodev.worldnews.model.Article
import com.tnodev.worldnews.model.NewsResponse
import com.tnodev.worldnews.repository.NewsRepo
import kotlinx.coroutines.*


class MainViewModel constructor(private val mainRespository: NewsRepo) :
    ViewModel()
{

    val erroMessage = MutableLiveData<String>();

    val newsList = MutableLiveData<NewsResponse>();

    var job: Job? = null;

    val loading = MutableLiveData<Boolean>();

    private  val exceptionHandler = CoroutineExceptionHandler{

            _,throwable ->

        onError("Exception handled: ${throwable.localizedMessage}");

    }



    fun getBreakingNews(category : String, pageNumber: Int){

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true);

            val response = mainRespository.getBreakingNews(category,pageNumber);
            withContext(Dispatchers.Main){

                if(response.isSuccessful){

                    newsList.postValue(response.body());
                    loading.value = false;
                }
                else{

                    onError("Error: ${response.message()}")
                }
            }

        }
    }
    private fun onError(message:String){

        erroMessage.postValue(message);
        loading.postValue(false);
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel();
    }

}