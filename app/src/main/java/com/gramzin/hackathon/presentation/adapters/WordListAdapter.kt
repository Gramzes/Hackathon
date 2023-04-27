package com.gramzin.hackathon.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gramzin.hackathon.data.Word
import com.gramzin.hackathon.databinding.WordHolderBinding


class WordListAdapter(private val onClick: (Word) -> Unit): RecyclerView.Adapter<WordListAdapter.WordHolder>() {
    private var words = mutableListOf<Word>()

    class WordHolder(private val binding: WordHolderBinding,
                     private val onClick: (Word) -> Unit): RecyclerView.ViewHolder(binding.root){
        init {
            binding.rcView.layoutManager = LinearLayoutManager(binding.root.context)
        }

        fun bind(word: Word){
            binding.info.setOnClickListener { onClick(word) }
            binding.rcView.adapter = WordSublistAdapter(word)
            binding.wordInfo.text = "${word.word}, ${word.children.size} раз"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val binding = WordHolderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return WordHolder(binding, onClick)
    }

    override fun getItemCount() = words.size

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        val word = words[position]
        holder.bind(word)
    }

    fun addWords(words:Collection<Word>){
        this.words = words.toMutableList()
        notifyDataSetChanged()
    }
}