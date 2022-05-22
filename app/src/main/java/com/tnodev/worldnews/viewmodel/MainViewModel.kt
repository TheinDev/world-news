package com.tnodev.worldnews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnodev.worldnews.model.Article
import com.tnodev.worldnews.model.NewsResponse
import com.tnodev.worldnews.repository.NewsRepo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow


class MainViewModel constructor(private val newsRepo: NewsRepo) :
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

            val response = newsRepo.getBreakingNews(category,pageNumber);
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

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepo.insertNews(article);
    }
    fun getSavedNews(): Flow<List<Article>> = newsRepo.getSavedNews();

    private fun onError(message:String){

        erroMessage.postValue(message);
        loading.postValue(false);
    }
    override fun onCleared() {
        super.onCleared()
        job?.cancel();
    }

}