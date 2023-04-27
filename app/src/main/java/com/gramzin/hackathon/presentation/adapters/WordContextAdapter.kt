package com.gramzin.hackathon.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gramzin.hackathon.databinding.WordContextHolderBinding

class WordContextAdapter: RecyclerView.Adapter<WordContextAdapter.WordContextHolder>() {
    lateinit var binding: WordContextHolderBinding
    private var strings = mutableListOf<String>()

    class WordContextHolder(private val binding: WordContextHolderBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(string:String){
            binding.textView4.text = string
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordContextHolder {
        binding = WordContextHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordContextHolder(binding)
    }

    override fun getItemCount() = strings.size

    override fun onBindViewHolder(holder: WordContextHolder, position: Int) {
        holder.bind(strings[position])
    }

    fun addWordContext(wordContext: Collection<String>){
        strings = wordContext.toMutableList()
        notifyDataSetChanged()
    }
}