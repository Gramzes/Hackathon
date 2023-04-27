package com.gramzin.hackathon.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.gramzin.hackathon.data.Word
import com.gramzin.hackathon.data.toWord
import com.gramzin.hackathon.java.TextProcessor
import kotlinx.coroutines.launch

class TopWordsViewModel(private val app: Application, string: String): AndroidViewModel(app) {
    private val words_ = MutableLiveData<List<Word>>()
    val words = words_ as LiveData<List<Word>>
    private val time_ = MutableLiveData<String>()
    val time = time_ as LiveData<String>
    init {
        getWords(string)
    }

    private fun getWords(string: String){
        viewModelScope.launch {
            val timeStart =System.nanoTime()
            val words = TextProcessor().process(string).map {
                it.toWord()
            }
            time_.postValue(((System.nanoTime() - timeStart)/1_000_000).toString() + "мс.")
            words_.postValue(words.sortedByDescending { it.word.length })
        }
    }

    class Factory(private val app: Application, private val string: String): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TopWordsViewModel(app, string) as T
        }
    }
}