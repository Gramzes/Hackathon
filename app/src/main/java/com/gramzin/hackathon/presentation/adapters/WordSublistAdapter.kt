package com.gramzin.hackathon.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gramzin.hackathon.data.Word
import com.gramzin.hackathon.databinding.SubwordHolderBinding

class WordSublistAdapter(private val word: Word):
    RecyclerView.Adapter<WordSublistAdapter.WordSublistHolder>() {

    class WordSublistHolder(private val binding: SubwordHolderBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(word: Word){
            binding.wordInfo.text = "${word.word}, ${word.indexes.size} раз"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordSublistHolder {
        val binding = SubwordHolderBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return WordSublistHolder(binding)
    }

    override fun onBindViewHolder(holder: WordSublistHolder, position: Int) {
        val word = word.children[position]
        holder.bind(word)
    }

    override fun getItemCount() = word.children.size

}