package com.gramzin.hackathon.presentation.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.gramzin.hackathon.data.Word
import com.gramzin.hackathon.data.toWord
import com.gramzin.hackathon.java.RootFinder
import com.gramzin.hackathon.java.TextProcessor
import com.gramzin.hackathon.java.aot.WordformMeaning

class TopWordsViewModel(private val app: Application, string: String): AndroidViewModel(app) {
    private val words_ = MutableLiveData<List<Word>>()
    val words = words_ as LiveData<List<Word>>

    init {
        getWords(string)
    }

    private fun getWords(string: String){
        val bfReader = app.assets.open("root/roots.txt").bufferedReader()
        WordformMeaning.archiveIS = app.assets.open("root/mrd")
        WordformMeaning.initMeth()
        RootFinder.txtFile = bfReader
        words_.value = TextProcessor(bfReader).process(string).map {
            it.toWord()
        }

    }

    class Factory(private val app: Application, private val string: String): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TopWordsViewModel(app, string) as T
        }
    }
}