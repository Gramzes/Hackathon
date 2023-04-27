package com.gramzin.hackathon.presentation.viewModel

import androidx.lifecycle.*
import com.gramzin.hackathon.data.BookService
import com.gramzin.hackathon.data.Word
import kotlinx.coroutines.launch

class FoundWordsViewModel(private val string: String, private val word: Word): ViewModel() {
    val wordsContext_ = MutableLiveData<List<String>>()
    val wordsContext = wordsContext_ as LiveData<List<String>>

    init {
        viewModelScope.launch {
            wordsContext_.postValue(BookService.getWordsContext(string, word, 40))
        }
    }

    class Factory(private val string: String, private val word: Word): ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FoundWordsViewModel(string, word) as T
        }
    }
}