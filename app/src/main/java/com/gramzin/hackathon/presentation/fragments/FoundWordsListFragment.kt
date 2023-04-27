package com.gramzin.hackathon.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gramzin.hackathon.data.Word
import com.gramzin.hackathon.databinding.FragmentFoundWordsListBinding
import com.gramzin.hackathon.presentation.adapters.WordContextAdapter
import com.gramzin.hackathon.presentation.viewModel.FoundWordsViewModel

class FoundWordsListFragment : Fragment() {
    private lateinit var binding: FragmentFoundWordsListBinding
    private var adapter = WordContextAdapter()
    private val viewModel: FoundWordsViewModel by viewModels{
        FoundWordsViewModel.Factory(requireArguments().getString("root")!!,
            requireArguments().getParcelable<Word>("word")!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoundWordsListBinding.inflate(inflater, container, false)
        binding.rcView.layoutManager = LinearLayoutManager(requireContext())
        adapter = WordContextAdapter()
        binding.rcView.adapter = adapter
        viewModel.wordsContext.observe(viewLifecycleOwner){
            adapter.addWordContext(it)
        }
        return binding.root
    }
}